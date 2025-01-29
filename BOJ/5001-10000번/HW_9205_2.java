import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HW_9205_2{
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            int n = Integer.parseInt(br.readLine()); // 편의점 개수
            Node[] nodes = new Node[n + 2];
            for(int i=0; i<n+2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                nodes[i]  = new Node(x, y);
            }
            System.out.println(Bfs(nodes, n) ? "happy" : "sad");
        }
    }
    private static boolean Bfs(Node[] nodes, int n) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 2];
        queue.add(nodes[0]); // 시작(집)
        visited[0] =true;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(cal(cur, nodes[n+1]) <=1000){ // 현재 지점과 거리 계산
                return true;
            }
            for(int i=1; i<n+2; i++){ // 편의점(1~n), 페스티벌(n+1) 모두 방문할 수 있는지 탐색
                if(!visited[i] && cal(cur, nodes[i]) <= 1000){ // 이동 가능한 지
                    visited[i] = true;
                    queue.add(nodes[i]);
                }
            }
        }
        return false;
    }
    private static int cal(Node a, Node b){
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
