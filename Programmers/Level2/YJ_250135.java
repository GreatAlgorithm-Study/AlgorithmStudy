public class YJ_250135 {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = getAlarms(h2,m2,s2)-getAlarms(h1,m1,s1);
        return s1==0 && m1==0? answer+1 : answer;
    }

    int getAlarms(int h, int m, int s){
        int alarms = 0;

        int mCount = h * (60-1) + m;    //1시간에 59번(60분 제외) + 1분당 1번
        int hCount = h * 60 + m;
        if(h>=12) {
            hCount--;   //24시인 경우 -1
        }


        //초침과 분침이 겹칠 경우
        if(s*6 >= m*6 + s*0.1){ // 초침의 각도 = s * 360/60 , 분침의 각도 = m * 360/60 + s * 360/(60*60)
            mCount++;
        }
        //초침과 시침이 겹칠 경우
        if(30*(h%12) + 0.5*m + s * ((double) 1 / 120) <= s*6){  // 시침의 각도 = (h%12) * 360/12 + m * 360/(12*60) + s * 360 / (12*60*60)
            hCount++;
        }

        alarms = mCount + hCount;
        return h>=12? alarms-1 : alarms;
    }
}
