## BOJ 1590 캠프가는 영식 
- BinarySearch 
- 🥈 Silver1
- 🔗[캠프가는 영식 문제 바로가기](https://www.acmicpc.net/problem/1590)



## 풀이

이분탐색을 이용하여 문제를 풀었습니다.

먼저 버스의 정보를 입력받아 list에 버스 출발 시간을 넣어주었고 이를 오름차순 정렬을 하였습니다.

그 다음 binarySearch 메소드를 통해 만약 마지막 버스의 출발시간이 영식이가 도착하는 시간보다 빠르면 return -1
첫번째 버스 출발시간이 영식이가 도착하는 시간보다 느리면 return list.get(0)-T

그리고 start는 0, end는 list 사이즈의 -1 (제일 마지막 index)로 지정하여 이분탐색을 진행하였습니다.

~~~java
		while(start<end) {
			long mid = (start+end)/2;
			
			if(list.get((int)mid) == T) {
				return 0;
			}
			else if(list.get((int)mid)>T) {
				end = mid;
			}else if(list.get((int)mid)<T){
				start = mid+1;
			}
			
		}
~~~

start가 end보다 크거나 같아지면 이분탐색을 마치고 list.get(start)-T를 return 하여 결과를 출력합니다.


## 소스코드
~~~java

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1590_S1_캠프가는_영식{
	static int N,T;
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			long startTime = Integer.parseInt(st.nextToken());
			long interval = Integer.parseInt(st.nextToken());
			long total = Integer.parseInt(st.nextToken());
			
			long time = startTime;
			for(int j=0; j<total; j++) {
				list.add(time);
				time += interval;
			}
		}
		
		Collections.sort(list);
		
		
		long ans = binarySearch();
		

		System.out.println(ans);
		
	}
	private static long binarySearch() {
		if(list.get(list.size()-1)<T)	return -1;
		if(list.get(0)>=T)	return list.get(0) - T;
		long start = 0;
		long end = list.size()-1;
		
		while(start<end) {
			long mid = (start+end)/2;
			
			if(list.get((int)mid) == T) {
				return 0;
			}
			else if(list.get((int)mid)>T) {
				end = mid;
			}else if(list.get((int)mid)<T){
				start = mid+1;
			}
			
		}
		
		
		return list.get((int)start) - T;
		
	}

}


~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 11776kb| 96ms|
