import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 모든 트럭들이 다리를 건너는 최단시간
// 시간 복잡도 : n<=1000, w<=100, L<=1000, O(nm)
// 트럭 순서에 변화X -> FIFO 구조

/*
다리 길이 관리 (다리 위 상태 큐) 사용을 떠올리지 못함
 */
class HW_13335_2{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭의 개수
        int w = Integer.parseInt(st.nextToken()); // 다리의 길이
        int L = Integer.parseInt(st.nextToken()); // 다리의 최대하중

        Queue<Integer> truck = new LinkedList<>(); // 트럭 대기 큐
        Queue<Integer> bridge = new LinkedList<>(); // 다리 위 상태 큐(다리 하중을 고려해야 하기 때문)
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            truck.add(Integer.parseInt(st.nextToken()));
        }
        for(int i=0; i<w; i++){
            bridge.add(0); // 다리를 빈 상태로 초기화
        }
        int second = 0; // 다리 건너는 최단 시간
        int sum = 0; // 다리 위 무게 합

        while(!bridge.isEmpty()){
            second++;
            sum = sum - bridge.poll(); // 트럭 한대 출발
            if(!truck.isEmpty()) {
                if (truck.peek() + sum <= L) {
                    int temp = truck.poll();
                    bridge.add(temp);
                    sum += temp;
                } else {
                    bridge.add(0);
                }
            }
        }
        System.out.println(second);
    }
}