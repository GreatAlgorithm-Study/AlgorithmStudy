import java.util.TreeSet;

public class JW_23326 {

    public static void main(String[] args) throws Exception {
        int n = read(), q = read();
        TreeSet<Integer> ts = new TreeSet<>(); // 시계(값이 증가) 방향으로 빠른 탐색을 위한 자료 구조
        for (int i = 1; i < n + 1; i++)
            if (read() == 1) {
                ts.add(i);
                ts.add(i + n); // 원형이기 때문에 배열 두 개를 합친 것처럼 연산
            }
        StringBuilder sb = new StringBuilder();
        int now = 1; // 첫 시작 위치
        while (q-- > 0) {
            int oper = read();
            int p;
            switch (oper) {
            case 1:
                p = read();
                // 값이 존재한다면 해당 값을 삭제
                if (ts.contains(p)) {
                    ts.remove(p);
                    ts.remove(p + n);
                // 존재하지 않는다면 해당 값을 삽입
                } else {
                    ts.add(p);
                    ts.add(p + n);
                }
                break;
            case 2:
                p = read() % n; // 필요 없는 부분 제거
                now += p;
                // 원형이므로 위치 조정
                if (now > n)
                    now %= n;
                break;
            case 3:
                // 다음 명소까지 움직여야하는 거리 계산
                if (ts.isEmpty())
                    sb.append("-1\n");
                else if (ts.contains(now)) {
                    sb.append("0\n");
                } else
                    sb.append(ts.higher(now) - now).append("\n");
                break;
            }
        }
        System.out.println(sb);
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