package DFS_BFS.Day250227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day02_촌수계산_DFS {

    public static List<Integer>[] person; // 담을 사람
    public static boolean[] visited;
    public static int count = 0; // dfs에서 빠져나거나 들어갈때 쓰이는 변수
    public static int result = -1;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 총 인원
        StringTokenizer st = new StringTokenizer(br.readLine()); // 촌수 계산 해야되는 사람 2명 입력 받기

        int person1 = Integer.parseInt(st.nextToken());
        int person2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine()); // 관계의 수

        int[][] graph = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        /*for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }*/

        solution(graph, person1, person2, n);

        System.out.println(result);
    }

    public static void solution(int[][] graph, int startNode, int endNode, int n) {
        person = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            person[i] = new ArrayList<>();
        }

//        System.out.println(Arrays.toString(person));

        // 양방향 그래프
        for (int[] i : graph) {
            person[i[0]].add(i[1]);
            person[i[1]].add(i[0]);
        }
//        System.out.println(Arrays.toString(person));

//        dfs(startNode, endNode);
        bfs(startNode, endNode, n);
    }


    public static void dfs(int startNode, int endNode) {
        if (startNode == endNode) {
            result = count;
        }
        // 방문 처리
        visited[startNode] = true;

        for (int now : person[startNode]) {
            // System.out.println(now); // 시작 노드가 7일시 -> 인저 노드 2가 나옴
            // 인접 노드를 방문 했는지 확인해본다.
            if (!visited[now]) {
                count++; // 들어갈 때 count++
                dfs(now, endNode); // 방문을 한 적이 없다면 그 노드에 진입
                count--; // 나올 때 count--
            }
        }
    }

    public static void bfs(int startNode, int endNode, int n) {
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 큐 생성
        visited[startNode] = true; // 시작 노드 방문 처리
        queue.add(startNode); // 시작 노드를 큐에 추가

        while (!queue.isEmpty()) {
            int now = queue.poll(); // 큐에서 노드 꺼내기

            if (now == endNode) { // 목표 노드에 도달한 경우
                result = count; // 결과에 현재 count 저장
                return; // 탐색 종료
            }

            for (int i = 1; i <= n; i++) {
                if (person[now].get(i) == 1 && dist[i] == 0) {
                    queue.add(i);
                    dist[i] = dist[now] + 1;
                }
            }

        }
    }
}
