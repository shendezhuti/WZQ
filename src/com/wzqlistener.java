package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 五子棋下棋子的事件接口实现类
 *
 * @author 默默天
 */
public class wzqlistener implements MouseListener, ActionListener {

    private Graphics gr;// 声明绘制棋子的画笔对象
    // 一般来说黑棋先手，白棋后手
    private int[][] ChessArray;
    public Boolean flag = false;// flag的值为false表明要下黑棋，如果flag的值为true表示要下白棋
    int[] getx = new int[16 * 16];
    int[] gety = new int[16 * 16];
    private int count = 0;
    private int xx, yy;
    private JPanel jp;
    private JTextField jt;

    /**
     * 构造方法
     *
     * @param //从五子棋frame类传递过来的画笔
     *            、数组、面板、文本对象
     */
    public wzqlistener(Graphics gr, int[][] ChessArray, JPanel jp, JTextField jt) {
        this.gr = gr;
        this.ChessArray = ChessArray;
        this.jp = jp;
        this.jt = jt;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

        // 获取鼠标按下的坐标
        int x = e.getX();
        int y = e.getY();
        // System.out.println("x=" + x + "y=" + y);

        float m = (float) (x - 20) / 40;
        int l = (int) Math.round(m) + 1;
        float n = (float) (y - 50) / 40;
        int h = (int) Math.round(n) + 1;
        System.out.println("l=" + l);
        System.out.println("h=" + h);
        // System.out.println(" "+ChessArray[h].length);

        /**
         * 为了防止棋子下在最上面造成数组越界的错误，设置此代码
         */
        if (h == 0) {
            ChessArray[h][l] = 10;
        }

        if (ChessArray[h][l] == 0) {
            if (!flag) {
                for (int i = 0; i < 40; i++) {
                    gr.setColor(new Color(i, i, i));
                    gr.fillOval(((l - 2) * 40 + 40 + i / 2), (h - 2) * 40 + 70
                            + i / 2, 40 - i, 40 - i);
                }
                flag = true;
                ChessArray[h][l] = 1;
                count++;
                getx[count] = h; // 存储所下棋子的位置
                gety[count] = l;
                tip();
            } else if (flag) {
                for (int i = 0; i < 40; i++) {
                    gr.setColor(new Color(i + 210, i + 210, i + 210));
                    gr.drawOval((l - 2) * 40 + 40 + i / 2, (h - 2) * 40 + 70
                            + i / 2, 40 - i, 40 - i);
                }
                flag = false;
                ChessArray[h][l] = -1;
                count++;
                getx[count] = h; // 存储所下棋子的位置
                gety[count] = l;
                tip();
            }

            wzqwin win = new wzqwin(ChessArray);
            if (win.validatechess(h, l) == true) {
                if (ChessArray[h][l] == 1) {
                    System.out.println("黑棋胜利！");
                    JOptionPane.showMessageDialog(null, "黑棋胜利");
                    restart();
                    jp.repaint();

                } else {
                    System.out.println("白棋胜利！");
                    JOptionPane.showMessageDialog(null, "白棋胜利");
                    restart();
                    jp.repaint();

                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if ("悔棋".equals(e.getActionCommand())) {
            huiqi();
            jp.repaint();
        }
        if ("新局".equals(e.getActionCommand())) {
            restart();
            jp.repaint();
        }

    }

    /**
     * 悔棋的方法
     */
    public void huiqi() {
        if (count > 0) {

            xx = getx[count];
            yy = gety[count];
            // System.out.println("xx="+xx+"yy="+yy);
            ChessArray[xx][yy] = 0;
            count--;
        }
    }

    /**
     * 重新开始游戏的方法
     */
    public void restart() {
        for (int i = 0; i < ChessArray.length; i++) {
            for (int j = 0; j < ChessArray.length; j++) {
                ChessArray[i][j] = 0;
                jt.setText("黑棋先手");
            }
        }
    }

    /**
     * 提示当前是谁下的方法
     */
    private  void tip() {
        if (flag == false) {
            jt.setText("轮到黑棋");
        } else {
            jt.setText("轮到白棋");
        }
    }
}
