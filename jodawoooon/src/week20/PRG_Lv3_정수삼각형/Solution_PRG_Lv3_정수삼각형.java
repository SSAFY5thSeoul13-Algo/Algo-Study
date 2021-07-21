package week20.PRG_Lv3_정수삼각형;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;
        //거쳐간 숫자의 최댓값을 return


        for(int i = 1 ; i < N ; i++){
            for(int j = 0 ; j <= i ; j++){
                
                if(j==0) triangle[i][j] += triangle[i-1][j];
                //맨 왼쪽 열
                
                else if(j==i) triangle[i][j] += triangle[i-1][j-1];
                //맨 오른쪽 열
                
                else triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
            }
        }
        
        
        for(int i=0; i<N ; i++){
            answer = Math.max(triangle[N-1][i], answer);
        }
        
        return answer;
    }
}