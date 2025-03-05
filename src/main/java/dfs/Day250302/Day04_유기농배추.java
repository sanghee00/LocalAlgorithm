package dfs.Day250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day04_유기농배추 {
    static boolean[][] visited;
    static int[][] square;
    static List<Integer> result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt;
    static int m;
    static int n;

    public static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int xx = dx[i] + x;
            int yy = dy[i] + y;

            if (xx >= 0 && yy >= 0 && xx < m && yy < n && !visited[yy][xx] && square[yy][xx] != 0) {
                visited[yy][xx] = true;
                cnt++;
                dfs(yy, xx);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 가로
            n = Integer.parseInt(st.nextToken()); // 세로
            int k = Integer.parseInt(st.nextToken()); // 배추가 심어져 있는 갯수

            square = new int[n][m];
            visited = new boolean[n][m];
            result = new ArrayList<>();

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                square[y][x] = 1;
            }

            /*for (int[] a :square) {
                for (int b : a) {
                    System.out.print(b);
                }
                System.out.println();
            }*/

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    if (square[j][l] == 1 && !visited[j][l]) {
                        dfs(j, l);
                        result.add(cnt);
                    }
                }
            }

            System.out.println(result.size());

        }

    }
}
