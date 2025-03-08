package DFS_BFS.Day250304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day04_숫자고르기 {
    public static boolean[] checked;
    public static int[] arr;
    public static int num;
    public static List<Integer> result = new ArrayList<>();

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

        for (int i : result) {
            System.out.println(i + 1);
        }


    }

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
}
