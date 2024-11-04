class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        String regex = "[^" + skill + "]"; // 스킬 순서 외의 문자를 지우기 위한 정규표현식
        for (String skill_tree : skill_trees)
            // 문자를 지웠을 때, 남은 문자가 스킬 순서와 일치할 때
            if (skill.indexOf(skill_tree.replaceAll(regex, "")) == 0)
                answer++;
        return answer;
    }
}