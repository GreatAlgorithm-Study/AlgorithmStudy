# Write your MySQL query statement below
-- 모든 보험 계약자를 위해 2016 tiv_2016 모든 총 투자 가치의 합계를 보고하는 솔루션을 작성
-- 조건 1: 하나 이상의 다른 보험 가입자와 동일한 tiv_2015 값을 갖기
-- 조건 2: 다른 보험 가입자와 같은 도시에 위치하지 않음 
-- 반올림 tiv_2016 소수점 이하 두 자리까지.

SELECT ROUND(SUM(tiv_2016),2) AS tiv_2016
FROM Insurance
WHERE tiv_2015 IN (
    SELECT tiv_2015
    FROM Insurance
    GROUP BY tiv_2015
    HAVING COUNT(tiv_2015) > 1
)
AND
(lat, lon) IN (
    SELECT lat, lon
    FROM Insurance
    GROUP BY lat, lon
    HAVING COUNT(*) = 1
);