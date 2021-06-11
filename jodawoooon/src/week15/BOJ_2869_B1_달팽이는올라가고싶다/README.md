
## BOJ 2869 달팽이는 올라가고 싶다
- bronze
- math

<br>

### 문제풀이
처음에는 단순하게 반복문으로 시도했으나
시간제한을 보고 수식을 세웠습니다.

달팽이가 각 날짜별로 나무 막대를 올라가는 meter수는 다음과 같습니다.
1. A
2. A-B+A
3. A-B+A-B+A
4. A-B+A-B+A-B+A

따라서 day번째 날의 올라간 meter는  

    (A-B)*(day-1)+A

입니다.

이 문제에서 주어진대로 높이가 V미터인 나무를 오르기 위해

    (A-B)*(day-1)+A >= V
    
    day-1 >= (V-A)/(A-B)
    day >= (V-A)/(A-B) + 1
이 되고

    ans = (int)Math.ceil((double)(V-A)/(double)(A-B)) +1;
이 됩니다.  

[막혔던 점]  
int형 ans를 출력할 때, ceil이라는 올림 method를 이용했는데  
이 때 분모와 분자를 각각 double형으로 명시해주지 않아서 정확한 값이 나오지 않았었습니다.

<br>

### 결과
메모리 : 11524KB  
시간 : 76ms
 