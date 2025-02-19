import java.io.*;
import java.util.*;

/*
 * 뉴스 전하기
 */

public class DH_1135 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] reverse = new int[N]; // 부모를 저장하는 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			
			if(p == -1) continue;
			reverse[i] = p; // 자신의 부모 저장
		}
		
		ArrayList<int[]> time[] = new ArrayList[N]; // 인접리스트 (노드, 걸리는 시간) 저장
		for(int i = 0; i < time.length; i++) time[i] = new ArrayList<>();
		
		int result = 0;
		
		for(int i = N - 1; i >= 0; i--) {
			// 현재 노드에 인접해있는 노드를 걸리는 시간 내림차순으로 정렬함
			Collections.sort(time[i], (o1, o2) -> Integer.compare(o2[1], o1[1]));
			
			int tmp = 0; // 현재 노드까지 걸리는 시간
			
			int childCnt = 1; // 자식의 수
			
			// 제일 오래 걸리는 시간 더해주기
			for(int[] child: time[i]) {
				tmp = Math.max(tmp, childCnt + child[1]);
				childCnt += 1;
			}
			
			// 인접리스트 만들어나가기
			time[reverse[i]].add(new int[] {i, tmp});
			result = Math.max(result, tmp);
		}
		
		System.out.println(result);
	}
}
