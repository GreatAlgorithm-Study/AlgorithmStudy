import java.io.*;
import java.util.*;
public class JY_병원_거리_최소화하기 {
	
	static int N, M;
	static int[][] g;
	static Map<Integer, Hospital> hMap;
	static List<Integer> tList;
	static int ans;
	static class Hospital {
		int num, x, y;

		public Hospital(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Hospital [num=" + num + ", x=" + x + ", y=" + y + "]";
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new int[N][N];
		hMap = new HashMap<>();
		int n = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
				if(g[i][j] == 2) {
					hMap.put(n, new Hospital(n, i, j));
					n++;
				}
			}
		}
		
		tList = new ArrayList<>();
		ans = Integer.MAX_VALUE;
		// 최대 경우의 수 : 100(사람수) * 13C7 * 7
		comb(0, 0);
		
		System.out.println(ans);
	}
	public static void comb(int depth, int start) {
		if(depth == M) {
			int score = find(tList);
			ans = Math.min(ans, score);
			return;
		}
		for(int i=start; i<hMap.size(); i++) {
			tList.add(i);
			comb(depth+1, i+1);
			tList.remove(tList.size()-1);
		}
	}
	public static int cal(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	public static int find(List<Integer> hList) {
		int total = 0;
		// 사람 반복
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(g[i][j] != 1) continue;
				int tmp = Integer.MAX_VALUE; 
				// 병원 반복
				for(int h : hList) {
					Hospital now = hMap.get(h);
					int dist = cal(i, j, now.x, now.y);
					tmp = Math.min(tmp, dist);
				}
				total += tmp;
			}
		}
		
		return total;
	}

}
