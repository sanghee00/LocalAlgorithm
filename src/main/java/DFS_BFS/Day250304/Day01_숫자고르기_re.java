package DFS_BFS.Day250304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01_숫자고르기_re {
    static boolean[] checked;
    static int[] arr;
    static List<Integer> result = new ArrayList<>();
    static int num;

    public static void dfs(int i) {
        if (arr[i] == num) {
            result.add(i);
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

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }
        checked = new boolean[n];

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
