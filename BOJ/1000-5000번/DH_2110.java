import java.io.*;
import java.util.*;

/*
 * 공유기 설치
 */

public class BOJ2110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수

        int[] arr = new int[N]; // 집의 좌표 저장
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        // 공유기 C개 설치
        // 가장 인접한 두 공유기 사이의 거리를 가능한 크게 설치하려고 함
        // 두 공유기의 사이의 거리를 최대로 하는 프로그램 작성
        int s = 0, e = arr[N - 1] + 1;

        int result = 0;
        // upperBound 구하기
        while(s < e) {
            int m = (s + e) / 2; // 공유기 사이 거리
            int cnt = getCnt(m, arr); // 설치해야되는 공유기 개수 구하기

            // 설치한 공유기 개수가 필요한 것보다 많다면 거리 늘리기
            if(cnt >= C) {
                result = m;
                s = m + 1;
            } else {
                e = m;
            }
        }

        System.out.println(result);
    }

    // 설치해야 하는 공유기의 개수를 구하는 함수
    static int getCnt(int m, int[] arr) {
        int result = 1;
        int prev = arr[0];

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] - prev >= m) {
                result++;
                prev = arr[i];
            }
        }
        return result;
    }
}
