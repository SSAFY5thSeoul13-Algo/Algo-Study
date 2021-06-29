


## PROGRAMMERS LV2 가장큰수
- level2
- sorting


<br>


### 문제 설명

0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.


### 풀이과정
Integer 타입의 arraylist `numList`에 `numbers`의 수를 저장한 후 정렬했습니다.
정렬할 때에는 String 타입으로 변환하여
`compareTo`메소드를 활용해 정렬합니다.

     Collections.sort(numList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// 정수를 이어 붙여 만들 수 있는 가장 큰 수
				String a = o1+""+o2;
				String b = o2+""+o1;
				return b.compareTo(a);
			}
        	
        });

모든 원소가 0일 때의 case를 고려하지 못 했었습니다..


<br>