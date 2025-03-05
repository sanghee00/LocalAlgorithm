package dfs.Day250228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day01_DFS와BFS {

    public static List<Integer>[] edgeList;
    public static boolean[] visited;
    public static List<Integer> dfsResult = new ArrayList<>();
    public static List<Integer> bfsResult = new ArrayList<>();

    public static void solution(int[][] graph, int startNode, int n) {
        edgeList = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < edgeList.length; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int[] i : graph) {
            edgeList[i[0]].add(i[1]);
            edgeList[i[1]].add(i[0]);
        }

        for (List<Integer> integers : edgeList) {
            integers.sort(Comparator.comparingInt(o1 -> o1));
        }

//        System.out.println(Arrays.toString(edgeList));
        dfs(startNode);
        visited = new boolean[n + 1]; // 초기화
        bfs(startNode);
    }

    public static void dfs(int startNode) {
        dfsResult.add(startNode);
        visited[startNode] = true;

        for (int i : edgeList[startNode]) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs(int startNode) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visited[startNode] = true;
        queue.add(startNode);

        // 비어 있지 않으면
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            bfsResult.add(poll);

            for (int i : edgeList[poll]) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int v = Integer.parseInt(st.nextToken()); // 시작 정점

        int[][] graph = new int[m + 1][2];
        for (int i = 1; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        /*for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
*/

        solution(graph, v, n);

        for (int i : dfsResult) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int i : bfsResult) {
            System.out.print(i + " ");
        }
    }
}
