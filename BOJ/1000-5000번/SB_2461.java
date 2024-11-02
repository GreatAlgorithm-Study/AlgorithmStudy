package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 대표 선수
* N개의 학급에서 한명씩 뽑을때, 이때 최대최소 차가 적게 나도록
* (능력치, 반)으로 객체를 만들고 하나의 리스트에 넣어 투포인터 진행
* */
public class SB_2461 {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int len = N*M;
        Student[] candi = new Student[len];                     // 모든 학생을 담을 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int ab = Integer.parseInt(st.nextToken());
                candi[i * M + j] = new Student(ab, i);
            }
        }

        Arrays.sort(candi);

        int left = 0;
        int right = 0;
        int mn = Integer.MAX_VALUE;

        int[] player = new int[N];                      // 각 반 별 대표 선수 수를 카운트할 배열
        int cnt = 0;                                    // 모든 반의 선수가 출전했는지 체크
        while (right < len){
            if (player[candi[right].idx]==0) cnt++;     // 반의 처음으로 나온 선수일 경우 cnt++
            player[candi[right].idx]++;                 // 선수 출전하면 해당 반의 선수++

            while (cnt == N) {                          // 모든 반의 선수가 출전 시
                mn = Math.min(mn, candi[right].ab-candi[left].ab);          // 능력치 차 갱신
                player[candi[left].idx]--;                                  // left포인터 학생 제거
                if (player[candi[left].idx]==0) cnt--;                      // 반 선수 없다면 cnt--;
                left++;
            }
            right++;
        }
        System.out.println(mn);
    }
    static class Student implements Comparable<Student>{
        int ab, idx;
        public Student(int ab, int idx) {
            this.ab = ab;
            this.idx = idx;
        }
        @Override
        public String toString() {
            return "Student{" +
                    "ab=" + ab +
                    ", idx=" + idx +
                    '}';
        }

        @Override
        public int compareTo(Student o) {
            return this.ab - o.ab;
        }
    }
}
