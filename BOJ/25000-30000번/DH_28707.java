import java.io.*;
import java.util.*;

/*
 * 배열 정렬
 */

public class DH_28707 {
	static final int INF = Integer.MAX_VALUE;
	static class Node implements Comparable<Node> {
		int[] arr;
		int c;
		public Node(int[] arr, int c) {
			this.arr = arr;
			this.c = c;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
	}
	static class Calculator {
		int l, r, c;
		public Calculator(int l, int r, int c) {
			this.l = l;
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static int[] A; // 초기 입력되는 배열
	static HashMap<String, Integer> cost; // key: Arrays.toString(int[] arr), value: int[] arr을 만들때까지 비용
	static ArrayList<Calculator> cal; // 조작에 대한 정보 저장

	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		PriorityQueue<Node> pq = new PriorityQueue();
		cost = new HashMap();
		
		pq.add(new Node(A.clone(), 0));
		cost.put(Arrays.toString(A), 0);
		Arrays.sort(A); // A의 비내림차순 결과
		String key = Arrays.toString(A);
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if(A.equals(Arrays.toString(current.arr))) break;
			
			for(Calculator ca: cal) {
				int[] copyArr = current.arr.clone();
				int l = ca.l, r = ca.r, c = ca.c;
				
				int tmp = copyArr[l];
				copyArr[l] = copyArr[r];
				copyArr[r] = tmp;
				
				String nextKey = Arrays.toString(copyArr);
				
				int nextCost = current.c + c;
				if(!cost.containsKey(nextKey)) cost.put(nextKey, INF);
				if(nextCost < cost.get(nextKey)) {
					cost.put(nextKey, nextCost);
					pq.add(new Node(copyArr, nextCost));
				}
			}
		}
		
		
		// cost의 키에 A의 비내림차순 결과가 있으면 비용 get하고, 아니라면 -1 출력
		System.out.println(cost.containsKey(key) ? cost.get(key) : -1);
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/BOJ28707.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < A.length; i++) A[i] = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		// 조작 list 저장
		cal = new ArrayList();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			cal.add(new Calculator(l, r, c));
		}
	}
}
