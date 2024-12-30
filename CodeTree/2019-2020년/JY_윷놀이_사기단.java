import java.io.*;
import java.util.*;

public class JY_윷놀이_사기단 {
	
	static final int M = 10;
	static final int C = 5;
	static final int P = 4;
	static int[] mrr;
	static boolean[] visited;
	// 말 위치 배열
	static int[] srr;
	static Map<Integer, Node> g;
	static int ans;
	static Set<Integer> blueSet;
	static class Node {
		int next, cost;

		public Node(int next, int cost) {
			super();
			this.next = next;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [next=" + next + ", cost=" + cost + "]";
		}
		

	}
	public static void initMap() {		
		g = new HashMap<>();
		int n = 2;
		g.put(0, new Node(1, 0));
		for(int i=1; i<21; i++) {
			g.put(i, new Node(i+1, n));
			n += 2;
		}
		
		// 10칸
		g.put(5, new Node(21, 10));
		g.put(21, new Node(22, 13));
		g.put(22, new Node(23, 16));
		g.put(23, new Node(29, 19));
		// 20칸
		g.put(10, new Node(24, 20));
		g.put(24, new Node(25, 22));
		g.put(25, new Node(29, 24));
		// 30칸
		g.put(15, new Node(26, 30));
		g.put(26, new Node(27, 28));
		g.put(27, new Node(28, 27));
		g.put(28, new Node(29, 26));
		
		// 25칸
		g.put(29, new Node(30, 25));
		g.put(30, new Node(31, 35));
		g.put(31, new Node(20, 40));
		
		// 40칸
		g.put(20, new Node(-1, 40));
		
		
		// blue Set 설정
		blueSet = new HashSet<>();
		blueSet.add(5);
		blueSet.add(10);
		blueSet.add(15);
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		mrr = new int[M+1];
		for(int i=1; i<M+1; i++) {
			mrr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// 0) 윷놀이 맵 생성
		initMap();
		printG();
		
//		System.out.println(findPos(15, 0, 5));

		visited = new boolean[g.size()+1];
		srr = new int[P+1];
		ans = 0;
		dfs(1, 0, new int[] {0, 0, 0, 0, 0});
		
		System.out.println(ans);
		
	}
	public static void printG() {
		for(int k: g.keySet()) {
			System.out.println(k+": "+g.get(k));
		}
		System.out.println();
	}
	public static int findPos(int num, int depth, int cnt) {
		if(depth == cnt) return num;
		
		if(num == 20) return g.get(num).next;
		else if(blueSet.contains(num) && depth > 0) {
			return findPos(num+1, depth+1, cnt);
		}
		else {
			return findPos(g.get(num).next, depth+1, cnt);
		}
		
	}
	public static boolean canGo(int[] arr, int nextPos) {
		for(int i=1; i<P+1; i++) {
			if(arr[i] == nextPos) return false;
		}
		return true;
	}
	public static void dfs(int depth, int score, int[] prr) {
		if(depth == M+1) {
			System.out.println(">>> score: "+score);
			ans = Math.max(ans, score);
			return;
		}
		
		// copy prr
		int[] trr = new int[P+1];
		for(int i=1; i<P+1; i++) trr[i] = prr[i];

		System.out.println("trr: "+Arrays.toString(trr)+" depth:"+depth+" score:"+score);
		int move = mrr[depth];
		for(int i=1; i<P+1; i++) {
			if(trr[i] == -1) continue;
			int nowPos = trr[i];
			int nextPos = findPos(nowPos, 0, move);
			System.out.println(">> now: "+nowPos+" next: "+nextPos);			
			// 다음 위치가 도착지
			if(nextPos == -1) {
				trr[i] = nextPos;
				dfs(depth+1, score, trr);
				trr[i] = nowPos;
			}
			else {
				if(!canGo(trr, nextPos)) continue;
				// 이동 시킬 수 있음
				trr[i] = nextPos;
				dfs(depth+1, score+g.get(nextPos).cost, trr);
				trr[i] = nowPos;
			}
		}
		
	}

}

/*
 * 여러말이 n칸을 나눠 이동 -> 조합
 * 파란색 칸 : 10의 배수
 * 
 * */
