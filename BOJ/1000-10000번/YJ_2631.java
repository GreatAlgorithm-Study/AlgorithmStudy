import java.util.*;
import java.io.*;

/**
 * 알고리즘: DP > 최장증가 부분수열(Longest Increasing Subsequence)
 * 시간복잡도: 2 <= N <= 200 이므로 신경쓰지 않아도 됨
 * 아이디어:
 * - 어떤 기준으로 정렬을 해야 하는지?
 * 최소 이동 횟수는 이미 정렬된 수는 제외하고, 나머지 정렬 안된 수들만 움직이기
 * - 이미 정렬된 수는 제외하는 기준?
 * 주어진 숫자들에서 오름차순으로 가장 길게 정렬된 수는 이동하지 않아도 됨
 *- 최장 오름차순 정렬 수 구하는 방법?
 * 현재 값과 이전 값을 비교해서 더 작은 이전 값일 경우 현재 정렬수(기본 1) +1 한다 (누적)
 * 이전 값들이 많아 질 경우 현재 값과 더 작은 이전 값들 중 가장 정렬수가 높은 값을 가져와서 +1 한다
 * 따라서 (전체 숫자 수 - 최장 오름차순 길이)를 한 나머지 개수가 움직여야 하는 갯수가 된다.
 */
public class YJ_2631 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int children = Integer.parseInt(br.readLine());
        int[] line = new int[children];
        for(int i=0; i<children; i++){
            line[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getMinChanged(children,line));
    }

    static int getMinChanged(int children,int[] line){
        int [] dp = new int[children];
        for(int i=0; i<children; i++){
            dp[i] = 1;  //최소값 1
            for(int j=0; j<i; j++){
                //line의 이전[j] 값들이 현재(i) 값보다 작고,
                //dp의 이전 값+1이 현재 값보다 클 경우 최장 정렬 수 갱신
                if(line[j] < line[i] && dp[j]+1 > dp[i]){
                    dp[i] = dp[j]+1;
                }
            }
        }
        Arrays.sort(dp);    //오름차순 정렬 시 마지막 수가 최장 정렬 수
        return children - dp[children-1];
    }
}
