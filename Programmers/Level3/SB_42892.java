import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SB_42892 {
    static int N;
    static Node[] tree;

    private static void postOrder(Node node, List<Integer> post) {
        if (node==null) return;         // 리프노드에서 더 이상 노드가 없으면 리턴

        postOrder(node.left, post);
        postOrder(node.right, post);
        post.add(node.n);
    }
    private static void preOrder(Node node, List<Integer> pre) {
        if (node==null) return;         // 리프노드에서 더 이상 노드가 없으면 리턴

        pre.add(node.n);
        preOrder(node.left, pre);
        preOrder(node.right, pre);
    }

    private static void setChild(Node parent, Node child) {
        if (child.x < parent.x){                            // 왼쪽 노드 정하기
            if (parent.left==null) parent.left = child;     // 왼쪽 자리 비었으면 child로 설정
            else setChild(parent.left, child);               // 비어있지 않으면 왼쪽 노드를 부모로 하는 child 설정
        }else {                                             // 오른쪽 노드 정하기
            if (parent.right==null) parent.right = child;
            else setChild(parent.right, child);
        }
    }
    public static int[][] solution(int[][] nodeinfo) {
        N = nodeinfo.length;
        tree = new Node[N];

        // 노드 값 포함해서 트리 배열 재구성
        for (int i = 0; i < N; i++) {
            int[] n = nodeinfo[i];
            tree[i] = new Node(n[0], n[1], i + 1);
        }
        Arrays.sort(tree);

        // 각 노드의 오른쪽, 왼쪽 값 설정
        Node root = tree[0];
        for (int i = 1; i < N; i++) {
            setChild(root, tree[i]);
        }

        // 전위 순회
        List<Integer> pre = new ArrayList<>();
        preOrder(root, pre);                        

        // 후위 순회
        List<Integer> post = new ArrayList<>();
        postOrder(root, post);              

        int[][] ans = new int[2][N];
        ans[0] = pre.stream().mapToInt(i->i).toArray();
        ans[1] = post.stream().mapToInt(i -> i).toArray();
        return ans;
    }

    static class Node implements Comparable<Node>{
        int x, y, n;
        Node left = null;
        Node right = null;

        public Node(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }

        @Override
        public int compareTo(Node o) {
            if (o.y-this.y==0) return this.x-o.x;
            return o.y-this.y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", n=" + n +
                    '}';
        }
    }
}
