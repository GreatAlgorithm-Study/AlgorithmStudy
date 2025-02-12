import java.util.*;
class JY_67258 {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        // 총 보석 종류구하기
        Set<String> gSet = new HashSet<>();
        for(String g: gems) {
            gSet.add(g);
        }
        int total = gSet.size();
        
        
        int minR = Integer.MAX_VALUE;
        // 투포인터 활용
        int s = 0;
        int e = s;
        Map<String, Integer> gMap = new HashMap<>();
        while(e < gems.length) {
            // System.out.println(">> now: "+gMap+", s:"+s+",e:"+e);
            // 보석 추가
            gMap.put(gems[e], gMap.getOrDefault(gems[e], 0)+1);
            
            // 현재 구간(gMap의 보석들)이 모든 보석을 포함함
            // 총 보석의 개수가 total보다 작을 떄까지 s증가 
            while(gMap.keySet().size() == total) {
                // 더 짧은 구간
                if(minR > (e-s)) {
                    minR = (e-s);
                    answer[0] = s+1;
                    answer[1] = e+1;
                }   
                // s지점의 보석 감소
                gMap.put(gems[s], gMap.get(gems[s])-1);
                // 감소 후, gems[s]의 개수가 0이된다면 gMap에서 삭제
                if(gMap.get(gems[s]) == 0) gMap.remove(gems[s]);
                s++;
            } 
            
            e++;
            
        }
        
        return answer;
    }
}