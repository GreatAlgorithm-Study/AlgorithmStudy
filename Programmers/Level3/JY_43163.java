import java.util.*;
class Solution {
    
    static class State{
        String word;
        int cnt;
        public State(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        // words에 target이 없으면 0
        if(!isInclude(target, words)) return answer;
        
        // BFS로 words에 있는 단어중 target 찾기
        int N = words.length;
        boolean[] visited = new boolean[N];
        Queue<State> q = new LinkedList<>();
        q.add(new State(begin, 0));
        while(!q.isEmpty()){
            State now = q.poll();
            if(now.word.equals(target)){
                answer = now.cnt;
                break;
            }

            for(int i=0; i<N; i++){
                if(!visited[i]){
                    int wc = 0;
                    for(int j=0; j<words[i].length(); j++){
                        if(words[i].charAt(j) == now.word.charAt(j)) wc++;
                    }
                    if(now.word.length() - wc == 1){
                        visited[i] = true;
                        q.add(new State(words[i], now.cnt+1));
                    }
                }
            }
        }

        return answer;
    }
    public static boolean isInclude(String target, String[] words){
        for(String word: words){
            if(target.equals(word)){
                return true;
            }
        }
        return false;
    }
}