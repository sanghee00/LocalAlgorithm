package dfs.Day250304;

import java.io.*;
import java.util.*;

public class Day01_숫자고르기 {
    static int num;
    static int[] arr;
    static boolean[] check;
    static List<Integer> answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        check = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }
        answer = new LinkedList<>(); // 답은 가변적이므로 리스트로 선언

        for (int i = 0; i < N; i++) {
            check[i] = true;
            num = i;
            dfs(i);
            check[i] = false;
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (Integer integer : answer) {
            System.out.println(integer + 1);
        }
    }

    public static void dfs(int i) {
        if (arr[i] == num) { // 한바퀴돌아 다시 제자리로 돌아오는지 체크
            answer.add(num); // 돌아온다면 정답에 추가
        }

        if (!check[arr[i]]) {
            check[arr[i]] = true;
            dfs(arr[i]);
            check[arr[i]] = false;
        }
    }
}