## BOJ 2661 G4 좋은수열
- 백트래킹
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2661

숫자 1, 2, 3으로만 이루어지는 수열이 있다. 임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면, 그 수열을 나쁜 수열이라고 부른다. 그렇지 않은 수열은 좋은 수열이다.

다음은 나쁜 수열의 예이다.

33
32121323
123123213
다음은 좋은 수열의 예이다.

2
32
32123
1232123
길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램을 작성하라. 예를 들면, 1213121과 2123212는 모두 좋은 수열이지만 그 중에서 작은 수를 나타내는 수열은 1213121이다.


#### 입력
입력은 숫자 N하나로 이루어진다. N은 1 이상 80 이하이다.

#### 출력
첫 번째 줄에 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서 가장 작은 수를 나타내는 수열만 출력한다. 수열을 이루는 1, 2, 3들 사이에는 빈칸을 두지 않는다.

###  💡 풀이

길이가 `N`이 될 때 까지 뒤쪽에 차례로 1,2,3을 붙여서 수열을 만들어 냈습니다

새로 숫자를 붙일 때마다 `checkIsGoodNum`을 호출하여 좋은 수열인지 확인한 후에 재귀호출을 해서 수열을 만들어냅니다

좋은 수열인지 확인할 때는 현재 만들어진 수열의 절반 길이인 `num.length()/2`길이 까지의 부분수열만 확인합니다

계속된 재귀에서 수열의 길이가 N이 되면 1,2,3순으로 숫자를 붙여왔기 때문에 가장 먼저 만들어진 수열이 가장 작으므로 해당 수열을 출력하고 `System.exit()`을 사용해 종료합니다


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2661_G4_좋은수열 {
	static int N;
	static List<String> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		makeNum("", 0, 0);
	}
	
	static void makeNum(String num, int depth, int prevNum) {
		if(depth == N) {
			System.out.println(num);
			System.exit(0);
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			if(prevNum == i)	continue;
			
			String nNum = num + Integer.toString(i);
			
			
			if(checkIsGoodNum(nNum)) {
				makeNum(nNum, depth+1, i);
			}
		}
	}
	
	static boolean checkIsGoodNum(String num) {
		if("".equals(num))
			return true;
		
		for (int i = 1; i <= num.length()/2; i++) {
			String num1 = num.substring(num.length()-i);
			String num2 = num.substring(num.length()-i*2, num.length()-i);
			
			if(num1.equals(num2)) {
				return false;
			}
		}
		
		return true;
	}
}





```


<br>



메모리|시간
--|--
12236 KB|96 ms