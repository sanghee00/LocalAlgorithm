package DFS_BFS.Day250301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01_단지번호붙이기 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static List<Integer> result = new ArrayList<>();
    static int cnt = 1;
    static boolean[][] visited;
    static int n;
    static int[][] square;

    public static void solution() {

        visited = new boolean[n][n];

        // 정사각형을 한번씩 돈다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (square[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j); // 좌표를 넘겨받음
                    result.add(cnt);
                    cnt = 1;
                }
            }
        }

    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;

        // 상하좌우
        for (int i = 0; i < 4; i++) {
            int xx = dx[i] + x;
            int yy = dy[i] + y;

            // -1이거나 n을 넘으면 범위를 넘은것으로 간주
            if (xx >= 0 && yy >= 0 && xx < n && yy <n && !visited[xx][yy] && square[xx][yy] == 1) {
                cnt++;
                dfs(xx, yy);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 정사각형 생성
        square = new int[n][n];

        for (int i = 0; i < n; i++) {
            String string = br.readLine();
            for (int j = 0; j < n; j++) {
                square[i][j] = string.charAt(j) - '0';
            }
        }


        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(square[i][j] + " ");
            }
            System.out.println();
        }*/

        solution();
        System.out.println(result.size());
        Collections.sort(result);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
