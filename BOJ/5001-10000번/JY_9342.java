import java.io.*;
import java.util.*;

public class JY_9342 {
	
	static boolean isOk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			String str = br.readLine();
			
			isOk = false;
			isGen(str, 0, 0);
			if(!isOk) System.out.println("Good");
			else System.out.println("Infected!");
		}

	}
	/*
	 * << type >> 규칙
	 * 0 : 문자열은 {A, B, C, D, E, F} 중 0개 또는 1개로 시작해야 한다.
	 * 1 : 그 다음에는 A가 하나 또는 그 이상 있어야 한다.
	 * 2 : 그 다음에는 F가 하나 또는 그 이상 있어야 한다.
	 * 3 : 그 다음에는 C가 하나 또는 그 이상 있어야 한다.
	 * 4 : 그 다음에는 {A, B, C, D, E, F} 중 0개 또는 1개가 있으며, 더 이상의 문자는 없어야 한다.
	 * */
	public static void isGen(String s, int idx, int type) {
		if(type == 0) {
			if(s.charAt(idx) >= 'A' && s.charAt(idx) <= 'F') {
				if(s.charAt(idx) == 'A') isGen(s, idx, 1); 		// 다음 조건도 A로 시작하므로 인덱스 증가X
				else isGen(s, idx+1, 1);
			}
		} else if(type == 1) {
			int next = check(s, idx, 'A');
			if(next != -1) {
				isGen(s, next, 2);
			}
		} else if(type == 2) {
			int next = check(s, idx, 'F');
			if(next != -1) {
				isGen(s, next, 3);
			}
		} else if(type == 3) {
			int next = check(s, idx, 'C');
			if(next != -1) {
				isGen(s, next, 4);
			}
		} else {
			if(idx == s.length()) isOk = true;
			else if(idx == s.length()-1 && s.charAt(idx) >= 'A' && s.charAt(idx) <= 'F') {
				isOk = true;
			}
		}
	}
	public static int check(String s, int idx, char c) {
		int i = idx;
		while(i < s.length()) {
			if(s.charAt(i) != c) break;
			i++;
		}
		if(i > idx) {
			return i;
		}
		return -1;
	}

}
