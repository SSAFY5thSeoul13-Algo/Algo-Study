package week34.devmatching2;

import java.util.*;

public class 행렬_테두리_회전하기 {
	public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows+1][columns+1];
        int num = 1;
        
        // 행렬에 1부터 rows x columns까지의 숫자를 한 줄씩 순서대로 넣는다.
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                arr[i][j] = num++;
            }
        }
        
        for(int i=0; i<queries.length; i++){
            int r1 = queries[i][0];
            int c1 = queries[i][1];
            int r2 = queries[i][2];
            int c2 = queries[i][3];
            int tmp = arr[r1][c1];
            int min = tmp;
            
            for(int r=r1+1; r<=r2; r++){
                min = Math.min(min,arr[r][c1]);
                arr[r-1][c1] = arr[r][c1];
            }
            
            for(int c=c1+1; c<=c2; c++){
                min = Math.min(min,arr[r2][c]);
                arr[r2][c-1] = arr[r2][c];
            }
            
            for(int r=r2-1; r>=r1; r--){
                min = Math.min(min,arr[r][c2]);
                arr[r+1][c2] = arr[r][c2];
            }
            
            for(int c=c2-1; c>=c1; c--){
                min = Math.min(min,arr[r1][c]);
                arr[r1][c+1] = arr[r1][c];
            }
            arr[r1][c1+1] = tmp;
            answer[i] = min;
            
        }
        return answer;
    }

}
