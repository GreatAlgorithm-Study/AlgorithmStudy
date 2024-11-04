import java.util.*;
import java.io.*;

class HW_42626 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int answer = 0;

        for(int i=0; i<scoville.length; i++){
            queue.add(scoville[i]);
        }

        while(queue.peek() < K){
            if(queue.size() ==1 ) return -1;
            queue.add(queue.poll() + queue.poll() *2);
            answer++;
        }

        return answer;
    }
}