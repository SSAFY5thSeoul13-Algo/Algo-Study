

## Programmers Lv2 소수찾기
- level2
- perm
- math
- HashSet


<br>


### 문제 설명
한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.


### 풀이과정

순열을 이용해 char형 배열인 `res`에 숫자를 조합해 저장했습니다.

    for (int i = 0; i < numArr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				res[cnt] = numArr[i];
				perm(cnt+1,numArr,res,visited);
				visited[i] = false;
			}
		}

조합된 숫자가 1개 이상일 경우 이어붙여서 `makeNum`을 만들었습니다

    if(cnt>0) {
			if(res[0]=='0') return;//맨 앞에 0 
			
			//수 이어 붙이기
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < cnt; i++) {
				sb.append(res[i]);
			}

			//만들어진 수 makeNum
			int makeNum = Integer.parseInt(sb.toString()); 

이 때 HashSet인 `primeSet`을 이용해서 중복검사를 진행합니다.
중복된 수가 아니라면 `isPrime` 메소드로 소수검사를 수행합니다.

    private static boolean isPrime(int num) {
		if(num<2) return false;
		if(num==2) return true;
		if(num%2==0) return false;
		
		//소수찾기
		for (int i = 3; i <= (int)Math.sqrt(num); i+=2) {
			if(num%i==0) return false;
		}
		
		return true;
	}

소수일 경우, primeSet에 집어넣고 `count`를 1 증가시킵니다.

    if(!primeSet.contains(makeNum)&&isPrime(makeNum)){
				primeSet.add(makeNum);
				count++;
			}

	

<br>

 