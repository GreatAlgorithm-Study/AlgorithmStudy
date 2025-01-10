// 주어진 격자를 통해 만들어지는 빛의 경로 사이클의 모든 길이들을 배열에 담아 오름차순으로 정렬
// 시간복잡도 : 500 * 500 * 4 -> 가능 -> O(NM)

import java.util.*;
class HW_86052 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int r, c;
    static boolean[][][] visited;
    public int[] solution(String[] grid) {
        r = grid.length;
        c = grid[0].length();
        visited = new boolean[r][c][4];
        List<Integer> answer = new ArrayList<>();

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                for(int d=0; d<4; d++){
                    if(!visited[i][j][d]){
                        answer.add(rotate(grid, i, j, d));
                    }
                }
            }
        }
        Collections.sort(answer); // 오름차순 정렬

        int[] result = new int[answer.size()]; // 배열로 변환 후 출력
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
    static int rotate(String[] grid, int x, int y, int dir){
        int cnt = 0;
        while(!visited[x][y][dir]){
            visited[x][y][dir] = true;
            cnt++;

            String input = String.valueOf(grid[x].charAt(y));
            if(input.equals("L")){
                dir = (dir+3)%4;
            } else if(input.equals("R")){
                dir = (dir+1)%4;
            }
            x = (x+dx[dir]+r) % r;
            y = (y+dy[dir]+c) % c;
        }
        return cnt;
    }
}