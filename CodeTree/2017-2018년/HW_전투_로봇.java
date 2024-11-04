import java.io.*;
import java.util.*;

public class HW_전투_로봇 {
    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int rbX, rbY;
    static int rblevel = 2; // 초기 로봇 레벨 : 2
    static int monsters = 0, time = 0;
    public static class Node{
        int x, y, d;
        Node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d; // 최단거리 기준으로 몬스터를 잡아야하기에
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==9) {// 전투 로봇 lv9
                    rbX = i;
                    rbY = j;
                    board[i][j] = 0; // 로봇 위치->빈칸 처리
                }
            }
        }
        while(true){
            Node target = bfs();
            if(target==null){
                break;
            }
            // 로봇 이동 및 시간 증가
            rbX = target.x;
            rbY = target.y;
            time += target.d;

            board[rbX][rbY] = 0;
            monsters++;
            if(monsters == rblevel){
                rblevel++;
                monsters = 0;
            }
        }
        System.out.println(time);
    }
    public static Node bfs(){ // 잡을 수 있는 몬스터 찾기
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[n][n];
        queue.add(new Node(rbX, rbY, 0)); // 초기값
        visited[rbX][rbY] = true; // 방문 처리
        List<Node> temp = new ArrayList<>(); // 잡을 수 있는 몬스터를 저장

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(check(nx, ny) && !visited[nx][ny] && board[nx][ny] <= rblevel){
                    visited[nx][ny] = true; // 방문 처리
                    if(board[nx][ny]>0 && board[nx][ny] <rblevel){
                        temp.add(new Node(nx, ny, cur.d+1)); // 후보로 추가
                    }
                    queue.add(new Node(nx, ny, cur.d+1)); // 다음 위치로
                }
            }
        }
        if(temp.isEmpty()){
            return null;
        }
        // 가장 가까운 거리의 몬스터를 없애기 위해 정렬
        temp.sort((a, b) ->{
            if(a.d == b.d){ // 가장 가까운 거리의 없앨 수 있는 몬스터가 하나 이상이라면
                if(a.x == b.x){
                    return Integer.compare(a.y, b.y); // 가장 위에 존재하는 몬스터
                }
                return Integer.compare(a.x, b.x); // 가장 왼쪽에 존재하는 몬스터부터
            }
            return Integer.compare(a.d, b.d); // 거리가 가장 가까운 몬스터
        });
        return temp.get(0); // 가장 우선순위가 높은 타겟 없앰
    }
    public static boolean check(int x, int y){
        return x >=0 && y >=0 && x < n && y <n;
    }
}