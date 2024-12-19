public class JW_1725_2 {

    static int[] arr;
    static int maxArea = 0;

    public static void main(String[] args) throws Exception {
        int n = read();
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        recursive(0, n - 1);
        System.out.println(maxArea);
    }

    // 분할 정복
    private static void recursive(int l, int r) {
        // 종료 조건
        if (l == r) {
            maxArea = Math.max(maxArea, arr[l]);
            return;
        }
        int m = (l + r) / 2;
        int h = arr[m];
        maxArea = Math.max(maxArea, arr[m]); // 너비가 1인 넓이 계산
        int toL = m, toR = m;
        // m을 기준으로 왼쪽, 오른쪽으로 1씩 늘려가면서 최대 넓이 계산 → 항상 최대높이의 왼쪽 or 오른쪽으로 최댓값
        while (l < toL && toR < r) {
            // 오른쪽으로 넓히는게 이득이라면
            if (arr[toL - 1] < arr[toR + 1]) {
                h = Math.min(h, arr[++toR]); // 최대 높이 갱신
            } else {
                h = Math.min(h, arr[--toL]); // 최대 높이 갱신
            }
            maxArea = Math.max(maxArea, h * (toR - toL + 1));
        }
        // 남은 인덱스까지 계산
        while (toR < r) {
            h = Math.min(h, arr[++toR]);
            maxArea = Math.max(maxArea, h * (toR - toL + 1));
        }
        while (l < toL) {
            h = Math.min(h, arr[--toL]);
            maxArea = Math.max(maxArea, h * (toR - toL + 1));
        }
        // 남은 부분에 대해서 재귀 호출
        recursive(l, m);
        recursive(m + 1, r);
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