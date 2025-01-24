import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[행렬 곱셈 순서]
모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값을 구하기
행렬 크기별로 곱해주면 되는거 아닌가? 고민
왜 dp가 필요한지..
문제점 : 곱셈 순서를 선택하지 않으면 계산량 증가함
중복된 계산: ABCD 이렇게 있으면 (AB)C)D=AB, ABC, ABCD, (A(BC)D)=BC,A(BC), A(BC))D, (AB)(CD) = AB x CD
해결 : 곱셈 순서에따라 연산 횟수가 달라지기 때문에, 곱셈 순서를 정해줘야함!!!- > 그래서 dp로 품

점화식 도출

 */
class BOJ_11049{
    static class Matrix{
        int y;
        int x;
        Matrix(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int N;
    static Matrix[] matrix;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 행렬의 개수
        matrix = new Matrix[N+1];
        dp = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                dp[i][j] = -1;
            }
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            matrix[i] = new Matrix(y, x);
        }
        System.out.println(excute(1, N));

        // 행렬 곱셈이 가능한지 여부 판단x (항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어짐)
        // [N][K] = [K][M] 이런식으로 주어져야함 -> N x M x K
    }
    private static int excute(int s, int e){
        int ans = Integer.MAX_VALUE;
        if(dp[s][e]!=-1){ // 이미 계산된 부분이면
            return dp[s][e]; // 반환 (메모이제이션)
        }
        if(s==e){ // 행렬이 1개일 경우
            return 0; // 계산할 필요x
        }
        if(s+1==e){ // 행렬이 2개인 경우
            return matrix[s].y * matrix[s].x  * matrix[e].x; // N x M x K
        }
        // 행렬이 2개 초과인 경우
        for(int i=s; i<e; i++){
            int a = matrix[s].y * matrix[i].x * matrix[e].x + excute(s, i) + excute(i+1, e);
            ans = Math.min(ans, a);
        }
        return dp[s][e] = ans;
    }
}