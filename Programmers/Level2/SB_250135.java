class SB_250135 {
    private static boolean overlapHour(Time cur, Time nxt) {
        if(cur.dh > cur.ds && nxt.dh <=nxt.ds) return true;
        return cur.ds == 354 && cur.dh > 354;
    }
    private static boolean overlapMin(Time cur, Time nxt) {
        if(cur.dm > cur.ds && nxt.dm <=nxt.ds) return true;
        return cur.ds == 354 && cur.dm > 354;               // 초침이 정각으로 갈때 0도 처리
    }
    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startTime = new Time(h1, m1, s1).getSecond();
        int endTime = new Time(h2, m2, s2).getSecond();

        int cnt = 0;
        for (int i = startTime; i < endTime; i++) {
            Time cur = new Time(i);
            Time nxt = new Time(i + 1);

            boolean isMinRing = overlapMin(cur, nxt);
            boolean isHourRing = overlapHour(cur, nxt);

            if (isMinRing && isHourRing){       // 시침 분침 모두 겹쳤을때
                if (nxt.dh== nxt.dm) cnt++;     // 시침분침의 위치가 같으면 한번만 카운트
                else cnt+=2;
            } else if (isMinRing || isHourRing) cnt++;
        }
        if (startTime==0 || startTime==43200) cnt++;    // 예외처리: 처음에 시분초 모두 같이 있을 경우(정오, 정각)
        return cnt;
    }

    static class Time{
        int h, m, s;
        double dh, dm, ds;

        public Time(int h, int m, int s) {
            this.h = h;
            this.m = m;
            this.s = s;
            setDegrees();
        }

        public Time(int s) {
            this.h = s / 3600;
            this.m = (s % 3600) / 60;
            this.s = (s % 3600) % 60;
            setDegrees();
        }


        private int getSecond() {
            return h * 3600 + m * 60 + s;
        }

        private void setDegrees() {
            this.dh = (h % 12) * 30d + m * 0.5 + s * (0.5 / 60);
            this.dm = m * 6 + s * 0.1;
            this.ds = s * 6;
        }
    }
}