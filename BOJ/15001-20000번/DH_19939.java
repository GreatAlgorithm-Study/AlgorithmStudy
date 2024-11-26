import java.io.*;
import java.util.*;

/*
 * 박 터뜨리기
 */

public class DH_19939 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		System.out.println(solution(N, K));
	}
	
	static int solution(int N, int K) {
		int sum = K * (K + 1) / 2; // 1부터 K까지의 합
		int min = 1, max = K, answer = 0;
		if(N < sum) return -1;
		
		while(N > sum) N -= K;
		
		answer = max - min;
		if(sum != N) answer += 1;
		
		return answer;
	}
}
