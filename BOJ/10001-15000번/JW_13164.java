import java.util.Arrays;

public class JW_13164 {

    public static void main(String[] args) throws Exception {
        int n = read(), k = read();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        // 다음 사람과 같은 조가 되었을 때 비용을 저장할 배열
        int[] diff = new int[n - 1];
        // 다음 사람과의 키 차이 계산
        for (int i = 0; i < n - 1; i++)
            diff[i] = arr[i + 1] - arr[i];
        // 오름차 순으로 정렬
        Arrays.sort(diff);
        int answer = 0;
        // 키 차이가 별로 안나는 조합은 묶어도 됨
        for (int i = 0; i < n - k; i++)
            answer += diff[i];
        System.out.println(answer);
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