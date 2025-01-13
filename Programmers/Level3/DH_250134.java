import java.util.*;

public class DH_250134 {
	static final int RED = 0, BLUE = 1, INF = Integer.MAX_VALUE;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static class Node {
        int r, c, color;
        int[][][] v;
        
        public Node() {}
        public Node(int color, int r, int c, int[][][] v) {
            this.r = r;
            this.c = c;
            this.color = color;
            this.v = v;
        }
        
        public void update(int r, int c) {
        	this.r = r;
        	this.c = c;
        }
        
        @Override
        public String toString() {
            return "[r: " + r + ", c: " + c + ", color: " + color + "]";
        }
    }
    static int answer = INF;
    
    static int solution(int[][] maze) {
        
        // 0: 빨간 수레 위치, 1: 파란 수레 위치, 2: 빨간 수레 도착 칸, 2: 파란 수레 도착 칸
        int[][] positionInfo = new int[4][2];
        initPoints(positionInfo, maze);
        
        bfs(RED, BLUE, positionInfo, maze);
        
        return answer == INF ? 0 : answer;
    }
    
    static void bfs(int red, int blue, int[][] positionInfo, int[][] maze) {
    	ArrayDeque<Node> q = new ArrayDeque<>();
    	
    	int[][][] rv = new int[2][maze.length][maze[0].length];
    	rv[red][positionInfo[red][0]][positionInfo[red][1]] = 1;
    	rv[blue][positionInfo[blue][0]][positionInfo[blue][1]] = 1;
    	
    	int[][][] bv = new int[2][maze.length][maze[0].length];
    	bv[red][positionInfo[red][0]][positionInfo[red][1]] = 1;
    	bv[blue][positionInfo[blue][0]][positionInfo[blue][1]] = 1;
    	
    	q.add(new Node(red, positionInfo[red][0], positionInfo[red][1], rv));
    	q.add(new Node(blue, positionInfo[blue][0], positionInfo[blue][1], bv));
    	
    	int flag = 0;

    	int fr = -1, fc = -1;
    	int maxDis = 0;

    	while(!q.isEmpty()) {
    		Node current = q.poll();
    		
    		if(positionInfo[current.color + 2][0] == current.r && positionInfo[current.color + 2][1] == current.c) {
    			System.out.println("color: " + current.color + " , depth: " + (current.v[current.color][current.r][current.c] - 1));
    			System.out.println("도착: " + current.r + " " + current.c + " " + (current.v[current.color][current.r][current.c] - 1));
    			flag |= 1 << current.color;
    			fr = current.r;
    			fc = current.c;
    			maxDis = current.v[current.color][current.r][current.c] - 1;
    		}
    		
    		if(flag == ((1 << 2) - 1)) {
    			System.out.println("color: " + current.color + " , depth: " + (current.v[current.color][current.r][current.c] - 1));

    			answer = Math.max(maxDis, current.v[current.color][current.r][current.c] - 1);
    			System.out.println("됐따 !!");
    			break;
    		}
        		
    		if((flag & (1 << current.color)) > 0) continue;
    		
    		for(int d = 0; d < 4; d++) {
    			int nr = current.r + dr[d];
    			int nc = current.c + dc[d];
    				
    			int[][][] currentV = current.v;
    			int[][][] tmpV = new int[2][currentV[0].length][currentV[0][0].length];
    			
    			for(int k = 0; k < currentV.length; k++) {
    				for(int r = 0; r < currentV[0].length; r++) {
    					for(int c = 0; c < currentV[0][0].length; c++) tmpV[k][r][c] = currentV[k][r][c];
    				}
    			}
    			
    			if(!check(nr, nc, maze.length, maze[0].length)) continue;
    			if(nr == fr && fc == nc) continue;
    			if(tmpV[current.color][nr][nc] > 0 || 
    					maze[nr][nc] == 5 || 
    					(tmpV[current.color ^ 1][nr][nc] == tmpV[current.color][current.r][current.c] + 1)) continue;
    			
    			System.out.println((current.color == 0 ? "RED" : "BLUE") + " " + nr + " " + nc + " " + current.v[current.color][current.r][current.c]);
    			
    			tmpV[current.color][nr][nc] = tmpV[current.color][current.r][current.c] + 1;
    			q.add(new Node(current.color, nr, nc, tmpV));
    		}
    	}
    }
    
    static void initPoints(int[][] positionInfo, int[][] maze) {
        for(int r = 0; r < maze.length; r++) {
        	for(int c = 0; c < maze[0].length; c++) {
        		if(maze[r][c] == 0 || maze[r][c] == 5) continue;
        		int status = maze[r][c] - 1;
        		positionInfo[status][0] = r;
        		positionInfo[status][1] = c;
        	}
        }
    }
    
    
    static boolean check(int nr, int nc, int r, int c) {
        return nr >= 0 && nr < r && nc >= 0 && nc < c;
    }
    

	public static void main(String[] args) {
		int[][] maze = {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}};
		System.out.println(solution(maze));
	}
}
