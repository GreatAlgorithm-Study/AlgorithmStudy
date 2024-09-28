// 시간 복잡도 : n, L <= 1,000 단순 구현 고려x
// 모든 트럭들이 다리를 건너는 최단시간 구하기
// 사용할 자료구조 : 큐 (먼저온 트럭이 먼저 나감),
import java.util.*;
import java.io.*;
public class HW_13335 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭의 수
        int w = Integer.parseInt(st.nextToken()); // 다리의 길이
        int L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중

        // 반복문을 어떻게 작성하지?
        st = new StringTokenizer(br.readLine());
        Queue<Integer> trucks = new LinkedList<>(); // 대기 중인 트럭 큐
        for(int i=0; i<n; i++){
            trucks.add(Integer.parseInt(st.nextToken())); // 입력 받음
        }

        Queue<Integer> bridge = new LinkedList<>(); // 다리위 트럭들 큐
        for(int i=0; i<w; i++){
            bridge.add(0); // 처음에는 다리위에 트럭이 없기 때문에 0으로 초기화
        }

        int cnt =0; // 다리 건너는 시간
        int weightBridge = 0; // 현재 다리 무게

        while (!bridge.isEmpty()) { // 다리 큐가 빌 때까지 반복
            cnt++; // 소요 시간++
            weightBridge -= bridge.poll(); // 트럭이 다리에서 내릴 때 (현재 다리 무게) - (다리 위 트럭 무게)
            if(!trucks.isEmpty()){ // 트럭 큐가 빌 때가지 반복
                if(trucks.peek() + weightBridge <= L){ // (현재 트럭 무게) + (현재 다리 무게) <= (다리 하중)
                    weightBridge += trucks.peek(); // 트럭이 다리 위에 있음 (다리 무게) + (현재 트럭 무게)
                    bridge.add(trucks.poll()); // 트럭 무게를 다리에 추가
                } else{
                    bridge.add(0); // L(다리 하중)보다 클 경우 트럭을 추가 하지않음
                }
            }
        }
        System.out.println(cnt);
    }
}

