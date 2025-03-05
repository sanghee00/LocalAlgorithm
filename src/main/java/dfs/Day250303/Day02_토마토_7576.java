package dfs.Day250303;

import java.util.*;
import java.io.*;

public class Day02_토마토_7576 {
    static Tomato[][] box;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static class Tomato {
        int x;
        int y;
        int day;

        public Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void bfs(int M, int N, ArrayDeque<Tomato> queue) {

        while (!queue.isEmpty()) {
            Tomato poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = dx[i] + poll.x;
                int yy = dy[i] + poll.y;

                if (xx >= 0 && yy >= 0 && xx < M && yy < N && !visited[yy][xx] && box[yy][xx].day != -1) {
                    visited[yy][xx] = true;
                    box[yy][xx].day = poll.day + 1;
                    queue.add(box[yy][xx]);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<Tomato> queue = new ArrayDeque<>();

        int M = Integer.parseInt(st.nextToken()); // 가로
        int N = Integer.parseInt(st.nextToken()); // 세로

        box = new Tomato[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tomato = Integer.parseInt(st.nextToken());

                // 토마토가 있는 곳은 바로 큐에 넣는다.
                if (tomato == 1) {
                    visited[i][j] = true;
                    queue.add(new Tomato(j, i, tomato));
                }
                box[i][j] = new Tomato(j, i, tomato);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j].day != 0) {
                    bfs(M, N, queue);
                }
            }
        }


        System.out.println(check(N, M));
    }
    public static int check(int N, int M) {
        int max = box[0][0].day;
        int day;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                day = box[i][j].day;
                if (day == 0) {
                    return -1;
                }

                if (max < day) {
                    max = day;
                }
            }
        }

        return max - 1;
    }


}
