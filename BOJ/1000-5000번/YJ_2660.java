import java.util.*;
import java.io.*;

public class YJ_2660 {
    static class Node {
        int index;
        int distance;
        Node (int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    static List<List<Integer>> graph = new ArrayList<>();
    static int MAX = 51;    //★무작정 MAX_VALUE 넣지않기
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        //다익스트라 초기화
        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            if(to == -1 && from == -1){
                break;
            }
            graph.get(to).add(from);
            graph.get(from).add(to);
        }

        getCandidates();
    }

    static void getCandidates(){
        int min = MAX;
        List<Integer> candidates = new ArrayList<>();

        //한 사람에 대한 모든 간선의 연결수 구하기
        for(int i=1; i<n+1; i++){
            //회원 별 연결점수 중 최고점을 저장
            int connection = findConnection(i);

            //회장 후보: 점수가 가장 낮은 사람
            if(connection < min){
                candidates = new ArrayList<>();
                candidates.add(i);
                min = connection;
            }else if(connection == min) {
                candidates.add(i);
            }
        }

        System.out.printf("%d %d%n",min,candidates.size());
        candidates.forEach(candidate -> System.out.printf("%d ",candidate));
    }

    static int findConnection (int index){
        PriorityQueue<Node> queue = new PriorityQueue<>((n1,n2) -> n2.distance - n1.distance);
        queue.offer(new Node(index,0));

        int[] table = new int[n+1];
        Arrays.fill(table,MAX);
        table[index] = 0;

        while(!queue.isEmpty()){
            Node person = queue.poll();

            for(int friend : graph.get(person.index)){
                if(table[friend] > table[person.index]+1){  //★다음 간선까지의 거리 1
                    table[friend] = table[person.index]+1;
                    //★거리를 누적해서 다음 순회에서 친구의 친구 > 친구의 친구의 친구 > ... 연결을 만듬
                    queue.offer(new Node(friend,table[friend]));    //★Node(연결된 친구,거리)
                }
            }
        }

        int max = 0;
        for(int i=1; i<n+1; i++){
            max = Math.max(max,table[i]);
        }
        return max;
    }
}