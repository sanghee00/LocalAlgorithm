package dfs.Day250228;

import java.util.*;
import java.io.*;

public class Week01_DFS와BFS {

    public static List<Integer>[] adjList;
    public static boolean[] visited;
    public static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int v = Integer.parseInt(st.nextToken()); // 시작 정점

        int[][] graph = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] i : graph) {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }

        for (int i = 0; i < adjList.length; i++) {
            Collections.sort(adjList[i]);
        }


        dfs(v);
        print();
        visited = new boolean[n + 1];
        result = new ArrayList<>();
        bfs(v);
        print();
    }

    public static void print() {
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void dfs(int startNode) {
        visited[startNode] = true;
        result.add(startNode);

        for (int now : adjList[startNode]) {
            if (!visited[now]) {
                visited[now] = true;
                dfs(now);
            }
        }

    }

    public static void bfs(int startNode) {
        Queue<Integer> queue = new ArrayDeque<>();
        visited[startNode] = true;
        queue.add(startNode);
        result.add(startNode);

        while (!queue.isEmpty()) {

            int poll = queue.poll();

            for (int i : adjList[poll]) {
                if (!visited[i]) {
                    result.add(i);
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
