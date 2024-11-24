import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_15831 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        boolean[] stones = new boolean[n];
        String str = br.readLine();
        for (int i = 0; i < n; i++)
            stones[i] = str.charAt(i) == 'W';
        int l = 0, r = 0; // 투 포인터
        int maxLen = 0;
        int pb = 0, pw = 0; // 현재 주운 돌의 상태
        // 투 포인터 진행
        while (r < n) {
            if (pb <= b) {
                if (stones[r])
                    pw++;
                else
                    pb++;
                r++;
            } else {
                if (stones[l])
                    pw--;
                else
                    pb--;
                l++;
            }
            if (pb <= b && pw >= w) {
                maxLen = Math.max(maxLen, r - l);
            }
        }
        System.out.println(maxLen);
    }
}