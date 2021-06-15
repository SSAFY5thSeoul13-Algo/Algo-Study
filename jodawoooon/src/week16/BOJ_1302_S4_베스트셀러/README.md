
## BOJ 1302 S4 베스트셀러
- silver4
- 자료구조
- HashMap

<br>


### 풀이 과정

책 Title을 key로, 개수를 value로 두고 HashMap을 사용했습니다.

    if(map.get(title)==null) { //1개팔린책
		map.put(title, 1);
	}else {
		map.put(title, map.get(title)+1); //팔린개수만큼
	}

팔린 책들을 입력으로 받아서
처음 넣는 값이면 value를 1로, 
한번 팔렸던 책이면 value를 1씩 증가시켜서 
map에 put했습니다.

그리고 map.keySet()을 검사해서

    for (String key : map.keySet()) {

팔린 개수 max값을 찾고, 가장 많이 팔린 책 제목을 ans에 갱신했습니다.

    if(map.get(key)>max) { //더 많이 팔렸으면
		max = map.get(key);  //max값 갱신
		ans = key; //ans 갱신			
	}

만약 가장 많이 팔린 책이 여러개라면

    else if(map.get(key)==max) { //가장 많이 팔린 책이 여러개면
		if(key.compareTo(ans)<0) { //사전순으로 더 앞서면
			ans = key; //ans 갱신
		}
	}

compareTo 메소드를 이용해서 사전순으로 더 앞서는지 검사했습니다.  
<br>

### 결과
메모리 : 25192KB  
시간 : 240ms
 