import java.io.*;
import java.util.*;

/*
 * 대표 선수
 */

public class DH_2461 {
	static class Node {
		int value, i, j;
		public Node(int value, int i, int j) {
			this.value = value;
			this.i = i;
			this.j = j;
		}
	}
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 학급의 수
		int M = Integer.parseInt(st.nextToken()); // 학생의 수
		
		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
			// 한 줄이 다 입력될 때 마다 정렬
			Arrays.sort(arr[i]);
		}

		int result = Integer.MAX_VALUE;
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

		int max = 0;
        for(int i = 0 ; i < N; i++){
        	pq.add(new Node(arr[i][0], i, 0));
            max = Math.max(arr[i][0],max);
        }
        
        while(true){
        	Node current = pq.poll();
            result = Math.min(max - current.value, result);

			// 제일 작은 값이 있는 원소의 idx가 열의 마지막 idx라면 반복문 나오기
            if(current.j == M - 1) break;

			// 최솟값이 있는 행의 열 idx를 + 1 한 뒤, pq에 넣어주고,
			// max 값 업데이트 해주기
            int value = arr[current.i][current.j + 1];
            pq.add(new Node(value, current.i, current.j + 1));
            max = Math.max(max,value);
        }
        System.out.println(result);
    }
}
