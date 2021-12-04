package week34.Programmers_LV2_행렬테두리회전하기;
import java.util.*;

class Solution {
    static int[][] delta = {
        {0,1},
        {1,0},
        {0,-1},
        {-1,0}
    };
    static int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int times = queries.length;
        
        int[] answer = new int[times];
        
        map = new int[rows+1][columns+1];
        
        for(int i = 1; i <= rows; i++){
            for(int j = 1; j <= columns; j++){
                map[i][j] =  (i-1)*columns + j;
            }
        }
        
        for(int t = 0; t < times; t++){
            int yStart = queries[t][0];
            int xStart = queries[t][1];
            int yEnd = queries[t][2];
            int xEnd = queries[t][3];
            
            int y = yStart;
            int x = xStart;
            int dir = 0;
            
            int yRange = yEnd - yStart;
            int xRange = xEnd - xStart;
            int yCount = 0;
            int xCount = 0;
            int nextNum = map[y][x];
            int min = nextNum;
            
            //방향은 3번만 바뀜
            while(dir < 4){              
                if(dir == 0 || dir == 2){
                    xCount++;
                }
                else if(dir == 1 || dir == 3){
                    yCount++;
                }
                
                if(dir == 4)    break;
                
                int ny = y + delta[dir][0];
                int nx = x + delta[dir][1];
                
                int temp = map[ny][nx];
                
                map[ny][nx] = nextNum;
                nextNum = temp;
                
                min = Math.min(min, nextNum);
                
                y = ny;
                x = nx;
                
                //방향을 바꾸는 경우
                if(xCount == xRange){
                    xCount = 0;
                    dir++;
                }
                else if(yCount == yRange){
                    yCount = 0;
                    dir++;
                }
            }
            
            //가장 작은 수 저장
            answer[t] = min;
        }
        
        return answer;
    }
}
