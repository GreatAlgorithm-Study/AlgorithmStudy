import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class YJ_색깔_폭탄 {
    static class Bundle implements Comparable<Bundle>{
        List<int[]> bombs;
        int red;
        int rows;
        int columns;

        Bundle(List<int[]> bombs, int red, int rows, int columns){
            this.bombs = bombs;
            this.red = red;
            this.rows = rows;
            this.columns = columns;
        }

        @Override
        public int compareTo(Bundle b){
            if(this.bombs.size() == b.bombs.size()){
                if(this.red == b.red){
                    if(this.rows == b.rows){
                        //3. 가장 열이 작은 폭탄 묶음
                        return this.columns - b.columns;
                    }
                    //2. 행이 가장 큰 폭탄 묶음 (행이 빨간색이 아니면서 가장 큰 칸)
                    return b.rows - this.rows;
                }
                //1. 빨간색 폭탄이 가장 적은 묶음
                return this.red - b.red;
            }
            //사이즈가 가장 큰 순서
            return b.bombs.size() - this.bombs.size();
        }
    }

    static int n;
    static int m;
    static int[][] bombs;
    static List<Bundle> bundles = new ArrayList<>(); //묶음 폭탄 저장소
    static final int BLACK = -1;
    static final int RED = 0;
    static final int NULL = -2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        n = Integer.parseInt(data[0]);
        m = Integer.parseInt(data[1]);
        bombs = new int[n][n];

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                bombs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;
        do{
            find();
            if(bundles.isEmpty()){
                break;
            }

            Bundle bombBundle = bundles.get(0);
            if(bundles.size() > 1){
                bombBundle = filter();
            }

            score += remove(bombBundle);
            move();

            bundles.clear();
        }while(true);

        System.out.println(score);
    }

    //가장 큰 폭탄 묶음 찾기 (연결되어있어야함)
    static void find(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!stop(i,j)){
                    bfs(i,j);
                }
            }
        }
    }

    static int[] nx = {1,0,-1,0};
    static int[] ny = {0,1,0,-1};
    static boolean[][] visited;
    private static void bfs(int x, int y){
        Deque<int[]> deque = new ArrayDeque<>();
        visited = new boolean[n][n];

        deque.offer(new int[]{x,y});
        visited[x][y] = true;
        int color = bombs[x][y];

        //폭탄 묶음 초기화
        List<int[]> bombList = new ArrayList<>();
        bombList.add(new int[]{x,y});
        int red = color == RED? 1 : 0;
        int rows = x;
        int columns = y;

        while(!deque.isEmpty()) {
            int[] current = deque.poll();
            for (int d = 0; d < 4; d++) {
                int dx = current[0] + nx[d];
                int dy = current[1] + ny[d];

                if (stop(dx, dy) || visited[dx][dy]) {
                    continue;
                }

                int bomb = bombs[dx][dy];
                //빨간색 폭탄 또는 한가지색 만 올 수 있음
                if(bomb == color || bomb == RED){
                    if(bomb == RED){
                        red++;
                    }
                    //빨간색이 아니면서 가장 행이 큰 폭탄 묶음
                    if(dx > rows && bomb != RED){
                        rows = dx;
                    }
                    //가장 작은 열
                    if(dy < columns){
                        columns = dy;
                    }

                    deque.offer(new int[]{dx,dy});
                    bombList.add(new int[]{dx,dy});
                    visited[dx][dy] = true;
                }
            }
        }

        //폭탄 묶음이란 2개 이상의 폭탄으로 구성
        if(bombList.size() > 1){
            bundles.add(new Bundle(bombList,red,rows,columns));
        }
    }

    private static boolean stop(int x, int y){
        return x < 0 || y < 0 || x >= n || y >= n || bombs[x][y] == BLACK || bombs[x][y] == NULL;
    }

    //동일한 폭탄묶음 필터링
    static Bundle filter(){
        Collections.sort(bundles);
        return bundles.get(0);
    }

    //폭탄 묶음 제거 > 폭탄 갯수 점수 구하기(C*C)
    static int remove (Bundle bombBundle){
        //제거하려면 위치를 알아야함
        List<int[]> bombList = bombBundle.bombs;
        for(int[] pos : bombList){
            bombs[pos[0]][pos[1]] = NULL;
        }

        int count = bombList.size();
        return count*count;
    }

    //폭탄들 움직이기 (중력 + 반시계 방향으로 90 도 회전)
    static void move(){
        moveDown();
        rotate();
        moveDown();
    }

    //중력 작용
    private static void moveDown(){
        for(int i=n-2; i>=0; i--){
            for(int j=0; j<n; j++){
                if(bombs[i][j] == BLACK || bombs[i][j] == NULL){
                    continue;
                }
                //가장 끝 빈공간까지 인덱스를 이동
                int index = i+1;
                while(index < n && bombs[index][j] == NULL){
                    index++;
                }
                //해당 인덱스 위치로 이동이 가능할 경우 while 내에서 +1 되었기 때문에 i+2 가 된 상태
                if(index-1 != i){
                    bombs[index-1][j] = bombs[i][j];
                    bombs[i][j] = NULL;
                }
            }
        }
    }

    //반시계 방향 90도 회전
    private static void rotate(){
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                temp[i][j] = bombs[j][n-1-i];
            }
        }
        bombs = temp;
    }
}
