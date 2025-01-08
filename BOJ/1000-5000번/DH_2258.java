import java.io.*;
import java.util.*;

/*
 * 정육점
 */

public class DH_2258 {

	static class Node implements Comparable<Node> {
		int w, c;

		public Node(int w, int c) {
			this.w = w;
			this.c = c;
		}

		public Node(int w, int c, int sum) {
			this.w = w;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			if (this.c != o.c)
				return Integer.compare(this.c, o.c); // 가격 오름차순
			return Integer.compare(o.w, this.w); // 무게 내림차순
		}
	}

	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		int N = Integer.parseInt(st.nextToken()); // 덩어리의 개수
		long M = Integer.parseInt(st.nextToken()); // 필요한 고기의 양

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			pq.add(new Node(a, b));
		}

		int result = INF;

		long totalSum = 0;
		int totalCost = 0, same = 0, markCost = -1;

		while (!pq.isEmpty()) {
			Node current = pq.poll();

			if (markCost != current.c) {
				markCost = current.c;
				same = 0;
			} else same += current.c;

			totalSum += current.w;
			totalCost = current.c;
			if (totalSum >= M)
				result = Math.min(result, totalCost + same);
		}

		System.out.println(result == INF ? -1 : result);
	}
}
