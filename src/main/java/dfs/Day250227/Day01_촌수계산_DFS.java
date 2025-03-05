package dfs.Day250227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day01_촌수계산_DFS {
    public static List<Integer>[] person; // 받을 사람
    public static int count; // 카운트
    public static int result = -1; // 카운트
    public static boolean[] visited; // 방문 처리
    public static int end;

    public static void solution(int[][] graph, int start, int n) {
        person = new ArrayList[n + 1];

        for (int i = 0; i < person.length; i++) {
            person[i] = new ArrayList<>();
        }

        for (int[] edge : graph) {
            person[edge[0]].add(edge[1]);
            person[edge[1]].add(edge[0]);
        }

        visited = new boolean[n + 1];

        dfs(start);
    }

    public static void dfs(int start) {
        visited[start] = true; // 방문 후 true
        if (start == end) {
            result = count;
            return;
        }

        for (int n : person[start]) {
            // 방문하지 않았다면
            if(!visited[n]) {
                count++;
                dfs(n);
                count--;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p2 = Integer.parseInt(st.nextToken()); // 사람1
        int p1 = Integer.parseInt(st.nextToken()); // 사람2
        end = p2;

        int m = Integer.parseInt(br.readLine()); // 간선
        int[][] graph = new int[m + 1][2];

        for (int i = 1; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();

        }*/


        solution(graph, p1, n);

        System.out.println(result);
    }
}

