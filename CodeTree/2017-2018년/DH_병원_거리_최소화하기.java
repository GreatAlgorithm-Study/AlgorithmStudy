import java.io.*;
import java.util.*;

/*
 * 병원 거리 최소화하기
 */

public class DH_병원_거리_최소화하기 {
	static int N, M;
	static int[] dis;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static int[][] map;
	static ArrayList<Integer> people, hospitals;
	
	public static void main(String[] args) throws Exception {
		initInput();

		// hospitals.size() 개에서 m개의 병원 고르기 (조합)
		System.out.println(comb(0, 0, 0, hospitals.size()));
	}
	
	static int comb(int depth, int idx, int status, int size) {
		int result = Integer.MAX_VALUE;
		
		if(depth == M) {
			
			Arrays.fill(dis, Integer.MAX_VALUE);
			
			// 선택된 병원과 사람들 사이의 최소 거리 구하기
			for(int i = 0; i < size; i++) {
				if((status & (1 << i)) == 0) continue;
				
				int pos = hospitals.get(i);
				int r = pos / N, c = pos % N;
				
				for(int k = 0; k < people.size(); k++) {
					int p = people.get(k);
					int pr = p / N, pc = p % N;

					int tmp = 0;
					tmp += Math.abs(pr - r) + Math.abs(pc - c);
					dis[k] = Math.min(dis[k], tmp);
				}
			}
			
			int minDis = 0;
			for(int d: dis) minDis += d;
			
			return minDis;
		}
		
		for(int i = idx; i < size; i++) {
			status |= (1 << i);
			result = Math.min(comb(depth + 1, i + 1, status, size), result);
			status ^= (1 << i);
		}
		
		return result;
	}
	
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // map의 크기
		M = Integer.parseInt(st.nextToken()); // M개의 병원 고르기 (조합)
		
		map = new int[N][N];
		
		people = new ArrayList<Integer>();
		hospitals = new ArrayList<Integer>(); // 병원들의 좌표 저장
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 0) continue;
				
				// 병원과 사람들의 좌표 저장
				int pos = r * N + c;
				if(map[r][c] == 1) people.add(pos);
				if(map[r][c] == 2) hospitals.add(pos);
			}
		}
		
		dis = new int[people.size()];
	}
}
