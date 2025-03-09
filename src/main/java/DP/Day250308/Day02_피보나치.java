package DP.Day250308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day02_피보나치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());

            int[] fibo0 = new int[41];
            int[] fibo1 = new int[41];
            fibo0[0] = 1;
            fibo0[1] = 0;

            fibo1[0] = 0;
            fibo1[1] = 1;

            for (int j = 2; j < a + 1; j++) {
                fibo0[j] = fibo0[j - 1] + fibo0[j - 2];
                fibo1[j] = fibo1[j - 1] + fibo1[j - 2];
            }

            System.out.println(fibo0[a] + " " + fibo1[a]);
        }



    }
}


