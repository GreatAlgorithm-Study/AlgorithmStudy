import java.io.*;
import java.util.*;

/*
 * 올림픽
 */

public class DH_8979 {
	static class Node implements Comparable<Node> {
		int g, s, b;
		public Node(int g, int s, int b) {
			this.g = g;
			this.s = s;
			this.b = b;
		}

		@Override
		public int compareTo(Node o) {
			if(this.g != o.g) return -1 * Integer.compare(this.g, o.g);
			if(this.s != o.s) return -1 * Integer.compare(this.s, o.s);
			return -1 * Integer.compare(this.b, o.b);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		TreeMap<Node, Integer> map = new TreeMap<Node, Integer>();
		Node[] arr = new Node[N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
				
			arr[idx - 1] = new Node(g, s, b);
			
			if(!map.containsKey(arr[idx - 1])) map.put(arr[idx - 1], 1);
			else map.put(arr[idx - 1], map.get(arr[idx - 1]) + 1);
		}
		
		int result = 0;

		int idx = 1;
		
		Node current = arr[K - 1];
		for(Node n: map.keySet()) {
			if(current.g == n.g && current.s == n.s && current.b == n.b) {
				result = idx;
				break;
			}
			idx += map.get(n);
		}
		
		System.out.println(result);
	}
}
