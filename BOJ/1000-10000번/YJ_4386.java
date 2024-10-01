import java.util.*;

/**
 * 알고리즘: 최소 신장 트리(MST) > union-find
 * 시간복잡도:1 ≤ n ≤ 100 로 고려하지 않아도 됨
 * 간선 정렬: O(eloge)
 * MST 경로압축: O(a(N))
 * O(eloge) + O(a(N)) = O(eloge)
 * 아이디어:
 * > 서로 다른 두 별을 일직선으로 이은 형태, 별자리를 만드는 최소 비용
 * 순환하지 않는 연결된 그래프 형태로 최소 값을 구하는 최소 신장 트리 문제
 * {x,y} 좌표 하나를 노드로 생각 (행 단위로 노드)
 * 두 별 사이의 거리 구하는 방법?
 * 두 점 x좌표와 y좌표 각각의 차이 > x좌표,y좌표 차이 제곱 > 두 제곱의 합 제곱급
 */
public class YJ_4386 {

    static class Star implements Comparable<Star> {
        double first;
        double second;
        double distance;

        Star(double first, double second, double distance) {
            this.first = first;
            this.second = second;
            this.distance = distance;
        }

        @Override
        public int compareTo(Star o){
            if(this.distance > o.distance){
                return 1;
            }else if(this.distance < o.distance){
                return -1;
            }
            return 0;
        }
    }

    static PriorityQueue<Star> queue = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] parent = new int[n+1];
        double[][] starPos = new double[n+1][2];
        for(int i=1; i<n+1; i++){
            starPos[i][0] = sc.nextDouble();
            starPos[i][1] = sc.nextDouble();
            parent[i] = i;
        }

        //두 별 사이 거리 구하기
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                double x = starPos[i][0];
                double y = starPos[i][1];
                double distance = getStarDistance(x,starPos[j][0],y,starPos[j][1]);
                //★행을 하나의 노드로 저장
                queue.offer(new Star(i,j,distance));
            }
        }

        double totalDistance = 0;
        int count = n-1;    //최소신장트리의 노선 수는 N-1
        while(!queue.isEmpty() && count>0){
            Star star = queue.poll();
            int first = (int) star.first;
            int second = (int) star.second;
            if(union(parent,first,second)){
                totalDistance += star.distance;
                count--;
            }
        }

        double result = (double) Math.round(totalDistance* 100)/100;
        System.out.println(result);
    }

    static double getStarDistance(double x1, double x2, double y1, double y2){
        double x = x1 - x2;
        double y = y1 - y2;
        double sum = Math.pow(x,2) + Math.pow(y,2);
        return Math.sqrt(sum);
    }

    //두개의 원소가 포함된 집합를 하나의 집합으로 합치기
    static boolean union(int[] parent ,int num1, int num2){
        num1 = find(parent,num1);
        num2 = find(parent,num2);

        //이미 같은 노드에 있기 때문에 합집합 연산 X
        if(num1==num2){
            return false;
        }
        //큰 쪽으로 노드 합치기
        if(num1<num2){
            parent[num2] = num1;
        }else{
            parent[num1] = num2;
        }
        return true;
    }

    //특정한 원소가 속한 집합이 누구의 집합인지 찾기
    static int find(int[] parent ,int num){
        if(parent[num] == num){ //루트노트 발견
            return num;
        }
        return parent[num] = find(parent,parent[num]);
        //루트노드를 만날때 까지 stack 쌓임 > 루트노드 발견 후 루트의 값으로 쌓인 stack return
    }
}
