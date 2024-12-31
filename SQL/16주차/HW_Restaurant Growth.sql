-- 지난 7일 동안 방문 날짜 별로 고객 방문 횟수 합과 평균 구하기
-- 문제에서 주어진 계산 방법 : 고객이 7일 기간(현재 날짜 + 6일 전) 동안 지불한 금액의 이동 평균을 계산
-- 핵심 아이디어
-- INTERVAL : SQL에서 날짜 및 시간 연산을 수행
-- 날짜나 시간 값에 특정 기간을 더하거나 뺄 때 사용된다.

SELECT visited_on,
    (
        SELECT SUM(amount) FROM customer
        WHERE visited_on BETWEEN DATE_SUB(c.visited_on, INTERVAL 6 DAY) AND c.visited_on
    ) AS amount,
    ROUND(
        (
        SELECT SUM(amount)/7 -- 7일 평균 계산
        FROM customer
        WHERE visited_on BETWEEN DATE_SUB(c.visited_on, INTERVAL 6 DAY) AND c.visited_on -- 현재 행의 방문 날짜 - 6일 전
        ), 2 -- 반올림 조건
    )AS average_amount
FROM customer c
WHERE visited_on >= (
    SELECT DATE_ADD(MIN(visited_on), INTERVAL 6 DAY) -- 기준 날짜(2019-01-01, MIN(visited_on)에 (+7일) 2019-01-07부터
    FROM Customer
)
GROUP BY visited_on -- 방문 날짜별로 결과를 그룹화
ORDER BY visited_on; -- 오름차순 정렬