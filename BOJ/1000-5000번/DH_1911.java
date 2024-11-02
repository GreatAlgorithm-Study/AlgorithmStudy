import java.io.*;
import java.util.*;

/*
 * 훍길 보수하기
 */

public class DH_1911 {
	static class Node {
		int s, e;
		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 폭우
		int L = Integer.parseInt(st.nextToken()); // 널빤지
		
		Node[] nodes = new Node[N];
		for(int i = 0; i < nodes.length; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			nodes[i] = new Node(s, e);
		}
		
		// 시작점 기준 정렬
		Arrays.sort(nodes, (o1, o2) -> Integer.compare(o1.s, o2.s));

		int current = 0, cnt = 0;
		for(Node n: nodes) {
			if(current < n.s) {
				current = n.s;
			}
			
			while(current < n.e) {
				current += L;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
