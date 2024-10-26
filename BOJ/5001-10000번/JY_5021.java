package day1022;

import java.io.*;
import java.util.*;

public class JY_5021 {
	
	static int N, M;
	static String king;
	static Map<String, List<String>> fMap;
	static Map<String, Integer> indegree;
	static Map<String, Double> score;
	static List<String> cList;
	static List<String> ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		fMap = new HashMap<>();
		indegree = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		king = st.nextToken();
		fMap.put(king, new ArrayList<>());
		indegree.put(king, 0);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String p1 = st.nextToken();
			String p2 = st.nextToken();
			
			if(fMap.get(p1) == null) {
				fMap.put(p1, new ArrayList<>());
			}
			if(fMap.get(p2) == null) {				
				fMap.put(p2, new ArrayList<>());
			}
			if(fMap.get(child) == null) {
				fMap.put(child, new ArrayList<>());				
			}
			// 자식 추가
			fMap.get(p1).add(child);
			fMap.get(p2).add(child);
			
			// 진입차수 설정
			indegree.put(p1, indegree.getOrDefault(p1, 0));
			indegree.put(p2, indegree.getOrDefault(p2, 0));
			indegree.put(child, indegree.getOrDefault(child, 0)+2);
			  
		}
		
		cList = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			cList.add(st.nextToken());
		}
		
//		System.out.println(fMap);
//		System.out.println(indegree);
//		System.out.println(cList);
		
		ans = new ArrayList<>();
		score = new HashMap<>();
		topologySort();
		
//		System.out.println(ans);
//		System.out.println(score);
		
		// 후계자 찾기
		String candidate = "";
		double s = 0.0;
		for(int i=0; i<ans.size(); i++) {
			String k = ans.get(i);
			for(String c: cList) {
				if(c.equals(k) && s < score.get(k)) {
					candidate = c;
					s = score.get(k);
					break;
				}
			}
		}
		
		System.out.println(candidate);
		
	}
	public static void topologySort() {
		Queue<String> q = new LinkedList<>();
		
		// 진입차수가 0인 노드 삽입
		q.add(king);
		for(String key: indegree.keySet()) {
			if(key.equals(king)) {
				score.put(key, 1.0);
				continue;
			}
			if(indegree.get(key) == 0) {
				q.add(key);
				score.put(key, 0.5);
			}
		}
		
		while(!q.isEmpty()) {
			String now = q.poll();
			ans.add(now);
			for(String c: fMap.get(now)) {
				indegree.put(c, indegree.get(c)-1);
				score.put(c, score.getOrDefault(c, 0.0)+ score.get(now)*0.5);
				if(indegree.get(c) == 0) {
					q.add(c);
				}
			}
		}

	}

}
