
## BOJ 17479 S3 정식당
- silver3
- 자료구조
- HashSet
- HashMap
  

<br>



### 풀이과정
변수
nmSum : 일반메뉴 주문 가격
spSum : 특별메뉴 주문 가격
flag : 서비스 메뉴 주문 했으면 true

메뉴판 3개를 사용

    //normal, special, service 메뉴판
	HashMap<String, Integer> normal = new HashMap<>();
	HashMap<String, Integer> special = new HashMap<>();
	HashSet<String> service = new HashSet<>();

그리고 입력받은 일반, 특별, 서비스 메뉴를 위 자료구조에 셋팅했습니다.  

그리고 사용자의 주문을 입력받아서  
 일반메뉴인지, 특별메뉴인지 구분하여 각각의 가격 sum값을 구했습니다.   
그리고서비스 메뉴 주문시 flag를 체크하여 단 하나만 주문했는지, 아닌지 확인했습니다.  

그리고 문제에 조건에서 주어진대로  
특별메뉴 주문조건, 서비스 메뉴 주문 조건을 만족하지 않으면 "No"를 출력했고  
주문이 제대로 들어 왔다면 "Okay"를 출력했습니다. 

가격이 int형의 범위를 넘어가므로 nmSum과 spSum의 자료형을 long으로 선언해주어야합니다.
 
<br>

### 결과
메모리 :   71444KB  
시간 :   620ms
 