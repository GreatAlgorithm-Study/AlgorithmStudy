import java.io.*;
import java.util.*;

//2 <= N <= 8 으로 조합 경우의 수 8! > 40320개
//1 <= A <= 10
public class YJ_28707 {
    static class Controller {
        int l;
        int r;
        int c;

        public Controller(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }

    static class Node implements Comparable<Node>{
        int v;
        int c;

        public Node(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o){
            return this.c - o.c;
        }
    }

    static int n;
    static int answer;
    static List<Controller> list = new ArrayList<>();
    static Map<Integer,Integer> table = new HashMap<>();    //<배열,최단거리> 저장 테이블
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        //배열 입력값 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sb.append(arr[i]);
        }

        //★정렬하는 배열 전체를 하나의 노드 단위로 생각
        int original = Integer.parseInt(sb.toString());
        //비내림차순 정렬 정답 저장
        Arrays.sort(arr);
        answer = arrToInt(arr);

        //조작 입력값 초기화
        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            list.add(new Controller(l,r,c));
        }

        table.put(original,0);
        pq.offer(new Node(original,0));
        dijkstra();
        System.out.println(table.getOrDefault(answer,-1));
    }

    public static void dijkstra(){
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(answer == node.v){
                break;
            }
            //신규 비용이 기존 비용보다 더 클 경우 갱신불가
            if(table.containsKey(node.v) && table.get(node.v) < node.c){
                continue;
            }
            for(Controller controller : list){
                int next = swap(node.v, controller);
                int cost = node.c + controller.c;
                if(table.containsKey(next) && table.get(next) <= cost){
                    continue;
                }
                //기존 비용+이동비용 이 기존의 최소 비용보다 더 작을 경우 최단거리(비용) 갱신
                table.put(next,cost);
                pq.offer(new Node(next,cost));
            }
        }
    }

    private static int swap(int v, Controller controller){
        int[] arr = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            if(v % 10 == 0){    // 10,20,30.. 등의 두 자릿수 배수가 있다는 뜻 (ex. 1410)
                arr[i] = v % 100;   //두 자릿수를 배열에 저장
                v /= 100;
            } else{   //단일 자릿수일 경우 기존 자릿수 유지
                arr[i] = v % 10;
                v /= 10;
            }
        }

        //조작에 맞춰서 자리 교체
        int l = controller.l;
        int r = controller.r;
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;

        return arrToInt(arr);
    }

    static StringBuilder sb = new StringBuilder();
    private static int arrToInt(int[] arr) {
        for(int num : arr){
            sb.append(num);
        }
        int result = Integer.parseInt(sb.toString());
        sb.setLength(0);
        return result;
    }
}