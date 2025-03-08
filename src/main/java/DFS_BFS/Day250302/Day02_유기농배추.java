package DFS_BFS.Day250302;

import java.util.*;
import java.io.*;

public class Day02_유기농배추 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int width;
    static int length;
    static int[][] square;
    static boolean[][] visited;
    static int cnt = 1;
    static List<Integer> total = new ArrayList<>();

    public static void solution(int l, int w) {
        visited = new boolean[l][w];
        width = w;
        length = l;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                if (square[i][j] == 1 && !visited[i][j])  {
                    dfs(i, j);
                    result.add(cnt);
                    cnt = 1;
                }
            }
        }

        total.add(result.size());
    }

    public static void dfs(int l, int w) {
        visited[l][w] = true;

        for (int i = 0; i < 4; i++) {
            int xx = dx[i] + w;
            int yy = dy[i] + l;

            if (xx >= 0 && yy >= 0 && xx < width && yy < length && !visited[yy][xx] && square[yy][xx] == 1) {
                visited[yy][xx] = true;
                cnt++;
                dfs(yy, xx);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        // 테스트 케이스 만큼 반복
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 가로 길이
            int n = Integer.parseInt(st.nextToken()); // 세로 길이
            int k = Integer.parseInt(st.nextToken()); // 받아야 되는 배추 갯수

            square = new int[n][m];

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                square[y][x] = 1;
            }

            solution(n, m);

        }


        for (int i : total) {
            System.out.println(i);
        }

    }
}