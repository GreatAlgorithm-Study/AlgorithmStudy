import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 주어지는 비행 스케줄은 항상 연결 그래프를 이룸 -> MST(최소 스패닝트리)
// 모든 국가를 연결하는데 필요한 최선 간선의 수 찾기
// 연결된 그래프에서  MST 간선수 : N-1개
public class HW_9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 국가의 수
            int M = Integer.parseInt(st.nextToken()); // 비행기의 수
            for(int j=0; j<M; j++){
                br.readLine(); // 각 비행 경로 입력만 받음(a, b)
            }
            System.out.println(N-1);
        }
    }
}