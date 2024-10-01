# https://school.programmers.co.kr/learn/courses/30/lessons/284531
# ROUND(숫자, 1): 소숫점 아래 두 번째 자리에서 반올림해서 첫번째까지만 보이기
# CONCAT(STR1, STR2): 두 개의 문자 붙이기
SELECT ROUTE
     , CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1), 'km') `TOTAL_DISTANCE`
     , CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') `AVERAGE_DISTANCE`
FROM SUBWAY_DISTANCE
GROUP BY ROUTE
ORDER BY ROUND(SUM(D_BETWEEN_DIST), 1) DESC