package day0909;

import java.util.*;
import java.io.*;
public class JY_불안한_무빙워크 {
    
    static int N, K;
    static Block[] urr;
    static Block[] brr;
    static class Block {
    	int st;
    	boolean visited;
    	public Block(int st, boolean visited) {
    		this.st = st;
    		this.visited = visited;
    	}
    	@Override
    	public String toString() {
    		return "s:"+this.st+" "+this.visited;
    	}
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        urr = new Block[100];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            urr[i] = new Block(Integer.parseInt(st.nextToken()), false);
        }
        brr = new Block[100];
        for(int i=0; i<N; i++){
        	brr[i] = new Block(Integer.parseInt(st.nextToken()), false);
        }
        
//        System.out.println(Arrays.toString(urr));
//        System.out.println(Arrays.toString(brr));
        
        int test = 0;
        while(!done()) {
        	game();
        	test++;
        }
        
        System.out.println(test);
        
    }
    public static boolean done() {
    	int fail = 0;
    	for(int i=0; i<N; i++) {
    		if(urr[i].st == 0) fail++;
    		if(brr[i].st == 0) fail++;
    	}
    	return fail >= K;
    }
    public static void game() {
    	// 무빙워크 회전
    	shift();
    	
    	// 사람 이동 + 안정성 처리
    	moveAll();
    	
    	// 사람 증가 + 안정성 처리
    	add();
    	
    	// N번째 칸에 사람있으면 내림
    	if(urr[N-1].visited) {
    		move(N-1);
    	}
    }
    public static void shift() {
    	Block tmp = urr[N-1];
    	for(int i=N-1; i>= 1; i--) {
    		urr[i] = urr[i-1];
    	}
    	urr[0] = brr[N-1];
    	
    	for(int i=N-1; i>=1; i--) {
    		brr[i] = brr[i-1];
    	}
    	brr[0] = tmp;
    }
    public static void add() {
    	// 안정성 0이상, 시작칸에 사람 없는 경우
    	if(urr[0].st > 0 && !urr[0].visited) {
//    		urr[0].st--;
//    		urr[0].visited = true;
    		urr[0] = new Block(urr[0].st-1, true);
    	}
    }
    public static boolean canGo(int idx) {
    	// N이상이면 밖으로 나감
    	if(idx == N) return true;

    	return urr[idx].st > 0 && !urr[idx].visited;
    }
    public static void move(int idx) {
    	// idx 위치의 사람 사라짐
    	urr[idx] = new Block(urr[idx].st, false);
    	
    	// 밖으로 나가는 것이 아니면 다음 칸에 사람 추가
    	if(idx + 1 < N) {
    		urr[idx+1] = new Block(urr[idx+1].st - 1, true);
    	}
    }
    public static void moveAll() {
    	// 사람이 있고, 그 다음 위치로 이동 가능한 경우
    	for(int i=N-1; i>=0 ;i--) {
    		if(urr[i].visited && canGo(i+1)) {
    			move(i);
    		}
    	}
    }
}
