package DFS_BFS.Day250303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day04_토마토 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Tomato> queue = new ArrayDeque<>();
    static int m;
    static int n;
    static boolean[][] visited;
    static int[][] box;

    static class Tomato {
        int x;
        int y;
        int value;

        public Tomato(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public static void bfs() {

        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + tomato.x;
                int ny = dy[i] + tomato.y;

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[ny][nx] && box[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    box[ny][nx] = tomato.value + 1;
                    queue.add(new Tomato(nx, ny, tomato.value + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 1) {
                    queue.add(new Tomato(j, i, 1));
                    visited[i][j] = true;
                }

                box[i][j] = num;
            }
        }

        bfs();

        boolean result = false;
        int total = Integer.MIN_VALUE;
        for (int[] i : box) {
            for (int j : i) {
                if (j == 0) {
                    result = true;
                }
                total = Math.max(total, j);
            }
        }


        if (result) {
            System.out.println(-1);
        } else {
            System.out.println(total - 1);
        }
    }
}
