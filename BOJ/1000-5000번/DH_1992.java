import java.io.*;

/*
 * 쿼드트리
 */

public class DH_1992 {
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			
			for(int c = 0; c < N; c++) {
				arr[r][c] = s.charAt(c) - '0';
			}
		}

		int length = N;
			
		String str = func(0, 0, length);
		System.out.println(str);
	}
	
	static String func(int r, int c, int l) {
		String s = "";
		
		int sum = 0;
		for(int sr = r; sr < r + l; sr++) {
			for(int sc = c; sc < c + l; sc++) {
				sum += arr[sr][sc];
			}
		}
		
		if(sum == 0) s += "0";
		else if(sum == l * l) s += "1";
		else {
			int nl = l >> 1;
			s += "(";
			s += func(r, c, nl); // 왼쪽 위
			s += func(r, c + nl, nl); // 오른쪽 위
			s += func(r + nl, c, nl); // 왼쪽 아래
			s += func(r + nl, c + nl, nl); // 오른쪽 아래
			s += ")";
		}
		return s;
	}
}
