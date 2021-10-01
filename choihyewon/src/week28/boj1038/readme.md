## BOJ 1038 감소하는 수 
- 🥇 Gold5
- 🔗[감소하는 수 문제 바로가기](https://www.acmicpc.net/problem/1038)



## 풀이

백트래킹으로 푸는 방법을 몰라서 다른 방식으로 풀었습니다..
먼저 N이 한자리수이거나 1023이상인 경우 (감소하는 수 중 제일 최대인 9876543210은 1022번째이므로) 각각 N을, -1을 출력해줍니다.
그리고 나머지의 경우 감소하는 수를 list에 다 넣은뒤 정렬을 해주어 N번째 값을 출력해주었습니다.

## 소스코드
~~~java
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1038_G5_감소하는_수 {
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 한자리수인 경우 자기 자신을 출력 
		if(N>=0 && N<=9) {
			System.out.println(N);
		}
		// 최대 감소하는 수는 9876543210로, 1022번째 감소하는 수 이므로 1023이상의 값이 나올 수 없다.
		else if(N>1022){
			System.out.println("-1");
		}else {
			for(int i=0; i<10; i++) {
				solution(i,1);
			}
			Collections.sort(list);
			System.out.println(list.get(N));
		}

	}
	private static void solution(long num, int idx) {
		if(idx>10) {
			return;
		}
		
		list.add(num);
		for(int i=0; i<num%10; i++) {
			solution((num*10)+i,idx+1);
		}
		
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 11616kb| 84ms|