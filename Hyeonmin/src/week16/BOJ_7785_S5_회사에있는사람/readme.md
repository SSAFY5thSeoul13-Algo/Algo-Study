### [문제풀이]
TreeSet을 사용해서 풀었습니다. StringTokenizer로 입력의 이름과 출,퇴근 여부를 문자열에 저장하고 출근인 경우 TreeSet에 해당 이름을 추가하고 퇴근인 경우 삭제하였습니다.
입력이 모두 끝이 나면 해당 TreeSet으로 list를 만들어 list를 정렬한 후에 출력했습니다.

처음에는 이름을 하나씩 출력하고 Tree대신에 Hash를 썼는데 StringBuilder와 TreeSet을 쓰니 시간을 줄일 수 있었습니다.

### [결과]
메모리 : 47992 KB
시간 : 492 ms