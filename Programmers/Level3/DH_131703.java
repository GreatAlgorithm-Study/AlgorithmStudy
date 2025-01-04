import java.util.*;

/*
 * 2차원 동전 뒤집기
 */

public class DH_131703 {
	static final int INF = Integer.MAX_VALUE;
	static int result = INF;
	static boolean[] sel;
    
    static int solution(int[][] beginning, int[][] target) {
        int r = beginning.length, c = beginning[0].length;
        int size = r + c;
        
        sel = new boolean[size];
        
        // beginning과 target를 비교하면서 값이 다른 좌표를 set에 저장함
        HashSet<Integer> set = getDiffPointIdx(beginning, target);
        
        // 모든 행과 열에 대해 뒤집어보기
        // 행, 열에 대해 (뒤집는다 / 뒤집지 않는다) 두 가지의 경우가 있으므로 powerset 이용
        powerSet(0, 0, beginning, target, set);
        
        return result == INF ? - 1: result;
    }
    
    static void powerSet(int idx, int cnt, int[][] b, int[][] t, HashSet<Integer> set) {
    	if(idx == sel.length) return;
    	
    	// 뒤집기
    	sel[idx] = true;
    	b = flipMap(idx, b.length, b, t, set);
    	if(set.isEmpty()) result = Math.min(result, cnt + 1);
    	powerSet(idx + 1, cnt + 1, b, t, set);
    	
    	// 원상복구 하기
    	sel[idx] = false;
    	b = flipMap(idx, b.length, b, t, set);
    	if(set.isEmpty()) result = Math.min(result, cnt);
    	powerSet(idx + 1, cnt, b, t, set);
    }
    

    // beginning과 target를 비교하면서 값이 다른 좌표를 set에 저장함
    static HashSet<Integer> getDiffPointIdx(int[][] beginning, int[][] target) {
    	
    	HashSet<Integer> set = new HashSet<>();
    	int r = beginning.length, c = beginning[0].length;
    	
        for(int br = 0; br < r; br++) {
        	for(int bc = 0; bc < c; bc++) {
        		if(beginning[br][bc] != target[br][bc]) {
        			// 2차원 좌표를 1차원으로 변환하여 set에 저장
        			int idx = br * c + bc;
        			set.add(idx);
        		}
        	}
        }
        return set;
    }
    
    // i: 뒤집는 행/열의 번호
    static int[][] flipMap(int i, int r, int[][] b, int[][] t, HashSet<Integer> set) {
    	
    	// i가 행의 개수보다 크다면 열을 뒤집어야 됨
        if(i < r) {
        	// 지정된 행, 열에 대해 0-1 반전 (1과 XOR 연산)
        	for(int bc = 0; bc < b[0].length; bc++) {
        		b[i][bc] ^= 1;
        		
        		// 좌표 비교해서 set 업데이트 해주기
        		int idx = i * b[0].length + bc;
        		if(b[i][bc] == t[i][bc]) if(set.contains(idx)) set.remove(idx);
        		else set.add(idx);
        	}
        } else {
        	// 지정된 행, 열에 대해 0-1 반전 (1과 XOR 연산)
        	for(int br = 0; br < b.length; br++) {
        		b[br][i - r] ^= 1;
        		
        		// 좌표 비교해서 set 업데이트 해주기
        		int idx = br * b[0].length + (i - r);
        		if(b[br][i - r] == t[br][i - r]) if(set.contains(idx)) set.remove(idx);
        		else set.add(idx);
        	}
        }
        
        return b;
    }
}
