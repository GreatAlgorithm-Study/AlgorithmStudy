import java.io.*;
import java.util.*;

/*
 * 초밥 회전 시스템을 구현을 통해 관리하면 매우 복잡해짐
 * 
 * 주방장이 만든 초밥이 손님에게 도달하는 시간을 계산한 뒤, 사라지는 시간
 * 손님들이 초밥을 다 먹고 떠나는 시간을 계산하여 쿼리에 추가
 * 
 * 초밥과 손님의 입장과 퇴장을 쿼리로 표현하여 시간 순으로 처리...
 * 모든 행동들이 시간 순으로 일어나기 때문에, 이렇게 구현하면 된다고 한다..!
 */

public class DH_코드트리_오마카세 {
	static class Query implements Comparable<Query> {
		int cmd, t, x, n;
		String name;

		public Query(int cmd, int t, int x, int n, String name) {
			this.cmd = cmd;
			this.t = t;
			this.x = x;
			this.n = n;
			this.name = name;
		}

		@Override
		public int compareTo(Query o) {
			if(this.t != o.t) return Integer.compare(this.t, o.t); // 시간 순
			return Integer.compare(this.cmd, o.cmd); // 명령순
		}
	}
	static class Time {
		int in, out;
		
		public Time(int in) {
			this.in = in;
		}
		
		public void setOutTime(int time) {
			this.out = Math.max(time, this.out);
		}
		
	}
	static int L, Q;
	
	// 쿼리가 시간 순으로 입력될 수 있도록 함
	static ArrayList<Query> query = new ArrayList<>();
	static HashSet<String> people = new HashSet<String>(); // 등장한 사람 목록 관리
	static HashMap<String, PriorityQueue<Query>> pQuery = new HashMap<>(); // 사람들마다 쿼리를 관리함
	static HashMap<String, Integer> pos = new HashMap<>(); // 사람들마다 위치 관리
	static HashMap<String, Time> time = new HashMap<>(); // 사람들의 출입 시간 관리
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/코드트리오마카세.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		for(int q = 0; q < Q; q++) {
			
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			int t = 0, x = 0, n = 0;
			String name = null;
			
			if(cmd == 100) {
				t = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				
				if(pQuery.get(name) == null) pQuery.put(name, new PriorityQueue<>());
				pQuery.get(name).add(new Query(cmd, t, x, n, name));
			}
			
			if(cmd == 200) {
				t = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				n = Integer.parseInt(st.nextToken());
				
				people.add(name);
				
				time.put(name, new Time(t));
				pos.put(name, x);
			}
			
			if(cmd == 300) {
				t = Integer.parseInt(st.nextToken());
			}
			
			query.add(new Query(cmd, t, x, n, name));
		}
		
		// 각 사람마다 자신의 이름이 적힌 초밥을 언제 먹게 되는지 계산하고, 기존 쿼리에 추가
		for(String p: people) {

			// 마지막으로 먹는 초밥 중 가장 늦은 시간이 됨!
			while(!pQuery.get(p).isEmpty()) {
				Query current = pQuery.get(p).poll();
				
				int removeTime = 0; // 사람이 나가게 되는 시간
				
				// 초밥 - 사람
				// 초밥이 사람이 등장하기 전에 미리 주어진 상황
				if(current.t < time.get(p).in) {
					
					// 사람이 들어왔을 때, 초밥의 위치
					int sushiPos = (current.x + (time.get(p).in - current.t)) % L;
					int addTime = (pos.get(p) - sushiPos + L) % L;

					removeTime = time.get(p).in + addTime;
				} 
				
				// 사람 - 초밥
				else {
					int addTime = (pos.get(p) - current.x + L) % L;
					removeTime = current.t + addTime;
				}
				
				time.get(p).setOutTime(removeTime); // 나가는 시간을 제일 마지막 초밥을 먹는 시간으로 설정해야 됨
				
				// 초밥이 사라지는 쿼리 추가
				query.add(new Query(111, removeTime, 0, 0, p));
			}
		}
		
		// 사람들이 나가는 쿼리 추가
		for(String p: people) query.add(new Query(222, time.get(p).out, 0, 0, p));
		
		Collections.sort(query);
		
		int sushiCnt = 0, peopleCnt = 0;
		
		for(Query current: query) {
			
			if(current.cmd == 100) sushiCnt++; // 초밥이 생겼다가
			else if(current.cmd == 111) sushiCnt--; // 초밥을 먹었다가
			else if(current.cmd == 200) peopleCnt++; // 사람이 들어왔다가
			else if(current.cmd == 222) peopleCnt--; // 사람이 나갔다가
			else sb.append(peopleCnt).append(" ").append(sushiCnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
}