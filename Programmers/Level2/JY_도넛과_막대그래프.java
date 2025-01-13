import java.util.*;

public class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, int[]> eMap = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            // a가 없다면 value 저장
            eMap.putIfAbsent(a, new int[2]);
            eMap.putIfAbsent(b, new int[2]);

            eMap.get(a)[0]++; // a는 n번 노드에서 나가는 간선
            eMap.get(b)[1]++; // b는 n번 노드로 들어오는 간선
        }
        return check(eMap);
    }


    private int[] check(Map<Integer, int[]> eMap) {
        int[] answer = new int[4];
        for (Map.Entry<Integer, int[]> entry : eMap.entrySet()) {
            int key = entry.getKey();
            int[] counts = entry.getValue();

            // 생성된 정점의 번호 확인
            if (counts[0] >= 2 && counts[1] == 0) {
                answer[0] = key;
            }
            // 막대 모양 그래프의 수 확인
            else if (counts[0] == 0 && counts[1] > 0) {
                answer[2]++;
            }
            // 8자 모양 그래프의 수 확인
            else if (counts[0] >= 2 && counts[1] >= 2) {
                answer[3]++;
            }
        }
        // 도넛 모양 그래프의 수 확인
        answer[1] = (eMap.get(answer[0])[0] - answer[2] - answer[3]);

        return answer;
    }
}

