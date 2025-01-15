import java.util.Arrays;

/*
 * 징검다리
 */

public class DH_43236_2 {
	
	static int solution(int distance, int[] rocks, int n) {
        return getUpperBound(distance, rocks, n);
    }
    
    static int getUpperBound(int distance, int[] rocks, int n) {
    	
    	int s = 0, e = distance;
    	
    	Arrays.sort(rocks);
    	
    	while(s <= e) {
    		// 각 지점 사이의 최솟값
    		int tmp = (s + e) / 2;
    		int getRemoveRockCnt = getRemoveRockCnt(rocks, tmp, distance);
    		
    		// upper bound를 구하기 위해서는
    		// getREmoveRockCnt == n일 때, s를 옮겨야 됨
    		// 지워야 되는 것들이 많다면, 뛰는 거리를 줄여야 됨
    		if(getRemoveRockCnt > n) e = tmp - 1;
    		else s = tmp + 1;
    	}
    	
    	return e;
    }
    
    // 각 지점 사이 갈 수 있는 최솟값이 tmp 일 때, 제거해야되는 바위의 개수
    static int getRemoveRockCnt(int[] rocks, int tmp, int distance) {
    	
    	int removeCnt = 0, prevPosition = 0;
    	
    	for(int i = 0; i < rocks.length + 1; i++) {
    		int next = i == rocks.length ? distance: rocks[i];
    		
    		// 각 지점 사이 최솟값이 tmp가 되기 위해서
    		// next - prevPosition 이 tmp보다 작다면 해당 부분을 징검다리를 없애서, tmp보다 길게 만들어줘야 함
    		if(next - prevPosition < tmp) {
    			removeCnt += 1;
    			continue;
    		}
    		
    		if(i == rocks.length) break;
    		prevPosition = rocks[i];
    	}
    	
    	return removeCnt;
    }
    
	public static void main(String[] args) {
		System.out.println(Math.log(1_000_000_000) / Math.log(2) * 50000);
		int distance = 25;
		int rocks[] = {2, 14, 11, 21, 17};
		int n = 2;
		
		System.out.println(solution(distance, rocks, n));
	}
}
