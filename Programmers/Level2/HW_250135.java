class HW_250135 {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int cnt = 0;

        double start = h1 * 3600 + m1 * 60 + s1;
        double end = h2 * 3600 + m2 * 60 + s2;

        int halfDay = 12 * 3600;
        if (start == 0 || start == halfDay)
            cnt++;

        while (start < end) {
            double h = (start / 120) % 360;  // 시침
            double m = (start / 10) % 360;   // 분침
            double s = (start * 6) % 360;    // 초침

            // 다음 시간의 각도 계산
            double nextH = ((start + 1) / 120) % 360;
            double nextM = ((start + 1) / 10) % 360;
            double nextS = ((start + 1) * 6) % 360;

            // 360도 -> 0도
            if (nextH == 0.0) {
                nextH = 360.0;
            }
            if (nextM == 0.0){
                nextM = 360.0;
            }

            if (nextS == 0.0) {
                nextS = 360.0;
            }

            if (s < h && nextH <= nextS)  // 초침 == 시침
                cnt++;

            if (s < m && nextM <= nextS)  // 초침==분침
                cnt++;

            if (nextH == nextS && nextM == nextS)  // 시침,분침==초침
                cnt--;

            start++;
        }
        return cnt;
    }
}