package DP.Day250308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day01_피보나치함수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int[] fibo1 = new int[41];
            int[] fibo2 = new int[41];
            fibo1[0] = 1;
            fibo1[1] = 0;

            fibo2[0] = 0;
            fibo2[1] = 1;

            int a = Integer.parseInt(br.readLine());
            for (int j = 2; j <= a; j++) {
                fibo1[j] = fibo1[j - 1] + fibo1[j - 2];
                fibo2[j] = fibo2[j - 1] + fibo2[j - 2];
            }
            System.out.println(fibo1[a] + " " + fibo2[a]);
        }


    }

}
