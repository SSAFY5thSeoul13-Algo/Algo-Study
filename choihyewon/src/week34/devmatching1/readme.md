## Programmers - 로또의 최고 순위와 최저 순위 
- Implementation
- Level 1
- 2021 Dev-Matching: 웹 백엔드 개발자(상반기)
-[로또의 최고 순위와 최저 순위 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/77484)

## 풀이

lottos 배열의 인덱스가 0인 경우와 0이 아닌 경우를 나누어서 문제를 풀었습니다.
0인 경우 zero를 count 해주었고, 아닌 경우에는 win_nums 배열과 비교하여 맞으면 match를 count해준뒤 break해주어 다음 인덱스로 넘어가며 문제를 구하였습니다.


## 소스코드
~~~java
class Solution {
	    public int[] solution(int[] lottos, int[] win_nums) {
	        int[] answer = new int[2];
	        int zero = 0;
	        int match = 0;
	        for(int i : lottos){
	            // 0이 아닌 경우에만 win_nums 탐색 
	            if(i==0){
	                zero++;
	            }else{
	                for(int j : win_nums){
	                    if(i==j){
	                        match++;
	                        break;
	                    }
	                }
	            }
	        }
	        
	        
	        answer[0] = match !=0 ? 7-match-zero : (zero !=0 ? 7-zero : 6);
	        answer[1] = match !=0 ? 7-match : 6;
	        
	        return answer;
	    }
	}

~~~

## 결과

|정확성|테스트|
|테스트 1 |	통과 (0.02ms, 79.3MB)|
|테스트 2 |	통과 (0.01ms, 73.5MB)|
|테스트 3 |	통과 (0.02ms, 73.6MB)|
|테스트 4 |	통과 (0.01ms, 78.5MB)|
|테스트 5 |	통과 (0.01ms, 73.3MB)|
|테스트 6 |	통과 (0.01ms, 76.4MB)|
|테스트 7 |	통과 (0.01ms, 73.9MB)|
|테스트 8 |	통과 (0.01ms, 77.5MB)|
|테스트 9 |	통과 (0.02ms, 78.8MB)|
|테스트 10|	통과 (0.02ms, 75.8MB)|
|테스트 11 |	통과 (0.02ms, 76.4MB)|
|테스트 12 |	통과 (0.03ms, 73.4MB)|
|테스트 13 |	통과 (0.01ms, 74.8MB)|
|테스트 14 |	통과 (0.01ms, 67.9MB)|
|테스트 15 |	통과 (0.02ms, 77.8MB)|