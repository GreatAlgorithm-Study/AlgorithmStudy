public class Main {
    public static void main(String[] args) throws Exception {
        int n = read();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        int[] lis = new int[n + 1];      // 최장 (연속) 증가 수열의 길이를 저장할 배열
        int len = 0;                     // 최대 길이를 저장할 변수
        for (int i = 0; i < n; i++) {
            int idx = arr[i];
            lis[idx] = lis[idx - 1] + 1; // 자신보다 하나 작은 값이 가진 LIS 길이 +1
            len = Math.max(len, lis[i]); // 최대 길이 갱신
        }
        System.out.println(n - len);
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}