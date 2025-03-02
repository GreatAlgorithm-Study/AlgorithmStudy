import java.io.*;
import java.util.*;
public class JY_21758 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 왼쪽부터 누적합
		int[] preL = new int[N];
		preL[0] = arr[0];
		for(int i=1; i<N; i++) {
			preL[i] += (preL[i-1]+arr[i]);
		}
		
		// 배열 뒤집기
		int[] reArr = new int[N];
		for(int i=N-1, j=0; i>=0; i--, j++) reArr[j] = arr[i];
		
		// 오른쪽부터 누적합
		int[] preR = new int[N];
		preR[0] = reArr[0];
		for(int i=1; i<N; i++) {
			preR[i] += (preR[i-1]+reArr[i]);
		}
		
		
		int ans = 0;
		for(int i=1; i<N-1; i++) {
			// 가장 왼쪽에 꿀통이 있을 떄,
			// 꿀통(고정), 벌2(i), 벌1(고정)
			// 벌1 + 벌2 - (벌2가 겹치는 부분)
			int left = preL[N-2] + preL[i-1] - arr[i];
			ans = Math.max(ans, left);
			
			// 가장 오른쪽에 꿀통이 있을 때,
			// 벌1(고정), 벌2(i), 꿀통(고정)
			// 벌1 + 벌2 - (벌2가 겹치는 부분)
			int right = preR[N-2] + preR[i-1] - reArr[i];
			ans = Math.max(ans, right);
			
			// 꿀통이 중간에 있을 때,
			// 벌1(고정), 꿀통(i), 벌2(고정)
			// 두 벌들을 제외한 누적합 + 꿀통위치
			int mid = preL[N-2] - preL[0] + arr[i];
			ans = Math.max(ans, mid);
		}
		
		System.out.println(ans);

	}

}
