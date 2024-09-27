// 시간 복잡도 : 단순 구현
// m년 이후 남아있는 리브로수의 총 높이의 합
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    static boolean[][] visited;
    static int sum; // m년 이후 남아있는 리브로수의 총 높이의 합
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 격자의 크기 N
        M = Integer.parseInt(st.nextToken()); // 리브로수를 키우는 총 년 수 m
        map = new int[N][N];

        // 원소 입력 받기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        // 초기 영양제 위치 (N-1, 0), (N-1, 1), (N-2, 0), (N-2, 1)
        visited[N-1][0] = visited[N-1][1] = visited[N-2][0] = visited[N-2][1] = true;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 이동 방향
            int p = Integer.parseInt(st.nextToken()); // 이동 칸 수

            simulation(d-1, p%N); // 인덱스, 격좌 범위 처리
        }

        sum =0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    public static void simulation(int direction, int pace){
        boolean[][] check = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j]){
                    int nx = (i + dx[direction] * pace + N) % N;
                    int ny = (j + dy[direction] * pace + N) % N;
                    check[nx][ny] = true;
                    map[nx][ny]++;
                }
            }
        }
        visited = check;

        // 대각선 체크
        for(int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if(visited[i][j]){
                    for(int d=1; d<8; d+=2){ // 대각선 방향 체크(1, 3, 5, 7)
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx<0 || ny <0 || nx >=N || ny >= N){
                            continue;
                        }
                        if(map[nx][ny]>0){ // 대각선에 있으면 ++
                            map[i][j]++;
                        }
                    }
                }
            }
        }

        // 높이가 2이상인 리브로수는 잘라냄
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j]){
                    visited[i][j] = false; // 기존 영양제 없앰
                }
                else if(map[i][j] >=2){  // 높이가 2이상인 리브로수는 잘라냄
                    map[i][j] -= 2;
                    visited[i][j] = true; // 새로운 영양제 배치
                }
            }
        }
    }
}