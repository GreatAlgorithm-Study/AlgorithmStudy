import java.io.*;
import java.util.*;

class DH_예술성 {
    static class Node {
        int num, cnt;
        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    // map: 주어진 배열 정보
    // v: idx를 저장하는 배열
    // dis: idx영역끼리 얼마나 맞닿아 있는지 저장하는 배열
    static int result, N, map[][], v[][], dis[][];
    static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    // 특정 영역의 정보 저장
    static HashMap<Integer, Node> hashMap;
    static Queue<int []> q;
    static Queue<int [][]> tmpQueue;

    static void solution() {
        int turn = 0;
        while(turn < 4) {
            result += getScore();
            rotateCross();
            rotateElement();
            turn++;
        }

        System.out.println(result);
    }

    static void rotateElement() { // 십자 모양을 제외한 4개의 정사각형을 회전하는 함수
        int tmp[][] = new int[N / 2][N / 2]; // tmp라는 배열에 정사각형 원본 저장

        // 시작점: (0, 0), (0, N / 2 + 1), (N / 2 + 1, 0), (N / 2 + 1, N / 2 + 1)
        for(int r = 0; r < N; r += (N / 2 + 1)) {
            for(int c = 0; c < N; c += (N / 2 + 1)) {
                for(int rr = r; rr < r + (N / 2); rr++) {
                    System.arraycopy(map[rr], c, tmp[rr - r], 0, N / 2);
                }

                for(int rr = 0; rr < N / 2; rr++) {
                    for(int cc = 0; cc < N / 2; cc++) {
                        // 배열 돌리기
                        map[r + rr][c + cc] = tmp[N / 2 - 1 - cc][rr];
                    }
                }
            }
        }
    }
    
    // 십자 회전
    static void rotateCross() {
        int tmp[]; // 원본 저장할 tmp 배열
        tmp = Arrays.copyOf(map[N / 2], N);
        for(int r = 0; r < N; r++) map[N / 2][r] = map[r][N / 2];
        for(int r = 0; r < N; r++) map[r][N / 2] = tmp[N - 1 - r];
    }

    // 조화로움의 합 구하기
    static int getBeautyScore() {
        int result = 0;
        for(int idx1 = 1; idx1 < dis.length; idx1++) {
            for(int idx2 = idx1 + 1; idx2 < dis.length; idx2++) {
                int tmp;
                tmp = (hashMap.get(idx1).cnt + hashMap.get(idx2).cnt);
                tmp *= hashMap.get(idx1).num;
                tmp *= hashMap.get(idx2).num;
                tmp *= dis[idx1][idx2];

                result += tmp;
            }
        }

        return result;
    }
    static int getScore() {
        bfs();
        return getBeautyScore();
    }

    static void bfs() {
        hashMap.clear();
        v = new int[N][N];

        int idx = 1;

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                int cnt = 0;

                if(v[r][c] != 0) continue;
                q.add(new int[] {r, c});
                cnt++;
                v[r][c] = idx;
                while(!q.isEmpty()) {
                    int current[] = q.poll();

                    for(int d = 0; d < 4; d++) {
                        int nr = current[0] + dr[d];
                        int nc = current[1] + dc[d];

                        if(!check(nr, nc)) continue;
                        if(v[nr][nc] != 0) {
                            if(v[nr][nc] != v[r][c]) tmpQueue.add(new int[][] {{r, c}, {nr, nc}});
                            continue;
                        } else if(map[nr][nc] != map[r][c]) continue;
                        q.add(new int[] {nr, nc});

                        v[nr][nc] = idx;
                        cnt++;
                    }
                }

                hashMap.put(idx, new Node(map[r][c], cnt));

                idx++;
            }
        }

        dis = new int[idx][idx];

        // dis: 맞닿아 있는 변의 수
        while(!tmpQueue.isEmpty()) {
            int current[][] = tmpQueue.poll();

            int idx1 = v[current[0][0]][current[0][1]];
            int idx2 = v[current[1][0]][current[1][1]];
            dis[idx1][idx2]++;
            dis[idx2][idx1]++;
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        hashMap = new HashMap<>();

        q = new LinkedList<>();
        tmpQueue = new LinkedList<>();

        for(int r = 0; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < map[0].length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }
}
