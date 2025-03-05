package dfs.Day250302;

import java.io.*;
import java.util.*;

public class Day01_유기농배추 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] square;
    static boolean[][] visited;
    static int w; // 너비
    static int l; // 높이
    static int k; // 배추 개수
    static int cnt = 1;

    public static void dfs(int x, int y) {
        visited[y][x] = true;

        /*
        System.out.println("-----------------------------------------------");
        for (int j = 0; j < l; j++) {
            for (int m = 0; m < w; m++) {
                System.out.print(visited[j][m] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
        */

        for (int i = 0; i < 4; i++) {
            int xx = dx[i] + x;
            int yy = dy[i] + y;

//            System.out.println(xx+ " " + yy);
            if (xx >= 0 && yy >= 0 && xx < w && yy < l && !visited[yy][xx] && square[yy][xx] == 1) {
                visited[yy][xx] = true;
                cnt++;
                dfs(xx, yy);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] result = new int[T];

        // 테스트 케이스 만큼 반복
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 가로
            l = Integer.parseInt(st.nextToken()); // 세로
            k = Integer.parseInt(st.nextToken()); // 심어져 있는 배추의 개수
            square = new int[l][w];
            visited = new boolean[l][w];
            List<Integer> resultList = new ArrayList<>();

            // 배추 개수 받음
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                square[y][x] = 1;
            }

            // 밭을 돈다.
            for (int j = 0; j < l; j++) {
                for (int m = 0; m < w; m++) {
                    if (square[j][m] == 1 && !visited[j][m]) {
                        dfs(m, j);
                        resultList.add(cnt);
                        cnt = 1;
                    }
                }
            }

            result[i] = resultList.size();
        }

        for (int i : result) {
            System.out.println(i);
        }
    }
}