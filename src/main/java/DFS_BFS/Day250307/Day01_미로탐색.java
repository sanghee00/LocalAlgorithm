package DFS_BFS.Day250307;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day01_미로탐색 {
    static Node[][] map;
    static boolean[][] visited;
    static Node endPoint;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;
    static int m;

    public static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return String.valueOf(cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new Node[n][m];
        visited = new boolean[n][m];
        endPoint = new Node(m, n, 1);

        for (int i = 0; i < n; i++) {
            String s =  br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = new Node(j, i, s.charAt(j) - '0');
            }
        }

        bfs(0, 0);

        System.out.println(map[endPoint.y - 1][endPoint.x - 1].cnt);
    }

    public static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y, 1));
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + pollNode.x;
                int ny = dy[i] + pollNode.y;

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[ny][nx] && map[ny][nx].cnt == 1) {
                    visited[ny][nx] = true;
                    map[ny][nx].cnt = pollNode.cnt + 1;
                    queue.add(new Node(nx, ny, pollNode.cnt + 1));
                }
            }
        }
    }
}
