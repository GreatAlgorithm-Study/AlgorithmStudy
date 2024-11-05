import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HW_21939 {
    public static class Problem implements Comparable<Problem> {
        int num;
        int level;
        public Problem(int num, int level){
            this.num = num;
            this.level = level;
        }
        @Override
        public int compareTo(Problem o) {
            if (level == o.level) {
                return Integer.compare(num, o.num);
            } else {
                return Integer.compare(level, o.level);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        TreeSet<Problem> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            set.add(new Problem(P, L));
            map.put(P, L);
        }
        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        sb.append(set.last().num).append('\n');
                    } else {
                        sb.append(set.first().num).append('\n');
                    }
                    break;
                case "add":
                    int P = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());
                    set.add(new Problem(P, L));
                    map.put(P, L);
                    break;
                default:
                    P = Integer.parseInt(st.nextToken());
                    L = map.get(P);

                    set.remove(new Problem(P, L));
                    map.remove(P);
            }
        }
        System.out.println(sb);
    }
}