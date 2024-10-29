import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        int n = read(), l = read();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++)
            arr[i] = new int[] { read(), read() };
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]); // 시작 좌표 순으로 정렬
        int total = 0, prev = 0;
        for (int i = 0; i < n; i++) {
            // 이미 덮혀있다면 스킵
            if (arr[i][1] < prev)
                continue;
            // 시작 좌표가 덮혀있는지 확인하여 최댓값으로 시작 좌표 계산
            prev = Math.max(prev, arr[i][0]);
            double len = arr[i][1] - prev;      // 덮어야하는 웅덩이의 남은 길이
            int cnt = (int) Math.ceil(len / l); // 사용해야하는 판자의 수
            total += cnt;    // 사용한 판자 수
            prev += cnt * l; // 마지막 좌표 기억
        }
        System.out.println(total);
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