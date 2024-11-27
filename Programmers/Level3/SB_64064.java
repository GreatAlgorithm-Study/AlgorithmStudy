import java.util.*;

public class SB_64064 {
    static int N;
    static int M;
    static String[] users;
    static String[] banned;
    static HashSet<List<Integer>> ans = new HashSet<>();

    private static boolean check(String user, int idx) {
        if (user.length()!=banned[idx].length()) return false;              // 길이가 다르면 false
        for (int i = 0; i < user.length(); i++) {
            if (banned[idx].charAt(i) == '*') continue;                     // *이면 패쓰
            if (banned[idx].charAt(i) != user.charAt(i)) return false;      // 단어 다르면 false
        }
        return true;
    }

    private static void dfs(int depth, boolean[] visited, List<Integer> tmp) {
        if (depth == M) {                   // 모든 밴 길이를 돌았을때
            if (tmp.size()==M){             // 사용자를 밴 숫자만큼 다 모았을 경우에만
                Collections.sort(tmp);      // 사용자 정렬해서 정답 Set에 넣기
                ans.add(tmp);
                return;
            }
        }

        for (int i = 0; i < N; i++) {       // 모든 사용자 돌아보면서
            if (visited[i]) continue;       // 이미 밴에 담은 사용자면 패쓰
            if (check(users[i], depth)){    // 해당 사용자가 밴 아디이에 해당하는지 체크
                visited[i] = true;
                tmp.add(i);
                dfs(depth + 1, visited, tmp);
                tmp.remove(Integer.valueOf(i));
                visited[i] = false;
            }
        }
    }

    public static int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        M = banned_id.length;
        boolean[] visited = new boolean[N];
        users = user_id;
        banned = banned_id;
        List<Integer> tmp = new ArrayList<>();

        dfs(0, visited, tmp);
        return ans.size();
    }
}
