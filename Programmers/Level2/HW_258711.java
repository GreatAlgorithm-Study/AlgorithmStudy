// [생성한 정점 번호, 도넛 그래프 수, 막대 그래프 수, 8자 그래프 수]
// 생성한 정점 번호 : 나가는선 여러개O, 들어오는 선은 X
// 도넛 그래프 : 사이클, In 1, Out 1
// 막대 그래프 : Out 0
// 8자 그래프 : 사이클 2개, In 2, Out 2
import java.util.*;
class HW_258711 {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> inLine = new HashMap<>();
        Map<Integer, Integer> outLine = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();

        for(int[] edge : edges){ // 모든 선을 확인하며 들어오는 선과 나가는선 개수 cnt
            int start = edge[0]; // 춟발점
            int end = edge[1]; // 도착점

            outLine.put(start, outLine.getOrDefault(start, 0)+1); // getOrDefault()로 값이 없으면 0부터 시작 해줌
            inLine.put(end, inLine.getOrDefault(end, 0)+1);

            nodes.add(start); // 노드 저장
            nodes.add(end);
        }
        int makeNode = -1; // 생성한 노드

        for(int n:nodes){
            int in = inLine.getOrDefault(n, 0);
            int out = outLine.getOrDefault(n, 0);

            if(in==0 && out>=2){
                makeNode = n;
                break;
            }
        }
        int donut = 0, stick=0, eight =0;

        for(int n : nodes){
            if(n==makeNode){
                continue;
            }
            int in = inLine.getOrDefault(n, 0);
            int out = outLine.getOrDefault(n, 0);

            if(in==1 && out==1){
                donut++;
            } else if(out==0){
                stick++;
            } else if(in>=2 && out>=2){
                eight++;
            }
        }
        // 도넛 그래프 수 = 생성한 노드의 나가는 간선 수 - (막대 그래프 수 + 8자 그래프 수)
        donut = outLine.get(makeNode) - (stick + eight);

        return new int[]{makeNode, donut, stick, eight};
    }
}
