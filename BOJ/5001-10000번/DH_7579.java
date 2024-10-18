import java.io.*;
import java.util.*;

/*
 * 앱
 */

public class DH_7579 {
	static class App {
		int m, c;

		public App(int m, int c) {
			this.m = m;
			this.c = c;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 앱의 개수
		int M = Integer.parseInt(st.nextToken()); // 추가 확보해야되는 메모리

		App[] apps = new App[N + 1];
		for(int i = 0; i < apps.length; i++) apps[i] = new App(0, 0);
		st = new StringTokenizer(br.readLine());

		for(int i = 1; i < apps.length; i++) {
			apps[i].m = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		int costSum = 0;
		for(int i = 1; i < apps.length; i++) {
			apps[i].c = Integer.parseInt(st.nextToken());
			costSum += apps[i].c;
		}

		// 메모리 M바이트를 확보하기 위한 앱 비활성화 최소 비용을 찾기 위한 dp 배열
		// j원일 때 최대 몇 바이트까지 확보할 수 있는지 저장
		int[][] knapsack = new int[N + 1][costSum + 1];

		for(int i = 1; i < knapsack.length; i++) {
		    // 0 포함시켜줘야 함!
			for(int c = 0; c < knapsack[0].length; c++) {
				if(c - apps[i].c >= 0) {
					knapsack[i][c] = Math.max(knapsack[i - 1][c - apps[i].c] + apps[i].m, knapsack[i - 1][c]);
				} else {
					knapsack[i][c] = knapsack[i - 1][c];
				}
			}
		}

		int result = 0;
		// 최대 확보할 수 있는 dp 배열의 정보까지 확인
		for(int c = 1; c < knapsack[0].length; c++) {
			if(knapsack[N][c] >= M) {
				result = c;
				break;
			}
		}

		System.out.println(result);
	}
}
