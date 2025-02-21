import java.io.*;
import java.util.*;

/*
 * 냅색 문제
 */

public class DH_1450 {
	static int N;
	static long C;
	static int[] arr1, arr2;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}

	static void solution() {
		
		// 반씩 나눈 물건들의 각각의 그룹에서 더했을 때 나올 수 있는 모든 경우 구해주기
		ArrayList<Long> list1 = new ArrayList<>();
		ArrayList<Long> list2 = new ArrayList<>();
		
		getSumList(list1, arr1, 0, 0);
		getSumList(list2, arr2, 0, 0);
		
		// 이분탐색을 위한 정렬
		Collections.sort(list1);
		Collections.sort(list2);

		// N개의 물건에 가방을 넣는 방법의 수 구하기
		System.out.println(getCount(list1, list2));
	}
	
	static long getCount(ArrayList<Long> list1, ArrayList<Long> list2) {
		long cnt = 0;
		
		for(long n: list1) {
			int s = 0, e = list2.size() - 1;
			
			long leftValue = C - n;
			
			while(s <= e) {
				int m = (s + e) / 2;
				
				// upperbound 구해야 됨 (s 옮기기)
				if(list2.get(m) <= leftValue) s = m + 1;
				else e = m - 1;
			}
			
			// 인덱스를 개수로 바꿔주기 위해 + 1을 함
			cnt += e + 1;
		}
		
		return cnt;
	}
	static void getSumList(ArrayList<Long> list, int[] arr, int depth, long sum) {
		if(depth == arr.length) {
			list.add(sum);
			return;
		}
		
		getSumList(list, arr, depth + 1, sum + arr[depth]);
		getSumList(list, arr, depth + 1, sum);
	}
	static void initInput() throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 물건의 개수
		C = Integer.parseInt(st.nextToken()); // 최대 담을 수 있는 무게

		// 입력되는 물건들 반 씩 나누어서 생각하기
		arr2 = new int[N / 2];

		if(N % 2 == 0) arr1 = new int[N / 2];
		else arr1 = new int[N / 2 + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < arr1.length; i++) arr1[i] = Integer.parseInt(st.nextToken());
		for(int i = 0; i < arr2.length; i++) arr2[i] = Integer.parseInt(st.nextToken());
	}
}
