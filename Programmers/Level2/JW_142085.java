import java.util.PriorityQueue;
class JW_142085 {
    public int solution(int n, int k, int[] enemy) {
        // 무적권을 사용할 때 그리디한 선택을 하기 위한 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int round = 0;
        int len = enemy.length;
        for (; round < len; round++) {
            // 라운드마다 우선순위 큐에 집어넣음
            pq.offer(enemy[round]);
            // 만약 해당 라운드의 몬스터를 무찌를 수 없을 경우
            if (n < enemy[round]) {
                // 무적권이 없다면 게임 종료
                if (0 == k)
                    break;
                // 현재 우선순위 큐에 들어가 있는 값들 중
                // 최댓값을 꺼내 해당 라운드 무적권 사용
                n += pq.poll(); // 해당 라운드의 몬스터 수를 다시 더해줌
                k--; // 무적권 감소
            }
            n -= enemy[round]; // 해당 라운드의 몬스터 수 만큼 병사 감소
        }
        return round;
    }
}