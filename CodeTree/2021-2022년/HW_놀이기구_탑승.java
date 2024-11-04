// 시간 복잡도 : N<=25, 주어진 조건 내 구현 가능

import java.io.*;
import java.util.*;

// 놀이기구 탑승
// 주어진 순서대로 학생을 좋아하는 학생 옆에 앉히기
public class HW_놀이기구_탑승 {
    static int n; // 격자 모양
    static int[][] map;
    static ArrayList<Integer>[] likestudents; // 학생별 좋아하는 학생 : 4명 (크기 고정, 중복X)
    static int[] dy = {-1, 1, 0, 0}; // 탐색 방향 : 상하좌우 (맨 처음 값 십자 가운데에)
    static int[] dx = {0, 0, -1, 1};
    static int[] score = {0, 1, 10, 100, 1000};

    static class Pos implements Comparable<Pos>{ // 자리배치의 우선순위를 설정하기 위해 사용됨
        int y, x, like, empty;
        public Pos(int y, int x, int like, int empty){
            this.y = y;
            this.x = x;
            this.like = like;
            this.empty = empty;
        }
        @Override
        public int compareTo(Pos other){
            if(this.like == other.like){
                if(this.empty == other.empty){
                    if(this.y == other.y){
                        return this.x - other.x; // x(열) 번호 기준 정렬
                    }
                    return this.y - other.y; // 빈 칸 수도 같은 경우, 행 번호가 작은지 여부.
                }
                return other.empty - this.empty; // 좋아하는 학생이 같은 경우, 인접한 빈 칸의 수가 많은지 여부.
            }
            return other.like - this.like; // 좋아하는 학생이 인접한 칸의 수가 많은지 여부.
        }
        @Override
        public String toString(){
            return this.y +"," + this.x + "like:" + this.like + "empty:" + this.empty;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        likestudents = new ArrayList[n * n + 1]; // 학생 번호 1부터 시작

        for (int i = 1; i <= n * n; i++) {
            likestudents[i] = new ArrayList<>();
        }

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentNumber = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                likestudents[studentNumber].add(Integer.parseInt(st.nextToken())); // 각 학생이 좋아하는 학생 번호
            }
            simulation(studentNumber); // 빈칸 찾기 및 배치
        }

        int totalScore = calScore(); // 최종 점수 계산
        System.out.println(totalScore);
    }

    static void simulation(int studentNumber) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0) {
                    int like = calLike(i, j, likestudents[studentNumber]); // 좋아하는 학생 수
                    int empty = calEmpty(i, j); // 빈 칸 수
                    pq.add(new Pos(i, j, like, empty)); // 우선순위 큐에 추가
                }
            }
        }
        Pos now = pq.poll(); // 우선순위가 가장 높은 칸 선택
        map[now.y][now.x] = studentNumber;
    }

    public static int calScore() {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int num = map[i][j];
                ArrayList<Integer> arraylist = likestudents[num];
                int likes = calLike(i, j, arraylist); // 인접한 좋아하는 학생 수
                res += score[likes]; // 점수 합산
            }
        }
        return res;
    }

    public static int calLike(int y, int x, ArrayList<Integer> arraylist) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!inRange(ny, nx)) continue; // 범위를 벗어나면 패스
            if (map[ny][nx] == 0) continue;

            for (int s : arraylist) {
                if (map[ny][nx] == s) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    public static int calEmpty(int y, int x) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!inRange(ny, nx)) continue;
            if (map[ny][nx] != 0) continue;

            cnt++;
        }
        return cnt;
    }

    public static boolean inRange(int y, int x) {
        return y > 0 && y <= n && x > 0 && x <= n;
    }
}