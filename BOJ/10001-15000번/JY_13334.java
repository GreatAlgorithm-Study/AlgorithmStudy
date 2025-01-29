import java.io.*;
import java.util.*;

public class JY_13334 {

	static int N;
	static long D;
	static class Pos implements Comparable<Pos>{
		long h, o;

		public Pos(long h, long o) {
			super();
			this.h = h;
			this.o = o;
		}
		
		// 끝점 기준으로 정렬
		@Override
		public int compareTo(Pos other) {
			return (int)(this.o - other.o);
		}

		@Override
		public String toString() {
			return "Pos [h=" + h + ", o=" + o + "]";
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		// 끝점이 작은 순으로 정렬 => 시작점만 신경쓰면됨
		// 반대로) 시작점 기준으로 정렬하면, 시작점이 이동할 때 이전 것들의 시작점, 끝점을 모두 신경써줘야 함
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			long h = Long.parseLong(st.nextToken());
			long o = Long.parseLong(st.nextToken());
			
			if(h <= o) {
				pq.add(new Pos(h, o));				
			} else {
				pq.add(new Pos(o, h));
			}
			
		}
		
		D = Long.parseLong(br.readLine());
		
		int ans = 0;
		PriorityQueue<Long> tpq = new PriorityQueue<>();
		while(!pq.isEmpty()) {
			Pos now = pq.poll();
			long start = now.o - D;
			tpq.add(now.h);
			
			while(!tpq.isEmpty() && tpq.peek() < start) {
				// 현재 시작지점 보다 작은 시작점들은 현재 구간에 포함될 수 없음
				tpq.poll();
			}
			
			ans = Math.max(ans, tpq.size());
		}
		
		System.out.println(ans);
		
	}

}
