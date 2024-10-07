import java.util.*;
import java.io.*;

class Student {
    int x;
    int y;
    int likeCount;
    int blanckCount;

    public Student(int x, int y, int likeCount, int blanckCount){
        this.x=x;
        this.y=y;
        this.likeCount=likeCount;
        this.blanckCount=blanckCount;
    }

    public boolean isBestPosition(Student best){
        //1. 4방향 탐색 "좋아하는 친구"의 수가 가장 많은 위치로 이동
        if(this.likeCount != best.likeCount){
            return this.likeCount > best.likeCount;
        }
        //2. 4방향을 탐색해서 비어있는 위치 카운팅
        else if(this.blanckCount != best.blanckCount){
            return this.blanckCount > best.blanckCount;
        }
        //3. 그 중 행 번호가 가장 작은 위치로 이동
        else if(this.x != best.x){
            return this.x < best.x;
        }
        //4. 그 중 열 번호가 가장 작은 위치로 이동
        return this.y < best.y;
    }

}

public class YJ_놀이기구_탑승 {
    static int[] numbers;  //학생들 번호
    static Map<Integer,List<Integer>> likeStudents = new HashMap<>();    //학생별 좋아하는 번호
    static int[][] ride;    //놀이기구 탑승
    static int n=0;
    static int TOTAL=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ride = new int[n][n];
        TOTAL = n*n;
        numbers = new int[TOTAL];

        //입력값 초기화
        for(int i=0; i<TOTAL; i++){
            String[] line = br.readLine().split("\\s");
            numbers[i] = Integer.parseInt(line[0]);

            //좋아하는 학생 4명
            List<Integer> likes = new ArrayList<>();
            for(int j=0; j<4; j++){
                likes.add(Integer.parseInt(line[j+1]));
            }
            likeStudents.put(numbers[i],likes);
        }

        for(int i=0; i<TOTAL; i++){
            //학생 1명을 모든 구간을 탐색하며 최우선 자리에 앉히기
            search(numbers[i], likeStudents.get(numbers[i]));
        }

        //점수판
        int score = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                score += calculateScore(i,j);
            }
        }
        System.out.println(score);
    }

    static void search(int number, List<Integer> likes){
        Student best = new Student(n,n,-1,-1);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(ride[i][j] != 0){
                    continue;
                }
                Student student = getCountByCondition(likes,i,j);
                if(student.isBestPosition(best)){
                    best = student;
                }
            }
        }
        ride[best.x][best.y] = number;
    }

    static int[] nx = {0,1,0,-1};
    static int[] ny = {1,0,-1,0};
    private static Student getCountByCondition(List<Integer> likes, int currentX, int currentY){
        int blanckCount=0;
        int likeCount=0;

        for(int i=0; i<4; i++){
            int x = currentX + nx[i];
            int y = currentY + ny[i];

            if(stop(x,y)){
                continue;
            }
            //2.비어있는 위치 카운팅
            if(ride[x][y] == 0){
                blanckCount++;
            }
            //1.좋아하는 친구 수 카운팅
            else if(likes.contains(ride[x][y])){
                likeCount++;
            }
        }
        return new Student(currentX,currentY,likeCount,blanckCount);
    }

    static int calculateScore(int currentX, int currentY){
        int friendCount = 0;
        List<Integer> likes = likeStudents.get(ride[currentX][currentY]);
        for(int p=0; p<4; p++){
            int x = currentX + nx[p];
            int y = currentY + ny[p];
            if(stop(x,y)){
                continue;
            }
            if(likes.contains(ride[x][y])){
                friendCount++;
            }
        }
        return getScore(friendCount);
    }

    private static int getScore(int friendCount){
        switch (friendCount){
            case 1:
                return 1;
            case 2:
                return 10;
            case 3:
                return 100;
            case 4:
                return 1000;
        }
        return 0;
    }

    private static boolean stop(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}