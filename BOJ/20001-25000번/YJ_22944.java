import java.util.*;
import java.io.*;

//BFS
public class YJ_22944 {
    static class Pos {
        int x;
        int y;
        int hp;
        int distance;
        int umbrella;
        int umbrellaCount;

        public Pos(int x, int y, int hp, int distance, int umbrella, int umbrellaCount) {
            this.x = x;
            this.y = y;
            this.hp = hp;
            this.distance = distance;
            this.umbrella = umbrella;
            this.umbrellaCount = umbrellaCount;
        }
    }

    static final char S = 'S';
    static final char U = 'U';
    static final char E = 'E';

    static int[] current;
    static char[][] space;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        space = new char[n][n];

        for(int i=0; i<n; i++){
            String data = br.readLine();
            for(int j=0; j<n; j++){
                if(S == data.charAt(j)){
                    current = new int[] {i,j};
                }
                space[i][j] = data.charAt(j);
            }
        }
        visited = new boolean[n][n][11];

        System.out.println(bfs(h,d));
    }

    static boolean[][][] visited;
    static int[] nx = {1,0,-1,0};
    static int[] ny = {0,1,0,-1};
    static int bfs(int h, int d) {
        Deque<Pos> deque = new ArrayDeque<>();
        int uc = 0;
        deque.offer(new Pos(current[0],current[1],h,0,0,0));
        visited[current[0]][current[1]][0] = true;

        while(!deque.isEmpty()){
            Pos pos = deque.poll();

            for(int i=0; i<4; i++){
                int x = pos.x + nx[i];
                int y = pos.y + ny[i];
                if(stop(x,y,pos.umbrellaCount)){
                    continue;
                }

                int hp = pos.hp;
                int umbrella = pos.umbrella;
                char current = space[x][y];

                if(current == E){
                    return ++pos.distance;
                }
                //우산획득 시 내구도 감소+우산갯수 카운팅, 아니면 죽음비 맞기
                if(current == U){
                    umbrella = d-1;
                    pos.umbrellaCount = ++uc;
                    space[x][y] = '.';
                }else{
                    if(umbrella > 0){
                        umbrella--;
                    }else{
                        hp--;
                    }
                }

                if(hp == 0){
                    continue;
                }
                visited[x][y][pos.umbrellaCount] = true;
                deque.offer(new Pos(x,y,hp,pos.distance+1,umbrella,pos.umbrellaCount));
            }
        }

        return -1;
    }

    private static boolean stop(int x, int y, int count){
        return x < 0 || y < 0 || x >= space.length || y >= space.length || visited[x][y][count];
    }
}
