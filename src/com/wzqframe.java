package com;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
/**
 * 五子棋下棋子的事件接口实现类
 *
 * @author 默默天
 */
@SuppressWarnings("serial")
 class wzqjframe extends JPanel implements wzqconfig {
    int[][] ChessArray = new int[17][17];

    public static void main(String[] args) {
        wzqjframe wzq = new wzqjframe();
        wzq.showUI();
    }

    /**
     * 下面是一个showUI的方法用于绘制五子棋盘
     */
    public void showUI() {
        /**
         * 写一个窗体，并且设置窗体的各类属性值
         */
        JFrame jf = new JFrame();
        jf.setTitle("泽鑫的五子棋板");
        jf.setSize(new Dimension(850, 700)); // 设置窗体大小
        jf.setLocationRelativeTo(null); // 设置居中
        jf.setDefaultCloseOperation(3);// 设置退出关闭所有程序
        // this指的就是JPanel类型的一个对象，因为我们让我们的类去继承了JPanel
        this.setPreferredSize(new Dimension(640, 700));
        this.setBackground(Color.white);

        /**
         * 给五子棋盘加一个背景，这样会显得更加的好看
         */
       // URL url = this.getClass().getResource("../image/悔棋.png");
        ImageIcon im2 = new ImageIcon(this.getClass().getResource("../image/qq1.jpg"));
        JLabel jl2 = new JLabel(im2);
        this.add(jl2);

        /**
         * 将我们的这个JPanel子类加到JFrame窗体上，因为待会我们会将五子棋下在面板上
         */
        jf.add(this, BorderLayout.WEST);
        JPanel jp2 = new JPanel();
        jp2.setPreferredSize(new Dimension(210, 700));
        jp2.setBackground(new Color(196, 131, 11));
        jf.add(jp2, BorderLayout.EAST);

        /**
         * 增添悔棋按钮
         */

        ImageIcon im1 = new ImageIcon(this.getClass().getResource("../image/悔棋.png"));

       // ImageIcon im1 = new ImageIcon("悔棋.png");
        JButton jb1 = new JButton(im1);
        jb1.setActionCommand("悔棋");// 图片添加的话按钮要设置actioncommand
        jb1.setPreferredSize(new Dimension(80, 40));
        jp2.add(jb1);

        JLabel jl1 = new JLabel("");
        jl1.setPreferredSize(new Dimension(40, 40));
        jp2.add(jl1);

        /**
         * 增添新局按钮
         */
        ImageIcon im4 = new ImageIcon(this.getClass().getResource("../image/qq4.png"));
        JButton jb2 = new JButton(im4);
        jb2.setPreferredSize(new Dimension(80, 40));
        jb2.setActionCommand("新局");
        jp2.add(jb2);

        /**
         * 写一个文本框，用于提示当前是哪一方在下棋子
         */
        JTextField jt1 = new JTextField("黑棋先手");
        jt1.setFont(new Font("宋体", Font.ITALIC, 16));
        jt1.setBackground(new Color(196, 131, 11));
        jt1.setPreferredSize(new Dimension(70, 40));
        jp2.add(jt1);

        // 设置可见
        jf.setVisible(true);

        // 在面板上获取Graphics类的对象
        Graphics g = this.getGraphics();

        /**
         * 给面板增添鼠标与动作监听器
         */
        wzqlistener m = new wzqlistener(g, ChessArray, this, jt1);
        this.addMouseListener(m);
        jb1.addActionListener(m);
        jb2.addActionListener(m);
    }

    /**
     * 重写面板绘制容器的方法,java在最小化，或者将所画图形拖到屏幕以下会自动调用paint方法
     */
    public void paint(Graphics g) {
        super.paint(g); // 调用父类的绘制容器的方法，这个是必须要的

        // System.out.println("=====>");
        /**
         * 绘制棋盘
         */
        for (int i = 0; i < 16; i++) {
            g.drawLine(START_X, START_Y + i * SIZE, START_X + SIZE * H_LINE,
                    START_Y + i * SIZE); // 画十六条横线
            g.drawLine(START_X + i * SIZE, START_Y, START_X + i * SIZE, START_Y
                    + SIZE * V_LINE); // 画十六条条竖线
        }
        /**
         * 重绘棋子
         */
        for (int i = 0; i < ChessArray.length; i++) {
            for (int j = 0; j < ChessArray[i].length; j++) {
                if (ChessArray[i][j] == 1) {
                    for (int m = 0; m <= 40; m++) {
                        g.setColor(new Color(m, m, m));
                        g.fillOval((j - 2) * 40 + 40 + m / 2, (i - 2) * 40 + 70
                                + m / 2, 40 - m, 40 - m);
                    }

                } else if (ChessArray[i][j] == -1) {
                    for (int m = 0; m < 40; m++) {
                        g.setColor(new Color(m + 210, m + 210, m + 210));
                        g.fillOval((j - 2) * 40 + 40 + m / 2, (i - 2) * 40 + 70
                                + m / 2, 40 - m, 40 - m);
                    }
                }
            }
        }

    }

}
