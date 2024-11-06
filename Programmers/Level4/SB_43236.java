import java.util.Arrays;

public class SB_43236 {
    private int removeCnt(int x, int[] rocks, int arrive) {     // x가 돌 사이간 최소거리가되려면 몇개의 돌을 지워야하는지
        int pre = 0;
        int remove = 0;
        for(int i=0; i<rocks.length; i++){
            if(rocks[i]-pre < x){
                remove++;
                continue;
            }
            pre = rocks[i];
        }
        if (arrive-pre < x) remove++;
        
        return remove;
    }
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        
        int ans = 0;
        while(left <=right){
            int mid = (left+right)/2;
            if(removeCnt(mid, rocks, distance) <= n){       // mid가 가능하면 길이 키워보기
                ans = Math.max(ans, mid);
                left = mid+1;
            } else right = mid-1;
        }
        return ans;
    }
}
