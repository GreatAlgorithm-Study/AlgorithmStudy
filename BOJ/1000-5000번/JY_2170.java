import java.io.*;
import java.util.*;
public class JY_2170 {
	
	static class Pos implements Comparable<Pos> {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pos other) {
			return this.x - other.x;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		List<Pos> pList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			pList.add(new Pos(x, y));
		}
		
		// x기준으로 정렬
		Collections.sort(pList);
		
		int s = pList.get(0).x;
		int e = pList.get(0).y;
		int ans = 0;
		for(int i=1; i<N; i++) {
			Pos now = pList.get(i);
			
			// now.x가 end보다 작다면 겹침 -> end 갱신
			if(e >= now.x) {
				e = Math.max(e, now.y);
			}
			// 겹치지 않는 경우
			else {
				// 기존것 더해주고 새로 업데이트
				ans += (e - s);
				s = now.x;
				e = now.y;
			}
		}
		ans += (e - s);
		System.out.println(ans);

	}

}
