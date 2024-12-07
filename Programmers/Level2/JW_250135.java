class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int now = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;
        // 시작부터 겹치고 시작할 경우
        if (now == 0 || now == 43200)
            answer++;
        while (now < end) {
            double[] nowAngles = calculateAndgles(now);
            double[] nextAngles = calculateAndgles(now + 1);
            boolean isHourMatched = chkHour(nowAngles, nextAngles);
            boolean isMinMatched = chkMin(nowAngles, nextAngles);
            if (isHourMatched)
                answer++;
            if (isMinMatched)
                answer++;
            // 둘 다 겹칠 경우에는 한번만 체크
            if (isHourMatched && isMinMatched)
                if (nextAngles[0] == nextAngles[1])
                    answer--;
            now++;
        }
        return answer;
    }

    private double[] calculateAndgles(int time) {
        double[] angles = new double[3];
        double h = time / 3600, m = time % 3600 / 60, s = time % 3600 % 60;
        // 시침의 현재 각도
        angles[0] = (h % 12) * (360d / 12) + m * (360d / 12 / 60) + s * (360d / 12 / 3600);
        // 분침의 현재 각도
        angles[1] = m * (360d / 60) + s * (360d / 60 / 60);
        // 초침의 현재 각도
        angles[2] = s * (360d / 60);
        return angles;
    }

    // 시침 겹침 확인
    private boolean chkHour(double[] now, double[] next) {
        double nowSec = now[2], nextSec = next[2];
        // 1초의 움직임 안에 시침이 포함되는가
        if (nowSec < now[0] && next[0] <= nextSec)
            return true;
        // 59초의 값은 따로 계산 → 원형이기에 각도가 0으로 초기화되기 때문
        if (nowSec == 354d && 354d < now[0])
            return true;
        return false;
    }

    // 분침 겹침 확인
    private boolean chkMin(double[] now, double[] next) {
        double nowSec = now[2], nextSec = next[2];
        // 1초의 움직임 안에 시침이 포함되는가
        if (nowSec < now[1] && next[1] <= nextSec)
            return true;
        // 59초의 값은 따로 계산 → 원형이기에 각도가 0으로 초기화되기 때문
        if (nowSec == 354d && 354d < now[1])
            return true;
        return false;
    }
}