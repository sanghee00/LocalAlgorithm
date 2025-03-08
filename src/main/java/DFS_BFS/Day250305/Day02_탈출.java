package DFS_BFS.Day250305;

import java.io.*;
import java.util.*;

public class Day02_탈출 {
    public static int r;
    public static int c;
    public static char[][] table;
    public static int result = -1;
    public static Queue<Node> water;
    public static Queue<Node> animal;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static class Node {
        int x;
        int y;
        int minute;

        public Node(int x, int y, int minute) {
            this.x = x;
            this.y = y;
            this.minute = minute;
        }
    }

    public static void bfs() {

        while (!animal.isEmpty()) {

            // 물 먼저
            int waterLen = water.size();
            for (int i = 0; i < waterLen; i++) {
                Node waterNode = water.poll();

                for (int j = 0; j < 4; j++) {
                    int x = dx[j] + waterNode.x;
                    int y = dy[j] + waterNode.y;

                    if (x >= 0 && y >= 0 && x < c && y < r && (table[y][x] == '.')) {
                        table[y][x] = '*';
                        water.add(new Node(x, y, waterNode.minute + 1));
                    }
                }
            }

            int animalLen = animal.size();
            for (int i = 0; i < animalLen; i++) {
                Node animalNode = animal.poll();

                for (int j = 0; j < 4; j++) {
                    int x = dx[j] + animalNode.x;
                    int y = dy[j] + animalNode.y;

                    if (x >= 0 && y >= 0 && x < c && y < r) {
                        if (table[y][x] == 'D') {
                            result = animalNode.minute;
                            return;
                        } else if (table[y][x] == '.') {
                            table[y][x] = 'S';
                            animal.add(new Node(x, y, animalNode.minute + 1));
                        }
                    }
                }
            }


        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());// 세로
        c = Integer.parseInt(st.nextToken());// 가로

        table = new char[r][c];
        water = new ArrayDeque<>();
        animal = new ArrayDeque<>();

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                char c = s.charAt(j);
                table[i][j] = c;

                if (c == '*') {
                    water.add(new Node(j, i, 1));
                }

                if (c == 'S') {
                    animal.add(new Node(j, i, 1));
                }
            }
        }

        bfs();
        if (result == -1) {
            System.out.println("KAKTUS");
        }
        else {
            System.out.println(result);
        }
    }

}
