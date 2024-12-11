import java.io.*;
import java.util.*;

/*
 * 파일 정리
 */

public class DH_20291 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		TreeMap<String, Integer> treeMap = new TreeMap<>();
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine().split("\\.")[1];
			if(treeMap.containsKey(s)) treeMap.put(s, treeMap.get(s) + 1);
			else treeMap.put(s, 1);
		}
		
		StringBuilder sb = new StringBuilder();
		for(String key: treeMap.keySet()) {
			sb.append(key).append(" ").append(treeMap.get(key)).append("\n");
		}
		
		System.out.println(sb);
	}
}
