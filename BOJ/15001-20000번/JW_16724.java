import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_16724 {

    static int n, m, cnt = 0;
    static int[] board;
    static boolean[] visited, isCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 행, 열을 인덱스화
        board = new int[n * m];
        visited = new boolean[n * m];
        isCycle = new boolean[n * m];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++)
                // 다음 인덱스 계산을 편하게 하기 위해 변화량으로 변환
                switch (line[j]) {
                case 'U':
                    board[i * m + j] = -m;
                    break;
                case 'D':
                    board[i * m + j] = m;
                    break;
                case 'L':
                    board[i * m + j] = -1;
                    break;
                case 'R':
                    board[i * m + j] = +1;
                    break;
                }
        }
        // 사이클 검출
        for (int i = 0; i < n * m; i++)
            if (!visited[i]) {
                dfs(i);
            }
        System.out.println(cnt);
    }

    private static void dfs(int cur) {
        // 방문하지 않은 인덱스라면
        if (!visited[cur]) {
            visited[cur] = true;
            dfs(cur + board[cur];); // 다음 재귀 호출
        // 방문했다면 사이클 발생
        } else if (!isCycle[cur])
            cnt++;
        isCycle[cur] = true;
    }
}