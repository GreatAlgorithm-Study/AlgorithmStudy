import java.util.*;

class Solution {
    static Set<Set<String>> resultSet; // 제재 아이디 목록을 저장할 집합
    static List<List<String>> matchedList; // banned_id와 매칭되는 user_id 목록

    public int solution(String[] user_id, String[] banned_id) {
        resultSet = new HashSet<>();
        matchedList = new ArrayList<>();

        for (int i = 0; i < banned_id.length; i++) { // banned_id와 매칭되는 user_id 찾기
            List<String> matched = new ArrayList<>();
            String regex = banned_id[i].replace("*", ".");
            for (int j = 0; j < user_id.length; j++) {
                if (user_id[j].matches(regex)) { // 정규식으로 매칭 확인
                    matched.add(user_id[j]);
                }
            }
            matchedList.add(matched);
        }
        backtrack(0, new HashSet<>());

        return resultSet.size();
    }

    private void backtrack(int depth, Set<String> selected) {
        if (depth == matchedList.size()) { // 모든 banned_id를 처리하면
            resultSet.add(new HashSet<>(selected)); // 결과 집합에 추가
            return;
        }

        List<String> currentMatched = matchedList.get(depth);
        for (int i = 0; i < currentMatched.size(); i++) {
            String user = currentMatched.get(i);
            if (!selected.contains(user)) { // 중복 방지
                selected.add(user); // 선택
                backtrack(depth + 1, selected); // 재귀
                selected.remove(user); // 선택 취소
            }
        }
    }
}