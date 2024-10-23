import java.util.LinkedList;
import java.util.Queue;

// 시간 복잡도 : O(n * p * m)
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i=0; i<skill_trees.length; i++){
            Queue<Character> queue = new LinkedList<>();
            for(int j=0; j<skill.length(); j++){ // 선행스킬 큐에 넣기
                queue.add(skill.charAt(j)); // skill 한 문자씩 queue에 넣음
            }

            boolean check = true;

            for(int j=0; j<skill_trees[i].length(); j++){ // 4
                char s = skill_trees[i].charAt(j);

                if(!queue.contains(s)){ // 선행 스킬이 아니라면 다음 스킬 확인
                    continue;
                } else {
                    if(s ==queue.peek()){ // 순서가 맞으면 큐에서 제거
                        queue.poll();
                    } else { // 순서가 틀리면 중단
                        check = false;
                        break;
                    }
                }
            }
            if(check){
                answer++;
            }
        }
        return answer;
    }
}