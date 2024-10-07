import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] g;
    static List<Integer>[] srr;
    static int[] score = {0, 1, 10, 100, 1000};
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static class Pos implements Comparable<Pos> {
        int x, y, like, empty;
        public Pos(int x, int y, int like, int empty) {
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }
        @Override
        public int compareTo(Pos other) {
            if(this.like == other.like) {
                if(this.empty == other.empty) {
                    if(this.x == other.x) {
                        return this.y - other.y;
                    }
                    return this.x - other.x;
                }
                return other.empty - this.empty;
            }
            return other.like - this.like;
        }
        @Override
        public String toString() {
            return this.x+","+this.y+" like:"+this.like+" empty:"+this.empty;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        g = new int[N+1][N+1];

        srr = new ArrayList[N*N+1];
        for(int s=0; s<N*N+1; s++) {
            srr[s] = new ArrayList<>();
        }

        for(int s = 1; s<N*N+1; s++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for(int i=0; i<4; i++) {
                srr[num].add(Integer.parseInt(st.nextToken()));
            }

            // 빈칸 찾기 & 대입
            findPos(num, srr[num]);
        }

        // print();

        // 점수 계산
        int ans = calScore();

        System.out.println(ans);
    }
    public static void print() {
        for(int i = 0; i<N+1; i++) {
            System.out.println(Arrays.toString(g[i]));
        }
    }
    public static boolean inRange(int x, int y) {
        return x>0 && x<=N && y>0 && y<=N;
    }
    public static void findPos(int num, List<Integer> sList) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(g[i][j] == 0) {
                    // 빈칸의 인접칸 중, 좋아하는 사람 수와 빈칸의 개수 계산
                    int like = calLike(i, j, sList);
                    int empty = calEmpty(i, j);
                    pq.add(new Pos(i, j, like, empty));
                }
            }
        }
        // 가장 우선순위가 높은 칸 출력
        Pos now = pq.poll();    
        // System.out.println(now);

        // 위치 기록
        g[now.x][now.y] = num;
    }
    public static int calLike(int x, int y, List<Integer> sList) {
        int cnt = 0;

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!inRange(nx, ny)) continue;
            if(g[nx][ny] == 0) continue;

            for(int s: sList) {
                if(g[nx][ny] == s) {
                    cnt++;
                    break;
                }
            }
        }

        return cnt;
    }
    public static int calEmpty(int x, int y) {
        int cnt = 0;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!inRange(nx, ny)) continue;
            if(g[nx][ny] != 0) continue;
            
            cnt++;
        }

        return cnt;
    }
    public static int calScore() {
        int res = 0;

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                int num = g[i][j];
                List<Integer> sList = srr[num];
                int likes = calLike(i, j, sList);
                res += score[likes];
            }
        }

        return res;
    }
}