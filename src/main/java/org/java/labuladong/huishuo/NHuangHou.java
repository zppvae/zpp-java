package org.java.labuladong.huishuo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * n皇后问题
 *
 * N * N 的棋盘，放置 N 个皇后，使它们不能相互攻击。【皇后攻击范围为同行、同列、同斜线（↖↙↗↘）】
 *
 * 这个问题本质上跟全排列问题差不多，决策树的每一层表示棋盘上的一行；每个节点可以做出的选择是，在改行的任意一列放置一个皇后。
 *
 * 按照 N 皇后问题的描述，我们为什么不检查左下角，右下角和下方的格子，只检查了左上角，右上角和上方的格子呢？
 * 因为皇后是一行一行从上往下放的，所以左下方，右下方和正下方不用检查（还没放皇后）；因为一行只会放一个皇后，所以每行不用检查。也就是最后只用检查上面，左上，右上三个方向。
 */
public class NHuangHou {

    public static void main(String[] args) {
        List<int[][]> res = new LinkedList<>();

        solveNQueen(8, res);

        System.out.println(res.size());
    }

    /**
     * 输入期盼边长 n，返回所有合法的放置
     * @param n
     * @return
     */
    public static List<int[][]> solveNQueen(int n, List<int[][]> res) {
        // 0 表示空，1 表示皇后
        int[][] board = new int[n][n];
        backtrack(board, 0, res);
        return res;
    }

    /**
     * 路径：    board 中小于 row 的那些行都已经成功地放置了皇后
     * 选择列表： 第 row 行的所有列都是放置皇后的选择
     * 结束条件： row 超过 board 的最后一行
     *
     * @param board
     * @param row
     */
    public static void backtrack(int[][] board, int row, List<int[][]> res) {
        // 触发结束条件
        if (row == board.length) {
            res.add(Arrays.copyOf(board, board.length));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            // 排除不合法的选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择，并修改状态
            board[row][col] = 1;

            // 进入下一行决策
            backtrack(board, row + 1, res);

            // backtrack 结束代表刚才做的选择的子树全结束，状态回到已做选择时。
            // 如果清除刚才的选择带来的状态改变，就可以重新做选择
            // 撤销选择
            board[row][col] = 0;
        }
    }

    /**
     * 是否可以在 board[row][col] 放置皇后
     *
     * @param board
     * @param row
     * @param col
     * @return
     */
    public static boolean isValid(int[][] board, int row, int col) {
        int n = board.length;

        // 检查列是否有皇后冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // 检查左上方是否有皇后冲突
        int i = row - 1;
        for (int j = col - 1; i >= 0 && j >= 0; i--,j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // 检查右上方是否有皇后冲突
        i = row - 1;
        for (int j = col + 1; i >= 0 && j < n; i--,j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

}
