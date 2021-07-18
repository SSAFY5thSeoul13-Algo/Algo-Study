## Programmers Lv3 정수 삼각형
- 동적계획법 (DP)
- level3



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/43105

![](https://images.velog.io/images/jodawooooon/post/8a349d27-3ba5-4205-908c-c658cce0353e/image.png)
위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.

삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.

<br>

#### ✔ 제한사항
- 삼각형의 높이는 1 이상 500 이하입니다.
- 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.

<br>
 
#### ✔ 입출력 예
| triangle | return | 
|--|--|
|[[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]	 | 30 |



###  💡 풀이

0,0에서 시작해서 아래칸으로 이동하면서 답을 구했다.  
맨 왼쪽 열, 오른쪽 열은 한 방향으로만 이동해왔으므로 따로 if문을 나누어 처리했다.  

```java
if(j==0) triangle[i][j] += triangle[i-1][j];
//맨 왼쪽 열
                
else if(j==i) triangle[i][j] += triangle[i-1][j-1];
//맨 오른쪽 열
                
else triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
```

<br><br>

###  💡 소스코드


```java
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
```

<br>

### 결과
정확성  테스트
테스트 1 〉	통과 (0.02ms, 52.7MB)
테스트 2 〉	통과 (0.03ms, 53.1MB)
테스트 3 〉	통과 (0.06ms, 52.6MB)
테스트 4 〉	통과 (0.06ms, 52.5MB)
테스트 5 〉	통과 (0.33ms, 53.2MB)
테스트 6 〉	통과 (0.14ms, 53.2MB)
테스트 7 〉	통과 (0.31ms, 52.2MB)
테스트 8 〉	통과 (0.09ms, 52.5MB)
테스트 9 〉	통과 (0.06ms, 52.2MB)
테스트 10 〉	통과 (0.09ms, 52.3MB)
효율성  테스트
테스트 1 〉	통과 (6.98ms, 60.1MB)
테스트 2 〉	통과 (8.81ms, 60.7MB)
테스트 3 〉	통과 (8.75ms, 62MB)
테스트 4 〉	통과 (8.24ms, 59.4MB)
테스트 5 〉	통과 (6.91ms, 60.9MB)
테스트 6 〉	통과 (7.60ms, 61.7MB)
테스트 7 〉	통과 (9.84ms, 57.3MB)
테스트 8 〉	통과 (6.49ms, 59.2MB)
테스트 9 〉	통과 (7.19ms, 60.1MB)
테스트 10 〉	통과 (6.93ms, 57.4MB)