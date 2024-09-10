import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 회전 초밥
* 문제: 최대로 초밥먹을 수 있는 갯수 구하기
* */
public class SB_2531_회전초밥 {
    static int N, D, K, C;
    static int[] foods;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        foods = new int[N];
        for (int i = 0; i < N; i++) {
            foods[i] = Integer.parseInt(br.readLine());
        }

        int left = 0;
        int right = 0;
        int mx = 0;

        HashMap<Integer, Integer> eat = new HashMap<>();
        eat.put(foods[left], 1);
        while (left < N) {
            while (right - left < K-1) {
                right++;
                if (foods[right%N]==C) continue;
                eat.put(foods[right%N], eat.getOrDefault(foods[right%N], 0) + 1);
            }
            mx = Math.max(mx, eat.size());

            eat.put(foods[left], eat.getOrDefault(foods[left], 0) - 1);
            if (eat.get(foods[left]) <= 0) {
                eat.remove(foods[left]);
            }
            left++;
        }
        System.out.println(mx+1);
    }
}

/*
* 회전초밥 일자가 아니라 원형으로 되어있음
* */