package DFS_BFS.Day250227;

import java.util.*;
import java.io.*;

public class Day02_촌수계산_BFS {
    //BFS탐색에 사용할 정보 클래스
    static class info {
        int index, count;    //현재 친척, 지나간 친척 수

        //생성자
        public info(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

    //그래프 정보
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        //그래프 초기화
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());
        int m = Integer.parseInt(br.readLine());
        //친척간의 관계 그래프 형태로 구현
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        bfs(s, e, n);    //BFS탐색 진행
        bw.write(answer + "");    //탐색 종료한 뒤 결과 BufferedWriter 저장
        bw.flush();        //결과 출력
        bw.close();
        br.close();
    }

    //친척간의 관계로 촌수를 구하는 BFS탐색하는 함수
    static void bfs(int start, int end, int N) {
        Queue<info> q = new LinkedList<>();    //BFS탐색에 사용할 Queue
        boolean[] visited = new boolean[N + 1];    //방문 확인 배열
        visited[start] = true;    //출발 친척 방문 확인
        q.add(new info(start, 0));    //출발 친척 Queue 저장
        //탐색 진행
        while (!q.isEmpty()) {
            info cur = q.poll();
            if (cur.index == end) {    //요구한 다른 친척 방문
                answer = cur.count;    //지나간 촌수 저장
                break;    //탐색 종료
            }
            //인접한 친척들 탐색
            for (int next : graph.get(cur.index)) {
                 if (!visited[next]) {    //방문하지 않은 인접한 친척일 때
                    visited[next] = true;
                    q.add(new info(next, cur.count + 1));
                }
            }
        }
    }
}