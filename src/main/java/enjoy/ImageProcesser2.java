package enjoy;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

@Data
@Slf4j
public class ImageProcesser2 {

    /**
     * 默认字符素材集
     */
    private static final char[] charset1 = {'M', '8', 'V', '|', ':', '.', ' '};

    /**
     * 字符画素材集
     */
    private char[] charset;

    /**
     * 储存转化后的字符串
     */
    private String imgString = "";

    /**
     * 使用指定字符集构造
     *
     * @param charset
     */
    public ImageProcesser2(char[] charset) {
        this.charset = charset;
    }

    /**
     * 使用默认字符集构造
     */
    public ImageProcesser2() {
        this.charset = charset1;
    }

    /**
     * 将图形文件转化为字符画字符串
     *
     * @param imagepath
     * @return
     */
    public ImageProcesser2 toBitmapConvert(String imagepath) {
        return toBitmapConvert(new File(imagepath));
    }

    /**
     * 将图形文件转化为字符画字符串
     *
     * @param imageFile
     * @return
     */
    private ImageProcesser2 toBitmapConvert(File imageFile) {
        if (!imageFile.exists()) {
            log.error("File is not exists!");
        }
        StringBuffer sb = new StringBuffer();
        try {
            //将图片文件装载如BufferedImage流
            BufferedImage buff = compressImage(ImageIO.read(imageFile));

            // 逐行扫描图像的像素点，读取RGB值，取其平均值，并从charset中获取相应的字符素材，并装载到sb中
            for (int x = 0; x < buff.getWidth(); x++) {
                for (int y = 0; y < buff.getHeight(); y++) {
                    sb.append(charset[(getRgbValue(buff, x, y) * charset.length - 1) / 255] + "");
                }
                sb.append("\r\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imgString = sb.toString();
        return this;
    }

    /**
     * 根据坐标获取rgb值
     *
     * @param buff
     * @param x
     * @param y
     */
    private static int getRgbValue(BufferedImage buff, int x, int y) {
        Color color = new Color(buff.getRGB(x, y));
        return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
    }

    /* 图像文件预处理:将图片压缩到 最长边为 100px */
    private BufferedImage compressImage(BufferedImage srcImg) {
        int h = srcImg.getHeight();
        int w = srcImg.getWidth();
        if (Math.max(h, w) <= 100) {
            return srcImg;
        }
        int newH = w > h ? 100 * h / w : 100;
        int newW = w < h ? 100 * w / h : 100;
        BufferedImage smallImg = new BufferedImage(newW, newH, srcImg.getType());
        Graphics g = smallImg.getGraphics();
        g.drawImage(srcImg, 0, 0, newW, newH, null);
        g.dispose();
        return smallImg;
    }

    /**
     * 将字符串保存为.txt文件
     *
     * @param fileName
     */
    private void saveAsTxt(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            for (int i = 0; i < imgString.length(); i++) {
                out.print(imgString.charAt(i));
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 批处理图像文件
     *
     * @param srcfile
     * @param tragetfile
     * @throws FileNotFoundException
     */
    public void batchImgFile(String srcfile, String tragetfile) {
        File srcFolder = new File(srcfile);
        File tragetFolder = new File(tragetfile);
        if (!tragetFolder.exists() ||
                !tragetFolder.isDirectory()) {
            tragetFolder.mkdirs();
        }
        ImageProcesser2 processer = new ImageProcesser2();
        ArrayList<File> fileList = Lists.newArrayList(srcFolder.listFiles());
        fileList.stream()
                .filter(file -> !file.isFile())
                .forEach(file -> {
                    processer.toBitmapConvert(file);
                    processer.saveAsTxt(tragetfile + "/" + file.getName() + ".txt");
                });
        log.error("All img were converted!");
    }

}
