## BOJ 1764 S4 듣보잡
- silver4
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
HashSet과 TreeSet 2개를 활용하여 풀었습니다.  

    HashSet<String> set1 = new HashSet<>();
	TreeSet<String> set2 = new TreeSet<>();


처음에는 ArrayList 2개를 썼다가 시간초과가 났었는데, list 대신에  
듣도못한사람, 보도못한사람 모두 중복되는 사람이 없으므로  
set을 사용하면 빠르게 값을 처리할 수 있습니다.    

먼저  HashSet에 듣도 못한사람의 명단을 넣고  

    if(set1.contains(name)) {
		set2.add(name);
	}

보도 못한 사람 중에, 듣도 못한 사람이 있다면
TreeSet에 add했습니다.

그리고 TreeSet은 오름차순 정렬되어 있으므로  
TreeSet의 Size와 명단을 순서대로 StringBuilder를 이용해서 출력했습니다.  

<br>

### 결과
메모리 : 25192KB  
시간 : 240ms
 