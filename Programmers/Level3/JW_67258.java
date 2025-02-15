import java.util.HashMap;
import java.util.HashSet;

class JW_67258 {
    public int[] solution(String[] gems) {
        int[] rs = { 0, 100_001 };
        int len = gems.length, cnt = 0;
        int l = 0, r = 0;
        {
            HashSet<String> hs = new HashSet<>();
            for (String gem : gems)
                hs.add(gem);
            cnt = hs.size(); // 총 보석의 수
        }
        HashMap<String, Integer> hm = new HashMap<>();
        // 투 포인터
        while (r < len) {
            String gem = gems[r++];
            hm.put(gem, hm.getOrDefault(gem, 0) + 1);
            while (hm.size() == cnt) {
                // 인덱스 갱신
                if (rs[1] - rs[0] > r - l - 1) {
                    rs[0] = l + 1;
                    rs[1] = r;
                }
                gem = gems[l++];
                hm.put(gem, hm.get(gem) - 1);
                if (hm.get(gem) == 0)
                    hm.remove(gem);
            }
        }
        return rs;
    }
}