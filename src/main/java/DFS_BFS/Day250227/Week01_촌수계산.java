package DFS_BFS.Day250227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week01_촌수계산 {
    public static List<Integer>[] ajList;
    public static boolean[] visited;
    public static int cnt;
    public static int result = -1;

    public static void solution(int[][] arr, int n, int startNode, int endNode) {
        ajList = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            ajList[i] = new ArrayList<>();
        }

        for (int[] i : arr) {
            ajList[i[0]].add(i[1]);
            ajList[i[1]].add(i[0]);
        }

        dfs(startNode, endNode);
    }

    public static void dfs(int startNode, int endNode) {
        if (startNode == endNode) {
            result = cnt;
        }
        visited[startNode] = true;

        for (int now : ajList[startNode]) {
            if (!visited[now]) {
                visited[now] = true;
                cnt++;
                dfs(now, endNode);
                cnt--;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(arr, n, startNode, endNode);

        System.out.println(result);
    }
}