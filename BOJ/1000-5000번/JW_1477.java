import java.util.Arrays;

public class JW_1477 {

    static int n, m, l;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        n = read();
        m = read();
        l = read();
        arr = new int[n + 1];       // 주유소의 위치를 입력 받을 변수
        for (int i = 0; i < n; i++)
            arr[i] = read();
        arr[n] = l;                 // 마지막으로 고속도로의 끝 입력
        Arrays.sort(arr);           // 이분 탐색을 위한 정렬
        int left = 1, right = l;
        // 이분 탐색
        while (left <= right) {
            int mid = (left + right) / 2;
            // 해당 값이 가능했다면 최소를 찾기 위해 범위를 줄여줌
            if (isPossible(mid)) {
                right = mid - 1;
            } else
                left = mid + 1;
        }
        // 휴게소가 없는 구간의 길이의 최댓값들 중 최솟값 반환
        System.out.println(left);
    }

    // 해당 값으로 주유소를 지었을 때 m개 이하로 지을 수 있는지
    private static boolean isPossible(int target) {
        int cnt = 0;
        int cur = 0;    // 현재 위치, 초기값은 시작 지점인 0
        for (int i = 0; i < n; i++) {
            cnt += (arr[i] - cur - 1) / target; // 현재 위치에서 다음 위치까지 갈 때 필요한 휴게소의 개수
            cur = arr[i]; // 현재 위치 갱신
        }
        return cnt <= m;
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