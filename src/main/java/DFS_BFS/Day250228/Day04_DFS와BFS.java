package DFS_BFS.Day250228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day04_DFS와BFS {
    public static List<Integer>[] adjList;
    public static List<Integer> result;
    public static boolean[] visited;

    public static void solution(int[][] graph, int startNode, int n) {
        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        result = new ArrayList<>();

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] i : graph) {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }

        for (int i = 0; i < adjList.length; i++) {
            Collections.sort(adjList[i]);
        }

        dfs(startNode);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
        result = new ArrayList<>();
        visited = new boolean[n + 1];
        bfs(startNode);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static void dfs(int node) {
        visited[node] = true;
        result.add(node);

        for (int now : adjList[node]) {
            if (!visited[now]) {
                dfs(now);
            }
        }
    }

    public static void bfs(int startNode) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            result.add(poll);

            for (int now : adjList[poll]) {
                if (!visited[now]) {
                    visited[now] = true;
                    queue.add(now);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int v = Integer.parseInt(st.nextToken()); // 시작할 노드

        int[][] graph = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        /*for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }*/

        solution(graph, v, n);
    }
}
