import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_30407 {
	static int N; // 냥냥펀치 수
	static int H; // 춘배의 체력
	static int D; // 현재 나비 사이의 거리 D
	static int K; // 네발로 걷기 시, 이동하는 거리
	static int[] R;
	static int maxH;

	public static void dfs(int rIdx, int currH, int dist, boolean surprise) {
		if(currH <= maxH) return;
		if(rIdx == N) {
			maxH = Math.max(maxH, currH);
			return;
		}

		// 웅크리기
		int punch = (R[rIdx] - dist) > 0 ? (R[rIdx] - dist) : 0;
		int damage = punch / 2;
		dfs(rIdx + 1, currH - damage, dist, surprise);


		// 네발로 걷기
		int nextDist = dist + K;
		damage = (R[rIdx] - nextDist) > 0 ? (R[rIdx] - nextDist) : 0;
		dfs(rIdx + 1, currH - damage, nextDist, surprise);

		// 깜짝 놀라게 하기
		if(surprise && rIdx < N - 1) {
			int tmpR = R[rIdx + 1];
			R[rIdx + 1] = 0;
			dfs(rIdx + 1, currH - punch, dist, !surprise);
			R[rIdx + 1] = tmpR;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		R = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			R[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, H, D, true);
		System.out.println(maxH <= 0 ? -1 : maxH);
	}
}
