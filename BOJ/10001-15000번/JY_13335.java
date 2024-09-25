package day0925;

import java.util.*;
import java.io.*;

public class JY_13335 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> bridges = new ArrayDeque<>();
		ArrayDeque<Integer> trucks = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trucks.add(Integer.parseInt(st.nextToken()));
		}
		
		// 다리 길이 만큼 임시로 크기가 0인 트럭 올리기
		for(int w=0; w<W; w++) {
			bridges.add(0);
		}
		
//		System.out.println(bridges);
//		System.out.println(trucks);
		
		
		int total = 0;
		int time = 0;
		while(!bridges.isEmpty()) {	
			// 다리에서 트럭 이동
			total -= bridges.poll();
			
			// 트럭을 추가할 수 있으면 추가 or 크기가 0인 트럭 추가
			if(!trucks.isEmpty()) {
				if(total+trucks.peek() <= L) {
					int truck = trucks.poll();
					total += truck;
					bridges.add(truck);
				} else {
					bridges.add(0);
				}				
			}
			time++;
		}
		System.out.println(time);
		

	}

}
