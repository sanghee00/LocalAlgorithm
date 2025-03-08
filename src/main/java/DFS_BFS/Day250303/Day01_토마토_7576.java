package DFS_BFS.Day250303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day01_토마토_7576 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Tomato[][] box;
    static boolean[][] visited;
    static int n;
    static int m;
    static ArrayList<Xy> xyIndex = new ArrayList<>();

    static class Tomato {
        int day;
        int x;
        int y;

        public Tomato(int day, int x, int y) {
            this.day = day;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
//            return " | " + "day " + day + " x: " + x + "y: " + y + " | ";
            return " | " + "day " + day + " | ";
        }
    }

    static class  Xy {
        int x;
        int y;

        public Xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x : " + x + " y : " + y;
        }

    }

    public static int bfs(ArrayList<Xy> xy) {
        ArrayDeque<Tomato> queue = new ArrayDeque<>();
        int result = 0;
        for (Xy xy1 : xy) {
            queue.add(box[xy1.y][xy1.x]);
            visited[xy1.y][xy1.x] = true;
        }

        while (!queue.isEmpty()) {
            Tomato pollTomato = queue.poll();
            result = pollTomato.day;

            for (int i = 0; i < 4; i++) {
                int xx = dx[i] + pollTomato.x;
                int yy = dy[i] + pollTomato.y;

                if (xx >= 0 && yy >= 0 && xx < m && yy < n && !visited[yy][xx] && box[yy][xx].day != -1) {
                    visited[yy][xx] = true;
                    box[yy][xx] = new Tomato(pollTomato.day + 1, xx, yy);
                    queue.add(box[yy][xx]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 가로
        n = Integer.parseInt(st.nextToken()); // 세로

        box = new Tomato[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = new Tomato(Integer.parseInt(st.nextToken()), j, i);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j].day == 1) {
                    xyIndex.add(new Xy(j, i));
                }
            }
        }


        int endBfs = bfs(xyIndex);;
        int total = 0;
        for (Tomato[] t : box) {
            for (Tomato tomato: t) {
                if (tomato.day == 0) {
                    total = -1;
                }
            }
        }

        if (total == -1) {
            System.out.println(total);
        } else {
            System.out.println(endBfs - 1);
        }
    }
}