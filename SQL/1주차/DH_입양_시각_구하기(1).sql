# https://school.programmers.co.kr/learn/courses/30/lessons/59412
# DATETIME 타입이라면 관련 함수 사용하기!
# YEAR(), HOUR(), MINUTE(), MONTHNAME(), ...
# DATE_ADD(), DATE_SUB, TIMEDIFF(), ...
# [주의] WHERE절에는 Alias 사용하지 못한다!!

SELECT HOUR(DATETIME) AS HOUR, COUNT(*) AS COUNT
FROM ANIMAL_OUTS
WHERE HOUR(DATETIME) >= 9 AND HOUR(DATETIME) < 20
GROUP BY HOUR
ORDER BY HOUR