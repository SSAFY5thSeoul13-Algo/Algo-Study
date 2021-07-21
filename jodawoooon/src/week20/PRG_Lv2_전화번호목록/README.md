
## Programmers Lv2 전화번호 목록
- 해시
- level2
- 자료구조



<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42577

전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

- 구조대 : 119
- 박준영 : 97 674 223
- 지영석 : 11 9552 4421

전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
<br>

#### ✔ 제한사항
- phone_book의 길이는 1 이상 1,000,000 이하입니다.
	- 각 전화번호의 길이는 1 이상 20 이하입니다.
	- 같은 전화번호가 중복해서 들어있지 않습니다.
<br>
 
#### ✔ 입출력 예
| phone_book | return | 
|--|--|
| ["119", "97674223", "1195524421"]	 | false |
| ["123","456","789"] | true |
| ["12","123","1235","567","88"] | false |

입출력 예 #1
앞에서 설명한 예와 같습니다.

입출력 예 #2
한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.

입출력 예 #3
첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.

<br><br>

###  💡 풀이 1. HashSet + substring (시간초과)


- `HashSet<String> set`
<br>

처음에는 HashSet과 SubString을 이용하여 풀었다.

1. `hone_book`의 phone을 `set`에 `add`한다.
	```java
    for(String phone : phone_book) {
        set.add(phone); //없는 prefix면 add
    }
    ```
2. `set`의 String 값과 `phone_book`의 phone 값을 비교한다.
	두 문자열의 길이 중 짧은 값을 `length`로 두고 substring으로 prefix를 자른다.
    ```java
   String subPhone = phone.substring(0, length);
    String subTgt = tgt.substring(0, length);
    
    if(subPhone.equals(subTgt)) return false;
    ```
	
    **이 두 값이 같으면 어떤 번호가 다른 번호의 접두어가 된다.**
    

이렇게 푸니까 효율성 체크 3,4번에서 시간초과가 계속 나왔다.

```java
    public boolean solution(String[] phone_book) {
		
        HashSet<String> set = new HashSet<>();
        
        for(String phone : phone_book) {
        	for(String tgt : set) {
        		int length = Math.min(phone.length(), tgt.length());
        		String subPhone = phone.substring(0, length);
        		String subTgt = tgt.substring(0, length);
        		
        		if(subPhone.equals(subTgt)) return false;
        	}
        	
        	set.add(phone); //없는 prefix면 add
        }

        return true;
    }
```

<br><br>

###  💡 풀이 2. String 비교 (시간초과)

그래서 그냥 `Hash`를 빼고 `phone_book`을 이중 루프로 탐색하면서 접두사를 탐색했다.

로직은 같음.

역시나 시간초과

```java
public boolean solution(String[] phone_book) {
    	for(String phone : phone_book) {
    		for(String tgt : phone_book) {
    			
    			if (phone.equals(tgt)) continue;
    			
    			int length = Math.min(phone.length(), tgt.length());
        		
        		String subPhone = phone.substring(0, length);
        		String subTgt = tgt.substring(0, length);
        		
        		if(subPhone.equals(subTgt)) return false;
    		}	
    	}

        return true;
    }
```


###  💡 풀이 3. Array + startsWith
  
Hash를 버리고 `Array`를 사용했다.  
먼저 Array를 오름차순으로 정렬하여 앞, 뒤에 유사한 Phone번호가 길이 순으로 오게 정렬했다.  
그리고 앞 뒤를 비교하여 `startsWith()`를 이용하여 prefix를 찾았다.  

```java
public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        //오름차순 정렬!!
        
        int len = phone_book.length;
        
        for(int i=0 ; i < len -1 ; i++){
            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        
        return true;
    }
```

<br>

### 결과
정확성  테스트
테스트 1 〉	통과 (0.27ms, 53.8MB)
테스트 2 〉	통과 (0.22ms, 52.6MB)
테스트 3 〉	통과 (0.22ms, 52.2MB)
테스트 4 〉	통과 (0.20ms, 52MB)
테스트 5 〉	통과 (0.25ms, 51.9MB)
테스트 6 〉	통과 (0.27ms, 53.1MB)
테스트 7 〉	통과 (0.25ms, 51.8MB)
테스트 8 〉	통과 (0.26ms, 52.6MB)
테스트 9 〉	통과 (0.22ms, 52.3MB)
테스트 10 〉	통과 (0.19ms, 53MB)
테스트 11 〉	통과 (0.27ms, 52.9MB)
테스트 12 〉	통과 (0.25ms, 52.8MB)
테스트 13 〉	통과 (0.20ms, 52.8MB)
테스트 14 〉	통과 (3.18ms, 53.7MB)
테스트 15 〉	통과 (8.06ms, 53.4MB)
테스트 16 〉	통과 (7.58ms, 53.9MB)
테스트 17 〉	통과 (6.80ms, 55.4MB)
테스트 18 〉	통과 (8.67ms, 53.4MB)
테스트 19 〉	통과 (6.43ms, 53.6MB)
테스트 20 〉	통과 (8.93ms, 54.1MB)

효율성  테스트
테스트 1 〉	통과 (17.24ms, 56.4MB)
테스트 2 〉	통과 (18.27ms, 56.3MB)
테스트 3 〉	통과 (360.07ms, 98.7MB)
테스트 4 〉	통과 (312.53ms, 95MB)

채점 결과
정확성: 83.3
효율성: 16.7
합계: 100.0 / 100.0
