## Progrmmers LV3 N으로 표현
- DP
- Level 3
- https://programmers.co.kr/learn/courses/30/lessons/42895
<br>

### 문제설명

> 아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

```
12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5
```

>5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.


### 제한사항
- N은 1 이상 9 이하입니다.
- number는 1 이상 32,000 이하입니다.
- 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
- 최솟값이 8보다 크면 -1을 return 합니다.

### 입출력 예
|N|	number|	return|
|----|----|----|
|5|	12|	4|
|2|	11|	3|


#### 입출력 예 설명
예제 #1
문제에 나온 예와 같습니다.

예제 #2
11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.

### 풀이 및 과정
Hash Set 자료구조를 사용하여 문제를 해결하였습니다.
- set[i] : N을 i번 사용해서 만들 수 있는 수의 집합.

Set[i]에 들어갈 수 있는 수들은 다음과 같습니다.
- N을 연속으로 i번 써서 만들 수 있는수 ex) N=2, i=3 => 222
- 사칙연산으로 만들 수 있는 수. 현재 찾을 범위 = i, 이전에 찾았던 수의 범위(1 ~ i-1)를 j라고 할때 <br> 
set[j]의 아이템들 + set[i-j]의 아이템들의 사칙연산으로 계산된 결과들이다.

#### 막힌점
- 계속해서 테스트 8번이 틀렸다고 나왔었는데, 맨처음 HashSet에 연속된 N을 넣어줄때 계산을 잘못해주었습니다 ㅠㅠ
- 원래는 HashSet이 아니라 list로 처리했는데, 중복된 결과들이 너무 많이 나와서 i가 증가할수록 너무나 많은 값들이 발생하여 Set으로 수정하였습니다.

### 소스코드
```java
import java.util.*;
class Solution {
	
	public void main(String[] args) {
		System.out.println(solution(2, 11));
	}
	
    public int solution(int N, int number) {
        int answer = 0;
        
        Set<Integer>[] list = new HashSet[9];
        
        for(int i=1; i<=8; i++){
            list[i] = new HashSet<Integer>();
        }
        int tmpN = N;
        // i : 1~8까지 순회
        for(int i=1; i<=8; i++){
            list[i].add(tmpN);
            // j : 1~i까지 순회
            for(int j=1; j<i; j++){
                // j번 아이템 순회
                for(int a: list[j]){
                    // i-j번 아이템 순회
                    for(int b : list[i-j]){
                        // 0인 경우는 의미가 없기때문에 제외
                    	if(b==0) continue;
                    	// 덧셈
                        list[i].add(a + b);
                        // 뺄셈
                        if(a-b >= 0)
                            list[i].add(a-b);
                        // 곱셈
                        list[i].add(a*b);
                        
                        // 나눗셈
                        list[i].add(a/b);
                    }
                }
            }
            
            for(int item : list[i]){
                if(item == number)
                    return i;
            }
            tmpN = 10 * tmpN + N;
        }
        // 모든 순회를 마치고도 찾지못했다면 도달할 수 없는 숫자이다.
        return -1;
    }
}
```

### 결과
```
테스트 1 〉	통과 (2.27ms, 53.1MB)
테스트 2 〉	통과 (0.09ms, 52.2MB)
테스트 3 〉	통과 (0.09ms, 52.5MB)
테스트 4 〉	통과 (26.95ms, 54MB)
테스트 5 〉	통과 (17.17ms, 53.5MB)
테스트 6 〉	통과 (1.18ms, 54.2MB)
테스트 7 〉	통과 (1.50ms, 53.3MB)
테스트 8 〉	통과 (26.97ms, 54MB)
테스트 9 〉	통과 (0.08ms, 53.9MB)
```

