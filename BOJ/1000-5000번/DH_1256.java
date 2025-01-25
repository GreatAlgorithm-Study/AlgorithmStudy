import java.io.*;
import java.util.*;
import java.math.*;

/*
 * 사전
 */

public class DH_1256 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // a의 개수
		int M = Integer.parseInt(st.nextToken()); // z의 개수
		BigInteger K = new BigInteger(st.nextToken());
		
		// N개의 a와 M개의 z를 통해 만들 수 있는 총 문자열의 개수
		BigInteger totalCnt = getCombCnt(N + M, N);
		
		// 전체 경우의 수보다 K가 크다면 가능하지 않은 경우
		if(totalCnt.compareTo(K) < 0) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		int currentCnt = N + M;
		for(int i = 0; i < currentCnt; i++) {
			
			// a가 될 수 있는지 확인
			BigInteger selectACnt = getCombCnt(N + M - 1, N - 1);

			// a를 선택했을 때 나올 수 있는 경우의 수와 같거나 작다면
			// a 선택해야 됨
			// 동시에 N의 개수가 0보다 커야 a를 선택할 수 있음
			if(M == 0 || (K.compareTo(selectACnt) <= 0 && N > 0)) {
				sb.append("a");
				N--;
			} else {
				K = K.subtract(selectACnt);
				sb.append("z");
				M--;
			}
		}
		
		System.out.println(sb);
	}
	
	// 조합 개수 구하기
	static BigInteger getCombCnt(int N, int K) {
		BigInteger nFactorial = getFactorial(N); // n! 값 구하기
		return nFactorial.divide(getFactorial(K)).divide(getFactorial(N - K));
	}
	
	// n! 값 구하기
	static BigInteger getFactorial(int N) {
		BigInteger result = BigInteger.ONE;
		
		for(int i = 1; i < N + 1; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		
		return result;
	}
}
