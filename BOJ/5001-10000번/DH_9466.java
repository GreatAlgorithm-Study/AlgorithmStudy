import java.io.*;
import java.util.*;

/*
 * 텀 프로젝트
 */

public class DH_9466 {
	
	static int cnt;
	static int[] arr;
	static boolean[] check, v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/DH_9466.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			arr = new int[n + 1];
			check = new boolean[n + 1];
			v = new boolean[n + 1];
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
				
			for(int i = 1; i < arr.length; i++) {
				if(check[i]) continue; // 확인한 학생이라면 건너뛰기
				dfs(i, i);
			}
			sb.append(arr.length - 1 - cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int start, int current) {
		if(check[current]) return;

		// 사이클이 발생한 경우
		if(v[current]) {
			check[current] = true;
			cnt += 1;
		}
		
		v[current] = true;
		dfs(start, arr[current]);
		
		check[current] = true; // 확인 표시
		v[current] = false; // 원상복구
	}
}
