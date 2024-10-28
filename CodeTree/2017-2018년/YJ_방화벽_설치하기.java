import java.util.*;
import java.io.*;

public class YJ_방화벽_설치하기 {
    static class Pos {
        int x;
        int y;
        Pos (int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    final static int EMPTY = 0;
    final static int WALL = 1;
    final static int FIRE = 2;
    final static int MAX_WALL = 3;

    static int N;
    static int M;
    static int[][] board;

    static List<Pos> emptySpots = new ArrayList<>();
    static List<Integer> emptySpotIdx = new ArrayList<>();
    static boolean[][] visited;
    static Deque<Pos> fireSpots = new ArrayDeque<>();
    static List<Pos> initFire = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j] == EMPTY){
                    emptySpots.add(new Pos(i,j));
                }else if(board[i][j] == FIRE){
                    initFire.add(new Pos(i,j));
                    fireSpots.offer(new Pos(i,j));
                    visited[i][j] = true;
                }
            }
        }

        if(emptySpots.size() < MAX_WALL){
            System.out.println(result);
        }
        execution(0,0);
        System.out.println(result);
    }


    public static void execution(int index, int install){
        if(install == MAX_WALL){
            for(int idx : emptySpotIdx){
                Pos pos = emptySpots.get(idx);
                board[pos.x][pos.y] = WALL;
            }
            //2. 불번지게 하기 > BFS
            spreadFire();
            //3. 빈칸 체크, 빈곳이 가장 많이 남은 곳 저장
            checkEmptySpots();
            //4. 방화벽 없애기
            init();
            return;
        }

        //빈칸과 index가 동일하다면 더이상 탐색x (빈칸갯수가 10일 경우 마지막 백트래킹은 789, index는 10)
        if(emptySpots.size() == index){
            return;
        }

        //1. 임의로 방화벽 설치 > 백트래킹
        emptySpotIdx.add(index);
        execution(index+1,install+1);
        emptySpotIdx.remove(emptySpotIdx.size()-1); //이전상태로 되돌리기

        execution(index+1,install);
    }

    final static int[] nx = {1,0,-1,0};
    final static int[] ny = {0,1,0,-1};
    static void spreadFire(){
        while(!fireSpots.isEmpty()){
            Pos current = fireSpots.poll();
            for(int i=0; i<4; i++){
                int x = current.x + nx[i];
                int y = current.y + ny[i];

                if(stop(x,y)){
                    continue;
                }

                fireSpots.offer(new Pos(x,y));
                visited[x][y] = true;
            }
        }
    }

    private static boolean stop(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= M || visited[x][y] || board[x][y] == 1;
    }

    static void checkEmptySpots(){
        int EmptyCount = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j] && board[i][j] == 0){
                    EmptyCount++;
                }
            }
        }

        result = Math.max(result,EmptyCount);
    }

    static void init(){
        for(int idx : emptySpotIdx){
            Pos pos = emptySpots.get(idx);
            board[pos.x][pos.y] = EMPTY;
        }

        visited = new boolean[N][M];
        for(Pos fire : initFire){
            fireSpots.offer(new Pos(fire.x,fire.y));
            visited[fire.x][fire.y] = true;
        }
    }
}
