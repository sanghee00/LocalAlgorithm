package DFS_BFS.Day250307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day02_미로탐색 {
    static Node[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int mainY;
    static int mainX;

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

        mainY = Integer.parseInt(st.nextToken());
        mainX = Integer.parseInt(st.nextToken());
        visited = new boolean[mainY][mainX];
        map = new Node[mainY][mainX];

        for (int i = 0; i < mainY; i++) {
            String s = br.readLine();
            for (int j = 0; j < mainX; j++) {
                map[i][j] = new Node(j, i, s.charAt(j) - '0');
            }
        }

        bfs(0, 0);
        System.out.println(map[mainY - 1][mainX - 1].cnt);
    }


    static void bfs(int x, int y) {
        visited[y][x] = true;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y, map[y][x].cnt));

        while (!queue.isEmpty()) {
            Node pollNode= queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + pollNode.x;
                int ny = dy[i] + pollNode.y;

                if (nx >= 0 && ny >= 0 && nx < mainX && ny < mainY && !visited[ny][nx] && map[ny][nx].cnt == 1) {
                    visited[ny][nx] = true;
                    map[ny][nx].cnt = pollNode.cnt + 1;
                    queue.add(new Node(nx, ny, pollNode.cnt + 1));
                }
            }
        }

    }
}
