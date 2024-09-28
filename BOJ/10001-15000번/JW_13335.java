import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws Exception {
        int n = read(), w = read(), l = read();
        int[] trucks = new int[n];
        for (int i = 0; i < n; i++)
            trucks[i] = read();
        // 트럭의 움직임을 큐로 구현
        Deque<Integer> dq = new ArrayDeque<>();
        // 다리의 길이만큼 빈 트럭을 넣어줌
        for (int i = 0; i < w; i++)
            dq.offer(0);
        int time = 0, sumWeight = 0, truckIdx = 0; // 현재 시간, 다리 위 트럭의 무게 총합, 트럭의 인덱스
        while (!dq.isEmpty()) {
            time++;                     // 시간 증가
            sumWeight -= dq.poll();     // 트럭을 다리에서 꺼냄
            // 넣어야할 트럭이 남아있고
            if (truckIdx < n) {
                // 최대 하중을 넘지않는다면 트럭 삽입
                if (l >= sumWeight + trucks[truckIdx]) {
                    dq.offer(trucks[truckIdx]);
                    sumWeight += trucks[truckIdx];
                    truckIdx++;
                } else
                    dq.offer(0);    // 트럭과 트럭 사이의 빈 공간을 유지
            }
        }
        System.out.println(time);
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}