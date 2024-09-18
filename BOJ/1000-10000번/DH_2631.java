/*
 * 줄세우기
 */

public class DH_2631 {
	static int N;
	static int[] arr, cnt;
	
    public static void main(String[] args) throws Exception {
        N = read();
        	
        arr = new int[N]; 
        cnt = new int[N];
        
        int maxCnt = 0;
        for(int i = 0; i < N; i++) arr[i] = read();
        
        for(int i = 0; i < N; i++) {
        	cnt[i] = 1;
        	
        	for(int j = 0; j < i; j++) {
        		if(arr[i] < arr[j]) continue;
        		if(cnt[i] < cnt[j] + 1) {
        			cnt[i] = cnt[j] + 1;
        		}
        	}
        	
        	maxCnt = Math.max(maxCnt, cnt[i]);
        }
        
        System.out.println(N - maxCnt);
    }
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if(c == 13) System.in.read();
        return n;
    }
}