package DP.Day250310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문제123더하기4 {
    // 풀다가 포기 -> 브론즈부터 풀면서 점화식을 세우는 훈련을 할 예정
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[100001][4];

        dp[1][1] =1;
        dp[2][1] =1;
        dp[2][2] =1;
        dp[3][1] =1;
        dp[3][2] =1;
        dp[3][3] =1;

        for(int i=4; i<10001; i++) {
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1]+ dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }



        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            System.out.println(dp[num][1] + dp[num][2] + dp[num][3]);

        }
    }
}
