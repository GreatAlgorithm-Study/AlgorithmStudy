import java.io.*;
import java.util.*;

public class DH_놀이기구_탑승 {
    static LinkedHashMap<Integer, HashSet<Integer>> hashMap;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static void solution() {
        // 학생들 map에 위치시키기
        for(int key: hashMap.keySet()) {
            placeKey(key);
        }

        // 점수 계산
        System.out.println(getScore());
    }

    // 점수계산
    static int getScore() {
        int result = 0;
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {

                int current = map[r][c];
                int likeCnt = 0;

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc)) continue;
                    // 주변에 친한 친구가 있으면 likeCnt 증가
                    if(hashMap.get(current).contains(map[nr][nc])) likeCnt++;
                }

                // 점수 배분: 10^(좋아하는 친구의 수 - 1)
                result += Math.pow(10, likeCnt - 1);
            }
        }

        return result;
    }
    static void placeKey(int key) {
        // setR, setC: key를 위치할 r, c 정보
        // 초기 빈 자리 수는 -1 로 해서 key의 위치가 잘못 정해지는 것 방지
        int setR = 0, setC = 0, likeCnt = 0, emptyCnt = -1;

        // map 순차 탐색하면서 학생이 앉을 수 있는 곳 찾기
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if(map[r][c] != 0) continue;

                int emptyTmpCnt = 0;
                int likeTmpCnt = 0;

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc)) continue;
                    int next = map[nr][nc];
                    if(next == 0) emptyTmpCnt++; // map이 비어있다면 emptyTmpCnt++
                    if(hashMap.get(key).contains(next)) { // 친한 친구에 속한다면 likeTmpCnt++;
                        likeTmpCnt++;
                    }
                }

                // r, c위치에서 좋아하는 친구 수가 이제까지 좋아하는 친구 수 보다 많으면
                // setR, setC 위치 update
                // likeCnt, emptyCnt 값도 update
                if(likeCnt < likeTmpCnt) {
                    setR = r;
                    setC = c;
                    likeCnt = likeTmpCnt;
                    emptyCnt = emptyTmpCnt;
                } else if(likeCnt == likeTmpCnt) {
                    // 좋아하는 친구 수가 이제까지 좋아하는 친구 수와 같다면
                    // 비어있는자리 확인하기
                    // 현재 위치에서 주변 비어있는 자리 개수가, 직전 자리에서 비어있는 개수보다 많다면
                    // setR, setC, emptyCnt 값 update
                    if(emptyCnt < emptyTmpCnt) {
                        setR = r;
                        setC = c;
                        likeCnt = likeTmpCnt;
                        emptyCnt = emptyTmpCnt;
                    }
                }
            }
        }

        map[setR][setC] = key;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }
    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        hashMap = new LinkedHashMap<>();

        for(int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int n0 = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());
            int n4 = Integer.parseInt(st.nextToken());

            hashMap.put(n0, new HashSet<>());
            hashMap.get(n0).add(n1);
            hashMap.get(n0).add(n2);
            hashMap.get(n0).add(n3);
            hashMap.get(n0).add(n4);
        }
    }
}
