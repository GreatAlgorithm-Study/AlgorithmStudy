public class JW_21578 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[] arr = new int[n], lSum = new int[n], rSum = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        // 누적합 초기화
        lSum[0] = arr[0];
        rSum[n - 1] = arr[n - 1];
        for (int i = 1; i < n; i++) {
            lSum[i] = lSum[i - 1] + arr[i]; // 왼쪽에서 오른쪽으로 누적합
            rSum[n - 1 - i] = rSum[n - i] + arr[n - 1 - i]; // 오른쪽에서 왼쪽으로 누적합
        }
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            // 벌꿀벌
            maxValue = Math.max(maxValue, (lSum[i] + rSum[i] - lSum[0] - rSum[n - 1]));
            if (i > 0)
                // 벌벌꿀
                maxValue = Math.max(maxValue, (lSum[n - 1] - lSum[i]) * 2 + lSum[i - 1] - lSum[0]);
            if (i < n - 1)
                // 꿀벌벌
                maxValue = Math.max(maxValue, (rSum[0] - rSum[i]) * 2 + rSum[i + 1] - rSum[n - 1]);
        }
        System.out.println(maxValue);
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