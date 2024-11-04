class JW_12913 {
    int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        for (int i = 1; i < n; i++)
            for (int j = 0; j < 4; j++)
                land[i][j] += Math.max(land[i - 1][(j + 1) % 4],
                        Math.max(land[i - 1][(j + 2) % 4], land[i - 1][(j + 3) % 4]));
        answer = Math.max(land[n - 1][0], Math.max(land[n - 1][1], Math.max(land[n - 1][2], land[n - 1][3])));
        return answer;
    }
}