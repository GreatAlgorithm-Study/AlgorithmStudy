import java.util.*;

/**
 * 알고리즘: Queue O(n^2)
 * 시간복잡도: 1 ≤ n ≤ 1,000 개의 범위의 트럭이 다리를 건너야함
 * 아이디어:
 * 한번의 반복을 했을 때 일어날 수 있는 모든 과정을 순서에 맞게 작성해야함
 * - 다리에 있는 모든 트럭은 한칸씩 전진한다
 * - 한컨씩 전진 했을 떼 "가장 앞에 있는 트럭"이 다리의 길이까지 왔다면 다리에서 탈출한다
 * - 다리 최대하중 이하일때만 트럭이 다리에 올라올 수 있다
 * - 한번의 반복이 시간 +1
 * 참고: https://dy-coding.tistory.com/entry/%EB%B0%B1%EC%A4%80-13335%EB%B2%88-%ED%8A%B8%EB%9F%AD-java
 */
public class YJ_13335 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        //n: 다리를 건너는 트럭의 수, w: 다리의 길이, L: 다리의 최대하중
        int N = sc.nextInt();
        int W = sc.nextInt();
        int L = sc.nextInt();

        //트럭별 이동해야 하는 수 저장공간
        int[] moveCount = new int[N];
        Queue<Truck> trucks = new LinkedList<>();
        for(int i=0; i<N; i++){
            trucks.offer(new Truck(i, sc.nextInt()));
        }

        System.out.println(getMinTime(W, L, trucks, moveCount));
    }

    static class Truck{
        int index;
        int weight;
        public Truck(int index,int weight){
            this.index = index;
            this.weight = weight;
        }
    }

    static int getMinTime(int W, int L, Queue<Truck> trucks, int[] moveCount){
        Queue<Truck> bridge = new LinkedList<>();
        int time = 0;
        int bridgeWeight = 0;

        while(!trucks.isEmpty()){   //최대 N번 반복
            //다리에 올라갈 예정인 트럭
            Truck truck = trucks.peek();
            int idx = truck.index;
            int weight = truck.weight;

            if(!bridge.isEmpty()){
                int start = bridge.peek().index;    //★다리 위에 있는 가장 앞에 있는 트럭의 인덱스
                for(int i = start; i<idx; i++){ //O(logN)★새로 올라가려는 트럭의 인덱스 idx 전까지 다리 위에 있는 트럭들이 한 칸씩 전진
                    moveCount[i]--;
                }

                if(moveCount[start] == 0) {
                    bridgeWeight -= bridge.poll().weight;
                }
            }

            //올라가려는 트럭이 다리 하중 무게보다 크지 않으면 다리에 입장 가능
            if(bridgeWeight + weight<=L){
                bridge.offer(trucks.poll());
                bridgeWeight += weight;
                moveCount[idx] = W; //각 트럭은 다리의 길이만큼 이동한다
            }
            time++;
        }
        return time+W;
    }

}
