import java.io.*;

/*
 * 소수의 연속합
 */

public class DH_1644 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		// N까지 숫자 중에서 어떤 것이 소수인지 알아내기
		boolean[] isNotPrime = getNotPrime(N);
		
		System.out.println(getCount(isNotPrime, N));
	}
	
	// 투포인터를 통해 소수의 연속합을 구했을 때, n이 되는 개수 구하기
	static int getCount(boolean[] isNotPrime, int n) {
		int count = 0;
		
		int s = 2, e = 2, sum = 2;
		
		while(s <= e && e < isNotPrime.length) {
			// e 옮기기
			if(sum < n) {
				
				while(e < isNotPrime.length) {
					
					e += 1;

					if(isNotPrime.length == e) break;
					
					if(!isNotPrime[e]) {
						sum += e;
						break;
					}
				}
			}
			
			// sum >= n → s 옮기기 
			else {
				if(sum == n) count += 1;
				
				sum -= s;
				
				while(s < e) {
					s += 1;
					
					if(!isNotPrime[s]) {
						break;
					}
				}
			}
		}
		
		return count;
	}

	// 에라토스테네스 체로 n까지 숫자 중에서 소수 걸러내기
	static boolean[] getNotPrime(int n) {
		boolean[] isNotPrime = new boolean[n + 1];
		
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(isNotPrime.length); i++) {
			if(isNotPrime[i]) continue;
			
			for(int j = i * i; j < isNotPrime.length; j += i) {
				isNotPrime[j] = true;
			}
		}
		
		return isNotPrime;
	}
}
