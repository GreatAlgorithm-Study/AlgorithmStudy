import java.io.*;
import java.util.*;

/*
 * 줄세우기
 * 중간에 끼어들 수 없기 때문에 일반적인 LIS로 풀 수 없음
 * -> 움직이지 않은 학생들이 증가순이어야 되고, 번호가 서로 붙어 있어야 함 (연속된 숫자로 구성된 LIS)
 */

public class DH_7570_2 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N =Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		int max = 0;
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			// 현재 위치에서 연속된 숫자들의 길이를 구하기 위해서는 (현재 - 1)에서의 길이를 확인해주어야 됨
			dp[num] = dp[num - 1] + 1;
			max = Math.max(max, dp[num]);
		}
		
		System.out.println(dp.length - 1 - max);
	}
}
