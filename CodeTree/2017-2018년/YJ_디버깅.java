import java.io.*;
import java.util.*;

/**
 * 알고리즘: dfs
 * 시간복잡도: 1 ≤ h ≤ 30 크기로 bfs 사용가능
 * 아이디어:
 * https://github.com/GreatAlgorithm-Study/AlgorithmStudy/issues/23
 */
public class YJ_디버깅 {
    static final int MAX_N = 10;
    static final int MAX_H = 30;

    static boolean[][] sadari = new boolean[MAX_H+1][MAX_N+1];
    static List<Connection> possibility = new ArrayList<>();
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //고객의 수 n, 메모리 유실 선의 개수 m, 취약 지점의 개수 h
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);
        int h = Integer.parseInt(first[2]);

        //사다리 취약점 표시
        for(int i=1; i<=m; i++){
            String[] next = br.readLine().split(" ");
            int number = Integer.parseInt(next[0]);
            int memory = Integer.parseInt(next[1]);
            sadari[number][memory] = true;
        }

        //추가 가능한 유실선 생성
        for(int i=1; i<=h; i++){
            for(int j=1; j<n; j++){
                if(!sadari[i][j]){
                    possibility.add(new Connection(i,j));
                }
            }
        }

        findMinConnection(0,0,h,n);
        //연결가능한 유실선이 없을 경우
        if(MIN == Integer.MAX_VALUE){
            MIN = -1;
        }
        System.out.print(MIN);
    }

    static class Connection {
        int a;
        int b;

        public Connection(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    //current(=i):연결가능한 유실선만큼 순회 ,count:추가한 유실선 갯수
    static void findMinConnection(int current, int count, int h, int n){
        //추가한 유실선의 수가 지금까지 구한 답보다 좋아질 수 없다면?? ★더 좋아진다는 기준이 뭐지?
        if(count >= MIN){
            return;
        }

        //최초 또는 재귀호출로 연결된 유실선이 제대로 사다리를 타는지 확인
        if(isConnected(h,n)){
            MIN = Math.min(MIN,count);
        }

        //문제 요구사항인 유실선의 갯수 최대 3
        //현재 순회하는 인덱스가 연결 가능한 유실선을 모두 순회하면 더 이상 유실선이 없음
        if(count == 3 || current == possibility.size()){
            return;
        }

        //연결 가능한 모든 유실선을 재귀호출 > ★이걸 왜 하지?
        findMinConnection(current+1, count, h,n);
        //current == possibility.size()로 인해 return 되어 재귀호출된 스택을 하나씩 반환

        //연결 가능한 유실선 가져오기(마지막부터 가져옴)
        int a = possibility.get(current).a;
        int b = possibility.get(current).b;
        //유실선 연결처리
        sadari[a][b] = true;
        //연결한 유실선이 조건을 만족하는지 재귀호출
        findMinConnection(current+1, count+1, h,n);
        sadari[a][b] = false;
    }

    //번호별 사다리 줄
    static int[] nums = new int[MAX_N+1];

    //모든 사다리 줄을 순회하며 i번-i고객 연결되어있는지 확인
    private static boolean isConnected (int h, int n){
        //유실선이 연결되어 있으면 버그이기 때문에 불가능
        for(int a=1; a<=h; a++){
            for(int b=2; b<n; b++){ //b가 2부터 시작하는 이유? b=1로 하면 n+1 불필요한 범위까지 확인하기 때문
                if(sadari[a][b] && sadari[a][b-1]){
                    return false;
                }
            }
        }

        //사다리 번호 지정
        for(int i=1; i<= n; i++){
            nums[i] = i;
        }

        //사다리 타는 방법: 연결지점 끼리 번호를 서로 교환
        for(int a=1; a<=h; a++) {
            for (int b = 1; b < n; b++) {
                if(sadari[a][b]){
                    int exchange = nums[b];
                    nums[b] = nums[b+1];
                    nums[b+1] = exchange;
                }
            }
        }

        //사다리타기 종료 검증: i번-i고객 일치
        for(int i=1; i<=n; i++){
            if(nums[i] != i){
                return false;
            }
        }
        return true;
    }
}
