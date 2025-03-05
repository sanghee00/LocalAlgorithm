package dfs.Day250301;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day04_단지번호붙이기 {
    static int[][] danji;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int cnt = 1;
    static List<Integer> resultList = new ArrayList<>();

    public static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && danji[i][j] != 0) {
                    dfs(i, j);
                    resultList.add(cnt);
                    cnt = 1;
                }
            }
        }
    }

    public static void dfs(int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int xx = dx[i] + x;
            int yy = dy[i] + y;

            if (xx >= 0 && yy >= 0 && xx < N && yy < N && !visited[yy][xx] && danji[yy][xx] != 0) {
                visited[yy][xx] = true;
                cnt++;
                dfs(yy, xx);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        danji = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String string = br.readLine();
            for (int j = 0; j < N; j++) {
                danji[i][j] = string.charAt(j) - '0';
            }
        }


        /*for (int[] i : danji) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }*/

        solution();
        System.out.println(resultList.size());
        Collections.sort(resultList);
        for (int i : resultList) {
            System.out.println(i);
        }
    }


}
