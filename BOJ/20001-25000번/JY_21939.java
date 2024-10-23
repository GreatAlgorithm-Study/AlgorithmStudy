package day1023;

import java.io.*;
import java.util.*;

public class JY_21939 {
	
	static int MAXNUM = 100001;
	// 문제 번호에 해당하는 난이도 저장 배열
	static int[] srr;
	
	// 가장 어려운 문제 출력 우선순위 큐
	static PriorityQueue<int[]> hpq = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[0]==o2[0]) return o2[1] - o1[1];
			return o2[0]-o1[0];
		}
	});
	// 가장 쉬운 문제 출력 우선순위 큐
	static PriorityQueue<int[]> epq = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[0]==o2[0]) return o1[1] - o2[1];
			return o1[0] - o2[0];
		}
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		srr = new int[MAXNUM];
		int N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			int[] exam = {level, num};
			hpq.add(exam);
			epq.add(exam);
			srr[num] = level;
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			
			if(type.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				srr[num] = level;
				int[] exam = {level, num};
				hpq.add(exam);
				epq.add(exam);
			} 
			
			else if(type.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());
				
				if(x == 1) {
					// 해당 문제번호의 난이도와 우선순위큐에 있는 난이도가 일치하는 문제찾기
					while(!hpq.isEmpty() && srr[hpq.peek()[1]] != hpq.peek()[0]) {
						// sloved된 문제라면 pass
						hpq.poll();
					}
					System.out.println(hpq.peek()[1]);
				} else {
					while(!epq.isEmpty() && srr[epq.peek()[1]] != epq.peek()[0]) {
						// sloved된 문제라면 pass
						epq.poll();
					}
					System.out.println(epq.peek()[1]);
				}
				
			} else {
				int num = Integer.parseInt(st.nextToken());
				srr[num] = -1;	// 난이도 -1로 solved 처리
			}
		}
		

	}

}
