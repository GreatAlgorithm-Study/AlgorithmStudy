package day1108;

import java.io.*;
import java.util.*;

public class JY_13164 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer> dList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			int n2 = Integer.parseInt(st.nextToken());
			dList.add(n2 - n1);
			n1 = n2;
		}
		
		Collections.sort(dList);
//		System.out.println(dList);
		
		// 뒤의 (K-1)개의 차이를 없애야 함
		int ans = 0;
		for(int i=0; i<N-K; i++) {
			ans += dList.get(i);
		}
		
		System.out.println(ans);
	}

}
