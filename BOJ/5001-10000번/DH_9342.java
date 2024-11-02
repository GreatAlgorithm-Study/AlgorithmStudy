import java.io.*;

/*
 * 염색체
 */

public class DH_9342 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			String s = br.readLine();
			if(s.matches("^[ABCEDF]?A+F+C+[ABCDEF]?$")) {
				sb.append("Infected!\n");
			} else sb.append("Good\n");
		}

		System.out.println(sb);
	}
}