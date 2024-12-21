import java.util.*;

// 시간복잡도 : nodeinfo < 10,000 -> O(N^2) 이하
class HW_42892 {
    static int index;
    static int[][] answer;
    static class Node{
        int x, y, number;
        Node left, right;

        Node(int x, int y, int number){
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes= new Node[nodeinfo.length];

        for(int i=0; i<nodeinfo.length; i++){ // 노드 초기화
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }

        Arrays.sort(nodes, (a, b) ->{ // 노드 정렬
            if(a.y != b.y){
                return b.y - a.y; // y 내림차순
            }
            return a.x - b.x; // x 오름차순
        });

        // 트리 생성
        Node root = nodes[0];
        for(int i=1; i<nodes.length; i++){
            insertNode(root, nodes[i]);
        }

        answer = new int[2][nodeinfo.length]; // 순회 결과를 저장
        index = 0;
        preorder(root); // 전위 순회(부모->왼쪽->오른쪽)
        index = 0; // 인덱스 초기화
        postorder(root); // 후위 순회(왼쪽->오른쪽->부모)

        return answer;

    }
    static void insertNode(Node parent, Node child) {
        if (child.x < parent.x) { // 왼쪽 서브트리
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else { // 오른쪽 서브트리
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }

    static void preorder(Node node){
        if(node != null){
            answer[0][index++] = node.number; // 부모 노드 방문
            preorder(node.left); // 왼쪽 서브트리 방문
            preorder(node.right); // 오른쪽 서브트리 방문
        }
    }
    static void postorder(Node node){
        if(node != null){
            postorder(node.left); // 왼쪽 서브트리 방문
            postorder(node.right); // 오른쪽 서브트리 방문
            answer[1][index++] = node.number; // 부모 노드 방문
        }
    }
}