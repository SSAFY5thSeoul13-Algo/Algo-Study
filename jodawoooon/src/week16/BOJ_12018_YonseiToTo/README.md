

## BOJ 12018 S3 Yonsei TOTO
- silver3
- 정렬
  

<br>



### 풀이과정
변수
N : 과목 수
M : 성준의 마일리지
P : 과목별 신청한 사람 수 
L : 과목별 수강인원 
tmp[] : 각 과목별 사람들이 신청한 마일리지 arr
minArr[] : 각 과목별 최소 마일리지 arr

1. 각 과목별 사람들이 신청한 마일리지를 tmp에 넣는다.
2. tmp 배열을 오름차순 정렬한다.
			
		
		 Arrays.sort(tmp);

3. 만약 과목별 신청한 사람수가 수강인원보다 같거나 크다면, 정렬된 tmpArr에서 P-L번째 값을 min값으로 둔다.
		

	    if(P>=L) minArr[i] = tmp[P-L];

4.	 만약 과목별 신청한 사람수가 수강인원보다 작다면, 그냥 1 마일리지만 배팅해도 수업을 들을 수 있다.
	

			if(P<L) minArr[i] = 1;

5. 모든 과목에 대한 min 마일리지 값인 minArr이 모두 셋팅되엇다면, minArr을 다시 오름차순 정렬한다
6. 남은 마일리지(M)이 minArr[i]보다 크다면 수강 가능하다.

	   if(M>=minArr[i]) {
			cnt++;
			M -= minArr[i];
		}else break;


<br>


### 결과
메모리 :   12872KB  
시간 :   96ms
 