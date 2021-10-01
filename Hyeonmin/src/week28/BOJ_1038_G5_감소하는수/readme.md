## BOJ 1038 G5 감소하는 수
- 백트래킹
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1038

음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소한다면, 그 수를 감소하는 수라고 한다. 예를 들어, 321과 950은 감소하는 수지만, 322와 958은 아니다. N번째 감소하는 수를 출력하는 프로그램을 작성하시오. 0은 0번째 감소하는 수이고, 1은 1번째 감소하는 수이다. 만약 N번째 감소하는 수가 없다면 -1을 출력한다.


#### 입력
첫째 줄에 N이 주어진다. N은 1,000,000보다 작거나 같은 자연수 또는 0이다.

#### 출력
첫째 줄에 N번째 감소하는 수를 출력한다.

###  💡 풀이

감소하는 수 중에 가장 큰 수는 `9876543210`이다. 두번째로 큰 수는 `987654321`이다 `987654321`은 변수에 저장이 가능하고 이 숫자는 1021번쨰로 큰 감소하는 수이다.

0부터 987654321까지의 감소하는 수를 모두 구하여 `list`에 저장한 후에 정렬을 한다

입력한 `N`값이 1021이하이면 리스트에서 해당 인덱스의 값을 꺼내고 1022이면 `9876543210`을 출력한다. 1022보다 큰 경우는 -1을 출력한다


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1038_G5_감소하는수 {
	static int N, count;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		calcNum(0, 0, 0);
		
		Collections.sort(list);
		
		if(N > list.size()-1)
			System.out.println(-1);
		else if(N == 1022)
			System.out.println("9876543210");
		else
			System.out.println(list.get(N));
	}
	
	static void calcNum(int num, int step, int prevNum) {
		if(step > 9) {
			return;
		}
		
		for (int i = num; i <= 9; i++) {
			int nNum = (int) (i*Math.pow(10, step)+prevNum);
			list.add(nNum);
			
			if(i != 9)
				calcNum(i+1, step+1, nNum);
		}
	}
}





```


<br>



메모리|시간
--|--
11616 KB|88 ms