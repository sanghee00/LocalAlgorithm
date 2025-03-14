package DP.Day250313;
/*
step [10 20 15 25 10 20]
dp[0] = 0
dp[1] = 10
dp[2] = 30

dp[3] = dp[3 - 2] + step[3]
dp[3] = dp[3 - 3] + step[3] + step[3 - 1]

dp[i] = dp[i - 2] + step[i]
dp[i] = dp[i - 3] + step[i] + step[i - 1]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day01_계단오르기 {
    public static void main(String[] args) throws IOException {
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

        dp[1] = step[1];
        dp[2] = step[1] + step[2];
        for (int i = 3; i <= n; i++) {
            int a = dp[i - 2] + step[i];
            int b = dp[i - 3] + step[i] + step[i - 1];
            dp[i] = Math.max(a, b);
        }

        System.out.println(dp[n]);
    }

}
