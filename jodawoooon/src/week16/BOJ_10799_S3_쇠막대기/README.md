
## BOJ 10799 S3 쇠막대기
- silver3
- 자료구조
- Stack
  

<br>

### 풀이 과정
( 와 ) 가 나왔을 때의 전체 cnt를 보면서 규칙성을 찾았습니다

- '(' 인 경우
	- 스택에 넣는다

- ')'인 경우
	스택에서 '('를 제거
	 		
			if(flag) {
					flag = false;
					stack.pop();
					cnt += stack.size();` }
	
flag=true이다 -> ()쌍이다 -> 레이저이다  
cnt에 stack의 사이즈(이전 '('의 개수)를 더한다 


		    else { //))이다 -> 쇠막대기 끝
					stack.pop();
					cnt++;
				}
				
레이저가 아니다 -> ))이다.  
쇠막대기가 닫히는 부분이다. cnt ++;

<br>

### 결과
메모리 : 14684KB  
시간 : 132ms
 