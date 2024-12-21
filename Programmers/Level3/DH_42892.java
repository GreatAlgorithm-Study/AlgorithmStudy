import java.util.*;

/*
 * 길 찾기 게임
 */

public class DH_42892 {
	static class Node {
		int v, x, y;
		Node l, r;
		
		public Node(int v, int x, int y) {
			this.v = v;
			this.x = x;
			this.y = y;
		}
	}
	static Node[] nodes;
	static int idx;
	
	static int[][] solution(int[][] nodeInfo) {
		
		nodes = new Node[nodeInfo.length];
		
		for(int i = 0; i < nodeInfo.length; i++) {
			nodes[i] = new Node(i + 1, nodeInfo[i][0], nodeInfo[i][1]);
		}
		
		// 우선순위에 따라 정렬
		Arrays.sort(nodes, (o1, o2)-> {
			if(o1.y != o2.y) return -1 * Integer.compare(o1.y, o2.y);
			return Integer.compare(o1.x, o2.x);
		});
		
		Node root = nodes[0];
		
		// 트리 만들기
		for(int i = 1; i < nodes.length; i++) {
			Node current = nodes[i];
			makeTree(root, current);
		}
		
		int[][] answer = new int[2][nodeInfo.length];

		preOrder(root, answer);
		
		idx = 0;
		postOrder(root, answer);
		
		return answer;
	}
	
	// 전위순회
	static void preOrder(Node p, int[][] answer) {
		if(p == null) return;
		answer[0][idx++] = p.v;
		preOrder(p.l, answer);
		preOrder(p.r, answer);
	}
	
	// 후외순회
	static void postOrder(Node p, int[][] answer) {
		if(p == null) return;
		postOrder(p.l, answer);
		postOrder(p.r, answer);
		answer[1][idx++] = p.v;
		
	}
	
	// [1]: x 좌표, [2]: y좌표
	static void makeTree(Node p, Node current) {
		if(p.x < current.x) {
			if(p.r == null) p.r = current;
			else makeTree(p.r, current);
		}
		else {
			if(p.l == null) p.l = current;
			else makeTree(p.l, current);
		}
	}
	
	public static void main(String[] args) throws Exception {
		int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
		System.out.println(solution(nodeInfo));
	}
}
