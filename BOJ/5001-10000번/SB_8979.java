import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_8979 {
    static int N, K;
    static List<Nation> nations = new ArrayList<>();
    private static int medal2Int(int g, int s, int b){
        return g * 1000000 + s * 1000 + b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nations.add(new Nation(idx, medal2Int(g, s, b)));
        }

        Collections.sort(nations);

        int rank = 1;
        int tmp = 1;                                        // 동점자 처리를 위한 변수(매번 증가)
        int preScore = nations.get(0).score;
        for (Nation cur : nations) {
            if (cur.score != preScore) rank = tmp;          // 서로 점수가 다르면 순위 갱신
            if (cur.idx==K){                                // 찾는 나라면 순위 출력
                System.out.println(rank);
                return;
            }
            tmp++;
            preScore = cur.score;
        }
    }

    static class Nation implements Comparable<Nation>{
        int idx, score;

        public Nation(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(Nation o) {
            return o.score - this.score;
        }

        @Override
        public String toString() {
            return "Nation{" +
                    "idx=" + idx +
                    ", score=" + score +
                    '}';
        }
    }
}
