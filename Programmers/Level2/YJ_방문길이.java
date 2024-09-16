import java.util.LinkedList;
import java.util.Queue;

/**
 * 알고리즘: BFS
 * 시간복잡도: O(n)
 * 아이디어:
 * BFS로 순회할때 방문한 지점을 방문처리 하는 것이 아닌 다녀간 길을 방문처리 해야 함
 * 다녀간 길을 표시하는 방법?
 * 
 */
public class YJ_방문길이 {
    public static void main(String[] args) {
        String dirs = "ULURRDLLU";  //7
//        String dirs = "LULLLLLLU";  //7
        System.out.println(solution(dirs));
    }

    static int answer = 0;
    static int[][] direction = {{0,1},{0,-1},{-1,0},{1,0}}; //상,하,좌,우
    static boolean[][] visited = new boolean[11][11];
    static Queue<int[]> queue = new LinkedList<>();
    static int solution(String dirs) {
        String[] input = dirs.split("");
        int[] start = {5,5};
        int[] end = {11,11};

        //현재위치 방문처리
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        for(String i : input){
            switch(i){
                case "U":
                    bfs(visited,direction[0]);
                    break;
                case "D":
                    bfs(visited,direction[1]);
                    break;
                case "L":
                    bfs(visited,direction[2]);
                    break;
                case "R":
                    bfs(visited,direction[3]);
                    break;
            }
        }
        return answer;
    }

    private static void bfs(boolean[][] visited, int[] direction){
        int[] current = queue.peek();
        //위치 이동
        int nx = current[0] + direction[0];
        int ny = current[1] + direction[1];

        if(nx < 0 || nx > visited[0].length -1 || ny < 0 || ny > visited.length-1){
            return;
        }
        if(visited[nx][ny]){
            return;
        }
        //방문처리
        queue.poll();
        visited[nx][ny] = true;
        //이동값 갱신
        queue.offer(new int[]{nx,ny});
        answer++;
    }
}
