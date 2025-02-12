import java.util.*;

class SB_67258 {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        HashMap<String, Integer> map = new HashMap<>();
        int N = set.size();
        int mn = Integer.MAX_VALUE;
        int[] ans = new int[2];

        int start = 0;
        for (int end = 0; end < gems.length; end++) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);     // 하나씩 보석 먹어나가기
            while (map.size()==N) {         // 먹은 보석의 개수가 전체 보석일때 동안
                if (end-start < mn) {       // 현재 길이가 짧다면 답 업데이트
                    mn = end-start;
                    ans[0] = start;
                    ans[1] = end;
                }
                map.put(gems[start], map.getOrDefault(gems[start], 0) - 1);
                if (map.get(gems[start])==0) map.remove(gems[start]);       // 보석뺐을때 0 되면 그 보석 못먹은거니까 빼기
                start++;
            }
        }
        ans[0]++;
        ans[1]++;
        return ans;
    }
}