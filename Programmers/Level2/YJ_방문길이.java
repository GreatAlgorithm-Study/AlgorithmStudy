import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 알고리즘: BFS
 * 시간복잡도: O(n)
 * 아이디어:
 * BFS로 순회할때 방문한 지점을 방문처리 하는 것이 아닌 다녀간 길을 방문처리 해야 함
 - 현재 위치(x,y) + 다음 위치(x,y) 를 문자열 형태로 저장
 > 중복일 경우 이미 다녀간 경로!
 - 다음 위치(x,y) + 현재 위치(x,y) 를 문자열 형태로 저장
 > 위아래 또는 좌우 왔다갔다한 경로도 이미 다녀간 경로!
 */
public class YJ_방문길이 {
    public static void main(String[] args) {
//        String dirs = "ULURRDLLU";  //7
        String dirs = "LULLLLLLU";  //7
        System.out.println(solution(dirs));
    }

    static int answer = 0;
    static int[][] direction = {{0,1},{0,-1},{-1,0},{1,0}}; //상,하,좌,우
    static HashSet<String> visited = new HashSet<>();
    static Queue<int[]> queue = new LinkedList<>();
    static int solution(String dirs) {
        String[] input = dirs.split("");
        int[] start = {5,5};
        int[] end = {11,11};

        //현재위치 방문처리
        queue.offer(start);
        for(String i : input){
            switch(i){
                case "U":
                    bfs(end,visited,direction[0]);
                    break;
                case "D":
                    bfs(end,visited,direction[1]);
                    break;
                case "L":
                    bfs(end,visited,direction[2]);
                    break;
                case "R":
                    bfs(end,visited,direction[3]);
                    break;
            }
        }
        return answer;
    }

    private static void bfs(int[] end, HashSet<String> visited, int[] direction){
        int[] current = queue.peek();
        int x = current[0];
        int y = current[1];
        //위치 이동
        int nx =  x + direction[0];
        int ny = y + direction[1];
        String visitLength = ""+x+y+nx+ny;
        String reverseLength = ""+nx+ny+x+y;

        if(nx < 0 || nx > end[0]-1 || ny < 0 || ny > end[1]-1){
            return;
        }
        //방문처리
        queue.poll();

        if(visited.contains(visitLength) || visited.contains(reverseLength)){
            queue.offer(new int[]{nx,ny});
            return;
        }
        visited.add(visitLength);
        queue.offer(new int[]{nx,ny});
        answer++;
    }
}
