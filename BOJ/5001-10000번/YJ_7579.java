import java.io.*;

public class YJ_7579 {
    static int[] memories;
    static int[] costs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        String[] nM = br.readLine().split("\\s");
        String[] nC = br.readLine().split("\\s");
        memories = new int[N];
        costs = new int[N];
        for(int i=0; i<N; i++){
            memories[i] = Integer.parseInt(nM[i]);
            costs[i] = Integer.parseInt(nC[i]);
        }

        System.out.println(getMinCostSum(N,M));
    }

    //내생각: dp배열의 값을 최소비용으로 두고 접근(X)
    //1차원 dp배열을 최대비용으로 값을 메모리로 두고 접근(O)
    static int getMinCostSum(int N, int M){
        //dp[비용] = 메모리
        int[] dp = new int[10001];  //최대 비용의 총합

        for(int app=0; app<N; app++){
            for(int c=10000; c>=costs[app]; c--){
                //최소 비용으로 가질수있는 최대메모리(최대라면 항상 메모리를 충족함)
                dp[c] = Math.max(dp[c], dp[c-costs[app]]+memories[app]);
                //현재 메모리 vs (현재 메모리를 추가한다면) 이전 상태의 메모리★ + 현재메모리
            }
        }

        int result = 0;
        for(int i=0; i<dp.length; i++){
            //최소 비용부터 탐색해서 적정메모리 이상이 있는지 조회
            if(dp[i] >= M){
                result = i;
                break;
            }
        }

        return result;
    }
}