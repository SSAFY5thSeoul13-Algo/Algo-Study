## Programmers LV3 다단계 칫솔 판매
- LV3


<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/77486

모든 판매원은 칫솔의 판매에 의하여 발생하는 이익에서 10% 를 계산하여 자신을 조직에 참여시킨 추천인에게 배분하고 나머지는 자신이 가집니다. 모든 판매원은 자신이 칫솔 판매에서 발생한 이익 뿐만 아니라, 자신이 조직에 추천하여 가입시킨 판매원에게서 발생하는 이익의 10% 까지 자신에 이익이 됩니다. 자신에게 발생하는 이익 또한 마찬가지의 규칙으로 자신의 추천인에게 분배됩니다. 단, 10% 를 계산할 때에는 원 단위에서 절사하며, 10%를 계산한 금액이 1 원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.

칫솔의 판매에서 발생하는 이익은 개당 100 원으로 정해져 있습니다.

각 판매원의 이름을 담은 배열 enroll, 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열 referral, 판매량 집계 데이터의 판매원 이름을 나열한 배열 seller, 판매량 집계 데이터의 판매 수량을 나열한 배열 amount가 매개변수로 주어질 때, 각 판매원이 득한 이익금을 나열한 배열을 return 하도록 solution 함수를 완성해주세요. 판매원에게 배분된 이익금의 총합을 계산하여(정수형으로), 입력으로 주어진 enroll에 이름이 포함된 순서에 따라 나열하면 됩니다.


#### 제한사항
enroll의 길이는 1 이상 10,000 이하입니다.
enroll에 민호의 이름은 없습니다. 따라서 enroll의 길이는 민호를 제외한 조직 구성원의 총 수입니다.
referral의 길이는 enroll의 길이와 같습니다.
referral 내에서 i 번째에 있는 이름은 배열 enroll 내에서 i 번째에 있는 판매원을 조직에 참여시킨 사람의 이름입니다.
어느 누구의 추천도 없이 조직에 참여한 사람에 대해서는 referral 배열 내에 추천인의 이름이 기입되지 않고 “-“ 가 기입됩니다. 위 예제에서는 john 과 mary 가 이러한 예에 해당합니다.
enroll 에 등장하는 이름은 조직에 참여한 순서에 따릅니다.
즉, 어느 판매원의 이름이 enroll 의 i 번째에 등장한다면, 이 판매원을 조직에 참여시킨 사람의 이름, 즉 referral 의 i 번째 원소는 이미 배열 enroll 의 j 번째 (j < i) 에 등장했음이 보장됩니다.
seller의 길이는 1 이상 100,000 이하입니다.
seller 내의 i 번째에 있는 이름은 i 번째 판매 집계 데이터가 어느 판매원에 의한 것인지를 나타냅니다.
seller 에는 같은 이름이 중복해서 들어있을 수 있습니다.
amount의 길이는 seller의 길이와 같습니다.
amount 내의 i 번째에 있는 수는 i 번째 판매 집계 데이터의 판매량을 나타냅니다.
판매량의 범위, 즉 amount 의 원소들의 범위는 1 이상 100 이하인 자연수입니다.
칫솔 한 개를 판매하여 얻어지는 이익은 100 원으로 정해져 있습니다.
모든 조직 구성원들의 이름은 10 글자 이내의 영문 알파벳 소문자들로만 이루어져 있습니다.


###  💡 풀이

재귀 호출을 이용해 풀었습니다. 각 사람의 추천인을 저장하는 `referralMap`, 이익을 저장한 `benifitMap`을 이용하여 판매 실적이 있는 사람들을 기준으로 재귀 호출을 했습니다.

`benifitMap`에 존재하지 않는 판매자는 center이므로 그 경우 재귀 호출을 종료합니다.

###  💡 소스코드
```java
import java.util.*;

class Solution {
    static Map<String, String> referralMap = new HashMap<>();
    static Map<String, Integer> benifitMap = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {        
        for(int i =0; i < enroll.length; i++){
            String name = enroll[i];
            String ref = referral[i];
            
            referralMap.put(name, ref);
            benifitMap.put(name, 0);
        }
        
        for(int i =0; i < seller.length; i++){
            calcBenifit(seller[i],amount[i]*100);
        }
        
        int[] answer = new int[enroll.length];
        
        for(int i =0; i < enroll.length; i++){
            answer[i] = benifitMap.get(enroll[i]);
        }
        
        
        return answer;
    }
    
    static void calcBenifit(String seller, int price){
        int tenPer = price/10;
        
        if(!benifitMap.containsKey(seller)){
            return;
        }
        else{
            String nextName = referralMap.get(seller);
            benifitMap.put(seller, benifitMap.get(seller)+price-tenPer);
            
            if(tenPer > 0)
                calcBenifit(nextName, tenPer);
        }
    }
}




```


<br>



정확성  테스트
테스트 1 〉	통과 (0.05ms, 77.3MB)
테스트 2 〉	통과 (0.17ms, 75.5MB)
테스트 3 〉	통과 (0.13ms, 76.8MB)
테스트 4 〉	통과 (0.18ms, 76.1MB)
테스트 5 〉	통과 (1.00ms, 81.7MB)
테스트 6 〉	통과 (3.90ms, 107MB)
테스트 7 〉	통과 (4.49ms, 110MB)
테스트 8 〉	통과 (5.52ms, 99.5MB)
테스트 9 〉	통과 (24.53ms, 99.5MB)
테스트 10 〉	통과 (65.44ms, 153MB)
테스트 11 〉	통과 (42.46ms, 132MB)
테스트 12 〉	통과 (48.69ms, 143MB)
테스트 13 〉	통과 (41.14ms, 131MB)