/***
 * 알고리즘: 일반구현
 * 시간복잡도: N <= 100,000 크기로 O(NlogN)까지 가능
 * 아이디어:
 * 요구사항은 이전의 행과 같은 열을 밝지 않고, 각 행의 열의 합이 최고점 찾기
 * 이전의 행과 현재의 행을 비교하는데, 이전 행의 열들 중 현재 행과 같은 열을 제외하고 최고점을 찾는다
 */
public class YJ_땅따먹기 {
    public static void main(String[] args) {
        int[][] land ={{1,2,3,5},{5,6,7,8},{4,3,2,1}};
        int answer = 0;

        // 이전 행의 열들 중 현재 행과 같은 열을 제외하고 최고점 (현재 행의 열: 0 , 이전행의 열: 1,2,3)
        // 각 행마다 이전 행의 최고점 열을 누적해서 더하기
        for(int i=1; i<land.length; i++){
            land[i][0] += maxLand(land[i-1][1],land[i-1][2],land[i-1][3]);
            land[i][1] += maxLand(land[i-1][0],land[i-1][2],land[i-1][3]);
            land[i][2] += maxLand(land[i-1][0],land[i-1][1],land[i-1][3]);
            land[i][3] += maxLand(land[i-1][0],land[i-1][1],land[i-1][2]);
        }

        // 마지막 행에 모든 행들의 누적 최고점 열이 있음
        int maxScore = 0;
        for(int i=0; i<land[0].length; i++){
            maxScore = Math.max(maxScore,land[land.length-1][i]);
        }
        System.out.println(answer);
    }

    private static int maxLand(int first, int second, int third){
        return Math.max(Math.max(first,second),third);
    }
}
