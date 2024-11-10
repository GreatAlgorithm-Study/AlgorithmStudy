import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 시간 복잡도 : 2<=N<=16 -> 16! 완전 탐색 불가 -> DP + 비트마스크 -> 16* 2^16 => O(N * 2 ^N) 가능
// 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하는 프로그램
// 리스트 데이터를 변수 1개에 저장 -> bit(이진수 표현)
public class HW_2098 {
    static int N; // 도시의 개수
    static int[][] W; // 도시 간의 이동 비용
    static int[][] d; // 최소 비용 저장
    static final int INF = Integer.MAX_VALUE >> 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = new int[16][16]; // 비용 저장 배열
        d = new int[16][1<<16]; // 최소 비용
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(tsp(0, 1));
    }

    public static int tsp(int c, int v) {
        if(v==(1<<N)-1){ // 모든 노드를 방문할 경우
            return W[c][0] == 0 ? INF : W[c][0]; // 시작 도시(0)로 돌아감 (돌아갈 수 없으면 INF 반환)
        }
        if(d[c][v] !=0){ // 이미 방문한 노드라면
            return d[c][v]; // 바로 반환
        }
        int min_val = INF;
        for(int i=0; i<N; i++){
            if((v&(1<<i))==0 && W[c][i] !=0){ // 방문한 적 없고 갈 수 있는 노드라면
                min_val = Math.min(min_val, tsp(i, (v | (1 << i))) + W[c][i]); // 현재도시(c) -> 다음 도시(i) 최소 비용 계산
            }
        }
        d[c][v] = min_val;
        return d[c][v];
    }
}