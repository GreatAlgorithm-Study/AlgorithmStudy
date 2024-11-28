import java.util.HashSet;

public class JW_64064 {

    public static void main(String[] args) {
        String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
        String[] banned_id = { "fr*d*", "abc1**" };
        solution(user_id, banned_id);
    }

    static int n;
    static int len;
    // 중복 제거를 위해 Set을 사용
    static HashSet<Integer> hs = new HashSet<>();

    public static int solution(String[] user_id, String[] banned_id) {
        n = user_id.length;     // 전체 유저의 수
        len = banned_id.length; // 밴 당한 유저의 수
        for (int i = 0; i < len; i++)
            // 정규표현식으로 사용하기 위해 `*`을 `.`로 변환
            banned_id[i] = banned_id[i].replace("*", ".");
        // 재귀로 가능한 모든 경우 찾기
        recursive(user_id, banned_id, 0, 0);
        return hs.size();
    }

    // 재귀 함수
    private static void recursive(String[] user_id, String[] banned_id, int depth, int visited) {
        // 최대 깊이 = 밴 당한 유저의 수
        if (depth == len) {
            hs.add(visited);
            return;
        }
        for (int i = 0; i < n; i++) {
            // 방문하지 않은 유저들 중에서 정규표현식과 일치하는 경우
            if ((visited & (1 << i)) == 0 && user_id[i].matches(banned_id[depth])) {
                // 방문 처리 후 다음 재귀 진행
                recursive(user_id, banned_id, depth + 1, visited | (1 << i));
            }
        }
    }
}