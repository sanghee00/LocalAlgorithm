package DFS_BFS.Day250303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week01_토마토 {
    static boolean[][] visited;
    static int[][] box;
    static Queue<Tomato> queue;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;
    static int m;

    static class Tomato {
        int x;
        int y;
        int cnt;

        public Tomato(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        box = new int[n][m];
        visited = new boolean[n][m];
        queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    visited[i][j] = true;
                    queue.add(new Tomato(j, i, 1));
                }
                box[i][j] = num;
            }
        }

        bfs();
        int result = Integer.MIN_VALUE;
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    result = -1;
                    break;
                } else if (total < box[i][j]) {
                    total = box[i][j];
                }
            }
        }

        if (result == -1) {
            System.out.println(result);
        } else {
            System.out.println(total - 1);
        }

    }

    static void bfs() {

        while (!queue.isEmpty()) {
            Tomato poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + poll.x;
                int ny = dy[i] + poll.y;

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[ny][nx] && box[ny][nx] != -1) {
                    visited[ny][nx] = true;
                    box[ny][nx] = poll.cnt + 1;
                    queue.add(new Tomato(nx, ny, poll.cnt + 1));
                }
            }

        }

    }
}
