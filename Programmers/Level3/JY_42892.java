import java.util.*;
class Solution {
    static List<Node> nList;
	static int N;
	static boolean[] visited;
	static class Node {
		int x, y, num;
        Node left, right;
		public Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
		@Override
		public String toString() {
			return this.num+": ["+this.x+","+this.y+"]";
		}
	}
    
    static List<Node> nodeList;
    static List<Integer> preOrderList;
    static List<Integer> postOrderList;
    
    public int[][] solution(int[][] nodeinfo) {
        N = nodeinfo.length;
        int[][] answer = new int[2][N];
    
        // 노드 리스트 생성 및 정렬
        nodeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nodeList.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }

        // Y 값 기준 내림차순, X 값 기준 오름차순 정렬
        nodeList.sort((o1, o2) -> o2.y == o1.y ? o1.x - o2.x : o2.y - o1.y);

        // 트리 만들기
        Node root = nodeList.get(0);
        for (int i = 1; i < nodeList.size(); i++) {
            insert(root, nodeList.get(i));
        }

        // 전위 순회 
        preOrderList = new ArrayList<>();
        postOrderList = new ArrayList<>();
        preOrder(root);
        postOrder(root);

        // 결과 출력
        for(int i=0; i<N; i++) {
            answer[0][i] = preOrderList.get(i);
            answer[1][i] = postOrderList.get(i);
        }
        // System.out.println("Pre-order: " + preOrderList);
        // System.out.println("Post-order: " + postOrderList);

        return answer;
    }

    public static void insert(Node parent, Node child) {
        // 부모의 x값이 자식보다 크다면 -> 왼쪽 자식
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        }
        // 부모의 x값이 자식보다 작다면 -> 오른쪽 자식
        else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }
    
    // 전위 순회 : 루->왼->오
    private static void preOrder(Node node) {
        if (node == null) return;
        preOrderList.add(node.num);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 후위 순회 : 왼->오->루
    private static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        postOrderList.add(node.num);
    }
}