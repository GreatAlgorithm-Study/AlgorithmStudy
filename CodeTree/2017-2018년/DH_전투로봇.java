import java.io.*;
import java.util.*;

public class DH_전투로봇 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class qPoint implements Comparable<qPoint> {
		Point p;
		int d;
		public qPoint(Point p, int d) {
			this.p = p;
			this.d = d;
		}
		
		@Override
		public int compareTo(qPoint o) {
			if(this.d == o.d) {
				if(this.p.r == o.p.r) return Integer.compare(this.p.c, o.p.c); // 열 오름차순
				return Integer.compare(this.p.r, o.p.r); // 행 오름차순s
			}
			return Integer.compare(this.d, o.d); // 거리 오름차순
		}
	}

	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static int N, dieMonsterCnt, totalTime;
	static int[][] levelMap, monsterIdxMap;
	static Point robot;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		
		int time;
		while((time = bfs()) != 0) {
			totalTime += time;
		}
		System.out.println(totalTime);
	}
	
	static int bfs() {

		PriorityQueue<qPoint> q = new PriorityQueue<>();
		boolean[][] v = new boolean[N][N];
		
		q.add(new qPoint(robot, 0));
		v[robot.r][robot.c] = true;
		int robotLevel = levelMap[robot.r][robot.c];
		levelMap[robot.r][robot.c] = 0;
		
		// 없앨 수 있는 몬스터 정보 구하기
		while(!q.isEmpty()) {
			qPoint current = q.poll();

			// 몬스터를 없앨 수 있다면
			if(canKillMonster(current.p, robotLevel)) {
				// 몬스터 없앤 개수 늘리기
				dieMonsterCnt++;
				// 없앤 몬스터의 개수와 로봇의 레벨이 같다면
				if(dieMonsterCnt == robotLevel) {
					// 로봇의 레벨 늘려주기
					dieMonsterCnt = 0;
					robotLevel++;
				}

				// map에 있는 몬스터의 정보 없애주기
				levelMap[current.p.r][current.p.c] = 0;
				monsterIdxMap[current.p.r][current.p.c] = 0;

				// 로봇 위치, map에 로봇 정보 갱신하기
				robot.r = current.p.r;
				robot.c = current.p.c;
				levelMap[current.p.r][current.p.c] = robotLevel;

				// 로봇이 이동한 위치 반환
				return current.d;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = current.p.r + dr[d];
				int nc = current.p.c + dc[d];

				// 범위를 벗어나거나 || 이미 방문했던 곳이거나 || 자신의 레벨보다 큰 몬스터가 있거나
				if(!check(nr, nc) || v[nr][nc] || levelMap[nr][nc] > robotLevel) continue;
				q.add(new qPoint(new Point(nr, nc), current.d + 1));
				v[nr][nc] = true;
			}
		}
		
		// 몬스터를 해치우지 못한 경우
		return 0;
	}
	
	static boolean canKillMonster(Point current, int robotLevel) {
		int monsterLevel = levelMap[current.r][current.c];
		return 0 < monsterLevel && monsterLevel < robotLevel;
	}
	
	static boolean check(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("../AlgorithmStudy/input/전투로봇.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		levelMap = new int[N][N];
		monsterIdxMap = new int[N][N];
		
		int idx = 1;
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				levelMap[r][c] = Integer.parseInt(st.nextToken());
				if(levelMap[r][c] == 9) {
					robot = new Point(r, c);
					levelMap[r][c] = 2; // 전투로봇의 초기 레벨: 2
				}
				else if(levelMap[r][c] != 0) {
					monsterIdxMap[r][c] = idx;
				}
			}
		}
		
//		printRobotInfo();
//		printlevelMapInfo();
//		printMostersInfo();
	}
	
	static void printRobotInfo() {
		System.out.println("robotInfo: " + robot.toString() + ", level: " + levelMap[robot.r][robot.c]);
	}
	
	static void printlevelMapInfo() {
		for(int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(levelMap[r]));
		}
		
		System.out.println();
	}
}
