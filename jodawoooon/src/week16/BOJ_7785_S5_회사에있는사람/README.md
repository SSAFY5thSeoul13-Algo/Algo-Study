
## BOJ 7785 S5 회사에있는사람
- silver5
- 자료구조
- Set

<br>

### Set

**HashSet**과 List를 비교해보면 크게 2가지 차이점이 있다.  
1. Set은 중복 값을 허용하지 않는다. 하나의 null만 저장할 수 있다..
2. HashSet은 특정한 순서를 가지고 있지 않다.

삽입 시 순서가 없기 때문에, Array나 List처럼 index값을 이용해 get할 수 없다.   
따라서 Iterator를 사용하여 집합안의 원소를 출력한다.  

주로 사용되는 Set은 HashSet말고도 **LinkedHashSet**, **TreeSet**이 있다.
- LinkedSet : 다른 Set과 동일하게 중복 허용  X이나, .add()한 순서대로 값이 저장된다.
- TreeSet : **오름차순으로 값을 정렬**해 가지고 있으며, 다른 set보다 대량의 데이터를 검색할 시 훨씬 빠르다.

<br>

### 풀이 과정
오름차순으로 값이 정렬되는 TreeSet을 활용하여 풀었습니다.  

    if(status.equals("enter")) {
		set.add(name);
	}else {
		set.remove(name);
	}

각 사람의 이름을 set에 add 또는 push하여 출입기록을 구현했습니다.  

그리고 사전 역순으로 출력하기 위해  

    Iterator<String> iter = set.descendingIterator();

TreeSet을 descendingIterator()하여 역순으로 읽었습니다.  

HashSet + list, TreeSet + Stack등 여러 조합을 사용해 보았으나  
이 방법이 제일 메모리와 시간 측면에서 효율적인 것 같아 위와 같이 풀어보았습니다.  
시간을 단축시키기 위해 StringBuilder를 사용하여 출력했습니다.  

<br>

### 결과
메모리 : 47796KB  
시간 : 496ms
 