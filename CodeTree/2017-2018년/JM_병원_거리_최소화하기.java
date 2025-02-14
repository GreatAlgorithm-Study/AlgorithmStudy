import java.util.*;
import java.io.*;

public class JM_병원_거리_최소화하기 {
    	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int N;
	static int M;
	static List<Point> hospital;
	static List<Point> person;
	static int answer;
	static int[][] dist;

	private static int calc(int picked) {
		int sum = 0;
		for(int i = 0; i < person.size(); i++) {
			int pDist = Integer.MAX_VALUE;
			for(int j = 0; j < hospital.size(); j++) {
			    if((picked & (1 << j)) == 0) continue;
			    if(pDist > dist[i][j]) pDist = dist[i][j];
			}
			sum += pDist;
		}
		return sum;
	}

	private static void pick(int next, int index, int picked) {
		if(index == M) {
			int currTotalSum = calc(picked);
			if(answer > currTotalSum) answer = currTotalSum;
			return;
		}

		for(int i = next; i < hospital.size(); i++) {
			pick(i + 1, index + 1, picked | (1 << i));
		}

	}

	private static void initDist() {
		for(int i = 0; i < person.size(); i++) {
			for(int j = 0; j < hospital.size(); j++) {
				dist[i][j] = Math.abs(person.get(i).y - hospital.get(j).y) + Math.abs(person.get(i).x - hospital.get(j).x);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		hospital = new ArrayList<>();
		person = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x == 1) person.add(new Point(i, j));
				else if(x == 2) hospital.add(new Point(i, j));
			}
		}

		dist = new int[person.size()][hospital.size()];
		initDist();

		answer = Integer.MAX_VALUE;
		pick(0, 0, 0);
		System.out.println(answer);
	}
}
