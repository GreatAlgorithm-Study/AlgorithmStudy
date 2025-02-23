class JW_389479 {
    public int solution(int[] players, int m, int k) {
        int n = players.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            int needs = (int) Math.floor((double) players[i] / m); // 증설해야하는 서버 수
            if (needs == 0)
                continue;
            total += needs; // 증설 횟수 누적
            // k시간 동안 유지 가능
            for (int j = 0; j < k && i + j < n; j++)
                players[i + j] = Math.max(0, players[i + j] - m * needs);
        }
        return total;
    }
}
