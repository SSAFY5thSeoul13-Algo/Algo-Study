## Programmers Lv3 등굣길
- 동적계획법
- level2



<br><br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42898

계속되는 폭우로 일부 지역이 물에 잠겼습니다. 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.

아래 그림은 m = 4, n = 3 인 경우입니다.

![](https://images.velog.io/images/jodawooooon/post/9069bc00-6455-487f-a91a-3aa5a1e1ab59/image.png)

가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타냅니다.

격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다. 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.

<br>

#### ✔ 제한사항
- 격자의 크기 m, n은 1 이상 100 이하인 자연수입니다.
	- m과 n이 모두 1인 경우는 입력으로 주어지지 않습니다.
- 물에 잠긴 지역은 0개 이상 10개 이하입니다.
- 집과 학교가 물에 잠긴 경우는 입력으로 주어지지 않습니다.
<br>
 
#### ✔ 입출력 예
|m|n|puddles|return|
|--|--|--|--|
|4|3|[[2, 2]]|4|
![](https://images.velog.io/images/jodawooooon/post/45397a47-7965-4c68-9397-fca219f9ce8a/image.png)


<br><br>

###  💡 풀이

웅덩이  puddles를 피해 (1,1)에서 (m,n)으로 가는 `최단 경로의 개수`를 찾으면 된다.  

오른쪽, 아래쪽으로만 이동이 가능하므로, 
특정 map[i][j]에 가는 경우의 수는 `위에서 아래`로, `왼쪽에서 오른쪽`으로 딱 두 가지이다.  

즉, dp[i][j] += dp[i-1][j] + dp[i][j-1]이다.  
이 때, 위나 왼쪽이 puddles인 경우만 제외해주면 된다.  


<br><br>

###  💡 소스코드



```java
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int dp[][] = new int[n+1][m+1];

        for(int[] puddle : puddles){
            dp[puddle[1]][puddle[0]] = -1;
        }
        //puddle 매핑
        
        dp[1][1] = 1;
        
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(dp[i][j]==-1) continue; //puddle이면 pass
                
                if(dp[i-1][j]!=-1) dp[i][j] += dp[i-1][j] % 1000000007; //위에가 puddle이 아니면
                if(dp[i][j-1]!=-1) dp[i][j] += dp[i][j-1] % 1000000007; //왼쪽이 puddle이 아니면
            }
        }
        
        return (dp[n][m] % 1000000007);
    }
}

```

<br><br>

### 🚩 결과
테스트 1 〉	통과 (0.02ms, 52.3MB)
테스트 2 〉	통과 (0.02ms, 52.3MB)
테스트 3 〉	통과 (0.03ms, 51.8MB)
테스트 4 〉	통과 (0.05ms, 52.7MB)
테스트 5 〉	통과 (0.04ms, 51.9MB)
테스트 6 〉	통과 (0.04ms, 53.7MB)
테스트 7 〉	통과 (0.04ms, 52.3MB)
테스트 8 〉	통과 (0.07ms, 54.2MB)
테스트 9 〉	통과 (0.04ms, 52.4MB)
테스트 10 〉	통과 (0.03ms, 53.2MB)
효율성  테스트
테스트 1 〉	통과 (1.15ms, 53.2MB)
테스트 2 〉	통과 (0.44ms, 53MB)
테스트 3 〉	통과 (0.56ms, 52.6MB)
테스트 4 〉	통과 (0.82ms, 52.6MB)
테스트 5 〉	통과 (0.64ms, 52.2MB)
테스트 6 〉	통과 (1.11ms, 52.3MB)
테스트 7 〉	통과 (0.52ms, 51.6MB)
테스트 8 〉	통과 (0.85ms, 53.1MB)
테스트 9 〉	통과 (0.88ms, 53.9MB)
테스트 10 〉	통과 (0.78ms, 54.2MB)