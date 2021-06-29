


## PROGRAMMERS LV2 H-Index
- level2
- sorting


<br>


### 문제 설명


H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과[1](https://programmers.co.kr/learn/courses/30/lessons/42747#fn1)에 따르면, H-Index는 다음과 같이 구합니다.

어떤 과학자가 발표한 논문  `n`편 중,`h`번 이상 인용된 논문이 `h`편 이상이고 나머지 논문이 h번 이하 인용되었다면  `h`의 최댓값이 이 과학자의 H-Index입니다.

어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.


### 풀이과정
Integer 타입의 arraylist `list`에 `citations`의 수를 저장한 후 내림차순 정렬했습니다.

`max` : 논문 인용 최댓값
`n` : 논문 개수

그리고 0부터 논문 인용 최댓값까지 for문을 돌려 `h-Index`를 찾았습니다.  
list에서 h번 이상 인용된 논문 개수의 `cnt`를 체크해  
h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면   
H-Index를 찾은 것이므로 반복문을 종료합니다.

    for (int i = max ; i>=0; i--) {
        	//hIndex찾기
        	hIndex = i; 
        	
        	int cnt = 0;
			for (int num : list) {
				//hIndex번 이상 인용된 논문 개수 cnt
				if(num>=hIndex) cnt++;
				else break;
			}
			
			//h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면
			if(cnt>=hIndex&&(n-cnt)<=hIndex) {
				break;
			}
		}

<br>