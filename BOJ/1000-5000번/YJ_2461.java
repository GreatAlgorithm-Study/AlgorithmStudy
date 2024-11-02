import java.io.*;
import java.util.*;

public class YJ_2461 {
    static class Student implements Comparable<Student>{
        int index;
        int power;
        Student (int index, int power){
            this.index = index;
            this.power = power;
        }

        @Override
        public int compareTo(Student o){
            return this.power - o.power;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] students = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                students[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(students[i]);
        }


        PriorityQueue<Student> pq = new PriorityQueue<>();
        int max = 0;
        for(int i=0; i<N; i++){
            pq.offer(new Student(i,students[i][0]));
            max = Math.max(max, students[i][0]);
        }

        int[] idxTable = new int[N];
        int result = Integer.MAX_VALUE;
        while(true){    //특정 구간을 반복해야 할때 while 과 구간을 움직일 포인트 지정하기
            //능력치 최소학생 뽑기
            Student student = pq.poll();
            result = Math.min(result, max- student.power);
            if(++idxTable[student.index] >= M){
                break;
            }
            //능력치가 최소인 학생의 반에서 다음 학생의 능력치
            int power = students[student.index][idxTable[student.index]];
            pq.offer(new Student(student.index,power));
            max = Math.max(max,power);
        }

        System.out.println(result);
    }
}
