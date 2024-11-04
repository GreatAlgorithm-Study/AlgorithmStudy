public class JW_2805 {

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
        // 매개 변수 탐색
        while (l <= r) {
            int target = (l + r) / 2; // 매개 변수 = 절단기의 높이
            if (isPossible(target)) {
                l = target + 1; // 만족할 수 있었다면 절단기의 높이의 최댓값를 구하기 위해 오른쪽 탐색
            } else {
                r = target - 1; // 만족할 수 없는 경우 절단기의 높이 줄여야 하므로 왼쪽 탐색
            }
        }
        System.out.println(r); // UpperBound 출력
    }

    // 해당 절단기의 높이로 자를 수 있는 나무의 총 길이를 구하는 결정함수
    private static boolean isPossible(int target) {
        long sum = 0;
        for (int i = 0; i < n; i++)
            sum += Math.max(0, arr[i] - target); // 음수가 될 수 없기 때문에 0으로 제한
        // 자른 나무 길이의 총 합이 m 이상이면 가능
        return sum >= m;
    }
    
    // 빠른 입력을 위한 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}