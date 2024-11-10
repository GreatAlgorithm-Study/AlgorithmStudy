import java.io.*;
import java.util.*;

public class YJ_전투_로봇 {
    static class Monster implements Comparable<Monster>{
        int x;
        int y;
        int level;

        Monster(int x, int y, int level){
            this.x = x;
            this.y = y;
            this.level = level;
        }

        @Override
        public int compareTo(Monster o){
            return this.level - o.level;
        }

        boolean requestUpdate(Monster prev){
            if(Objects.isNull(prev)){
                return true;
            }
            //죽일 수 있는 몬스터 중 현재 지점에서 갈 수 없다면 distance 는 0
            if(distance[this.x][this.y] == 0 || distance[prev.x][prev.y] == 0){
                return false;
            }
            //가장 가까운 몬스터: 거리 > 행 > 열
            if(distance[this.x][this.y] != distance[prev.x][prev.y]){
                return distance[this.x][this.y] < distance[prev.x][prev.y];
            }
            if(this.x != prev.x){
                return this.x < prev.x;
            }
            return this.y < prev.y;
        }
    }

    static class Pos {
        int x;
        int y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static final int ROBOT = 9;

    static int n = 0;
    static int[][] game;
    static boolean[][] visited;
    static int[][] distance;
    static List<Monster> monsterList = new ArrayList<>();
    static Deque<Pos> deque = new ArrayDeque<>();
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        game = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                game[i][j] = Integer.parseInt(st.nextToken());

                //로봇 위치 저장
                if(game[i][j] == ROBOT){
                    deque.offer(new Pos(i,j));
                    game[i][j] = 0;
                }
                //몬스터 위치 저장
                else if(game[i][j] != 0 && game[i][j] != ROBOT){
                    monsterList.add(new Monster(i,j,game[i][j]));
                }
            }
        }

        Collections.sort(monsterList);
        simulate();
        System.out.println(time);
    }

    static void simulate (){
        distance = new int[n][n];
        int kill = 0;
        int level = 2;

        while(!monsterList.isEmpty()){
            visited = new boolean[n][n];
            distance = new int[n][n];

            //BFS 탐색으로 현재 지점부터 방문가능한 모든 지점의 최단거리 탐색
            Pos current = deque.peek();
            visited[current.x][current.y] = true;
            if(!bfs(level)){
                return;
            }

            //죽일 수 있는 몬스터 찾기
            Monster killedMonster = findMonster(level);
            //현재 레벨에서 죽일 수 있는 몬스터가 없으면 종료
            if(Objects.isNull(killedMonster)) {
                return;
            }

            //몬스터 죽인 후 처리
            time += distance[killedMonster.x][killedMonster.y];
            game[killedMonster.x][killedMonster.y] = 0;
            deque.offer(new Pos(killedMonster.x,killedMonster.y));
            monsterList.remove(killedMonster);
            kill++;

            //레벨만큼 몬스터를 죽이면 레벨 상승
            if(level == kill){
                level++;
                kill = 0;
            }
        }
    }

    static boolean bfs(int level){
        int[] nx = {1,0,-1,0};
        int[] ny = {0,1,0,-1};
        int flag = 0;

        while(!deque.isEmpty()){
            Pos pos = deque.poll();

            for(int i=0; i<4; i++){
                int x = nx[i] + pos.x;
                int y = ny[i] + pos.y;

                if(stop(x,y,level)){
                    continue;
                }
                //죽일 수 있는 몬스터가 존재
                if(game[x][y] < level){
                    flag += game[x][y];
                }
                distance[x][y] = distance[pos.x][pos.y] + 1;
                visited[x][y] = true;
                deque.offer(new Pos(x,y));
            }
        }

        return flag > 0;
    }

    private static boolean stop(int x, int y, int level){
        return x < 0 || y < 0 || x >= n || y >= n || visited[x][y] || game[x][y] > level;
    }

    static Monster findMonster(int level){
        Monster killMonster = null;
        for (Monster monster : monsterList) {
            if (monster.level >= level) {
                break;
            }
            if (monster.requestUpdate(killMonster)) {
                killMonster = monster;
            }
        }
        return killMonster;
    }
}
