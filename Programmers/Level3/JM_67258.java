import java.util.*;

// 모든 보석을 하나 이상 포함하는 가장 짧은 구간
class JM_67824 {
	public static int[] findShortest(String[] gems, int gemCount) {
		int[] answer = {-1, -1};
		Map<String, Integer> map = new HashMap<>();

		int l = 0, r = 0;
		while(l < gems.length && r < gems.length) {
			map.put(gems[r], map.getOrDefault(gems[r], 0) + 1);

			while(map.size() == gemCount && l <= r) {
				if(answer[0] == -1 || (answer[1] - answer[0]) > (r - l)) {
					answer[0] = l + 1;
					answer[1] = r + 1;
				}
				if(map.get(gems[l]) == 1) map.remove(gems[l]);
				else map.put(gems[l], map.get(gems[l]) - 1);
				l++;
			}
			r++;
		}

		return answer;
	}

	public static int[] solution(String[] gems) {
		Set<String> set = new HashSet<String>(Arrays.asList(gems));
		int gemCount = set.size();
		return findShortest(gems, gemCount);
	}
}
