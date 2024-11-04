import java.util.*;

class JY_214288 {
    
    static int N, K;
    static List<int[]> kList;
    
    public int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE;
        N = n;
        K = k;
        
        kList = new ArrayList<>();
        // 상담원 분배: 중복 순열
        int[] krr = new int[K+1];
        makeList(1, 0, krr);
        
        // 상담 진행
        for(int[] c: kList) {
            answer = Math.min(answer, consult(c, reqs));
        }
        
        return answer;
    }
    public static void makeList(int depth, int total, int[] krr) {
        if(depth == K+1) {
            if(total == N) {
                // System.out.println(Arrays.toString(krr));
                // 배열 복사
                int[] nKrr = Arrays.copyOf(krr, krr.length);
                kList.add(nKrr);
            }
            return;
        }
        
        for(int i=1; i<N+1; i++) {
            krr[depth] = i;
            makeList(depth+1, total+i, krr);
        }
    }
    public static int consult(int[] krr, int[][] reqs) {
        PriorityQueue<Integer>[] mento = new PriorityQueue[K+1];
        
        // 유형별로 우선순위 큐를 사용해 가장 상담이 빨리 끝나는 상담원을 출력
        // 각 타입에 분배한 멘토인원(krr)만큼 
        // 각 멘토의 끝나는 시간 추가, 초기값은 모두 0
        for(int i=1; i<K+1; i++) {
            mento[i] = new PriorityQueue<>();
            for(int j=0; j<krr[i]; j++){
                mento[i].add(0);
            }
            
        }
        
        int total = 0;
        for(int[] req: reqs) {
            int type = req[2];
            int endTime = mento[type].poll();
            // 기다린 시간 (기다리자 않았다면 0)
            int waitTime = Math.max(0, endTime - req[0]);
            total += waitTime;
            // 현재 상담자 req의 상담이 끝나는 시간 기록
            mento[type].add(waitTime + req[0] + req[1]);
        }
        
        return total;
    }
}