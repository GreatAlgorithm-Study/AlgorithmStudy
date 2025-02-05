import java.util.ArrayList;

class JW_42892 {

    class Node {
        int v, x, y;
        Node left;
        Node right;

        Node(int v, int x, int y) {
            this.v = v;
            this.x = x;
            this.y = y;
        }
    }

    ArrayList<Node> tree = new ArrayList<>();
    int preIdx = 0;
    int postIdx = 0;
    int[][] answer;

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        for (int i = 0; i < n; i++)
            tree.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        // 노드 정렬 (깊이, 왼쪽 순으로)
        tree.sort((v1, v2) -> v1.y != v2.y ? v2.y - v1.y : v1.x - v2.x);
        Node root = tree.get(0); // 첫 노드가 루트
        for (int i = 1; i < n; i++)
            insert(root, tree.get(i)); // 루트에 노드 삽입
        answer = new int[2][n];
        traversal(root); // 트리 순회
        return answer;
    }

    // 트리 순회
    private void traversal(Node node) {
        answer[0][preIdx++] = node.v; // preOrder
        if (node.left != null)
            traversal(node.left);
        if (node.right != null)
            traversal(node.right);
        answer[1][postIdx++] = node.v; // postOrder
    }

    private void insert(Node root, Node node) {
        // 왼쪽에 삽입되어야 할 경우
        if (root.x > node.x)
            if (root.left == null)
                root.left = node;
            else
                insert(root.left, node); // 재귀 호출
        else if (root.right == null)
            root.right = node;
        else
            insert(root.right, node); // 재귀 호출
    }
}