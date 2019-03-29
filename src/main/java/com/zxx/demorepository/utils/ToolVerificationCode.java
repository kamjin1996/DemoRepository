package com.zxx.demorepository.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @auther: kam
 * @date: 9:11 2019-03-29
 * @description: 验证码工具（弹窗）
 */
public class ToolVerificationCode extends JPanel {
    final private int width     = 60;      // 图片的宽度
    final private int height    = 20;      // 图片的高度
    final private int codeCount = 4;       // 验证码字符个数
    final private int lineCount = 200;     // 验证码干扰线数

    final private static char[] charCode = {
            // 去掉 'O'、'o'、'0'、'I'、'i'、'L'、'l'、'1'
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w','x', 'y', 'z',
            '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static final long serialVersionUID = 1L;

    /**
     * 生成码
     */
    public ToolVerificationCode createCode(Graphics g) {
        // 创建画笔
        Graphics2D graphics2D = (Graphics2D) g;
        // 填充矩形
        graphics2D.setColor(genRandColor(200, 250));
        graphics2D.fillRect(0, 0, width, height);
        // 画边框
        graphics2D.setColor(genRandColor(0, 20));
        graphics2D.drawRect(0, 0, width - 1, height - 1);
        // 设置字体
        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        // 干扰线
        Random random = new Random();
        int lineWidth = 2; // 干扰线长度
        for (int i = 0; i < lineCount; i++) {
            graphics2D.setColor(genRandColor(150, 200));
            int x1 = random.nextInt(width - lineWidth - 1) + 1;
            int y1 = random.nextInt(height - lineWidth - 1) + 1;
            int x2 = random.nextInt(lineWidth);
            int y2 = random.nextInt(lineWidth);
            graphics2D.drawLine(x1, y1, x1 + x2, y1 + y2);
        }
        // 生成码
        for (int i = 0; i < codeCount; i++) {
            String str = String.valueOf(charCode[random.nextInt(charCode.length)]);
            graphics2D.setColor(genRandColor(20, 130));
            graphics2D.drawString(str, 15 * i + random.nextInt(5), random.nextInt(5) + 13);
        }
        graphics2D.dispose();

        return this;
    }

    // 随机颜色范围
    private Color genRandColor(int fc, int bc) {
        Random random = new Random();
        fc = fc > 255 ? 255 : fc;
        bc = bc > 255 ? 255 : bc;

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        createCode(g);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.add(new ToolVerificationCode());
        jFrame.setBounds(0, 0, 200, 100);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

}