package com.zxx.demorepository.test.util;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @auther: tuosen
 * @date: 17:25 2019-09-05
 * @description: Pdf工具
 */
@Slf4j
public class PdfUtil {

  public static void main(String[] args) throws IOException {
    addImg(
        "D:\\一小时部署.pdf", "D:\\已添加水印.pdf", "D:\\微信图片_20190905174346.png", 220F, 220F, 00F, 200F, 1);
  }

  public static void addImg(
      @NotBlank String pdfPath,
      @NotBlank String destPath,
      @NotBlank String imgPath,
      float posX,
      float posY,
      float imgWidth,
      float imgHeight,
      int pageNum)
      throws IOException {
    File pdfFile = checkOrGetFile(pdfPath);
    File imgFile = checkOrGetFile(imgPath);
    if (pageNum <= 0) {
      throw new RuntimeException("pageNum页数异常");
    }

    PdfDocument pdfDocument = null;
    PdfReader reader = null;
    PdfWriter pdfWriter = null;
    try {
      reader = new PdfReader(pdfFile.getPath());
      pdfWriter = new PdfWriter(destPath);
      pdfDocument = new PdfDocument(reader, pdfWriter);
      PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument, pageNum);

      ImageData imageData = ImageDataFactory.create(imgFile.getPath());
      if (FloatUtil.isBlank(imgHeight)) {
        pdfCanvas.addImage(imageData, posX, posY, imgWidth, false);
      } else if (FloatUtil.isBlank(imgWidth)) {
        pdfCanvas.addImage(imageData, posX, posY, imgHeight, false, true);
      } else if (FloatUtil.isBlank(imgHeight) && FloatUtil.isBlank(imgWidth)) {
        pdfCanvas.addImage(imageData, posX, posY, false);
      }

      pdfCanvas.release();
    } finally {
      if (Objects.nonNull(pdfDocument)) {
        pdfDocument.close();
      }
      if (Objects.nonNull(pdfWriter)) {
        pdfWriter.close();
      }
      if (Objects.nonNull(reader)) {
        reader.close();
      }
    }
  }

  private static File checkOrGetFile(String filePath) {
    File file = new File(filePath);
    if (!file.exists() || !file.isFile() || file.isDirectory()) {
      throw new RuntimeException("文件:" + filePath + ",不存在或不是文件");
    }
    return file;
  }
}
