import java.io.*;
import java.util.Arrays;

public class YJ_2805 {
    static int[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        trees = new int[N];
        String[] t = br.readLine().split("\\s");
        for(int i=0; i<t.length; i++){
            trees[i] = Integer.parseInt(t[i]);
        }

        Arrays.sort(trees);
        System.out.println(binarySearch(M));
    }

    static int binarySearch(int M){
        int left = 0;
        int right = trees[trees.length-1];

        while(left <= right){
            int mid = (left+right)/2;
            long sum = 0;

            for(int tree : trees){
                int cutting = tree - mid;
                if(cutting > 0){
                    sum += cutting;
                }
            }

            if(sum >= M){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        return right;
    }
}
