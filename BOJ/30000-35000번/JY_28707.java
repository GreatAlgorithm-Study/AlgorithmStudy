package day1126;

import java.io.*;
import java.util.*;

public class JY_28707 {

	static int N, M;
	static int[] orr;
	static int[][] crr;
	static Map<Integer, Integer> cMap;
	static class Node implements Comparable<Node> {
		int[] arr;
		int num, cost;
		
		public Node(int[] arr, int num, int cost) {
			super();
			this.arr = arr;
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			return this.cost - other.cost;
		}

		@Override
		public String toString() {
			return "Node [arr=" + Arrays.toString(arr) + ", num=" + num + ", cost=" + cost + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		orr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			orr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		// 명령 리스트
		crr = new int[M][3];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			crr[i][0] = Integer.parseInt(st.nextToken()) -1;
			crr[i][1] = Integer.parseInt(st.nextToken()) -1;
			crr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		cMap = new HashMap<>();
		
		dijkstra();
//		System.out.println(cMap);
		
		// 비내림차순 노드 찾기
		Arrays.sort(orr);
		int num = arrayToInt(orr);
		if(cMap.containsKey(num)) {
			System.out.println(cMap.get(num));
		} else {
			System.out.println(-1);
		}
		
	}
	public static int arrayToInt(int[] brr) {
		int val = 1;
		int ans = 0;
		for(int i=N-1; i>=0; i--) {
			ans += (val*brr[i]);
			val *= 10;
		}
		return ans;
	}
	public static int[] copyArray(int brr[]) {
		int[] tmp = new int[N];
		for(int i=0; i<N; i++) {
			tmp[i] = brr[i];
		}
		return tmp;
	}
	public static int[] swapArray(int[] brr, int i, int j) {
		int[] trr = copyArray(brr);
		int tmp = trr[i];
		trr[i] = trr[j];
		trr[j] = tmp;
		
		return trr;
	}
	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int num = arrayToInt(orr);
		cMap.put(num, 0);
		pq.add(new Node(copyArray(orr), num, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			for(int i=0; i<M; i++) {
				int[] next = swapArray(now.arr, crr[i][0], crr[i][1]);
				int nextNum = arrayToInt(next);
				
				if(cMap.getOrDefault(nextNum, Integer.MAX_VALUE) > now.cost+crr[i][2]) {
					cMap.put(nextNum, now.cost+crr[i][2]);
					pq.add(new Node(next, nextNum, now.cost+crr[i][2]));
				}
			}
		}
	}

}
