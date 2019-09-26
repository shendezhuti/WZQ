package com;

/**
 * 判断是否五子相连
 *
 * @author 默默天
 *
 */

public class wzqwin {

    private int[][] chessArray;
    private int count=1;

    /**
     * 传递存储棋子的数组对象
     *
     * @param chessArray
     */
    public wzqwin(int[][] chessArray) {
        this.chessArray = chessArray;
    }

    /**
     * 验证是否五颗棋子相连
     *
     * @param h
     *            当前所下棋子的行
     * @param l
     *            当前所下棋子的列
     * @return 返回true表示五子相连，返回false表示没有
     */



    private int countH(int h, int l) {
        ; // 把当前所下棋子记录了


        // 向右进行统计
        for (int i = l + 1; i < chessArray.length; i++) {
            if (chessArray[h][l] == chessArray[h][i]) {
                //System.out.println("chessArray="+chessArray[h][l]);
                count++;
                System.out.println("向右的count="+count);
            } else {
                break;  //有一颗不同，则退出判断
            }
        }
        // 往左进行统计

        for (int i = l - 1; i > 0; i--) {
            if (chessArray[h][l] == chessArray[h][i]) {
                count++;
                System.out.println("向左的count="+count);
            } else {
                break;
            }
        }
        if(count==5){
            return count;
        }else{
            count=1;
        }
        // 往上进行统计
        for (int i = h - 1; i >0; i--) {
            if (chessArray[h][l] == chessArray[i][l]) {
                count++;
                System.out.println("向上的count="+count);
            } else {
                break;
            }
        }

        // 往下进行统计
        for (int i = h + 1; i <  chessArray.length; i++) {
            if (chessArray[h][l] == chessArray[i][l]) {
                count++;
                System.out.println("向下的count="+count);
            } else {
                break;
            }

        }
        if(count==5){
            return count;
        }else{
            count=1;
        }
        // 往左上进行统计
        for (int i = h - 1, j = l - 1; i > 0 && j >0; i--, j--) {
            if (chessArray[h][l] == chessArray[i][j]) {
                count++;
                System.out.println("向左上的count="+count);
            } else {
                break;
            }
        }
        // 往右下角进行统计
        for (int i = h + 1, j = l + 1; i < chessArray.length
                && j < chessArray.length; i++, j++) {
            if (chessArray[h][l] == chessArray[i][j]) {
                count++;
                System.out.println("向右下的count="+count);
            } else {
                break;
            }
        }
        if(count==5){
            return count;
        }else{
            count=1;
        }
        // 往左下角进行统计
        for (int i = h - 1, j = l + 1; i > 0 && j < chessArray.length; i--, j++) {
            if (chessArray[h][l] == chessArray[i][j]) {
                count++;
                System.out.println("向左下的count="+count);
            } else {
                break;
            }

        }

        // 往右上角进行统计
        for (int i = h + 1, j = l - 1; i < chessArray.length && j > 0; i++, j--) {
            if (chessArray[h][l] == chessArray[i][j]) {
                count++;
                System.out.println("向右上的count="+count);
            } else {
                break;
            }
        }
        if(count==5){
            return count;
        }else{
            count=1;
        }
        System.out.println("最终的count="+count);
        return count;
    }
    public boolean validatechess(int h, int l) {
        boolean state = false;
        if (countH(h, l) >= 5) {

            state = true;
        }
        return state;

    }
}
