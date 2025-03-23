import java.util.Arrays;

public class JW_3151 {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        n = read();
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        Arrays.sort(arr);
        long cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > 0)
                break;
            for (int j = i + 1; j < n; j++) {
                int target = arr[i] + arr[j];
                int upperIndex = getBound(j + 1, -target, true);
                int lowerIndex = getBound(j + 1, -target, false);
                cnt += upperIndex - lowerIndex;
            }
        }
        System.out.println(cnt);
    }

    private static int getBound(int s, int target, boolean flag) {
        int l = s, r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target || (flag && arr[mid] == target)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
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
