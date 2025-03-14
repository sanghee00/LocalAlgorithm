package DP.Day250310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Day02_피보나치수2 {
    static BigInteger[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        dp = new BigInteger[91];
        dp[0] = BigInteger.valueOf(0);
        dp[1] = BigInteger.valueOf(1);

        for (int i = 2; i < a + 1; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }

        System.out.println(dp[a]);
    }
}
