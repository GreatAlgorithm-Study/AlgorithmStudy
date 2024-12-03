import java.io.*;
import java.util.*;

public class JY_2096 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] dp1 = new int[N][3];
		int[][] dp2 = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			dp1[i][0] = Integer.parseInt(st.nextToken());
			dp1[i][1] = Integer.parseInt(st.nextToken());
			dp1[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			dp2[i][0] = dp1[i][0];
			dp2[i][1] = dp1[i][1];
			dp2[i][2] = dp1[i][2];
		}
		
		for(int i=1; i<N; i++) {
			dp1[i][0] = dp1[i][0] + Math.min(dp1[i-1][0], dp1[i-1][1]);
			dp1[i][1] = dp1[i][1] + Math.min(dp1[i-1][0], Math.min(dp1[i-1][1], dp1[i-1][2]));
			dp1[i][2] = dp1[i][2] + Math.min(dp1[i-1][1], dp1[i-1][2]);
			
			dp2[i][0] = dp2[i][0] + Math.max(dp2[i-1][0], dp2[i-1][1]);
			dp2[i][1] = dp2[i][1] + Math.max(dp2[i-1][0], Math.max(dp2[i-1][1], dp2[i-1][2]));
			dp2[i][2] = dp2[i][2] + Math.max(dp2[i-1][1], dp2[i-1][2]);
		}
		
		int max = Math.max(dp2[N-1][0], Math.max(dp2[N-1][1], dp2[N-1][2]));
		int min = Math.min(dp1[N-1][0], Math.min(dp1[N-1][1], dp1[N-1][2]));
		
		System.out.println(max+" "+min);

	}

}
