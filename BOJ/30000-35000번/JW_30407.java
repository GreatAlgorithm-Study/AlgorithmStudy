import java.util.ArrayDeque;
import java.util.Deque;

public class JW_30407 {

    public static void main(String[] args) throws Exception {
        int n = read();
        Deque<int[]> dq = new ArrayDeque<>();
        int h = read(), d = read(), k = read();
        dq.offer(new int[] { h, d, 1, 0 });
        int t = 0, damage = 0, tempDamage = 0;
        while (n-- > 0) {
            t = dq.size();
            tempDamage = read();
            while (t-- > 0) {
                int[] cur = dq.poll();
                damage = Math.max(0, tempDamage - cur[1]); // 받을 데미지
                // 현재 체력이 0 이하라면 건너뛰기
                if (cur[0] <= 0)
                    continue;
                // 깜짝 놀래켰었다면
                if (cur[3] == 1) {
                    cur[3] = 0;
                    damage = 0;
                }
                // 깜짝 놀래킬 수 있다면
                if (cur[2] != 0)
                    dq.offer(new int[] { cur[0] - damage, cur[1], 0, 1 });
                dq.offer(new int[] { cur[0] - Math.max(0, damage - k), cur[1] + k, cur[2], cur[3] });
                dq.offer(new int[] { cur[0] - damage / 2, cur[1], cur[2], cur[3] });
            }
        }
        // 최댓값 갱신
        int maxH = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            if (cur[0] <= 0)
                continue;
            maxH = Math.max(maxH, cur[0]);
        }
        System.out.println(maxH != 0 ? maxH : -1);
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