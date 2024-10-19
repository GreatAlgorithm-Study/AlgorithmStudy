import java.io.*;

public class YJ_4781 {
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = br.readLine();
            if("0 0.00".equals(line)){
                break;
            }
            String[] data = line.split("\\s");
            int n = Integer.parseInt(data[0]);
            int money = stringToInt(data[1]);

            dp = new int[money+1];
            for(int i=1; i<n+1; i++){
                String[] candy = br.readLine().split("\\s");
                int calorie = Integer.parseInt(candy[0]);
                int price = stringToInt(candy[1]);
                knapsack(calorie,price,money);
            }

            System.out.println(dp[money]);
        }
        br.close();
    }

    static void knapsack(int calorie,int price,int money){
        for (int m = price; m <= money; m++) {
            dp[m] = Math.max(dp[m], calorie + dp[m - price]);
        }
    }

    private static int stringToInt(String data) {
        return Integer.parseInt(data.replace(".",""));
    }
}
