
import java.io.*;
import java.util.*;

public class YJ_술래잡기_체스 {
    static class Chess implements Comparable<Chess>{
        int x;
        int y;
        int number;
        int direction;

        Chess (int x, int y, int number, int direction){
            this.x = x;
            this.y = y;
            this.number = number;
            this.direction = direction;
        }

        @Override
        public int compareTo(Chess c){
            return this.number - c.number;
        }
    }

    static final int NUM = 4;
    static final Chess EMPTY = new Chess(0,0,0,0);
    static Chess[][] game = new Chess[NUM][NUM];
    static PriorityQueue<Chess> pq = new PriorityQueue<>();
    static int score = 0;
    static Chess tagger;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //초기값 세팅
        for(int i=0; i<NUM; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<NUM; j++){
                int number = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken())-1;
                Chess chess = new Chess(i,j,number,direction);
                game[i][j] = chess;
                pq.offer(chess);
            }
        }
        //술래 위치 세팅
        score = game[0][0].number;
        tagger = game[0][0];
        game[0][0] = null;

        //체스 시작
        boolean flag = true;
        while(flag){
            moveThiefs();   //말 이동
            flag = moveTagger();    //술래 이동
        }
        System.out.println(score);
    }

    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] nx = {-1,-1,0,1,1,1,0,-1};
    static int[] ny = {0,-1,-1,-1,0,1,1,1};
    public static void moveThiefs(){
        while(!pq.isEmpty()){
            Chess thief = pq.poll(); //체스 자리 찾을 때까지 이동
            int x = thief.x;
            int y = thief.y;
            //FIXME 자리찾기
            findSpace(thief);

            //FIXME 자리교체
            Chess temp = game[thief.x][thief.y];
            game[thief.x][thief.y] = thief;
            game[x][y] = temp;
        }
        //pq 채우기
        for(Chess[] rows : game){
            for(Chess chess : rows){
                pq.offer(chess);
            }
        }
    }

    private static void findSpace(Chess chess){
        int tempD = chess.direction;
        for (int i=0; i<NUM*2; i++){
            tempD %= 8;
            int tempX = chess.x + nx[tempD];
            int tempY = chess.y + ny[tempD];
            if(stop(tempX,tempY) || Objects.isNull(game[tempX][tempY])){
                tempD++;
                continue;
            }
            chess.x = tempX;
            chess.y = tempY;
            return;
        }
    }

    //bfs 탐색을 통해 갈 수 있는 곳 중 가장 큰 말 찾기
    public static boolean moveTagger(){
        Deque<Chess> deque = new ArrayDeque<>();
        Chess maxThief = EMPTY;

        boolean[][] visited = new boolean[NUM][NUM];
        deque.offer(tagger);
        visited[tagger.x][tagger.y] = true;

        while(!deque.isEmpty()){
            Chess tagger = deque.poll();
            for (int i=0; i<8; i++){
                int tempX = tagger.x + nx[i];
                int tempY = tagger.y + ny[i];
                //술래말은 도둑말이 없는 곳으로는 이동할 수 없습니다
                if(stop(tempX,tempY) || visited[tagger.x][tagger.y] || game[tempX][tempY].number == 0){
                    continue;
                }

                visited[tagger.x][tagger.y] = true;
                Chess thief = game[tempX][tempY];
                deque.offer(thief);
                if(thief.number > maxThief.number){
                    maxThief = thief;
                }
            }
        }

        if(maxThief.number != 0){
            game[tagger.x][tagger.y] = EMPTY;   //기존에 술래가 있던 위치 빈공간으로 만들기
            //술래 위치 이동
            score += maxThief.number;
            tagger = game[maxThief.x][maxThief.y];
            game[maxThief.x][maxThief.y] = null;
            return true;
        }
        return false;   //도둑말을 잡지 못했다면 게임 종료
    }


    private static boolean stop(int x, int y){
        return x < 0 || x >= NUM || y < 0 || y >= NUM;
    }
}
