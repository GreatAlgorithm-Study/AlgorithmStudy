// 배열 A를 비내림차순으로 정렬하기 위해 필요한 비용 총합의 최솟값을 출력
// 불가능할 경우 -1 출력

// 시간복잡도 : N<=8, 8!=40,320 -> 다익스트라O
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static class Node implements Comparable<Node>{ // 배열 자체를 노드로
        int[] arr; // 배열 상태
        int cost; // 현재 상태까지 발생한 비용
        public Node(int[] arr, int cost){
            this.arr = arr;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){ // 비용 기준으로 오름차순 정렬
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 배열의 길이
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine()); // 조작의 개수
        int[][] op = new int[M][3];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            op[i][0] = Integer.parseInt(st.nextToken())-1;
            op[i][1] = Integer.parseInt(st.nextToken())-1;
            op[i][2] = Integer.parseInt(st.nextToken()); // 비용
        }
        int[] ans = arr.clone();
        Arrays.sort(ans);

        PriorityQueue<Node> queue = new PriorityQueue<>(); // 다익스트라 우선순위큐
        queue.offer(new Node(arr, 0)); // 초기 비용 0 큐에 삽입

        HashMap<String, Integer> visit = new HashMap<>(); // 배열 상태 중복 방지
        visit.put(Arrays.toString(arr), 0); // 배열 상태를 문자열로 변환

        while(!queue.isEmpty()){
            Node cur = queue.poll(); // 비용 가장 작은 노드 꺼냄

            if(Arrays.equals(ans, cur.arr)){ // 배열이 정렬 됐다면 종료
                System.out.println(cur.cost);
                return;
            }

            for(int[] o : op){
                int l = o[0];
                int r = o[1];
                int c = o[2];

                int[] cloneArr = cur.arr.clone();

                int temp = cloneArr[l]; // swap
                cloneArr[l] = cloneArr[r];
                cloneArr[r] = temp;

                int newCost = cur.cost + c; // 새로운 비용 계산
                String newNode = Arrays.toString(cloneArr);

                if(!visit.containsKey(newNode)|| visit.get(newNode) > newCost){ // 새로운 비용이 더 작으면 갱신
                    queue.offer(new Node(cloneArr, newCost));
                    visit.put(newNode, newCost); // 방문처리
                }
            }
        }
        System.out.println(-1); // 정렬 불가
    }
}