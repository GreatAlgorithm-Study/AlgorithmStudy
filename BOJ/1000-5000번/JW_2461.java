import java.util.ArrayList;
import java.util.PriorityQueue;

public class JW_2461 {

    public static void main(String[] args) throws Exception {
        int n = read(), m = read();
        ArrayList<PriorityQueue<Integer>> arr = new ArrayList<>(); // 각 학급 정보를 저장할 List
        for (int i = 0; i < n; i++) {
            // 능력치는 모든 학생이 서로 다르다
            // 학급 별 능력치를 우선 순위 큐를 이용하여 정렬하며 저장
            arr.add(new PriorityQueue<Integer>());
            for (int j = 0; j < m; j++)
                arr.get(i).add(read());
        }
        // 최솟값과 최댓값을 꺼내기 위해 우선순위 큐를 2개를 이용
        // int[] {능력치, 학급}
        PriorityQueue<int[]> pqNatural = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pqReversal = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < n; i++) {
            int p = arr.get(i).poll();
            pqNatural.offer(new int[] { p, i });
            pqReversal.offer(new int[] { p, i });
        }
        // 초기 최솟값 설정
        int min = pqReversal.peek()[0] - pqNatural.peek()[0];
        while (true) {
            int[] prev = pqNatural.poll(); // 최솟값을 가진 학생 정보
            // 그 학생의 반에 있는 정보를 모두 사용했다면 종료
            if (arr.get(prev[1]).isEmpty())
                break;
            int next = arr.get(prev[1]).poll(); // 최솟값 능력치를 가진 학생의 반에서 다음 학생
            pqNatural.offer(new int[] { next, prev[1] });
            pqReversal.offer(new int[] { next, prev[1] });
            // 최솟값 결정
            min = Math.min(min, pqReversal.peek()[0] - pqNatural.peek()[0]);
        }
        System.out.println(min);
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