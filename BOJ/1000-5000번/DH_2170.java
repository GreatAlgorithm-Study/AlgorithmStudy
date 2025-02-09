import java.io.*;
import java.util.*;

/*
 * 선 긋기
 */

public class DH_2170 {
	static class Point implements Comparable<Point> {
		int s, e;
		public Point(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.s != o.s) return Integer.compare(this.s, o.s);
			return Integer.compare(this.e, o.e);
		}
	}
	
	static final int MIN = -1_000_000_000, MAX = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		
		// 우선순위큐에 점들 넣어주기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			pq.add(new Point(s, e));
		}
		
		// (MAX, MAX) 로 넣으면 마지막에 넣은 점이 MAX일 때, 올바른 정답이 안나옴
		pq.add(new Point(MAX + 1, MAX + 1));
		
		int start = MIN, end = MIN;
		int result = 0;
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			
			// 현재 시작점이 이전까지 확인한 지점들의 끝점보다 크면
			// 이전 점들을 이어서 만들어진 선분의 길이 더해주기
			if(end < current.s) {
				result += end - start;
				
				start = current.s;
				end = current.e;
				
			} else {
				end = Math.max(end, current.e);
			}
		}
		
		System.out.println(result);
	}
}
