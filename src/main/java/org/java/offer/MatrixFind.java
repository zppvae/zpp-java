package org.java.offer;

/**
 *
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * Consider the following matrix:
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 *
 * Given target = 5, return true.
 * Given target = 20, return false.
 *
 * @author zpp
 * @date 2018/10/24 16:05
 */
public class MatrixFind {

    /**
     * 从二位数组中查找目标元素
     * @param target
     * @param matrix
     * @return
     */
    public static boolean find(int target,int [][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        //c从右上角开始减
        int r = 0,c = cols - 1;

        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c]) {
                System.out.println("目标元素找到：行【"+r+"】列【"+c+"】");
                return true;
            } else if (target > matrix[r][c]) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }

    public static void main(Strings[] args){
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        find(21,matrix );
    }
}
