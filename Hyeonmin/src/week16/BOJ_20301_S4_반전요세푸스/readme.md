### [문제풀이]
Deque를 사용해서 풀었습니다. Deque에 순서대로 사람의 번호를 넣고 isForward 변수를 두어서 정방향 여부를 결정했습니다. Deque가 빌 때 까지 while문을 실행해고 while문 안에서 K-1번 반복되는 for문을 통해 Deque에서 값을 빼낸다음 집어넣기를 반복하고 K번째에 해당하는 값을 StringBuilder에 저장했습니다. 값을 하나 삭제할 때 마다 count를 증가시키고 count가 M과 같아지면 count를 0으로 초기화하고 isForward를 !isForward로 변경하여 방향을 바꿔줬습니다.

while문이 끝이나면 StringBuilder에 있는 값을 출력했습니다.

### [결과]
메모리 : 23728 KB
시간 : 272 ms