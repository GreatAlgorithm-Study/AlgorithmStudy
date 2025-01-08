import java.util.*;

/*
 * 빛의 경로 사이클
 */

public class DH_86052 {
    static class Node {
    	// Node가 [r][c] 지점일 때, [r][c]에서 dir 방향으로 갈 때, 현재 거리
    	// 변수명은 dir이지만, 방향이 아닌 거리를 저장
        int[] dir;
        
        public Node(int[] dir) {
            this.dir = dir;
        }
    }
    
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 상, 우, 하, 좌
    
	static int[] solution(String[] grid) {
        
        int row = grid.length, column = grid[0].length();
        
        ArrayList<Integer> answerList = new ArrayList<>();
        
        // 빛의 경로들 저장
        Node[][] nodeMap = initNodeMap(row, column);
        
        // 모든 격자 확인
        for(int r = 0; r < row; r++) {
        	for(int c = 0; c < column; c++) {
        		
        		// 한 격자에 대해 상, 우, 하, 좌 모두 확인
        		for(int d = 0; d < 4; d++) {
        			
        			// 사이클이 발생하는지 확인 (사이클 발생 → 0 보다 큰 숫자 반환) 
        			int cycleLength = getCycleLength(grid, nodeMap, r, c, d);        
        			if(cycleLength == 0) continue;
        			answerList.add(cycleLength);
        		}        	
        	}
        }
        
        Collections.sort(answerList);
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) answer[i] = answerList.get(i);
        
        return answer;
    }
    
	// 사이클이 발생하는지 확인하는 함수
    static int getCycleLength(String[] grid, Node[][] nodeMap, int sr, int sc, int sd) {
        
        char startCh = grid[sr].charAt(sc);
        if(startCh == 'L') sd = (sd + 4 - 1) % 4;
        if(startCh == 'R') sd = (sd + 4 + 1) % 4;
        
        // 초기 시작 좌표와 방향 저장
        int initR = sr, initC = sc, initD = sd;
        
        int dis = 0;
        int r = grid.length, c = grid[0].length();
        
        while(true) {
        	// dis가 0이 아니면서 현재 시작 좌표와 방향이 초기 좌표와 방향과 같다면 사이클이 발생한 것
        	if(dis != 0 && sr == initR && sc == initC && sd == initD) return dis;
        	if(nodeMap[sr][sc].dir[sd] != 0) break; // 만약에 dir[sd] != 0이라면 이미 사이클이 발생하는지 확인한 경로

        	// 현재 좌표에서 가야되는 방향을 거리로 채워넣기
        	nodeMap[sr][sc].dir[sd] = ++ dis;
        	
        	// 다음 좌표 구하기
            int nr = (sr + r + dr[sd]) % r;
            int nc = (sc + c + dc[sd]) % c;
            int nd = sd;
            
            // 다음 좌표에서의 방향 구하기
            if(grid[nr].charAt(nc) == 'L') nd = (nd + 4 - 1) % 4;
            if(grid[nr].charAt(nc) == 'R') nd = (nd + 4 + 1) % 4;
            
            // 현재 좌표 업데이트
            sr = nr;
            sc = nc;
            sd = nd;
        }
        
        return 0;
    }

    static Node[][] initNodeMap(int row, int column) {
        Node[][] nodeMap = new Node[row][column];
        
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < column; c++) {
                int[] dir = new int[4];
                
                nodeMap[r][c] = new Node(dir);
            }
        }
        
        return nodeMap;
    }
    
	public static void main(String[] args) {
		String[] grid = {"SL", "LR"};
		System.out.println(Arrays.toString(solution(grid)));
	}
}
