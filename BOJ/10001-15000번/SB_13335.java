import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SB_13335 {
    static int N, W, L;
    static int[] trucks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());       // 다리 길이
        L = Integer.parseInt(st.nextToken());       // 다리 최대하중

        trucks = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Truck> que = new ArrayDeque<>();
        que.offer(new Truck(trucks[0], 1));

        int weight = trucks[0];
        int time = 1;
        int idx = 1;
        while (!que.isEmpty()) {
            time++;

            if (time - que.peek().inTime == W)
                weight -= que.poll().w;

            if (idx < N && weight + trucks[idx] <= L && que.size() < W) {   // 다리에 트럭추가 가능 시
                que.offer(new Truck(trucks[idx], time));
                weight += trucks[idx];
                idx++;
            }
        }
        System.out.println(time);

    }
    static class Truck{
        int w;
        int inTime;

        Truck(int w, int inTime) {
            this.w = w;
            this.inTime = inTime;
        }
    }
}
