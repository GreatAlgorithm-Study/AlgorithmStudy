import java.io.*;
import java.util.*;

/*
수도배관공사
 */

public class DH_2073 {
	static class Pipe {
		int l, c;
		public Pipe(int l, int c) {
			this.l = l;
			this.c = c;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken()); // 거리
		int P = Integer.parseInt(st.nextToken()); // 파이프 개수

		Pipe[] pipes = new Pipe[P + 1];
		for(int i = 1; i < P + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			pipes[i] = new Pipe(l, c);
		}

		// 수도관 한 개를 만들어 총 길이가 정확히 D가 되어야 함
		int[] dp = new int[D + 1];
		dp[0] = Integer.MAX_VALUE;

		for(int i = 1; i < P + 1; i++) {
			for(int j = D; j >= pipes[i].l; j--) {
				int min = Math.min(dp[j - pipes[i].l], pipes[i].c);
				dp[j] = Math.max(dp[j], min);
			}
		}

		System.out.println(dp[D]);
	}
}
