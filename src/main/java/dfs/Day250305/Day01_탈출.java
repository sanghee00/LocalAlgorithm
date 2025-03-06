package dfs.Day250305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Day01_탈출 {
// 처음부터 이상하게 접근해서 돌아가지 않는 코드입니다.
// 일단 남겨두겠습니다.


    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int r; // 세로
    static int c; // 가로
    static Node endNode;
    static int result = -1;

    public static void bfs(Node[][] table, ArrayDeque<Node> queue, boolean[][] visited) {

        // 물이 다차는데 걸리는 시간을 구한다.
        while (!queue.isEmpty()) {
            Node n = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = dx[i] + n.x;
                int yy = dy[i] + n.y;

                if (xx >= 0 && yy >= 0 && yy < r && xx < c && !visited[yy][xx] && !table[yy][xx].value.equals("X") && !table[yy][xx].value.equals("*") && !table[yy][xx].value.equals("D")) {
                    visited[yy][xx] = true;
                    table[yy][xx].value = "*";
                    table[yy][xx].cnt = n.cnt + 1;
                    queue.add(new Node(xx, yy, table[yy][xx].value, n.cnt + 1));
                }
            }

        }
    }

    public static void checkBfs(Node[][] water, Node[][] animal, boolean[][] visited, int[] startArr) {
        ArrayDeque<Node> queue = new ArrayDeque();
        queue.add(new Node(startArr[0], startArr[1], "S", 0));
        visited[startArr[1]][startArr[0]] = true;

        while (!queue.isEmpty()) {
            Node n = queue.poll();

            for (int i = 0; i < 4; i++) {
                int xx = dx[i] + n.x;
                int yy = dy[i] + n.y;

                if (xx >= 0 && yy >= 0 && yy < r && xx < c && !visited[yy][xx]) {

                    if ((water[yy][xx].cnt > animal[yy][xx].cnt)) {
                        System.out.println(animal[yy][xx]);

                        for (int j = 0; j < 4; j++) {
                            int xxx = dx[j] + animal[yy][xx].x;
                            int yyy = dy[j] + animal[yy][xx].y;

                            if (xxx >= 0 && yyy >= 0 && yyy < r && xxx < c) {
                                System.out.println("dsfadsf  " + animal[yyy][xxx]);
                                if ((xxx == endNode.x) && (yyy == endNode.y)) {
                                    result = animal[yy][xx].cnt + 1;
                                    break;
                                }
                            }


                        }

                        visited[yy][xx] = true;
                        queue.add(new Node(xx, yy, animal[yy][xx].value, n.cnt + 1));
                    }

                }
            }

        }

    }


    public static class Node {
        String value;
        int x;
        int y;
        int cnt;

        public Node(int x, int y, String value, int cnt) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
//            return x + " : " + y + " : " + value;
            return cnt + "";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        Node[][] table1 = new Node[r][c];
        Node[][] table2 = new Node[r][c];
        boolean[][] visited1 = new boolean[r][c];
        boolean[][] visited2 = new boolean[r][c];

        ArrayDeque<Node> water = new ArrayDeque<>();
        ArrayDeque<Node> animal = new ArrayDeque<>();

        int[] animalIndex = {0, 0};

        for (int i = 0; i < r; i++) {
            String s =  br.readLine();
            for (int j = 0; j < c; j++) {
                String ss = String.valueOf(s.charAt(j));

                if (ss.equals("*")) {
                    visited1[i][j] = true;
                    water.add(new Node(j, i, "*", 0));
                }

                if (ss.equals("S")) {
                    visited2[i][j] = true;
                    animalIndex[0] = j;
                    animalIndex[1] = i;
                    animal.add(new Node(j, i, "S", 0));
                }

                if (ss.equals("D")) {
                    endNode = new Node(j, i, "D", 0);
                }

                table1[i][j] = new Node(j, i, ss, 0);
                table2[i][j] = new Node(j, i, ss, 0);
            }

        }

        bfs(table1, water, visited1);
        bfs(table2, animal, visited2);

        visited1 = new boolean[r][c];
        // table2가 고슴도치
        checkBfs(table1, table2, visited1, animalIndex);

        for (Node[] s : table1) {
            System.out.println(Arrays.toString(s));
        }
        System.out.println("---------------------------");
        for (Node[] s : table2) {
            System.out.println(Arrays.toString(s));
        }


        System.out.println(result);

    }
}
