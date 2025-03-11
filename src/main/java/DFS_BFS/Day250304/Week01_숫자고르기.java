package DFS_BFS.Day250304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Week01_숫자고르기 {
    static int num;
    static boolean[] visited;
    static int[] arr;
    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine()) -  1;
        }

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            num = i;
            dfs(i);
            visited[i] = false;
        }

        System.out.println(result.size());
        Collections.sort(result);
        for (int i : result) {
            System.out.println(i + 1);
        }
    }

    public static void dfs(int i) {
        if (num == arr[i]) {
            result.add(i);
        }

        if (!visited[arr[i]]) {
            visited[arr[i]] = true;
            dfs(arr[i]);
            visited[arr[i]] = false;
        }
    }
}
