public class YJ_150369 {
    public static void main(String[] args) {}

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int delivery = 0;
        int pickup = 0;
        long total = 0;

        for(int i =n-1; i>=0; i--){
            delivery += deliveries[i];
            pickup += pickups[i];
            //배달이나 수거할게 있을 경우
            while (delivery > 0 || pickup > 0){
                //배달 or 수거를 다 할때 까지 최대 용량 cap 만큼 더하기
                delivery -= cap;
                pickup -= cap;
                //이때 현재 위치까지 왔다갔다는 뜻으로 현재위치*2 왕복 계산하기
                //왔다간 이유는 한번 최대용량 만큼 충전하면 이후 남은 집들도 들리면서 배달 or 수거를 하기 때문
                total += (i+1)*2;
            }
        }

        return total;
    }
}