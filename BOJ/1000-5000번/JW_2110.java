import java.util.Arrays;

public class Main {

    static int n, c;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        n = read();
        c = read();
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        Arrays.sort(arr); // 매개 변수 탐색을 위한 정렬
        int l = 1, r = arr[n - 1];
        // 매개 변수 탐색
        while (l <= r) {
            int param = (l + r) / 2; // 매개 변수 : 두 공유기 사이의 최소 거리
            if (isPossible(param)) {
                l = param + 1; // 최소 거리의 최댓값을 찾기 위해 오른쪽 탐색
            } else {
                r = param - 1; // 해당 거리로는 불가능 했으므로 왼쪽 탐색
            }
        }
        System.out.println(r); // Upper Bound 출력
    }

    private static boolean isPossible(int param) {
        int cnt = 1;
        int prev = 0; // 마지막으로 공유기가 설치된 집의 인덱스
        for (int i = 1; i < n; i++) {
            // 마지막으로 설치된 집과의 거리가 param보다 크다면 공유기 설치
            if (arr[i] - arr[prev] >= param) {
                cnt++;
                prev = i;
            }
        }
        // 많이 설치되도 됨 -> 몇 개 지워버리면 되니깐
        return cnt >= c;
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