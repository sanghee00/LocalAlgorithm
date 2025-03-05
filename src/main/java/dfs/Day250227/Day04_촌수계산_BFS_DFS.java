package dfs.Day250227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day04_촌수계산_BFS_DFS {
    static List<Node>[] adjList;
    static boolean[] visited;
    static int n;
    static Node endNode;
    static int result = -1;

    public static class Node {
        public int cnt;
        public int value;

        public Node(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "|" + value + ", " + cnt + "|";
        }
    }

    public static void dfs(Node startNode) {
        visited[startNode.value] = true;
        if (startNode.value == endNode.value) {
            result = startNode.cnt;
            return; 
        }

        for (Node n : adjList[startNode.value]) {

            if (!visited[n.value]) {
                dfs(new Node(n.value, startNode.cnt + 1));
            }

        }

    }

    public static void bfs(Node startNode) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(startNode);
        visited[startNode.value] = true;
        // System.out.println("startNode " + "index " + startNode.value + " " + visited[startNode.value]);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            // System.out.println(poll);
            if (poll.value == endNode.value) {
                result = poll.cnt;
            }

            for (Node node : adjList[poll.value]) {
                if (!visited[node.value]) {
                    // System.out.println("index " + node.value + " " + visited[node.value] + " node " + node);
                    visited[node.value] = true;
                    queue.add(new Node(node.value, poll.cnt + 1));
                }
            }
        }

    }

    public static void solution(int[][] graph, Node startNode) {
        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

//        System.out.println(Arrays.toString(adjList));

        // 인접리스트 생성
        for (int[] i : graph) {
            adjList[i[0]].add(new Node(i[1], 0));
            adjList[i[1]].add(new Node(i[0], 0));
        }

//        bfs(startNode);
        dfs(startNode);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Node startNode = new Node(Integer.parseInt(st.nextToken()), 0);
        endNode = new Node(Integer.parseInt(st.nextToken()), 0);
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

       /* for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }*/

        solution(graph, startNode);
        System.out.println(result);
    }
}
