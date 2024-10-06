import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 알고리즘: 최소신장트리(MST) > 크루스칼 알고리즘
 * 시간복잡도: M은 1이상 1,000,000이하이므로 O(NLogN)
 * 아이디어:
 * > 최소 신장 트리 조건을 충족하는 문제
 * - 같은 마을안에서는 집 사이에 경로가 존재하지만, 길을 없앨 수 있다.
 * - 순환 싸이클이 아니더라도 집끼리 연결만 되어 있으면 됨
 * - 연결된 경로의 사이는 최소값으로만 연결
 * 추가 요구사항
 * - 분리된 두 마을 사이에 있는 길들은 없애도 된다
 */
public class YJ_1647 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String[] first = sc.readLine().split("\\s");
        int N = Integer.parseInt(first[0]);
        int M = Integer.parseInt(first[1]);
        int[][] hometown = new int[M][3];
        for(int i=0; i<M; i++){
            String[] line = sc.readLine().split("\\s");
            int[] set = new int[line.length];
            for(int j=0; j<line.length; j++){
                set[j] = Integer.parseInt(line[j]);
            }
            hometown[i]= set;
        }

        System.out.println(getMinMaintenance(N, hometown));
    }

    static int getMinMaintenance(int N,int[][] hometown){
        int cost = 0;
        int[] parent = new int[N+1];
        //자기 자신을 부모로 가지도록 초기값 설정
        for(int i=1; i<N+1; i++){
            parent[i] = i;
        }
        //유지비 오름차순 정렬 > 유지비가 적은것부터 계산
        Arrays.sort(hometown, Comparator.comparingInt(o -> o[2]));  //O(NlogN)

        int maxCost = 0;
        for(int i=0; i<hometown.length; i++){   //O(M)
            if(find(parent, hometown[i][0]) != find(parent, hometown[i][1])){
                //최소 유지비 누적
                cost += hometown[i][2];
                maxCost= Math.max(maxCost,hometown[i][2]);
                //집합의 부모가 같도록 병합
                union(parent,hometown[i][0],hometown[i][1]);
            }
        }
        return cost-maxCost;
    }

    //집합의 부모 찾기
    private static int find(int[] parent, int i){
        if(parent[i] == i){
            return i;
        }
        return find(parent, parent[i]);
    }

    //집합의 더 작은 부모로 병합하기
    private static void union(int[] parent, int a, int b){
        int parentA = find(parent,a);
        int parentB = find(parent,b);
        //더 작은 부모의 값을 다른 배열의 부모값으로 변경해서 합치기(배열의 값이 같을 경우 같은 부모)
        if(parentA > parentB){
            parent[parentA] = parentB;
        }else{
            parent[parentB] = parentA;
        }
    }

}
