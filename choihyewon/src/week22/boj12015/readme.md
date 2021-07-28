## BOJ - 12015 가장 긴 증가하는 부분수열 2
- BinarySearch
- 🥇Gold 2

🔗[가장 긴 증가하는 부분 수열 2 문제 바로가기](https://www.acmicpc.net/problem/12015)


## 풀이

최장 증가 부분 수열 문제를 풀면 대게 동적계획법을 사용해서 문제를 풉니다. 
그러나 동적계획법을 통해 이 문제를 풀게 되면 입력값이 최대 1000000이기 때문에 시간 복잡도가 O(N^2) 이 발생하여 시간초과가 발생합니다. 따라서 이 문제는 이분탐색으로 풀어 시간복잡도를 O(NlogK)로 줄여야 합니다.

먼저 숫자 비교를 위해 리스트를 생성한 후 0을 넣어줍니다.

그리고 N의 길이만큼 for문을 실행하여 숫자를 차레로 입력받습니다.
만약 입력받는 숫자가 리스트의 맨 마지막 값보다 크다면 증가수열이므로 리스트에 값을 넣어줍니다.

~~~java
			if(num>list.get(list.size()-1)) {
				list.add(num);
			}
~~~

만약 입력받는 숫자가 리스트의 맨 마지막 값보다 작다면 이분 탐색을 시작합니다.
left는 0, right는 list의 맨 마지막 index로 값을 설정하고 그 중간값 mid를 구하여
현재 입력받는 값과 list의 mid 번째의 값과 비교를 해줍니다.

~~~java
				left = 0;
				right = list.size()-1;
				// 이분탐색 시작 
				while(left<right) {
					mid = (left+right) / 2;
					if(num<=list.get(mid)) {
						right = mid;
					}else {
						left = mid + 1;
					}
				}
				list.set(right, num);
~~~

이분탐색을 통해 저장된 list의 길이에서 1을 뺀 값이 정답입니다. (처음에 0을 넣었으므로 1을 빼준다.)




## 막힌점 
만약 테스트 케이스가 [10,20,1,2,30] 인경우 [10,20,30] 이나 [1,2,30] 이나 최장 증가 부분 수열의 길이는 같기 때문에 이분탐색시 [1,2]는 [10,20]을 내포하고 있기 때문에 덮어져도 되는 것을 이해하는 부분에서 시간이 조금 걸렸습니다.


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12015_G2_가장_긴_증가하는_부분수열2 {
	static int N;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		// 수열의 최솟값은 1이므로 비교를 위해 0을 넣어준다.
		list.add(0);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num, left, right, mid;
		for(int i=0; i<N; i++) {
			num = Integer.parseInt(st.nextToken());
			// list의 맨 마지막값보다 크다면 list에 넣는다.
			if(num>list.get(list.size()-1)) {
				list.add(num);
			}else{
				left = 0;
				right = list.size()-1;
				// 이분탐색 시작 
				while(left<right) {
					mid = (left+right) / 2;
					if(num<=list.get(mid)) {
						right = mid;
					}else {
						left = mid + 1;
					}
				}
				list.set(right, num);
			}
		}
		
		System.out.println(list.size()-1);
		
	

	}

}
~~~

## 결과 

| 메모리  | 시간 |
|----|----|
|147216KB | 804ms |

