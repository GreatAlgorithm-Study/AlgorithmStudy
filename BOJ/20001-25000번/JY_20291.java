import java.io.*;
import java.util.*;

public class JY_20291 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Map<String, Integer> fMap = new TreeMap<>();
		
		int N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			String[] fName = br.readLine().split("\\.");
			fMap.put(fName[1], fMap.getOrDefault(fName[1], 0)+1);
		}
		
		for(String k : fMap.keySet()) {
			System.out.println(k+" "+fMap.get(k));
		}
	}

}
