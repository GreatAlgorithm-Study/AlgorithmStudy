import java.io.*;
import java.util.*;

/*
 * 히스토그램
 */

public class DH_1725 {

//	---------- 스택 사용 코드 ---------- 
	static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 2];
        // 양 끝에 0을 넣어줌
        for (int i = 1; i < N + 1; i++) arr[i] = Integer.parseInt(br.readLine());

        long result = getMaxArea(N);
        System.out.println(result);
    }

    static long getMaxArea(int N) {
        long maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int height = arr[stack.pop()];
                int width = i - (stack.isEmpty() ? 0 : stack.peek() + 1);
                maxArea = Math.max(maxArea, height * width);
            }
            
            // push 하려는 막대의 높이가 stack.peek()보다 크다면 stack에 넣어주기
            stack.push(i);
        }

        return maxArea;
    }
	
//	---------- 시간 초과 코드 ---------- 
    static int[] tree, idxTree;
    static final int INF = Integer.MAX_VALUE;
	static void timeOutCode() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		arr = new int[N + 2];
		
		int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
		tree = new int[1 << k];
		idxTree = new int[1 << k];
		
		for(int i = 1; i < N + 1; i++) arr[i] = Integer.parseInt(br.readLine());
		
		long result = 0;
		for(int i = 1; i < N + 1; i++) {
			int l = getIdx(0, i - 1, i), r = getIdx(i + 1, N + 1, i);
			result = Math.max(result, (r - l - 1) * arr[i]);
		}
		
		System.out.println(result);
	}
	
	static int getIdx(int s, int e, int c) {
		// 인덱스가 s와 e 사이이면서 현재(c)보다 작은 숫자를 가진 원소의 idx 구하기
		boolean isLeft = e <= c;
		int k = (int) Math.ceil(Math.log(e - s + 1) / Math.log(2)) + 1;
		
		int idx = 1 << (k - 1);
		for(int i = s; i < e + 1; i++) {
			tree[idx + i - s] = arr[i];
			
			// 인덱스 저장 (idxTree에서 0인 것과 0번 인덱스를 구분하기 위해, 진짜 인덱스에 + 1을 해줌)
			idxTree[idx + i - s] = i + 1;
		}
		
		if(isLeft) {
				
			for(int i = idx + e + 1 - s; i < idxTree.length; i++) {
				tree[i] = tree[i - 1];
				idxTree[i] = idxTree[i - 1];
			}
		}
		
		for(int i = idx - 1; i > 0; i--) {
			int a = i * 2;
			int b = i * 2 + 1;
			
			if(tree[a] < arr[c] && tree[b] < arr[c]) {
				if(isLeft) {
					tree[i] = tree[b];
					idxTree[i] = idxTree[b];
				} else {
					tree[i] = tree[a];
					idxTree[i] = idxTree[a];
				}
			} else if(tree[a] < arr[c] && tree[b] >= arr[c]) {
				tree[i] = tree[a];
				idxTree[i] = idxTree[a];
			} else if(tree[a] >= arr[c] && tree[b] < arr[c]) {
				tree[i] = tree[b];
				idxTree[i] = idxTree[b];
			} else {
				tree[i] = tree[a];
				if(isLeft) idxTree[i] = Math.min(idxTree[a], idxTree[b]);
				else idxTree[i] = Math.max(idxTree[a], idxTree[b]);
			}
		}
		
		return idxTree[1] - 1;
	}
}
