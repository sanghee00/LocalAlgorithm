package DP.Day250313;

import java.io.*;

public class Day02_계단오르기 {
/*
*   dp[0] = 0
*   dp[1] = 10
*   dp[2] = 30
*
*   세 칸 연속은 안됨
*   case1. dp의 4를 구한다고 했을때 3을 건너뛴 케이스 dp[2](뛴 곳) -> step[4]
*   case2. dp의 4를 구한다고 했을때 3을 건너온 케이스 dp[1](뛴 곳) -> step[4] + step[3]
*   dp[4] = dp[1] + step[4] + step[4 - 1]
*   dp[4] = dp[2] + step[4]
*   dp[n] = dp[n - 3] + step[n] + step[n - 1]
*/
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int[] step = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            step[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(step[1]);
            return;
        }

        dp[0] = 0;
        dp[1] = step[1];
        dp[2] = step[1] + step[2];

        for (int i = 3; i <= n; i++) {
            int case1 = dp[i - 3] + step[i] + step[i - 1];
            int case2 = dp[i - 2] + step[i];
            dp[i] = Math.max(case1, case2);
        }

        System.out.println(dp[n]);
    }
}

