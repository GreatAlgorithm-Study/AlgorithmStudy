import java.io.*;
import java.util.*;
public class JY_1450 {
	
	static int N, C;
	static int[] arr;
	static List<Integer> left;
	static List<Integer> right;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 왼쪽, 오른쪽 부분 집합 구하기
		left = new ArrayList<>();
		right = new ArrayList<>();
		comb(left, 0, N/2, 0);		
		comb(right, N/2, N, 0);	
		
		// 오른쪽 부분합 정렬
		Collections.sort(right);
		
		int cnt = 0;
		int idx = 0;
		for(int i=0; i<left.size(); i++) {
			idx = bs(0, right.size()-1, left.get(i));
			cnt += (idx+1);
		}
		
		System.out.println(cnt);
		

	}
	public static void comb(List<Integer> cList, int s, int e, int sum) {
		// 최대 무게 C보다 크면 return
		if(sum > C) return;
		// 모두 탐색
		if(s == e) {
			cList.add(sum);
			return;
		}
		
		// s번째 포함 X
		comb(cList, s+1, e, sum);
		// s번째 포함 O
		comb(cList, s+1, e, sum+arr[s]);
	}
	public static int bs(int s, int e, int a) {
		int ans = 0;
		while(s <= e) {
			int mid = (s + e) / 2;
			if(right.get(mid) <= C - a) {
				ans = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return ans;
	}

}
