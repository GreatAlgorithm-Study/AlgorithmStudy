import java.io.*;
import java.util.*;

public class YJ_20007 {
    static class Pos implements Comparable<Pos>{
        int index;
        int distance;

        Pos (int index, int distance){
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Pos o){
            return this.distance - o.distance;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static List<List<Pos>> graph = new ArrayList<>();
    static int[] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        table = new int[N];
        Arrays.fill(table,INF);

        //graph 모델링
        for(int i=0; i<N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Pos(to,distance));
            graph.get(to).add(new Pos(from,distance));
        }

        shareMochi(Y);
        Arrays.sort(table);
        System.out.println(countDays(X));
    }

    static void shareMochi (int index){
        PriorityQueue<Pos> road = new PriorityQueue<>();

        table[index] = 0;
        road.offer(new Pos(index,0));

        while(!road.isEmpty()){
            Pos pos = road.poll();
            int home = pos.index;
            int distance = pos.distance;

            //기존의 최단거리 값이 더 작을 경우 유지
            if(distance > table[home]){
                continue;
            }

            //기존의 최단거리 값이 더 작을 경우 유지
            for(Pos next : graph.get(home)){
                int currentAndNext = distance + next.distance;    //처음시작+다음노드까지의 거리
                //현재 거리가 기존보다 최단거리일 경우 갱신하기
                if(currentAndNext < table[next.index]){    //다음노드의 기존의 최단거리와 비교
                    table[next.index] = currentAndNext;
                    road.offer(new Pos(next.index,table[next.index]));
                }
            }
        }
    }

    static int countDays(int x){
        int days = 1;
        int total = 0;
        for(int i=0; i<table.length; i++){
            if(table[i]*2 > x){
                days = -1;
                break;
            }
            total += table[i]*2;
            if(total > x){
                days++;
                total = table[i]*2;
            }
        }
        return days;
    }

}
