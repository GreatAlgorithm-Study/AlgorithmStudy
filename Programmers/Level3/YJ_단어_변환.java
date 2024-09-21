import java.util.LinkedList;
import java.util.Queue;

/**
 * 알고리즘: BFS
 * 시간복잡도: 3 <= words <= 50 크기로 BFS 가능
 * 아이디어:
 * - bfs 를 활용 해 최소값(최단거리)를 어떻게 찾을 수 있는지?
 * 이동할 수 있는 단어의 갯수만큼 너비 탐색 진행 > 너비 탐색이 끝났다면 현재 단어에서 인접 단어들 까지의 거리가 각각 1 이라는 뜻
 * 한번의 너비 탐색이 끝나면 해당 레벨의 최단거리 탐색 종료 > 다음 레벨 탐색
 * - visited 를 어떤 목적으로 활용하는지?
 * begin에서 target으로 변환하는 단게의 최소 횟수를 전체적으로 탐색하므로 이미 방문했을 경우 방문 하지 않는다
 */
public class YJ_단어_변환 {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        //"hit" -> "hot" -> "dot" -> "dog" -> "cog" 4단계 변환
        System.out.println(solution(begin, target, words));
    }

    public static int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);

        int distance = 0;
        while(!queue.isEmpty()){
            for(int i=0; i<queue.size(); i++){
                String current = queue.poll();
                //단어를 찾았을 경우 지금까지 계산된 최단거리 반환
                if(current.equals(target)){
                    return distance;
                }

                for(int j=0; j<words.length; j++){
                    //인접한 단어 탐색
                    int different = 0;
                    String w = words[j];
                    for(int k=0; k<w.length(); k++){
                        if(current.charAt(k) != w.charAt(k)){
                            different++;
                        }
                    }

                    //방문하지 않은 인접한 단어일 경우
                    if(!visited[j] && different == 1){
                        visited[j] = true;  //인접한 단어 방문처리
                        queue.offer(w);
                    }
                }
            }
            distance++;
        }
        return 0;
    }
}
