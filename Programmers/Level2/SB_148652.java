public class SB_148652 {
    private static boolean isOne(long num) {
        while (num > 0) {
            if (num % 5 == 2) return false;
            num /= 5;
        }
        return true;
    }
    public static int solution(int n, long l, long r) {
        l --;

        int ans = 0;
        for (long i = l; i < r; i++) {
            if (isOne(i)) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 2;
        long l = 4;
        long r = 17;
        System.out.println(solution(n, l, r));
    }
}
