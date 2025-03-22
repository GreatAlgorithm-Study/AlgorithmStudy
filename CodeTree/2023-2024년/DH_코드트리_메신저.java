import java.io.*;
import java.util.*;

public class DH_코드트리_메신저 {
	static final int MAX_DEPTH = 20;
	static int N, Q;
	static int[] p, authority, alarm;
	static int[][] tree;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
    public static boolean[] isOff;

	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("./input/코드트리메신저.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		authority = new int[N + 1];
		
		// tree[i][j]: i번 노드가 j 높이만큼 위에까지 전달할 수 있는 채팅의 수
		tree = new int[N + 1][MAX_DEPTH + 1]; 
		alarm = new int[N + 1];
		isOff = new boolean[N + 1];
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			
			int o = Integer.parseInt(st.nextToken());
			
			if(o == 100) step1(st);
			if(o == 200) step2(st);
			if(o == 300) step3(st);
			if(o == 400) step4(st);
			if(o == 500) step5(st);
			
		}
		
		System.out.println(sb);
	}
	
	// 400 
	// 채팅방 부모 바꾸기
	static void step4(StringTokenizer st) {
		
		// a, b 채팅방 부모 바꾸기
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
        boolean bef_noti1 = isOff[a];
        boolean bef_noti2 = isOff[b];


        // 알람망이 켜져 있다면 알림망 끄고, tree, alarm에서 a, b가 미치는 영향 없애주기
        if (!isOff[a]) toggle_noti(a); 
        if (!isOff[b]) toggle_noti(b);

        changeParent(a, b);
        
        // 알람망이 원래 켜져있는 상태였으면, tree, alarm 값 세팅해주기
        if (!bef_noti1) toggle_noti(a);
        if (!bef_noti2) toggle_noti(b);
	}
	
    // 채팅의 알림 상태를 토글합니다.
    public static void toggle_noti(int chat) {
    	
    	int mul = isOff[chat] ? 1: -1;
    	
        int parent = p[chat];
        int dis = 1;
        
        // 상위 채팅으로 이동하며 tree와 alarm값 수정하기
        while (parent != 0) {
        	
        	// dis만큼 위로 올라갈수록, parent도 p[p[...[parent]]]가 됨
        	alarm[parent] += tree[chat][dis] * mul;
        	
        	for(int i = dis + 1; i < MAX_DEPTH + 1; i++) {
        		// 위로 몇 칸을 가든, 바로 위에 있는 부모의 알람의 개수에 영향이 감
        		alarm[parent] += tree[chat][i] * mul; 
        		tree[parent][i - dis] += tree[chat][i] * mul;
        	}
        	
        	if(isOff[parent]) break; // 알람망이 꺼져 있으면 나가기
        	parent = p[parent];
            dis++;
        }
            
        isOff[chat] = !isOff[chat];
    }
	
	static void changeParent(int a, int b) {
		int tmp = p[a];
		p[a] = p[b];
		p[b] = tmp;
	}
	
	// 200
	// 채팅방 설정 바꿔주기
	static void step2(StringTokenizer st) {
		// current 채팅방 알림망 설정 바꿔주기
		int current = Integer.parseInt(st.nextToken());
		toggle_noti(current);
	}
	
	// 300
	// 권한 세기 변경
	static void step3(StringTokenizer st) {
		int n = Integer.parseInt(st.nextToken());
		int power = Math.min(Integer.parseInt(st.nextToken()), 20);
		
		int origin = authority[n];
		authority[n] = power;
		
		tree[n][origin]--;
		
        if (!isOff[n]) { // 알람망이 켜져있는 상태라면, 상위 채팅방에 대한 알람 개수도 수정하기
			int parent = p[n];
			int depth = 1;
			
			while(parent != 0) {
				if(origin >= depth) alarm[parent]--;
				if(origin > depth) tree[parent][origin - depth]--;
		        if(isOff[parent]) break;
				
				parent = p[parent];
				depth++;
			}
		}
		
		tree[n][power]++;
		
        if (!isOff[n]) {
			int parent = p[n];
			int depth = 1;
			
			while(parent != 0) {
				if(power >= depth) alarm[parent]++;
				if(power > depth) tree[parent][power - depth]++;
                if(isOff[parent]) break;
				
				parent = p[parent];
				depth++;
			}
		}
	}

	static void step5(StringTokenizer st) {
		int current = Integer.parseInt(st.nextToken());
		sb.append(alarm[current]).append("\n");
	}
	
	static void step1(StringTokenizer st) {
		
		for(int i = 1; i < N + 1; i++) {
			int parent = Integer.parseInt(st.nextToken());
			p[i] = parent;
		}
		
        for (int i = 1; i < N + 1; i++) {
        	
            authority[i] = Integer.parseInt(st.nextToken());
            if(authority[i] > 20) authority[i] = 20; // 권한의 세기가 20이 넘을 때를 주의해야 됨
            
            int current = i;
            
            for(int k = authority[i]; k > 0; k--) {
            	tree[current][k]++;
            	alarm[p[current]]++;
            	current = p[current];
            }
        }

	}

	static void printTreeInfo() {
		System.out.println("트리 정보 -------------------------");
		
		for(int r = 0; r < tree.length; r++) {
			System.out.println(r + ": " + Arrays.toString(tree[r]));
		}
	}
	
	static void printAlarmInfo() {
		System.out.println("알람 정보 ---------------------------");
		System.out.println(Arrays.toString(alarm));
		System.out.println();
	}
}
