## BAEKJOON GOLD4 1593 문자 해독
- 슬라이딩 윈도우
- Gold 4
- https://www.acmicpc.net/problem/1593
<br>

### 문제설명

> 마야 문자를 해독하는 일은 예상 외로 어려운 일이다. 현재에도 뜻이 완전히 밝혀진 마야 문자는 거의 없는 실정이며, 그나마 해독에 진척이 시작된 지는 30여 년도 되지 않았다.
마야 문자는 소리를 나타내는 여러 종류의 그림글자로 구성되는데, 이 글자들이 여러 위치에서 결합함으로써 단어를 형성한다.
마야 문자 해독을 어렵게 하는 요인 중 하나는 바로 단어를 읽는 순서이다. 마야 문자를 쓰는 고대인들은 단어를 기록할 때 특정한 규칙 대신, 그들이 보기에 좋게 보이도록 단어를 이루는 글자들을 아무렇게나 배열했다. 그렇기 때문에 고고학자들이 마야 기록에서 단어를 이루는 각 그림글자들의 발음을 알아내더라도 그 단어를 실제로 발음하는 방법은 정확히 알 수 없는 셈이다.
고고학자들은 W라는 특정 단어를 발굴 기록으로부터 찾고 있다. 그 단어를 구성하는 각 글자들은 무엇인지 알고 있지만, 이것이 고대 기록에 어떤 형태로 숨어 있는지는 다 알지 못한다.
W를 이루고 있는 g개의 그림문자와, 연구 대상인 벽화에 기록된 마야 문자열 S가 주어졌을 때, 단어 W가 마야 문자열 S에 들어있을 수 있는 모든 가짓수를 계산하는 프로그램을 작성하시오. 즉, 문자열  S안에서 문자열 W의 순열 중 하나가 부분 문자열로 들어있는 모든 경우의 수를 계산하라는 뜻이다.

### 입력
- 첫째 줄에 고고학자들이 찾고자 하는 단어 W의 길이 g와 발굴된 벽화에서 추출한 문자열 S의 길이 |S|가 빈 칸을 사이에 두고 주어진다. (1≤g≤3000,  g≤|S|≤3,000,000)
- 둘째 줄에 W, 셋째 줄에 S의 실제 내용이 들어있다. 
- 모든 문자열은 알파벳으로 이루어지며, 대소문자를 구분한다.

### 출력
첫째 줄에 W의 순열이 S 안에 있을 수 있는 형태의 개수를 출력한다

### 입출력 예

#### 예제 1
입력
```
4 11
cAda
AbrAcadAbRa
```
출력
```
2
```

### 풀이 및 과정
슬라이딩  윈도우를 이용하여 문제를 해결하였습니다.

먼자 찾고자하는 단어를 int배열을 이용하여 저장합니다.

```java
W = br.readLine();
		
// 찾을 문자열 상태 설정
for(char tar : W.toCharArray()) {
	if(tar <= 'Z') wCnt[tar - 'A']++;
	else wCnt[tar - 'a' + 26]++;
}
```

그 후, 슬라이딩 알고리즘을 이용합니다.

먼저 윈도우의 크기가 g(W의 길이)가 될때까지 int배열에 적용합니다.

적용을 한후, 찾을 문자열 상태와 같은지 확인하고 같다면 ans를 증가시킵니다.

그리고 슬라이딩 윈도우의 첫글자를 빼줍니다.

```java
// 슬라이딩 윈도우
int length = 0, first = 0;
for(char tar : S.toCharArray()) {
	
	// 슬라이싱 위도우 증가
	if(tar <= 'Z') sCnt[tar - 'A']++;
	else sCnt[tar - 'a' + 26]++;
	
	length++;
	
	// 찾을 문자열길이라면
	if(length == g) {
		
		// 같은 것인지 확인
		if(sameCnt()) ans++;
		
		// 맨 앞글자 빼기
		tar = S.charAt(first);
		
		if(tar <= 'Z') sCnt[tar - 'A']--;
		else sCnt[tar - 'a' + 26]--;
		
		length--;
		first++;
	}
}
```
### 소스코드
```java
package week24.BOJ_1593_G4_문자해독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * g : 찾으려는 특정단어 길이
	 * s : 발굴기록 길이
	 * W : 특정단어
	 * S : 발굴기록
	 * 
	 * wCnt : 찾을 문자열 상태
	 * sCnt : 슬라이싱 윈도우 상태
	 */
	static int g, s, ans;
	static String W, S;
	
	static int[] wCnt, sCnt;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		g = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		
		wCnt = new int[52];
		sCnt = new int[52];
		
		W = br.readLine();
		
		// 찾을 문자열 상태 설정
		for(char tar : W.toCharArray()) {
			if(tar <= 'Z') wCnt[tar - 'A']++;
			else wCnt[tar - 'a' + 26]++;
		}
		
		
		
		S = br.readLine();
		
		// 슬라잇이 윈도우
		int length = 0, first = 0;
		for(char tar : S.toCharArray()) {
			
			// 슬라이싱 위도우 증가
			if(tar <= 'Z') sCnt[tar - 'A']++;
			else sCnt[tar - 'a' + 26]++;
			
			length++;
			
			// 찾을 문자열길이라면
			if(length == g) {
				
				// 같은 것인지 확인
				if(sameCnt()) ans++;
				
				// 맨 앞글자 빼기
				tar = S.charAt(first);
				
				if(tar <= 'Z') sCnt[tar - 'A']--;
				else sCnt[tar - 'a' + 26]--;
				
				length--;
				first++;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean sameCnt() {
		for(int i=0; i<52; i++) {
			if(wCnt[i] != sCnt[i]) return false;
		}
		
		return true;
	}
}
```

### 결과
```
메모리 : 38832KB	
시간 : 236ms
```
