import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도 : N! (<=100) 시간 초과 -> dp로 중복 계산 줄이기
// 필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용을 계산
// 메모리 활성화 or 비활성화
class HW_7579_2{
    static int[] coins;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 활성화 되어 있는 앱의 개수
        int M = Integer.parseInt(st.nextToken()); // M 바이트 이상 메모리 추가 확보
        int[] apps = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){ // 사용중인 바이트 수
            apps[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] costs = new int[N];
        int sum =0;
        for(int i=0; i<N; i++){ // 비활성화 했을 경우 비용
            costs[i] = Integer.parseInt(st.nextToken());
            sum += costs[i];
        }

        int[] dp = new int[sum+1];
        for(int i=0; i<N; i++){
            for(int j=sum; costs[i]<=j; j--){
                dp[j] = Math.max(dp[j], dp[j-costs[i]] + apps[i]);
            }
        }

        for(int i=0; i<=sum; i++){
            if(M<=dp[i]){
                System.out.println(i);
                break;
            }
        }

    }
}