package enjoy;

import org.junit.Test;

import java.io.File;

/**
 * @auther: kam
 * @date: 16:48 2019-01-25
 * @description: 图片转文字测试
 */
public class ImgTest {

    @Test
    public void testImg(){
        String img = "D:\\NewWorkSpace\\MybatisPlusTest\\DemoRepository\\src\\main\\java\\enjoy";
        String txt = "D:\\NewWorkSpace\\MybatisPlusTest\\DemoRepository\\src\\main\\java\\enjoy";
        ImageProcesser imageProcesser = new ImageProcesser();
        imageProcesser.batchImgFile(img,txt);
    }

}
