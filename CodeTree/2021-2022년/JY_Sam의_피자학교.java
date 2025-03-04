import java.io.*;
import java.util.*;
public class JY_Sam의_피자학교 {
	
	static int N, K;
	static int MX, MY, D; 
	static int[] arr;
	static int[][] g;
	// 상 우 하 좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 도우 말 때, 총 그래프 크기
		findSize();
//		System.out.println("MZ: "+MX+" MY: "+MY+" D: "+D);
		
		
		int ans = 1;
		while(true) {
			// 1) 밀가루 넣기
			inputWheat();

			// 2) 도우 말기
			rollDough();

			// 3) 도우 눌러주기
			pressDough();

			// 4) 도우 2번 반으로 접기
			halfDough();

			// 5) 3번 반복
			pressDough();

			if(isDone()) break;
			
			ans++;
		}
		
		System.out.println(ans);
		

	}
	public static void printG(int[][] a) {
		for(int i=0; i<a[0].length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		System.out.println();
	}
	public static void findSize() {
		// M : 말린 도우 사이즈
		// D: 총 도우 그래프 사이즈
		
		boolean isStop = false;
		for(int i=1; i<N; i++) {
			if(isStop) break;
			for(int j=i; j<i+2; j++) {
				int rollSize = Math.max(i, j);
				int bottom = N - (i*j);
				if(rollSize > bottom) {
					isStop = true;
					break;
				}
				MX = i;
				MY = j;
			}
		}
		D = Math.max(N - (MX*MY), MX+1);
		
	}
	public static void inputWheat() {
		int minW = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			minW = Math.min(minW, arr[i]);
		}
		
		// 가장 작은 위치에 밀가루 +1 (여러개라면 모두)
		for(int i=0; i<N; i++) {
			if(arr[i] == minW) arr[i]++;
		}
	}
	public static boolean inRange(int x, int y, int sx, int sy) {
		return x>=0 && x<sx && y>=0 && y<sy;
	}
	public static void rollDough() {
		// g 생성
		g = new int[D][D];
		
		// 말린 도우 시작점
		int x = MX-1;
		int y = 0;
		int dir = 0;
		for(int i=MX*MY-1; i>=0; i--) {
			g[x][y] = arr[i];
			
			// 다음 위치
			int nx = x+dx[dir];
			int ny = y+dy[dir];
			// 방향을 바꿔야 함
			if(!inRange(nx, ny, MX, MY) || g[nx][ny] != 0) {
				dir = (dir+1)%4;
				nx = x+dx[dir];
				ny = y+dy[dir];
			}
			x = nx;
			y = ny;
		}
		
		// 나머지 바닥 도우 넣기
		x = MX;
		y = 0;
		for(int i=MX*MY; i<N; i++) {
			g[x][y] = arr[i];
			y++;
		}
		
	}
	public static void pressDough() {
		int DX = g.length;
		int DY = g[0].length;
		int[][] tmp = new int[DX][DX];
		
		for(int i=0; i<DX; i++) {
			for(int j=0; j<DY; j++) {
				if(g[i][j] == 0) continue;
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(!inRange(nx, ny, DX, DY)) continue;
					if(g[nx][ny] == 0) continue;
					
					int div = Math.abs(g[i][j]-g[nx][ny]) / 5;
					if(g[i][j] > g[nx][ny]) tmp[i][j] -= div;
					else tmp[i][j] += div;
				}
			}
		}
		
		for(int i=0; i<DX; i++) {
			for(int j=0; j<DY; j++) {
				g[i][j] += tmp[i][j];
			}
		}
		
		// 도우 1자로 펴기
		int[] trr = new int[N];
		int idx = 0;
		for(int j=0; j<DY; j++) {
			for(int i=DX-1; i>=0; i--) {
				if(g[i][j] == 0) continue;
				trr[idx] = g[i][j];
				idx++;
			}
		}
		
		arr = trr;
	}
	public static void halfDough() {
		int S = N / 4;
		int dir = 1;  // 우 ->
		
		int M = Math.max(S, 4);
		g = new int[M][M];
		
		// 맨 아래층
		int x = 3;
		int y = 0;
		for(int i=N-S; i<N; i++) {
			g[x][y] = arr[i];
			x = x + dx[dir];
			y = y + dy[dir];
		}
		dir = (dir+2)%4;
		
		x = 2;
		for(int i=0; i<N-S; i+=S) {
			if(x % 2 == 0) y = S-1;
			else y = 0;
			for(int j=i; j<i+S; j++) {
				g[x][y] = arr[j];
				x = x + dx[dir];
				y = y + dy[dir];
			}
			x--;
			dir = (dir+2)%4;
			
		}
	}
	public static boolean isDone() {
		int minV = Integer.MAX_VALUE;
		int maxV = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			minV = Math.min(minV, arr[i]);
			maxV = Math.max(maxV, arr[i]);
		}
		
		return Math.abs(maxV-minV) <= K;
	}

}
