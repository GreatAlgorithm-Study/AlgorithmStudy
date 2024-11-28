import java.util.*;

class HW_64064 {
    static Set<Set<String>> resultSet; // 제재 아이디 목록을 저장할 집합
    static List<List<String>> matchedList; // banned_id와 매칭되는 user_id 목록
    static boolean[] visited; // 방문 체크 배열

    public int solution(String[] user_id, String[] banned_id) {
        resultSet = new HashSet<>();
        matchedList = new ArrayList<>();

        // banned_id와 매칭되는 user_id 찾기
        for (int i = 0; i < banned_id.length; i++) {
            List<String> matched = new ArrayList<>();
            for (int j = 0; j < user_id.length; j++) {
                if (banned_id[i].length() == user_id[j].length()) {
                    boolean match = true; // 일치할 경우

                    for (int k = 0; k < banned_id[i].length(); k++) {
                        if (banned_id[i].charAt(k) != '*' && banned_id[i].charAt(k) != user_id[j].charAt(k)) { // * 제외 문자 정확히 일치
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        matched.add(user_id[j]); // 일치할 경우 리스트에 추가
                    }
                }
            }
            matchedList.add(matched);
        }

        visited = new boolean[user_id.length];
        backtrack(0, new HashSet<>());

        return resultSet.size();
    }

    // 백트래킹
    private void backtrack(int depth, Set<String> selected) {
        if (depth == matchedList.size()) {
            resultSet.add(new HashSet<>(selected)); // 결과 집합에 추가
            return;
        }

        List<String> currentMatched = matchedList.get(depth);
        for (int i = 0; i < currentMatched.size(); i++) {
            String user = currentMatched.get(i);
            if (!selected.contains(user)) { // 중복될 경우 방지
                selected.add(user); // 선택
                backtrack(depth + 1, selected); // 재귀
                selected.remove(user); // 선택 취소
            }
        }
    }
}
