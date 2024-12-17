import java.io.*;
import java.util.*;

/*
 * 프렉탈 평면
 */

public class DH_1030 {
	static StringBuilder sb = new StringBuilder();
	static int s, N, K, R1, R2, C1, C2;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		s = stoi(st); N = stoi(st); K = stoi(st);
		R1 = stoi(st); R2 = stoi(st);
		C1 = stoi(st); C2 = stoi(st);
		
		// 시간 s일 때, R1행 C1열부터 R2행 C2열까지의 모습을 출력
		if(s == 0) sb.append("0");
		else {
			for(int r = R1; r < R2 + 1; r++) {
				for(int c = C1; c < C2 + 1; c++) {
					int outer = (int) Math.pow(N, s);
					double percent = ((N - K) >> 1) * (1.0) / N;
					sb.append(getBlockStatus(r, c, s, N, outer, percent));
				}
				
				sb.append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static int getBlockStatus(int r, int c, int s, int n, int outer, double percent) {
		
		// 바깥 사각형과 안쪽 사각형 사이 간격 구하기
		int interval = (int) (outer * percent);

		if(isInCenter(r, c, interval, outer)) return 1;
		if(s == 1) return 0;

		// 그 다음 확인할 사각형 사이즈
		int nextRecSize = (outer / n);
		return getBlockStatus(r % nextRecSize, c % nextRecSize, s - 1, n, nextRecSize, percent);
	}
	
	static boolean isInCenter(int r, int c, int interval, int outer) {
		return r >= interval && r < (outer - interval) && c >= interval && c < (outer - interval);
	}

	static int stoi(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
