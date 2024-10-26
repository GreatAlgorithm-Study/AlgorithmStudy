class Solution {
    static int answer;
    public int solution(String skill, String[] skill_trees) {
        answer = 0;
        
        for(String t: skill_trees) {
            // 포함된 스킬 개수 카운트
            int cnt = containSkill(t, skill);
            // dfs로 가능한지 판단
            dfs(0,0, t, skill, cnt);
        }
        
        return answer;
    }
    public static int containSkill(String tree, String skill) {
        int cnt = 0;
        for(int i=0; i<skill.length(); i++) {
            for(int j=0; j<tree.length(); j++) {
                if(skill.charAt(i) == tree.charAt(j)){
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
    public static void dfs(int tIdx, int sIdx, String tree, String skill, int cnt) {
        // 모든 스킬을 순서대로 탐색했다면 가능
        if(sIdx == cnt){
            answer += 1;
            return;
        }
        // 스킬트리를 모두 탐색했다면 중단
        if(tIdx == tree.length()) return;
        
        // 스킬트리의 현재위치와 스킬의 현재위치의 알파벳이 일치하면 인덱스 1씩 증가
        if(tree.charAt(tIdx) == skill.charAt(sIdx)) {
            dfs(tIdx+1, sIdx+1, tree, skill, cnt);
        } 
        // 일치하지 않는다면 스킬트리 인덱스만 증가
        else {
            dfs(tIdx+1, sIdx, tree, skill, cnt);
        }
    }
}