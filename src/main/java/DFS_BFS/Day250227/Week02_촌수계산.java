package DFS_BFS.Day250227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week02_촌수계산 {
    static List<Integer>[] adjList;
    static boolean[] visited;
    static int cnt;
    static int endNode;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int person1 = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[][] graph = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] i : graph) {
            adjList[i[0]].add(i[1]);
            adjList[i[1]].add(i[0]);
        }

        dfs(person1);
        System.out.println(result);
    }

    public static void dfs(int startNode) {
        visited[startNode] = true;

        if (startNode == endNode) {
            result = cnt;
        }

        for (int i : adjList[startNode]) {
            if (!visited[i]) {
                visited[i] = true;

                cnt++;
                dfs(i);
                cnt--;
            }
        }
    }
}
//9
//7 3
//7
//1 2
//1 3
//2 7
//2 8
//2 9
//4 5
//4 6