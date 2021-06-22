
## BOJ 2164 S4 카드2
- silver4
- 자료구조
- Queue : **선입선출(FIFO,  First in first out) 방식**의 자료구조를 말한다.  
  

<br>

### 풀이 과정
자료구조 Queue를 이용하면 단순하게 풀 수 있는 문제였습니다.

    while(queue.size()>1) {
		//1. 제일 위에 있는 카드를 바닥에 버린다.
		queue.poll();
			
		//2.그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
		queue.add(queue.poll());
	}

<br>

### 결과
메모리 : 43124KB  
시간 : 136ms
 