import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_32070 {
	static class Color {
		int box;
		boolean up;

		public Color(int box, boolean up) {
			this.box = box;
			this.up = up;
		}
	}
	static int N;
	static Color[][] colors;
	static int[] boxParent;
	static int[] subBoxCount;

	private static int findParent(int x) {
		if(x == boxParent[x]) return x;
		return boxParent[x] = findParent(boxParent[x]);
	}

	private static void union(int u, int v) {
		int uP = findParent(u);
		int vP = findParent(v);
		if(uP < vP) {
			boxParent[vP] = uP;
			subBoxCount[uP] += subBoxCount[vP];
		}
		else if(uP > vP) {
			boxParent[uP] = vP;
			subBoxCount[vP] += subBoxCount[uP];
		}
	}

	private static void makeCycle() {
		for (int i = 1; i <= N; i++) {
			boxParent[i] = i;
			subBoxCount[i] = 1;
		}
		
		for (int i = 1; i <= N; i++) { // 같은 색의 공을 담은 box union
			union(colors[i][0].box, colors[i][1].box);
		}
	}

	private static boolean isTwoPairsOnTop() {
		int[] twoPairs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if(colors[i][0].up && colors[i][1].up) {
				int rootIdx = findParent(colors[i][0].box);
				twoPairs[rootIdx]++;
			}
		}
		for (int i = 1; i <= N; i++) {
			int rootIdx = findParent(colors[i][0].box);
			if(twoPairs[rootIdx] >= 2) return true;
		}
		return false;
	}

	private static int solve() {
		makeCycle();
		if(isTwoPairsOnTop()) return -1;

		int ans = 0;
		boolean[] check = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			int rootIdx = findParent(colors[i][0].box);
			if(check[rootIdx]) continue;

			check[rootIdx] = true;

			if(subBoxCount[rootIdx] >= 2) {
				ans += subBoxCount[rootIdx] + 1;
			}
		}

		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		colors = new Color[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(colors[a][0] == null) colors[a][0] = new Color(i, true);
			else colors[a][1] = new Color(i, true);
			if(colors[b][0] == null) colors[b][0] = new Color(i, false);
			else colors[b][1] = new Color(i, false);
		}

		boxParent = new int[N + 1];
		subBoxCount = new int[N + 1];
		System.out.println(solve());
	}
}
