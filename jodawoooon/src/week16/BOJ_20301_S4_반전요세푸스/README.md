
## BOJ 20301 S4 반전 요세푸스
- silver4
- 자료구조
- Deque
  

<br>

### Deque(Doublee Ended Queue)
Deque은 front와 rear에서 삽입,삭제가 모두 가능하다.

- 장점
	-   데이터의 삽입과 삭제가 빠르다.
	-   크기가 가변적이다.
	-   앞, 뒤에서 데이터를 삽입/삭제할 수 있다.
	-   index로 임의 원소 접근이 가능하다.
	-   새로운 원소 삽입 시에, 메모리를 재할당하고 복사하지 않고 새로운 단위의 메모리 블록을 할당하여 삽입한다.
	
- 단점
	-   deque의 중간에서의 삽입과 삭제가 어렵다.
	-   구현이 어렵다.

### 풀이과정
N : 사람의 수  
K : 제거 대상 수  
M :  M명 제거시 방향 바뀜  
flag : 짝수이면 정방향, 홀수이면 반대방향  

deque에 1부터 N까지의 수를 add한 뒤에  
deque가 isEmpty할 때 까지 while문안에서 K번째 대상을 제거시킵니다.  

- flag가 짝수인 경우
	

	    for (int i = 1; i <= K; i++) {
				if(i==K) {
					sb.append(deque.pollFirst()).append("\n");
				}else {
					deque.addLast(deque.pollFirst());
				}
			}
K번째 숫자 전까지 앞에서 빼내서 뒤로 옮기고  
K번째 숫자는 제거

- flag가 홀수인 경우
	

	    for (int i = 1; i <= K; i++) {
				if(i==K) {
					sb.append(deque.pollLast()).append("\n");
				}else {
					deque.addFirst(deque.pollLast());
				}
			}
K번째 숫자 전까지 뒤에서 빼내서 앞으로 옮기고  
K번째 숫자는 제거

- 제거한 사람의 숫자(cnt)가 M이면
	flag를 하나 증가시켜서 홀수를 짝수로, 짝수를 홀수로 변화시킨다.  
	 그리고 cnt는 다시 0으로 리셋

### 결과
메모리 : 19852KB  
시간 : 268ms
 