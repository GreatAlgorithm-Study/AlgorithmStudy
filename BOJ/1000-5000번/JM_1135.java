import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 완전 틀림, 해결책 제시 X
 */
public class JM_1135 {
	static class Node {
		int num;
		int time;

		public Node(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}
	static int N;
	static ArrayList<Integer>[] trees;

	private static int dfs(int curr) {
		int maxTime = 0;
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.time - o1.time);
		for(int next: trees[curr]) {
			int nextTime = dfs(next);
			queue.offer(new Node(next, nextTime));
		}

		int t = 1;
		while(!queue.isEmpty()) {
			Node next = queue.poll();
			maxTime = Math.max(maxTime, next.time + t);
			t++;
		}

		return maxTime;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		trees = new ArrayList[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = new ArrayList<>();
			int parent = Integer.parseInt(st.nextToken());
			if(parent != -1) trees[parent].add(i);
		}
		System.out.println(dfs(0));
	}
}
