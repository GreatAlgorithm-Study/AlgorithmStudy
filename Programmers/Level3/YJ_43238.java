public class YJ_43238 {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        System.out.println(binarySearch(n,times));
    }

    static long binarySearch(int n, int[] times) {
        int length = times.length;
        long left = 0;
        long right = (long) times[length-1]*n;

        while(left<right){
            long mid = (left+right)/2;

            long count = 0;
            for(int time : times){
                count += mid/time;
            }

            if(count>=n){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return right;
    }
}
