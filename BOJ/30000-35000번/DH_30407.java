import java.io.*;
import java.util.*;

/*
 * 나비의 간식을 훔쳐먹은 춘배
 */

public class DH_30407 {
	static int N, H, D, K, result;
	static int[] R;
	
	public static void main(String[] args) throws Exception {
		initInput();
		
		// 깜짝 놀라게 하는 경우가 없을 때
		powerset(0, H, D, -2);
		
		// i 번째에 깜짝 놀라게 하기 시전
		for(int i = 0; i < N; i++) powerset(0, H, D, i);

		if(result <= 0) System.out.println(-1);
		else System.out.println(result);
	}
	
	static void powerset(int depth, int h, int dis, int surprised) {
		if(depth == N) {
			result = Math.max(result, h);
			return;
		}
		
		if(h <= 0 || h < result) return;

		//깜짝 놀라게 하기
		if(surprised == depth) {
			int punchPower = Math.max(0, R[depth] - dis);
			powerset(depth + 1, h - punchPower, dis, surprised);
		} else {
			
			// 웅크리기 ------------------------------------------
			int punchPower = 0; // 나비의 펀치 세기
			
			if(surprised + 1 == depth) punchPower = 0; // 깜짝 놀라게 하기 다음 턴의 경우 나비의 행동 무시함
			else punchPower = Math.max(0, (R[depth] - dis) / 2);

			powerset(depth + 1, h - punchPower, dis, surprised);
			
			// 네발로 걷기 ------------------------------------------
			int nextDis = dis + K;
			
			if(surprised + 1 == depth) punchPower = 0; // 깜짝 놀라게 하기 다음 턴의 경우 나비의 행동 무시함
			else punchPower = Math.max(0, R[depth] - nextDis);
			
			powerset(depth + 1, h - punchPower, nextDis, surprised);
		}
	}

	static void initInput() throws Exception {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 냥냥펀치의 수
		
		st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken()); // 춘배의 체력
		D = Integer.parseInt(st.nextToken()); // 현재 나비 사이의 거리
		K = Integer.parseInt(st.nextToken()); // 네발로 걷기 시 이동하는 거리
		
		R = new int[N];
		
		for(int i = 0; i < N; i++) R[i] = Integer.parseInt(br.readLine());
	}
}
