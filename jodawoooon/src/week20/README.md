## Programmers Lv3 N으로 표현
- 동적계획법 (DP)
- level3
- DFS



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42895


아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

<br>

#### ✔ 제한사항
- N은 1 이상 9 이하입니다.
- number는 1 이상 32,000 이하입니다.
- 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
- 최솟값이 8보다 크면 -1을 return 합니다.
<br>
 
#### ✔ 입출력 예
| N | number | return | 
|--|--|--|
| 5 | 12 | 4 |
| 2 | 11 | 3 |

예제 #1
문제에 나온 예와 같습니다.

예제 #2
11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.

###  💡 풀이


숫자 N만으로 사칙연산을 이용해 number를 만드는 문제였다.
dp로는 못 풀겠어서 dfs로 풀었다..

단순히 N의 사칙연산이 아니라 N 여러개를 붙여서 NN, NNN, NNNN을 만들어서 연산할 수 있다.  

그리고 N은 8개 이하로 사용할 수 있으므로  
N을 8개까지 붙여가면서 dfs로 target number를 구할 수 있는지 찾았다.


<br>



<br><br>

###  💡 소스코드


```java
import java.util.*;

class Solution {
    static int ans = Integer.MAX_VALUE;
    public int solution(int N, int number) {
        
        dfs(0,0, N, number);
        
        if(ans>8) return -1;
        else return ans;
    }
    
    static void dfs(int idx, int sum, int N, int number){
        if(idx>8) {
            return;
            //최솟값이 8보다 크면 -1을 return 
        }
        
        if(sum==number){ //target number
            ans = Math.min(ans, idx);
            //N 사용횟수의 최솟값
        }
        
        //5, 55, 555, 5555
        int operNum = N;
        for(int i=1 ; i < 8 ; i++){
            
            dfs(idx+i, sum+operNum, N, number);
            dfs(idx+i, sum-operNum, N, number);
            dfs(idx+i, sum*operNum, N, number);
            dfs(idx+i, sum/operNum, N, number);
            //사칙연산만
            
            
            operNum *= 10;
            operNum += N;
            
            
        }
    }
}
```

<br>

### 결과
테스트 1 〉	통과 (30.75ms, 53MB)
테스트 2 〉	통과 (24.63ms, 53MB)
테스트 3 〉	통과 (28.05ms, 53MB)
테스트 4 〉	통과 (21.50ms, 53.8MB)
테스트 5 〉	통과 (34.37ms, 52.4MB)
테스트 6 〉	통과 (30.43ms, 53.1MB)
테스트 7 〉	통과 (27.62ms, 52.6MB)
테스트 8 〉	통과 (39.35ms, 52.5MB)
테스트 9 〉	통과 (26.44ms, 52.6MB)