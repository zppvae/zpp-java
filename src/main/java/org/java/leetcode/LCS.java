package org.java.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态规划-最长子序列
 *
 *             C[i-1,j-1] + 1           若i，j > 0，Xi = Yj
 *  C[i,j] =
 *			   Max(C[i,j-1],C[i-1,j])   若i，j > 0，Xi != Yj
 */
@Slf4j
public class LCS {
	public int findLCS(String A,String B){
		int n = A.length();
		int m = B.length();
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();
		int [][] dp = new int[n][m];
		for(int i = 0;i<n;i++){
			//第一列
			//每行第一个数 = 第一列的数
			if(a[i] == b[0]){
				dp[i][0] = 1;
				for(int j = i+1;j<n;j++){
					//同一行之后的数全为1
					dp[j][0] = 1;
				}
				break;
			}
		}
		//第一行
		for(int i = 0;i<m;i++){
			if(a[0] == b[i]){
				dp[0][i] = 1;
				for(int j = i+1;j<m;j++){
					dp[0][j] = 1;
				}
				break;
			}
		}
		for(int i = 1;i<n;i++){
			for(int j = 1;j<m;j++){
				if(a[i] == b[j]){
					dp[i][j] = dp[i-1][j-1]+1;
				}else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		for(int i = 0;i<n;i++){
			for(int j = 0;j<m;j++){
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		return dp[n-1][m-1]; //返回矩阵右下角的值，即最大值
	}
	
	public static void main(String [] args){
		LCS lcs = new LCS();
		int findLCS = lcs.findLCS("android", "random");
		System.out.println(findLCS);
	}
}
