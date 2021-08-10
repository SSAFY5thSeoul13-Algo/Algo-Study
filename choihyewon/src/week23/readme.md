## BOJ 5052 전화번호 목록 
- String
- 🥇 Gold4
- 🔗[전화번호 목록 문제 바로가기](https://www.acmicpc.net/problem/5052)



## 풀이

각 전화번호를 list에 넣어준뒤 정렬을 해주었습니다.
그리고 오름차순 정렬이므로 바로 뒤의 인덱스만 비교해주면 다른번호가 접두어를 포함하는지 확인이 가능하므로 startWith을 이용하여 정답을 구했습니다.

## 막힌점 
처음에는 이중for문을 통해 먼저 비교할 전화번호와 그 다음 인덱스를 다 비교해주어 시간초과가 났습니다.
바로 뒤 인덱스의 전화번호만 비교하면 이중for문을 쓸 필요가 없으므로 밑의 코드로 수정해주었습니다.

## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_5052_G4_전화번호_목록 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			List<String> list = new ArrayList<String>();
			
			for(int i=0; i<n; i++) {
				String number = br.readLine();
				list.add(number);				
			}
			
			Collections.sort(list);
			
			boolean isCheck = true;
			for(int i=0; i<n-1; i++) {
				if(list.get(i+1).startsWith(list.get(i))) {
					isCheck = false;
				}
			}
			
			if(isCheck) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 33196kb| 440ms|