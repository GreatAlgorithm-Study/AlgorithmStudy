import java.util.Arrays;

public class JW_2352 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        // LIS의 각 길이별 마지막 값을 저장 할 배열
        int[] lis = new int[n];
        int len = 0;    // LIS의 길이
        for (int i = 0; i < n; i++) {
            // LIS에서 해당 값이 들어갈 자리 찾기
            int idx = Arrays.binarySearch(lis, 0, len, arr[i]);
            if (idx < 0)
                idx = ~idx;
            lis[idx] = arr[i];
            if (idx == len)
                len++;
        }
        System.out.println(len);
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

// 꼬이지 않게 연결
// 연결선이 서로 꼬이지 않도록(겹치거나 교차하지 않음) 최대 몇 개까지 연결할 수 있는지??

// 각 자리가 연결되어야하는 포트의 번호
