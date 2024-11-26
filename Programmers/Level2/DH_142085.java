import java.io.*;
import java.util.*;

/*
 * 디펜스 게임
 */

public class DH_142085 {
    public static int solution(int n, int k, int[] enemy) {
    	int answer = 0;
    	
    	PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
    	
    	int sum = 0;
    	
    	// 큐에 하나씩 적들을 담으면서, n보다 적들의 합이 더 커진다면
    	// 큐에서 가장 많은 적들을 꺼내서 무적권을 사용하기
    	for(int i = 0; i < enemy.length; i++) {
    		sum += enemy[i];
    		q.add(enemy[i]);
    		
    		if(n < sum && k > 0) {
    			int diff = q.poll();
    			sum -= diff;
    			k -= 1;
    		}
    		
    		if(n < sum) break;
    		answer = i + 1;
    	}
    	return answer;
    }

    public static void main(String[] args) {
		int n = 2, k = 4;
		int[] enemy = {3, 3, 3, 3};
		
		System.out.println(solution(n, k, enemy));
	}
}
