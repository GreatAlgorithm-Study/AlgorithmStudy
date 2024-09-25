import java.util.Scanner;

/**
 * 알고리즘:
 * 시간복잡도: n의 크기 10^4 > O(n^2)까지 가능
 * 아이디어:
 * {1,2,3} 한정된 숫자로 n을 만들기 위해 반복되는 규칙이 있는지 확인
 *
 * n 을 만들기 위한 경우의 수 찾기
 * 이때 1을 시작으로 1에서 찾을 수 있는 모든 경우의 수를 찾은 후 다음 수로 이동. 순열 중복 불가
 * n=1 -> (1)
 * n=2 -> (1 1) (2)
 * n=3 -> (1 1 1) (1 2) (3)
 * n=4 -> (1 1 1 1) (1 1 2) (1 3) (2 2)
 * n=5 -> (1 1 1 1 1) (1 1 1 2) (1 2 2) (1 1 3) (2 3)
 *
 * 이때 각 경우의 마지막 수가 1,2,3 각각 몇번 나오는지 갯수를 셌을 때, 이 갯수의 총합이 방법의 수가 된다
 * n=1 -> 1:1개             -> 1
 * n=2 -> 1:1개 2:1개       -> 1+1= 2
 * n=3 -> 1:1개 2:1개 3:1개 -> 1+1+1= 3
 * n=4 -> 1:1개 2:2개 3:1개 -> 1+2+1= 4
 * n=5 -> 1:1개 2:2개 3:2개 -> 1+2+2= 5
 * n=6 -> 1:1개 2:3개 3:3개 -> 1+3+3= 5
 * n=7 -> 1:1개 2:3개 3:4개 -> 1+3+4= 8
 */
public class YJ_15989 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] testCase = new int[len];
        for(int i=0; i<len; i++){
            testCase[i]= sc.nextInt();
        }

        int[][] dp = new int[10001][3];
        //1
        dp[0][0] = 1;
        //2
        dp[1][0] = 1;
        dp[1][1] = 1;
        //3
        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;

        for(int i=3; i<dp.length; i++){
            dp[i][0] = dp[i-1][0];
            dp[i][1] = dp[i-2][0] + dp[i-2][1];
            dp[i][2] = dp[i-3][0] + dp[i-3][1] + dp[i-3][2];
        }

        for(int test : testCase){
            test = test-1;
            System.out.println(dp[test][0] + dp[test][1] + dp[test][2]);
        }
    }

}
