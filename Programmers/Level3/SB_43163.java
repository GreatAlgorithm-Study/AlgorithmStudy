import java.util.Arrays;

class Solution {
    static int ans = Integer.MAX_VALUE;

    private static boolean canChange(String cur, String nxt) {  // 한글자만 다르기
        int dif = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i)!=nxt.charAt(i)) dif++;
            if (dif >1) return false;
        }
        return dif==1;
    }
    private static void dfs(String cur, String target, String[] words, boolean[] visited, int cnt) {
        if (cur.equals(target)) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canChange(cur, words[i])){
                visited[i] = true;
                dfs(words[i], target, words, visited, cnt+1);
                visited[i] = false;
            }
        }
    }
    public static int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) return 0;

        boolean[] visited = new boolean[words.length];
        dfs(begin, target, words, visited, 0);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}