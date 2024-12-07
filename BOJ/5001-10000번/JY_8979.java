package day1204;

import java.io.*;
import java.util.*;

public class JY_8979 {
	
	static class Nation implements Comparable<Nation> {
		int num, g, s, b;

		public Nation(int num, int g, int s, int b) {
			super();
			this.num = num;
			this.g = g;
			this.s = s;
			this.b = b;
		}

		@Override
		public int compareTo(Nation other) {
			if(this.g == other.g) {
				if(this.s == other.s) {
					return other.b - this.b;
				}
				return other.s - this.s;
			}
			return other.g - this.g;
		}

		@Override
		public String toString() {
			return "Nation [num=" + num + ", g=" + g + ", s=" + s + ", b=" + b + "]";
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Nation> nList = new ArrayList<>();
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nList.add(new Nation(num, g, s, b));
		}
		
		Collections.sort(nList);
		
		int[] arr = new int[N+1];
		int rank = 1;
		arr[1] = rank;
		
		for(int i=1; i<N; i++) {
			Nation pre = nList.get(i-1);
			Nation now = nList.get(i);
			// 현재 랭킹 유지
			if(pre.g == now.g && pre.s == now.s && pre.b == now.b) {
				arr[i+1] = rank;
				continue;
			}
			// 랭킹 업데이트 될때 사이 랭킹은 사라짐
			rank = i+1;
			arr[i+1] = rank;
		}
		
		
		for(int i=0; i<N; i++) {
			if(nList.get(i).num == K) {
				System.out.println(arr[i+1]);
				break;
			}
		}
	}

}
