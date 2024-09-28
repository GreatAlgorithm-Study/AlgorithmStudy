// 시간 제한 : 2초,
// 버그를 고치기 위해 필요한 메모리 유실선 개수의 최솟값 출력
import java.io.*;
import java.util.*;
public class HW_디버깅 {
    private static int n, m, h, answer;
    private static int[][] map;
    private static boolean finish = false; // 최소한의 가로선을 찾았는지 여부
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 고객 수
        m = Integer.parseInt(st.nextToken()); // 취약 지점
        h = Integer.parseInt(st.nextToken()); // 메모리 유실선

        map = new int[h+1][n+1]; // h x n 행렬
        int a, b;

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine()); // 독립적인 좌표 정보
            a = Integer.parseInt(st.nextToken()); // 취약 지점
            b = Integer.parseInt(st.nextToken()); // 메모리 유실이 일어난 지점
            map[a][b] = 1; // 사다리 오른쪽 이동 가능
            map[a][b+1] = 2; // 사다리 왼쪽 이동 가능 (오른쪽으로 이동한 상태이므로 2로 표현)
        }

        for(int i=0; i<=3; i++){ // 필요한 선의 개수가 3보다 큰 값이거나
            answer = i;
            dfs(1, 0);
            if (finish) break;
        }
        System.out.println((finish) ? answer : -1); // 버그를 고치는 것이 불가능하다면 -1을 출력
    }

    private static void dfs(int a, int count){
        if(finish) return;
        if(answer ==count){
            if(check()) finish = true;
            return;
        }
        for(int i=a; i<h+1; i++){
            for(int j=1; j<n; j++){
                if(map[i][j]==0 && map[i][j+1]==0){ // 가로선이 추가되지 않았으면
                    map[i][j] =1; //  오른쪽 이동
                    map[i][j+1] = 2; // 왼쪽 이동
                    dfs(i, count+1); // 가로선 하나 추가 -> 재귀 호출
                    map[i][j] = map[i][j+1] =0; // 백트래킹 : 가로선 제거
                }
            }
        }
    }

    private static boolean check(){ // 제대로 이동할 수 있는지 확인
        for(int i=1; i<=n; i++){ // 모든 고객에 대해 검사
            int a=1, b=i;
            while (a <= h) { // 취약 지점의 끝까지 내려가면서 이동
                if (map[a][b] == 1) {  // 오른쪽으로 이동하는 경우
                    b++;
                } else if (map[a][b] == 2) {  // 왼쪽으로 이동하는 경우
                    b--;
                }
                a++;  // 아래로 한 칸 이동
            }
            if(b!=i) return false; // 출발위치 != 도착위치
        }
        return true; // 출발위치 = 도착 위치
    }
}

