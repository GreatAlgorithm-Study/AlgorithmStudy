class Solution {
    static int answer;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        
        visited = new boolean[words.length];
        dfs(0, 0, begin, target, words);
        
        if(answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        
        return answer;
    }
    public static void dfs(int depth, int step, String now, String target, String[] words) {
        if(now.equals(target)) {
            answer = Math.min(answer, step);
            return;
        }
        for(int i=0; i<words.length; i++) {
            if(visited[i]) continue;
            if(!isOk(now, words[i])) continue;
            
            visited[i] = true;
            dfs(depth+1, step+1, words[i], target, words);
            visited[i] = false;
        }
    }
    public static boolean isOk(String now, String next) {
        int cnt = 0;
        for(int i=0; i<now.length(); i++) {
            if(now.charAt(i) != next.charAt(i)) cnt++;
        }
        
        if(cnt == 1) return true;
        return false;
    }    
}
