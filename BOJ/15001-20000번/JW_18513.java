import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class JW_18513 {

    public static void main(String[] args) throws Exception {
        int n = read(), k = read();
        Deque<int[]> dq = new ArrayDeque<>();
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int p = read();
            dq.offer(new int[] { p, p });
            hs.add(p);
        }
        long answer = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                int[] cur = dq.poll();
                int next = cur[0] + 1;
                if (!hs.contains(next)) {
                    dq.offer(new int[] { next, cur[1] });
                    hs.add(next);
                    answer += Math.abs(next - cur[1]);
                    if (--k == 0) {
                        System.out.println(answer);
                        return;
                    }
                }
                next = cur[0] - 1;
                if (!hs.contains(next)) {
                    dq.offer(new int[] { next, cur[1] });
                    hs.add(next);
                    answer += Math.abs(next - cur[1]);
                    if (--k == 0) {
                        System.out.println(answer);
                        return;
                    }
                }
            }
        }
    }

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
