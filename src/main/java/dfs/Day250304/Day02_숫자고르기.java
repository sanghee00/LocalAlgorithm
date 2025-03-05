package dfs.Day250304;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day02_숫자고르기 {
    static boolean[] checked;
    static int num;
    static int[] arr;
    static List<Integer> result = new ArrayList<>();

    public static void dfs(int i) {
        if (num == arr[i]) {
            result.add(arr[i]);
        }

        if (!checked[arr[i]]) {
            checked[arr[i]] = true;
            dfs(arr[i]);
            checked[arr[i]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        checked = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }

        for (int i = 0; i < n; i++) {
            checked[i] = true;
            num = i;
            dfs(i);
            checked[i] = false;
        }

        System.out.println(result.size());
        Collections.sort(result);
        for (int i : result) {
            System.out.println(i + 1);
        }

    }
}
