### [문제풀이]
우선 citations 배열을 정렬한 후 ciataions에 들은 값 중 가장 큰 값을 마지막 인덱스로 갖는 배열 arr을 만들었습니다. 
그 후에 citations의 값을 모두 확인한 후 그 값에 해당하는 인덱스의 arr배열의 값을 증가 시켰습니다.

citations배열의 값의 확인이 모두 끝나면 arr 배열을 뒤에서 부터 탐색하여 idx의 인덱스인 arr배열 값이 idx이상이면 h = idx 로 만들고 종료 했습니다.
arr 배열 같이 idx 보다 작으면 arr[idx-1]에 arr[idx] 값을 더해준 후에 다시 위의 과정을 반복했습니다. 
