package DFS_BFS.Day250228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week02_DFS_BFS {
    static List<Integer>[] adjList;
    static boolean[] visited;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int v = Integer.parseInt(st.nextToken()); // 시작 노드

        int[][] graph = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] i : graph) {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }

        for (List<Integer> integers : adjList) {
            Collections.sort(integers);
        }

        result = new ArrayList<>();
        dfs(v);
        for (int i : result) {
            System.out.print(i + " ");
        }
        visited = new boolean[n + 1];
        result = new ArrayList<>();
        bfs(v);
        System.out.println();
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    static void dfs(int i) {
        result.add(i);
        visited[i] = true;

        for (int now : adjList[i]) {
            if (!visited[now]) {
                visited[now] = true;
                dfs(now);
            }
        }
    }

    static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        result.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {

            int poll = queue.poll();

            for (int i : adjList[poll]) {
                if (!visited[i]) {
                    visited[i] = true;
                    result.add(i);
                    queue.add(i);
                }

            }

        }

    }
}
