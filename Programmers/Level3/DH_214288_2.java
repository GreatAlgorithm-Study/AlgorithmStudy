import java.util.*;

/*
 * 상담원 인원
 */

public class DH_214288_2 {
	
	static int result = Integer.MAX_VALUE;
	
	static int solution(int k, int n, int[][] reqs) {
		
		int[] question = new int[k]; // 각 상담마다 멘토가 몇 명이 배정이 되는지 저장
		func(0, n, k, question, n, reqs);
		
		return result;
	}
	
	// 중복순열로 문제에 대해 멘토 인원 지정하기
	// value값을 통해 중복순열이면서, 각 상담에 배정된 인원의 합계가 n이 될 수 있도록 하기
	static void func(int depth, int n, int k, int[] question, int value, int[][] reqs) {
		if(depth == k) {
			if(value == 0) getWaitingTime(question, reqs);
			return;
		}
		
		for(int i = 1; i < n + 1; i++) {
			question[depth] = i;
			func(depth + 1, n, k, question, value - i, reqs);
		}
	}
	
	static void getWaitingTime(int[] question, int[][] reqs) {
		
		int waitingTime = 0;
		
		// 끝나는 순서를 오름차순으로 저장할 수 있도록 우선순위 큐 사용
		ArrayList<PriorityQueue<Integer>> waitingQueue = new ArrayList<PriorityQueue<Integer>>();
		for(int i = 0; i < question.length; i++) waitingQueue.add(new PriorityQueue<Integer>());

		for(int[] req: reqs) {
			
			int startAt = req[0]; // 시작 시간
			int spendTime = req[1]; // 상담에 걸리는 시간
			int type = req[2] - 1; // 상담 유형 번호
			
			// 상담하고 있는 인원이 꽉 찬 경우
			// 제일 먼저 끝나는 상담이 현재 상담받을 사람의 시작 시간보다 늦게 끝난다면 대기하기
			// 그게 아니라면 대기 시간 없이 바로 상담 받기!→
			if(waitingQueue.get(type).size() == question[type]) {
				int endAt = waitingQueue.get(type).poll();
				
				if(endAt > startAt) {
					waitingTime += (endAt - startAt);
					startAt = endAt;
				}
			}
			
			// 현재 사람이 받는 상담 번호에 대해 언제 끝나는지 저장하기
			waitingQueue.get(type).add(startAt + spendTime);
		}
		
		result = Math.min(result, waitingTime);
	}
}
