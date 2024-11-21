public class JW_9466 {

    static int n;       // 전체 학생 수
    static int cnt;     // 사이클이 발생한 학생 수
    static int[] arr;   // 함께하고 싶은 학생을 저장할 배열

    static boolean[] visited;   // 방문 확인 배열
    static boolean[] isCycle;   // 사이클 확인 배열

    public static void main(String[] args) throws Exception {
        int t = read();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            n = read();
            cnt = 0;
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            isCycle = new boolean[n + 1];
            for (int i = 1; i <= n; i++)
                arr[i] = read();
            for (int i = 1; i <= n; i++)
                // 방문하지 않은 학생들에 대해서 탐색
                if (!visited[i])
                    recurse(i);
            sb.append(n - cnt).append("\n");
        }
        System.out.println(sb);
    }

    // 사이클이 발생하는지 재귀로 탐색
    private static void recurse(int cur) {
        // 방문 처리
        visited[cur] = true;
        int next = arr[cur];    // 현재 학생이 함께하고 싶은 학생
        // 다음 학생이 방문 처리가 되어있지 않다면
        if (!visited[next]) {
            // 다음 학생 탐색
            recurse(next);
            
        // 방문한 학생인데 사이클이 아직 발생하지 않은 학생일 경우
        } else if (!isCycle[next]) {
            // 현재 학생이 이루는 모든 사이클의 수를 계산
            cnt++;
            while (next != cur) {
                cnt++;
                next = arr[next];
            }
        }
        // 현재 재귀에 있는 모든 학생 사이클 체크
        // 다른 재귀에서 해당 학생들은 사이클이 발생할 수 없으므로
        isCycle[cur] = true;
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}