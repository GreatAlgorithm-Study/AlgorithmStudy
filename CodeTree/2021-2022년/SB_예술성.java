import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_예술성 {
    static int N;
    static int[][] board;
    static int[][] visited;
    static HashMap<List<Integer>, Integer> combiInfo;
    static List<Group> groups;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int splitGroup(int x, int y, int g_idx) {
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(x, y, g_idx));
        visited[x][y] = g_idx;

        int area = 1;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0<=nx && nx<N && 0<=ny && ny<N)) continue;
                if (visited[nx][ny] == 0 && board[nx][ny] == board[cur.x][cur.y]) {     // 아직 방문하지 않았고, 같은 그룹일때(숫자 같음)
                    que.offer(new Node(nx, ny, cur.idx));
                    visited[nx][ny] = cur.idx;
                    area++;
                } else {                                                                // 맞닿은 변 수 업데이트
                    if (board[nx][ny] != board[cur.x][cur.y] && visited[nx][ny] > 0) {  // 다른 그룹과 경계이고, 해당 그룹의 idx가 정해져 있을 경우
                        int g1 = visited[nx][ny];
                        int g2 = cur.idx;
//                        List<Integer> combi = List.of(Math.min(g1, g2), Math.max(g1, g2));
                        List<Integer> combi = new ArrayList<>();
                        combi.add(Math.min(g1, g2));
                        combi.add(Math.max(g1, g2));
                        combiInfo.put(combi, combiInfo.getOrDefault(combi, 0) + 1);
                    }
                }
            }
        }
        return area;
    }

    private static int calHarmoni() {
        int val = 0;
        for (Map.Entry<List<Integer>, Integer> entry : combiInfo.entrySet()) {
            Integer boundary = entry.getValue();
            if (boundary==0) continue;        // 서로 맞닿아있지않으면 패쓰

            List<Integer> combiGroup = entry.getKey();
            int g1 = combiGroup.get(0);
            int g2 = combiGroup.get(1);
            val += (groups.get(g1).cnt + groups.get(g2).cnt) * groups.get(g1).num * groups.get(g2).num * boundary;
        }
        return val;
    }

    private static void rotatePlus() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, tmp[i], 0, N);
        }

        // 세로막대기 -> 가로막대기 (0,2 -> 1,3)
        for (int i = 0; i < N; i++) {
            int j = N/2;
            board[j][i] = tmp[i][j];
        }

        // 가로 막대기 -> 세로 막대기 (1,3 -> 0,2)
        for (int j = 0; j < N; j++) {
            int i = N/2;
            board[N - j - 1][i] = tmp[i][j];
        }
    }

//    private static void rotateSquare() {
//        int[][] tmp = new int[N][N];
//        int M = N / 2;
//
//        // 왼쪽 위
//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < M; j++) {
//                tmp[i][j] = board[M - 1 - j][i];
//            }
//        }
//
//        // 오른쪽 위
//        for (int i = 0; i < M; i++) {
//            for (int j = M + 1; j < N; j++) {
//                tmp[i][j] = board[M - 1 - (j - (M + 1))][i + M + 1];
//            }
//        }
//
//        // 왼쪽 아래
//        for (int i = M + 1; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                tmp[i][j] = board[N - 1 - (i - (M + 1))][j];
//            }
//        }
//
//        // 오른쪽 아래
//        for (int i = M + 1; i < N; i++) {
//            for (int j = M + 1; j < N; j++) {
//                tmp[i][j] = board[N - 1 - (i - (M + 1))][j];
//            }
//        }
//
//        // 원본 배열에 결과 복사
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                if (i == M || j == M) continue;
//                board[i][j] = tmp[i][j];
//            }
//        }
//    }

    public static void rotateSquare() {
        int[][] tmp = new int[N][N];
        int M = N/2;
        for(int i=0;i<N/2;i++) {
            for(int j=0;j<N/2;j++) {
                tmp[i][j] = board[N-1-j-M-1][i];
            }
        }

        for(int i=0;i<N/2;i++) {
            for(int j=N/2+1;j<N;j++) {
                tmp[i][j] = board[N-1-j][i+M+1];
            }
        }

        for(int i=N/2+1;i<N;i++) {
            for(int j=0;j<N/2;j++) {
                tmp[i][j] = board[N-1-j][i-M-1];
            }
        }

        for(int i=N/2+1;i<N;i++) {
            for(int j=N/2+1;j<N;j++) {
                tmp[i][j] = board[N-1-j+M+1][i];
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(i == N/2 || j == N/2) continue;
                board[i][j] = tmp[i][j];
            }
        }

    }


    // 그룹 나누기 && 그룹간 맞닿은 변 수 구하기
    private static void makeGroup() {
        int groupIdx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]==0) {
                    int area = splitGroup(i, j, groupIdx);
                    groups.add(new Group(board[i][j], area));
                    groupIdx++;
                }
            }
        }
    }

    private static void play() {
        // 변수 초기화
        visited = new int[N][N];
        combiInfo = new HashMap<>();
        groups = new ArrayList<>();
        groups.add(new Group(0, 0));    // 인덱스 0 처리

        // 보드 회전
        rotatePlus();
        rotateSquare();

        // 회전 후 그룹 나누기 && 그룹간 맞닿은 변 수 구하기
        makeGroup();

        // 회전 후 조화로움 계산
        ans += calHarmoni();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        visited = new int[N][N];
        groups = new ArrayList<>();
        groups.add(new Group(0, 0));    // 인덱스 0 처리
        combiInfo = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 그룹 나누기 && 그룹간 맞닿은 변 수 구하기
        makeGroup();
        // 초기 조화로움 계산
        ans += calHarmoni();

        int time = 0;
        while (time++ < 3) {
            play();
        }
        System.out.println(ans);
    }

    static class Group{
        int num;
        int cnt;

        Group(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static class Node{
        int x;
        int y;
        int idx;

        Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }
}
