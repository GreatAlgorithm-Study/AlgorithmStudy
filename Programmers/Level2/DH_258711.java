import java.util.*;

/*
 * 도넛과 막대 그래프
 */

class DH_258711 {
	static HashMap<Integer, int[]> hashMap = new HashMap<>();

	static int[] solution(int[][] edges) {
		
		int[] answer = new int[4];
		for(int[] edge: edges) {
			
			int a = edge[0]; // [0]: 나가는거
			int b = edge[1]; // [1]: 들어오는거
		
			hashMap.put(a, hashMap.getOrDefault(a, new int[2]));
			hashMap.put(b, hashMap.getOrDefault(b, new int[2]));
			
			hashMap.get(a)[0] += 1;
			hashMap.get(b)[1] += 1;
		}
		
		for(int node: hashMap.keySet()) {
			int[] count = hashMap.get(node);
			
			// 나가는 간선: 2개 이상, 들어오는 간선: 0개 -> 생성된 노드
			if(count[0] >= 2 && count[1] == 0) answer[0] = node;

			// 나가는 간선: 0개, 들어오는 간산: 0개 이상 -> 직선 그래프
			else if(count[0] == 0 && count[1] > 0) answer[2] += 1;
			
			// 나가는 간선: 2개 이상, 들어오는 간선: 2개 이상 -> 8자 그래프
			else if(count[0] >= 2 && count[1] >= 2) answer[3] += 1;
		}

		// 생성된 노드에서 나가는 간선의 개수: 총 그래프의 개수
		answer[1] = hashMap.get(answer[0])[0] - (answer[2] + answer[3]);
		return answer;
	}
	
	public static void main(String[] args) {
//		int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
		int[][] edges = {{4, 10}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, 
						 {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
		
		System.out.println(Arrays.toString(solution(edges)));
	}
}
