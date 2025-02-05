public class JW_2805_2 {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        n = read();
        m = read();
        arr = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = read();
            r = Math.max(r, arr[i]);
        }
        // 매개변수 탐색
        while (l <= r) {
            int target = (l + r) / 2;
            if (isPossible(target))
                l = target + 1;
            else
                r = target - 1;
        }
        System.out.println(r);
    }

    // 결정함수
    private static boolean isPossible(int target) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(0, arr[i] - target);
            if (sum >= m)
                return true;
        }
        return false;
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