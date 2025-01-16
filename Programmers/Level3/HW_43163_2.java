// 규칙에따라 begin에서 target으로 변환하는 가장 짧은 변환 과정 출력
// 시간 복잡도 : 3 <= 단어의 길이 <=10, 3<= words.length <=50 (중복 단어X) O(V+E)
// 최소 변환 횟수 찾기 : BFS
// 단어 변환 : 간선으로 생각하고, 한글자 차이나는 단어들을 간선으로 연결 후 간선 수 출력
import java.util.*;

class HW_43163_2 {
    public int solution(String begin, String target, String[] words) {
        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.add(begin);
        int depth = 0;

        while(!queue.isEmpty()){
            for(int i=0; i<queue.size(); i++){
                String cur = queue.poll();

                if(cur.equals(target)){
                    return depth;
                }

                for(int j=0; j<words.length; j++){ // 변환 가능한 단어 찾기
                    if(!visited[j] && check(cur, words[j])){
                        visited[j] = true;
                        queue.add(words[j]);
                    }
                }
            }
            depth++;
        }
        return 0; // 변환X -> 0
    }
    private static boolean check(String cur, String word){ // 한글자만 다른지 체크하는 함수
        int cnt = 0; // 다른 글자 수
        for(int i=0; i<cur.length(); i++){
            if(cur.charAt(i) != word.charAt(i)){
                cnt++; // 다른 글자 개수 cnt
            }
            if(cnt > 1){
                return false;
            }
        }
        return true;
    }
}