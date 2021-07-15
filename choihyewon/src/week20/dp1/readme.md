## Programmers - 순위 
- DFS 
- Level3

🔗[N으로 표현 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/42895)

## 풀이
원래 카테고리가 dp로 되어있었으나 dp로 해결하지 못하여 dfs를 이용하여 문제를 풀었습니다.
만들 수 있는 모든 수를 가지고 사칙연산을 하여 N 사용횟수가 8보다 더 큰 경우에는 -1을 answer에 저장하여 return 해주었습니다.
그리고 예를 들어 N이 5인 경우, 5 외에도 55,555,5555... 이런 숫자를 연산하기 위해

~~~java
tmp = tmp * 10 + N;
~~~
부분을 통해 N이 연속인 숫자를 만들어 덧셈, 뺄셈, 곱셈, 나눗셈 사칙연산을 진행해주어 N 사용횟수의 최솟값을 구하였습니다.

 

## 소스코드
~~~java
class Solution {
    static int answer = 9;
	public int solution(int N, int number) {
        dfs(N, number, 0, 0);
        
        if(answer == 9)
            return -1;
        return answer;
    }

	private void dfs(int N, int number, int cnt, int result) {
		// 최솟값이 8보다 크면 -1 return
		if(cnt > 8) {
			answer = -1;
			return;
		}
		
		if(result==number) {
			answer = Math.min(answer, cnt);
            return;
		}
		
		int tmp = N;
		for(int i=0; i<8-cnt; i++) {
			dfs(N, number, cnt+i+1, result+tmp);
			dfs(N, number, cnt+i+1, result-tmp);
			dfs(N, number, cnt+i+1, result*tmp);
			dfs(N, number, cnt+i+1, result/tmp);
            tmp = tmp * 10 + N;
		}
		
		
	}
}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (5.08ms, 52.1MB)|
|테스트 2 |	통과 (6.49ms, 52.6MB)|
|테스트 3 |	통과 (5.49ms, 53.8MB)|
|테스트 4 |	통과 (5.62ms, 51.8MB)|
|테스트 5 |	통과 (4.24ms, 52.2MB)|
|테스트 6 |	통과 (5.55ms, 52.5MB)|
|테스트 7 |	통과 (5.04ms, 51.7MB)|
|테스트 8 |	통과 (6.15ms, 53MB)|
|테스트 9 |	통과 (3.37ms, 52.3MB)|