import java.util.Arrays;

public class JW_2631 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[] arr = new int[n];         // LIS의 길이별 최솟값을 저장할 배열
        int len = 0;                    // LIS의 길이
        for (int i = 0; i < n; i++) {
            int value = read();
            int idx = Arrays.binarySearch(arr, 0, len, value);  // 이분탐색으로 적절한 위치 반환
            if (idx < 0)
                // 인덱스가 음수일 경우 보수연산으로 적절한 위치로 바꿔줌
                idx = ~idx;
            arr[idx] = value;   // idx 길이 위치의 LIS 최솟값 갱신
            // 인덱스가 LIS의 맨 뒤를 가리킬 때
            if (idx == len)
                len++;
        }
        System.out.println(n - len);
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