import java.io.*;
import java.util.*;

/*
 * 준표의 조약돌
 */

public class DH_15831 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 조약돌의 총 개수
		int B = Integer.parseInt(st.nextToken()); // 검은 조약돌의 최대 개수 
		int W = Integer.parseInt(st.nextToken()); // 하얀 조약돌의 최소 개수
		
		String str = br.readLine();
		
		int s = 0, e = 0, bCnt = 0, wCnt = 0, answer = 0;
		
		while(e < N) {
			if(B < bCnt) { // 검정 조약돌의 개수가 크다면 start 지점 오른쪽으로
				if(str.charAt(s) == 'B') bCnt -= 1;
				else wCnt -= 1;
				s += 1;
			} else { // 하얀 조약돌의 개수가 작다면 end 지점 오른쪽으로
				if(str.charAt(e) == 'B') bCnt += 1;
				else wCnt += 1;
				
				e += 1;
			}
			
			if(bCnt <= B && wCnt >= W) answer = Math.max(answer, e - s);
		}
		
		System.out.println(answer);
	}
}
