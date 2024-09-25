import java.util.*;
import java.io.*;

/*
트럭
 */

public class DH_13335 {
    static class Truck {
        int w, d;
        public Truck(int w, int d) {
            this.w = w;
            this.d = d;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<Integer> q = new ArrayDeque<>();
        LinkedList<Truck> tunnel = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) q.add(Integer.parseInt(st.nextToken()));

        int tunnelWeight = 0;
        int time = 0;

        while(true) {
            time++;

            // 다리 끝에 도달한 트럭이 있는지 확인
            if(!tunnel.isEmpty() && tunnel.get(0).d == w) {
                tunnelWeight -= tunnel.get(0).w;
                tunnel.remove(0);
            }
        
            // 다리에 들어올 수 있는 트럭이 있는지 확인
            if(!q.isEmpty() && tunnelWeight + q.peek() <= L) {
                tunnelWeight += q.peek();
                tunnel.add(new Truck(q.poll(), 0));
            }

            // 다리에 있는 트럭의 위치 한 칸씩 옮겨주기
            for(Truck t: tunnel) t.d += 1;
            if(tunnel.isEmpty()) break;
        }
        System.out.println(time);
    }
}