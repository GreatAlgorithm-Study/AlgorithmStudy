public class YJ_64062 {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(new YJ_64062().solution(stones,k));
    }

    public int solution(int[] stones, int k) {
        int friends = 0;

        int left = 1;
        int right = 200_000_000;    //★친구들의 수를 최대값으로 지정
        while(left < right){
            int mid = (left + right)/2;

            //더 많은 친구들이 다리를 건널 수 있는지
            if(canGo(mid,stones,k)){
                left = mid+1;
                friends = Math.max(friends, mid);
            }else{
                //친구들이 다리를 건널 수 없다면 숫자를 줄이기
                right = mid;
            }
        }

        return friends;
    }

    boolean canGo(int friends, int[] stones, int k){
        int count = 0;
        for(int stone : stones){
            //한명씩 비교할 필요XXXXX ★하나의 돌다리에 친구 수만큼 올라갈 수 있는지만 판단★
            if(stone - friends < 0){
                count++;
                if(count == k){
                    return false;
                }
                continue;
            }
            count = 0;
        }
        return true;
    }
}
