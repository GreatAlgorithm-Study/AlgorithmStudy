import java.io.*;
import java.util.*;

public class JY_1992 {

	static int N;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				graph[i][j] = row[j] - '0';
			}
		}
		div(0, 0, N);
	}

	public static void div(int sx, int sy, int size) {
		int sum = 0;
		for (int i = sx; i < sx + size; i++) {
			for (int j = sy; j < sy + size; j++) {
				sum += graph[i][j];
			}
		}
		if (sum == 0) {
			System.out.print(0);
		} else if (sum == size * size) {
			System.out.print(1);
		} else {
			int half = size / 2;
			System.out.print("(");
			div(sx, sy, half);
			div(sx, sy+half, half);
			div(sx+half, sy, half);
			div(sx+half, sy+half, half);
			System.out.print(")");
		}
	}

}