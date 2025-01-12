import java.util.*;

class Solution {
    static boolean[][][] visited;
    static int N, M;
    // 하 -> 좌 -> 상 -> 우
    static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, -1, 0, 1};

    public int[] solution(String[] grid) {
        List<Integer> aList = new ArrayList<>();
        N = grid.length;
        M = grid[0].length();
        // 방향별로 방문 처리하기 위해 3차원으로 생성
        visited = new boolean[N][M][4];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                for(int d=0;d<4;d++) {
                    if(!visited[i][j][d]) {
                        aList.add(light(grid, i, j, d));
                    }
                }
            }
        }
        
        Collections.sort(aList);
        int[] answer = new int[aList.size()];
        for(int i=0; i<aList.size(); i++) {
            answer[i] = aList.get(i);
        }

        return answer;
    }

    public int light(String[] g, int i, int j, int d) {
        int cnt = 0;

        while(!visited[i][j][d]) {
            cnt++;
            visited[i][j][d] = true;

            if(g[i].charAt(j) == 'L') {
                // 좌회전
                d = (d+3) % 4; 
            }
            if(g[i].charAt(j) == 'R') {
                // 우회전
                d = (d+1) % 4; 
            }
            
            i = (i+dx[d]+N) % N;
            j = (j+dy[d]+M) % M;
        }
        return cnt;
    }
}
