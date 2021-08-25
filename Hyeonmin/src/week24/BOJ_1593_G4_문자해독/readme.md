## BOJ 1593 G4 문자 해독
- 슬라이딩 윈도우
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1593

마야 문자 해독을 어렵게 하는 요인 중 하나는 바로 단어를 읽는 순서이다. 마야 문자를 쓰는 고대인들은 단어를 기록할 때 특정한 규칙 대신, 그들이 보기에 좋게 보이도록 단어를 이루는 글자들을 아무렇게나 배열했다. 그렇기 때문에 고고학자들이 마야 기록에서 단어를 이루는 각 그림글자들의 발음을 알아내더라도 그 단어를 실제로 발음하는 방법은 정확히 알 수 없는 셈이다.

고고학자들은 W라는 특정 단어를 발굴 기록으로부터 찾고 있다. 그 단어를 구성하는 각 글자들은 무엇인지 알고 있지만, 이것이 고대 기록에 어떤 형태로 숨어 있는지는 다 알지 못한다.

W를 이루고 있는 g개의 그림문자와, 연구 대상인 벽화에 기록된 마야 문자열 S가 주어졌을 때, 단어 W가 마야 문자열 S에 들어있을 수 있는 모든 가짓수를 계산하는 프로그램을 작성하시오. 즉, 문자열  S안에서 문자열 W의 순열 중 하나가 부분 문자열로 들어있는 모든 경우의 수를 계산하라는 뜻이다.


#### 입력
첫째 줄에 고고학자들이 찾고자 하는 단어 W의 길이 g와 발굴된 벽화에서 추출한 문자열 S의 길이 |S|가 빈 칸을 사이에 두고 주어진다. (1≤g≤3000,  g≤|S|≤3,000,000) 둘째 줄에 W, 셋째 줄에 S의 실제 내용이 들어있다. 모든 문자열은 알파벳으로 이루어지며, 대소문자를 구분한다.

#### 출력
첫째 줄에 W의 순열이 S 안에 있을 수 있는 형태의 개수를 출력한다.

###  💡 풀이

변수
`int[] targetWord` : 찾으려는 문자열의 알파벳 수를 저장할 배열
`int[] foundWord` : 추출한 문자열의 알파벳 수를 저장할 배열
`int tLen` : 찾으려는 문자열의 길이
`int fLen` : 추출한 문자열의 길이
`char[] W` : 찾으려는 문자열
`char[] S` : 추출한 문자열


<br>

`W`의 각 문자를 인덱스로하여서 `targetWord`에 각 문자에 해당하는 배열의 위치의 수를 증가시킨다


```java
		for (char c : W) {
			int idx = c;
			
			targetWord[idx]++;
		} 
```


추출한 문자열의 처음부터 `tLen`만큼의 문자를 선택해 `foundWord`에 각 문자를 int형으로 변환한곳을 인덱스로 하여 수를 카운트해 저장한다

`tLen`만큼의 문자의 수를 카운트 했으면 `W`에 해당하는 문자를 인덱스로 하는 `targetWord`의 숫자와 `foundWord`의 숫자를 비교해서 수가 일치하면 `S`안에 `W`가 1개 있는 것으로 취급하여 카운트를 1 증가시킨다

`S`의 마지막 부분까지 반복을 하여 결과를 구한다 


```java
		for (int i = 0; i < fLen; i++) {
			int idx = S[i];
			
			foundWord[idx]++;
			
			//추출한 문자열에서 선택한 문자가 찾으려는 단어길이보다 작은 경우
			if(i < tLen - 1)
				continue;
			
			boolean check = true;
			
			//문자열에서 선택한 알파벳과 단어의 알파벳 수가 일치하는지 확인
			for (char c : W) {
				int wIdx = c;
				
				if(foundWord[wIdx] != targetWord[wIdx]) {
					check = false;
					break;
				}
			}
			
			//수가 일치하면 카운트
			if(check)
				count++;
			
			//가장 앞에있는 문자를 제외
			idx = S[i-(tLen-1)];
			
			foundWord[idx]--;
			
		}
```





<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1593_G4_문자해독 {
	static int[] targetWord, foundWord;
	static int tLen, fLen;
	static char[] W, S; 
	static int count;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		tLen = Integer.parseInt(st.nextToken());
		fLen = Integer.parseInt(st.nextToken());
		
		targetWord = new int[300];
		foundWord = new int[300];
		
		//찾으려는 단어
		W = br.readLine().toCharArray();
		//추출한 문자열
		S = br.readLine().toCharArray();
		
		//찾으려는 단어의 알파벳 수를 배열에 저장
		for (char c : W) {
			int idx = c;
			
			targetWord[idx]++;
		}
		
		
		for (int i = 0; i < fLen; i++) {
			int idx = S[i];
			
			foundWord[idx]++;
			
			//추출한 문자열에서 선택한 문자가 찾으려는 단어길이보다 작은 경우
			if(i < tLen - 1)
				continue;
			
			boolean check = true;
			
			//문자열에서 선택한 알파벳과 단어의 알파벳 수가 일치하는지 확인
			for (char c : W) {
				int wIdx = c;
				
				if(foundWord[wIdx] != targetWord[wIdx]) {
					check = false;
					break;
				}
			}
			
			//수가 일치하면 카운트
			if(check)
				count++;
			
			//가장 앞에있는 문자를 제외
			idx = S[i-(tLen-1)];
			
			foundWord[idx]--;
			
		}
		
		System.out.println(count);
		
		
	}

}





```


<br>



메모리|시간
--|--
39444 KB|268 ms