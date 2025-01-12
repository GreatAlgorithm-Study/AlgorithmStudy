import java.util.*;
import java.io.*;

public class JY_20061 {
	
	static final int M = 10;	// 전체 배열의 크기
	static final int R = 4;		// 빨간색 보드의 크기
	static int N;
	static int[][] g;
	static int score;
	// 블록 벡터(한개블록, 가로블록, 세로블록)
	static int[] dx = {0, 0, 1};
	static int[] dy = {0, 1, 0};
	

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("src/monomino.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());

		g = new int[M][M];
		score = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 1) 블럭 놓기
			play(t-1, x, y);
			
			// 2) 가득찬 타일 체크
			checkGreen();
			checkBlue();

			
			// 3) 연한색 부분 체크
			checkSpecialGreen();
			checkSpecialBlue();

		}
		
		// 4) 남아있는 블럭 체크
		int cnt = 0;
		for(int x=0; x<M; x++) {
			for(int y=0; y<M; y++) {
				cnt += g[x][y];
			}
		}
		System.out.println(score);
		System.out.println(cnt);
		
	}
	public static void print() {
		for(int i=0; i<M; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println();
	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<M && y>=0 && y<M;
	}
	public static boolean isFill(int x, int y, int ox, int oy) {
		return inRange(x, y) && inRange(ox, oy) &&  g[x][y] == 0 && g[ox][oy] == 0;
	}
	public static void fillGreen(int t, int x, int y) {
		int nx = x;			// 블록이 놓일 x행 위치
		int oy = y + dy[t];
		for(int i=R; i<M; i++) {
			int ox = i + dx[t];
			// 현재 칸에 블록이 있다면 그 이전 행에 블록 놓기
			if(!isFill(i, y, ox, oy)) {
				nx = i-1;
				break;
			}
		}
		if(nx == x) nx = M-1;
		g[nx][y] = 1;
		g[nx+dx[t]][y+dy[t]] = 1;

	}
	public static void fillBlue(int t, int x, int y) {
		int ny = y;				// 블록이 놓일 y열 위치
		int ox = x + dx[t];
		for(int j=R; j<M; j++) {
			int oy = j + dy[t];
			// 현재 칸에 블록이 있다면 그 이전 행에 블록 놓기
			if(!isFill(x, j, ox, oy)) {
				ny = j-1;
				break;
			}
		}
		if(ny == y) ny = M-1;
		g[x][ny] = 1;
		g[x+dx[t]][ny+dy[t]] = 1;
	}
	public static void play(int t, int x, int y) {
		// 초록색 보드 채우기(행)
		fillGreen(t, x, y);
		
		// 파란색 보드 채우기(열)
		fillBlue(t, x, y);
	}
	public static void moveRow(int x, int sx) {
		// 임시 배열 생성
		int[][] tmp = new int[M][M];
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = g[i][j];
			}
		}
		// 기준점 위의 블록들을 한칸 씩 아래로 이동
		for(int i=x; i>=sx; i--) {
			for(int j=0; j<R; j++) {
				tmp[i][j] = g[i-1][j];
			}
		}
		
		g = tmp;
	}
	public static void checkGreen() {
		int x = R+2;
		while(x < M) {
			// 열체크
			int cnt = 0;
			for(int y=0; y<R; y++) {
				cnt += g[x][y];
			}
			// x행이 가득참
			if(cnt == R) {
				score += 1;
				// x행 비우기
				Arrays.fill(g[x], 0);
				
				// 한칸씩 이동 시키기
				moveRow(x, R+1);
				x = R + 2;	// 다시 처음부터 탐색
				continue;
			}
			x++;
		}
	}
	public static void moveCol(int y, int sy) {
		// 임시 배열 생성
		int[][] tmp = new int[M][M];
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = g[i][j];
			}
		}
		// 기준점 왼쪽의 블록들을 한칸 씩 오른쪽으로 이동
		for(int j=y; j>=sy; j--) {
			for(int i=0; i<R; i++) {
				tmp[i][j] = g[i][j-1];
			}
		}
		
		g = tmp;
	}
	public static void checkBlue() {
		int y = R+2;
		while(y < M) {
			// 행체크
			int cnt = 0;
			for(int x=0; x<R; x++) {
				cnt += g[x][y];
			}
			// y열이 가득참
			if(cnt == R) {
				score += 1;
				// y열 비우기
				for(int x=0; x<R; x++) {
					g[x][y] = 0;
				}
		
				// 한칸씩 이동 시키기
				moveCol(y, R+1);
				y = R + 2;	// 다시 처음부터 탐색
				continue;
			}
			y++;
		}
	}
	public static void checkSpecialGreen() {
		int cnt = 0;
		for(int i=R; i<R+2; i++) {
			for(int j=0; j<R; j++) {
				if(g[i][j] == 1) {
					cnt++;
					break;
				}
			}
		}
		// 연한칸에 블록 존재
		while(cnt > 0) {
			// 마지막 행 비우기
			Arrays.fill(g[M-1], 0);
			
			// 한칸씩 이동 시키기
			moveRow(M-1, R);
			cnt--;
		}
	}
	public static void checkSpecialBlue() {
		int cnt = 0;
		for(int j=R; j<R+2; j++) {
			for(int i=0; i<R; i++) {
				if(g[i][j] == 1) {
					cnt++;
					break;
				}
			}
		}
		// 연한칸에 블록 존재
		while(cnt > 0) {
			// 마지막 열 비우기
			for(int x=0; x<R; x++) {
				g[x][M-1] = 0;
			}
			
			// 한칸씩 이동 시키기
			moveCol(M-1, R);
			cnt--;
		}
	}

}
