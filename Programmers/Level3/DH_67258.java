/*
 * 보석 쇼핑
 * HashMap & 투포인터
 * 비트 연산을 했었음 ㅠㅠ → gems 크기 100,000 이하이기 때문에 안됨 
 */

import java.util.*;

public class DH_67258 {
	
	static HashMap<String, Integer> gToIdx = new HashMap<String, Integer>();
	static HashSet<Integer> set = new HashSet<Integer>();
	
	static int[] solution(String[] gems) {
		initHashMap(gems);
		
		// 보석들의 종류
		int gCnt = gToIdx.size();
		return twoPointer(0, 0, gems, gCnt);
	}
	

	static int[] twoPointer(int s, int e, String[] gems, int type) {
		int[] result = new int[2];
		int[] cnt = new int[type];

		int idx = gToIdx.get(gems[0]);
		
		int length = Integer.MAX_VALUE;
		
		cnt[idx] += 1; // 개수 늘려주고
		set.add(idx);
		
		while(s <= e) {

			// 다 고른 상태라면
			if(set.size() == type) {
				
				if(e - s < length) {
					length = e - s;
					result[0] = s + 1;
					result[1] = e + 1;
				} 
				
				// 만약 맨 왼쪽 보석 개수가 한 개 보다 많으면
				idx = gToIdx.get(gems[s]);
				cnt[idx] -= 1;
				
				// 0개라면 set에서 없애주기
				if(cnt[idx] == 0) set.remove(idx);
				s += 1;

			} else {
				// 다 고르지 않은 상태라면
				e += 1;
				if(e >= gems.length) break;

				idx = gToIdx.get(gems[e]);
				cnt[idx] += 1;
				
				set.add(idx);
			}
		}
		
		return result;
	}
	
	static void initHashMap(String[] gems) {
		// 보석들의 idx를 만들어줌
		for(String g: gems) {
			if(!gToIdx.containsKey(g)) gToIdx.put(g, gToIdx.keySet().size());
		}
	}
	public static void main(String[] args) throws Exception {
		String[] gems = {"A","A","A"};
		
		System.out.println(Arrays.toString(solution(gems)));
	}
}
