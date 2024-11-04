// 최소 단어 변환 과정
// 단계별로 진행하여 최단 경로 찾기 -> BFS
import java.util.*;

class HW_43163 {
    public int solution(String begin, String target, String[] words) {
        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.add(begin);
        int answer = 0;

        while(!queue.isEmpty()){
            answer++;
            int size = queue.size();
            for(int i=0; i<size; i++){
                String current = queue.poll();
                for(int j=0; j<words.length; j++){
                    if(!visited[j] && convert(current, words[j])){
                        if(words[j].equals(target)){
                            return answer;
                        }
                        visited[j] = true;
                        queue.add(words[j]);
                    }
                }
            }
        }
        return 0; // 변환 할 수 없을 경우 0 출력
    }
    public boolean convert(String word1, String word2){ // 최소 변환 과정 : 한글자만 다를 경우 변환
        int dif = 0;
        for(int i=0; i<word1.length(); i++){
            if(word1.charAt(i) != word2.charAt(i)){
                dif++;
            }
            if(dif >1) return false;
        }
        return dif == 1;
    }

}