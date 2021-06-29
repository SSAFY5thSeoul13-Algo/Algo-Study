


## PROGRAMMERS LV2 카펫
- level2
- brute


<br>


### 문제 설명
Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.


### 풀이과정
먼저 카펫의 총 넓이인 sum을 구합니다

    int sum = brown + yellow;

그러면 가로 세로를 곱하면 총 넓이인 sum이 나와야 한다. 
이 때 1개 이상의 yellow 격자가 있고, 그 테두리 1줄이 갈색격자이므로 일단 가로,세로의 길이는 최소 3 이상이다.
따라서 가로,세로가 각각 3이상이고 가로*세로=sum 경우의 수를 찾았다.

    if(i*j==sum) { //가로 세로 경우의 수 i,j
		int cnt = 0;
		for (int x = 0; x < i; x++) {
			for (int y = 0; y < j; y++) {
				if(x==0||y==0||x==i-1||y==j-1) cnt++;
			}
		}
		
		//둘러 싸고 있는 격자의 개수가 brown과 같다면 가로,세로를 찾은 것
		if(cnt==brown) {
			answer[0]=Math.max(i, j); //더 긴게 가로길이
			answer[1]=Math.min(i, j); //더 작은게 세로길이
						
			return answer;
		}
					
	}

그리고 가운데를 제외한 테두리의 개수를 cnt로 체크했습니다.   
이 cnt가 brown의 개수와 같다면 가로, 세로를 구한 것과 같고, 이 때의 가로 세로를 return합니다.

<br>