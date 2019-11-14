package com.zxx.demorepository.test.util;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: tuosen
 * @date: 16:01 2019-09-06
 * @description: 二维码工具
 */
@Slf4j
public class QRCodeUtil {

  private static final String CHARSET = "utf-8";
  private static final String FORMAT_NAME = "JPG";
  // 二维码尺寸
  private static final int QRCODE_SIZE = 300;
  // LOGO宽度
  private static final int WIDTH = 60;
  // LOGO高度
  private static final int HEIGHT = 60;

  private static BufferedImage createImage(String content, String logoImgPath, boolean needCompress)
      throws Exception {
    Map hints = new HashMap();
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
    hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
    hints.put(EncodeHintType.MARGIN, 1);
    BitMatrix bitMatrix =
        new MultiFormatWriter()
            .encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
    int width = bitMatrix.getWidth();
    int height = bitMatrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
      }
    }
    if (StringUtils.isBlank(logoImgPath)) {
      return image;
    }
    // 插入图片
    QRCodeUtil.insertImage(image, logoImgPath, needCompress);
    return image;
  }

  private static void insertImage(BufferedImage source, String logoImgPath, boolean needCompress)
      throws Exception {
    File file = new File(logoImgPath);
    if (!file.exists()) {
      log.error("文件不存在:{}", logoImgPath);
      return;
    }
    if (file.isDirectory()) {
      log.error("文件不能为文件夹:{}", logoImgPath);
      return;
    }

    Image src = ImageIO.read(new File(logoImgPath));
    int width = src.getWidth(null);
    int height = src.getHeight(null);
    if (needCompress) { // 压缩LOGO
      if (width > WIDTH) {
        width = WIDTH;
      }
      if (height > HEIGHT) {
        height = HEIGHT;
      }
      Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics g = tag.getGraphics();
      g.drawImage(image, 0, 0, null); // 绘制缩小后的图
      g.dispose();
      src = image;
    }
    // 插入LOGO
    Graphics2D graph = source.createGraphics();
    int x = (QRCODE_SIZE - width) / 2;
    int y = (QRCODE_SIZE - height) / 2;
    graph.drawImage(src, x, y, width, height, null);
    Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
    graph.setStroke(new BasicStroke(3f));
    graph.draw(shape);
    graph.dispose();
  }

  public static void encode(String content, String logoImgPath, String destPath, boolean needCompress)
      throws Exception {
    BufferedImage image = QRCodeUtil.createImage(content, logoImgPath, needCompress);
    mkdirs(destPath);
    ImageIO.write(image, FORMAT_NAME, new File(destPath));
  }

  public static BufferedImage encode(String content, String logoImgPath, boolean needCompress)
      throws Exception {
    BufferedImage image = QRCodeUtil.createImage(content, logoImgPath, needCompress);
    return image;
  }

  private static void mkdirs(String destPath) {
    File file = new File(destPath);
    // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
    if (!file.exists() && !file.isDirectory()) {
      file.mkdirs();
    }
  }

  public static void encode(String content, String logoImgPath, String destPath) throws Exception {
    QRCodeUtil.encode(content, logoImgPath, destPath, false);
  }

  public static void encode(String content, String destPath) throws Exception {
    QRCodeUtil.encode(content, null, destPath, false);
  }

  public static void encode(
      String content, String logoImgPath, OutputStream output, boolean needCompress) throws Exception {
    BufferedImage image = QRCodeUtil.createImage(content, logoImgPath, needCompress);
    ImageIO.write(image, FORMAT_NAME, output);
  }

  public static void encode(String content, OutputStream output) throws Exception {
    QRCodeUtil.encode(content, null, output, false);
  }

  public static String decode(File file) throws Exception {
    BufferedImage image = ImageIO.read(file);
    if (image == null) {
      return null;
    }
    BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
    Map hints = new HashMap();
    hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
    Result result = new MultiFormatReader().decode(bitmap, hints);
    return result.getText();
  }

  public static String decode(String path) throws Exception {
    return QRCodeUtil.decode(new File(path));
  }
}
