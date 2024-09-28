import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 : O(V+E)
// 각 상대편 말을 잡기 위한 나이트의 최소 이동 수
// BFS
public class HW_18404 {
    static int N, M, X, Y;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 각 상대편 말을 잡기 위한 최소 이동 수
        N = Integer.parseInt(st.nextToken()); // N * N
        M = Integer.parseInt(st.nextToken()); // 상대편 말의 개수

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken()); // 나이트의 위치 X
        Y = Integer.parseInt(st.nextToken()); // 나이트의 위치 Y

        visited = new int[N + 1][N + 1];

        for(int i=1; i<N+1; i++){
            Arrays.fill(visited[i], -1); // 카운트 및 방문여부 체크를 위한 초기화
        }
        Bfs();
        // 상대편 말의 위치 (A, B)
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(visited[A][B]).append(' ');
        }
        System.out.println(sb.toString());
    }
    private static void Bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        queue.add(Y);
        visited[X][Y] = 0;

        while(!queue.isEmpty()){
            int X = queue.poll();
            int Y = queue.poll();
            for(int i=0; i<8; i++){
                int nX = X + dx[i];
                int nY = Y + dy[i];
                if(nX < 1 || nY < 1 || nX > N || nY >N){
                    continue;
                }
                if(visited[nX][nY] != -1) {
                    continue;
                }
                visited[nX][nY] = visited[X][Y] + 1;
                queue.add(nX);
                queue.add(nY);
            }
        }
    }
}

