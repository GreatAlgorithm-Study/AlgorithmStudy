import java.io.*;
import java.util.*;

/*
 * 물병
 */

public class DH_1052 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		
		while(Integer.bitCount(n) > k) {
			answer += 1;
			n += 1;
		}
		
		System.out.println(answer);
	}
}
