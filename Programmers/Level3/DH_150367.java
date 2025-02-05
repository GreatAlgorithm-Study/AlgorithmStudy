import java.util.*;

/*
 * 표현 가능한 이진트리
 */

public class DH_150367 {
	static char[] tree;
	static int idx;
	
	static int[] solution(long[] numbers) {
		
		int[] answer = new int[numbers.length];
		
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == 0) {
				answer[i] = 0;
				continue;
			}
			
			// 입력된 수를 이진수 문자열로 바꿈
			String binaryString = numToBinaryStr(numbers[i]);

			int totalLength = binaryString.length();
			tree = new char[totalLength];
			setTree(binaryString, totalLength);
			answer[i] = check(tree) ? 1: 0;
		}
		
		return answer;
	}
	
	static String numToBinaryStr(long number) {
		String binaryString = Long.toBinaryString(number);
		
		// 포화 이진 트리를 구성하기 위해 필요한 노드의 총 개수
		int totalLength = getTotalLength(binaryString.length());
		
		// 추가로 더해야되는 0의 개수
		int addZeroLength = totalLength - binaryString.length();
		String addZerostr = "0".repeat(addZeroLength);
		
		// 이진트리로 표현할 수 있도록 문자열 가공해주기
		binaryString = addZerostr + binaryString;
		return binaryString;
	}
	
	// 표현 가능한 이진트리인지 확인
	static boolean check(char[] tree) {
		boolean available = true;
		
		// 부모노드가 0인데, 자식 노드가 하나라도 1이라면 표현 불가능한 이진트리
		// 확인해야 되는 부모 노드 idx: 0 ~ (마지막 depth - 1) 노드까지
		// 왜냐하면 마지막 depth 노드들은 자식이 없기 때문
		int depth = (int) (Math.log(tree.length) / Math.log(2)) + 1;
		int startIdx = (1 << (depth - 1)) - 1;
		
		for(int i = 0; i < startIdx; i++) {
			int currentIdx = i + 1;
			int next1 = currentIdx * 2;
			int next2 = currentIdx * 2 + 1;
			
			next1 -= 1; next2 -= 1;
			
			if(tree[i] == '0' && (tree[next1] == '1' || tree[next2] == '1')) {
				available = false;
				break;
			}
		}

		return available;
	}
	
	// inorder로 주어진 정보로 tree 구성하기
	static void setTree(String s, int totalLength) {
		idx = 0;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, totalLength + 1});
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int l = current[0], r = current[1];
			if(l == r || r - l == 1) continue;

			int middle = (l + r) >> 1;
			tree[idx] = s.charAt(middle - 1);
			idx += 1;
			
			q.add(new int[] {l, middle});
			q.add(new int[] {middle, r});
		}
	}
	
	// 현재 트리의 크기가 length인 경우, 만들 수 있는 포화이진트리의 크기
	static int getTotalLength(int length) {
		int n = 1, digit = 0;
		
		while(true) {
			digit = (1 << n++) - 1;
			if(digit >= length) break;
		}
		
		return digit;
	}
	
	public static void main(String[] args) throws Exception {
		long[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 128, 129, 16512, 2147516555L};
		System.out.println(Arrays.toString(solution(numbers)));
	}
}
