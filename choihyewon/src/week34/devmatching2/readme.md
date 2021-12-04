## Programmers - ��� �׵θ� ȸ���ϱ�
- Implementation
- Level 2
- 2021 Dev-Matching: �� �鿣�� ������(��ݱ�)
-[��� �׵θ� ȸ���ϱ� ���� �ٷΰ���](https://programmers.co.kr/learn/courses/30/lessons/77485)

## Ǯ��

���� rows X columns ũ���� �迭�� ������־� ������� ���� 1�� Ŀ���� ���ڸ� �־��־����ϴ�.
�׸��� �迭�� x1,y1�� ��ġ�� ���� tmp�� �־��ְ� ���� ȸ���� �����־����ϴ�.
ȸ���� �ϸ鼭 Math.min�� ���� ȸ���ϴ� �� �� �ּڰ��� �����־����ϴ�.
�׸��� tmp����  x1,y1+1 ��ġ�� �־��־� ȸ���� �Ϸ��Ͽ��� ������� �ּڰ��� �����־� ���� ���Ͽ����ϴ�.

## �ҽ��ڵ�
~~~java
import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows+1][columns+1];
        int num = 1;
        
        // ��Ŀ� 1���� rows x columns������ ���ڸ� �� �پ� ������� �ִ´�.
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

## ���

|��Ȯ��|�׽�Ʈ|
|-----|-----|
|�׽�Ʈ 1 |	��� (0.03ms, 78.7MB)|
|�׽�Ʈ 2 |	��� (0.03ms, 76.1MB)|
|�׽�Ʈ 3 |	��� (11.91ms, 96.7MB)|
|�׽�Ʈ 4 |	��� (8.90ms, 84MB)|
|�׽�Ʈ 5 |	��� (15.54ms, 91.5MB)|
|�׽�Ʈ 6 |	��� (10.79ms, 98MB)|
|�׽�Ʈ 7 |	��� (14.17ms, 90.3MB)|
|�׽�Ʈ 8 |	��� (9.36ms, 90.4MB)|
|�׽�Ʈ 9 |	��� (12.33ms, 95.4MB)|
|�׽�Ʈ 10 |	��� (9.68ms, 89MB)|
|�׽�Ʈ 11 |	��� (8.85ms, 77.4MB)|