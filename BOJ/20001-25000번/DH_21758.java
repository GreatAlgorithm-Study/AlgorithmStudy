import java.io.*;
import java.util.*;

/*
 * 꿀 따기
 */

public class DH_21758 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		// arr, S1: 왼쪽에서 오른쪽으로 움직일 때 사용
		int[] arr = new int[N];
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());

		// tmp, S2: 오른쪽에서 왼쪽으로 움직일 때 사용 (arr를 뒤집어줌)
		int[] tmp = new int[N];
		for(int i = 0; i < N; i++) tmp[i] = arr[N - 1 - i];

		// 누적합 구하기
		int[] S1 = new int[N + 1], S2 = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			S1[i] = S1[i - 1] + arr[i - 1];
			S2[i] = S2[i - 1] + tmp[i - 1];
		}
		
		int result = 0;
		result = Math.max(result, getHoney(S1, arr, N));
		result = Math.max(result, getHoney(S2, tmp, N));
		
		System.out.println(result);
	}
	
	// 꿀벌들이 얻을 수 있는 꿀의 최대값 구하기
	static int getHoney(int[] S, int[] arr, int N) {
		int bee1 = 0, result = 0;
		
		// 첫 번째 꿀벌이 얻을 수 있는 꿀의 양
		int getBee1 = S[N] - S[bee1] - arr[bee1];
		
		// '꿀벌 - 꿀벌 - 꿀' 일 때
		for(int bee2 = 1; bee2 < N - 1; bee2++) {
			int tmpGetBee1 = getBee1 - arr[bee2]; // 다른 꿀벌이 있는 위치의 꿀은 못얻음
			
			// 두 번째 꿀벌이 얻ㅇ르 수 있는 꿀의 양
			int getBee2 = S[N] - S[bee2] - arr[bee2];
			result = Math.max(result, tmpGetBee1 + getBee2);
		}
		
		// '꿀벌 - 꿀 - 꿀벌' 일 때
		for(int honey = 1; honey < N - 1; honey++) {
			int tmpGetBee1 = S[honey + 1] - S[bee1] - arr[bee1]; // 다른 꿀벌이 있는 위치의 꿀은 못얻음
			int getBee2 = S[N] - S[honey] - arr[N - 1];
			
			result = Math.max(result, tmpGetBee1 + getBee2);
		}
		
		return result;
	}
}
