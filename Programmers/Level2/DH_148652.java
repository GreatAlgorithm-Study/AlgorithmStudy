/*
 * 유사 칸토어 비트열
 */

public class DH_148652 {
	static long solution(int n, long l, long r) {
		return getOneCnt(r, n) - getOneCnt(l - 1, n);
	}
	
	static long getOneCnt(long e, int depth) {
		if(depth == 0) return 1; // 0번째 비트열: 1
		
		long digit = (long) Math.pow(5, depth - 1); // depth번째에서 한 그룹당 몇 개의 자릿수를 가지고 있는지
		
		// e가 속하는 구간이 어디인지 구하기 (0, 1, 2, 3, 4)
		int area = (int) (e / digit);
		if(e % digit == 0) area -= 1; // 구간 경계에 있는거 올바른 구간에 포함될 수 있도록 -1 해주기
		
		int prevOneCnt = (int) Math.pow(4, depth - 1);
		
		// 0기준 왼쪽 (1인 구역의 개수가 전체 자리수)개
		if(area < 2) return prevOneCnt * area + getOneCnt(e - (digit * area), depth - 1);
		// 0있는 부분
		else if(area == 2) return prevOneCnt * area;
		// 0기준 오른쪽 (1인 구역의 개수가 전체 자리수 - 1)개
		return prevOneCnt * (area - 1) + getOneCnt(e - (digit * area), depth - 1);
	}
	public static void main(String[] args) {
		int n = 2, l = 4, r = 17;
		System.out.println(solution(n, l, r));
	}
}
