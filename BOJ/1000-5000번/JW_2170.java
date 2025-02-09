import java.util.Arrays;

public class JW_2170 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++)
            arr[i] = new int[] { read(), read() };
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        int totalLen = 0, sPos = arr[0][0], ePos = arr[0][1];
        for (int i = 1; i < n; i++) {
            if (arr[i][0] <= ePos) {
                sPos = Math.min(sPos, arr[i][0]);
                ePos = Math.max(ePos, arr[i][1]);
            } else {
                totalLen += ePos - sPos;
                sPos = arr[i][0];
                ePos = arr[i][1];
            }
        }
        totalLen += ePos - sPos;
        System.out.println(totalLen);
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