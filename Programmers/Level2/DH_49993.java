import java.util.*;

/*
스킬트리
 */

public class DH_49993 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
    
        // HashMap을 통해 각 자리가 몇 번째 순서인지 저장
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < skill.length(); i++) hashMap.put(skill.charAt(i), i);

        for(String s: skill_trees) {

            int idx = 0;
            boolean flag = true;

            // 0번째 인덱스부터 확인
            // 해당 인덱스가 마지막까지 확인한 인덱스의 자릿수 크다면
            // false 반환
            for(int i = 0; i < s.length(); i++) {
                if(hashMap.containsKey(s.charAt(i))) {
                    if(hashMap.get(s.charAt(i)) > idx) {
                        flag = false;
                        break;
                    }
                }

                if(s.charAt(i) == skill.charAt(idx)) idx++;
                if(idx == skill.length()) break;
            }

            if(flag) answer++;
        }

        return answer;
    }
}
