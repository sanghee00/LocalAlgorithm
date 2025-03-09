package DFS_BFS.Day250305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day04_탈출 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static int r;
    static int c;
    static Queue<Node> water;
    static Queue<Node> animal;
    static int result = -1;

    public static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        water = new ArrayDeque<>();
        animal = new ArrayDeque<>();

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                char c1 = s.charAt(j);
                if (c1 == '*') {
                    water.add(new Node(j, i, 0));
                } else if (c1 == 'S') {
                    animal.add(new Node(j, i, 0));
                }

                map[i][j] = c1;
            }
        }

        bfs();

        if (result == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }

    public static void bfs() {

        while (!animal.isEmpty()) {

            int waterLen = water.size();
            for (int i = 0; i < waterLen; i++) {
                Node pollWater = water.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = dx[j] + pollWater.x;
                    int ny = dy[j] + pollWater.y;

                    if (nx >= 0 && ny >= 0 && nx < c && ny < r && map[ny][nx] == '.') {
                        map[ny][nx] = '*';
                        water.add(new Node(nx, ny, 0));
                    }

                }
            }

            int animalLen = animal.size();
            for (int i = 0; i < animalLen; i++) {
                Node pollAnimal = animal.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = dx[j] + pollAnimal.x;
                    int ny = dy[j] + pollAnimal.y;

                    if (nx >= 0 && ny >= 0 && nx < c && ny < r && map[ny][nx] != 'X' && map[ny][nx] != '*') {
                        if (map[ny][nx] == 'D') {
                            result = pollAnimal.cnt + 1;
                            return;
                        } else if (map[ny][nx] == '.') {
                            map[ny][nx] = 'S';
                            animal.add(new Node(nx, ny, pollAnimal.cnt + 1));
                        }

                    }

                }

            }

        }
    }


}
