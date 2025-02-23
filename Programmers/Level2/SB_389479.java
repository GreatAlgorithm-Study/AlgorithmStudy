class Solution {
    public int solution(int[] players, int m, int k) {
        int[] servers = new int[24];
        int cnt = 0;

        for (int cur = 0; cur < 24; cur++) {
            if (players[cur] / m > servers[cur]) {
                int need = (players[cur] / m) - servers[cur];

                // k시간 동안 서버 증설
                for (int i = 0; i < k; i++) {
                    if (cur + i < 24) {
                        servers[cur+i] += need;
                    }
                }
                cnt += need;
            }
        }
        return cnt;
    }
}