import java.util.Arrays;

public class JW_8879 {

    public static void main(String[] args) throws Exception {
        int n = read(), k = read();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++)
            arr[i] = new int[] { read(), read(), read(), read() };
        // 정렬
        Arrays.sort(arr, (o1, o2) -> o2[1] != o1[1] ? o2[1] - o1[1] : o2[2] != o1[2] ? o2[2] - o1[2] : o2[3] - o1[3]);
        int rank = 1;
        for (int i = 0; i < n; i++) {
            // 등수 증가 조건
            if (i > 0 && !isSame(arr[i - 1], arr[i]))
                rank = i + 1;
            // 종료 조건
            if (arr[i][0] == k)
                break;
        }
        System.out.println(rank);
    }

    // 동일 점수 확인
    private static boolean isSame(int[] A, int[] B) {
        return A[1] == B[1] && A[2] == B[2] && A[3] == B[3];
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