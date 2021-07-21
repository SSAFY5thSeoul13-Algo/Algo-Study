## Programmers Lv2 위장
- 해시
- level2



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42578

스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.  

예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.  

종류	이름  
얼굴	동그란 안경, 검정 선글라스  
상의	파란색 티셔츠  
하의	청바지  
겉옷	긴 코트  


스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.  
<br>

#### ✔ 제한사항
- clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
- 같은 이름을 가진 의상은 존재하지 않습니다.
- clothes의 모든 원소는 문자열로 이루어져 있습니다.
- 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
- 스파이는 하루에 최소 한 개의 의상은 입습니다.
<br>
 
#### ✔ 입출력 예
| clothes | return | 
|--|--|
| [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]] | "5 |
| [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]] | 3 |

예제 #1
headgear에 해당하는 의상이 yellow_hat, green_turban이고 eyewear에 해당하는 의상이 blue_sunglasses이므로 아래와 같이 5개의 조합이 가능합니다.

```
1. yellow_hat
2. blue_sunglasses
3. green_turban
4. yellow_hat + blue_sunglasses
5. green_turban + blue_sunglasses
```

예제 #2
face에 해당하는 의상이 crow_mask, blue_sunglasses, smoky_makeup이므로 아래와 같이 3개의 조합이 가능합니다.
```
1. crow_mask
2. blue_sunglasses
3. smoky_makeup
```

<br><br>

###  💡 풀이
하루에 최소 1개의 의상을 입어야 하고, 매일 다른 의상을 입어야한다.  
*같은 이름을 가진 의상은 존재하지 않으므로* 종류별로 갯수만 구분하면 된다.  

의상의 종류와 각 개수를 같이 저장하기 위해서 `Map`을 이용했다.  
HashMap의 Key는 의상의 종류이고 value는 각 종류의 갯수이다.  

```java
 HashMap<String, Integer> map = new HashMap<>();
```

예를 들어 headgear가 n개일 때, 선택할 수 가짓 수는 `n+1`(아예 안 입는 경우)개 이다.

즉 각 의상의 종류의 개수(`value`)+1를 곱하면 모든 의상에 대해 선택할 수 있는 경우의 수가 된다. 

```java
for(String key : map.keySet()){
	answer *= map.get(key) + 1;
}
```
이 때, 하루에 최소 1개의 의상은 입어야 되므로 모두 선택하지 않는 경우의 수를 빼주면 된다.



<br>



<br><br>

###  💡 소스코드
```java
import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes){
            String key = cloth[1];
            map.put(key, map.getOrDefault(key, 0)+1);
        }
        
        for(String key : map.keySet()){
            answer *= map.get(key) + 1;
            //해당 의상 종류를 안 입을 수도 있으므로 value+1
        }
        
        return answer-1;
        //하루에 최소 1개의 의상은 입어야한다. (answer-1)
    }
}
```

<br>

### 결과

정확성  테스트  
테스트 1 〉	통과 (0.11ms, 52.5MB)  
테스트 2 〉	통과 (0.07ms, 52.5MB)  
테스트 3 〉	통과 (0.08ms, 52.1MB)  
테스트 4 〉	통과 (0.13ms, 52.5MB)  
테스트 5 〉	통과 (0.05ms, 51.8MB)  
테스트 6 〉	통과 (0.05ms, 52.2MB)  
테스트 7 〉	통과 (0.08ms, 52.4MB)  
테스트 8 〉	통과 (0.08ms, 52.4MB)  
테스트 9 〉	통과 (0.04ms, 52.6MB)  
테스트 10 〉	통과 (0.05ms, 52.5MB)  
테스트 11 〉	통과 (0.04ms, 52.9MB)  
테스트 12 〉	통과 (0.08ms, 52.4MB)  
테스트 13 〉	통과 (0.08ms, 52.8MB)  
테스트 14 〉	통과 (0.05ms, 52MB)  
테스트 15 〉	통과 (0.04ms, 52.3MB)  
테스트 16 〉	통과 (0.04ms, 52.1MB)  
테스트 17 〉	통과 (0.09ms, 53.4MB)  
테스트 18 〉	통과 (0.05ms, 52.8MB)  
테스트 19 〉	통과 (0.05ms, 52.2MB)  
테스트 20 〉	통과 (0.06ms, 53.2MB)  
테스트 21 〉	통과 (0.06ms, 53.1MB)  
테스트 22 〉	통과 (0.06ms, 52.2MB)  
테스트 23 〉	통과 (0.04ms, 52.2MB)  
테스트 24 〉	통과 (0.11ms, 52.9MB)  
테스트 25 〉	통과 (0.07ms, 52.9MB)  
테스트 26 〉	통과 (0.07ms, 53.9MB)  
테스트 27 〉	통과 (0.07ms, 52.7MB)  
테스트 28 〉	통과 (0.06ms, 51.8MB)  

채점 결과  
정확성: 100.0  
합계: 100.0 / 100.0  