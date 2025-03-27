import java.io.*;
import java.util.*;
public class JY_1644 {

	static int N;
	static List<Integer> pList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		pList = new ArrayList<>();
		findPrime();
		
//		System.out.println(pList);
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		int s = 0;
		int e = 0;
		int total = pList.get(e);
		int cnt = 0;
		while(s <= e) {
			if(total == N) {
				cnt++;
				total -= pList.get(s);
				s++;
			}
			else if(total > N) {
				total -= pList.get(s);
				s++;
			}
			else {
				e++;
				if(e >= pList.size()) break;
				total += pList.get(e);
			}
		}
		
		System.out.println(cnt);

	}
	public static void findPrime() {
		boolean[] isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		
        for(int i = 2; i <= Math.sqrt(N); i++){ // 2부터 n의 제곱근까지의 모든 수를 확인
            if(isPrime[i]){ // 해당수가 소수라면, 해당수를 제외한 배수들을 모두 false 처리하기
                for(int j = i*i; j<= N; j += i){//그 이하의 수는 모두 검사했으므로 i*i부터 시작
                    isPrime[j] = false;
                }
            }
        }
        for(int i=2; i<N+1; i++) {
        	if(isPrime[i]) pList.add(i);
        }
	}

}
