import java.util.LinkedList;
import java.util.Queue;

/**
 * 알고리즘: queue<br/>
 * 시간복잡도: 2 <= prices <= 100,000 크기로 O(NlogN) 까지 가능 <br/>
 * 아이디어: <br/>
 * queue 에 모든 주식을 넣고 하나씩 꺼냄 <br/>
 * 다음 주식이 현재 주식 보다 작다면 누적된 값++ , 멈춤 <br/>
 * 다음 주식이 현재 주식 보다 크거나 같다면 누적된 값++, 주식이 떨어질 때 까지 늘려간다 <br/>
 * 어떤 경우에서든 +1 을 하기 때문에 배열의 index를 활용함 > 배열[index]++
 */

public class YJ_주식가격 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        int[] result = solution(prices);
        for(int r: result){
            System.out.print(r+" ");
        }
    }

    static int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        Queue<Integer> queue = new LinkedList<>();
        for(int price : prices){
            queue.offer(price);
        }

        int i=0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int k=i+1; k<len; k++){
                if(current > prices[k]){
                    answer[i]++;
                    break;
                }
                if(current <= prices[k]){
                    answer[i]++;
                }
            }
            i++;
        }

        return answer;
    }
}
