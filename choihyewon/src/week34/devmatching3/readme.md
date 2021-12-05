## Programmers - 다단계 칫솔 판매
- HashMap
- Level 3
- 2021 Dev-Matching: 웹 백엔드 개발자(상반기)
-[다단계 칫솔 판매 문제 바로가기](https://programmers.co.kr/learn/courses/30/lessons/77486)

## 풀이

먼저 자식과 부모를 담는 hashmap과 자신과 순서를 담는 hashmap을 만들어주었습니다.

~~~java
		HashMap<String,String> parents = new HashMap<>();
		HashMap<String,Integer> order = new HashMap<>();
		
		for(int i=0; i<enroll.length; i++){
            String child = enroll[i];
            String parent = referral[i];
            parents.put(child,parent);
            order.put(child,i);
		}
~~~

그리고 seller의 배열을 순차적으로 돌면서 추천인이  "-" 이면 break, 아닐경우 현재 추천인이 본인이 되어 다시 반복문을 돌며 칫솔의 판매값을 누적해서 합해주었습니다.
이 때 자신이 가져야할 돈은 추천인에게 10%를 떼고 남은 돈이므로 amount[자신의 인덱스값] - (amount[자신의 인덱스값]/10)를 더해주면 됩니다.

~~~java
		for(int i=0; i<seller.length; i++){
            String child = seller[i];
            String parent = (String)parents.get(child);
            int money = amount[i]*100;
            while(true){
                int index = order.get(child);
                answer[index] += money-money/10;
                
                if(parent.equals("-")){
                    break;
                }
                
                child = parent;
                parent = (String)parents.get(child);
                money = money/10; 
 
                if(money<1){
                    break;
                }
            }
        }
~~~


## 막힌점
처음에 11~13에서 시간초과가 나왔습니다. 다른 사람의 코드를 참고하며 보니 돈을 10%씩 추천인에게 주다보면 돈이 나누어 지지 않는 경우를 고려하지 않아서 생기는 문제였습니다. 따라서 while문 안에 money가 1보다 작은 경우를 break를 해주어 이를 해결했습니다.

~~~java
                if(money<1){
                    break;
                }
~~~

## 소스코드
~~~java
import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String,String> parents = new HashMap<>();
        HashMap<String,Integer> order = new HashMap<>();
        
        for(int i=0; i<enroll.length; i++){
            String child = enroll[i];
            String parent = referral[i];
            parents.put(child,parent);
            order.put(child,i);
        }
        
        for(int i=0; i<seller.length; i++){
            String child = seller[i];
            String parent = (String)parents.get(child);
            int money = amount[i]*100;
            while(true){
                int index = order.get(child);
                answer[index] += money-money/10;
                
                if(parent.equals("-")){
                    break;
                }
                
                child = parent;
                parent = (String)parents.get(child);
                money = money/10; 
 
                if(money<1){
                    break;
                }
            }
        }
        return answer;
    }
}
~~~

## 결과

|정확성|테스트|
|-----|-----|
|테스트 1 |	통과 (0.04ms, 71.8MB)|
|테스트 2 |	통과 (0.07ms, 74.5MB)|
|테스트 3 |	통과 (0.08ms, 73.6MB)|
|테스트 4 |	통과 (0.14ms, 85.4MB)|
|테스트 5 |	통과 (1.26ms, 78MB)|
|테스트 6 |	통과 (2.64ms, 106MB)|
|테스트 7 |	통과 (2.86ms, 106MB)|
|테스트 8 |	통과 (4.12ms, 104MB)|
|테스트 9 |	통과 (22.17ms, 125MB)|
|테스트 10 |	통과 (62.95ms, 133MB)|
|테스트 11 |	통과 (33.03ms, 147MB)|
|테스트 12 |	통과 (40.74ms, 140MB)|
|테스트 13 |	통과 (36.01ms, 130MB)|