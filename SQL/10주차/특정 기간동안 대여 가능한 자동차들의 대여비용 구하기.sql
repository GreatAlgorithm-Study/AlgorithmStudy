-- https://school.programmers.co.kr/learn/courses/30/lessons/157339

SELECT
    c.CAR_ID,
    c.CAR_TYPE,
    FLOOR((c.DAILY_FEE*30) * (1-(dp.DISCOUNT_RATE/100))) FEE
FROM CAR_RENTAL_COMPANY_CAR c
LEFT JOIN (
    SELECT CAR_ID, MIN(START_DATE) START_DATE, MAX(END_DATE) END_DATE
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY rh
    GROUP BY CAR_ID
) rh
ON c.CAR_ID = rh.CAR_ID
INNER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN dp
ON c.CAR_TYPE = dp.CAR_TYPE
WHERE dp.DURATION_TYPE LIKE '30%'
    AND c.CAR_TYPE IN ('세단','SUV')
    AND '2022-11-01' > rh.END_DATE OR '2022-11-30' < rh.START_DATE
HAVING FEE BETWEEN 500000 AND 1999999
ORDER BY FEE DESC, CAR_TYPE, CAR_ID DESC;