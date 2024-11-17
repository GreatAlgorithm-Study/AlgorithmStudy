import java.util.*;
import java.io.*;

//구현 + BFS
public class YJ_회전하는빙하 {
    static int[][] glacier;
    static int[] levels;
    static int size = 0;
    static Deque<int[]> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] data = br.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int q = Integer.parseInt(data[1]);

        size = (int) Math.pow(2,n);
        glacier = new int[size][size];
        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++){
                glacier[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        levels = new int[q];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<q; i++){
            levels[i] = Integer.parseInt(st.nextToken());
        }


        for(int l : levels){
            //얼음 회전하기
            if(l > 0){
                rotate(l);
            }
            //얼음 녹이기
            meltingGlaciers();
        }

        //BFS 탐색: 큰 얼음 군집 크기 구하기
        int total = 0;
        int iceSet = 0;
        boolean[][] visited = new boolean[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(glacier[i][j] < 1 || visited[i][j]){ //★얼음이 0 이거나 방문했을 경우 얼음 집합X
                    continue;
                }
                //★가능한 집합 초기화
                deque.offer(new int[]{i,j});
                visited[i][j] = true;
                total += glacier[i][j];

                int[] results = bfs(visited);
                total += results[0];
                iceSet = Math.max(iceSet,results[1]);
            }
        }
        System.out.printf("%d%n%d",total,iceSet);
    }

    static int[] nx = {0,1,0,-1};
    static int[] ny = {1,0,-1,0};
    static int[] bfs(boolean[][] visited){
        int total = 0;
        int set = 0;

        while(!deque.isEmpty()){
            int[] pos = deque.poll();
            set++;
            for(int i=0; i<4; i++){
                int x = pos[0] + nx[i];
                int y = pos[1] + ny[i];

                if(stop(x,y) || visited[x][y]){
                    continue;
                }

                deque.offer(new int[]{x,y});
                visited[x][y] = true;
                total += glacier[x][y];
            }
        }
        return new int[]{total,set};
    }


    static int[][] temp;
    static void rotate(int level){
        temp = new int[size][size];
        int range = (int) Math.pow(2,level);
        int levelSize = (int) Math.pow(2,level-1);  //2^L−1* 2^L−1 만큼 잘라 4등분

        for(int i=0; i<size; i+=range){
            for(int j=0; j<size; j+=range){
                //★4등분(나눴을 때 회전을 위해 각 왼쪽윗 모서리를 기준점으로 잡음)
                move(i,j ,levelSize,0);                 //왼쪽위 > 오른쪽위으로 이동
                move(i,j+levelSize ,levelSize,1);   //오른쪽위 > 오른쪽아래로 이동
                move(i+levelSize ,j+levelSize ,levelSize,2);    //오른쪽아래 > 왼쪽아래
                move(i+levelSize ,j ,levelSize,3);  //왼쪽아래 > 왼쪽위로 이동
            }
        }
        glacier = temp; //★새로운 배열을 만들어서 복사
    }


    private static void move(int row, int col, int levelSize, int direction){
        //나뉜 구간 안에서 레벨만큼 회전(1레벨:1번, 2레벨:4번, 3레벨:8번)
        for(int x=row; x<row+levelSize; x++){
            for(int y=col; y<col+levelSize; y++){
                int nextX = x + nx[direction] * levelSize;  //레벨의 간격만큼 이동하기 위해 levelSize 곱하기
                int nextY = y + ny[direction] * levelSize;

                temp[nextX][nextY] = glacier[x][y];
                System.out.printf("glacier[%d][%d] = %d > ",x,y,glacier[x][y]);
                System.out.printf("temp[%d][%d] = %d%n",nextX,nextY,temp[nextX][nextY]);
            }
        }
    }

    static void meltingGlaciers(){
        temp = new int[size][size];
        //녹는 빙하 계산하기
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(canMelt(i,j)){
                    temp[i][j]++;
                }
            }
        }
        //빙하 녹이기
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                glacier[i][j] -= temp[i][j];
            }
        }
    }

    //상하좌우 인접한 칸에 얼음이 3개 미만인 경우 얼음 녹음
    static final int LIMIT = 3;
    private static boolean canMelt(int x, int y){
        int count = 4;
        for(int i=0; i<4; i++){
            int currentX = x + nx[i];
            int currentY = y + ny[i];

            if(stop(currentX,currentY)){
                count--;
            }
        }
        return count < LIMIT;
    }

    private static boolean stop(int x, int y){
        return x < 0 || y < 0 || x >= size || y >= size || glacier[x][y] <= 0;
    }

}