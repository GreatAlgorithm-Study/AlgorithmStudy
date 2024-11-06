import java.util.*;
import java.io.*;

public class JY_3020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(arr));
		int[] urr = new int[H+2];
		int[] drr = new int[H+2];
		for(int i=0; i<N; i++) {
			// 석순
			if(i % 2 == 0) {
				urr[1]++;
				urr[arr[i]+1]--;
			}
			// 종유순
			else {
				drr[H]++;
				drr[H-arr[i]]--;
			}
		}
		
		for(int i=1; i<H+2; i++) {
			urr[i] += urr[i-1];
		}
//		System.out.println(Arrays.toString(urr));
		for(int i=H-1; i>=0; i--) {
			drr[i] += drr[i+1];
		}
//		System.out.println(Arrays.toString(drr));
		
		int ans = Integer.MAX_VALUE;
		int total = 0;
		for(int i=1; i<=H; i++) {
			if(ans > urr[i]+drr[i]) {
				ans = urr[i]+drr[i];
				total = 1;
			} else if(ans == urr[i]+drr[i]) {
				total++;
			}
		}
		System.out.println(ans+" "+total);
		

	}

}
