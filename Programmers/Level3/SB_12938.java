class Solution {
    public int[] solution(int n, int s) {
        if (s<n) return new int[]{-1};
        
        int div = s/n;
        int rm = s%n;

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {       // 몫 분배
            ans[i] = div;
        }

        for (int i = 0; i < rm; i++) {      // 나머지 거꾸로 분배
            ans[n-1-i]++;
        }

        return ans;
    }
}