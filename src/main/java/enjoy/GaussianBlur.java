package enjoy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jhlabs.image.GaussianFilter;

/**
 * 简单高斯模糊算法
 *
 *
 * @return void [返回类型说明] @exception throws [违例类型] [违例说明] @see [类、类#方法、类#成员]
 */
public class GaussianBlur {

    public static void main(String[] args) throws IOException {
        File file = new File("http://image.qingqu.show/5KU5Ag7kgjLGpOMu03uLFBH1v6bq.png");
        BufferedImage blur = blur(file);
        // saveAs
        saveAs(blur, "jpg", new File("F:\\1111.jpg"));
    }

    public static BufferedImage blur(File data) throws IOException {
        BufferedImage img = ImageIO.read(data);
        GaussianFilter gaussianFilter = new GaussianFilter(10);
        gaussianFilter.filter(img, img);
        return img;
    }

    /**
     * 保存图片
     *
     * @param image
     *            图片
     * @param formatName
     *            格式名称
     * @param outFile
     *            输入文件
     * @throws IOException
     */
    public static void saveAs(BufferedImage image, String formatName, File outFile) throws IOException {
        if (formatName.equalsIgnoreCase("jpg") || formatName.equalsIgnoreCase("jpeg")) { // 重画一下，要么会变色
            BufferedImage tag;
            tag = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            image = tag;
        }
        ImageIO.write(image, formatName, outFile);
    }
}