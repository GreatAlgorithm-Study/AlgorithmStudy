import java.util.ArrayDeque;
import java.util.Deque;

class JW_43163 {
    public int solution(String begin, String target, String[] words) {
        Deque<String> dq = new ArrayDeque<>();
        dq.offer(begin);
        int cnt = 0;
        boolean[] visited = new boolean[words.length];
        // BFS 시작
        while (!dq.isEmpty()) {
            // 현재 깊이에서 진행할 수 있는 만큼
            int t = dq.size();
            while (t-- > 0) {
                String str = dq.poll();
                // 종료 조건
                if (str.equals(target)) {
                    return cnt;
                }
                for (int i = 0; i < words.length; i++) {
                    if (!visited[i] && isPossible(str, words[i])) {
                        visited[i] = true;
                        dq.offer(words[i]);
                    }
                }
            }
            cnt++;
        }
        return 0;
    }

    // 일치하지 않는 문자가 1개인지 판단
    private boolean isPossible(String A, String B) {
        int cnt = 0;
        for (int i = 0; i < A.length(); i++)
            if (A.charAt(i) != B.charAt(i))
                cnt++;
        return cnt == 1;
    }
}