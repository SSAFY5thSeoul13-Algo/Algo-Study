### [문제풀이]
HashMap을 사용해서 제목을 key로 팔린 수를 value로 하여 새로 팔린 것은 value를 1로하여 map에 집어넣고 map에 이미 존재하는 책인경우 value값을 읽어와서 +1을 해서 다시 map에 저장했습니다.
HashMap을 정렬하기 위해서 Entiy로 리스트를 만들고 Collections.sort를 사용해서 value의 내림차순으로 정렬을 하되 두 값의 value가 같으면 key값을 비교해서 정렬했습니다.
정렬이 끝난 후에는 리스트의 가장 처음 데이터의 key값을 출력했습니다.

### [결과]
메모리 : 11736 KB
시간 : 80 ms