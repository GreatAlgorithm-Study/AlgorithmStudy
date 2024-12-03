import java.io.*;
import java.util.*;

public class JY_15831 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		String srr = br.readLine();
		
		int s = 0;
		int e = 0;
		int bc = 0;
		int wc = 0;
		int ans = 0;
		
		while(e < N) {
			// 조약돌 개수 카운트
			if(srr.charAt(e) == 'B') bc++;
			else wc++;

			// 검은 조약돌이 B개 보다 많으면 시작점 증가로 구간 감소시키기
			if(bc > B) {
				if(srr.charAt(s)=='B') bc--;
				else wc--;
				s++;
			}	
			
			// 준표가 만족하는 조약돌 개수
			if(wc >= W && bc <= B) {
				ans = Math.max(ans, (e-s)+1);
			}
			
			e++;
		}
		System.out.println(ans);

	}

}
