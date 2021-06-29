


## PROGRAMMERS LV1 모의고사
- level1
- brute


<br>


### 문제 설명

1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.


### 풀이과정
각 학생이 문제를 찍는 방식

    static int[] std1 = {1,2,3,4,5};
	static int[] std2 = {2,1,2,3,2,4,2,5};
	static int[] std3 = {3,3,1,1,2,2,4,4,5,5};



maxList : 가장 높은 점수를 받은 사람

int형 배열인 `cnt`에 각각의 학생이 맞은 점수를 세서 저장한다

    for (int i = 0; i < answers.length; i++) {
			if(answers[i]==std1[i%5]) cnt[0]++; //1번 맞은 개수
			if(answers[i]==std2[i%8]) cnt[1]++; //2번 맞은 개수
			if(answers[i]==std3[i%10]) cnt[2]++; //3번 맞은 개수
		}

for문으로 max값과 maxList를 구했습니다.
그리고 int형 배열인 res에 maxList값을 옮겨 return했습니다.
<br>