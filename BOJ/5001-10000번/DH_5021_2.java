import java.io.*;
import java.util.*;

/*
 * 왕위 계승
 */

public class DH_5021_2 {
	
	static HashMap<String, Integer> nameToIdx = new HashMap<String, Integer>();
	static int[] indegree; // 진입차수 배열
	static double[] power; // 혈통 점수
	static int idx;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/BOJ5021.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 가족 정보
		int M = Integer.parseInt(st.nextToken()); // 왕위 계승 주장 사람 정보
		
		// 가족 정보가 N개이기 때문에, 사람은 최대 3N명까지 나올 수 있음!
		indegree = new int[3 * N];
		power = new double[3 * N];
		
		// 나라를 세운 사람 정보
		String s = br.readLine();
		nameToIdx.put(s, idx);
		power[idx] = 1;
		idx += 1;
		
		// 인접 리스트 저장
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 3 * N; i++) adj.add(new ArrayList<Integer>());
		
		// 입력받는 가족 정보를 기반으로 인접 리스트 & 진입차수 배열 만들기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String s1 = st.nextToken(); // 자식
			String s2 = st.nextToken(); // 부모 1
			String s3 = st.nextToken(); // 부모 2
			
			putName(s1);
			putName(s2);
			putName(s3);
			
			indegree[nameToIdx.get(s1)] += 2;
			adj.get(nameToIdx.get(s2)).add(nameToIdx.get(s1));
			adj.get(nameToIdx.get(s3)).add(nameToIdx.get(s1));
		}
		
		// 위상정렬을 하기 위해 사용하는 큐
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		// 진입차수 배열이 0인 것들만 우선 큐에 넣어줌
		for(String key: nameToIdx.keySet()) {
			int idx = nameToIdx.get(key);
			
			if(indegree[idx] != 0) continue;
			q.add(idx);
			
			// 혈통점수가 0이면서 들어오는 간선이 없는 경우는 왕족이 아닌 사람이므로
			// 혈통점수를 0.5로 바꿔주기
			if(power[idx] == 0) power[idx] = 0.5;
		}

		// 위상정렬
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: adj.get(current)) {
				// 진입차수 배열을 하나씩 없애기
				indegree[next]--;
				
				// 연결된 노드들의 혈통 점수 계산해주기
				power[next] += power[current] / 2;
				
				// 진입차수가 0이 된다면 큐에 넣어주기
				if(indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		
		double maxPower = 0;
		String result = null;
		
		// 왕위계승을 주장하는 사람들 중에서 혈통 점수가 가장 큰 사람 이름 출력하기
		for(int i = 0; i < M; i++) {
			String name = br.readLine();
			
			int key = nameToIdx.getOrDefault(name, -1);
			
			if(key == -1) continue;
			if(maxPower < power[key]) {
				maxPower = power[key];
				result = name;
			}
		}
		
		System.out.println(result);
	}
	
	static void putName(String s) {
		if(!nameToIdx.containsKey(s)) nameToIdx.put(s, idx++);
	}
}
