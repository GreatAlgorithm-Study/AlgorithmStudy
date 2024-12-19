# Write your MySQL query statement below
-- 고객이 7일 동안 지불한 금액의 이동 평균(즉, 현재 날짜 + 6일 전)을 계산
-- 평균_금액은 소수점 이하 두 자리로 반올림 
-- visited_on 오름차순으로 정렬


SELECT visited_on, 
    (
        SELECT SUM(amount)
        FROM Customer
        WHERE visited_on BETWEEN DATE_SUB(c.visited_on, INTERVAL 6 DAY) AND c.visited_on
    ) AS amount,
    ROUND(
        (SELECT SUM(amount) / 7
        FROM Customer
        WHERE visited_on BETWEEN DATE_SUB(c.visited_on, INTERVAL 6 DAY) AND c.visited_on)
        , 2
    ) AS average_amount
FROM Customer c
WHERE visited_on >= (
    SELECT DATE_ADD(min(visited_on), INTERVAL 6 DAY)
    FROM Customer
)
GROUP BY visited_on