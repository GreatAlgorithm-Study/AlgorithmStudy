import java.io.*;
import java.util.*;

/*
 * 행복 유치원
 */

public class DH_13164 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 원생의 수
		int K = Integer.parseInt(st.nextToken()); // 조의 개수
		
		st = new StringTokenizer(br.readLine());
		
		int[] diff = new int[N - 1];
		
		int prev = Integer.parseInt(st.nextToken());
		for(int i = 0; i < diff.length; i++) {
			int current = Integer.parseInt(st.nextToken());
			diff[i] = current - prev;
			prev = current;
		}
		
		Arrays.sort(diff);
		int result = 0;
		for(int i = 0; i < N - K; i++) result += diff[i];
		System.out.println(result);
	}
}
