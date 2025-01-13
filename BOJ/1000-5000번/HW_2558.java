import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// O(N logN) 이하로 풀기
// 최소 비용을 구하는 프로그램
public class HW_2558 {
    static class Meat implements Comparable<Meat>{
        int weight;
        int price;
        public Meat(int weight, int price){
            this.weight = weight;
            this.price = price;
        }
        @Override
        public int compareTo(Meat other){
            if(this.price==other.price){
                return other.weight - this.weight; // 가격이 같을 경우 무게 기준 내림차순*
                // 덩어리보다 싼 가격들은 덤으로 주기 때문에, 비싼 고기를 사면 최소 비용으로 구할 수 있음
            }
            return this.price - other.price; // 가격 기준 오름차순 정렬
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        Meat[] meats = new Meat[N];
        long sum = 0; // 전체 고기 무게의 합
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            meats[i] = new Meat(weight, price);
            sum += weight;
        }
        if(sum<M){ // 전체 고기를 다 합쳐도 M보다 작을 경우
            System.out.println(-1); // -1
            return;
        }
        Arrays.sort(meats); // 가격 기준 오름차순 정렬

        long curSum = 0; // 지금까지 구매한 덩어리 무게 누적
        long curCost = 0; // 지금까지 구매한 비용
        int curPrice = 0; // 현재 기준 가격(비교하는)
        long ans = Long.MAX_VALUE;
        for (Meat meat : meats) { // 동일가격이면 비용 추가, 가격 달라지면 새로 시작
            curSum += meat.weight; // 무게 누적
            if(curPrice != meat.price){ // 더 비싼 가격을 만날 경우 (다른 가격 -> 새로운 가격)
                curPrice = meat.price; // 새로 비용 계산 시작 (덤x),
                curCost = meat.price; // 같은 가격대 고기는 사려면 비용을 내야함
            } else{ // (같은 가격 -> 추가 비용 필요)
                curCost += meat.price; // 동일한 가격 고기일 경우 동일한 돈 내기
            }
            if(curSum >= M){
                ans = Math.min(ans, curCost);
            }
        }
        // 3) 결과 출력
        if(ans == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }
}