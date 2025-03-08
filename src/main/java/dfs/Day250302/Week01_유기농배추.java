package dfs.Day250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week01_유기농배추 {
    static int n;
    static int m;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t =  Integer.parseInt(br.readLine());
        int[] total = new int[t];

        for (int i = 0; i < t; i++) {

            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 가로
            n = Integer.parseInt(st.nextToken()); // 세로
            map = new int[n][m];
            visited = new boolean[n][m];
            List<Integer> result = new ArrayList<>();

            int k = Integer.parseInt(st.nextToken());

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = 1;
            }

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    if (!visited[j][l] && map[j][l] == 1) {
                        bfs(l, j);
                        result.add(cnt);
                        cnt = 1;
                    }
                }
            }

            total[i] = result.size();

        }

        for (int i : total) {
            System.out.println(i);
        }

    }

    static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + pollNode.x;
                int ny = dy[i] + pollNode.y;

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[ny][nx] && map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    cnt++;
                    queue.add(new Node(nx, ny));
                }
            }
        }

    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
