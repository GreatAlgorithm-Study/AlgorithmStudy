import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        List<Pool> pools = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pools.add(new Pool(s, e));
        }

        Collections.sort(pools);
        int cnt = 0;
        int start = 0;
        for (Pool p : pools) {
            if (start >= p.e) continue;         // 이미 덮어진 웅덩이면 패쓰

            int curStart = Math.max(start, p.s);
            int need = (p.e-curStart)/L;
            if ((p.e-curStart)%L!=0) need++;    // 나머지 있으면 더해주기

            cnt += need;
            start = curStart + need * L;        // 놓아야할 널판지 시작점 업데이트
        }
        System.out.println(cnt);
    }
    
    static class Pool implements Comparable<Pool>{
        int s, e;
        public Pool(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pool o) {
            return this.s-o.s;
        }
    }
}
