import java.io.*;
import java.util.*;

/*
 * 철로
 */

public class DH_13334 {
	static class Node implements Comparable<Node> {
		int s, e;
		public Node(int s, int e) {
			this.s = s; // 시작 지점
			this.e = e; // 끝 지점
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.e, o.e); // 끝 지점에 대해 오름차순
		}
	}
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("./input/BOJ13334.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		// 입력되는 사무실, 집 지점은 왼쪽 지점을 기준으로 오름차순으로 우선순위를 적용하여 큐에 저장
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 시작점과 끝점이 정렬이 안된 상태에서 주어지기 때문에 
			// 입력 값에서 최소, 최대를 구해서 시작, 끝점을 구함
			int s = Math.min(a, b), e = Math.max(a, b);
			
			pq.add(new Node(s, e));
		}
		
		int d = Integer.parseInt(br.readLine());
		int startLimit = -100_000_000; // 처음 왼쪽 기준점을 최소값으로 설정
		int result = 0;
		
		// 입력을 받을 때는 끝점에 대해 오름차순이었지만
		// 철도 범위 안의 사람의 수를 구하기 위해서는 시작점 기준으로 오름차순으로 하는 우선순위큐가 추가로 있어야 됨
		// 시작점 오름차순으로 해야되는 이유: 왼쪽 시작점을 크기가 작은거부터 큰거 순서로 확인해야 되기 때문
		PriorityQueue<Node> tmp = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.s, o2.s));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			// 집과 사무실 사이의 거리가 철도의 길이보다 길다면 넘어가기
			if(current.e - current.s > d) continue;
			
			// 현재 사람의 오른쪽 지점 - 왼쪽 기준점이 철도의 길이보다 길다면
			if(current.e - startLimit > d) {
				
				tmp.add(current); // tmp큐에 넣어주기
				
				// current를 tmp에 넣게 되면서 왼쪽 시작점의 위치를 조정해야될 수도 있음
				while(!tmp.isEmpty()) {
					// 현재 끝 지점 - 가능한 왼쪽 시작점 <= d가 될 때까지 tmp.poll 하기
					if(current.e - tmp.peek().s > d) {
						tmp.poll();
					} else break;
				}
				// 왼쪽 시작점 조정해주기
				startLimit = Math.min(current.s, tmp.peek().s);
				
			} 
			// 현재 사람의 오른쪽 기점 - 왼쪽 기준점 <= 철도의 길이
			else {
				// tmp큐에 넣어주기
				tmp.add(current);
				
				// current에서 시작점이 현재 왼쪽 기준점보다 작아질 수 있기 때문에 
				// startLimit 값 조정해주기
				startLimit = Math.min(startLimit, current.s);
			}

			result = Math.max(result, tmp.size());
		}
		
		System.out.println(result);
	}
}
