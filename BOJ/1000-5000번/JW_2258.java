import java.util.PriorityQueue;
import java.util.TreeMap;

public class JW_2258 {

    public static void main(String[] args) throws Exception {
        int n = read(), m = read();
        TreeMap<Integer, Integer> tm = new TreeMap<>(); // 누적합을 저장할 맵
        TreeMap<Integer, PriorityQueue<Integer>> pq = new TreeMap<>(); // 각 가격 별로 살 수 있는 고기
        for (int i = 0; i < n; i++) {
            int amount = read(), cost = read();
            pq.putIfAbsent(cost, new PriorityQueue<>((o1, o2) -> o2 - o1)); // 내림차 순으로 정렬
            pq.get(cost).add(amount);
            tm.put(cost, tm.getOrDefault(cost, 0) + amount); // 누적합 계산
        }
        // 사려는 가격보다 작은 가격들은 덤으로 받을 수 있기 때문에 누적합에 더해줌
        for (int cost : tm.keySet())
            // 현재 가격보다 높은 가격이 있다면
            if (tm.higherKey(cost) != null)
                // 해당 누적합에 현재까지의 누적합을 합
                tm.put(tm.higherKey(cost), tm.get(tm.higherKey(cost)) + tm.get(cost));
        // 마지막 누적합으로도 조건을 만족할 수 없다면 -1 출력
        if (tm.get(tm.lastKey()) < m) {
            System.out.println(-1);
            return;
        }
        int min = Integer.MAX_VALUE;
        // 적은 가격과 무거운 덩어리부터 그리디하게 탐색
        for (int cost : pq.keySet()) {
            PriorityQueue<Integer> meat = pq.get(cost);
            int needs = m;
            if (tm.lowerKey(cost) != null)
                needs -= tm.get(tm.lowerKey(cost)); // 누적합을 가져와 구해야하는 무게에서 뺌
            int cnt = 0; // 현재 금액에서 사야하는 고기의 수
            while (!meat.isEmpty()) {
                needs -= meat.poll();
                cnt++;
                if (needs <= 0) {
                    min = Math.min(min, cost * cnt); // 구매한 고기들의 수와 가격으로 비교
                    break; // 더 이상 탐색하지 않아도 되기 때문에 해당 금액 종료
                }
            }
        }
        System.out.println(min);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}