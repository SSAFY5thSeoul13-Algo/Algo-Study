## Programmers Lv2 전화번호 목록
- 해시
- level2

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/42577

전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

구조대 : 119
박준영 : 97 674 223
지영석 : 11 9552 4421
전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.


#### 제한사항
phone_book의 길이는 1 이상 1,000,000 이하입니다.
각 전화번호의 길이는 1 이상 20 이하입니다.
같은 전화번호가 중복해서 들어있지 않습니다.
<br><br>

###  💡 풀이

변수
`Set<String> set = new HashSet<>()` : 접두사들을 저장할 해시 

<br>

`phone_book`를 `Arrays.sort`를 사용해서 정렬을 하면

```
88
567
1235
123
```

처럼 겹치는 숫자가 있는 경우 길이가 더 작은 숫자가 뒤로 정렬이 된다
이후 배열의 역순으로 문자여을 살피는데 이미 `set`에 있는 경우 `false`를 반환한다
`set`에 없는 경우 해당 문자열의 접두사가 될 수 있는 부분 문자열들을 `set`에 저장한다


```
		//배열의 역순으로
		for (int i = phone_book.length-1; i >=0 ; i--) {
			
			//각 배열의 문자열
			String string = phone_book[i];

			//접두사 목록에 있는 경우 false반환
			if(set.contains(string))
				return false;
			
			//해당 번호의 접두사가 될 수 있는 문자열들을 set에 저장
			for (int j = 1; j < string.length(); j++) {
				String str = string.substring(0, j);
				set.add(str);
			}
		}
```

for문이 실행될 동안 접두사가 없는 경우 `true`를 리턴한다

```
		//접두사가 없는 경우
		return true;
```


<br><br>

###  💡 소스코드
```
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Programmers_LV2_전화번호목록 {

	public static void main(String[] args) {
		String[] phone_book = {"12","123","1235","567","88"};
		boolean answer = solution(phone_book);
		
		System.out.println(answer);
	}

	static boolean solution(String[] phone_book) {
		//접두사를 저장할 해시셋
		Set<String> set = new HashSet<>();
		
		//번호 문자열을 정렬
		Arrays.sort(phone_book);
		
		//배열의 역순으로
		for (int i = phone_book.length-1; i >=0 ; i--) {
			
			//각 배열의 문자열
			String string = phone_book[i];

			//접두사 목록에 있는 경우 false반환
			if(set.contains(string))
				return false;
			
			//해당 번호의 접두사가 될 수 있는 문자열들을 set에 저장
			for (int j = 1; j < string.length(); j++) {
				String str = string.substring(0, j);
				set.add(str);
			}
		}
		
		//접두사가 없는 경우
		return true;
	}
}



```


<br>


####정확성  테스트

| 번호  | 결과 |
|----|----|
|테스트 1 |	통과 (0.17ms, 51.9MB)|
|테스트 2 |	통과 (0.24ms, 53.3MB)|
|테스트 3 |	통과 (0.24ms, 52.5MB)|
|테스트 4 |	통과 (0.27ms, 52.5MB)|
|테스트 5 |	통과 (0.24ms, 52.5MB)|
|테스트 6 |	통과 (0.23ms, 52.3MB)|
|테스트 7 |	통과 (0.25ms, 52.7MB)|
|테스트 8 |	통과 (0.40ms, 51.4MB)|
|테스트 9 |	통과 (0.24ms, 52.3MB)|
|테스트 10 |	통과 (1.36ms, 52.3MB)|
|테스트 11 |	통과 (0.25ms, 52.1MB)|
|테스트 12 |	통과 (0.25ms, 52.2MB)|
|테스트 13 |	통과 (0.23ms, 52.5MB)|
|테스트 14 |	통과 (6.57ms, 54.2MB)|
|테스트 15 |	통과 (8.23ms, 53.6MB)|
|테스트 16 |	통과 (19.56ms, 56.5MB)|
|테스트 17 |	통과 (21.63ms, 57.1MB)|
|테스트 18 |	통과 (21.47ms, 57.4MB)|
|테스트 19 |	통과 (26.80ms, 57.1MB)|
|테스트 20 |	통과 (21.88ms, 57.5MB)|

####효율성  테스트

| 번호  | 결과 |
|----|----|
|테스트 1 |	통과 (15.45ms, 57.2MB)|
|테스트 2 |	통과 (20.38ms, 57.4MB)|
|테스트 3 |	통과 (692.82ms, 236MB)|
|테스트 4 |	통과 (788.13ms, 201MB)|