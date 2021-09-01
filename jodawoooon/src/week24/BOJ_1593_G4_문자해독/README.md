## BOJ 1593 G4 문자 해독
- 슬라이딩 윈도우
- Gold4



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1593

마야 문자를 해독하는 일은 예상 외로 어려운 일이다. 현재에도 뜻이 완전히 밝혀진 마야 문자는 거의 없는 실정이며, 그나마 해독에 진척이 시작된 지는 30여 년도 되지 않았다.

마야 문자는 소리를 나타내는 여러 종류의 그림글자로 구성되는데, 이 글자들이 여러 위치에서 결합함으로써 단어를 형성한다.

마야 문자 해독을 어렵게 하는 요인 중 하나는 바로 단어를 읽는 순서이다. 마야 문자를 쓰는 고대인들은 단어를 기록할 때 특정한 규칙 대신, 그들이 보기에 좋게 보이도록 단어를 이루는 글자들을 아무렇게나 배열했다. 그렇기 때문에 고고학자들이 마야 기록에서 단어를 이루는 각 그림글자들의 발음을 알아내더라도 그 단어를 실제로 발음하는 방법은 정확히 알 수 없는 셈이다.

고고학자들은 W라는 특정 단어를 발굴 기록으로부터 찾고 있다. 그 단어를 구성하는 각 글자들은 무엇인지 알고 있지만, 이것이 고대 기록에 어떤 형태로 숨어 있는지는 다 알지 못한다.

W를 이루고 있는 g개의 그림문자와, 연구 대상인 벽화에 기록된 마야 문자열 S가 주어졌을 때, 단어 W가 마야 문자열 S에 들어있을 수 있는 모든 가짓수를 계산하는 프로그램을 작성하시오. 즉, 문자열  S안에서 문자열 W의 순열 중 하나가 부분 문자열로 들어있는 모든 경우의 수를 계산하라는 뜻이다.
<br>

#### ✔ 입력
첫째 줄에 고고학자들이 찾고자 하는 단어 W의 길이 g와 발굴된 벽화에서 추출한 문자열 S의 길이 |S|가 빈 칸을 사이에 두고 주어진다. (1≤g≤3000,  g≤|S|≤3,000,000) 둘째 줄에 W, 셋째 줄에 S의 실제 내용이 들어있다. 모든 문자열은 알파벳으로 이루어지며, 대소문자를 구분한다.
<br>

#### ✔ 출력
첫째 줄에 W의 순열이 S 안에 있을 수 있는 형태의 개수를 출력한다.
<br>


<br>

###  💡 풀이

처음에 W 단어의 알파벳들을 대문자와 소문자로 나누어서 사용된 `upperW`와 `lowerW` 배열에 저장했다.

<br>

그리고 맨 S 단어에 대해서도 마찬가지로 `upperS`와 `lowerS`로 나누어 알파벳을 저장한다.

이 때 슬라이딩 윈도우를 이용하여, S의 부분수열이 W의 길이(`g`)와 같아질 때 비교한다.   

S의 부분수열에서 사용된 알파벳이 W 사용된 알파벳과 동일하다면 W의 순열이 S 안에 있을 수 있는 형태이다.   
```java
	private static boolean check() {
		
		for(int i =0;i<26;i++) {
			if(upperW[i]!=upperS[i]) return false;
			if(lowerW[i]!=lowerS[i]) return false;
		}
		
		return true;
	}
```


따라서 이때 `ans++`한다.

그리고 윈도우 맨 앞의 알파벳(`i-g+1`번째)을 제거 후(맨 앞 index를 삭제) 하고 새로운 index를 뒤에 추가하고 위 과정을 반복한다.

```java
if(len==g) {
	if(check()) ans++;
				
	c = S.charAt(i-g+1);
	if(c<'a') upperS[c-'A']--;
	else lowerS[c-'a']--;
	len--;
}
```
<br><br>

접근방법에 대해 생각하지 못해,
다른 풀이들을 참고하였는데
그래도 어렵다..

<br><br>

###  💬 소스코드


```java

package week24.BOJ_1593_G4_문자해독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1593 G4 문자해독
 * @Author : Daun JO
 * @Date : 2021. 8. 29. 
 * @Algorithm : Sliding Window
 *
 */
public class Main_BOJ_1593_G4_문자해독 {
	
	static int g, s, ans;
	static String W, S;
	static int[] upperW, lowerW, upperS, lowerS;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		g = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		W = br.readLine();
		S = br.readLine();
		
		//W의 순열이 S 안에 있을 수 있는 형태의 개수 => 순열 검사 시간초과
		
		//검사해야하는 문자열의 길이는 고정(g)이기 때문에 슬라이딩 윈도우 기법을 사용하여 구간을 검사
		
		
		upperW = new int[26];
		lowerW = new int[26];
		upperS = new int[26];
		lowerS = new int[26];
		
		for(int i = 0 ; i < g ; i++) {
			char c = W.charAt(i);
			//W 단어에 사용된 알파벳
			if(c<'a') upperW[c-'A']++;
			else lowerW[c-'a']++;
		}
		
		
		int len = 0;

		for(int i =0;i<s;i++) {
			char c = S.charAt(i);
			if(c<'a') upperS[c-'A']++;
			else lowerS[c-'a']++;
			
			len++;
			
			if(len==g) {
				if(check()) ans++;
				
				c = S.charAt(i-g+1);
				if(c<'a') upperS[c-'A']--;
				else lowerS[c-'a']--;
				len--;
			}
		}
		
		System.out.println(ans);
		
	}
	private static boolean check() {
		
		for(int i =0;i<26;i++) {
			if(upperW[i]!=upperS[i]) return false;
			if(lowerW[i]!=lowerS[i]) return false;
		}
		
		return true;
	}
}

```

<br><br>


###  💯 채점 결과
메모리 33028 시간 272
		