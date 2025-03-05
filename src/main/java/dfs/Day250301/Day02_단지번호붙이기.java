package dfs.Day250301;

import java.io.*;
import java.util.*;

public class Day02_단지번호붙이기 {
    static int[][] square;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static boolean[][] visited;
    static int cnt;
    static List<Integer> result;

    public static void solution(int n) {
        visited = new boolean[n][n];
        cnt = 1;
        result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (square[i][j] == 1 && !visited[i][j]) {
                    dfs(n, i, j);
                    result.add(cnt);
                    cnt = 1;
                }
            }
        }

    }

    public static void dfs(int n, int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int xx = dx[i] + x;
            int yy = dy[i] + y;

            if (xx >= 0 && yy >= 0 && xx < n && yy < n && !visited[xx][yy] && square[xx][yy] == 1) {
                visited[xx][yy] = true;
                dfs(n, xx, yy);
                cnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        square = new int[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                square[i][j] = s.charAt(j) - '0';
            }
        }

        solution(n);

        System.out.println(result.size());
        Collections.sort(result);
        for (int i : result) {
            System.out.println(i);
        }
    }

}
