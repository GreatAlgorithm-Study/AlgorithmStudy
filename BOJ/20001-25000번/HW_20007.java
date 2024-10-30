import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도 : N<=1,000, M<=100,000 O(NM) 가능
// 이웃집 모두에 떡을 돌리기 위한 최소 일을 출력
// 만약 모두 방문할수 없으면 -1을 출력
public class Main {
    static class Node implements Comparable<Node>{
        int house;
        int cost;
        Node(int house, int cost){
            this.house = house;
            this.cost = cost;
        }
        public int compareTo(Node other){
            return this.cost - other.cost;
        }
    }

    static List<Node>[] graph;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int X = Integer.parseInt(st.nextToken()); // 도로 길이 <= 1000000000
        int Y = Integer.parseInt(st.nextToken()); // 성현이 집

        // 그래프 초기화
        graph = new ArrayList[N];
        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){ // 도로 위치 입력 받기 -> 그래프?
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C)); // A -> B 양방향
            graph[B].add(new Node(A, C)); // B -> A
        }
        distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        dijkstra(Y);

        int days = 1; // 최소 일수(첫날 1일)
        int totalDistance = 0;
        Arrays.sort(distance);

        for (int i = 0; i < N; i++) { // 거리 확인
            if (i != Y) { // 성현이의 집은 제외
                if (distance[i] * 2 > X) { // 왕복 거리 초과
                    System.out.println(-1); // 방문x
                    return;
                }
                if (totalDistance + distance[i] * 2 > X) { // 하루 이동 거리 초과
                    days++; // 하루 추가
                    totalDistance = 0; // 새로운 날로 초기화
                }
                totalDistance += distance[i] * 2; // 방문한 거리 추가
            }
        }
        System.out.println(days); // 최소 일수
    }
    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0; // 시작 위치는 거리 0

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curHouse = cur.house;
            int curCost = cur.cost;

            if (curCost > distance[curHouse]) continue;

            for (int i = 0; i < graph[curHouse].size(); i++) {
                Node neighbor = graph[curHouse].get(i);
                int newCost = distance[curHouse] + neighbor.cost;

                if (newCost < distance[neighbor.house]) {
                    distance[neighbor.house] = newCost;
                    pq.add(new Node(neighbor.house, newCost));
                }
            }
        }
    }
}