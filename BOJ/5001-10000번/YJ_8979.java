import java.io.*;
import java.util.*;

public class YJ_8979 {
    static class Nation implements Comparable<Nation> {
        int name;
        int gold;
        int silver;
        int bronze;

        public Nation(int name, int gold, int silver, int bronze) {
            this.name = name;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Nation o) {
            if(this.gold == o.gold){
                if(this.silver == o.silver){
                    return o.bronze - this.bronze;
                }
                return o.silver - this.silver;
            }
            return o.gold - this.gold;
        }
    }

    static PriorityQueue<Nation> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int nation = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            pq.offer(new Nation(nation, gold, silver, bronze));
        }

        System.out.println(findRank(K));
    }

    static int findRank(int K){
        int rank = 1;
        Nation prev = pq.poll();
        if(prev.name == K){
            return rank;
        }

        int same = 0;
        while(!pq.isEmpty()){
            Nation nation = pq.poll();
            //동점일 경우
            if(prev.gold == nation.gold && prev.silver == nation.silver && prev.bronze == nation.bronze){
                same++;
            }else {
                rank++;
                rank += same;
                same = 0;
            }

            if(nation.name == K){
                break;
            }
            prev = nation;
        }
        return rank;
    }
}
