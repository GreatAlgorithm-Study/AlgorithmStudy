import java.io.*;

public class YJ_1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split("\\s");
        String[] h = br.readLine().split("\\s");
        int[] exhaustion = new int[N+1];
        int[] happy = new int[N+1];

        for(int i=1; i<N+1; i++){
            exhaustion[i] = Integer.parseInt(s[i-1]);
            happy[i] = Integer.parseInt(h[i-1]);
        }

        final int MAX_STAMINA = 100;
        int[] dp = new int[MAX_STAMINA+1];   //dp[체력]= 최대 기쁨
        for(int i = 1; i < N+1; i++){
            for(int stamina=MAX_STAMINA; stamina > 0; stamina--) {   //뒤에서 부터 특정 체력에 대한 기쁨을 갱신하면서 반복
                int total = stamina-exhaustion[i];  //현재 체력에서 체력소모를 빼서 현재 상태에서 가능한 상황을 고려
                if (total > 0) {
                    dp[stamina] = Math.max(dp[stamina], dp[total] + happy[i]);    //"이미 갱신된 기쁨" vs "현재 체력소모를 포함하기 위해 이전 상태의 기쁨 + 현재 기쁨"을 비교하기
                    //System.out.printf("dp[%d] = Math.max(dp[%d], dp[%d]+happy[%d])\n", stamina, stamina, total , i);
                    //System.out.printf("%d = Math.max(%d, %d)\n\n", dp[stamina], dp[stamina], dp[total] + happy[i]);
                }
            }
        }

        System.out.println(dp[MAX_STAMINA]);    //최대체력인 dp[100]은 항상 최대기쁨 값이 보장된다
    }
}
