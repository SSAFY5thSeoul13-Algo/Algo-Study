먼저 int형 배열이던 numbers를 String 형 배열로 변환시켜주었습니다. 그 이유는 numbers가 6,10,2인 경우 내림차순 정렬을 하게되면 10,6,2가 되어 answer가 1062로 틀린답이 나옵니다. 그래서 String 형태로 바꿔준 후, o2+o1와 o1+o2를 비교하여 정렬된 배열을 String 값으로 출력하였습니다. <br>
[막힌점] : numbers가 0 0 0 인 경우를 고려해주지 않아
~~~
if(str[0].equals("0")) {
	        	return str[0];
}
~~~
부분을 추가해주었습니다.