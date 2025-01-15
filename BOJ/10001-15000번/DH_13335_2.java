import java.io.*;
import java.util.*;

/*
 * 트럭
 */

public class DH_13335_2 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭의 수
		int w = Integer.parseInt(st.nextToken()); // 다리의 길이
		int L = Integer.parseInt(st.nextToken()); // 다리 최대 하중
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		for(int i = 0; i < w; i++) q.add(0);
		
		int[] truck = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) truck[i] = Integer.parseInt(st.nextToken());
		
		int tunnelWeight = 0;
		int truckIdx = 0;
		int time = 0;
		
		while(!q.isEmpty()) {
			
			time++;
			tunnelWeight -= q.poll(); // 맨 앞에 있는거 빼주고, 모든 원소 앞으로 당겨주기
			
			if(truckIdx == n) continue;
			if(tunnelWeight + truck[truckIdx] <= L) {
				tunnelWeight += truck[truckIdx];
				q.add(truck[truckIdx]);
				truckIdx++;
			}
			else q.add(0);
		}
		
		System.out.println(time);
		
		
	}
}
