import java.util.*;

public class YJ_118669 {
    class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    List<ArrayList<Node>> graph = new ArrayList<>();
    int[] table;
    Set<Integer> summitSet = new HashSet<>();
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        //산봉우리 중복제거
        for(int mountain : summits){
            summitSet.add(mountain);
        }
        //최소 가중치 테이블 초기화
        table = new int[n+1];
        Arrays.fill(table, Integer.MAX_VALUE);
        //그래프 모델링
        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            int to = path[0];
            int from = path[1];
            int distance = path[2];
            graph.get(to).add(new Node(from, distance));
            graph.get(from).add(new Node(to, distance));
        }

        dijkstra(gates);

        int[] answer = {0,Integer.MAX_VALUE};
        for (int mountain : summits) {
            int intensity = answer[1];
            if (table[mountain] < intensity) {  //intensity의 최솟값
                answer[0] = mountain;
                answer[1] = table[mountain];
            } else if (table[mountain] == intensity && mountain < answer[0]) {
                answer[0] = mountain;   //최소 intensity 가 여러개일 경우 낮은 등산코스
            }
        }
        return answer;
    }

    // 휴식 없이 이동해야 하는 시간 중 가장 긴 시간을 해당 등산코스의 intensity (가중치)
    // 출입구는 처음과 끝에 한 번씩, 산봉우리는 한 번만 포함 -> 출발 > 산봉우리 까지의 거리
    void dijkstra(int[] gates){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //출발지점으로 초기화
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            table[gate] = 0;
        }

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int index = node.index;
            int distance = node.distance;

            if(table[index] < distance){
                continue;
            }
            if(summitSet.contains(index)){  //현재 노드가 산봉우리라면 패스
                continue;
            }

            for(Node next : graph.get(index)){
                int nextIndex = next.index;
                int cost = Math.max(table[index], next.distance);
                if(table[nextIndex] > cost){
                    table[nextIndex] = cost;
                    pq.offer(new Node(nextIndex, cost));
                }
            }
        }
    }
}
