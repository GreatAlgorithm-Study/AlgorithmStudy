/**
 * 알고리즘: 단순 구현?
 * 시간복잡도: O(n)
 * 아이디어:
 *
 */
public class YJ_방문길이 {
    public static void main(String[] args) {
//        String dirs = "ULURRDLLU";  //7
        String dirs = "LULLLLLLU";  //7
        System.out.println(solution(dirs));
    }

    static int solution(String dirs) {
        int answer = 0;
        boolean[][] visited = new boolean[11][11];
        String[] direction = dirs.split("");

        //현재위치 저장
        int x =5;
        int y =5;
        visited[x][y] = true;
        boolean isvisited = false;
        //U:(0,1), D:(0,-1), R:(1,0), L:(-1,0)
        for(String d : direction){
            switch(d){
                case "U":
                    isvisited = visited[x][y+1];
                    if(!isvisited && 11 > y+1){
                        visited[x][y+1] = true;
                        y = y+1;
                        answer++;
                    }
                case "D":
                    isvisited = visited[x][y-1];
                    if(!isvisited && 0 < y-1){
                        visited[x][y-1] = true;
                        y = y-1;
                        answer++;
                    }
                    break;
                case "R":
                    isvisited = visited[x+1][y];
                    if(!isvisited){
                        visited[x+1][y] = true;
                        x = x+1;
                        answer++;
                    }
                    break;
                case "L":
                    isvisited = visited[x-1][y];
                    if(!isvisited && 0 < x-1){
                        visited[x-1][y] = true;
                        x = x-1;
                        answer++;
                    }
                    break;
            }
        }

        return answer;  //캐릭터가 처음 걸어본 길의 길이
    }
}
