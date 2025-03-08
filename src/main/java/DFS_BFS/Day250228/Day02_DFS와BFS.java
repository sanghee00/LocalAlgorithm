package DFS_BFS.Day250228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day02_DFS와BFS {

    public static List<Integer>[] adjList;
    public static List<Integer> dfsResult = new ArrayList<>();
    public static List<Integer> bfsResult = new ArrayList<>();
    public static boolean[] visited;

    public static void solution(int[][] graph, int n, int startNode) {
        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] i : graph) {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }

        for (List<Integer> integers : adjList) {
            integers.sort(Comparator.comparing(o1 -> o1));
        }

        // System.out.println(Arrays.toString(adjList));
        dfs(startNode);
        visited = new boolean[n + 1];
        bfs(startNode);
    }

    public static void dfs(int startNode) {
        visited[startNode] = true;
        dfsResult.add(startNode);

        for (int now : adjList[startNode]) {
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
            bfsResult.add(poll);

            for (int i : adjList[poll]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int v = Integer.parseInt(st.nextToken()); // 시작 노드

        int[][] graph = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(graph, n, v);

        for (int i : dfsResult) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : bfsResult) {
            System.out.print(i + " ");
        }
    }

}
