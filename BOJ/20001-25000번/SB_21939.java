import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_21939 {
    static int N, M;
    static Set<Integer> check = new HashSet<>();        // 풀어야할 문제들
    static Map<Integer, Problem> notes = new HashMap<>();   // 문제 번호, 문제
    static StringBuilder sb = new StringBuilder();

    private static void recommend(PriorityQueue<Problem> pq) {
        while (!pq.isEmpty()) {
            Problem p = pq.peek();
            if (check.contains(p.idx)){     // 풀어야할 문제에 속하면 추천
                sb.append(p.idx).append('\n');
                break;
            }
            pq.poll();              // 풀 문제가 아니면(이미 푼 문제) 삭제
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> pqHard = new PriorityQueue<>();
        PriorityQueue<Problem> pqEasy = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(p, l);
            pqHard.add(problem);
            pqEasy.add(problem);
            check.add(p);
            notes.put(p, problem);
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")){
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                if (notes.containsKey(p)) {         // 동일한 번호가 들어오면, 이전 번호 삭제
                    Problem prev = notes.get(p);
                    pqHard.remove(prev);
                    pqEasy.remove(prev);
                    check.remove(prev.idx);
                }
                Problem problem = new Problem(p, l);
                pqHard.add(problem);
                pqEasy.add(problem);
                notes.put(p, problem);
                check.add(p);
            } else if (cmd.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                check.remove(p);
            }else{
                int r = Integer.parseInt(st.nextToken());
                if (r==1) recommend(pqHard);
                else recommend(pqEasy);
            }
        }
        System.out.println(sb);
    }

    static class Problem implements Comparable<Problem>{
        int idx, level;

        public Problem(int idx, int level) {
            this.idx = idx;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (o.level-this.level==0) {
                return o.idx - this.idx;
            }
            return o.level - this.level;
        }
    }
}
