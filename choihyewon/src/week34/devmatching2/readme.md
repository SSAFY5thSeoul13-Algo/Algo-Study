## Programmers - 행렬 테두리 회전하기
- Implementation
- Level 2
- 2021 Dev-Matching: 웹 백엔드 개발자(상반기)
-[행렬 테두리 회전하기 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/77485)

## 풀이

먼저 rows X columns 크기의 배열을 만들어주어 순서대로 점점 1씩 커지는 숫자를 넣어주었습니다.
그리고 배열의 x1,y1에 위치한 값은 tmp에 넣어주고 각각 회전을 시켜주었습니다.
회전을 하면서 Math.min을 통해 회전하는 수 중 최솟값을 구해주었습니다.
그리고 tmp값은  x1,y1+1 위치에 넣어주어 회전을 완료하였고 순서대로 최솟값을 구해주어 답을 구하였습니다.

## 소스코드
~~~java
import java.util.*;
class Solution {
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
~~~

## 결과

|정확성|테스트|
|-----|-----|
|테스트 1 |	통과 (0.03ms, 78.7MB)|
|테스트 2 |	통과 (0.03ms, 76.1MB)|
|테스트 3 |	통과 (11.91ms, 96.7MB)|
|테스트 4 |	통과 (8.90ms, 84MB)|
|테스트 5 |	통과 (15.54ms, 91.5MB)|
|테스트 6 |	통과 (10.79ms, 98MB)|
|테스트 7 |	통과 (14.17ms, 90.3MB)|
|테스트 8 |	통과 (9.36ms, 90.4MB)|
|테스트 9 |	통과 (12.33ms, 95.4MB)|
|테스트 10 |	통과 (9.68ms, 89MB)|
|테스트 11 |	통과 (8.85ms, 77.4MB)|