public class JW_3020 {

    public static void main(String[] args) throws Exception {
        int n = read(), h = read();
        int[] toUp = new int[h + 1]; // 석순
        int[] toDw = new int[h + 1]; // 종유석
        // 높이별로 몇 개가 존재하는 지 카운트
        for (int i = 0; i < n / 2; i++) {
            toUp[read()]++;
            toDw[read()]++;
        }
        // 해당 높이에서 부셔질 수 있는 장애물의 수 카운트
        for (int i = h - 1; i > 0; i--) {
            // 자신보다 큰 수들의 개수 합을 구해줌
            toUp[i] += toUp[i + 1];
            toDw[i] += toDw[i + 1];
        }
        int min = n, cnt = 0;
        for (int i = 1; i <= h; i++) {
            // i의 높이에서 부서지는 종유석 + (i + 1)의  높이에서 부서지는 석순
            int broken = toUp[i] + toDw[h - i + 1];
            if (broken < min) {
                min = broken;
                cnt = 1;
            } else if (broken == min)
                cnt++;
        }
        System.out.println(min + " " + cnt);
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