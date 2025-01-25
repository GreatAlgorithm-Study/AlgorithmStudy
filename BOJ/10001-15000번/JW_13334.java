import java.util.Arrays;
import java.util.PriorityQueue;

public class JW_13334 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[][] line = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = read(), b = read();
            line[i] = new int[] { Math.min(a, b), Math.max(a, b) }; // 작은 값은 0번으로
        }
        int d = read();
        Arrays.sort(line, (o1, o2) -> o1[1] - o2[1]); // 도착 지점을 기준으로 정렬
        int max = 0; // 최대 인원 수
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선 순위 큐(오름차순)
        for (int i = 0; i < n; i++) {
            pq.offer(line[i][0]); // i번째 시작 지점을 삽입
            // 큐에 들어있는 시작 지점이 i번째 도착 지점까지 거리가 d 이상인 것을 전부 꺼냄
            while (!pq.isEmpty() && line[i][1] - pq.peek() > d)
                pq.poll();
            max = Math.max(max, pq.size()); // 현재 큐에 들어있는 요소의 수가 가능한 사람의 수
        }
        System.out.println(max);
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)
            n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return m ? ~n + 1 : n;
    }
}
