import java.util.*;

/*
서버 증설 횟수
*/

class DH_389479 {
    static class Node implements Comparable<Node> {
        int s, e;
        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.s != o.s) return Integer.compare(this.s, o.s); // 시작 순서
            return Integer.compare(this.e, o.e); // 끝나는 순서
        }
    }
    public int solution(int[] players, int m, int k) {
        Queue<Node> q = new ArrayDeque<>();
        
        int answer = 0;
        
        for(int i = 0; i < players.length; i++) {
            while(!q.isEmpty() && q.peek().e <= i) q.poll();
            
            int serverCnt = q.size(); // 현재 사용되는 서버의 수
            
            // 현재 서버로 사용자들이 게임을 할 수 있을 때
            if(players[i] < (serverCnt + 1) * m) continue;
            
            // 추가해야되는 서버 개수 구하기
            int sub = players[i] - (serverCnt + 1) * m;
            
            // n → (n × m) ≤ 이용할 수 있는 사람 < ((n + 1) × m) 
            // 추가해야 되는 서버 수≤
            int addServerCnt = sub / m + 1; 
            
            // addServerCnt만큼 서버 추가해주기
            for(int j = 0; j < addServerCnt; j++) {
                q.add(new Node(i, i + k)); // (시작시간, 끝나는 시간)
                answer += 1;
            }
        }
        
        return answer;
    }
}