import java.util.*;

class Solution {
    static int K, N;
    static List<List<Integer>> tList;
    static List<Integer> cList;
    public int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE;
        
        K = k;
        N = n;
        
        // 1) 상담원 배분하기
        tList = new ArrayList<>();
        cList = new ArrayList<>();
        comb(0);
        
        // System.out.println(tList);
        
        // 2) 상담하기
        for(int i=0; i<tList.size(); i++) {
            int time = consult(tList.get(i), reqs);
            answer = Math.min(answer, time);
        }
        
        return answer;
    }
    public static void comb(int cnt) {
        if(cnt == K) {
            List<Integer> tmp = new ArrayList<>();
            int sum = 0;
            for(int i=0; i<cList.size(); i++) {
                tmp.add(cList.get(i));
                sum += cList.get(i);
            }
            if(sum == N) {
                tList.add(tmp);
            }
            return;
        }
        
        for(int i=1; i<N+1; i++) {
            cList.add(i);
            comb(cnt+1);
            cList.remove(cList.size()-1);
        }
    }
    public static int consult(List<Integer> kList, int[][] reqs) {
        // 각 유형별, 상담사별 끝나는 시간 저장
        PriorityQueue<Integer>[] prr = new PriorityQueue[K+1];
        for(int i=1; i<K+1; i++) {
            prr[i] = new PriorityQueue<>();
            
            // i 유형의 상담사 수만큼 끝나는 시간 추가하기 -> 초기값은 0
            for(int j=0; j<kList.get(i-1); j++){
                prr[i].add(0);
            }
        }
        // 참가자 반복
        int total = 0;
        for(int[] req: reqs) {
            int type = req[2];
            // 상담 끝난 상담자 poll
            // end: 이전 상담이 끝난 시간
            int end = prr[type].poll();
            // 기다린 시간
            int wait = 0;
            if(end > req[0]) wait = end - req[0];
            total += wait;
            
            // 새로운 상담 시작
            prr[type].add(wait + req[0] + req[1]);
        }
        
        return total;
        
    }
}