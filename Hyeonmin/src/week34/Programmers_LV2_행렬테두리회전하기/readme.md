## Programmers LV2 행렬 테두리 회전하기
- LV2
- 2021 Dev-Matching: 웹 백엔드 개발자(상반기)

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/77485#

ows x columns 크기인 행렬이 있습니다. 행렬에는 1부터 rows x columns까지의 숫자가 한 줄씩 순서대로 적혀있습니다. 이 행렬에서 직사각형 모양의 범위를 여러 번 선택해, 테두리 부분에 있는 숫자들을 시계방향으로 회전시키려 합니다. 각 회전은 (x1, y1, x2, y2)인 정수 4개로 표현하며, 그 의미는 다음과 같습니다.

x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전합니다.

행렬의 세로 길이(행 개수) rows, 가로 길이(열 개수) columns, 그리고 회전들의 목록 queries가 주어질 때, 각 회전들을 배열에 적용한 뒤, 그 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.


#### 제한사항
rows는 2 이상 100 이하인 자연수입니다.
columns는 2 이상 100 이하인 자연수입니다.
처음에 행렬에는 가로 방향으로 숫자가 1부터 하나씩 증가하면서 적혀있습니다.
즉, 아무 회전도 하지 않았을 때, i 행 j 열에 있는 숫자는 ((i-1) x columns + j)입니다.
queries의 행의 개수(회전의 개수)는 1 이상 10,000 이하입니다.
queries의 각 행은 4개의 정수 [x1, y1, x2, y2]입니다.
x1 행 y1 열부터 x2 행 y2 열까지 영역의 테두리를 시계방향으로 회전한다는 뜻입니다.
1 ≤ x1 < x2 ≤ rows, 1 ≤ y1 < y2 ≤ columns입니다.
모든 회전은 순서대로 이루어집니다.
예를 들어, 두 번째 회전에 대한 답은 첫 번째 회전을 실행한 다음, 그 상태에서 두 번째 회전을 실행했을 때 이동한 숫자 중 최솟값을 구하면 됩니다.

###  💡 풀이

`queries`의 길이 만큼 반복문을 실행해서 풀이한다.

y, x축 별로 길이를 구하고 그 길이를 한 방향에 대해서 그 길이 만큼만 이동한다. 길이를 넘어가는 경우 방향을 변경한다.
방향 변경이 4번째 이루어지는 순간 while문을 종료한다

while문의 각 실행마다 가장 작은 숫자 `min`을 구해 `answer[t]`에 저장한다



<br><br>

###  💡 소스코드
```java
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





```


<br>



정확성  테스트
테스트 1 〉	통과 (0.03ms, 76.2MB)
테스트 2 〉	통과 (0.09ms, 76.4MB)
테스트 3 〉	통과 (13.66ms, 84.3MB)
테스트 4 〉	통과 (13.30ms, 86.8MB)
테스트 5 〉	통과 (13.80ms, 99MB)
테스트 6 〉	통과 (16.73ms, 91.3MB)
테스트 7 〉	통과 (22.78ms, 93.9MB)
테스트 8 〉	통과 (14.26ms, 93.6MB)
테스트 9 〉	통과 (14.48ms, 97.2MB)
테스트 10 〉	통과 (13.79ms, 92.5MB)
테스트 11 〉	통과 (19.64ms, 83.8MB)