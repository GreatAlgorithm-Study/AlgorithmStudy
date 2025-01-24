import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제 조건
모든 문자열은 N개의 'a'와 M개의 'z'로 이루어져있다. (다른 문자 X)
n+mCn or n+mCm, 'a'를 n개 선택하면 'z'는 m개 자동으로 선택된다고 가정
dp로 탐색 시간 줄이기
dp[i][j] = dp[i-1][j] + dp[i][j-1]
K번째 정렬된 수 찾기

 */
class HW_1256{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[202][202];
        for(int i=0; i<=200; i++){
            for(int j=0; j<=i; j++) {
                if(i==j || j==0){ // iCi=1, iC0=1
                    dp[i][j]=1;
                } else{
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    if(dp[i][j] > 1_000_000_000){ // 범위 초과 방지
                        dp[i][j] = 1_000_000_001; // K 범위보다 큰 경우만 판단하면되기 때문에 K보다 크다는 사실만 나타냄
                    }
                }
            }
        }
        if(dp[N+M][M] < K){ // K번째 문자열이 없는 경우
            System.out.println(-1);
        } else{
            while(!(N==0 && M==0)){
                if(K<=dp[N-1+M][M]){ // 'a'로 시작하는 경우
                    System.out.print("a");
                    N--;
                } else{ // 'z'로 시작하는 경우
                    System.out.print("z");
                    K=K-dp[N-1+M][M];
                    M--;
                }
            }
        }

    }
}