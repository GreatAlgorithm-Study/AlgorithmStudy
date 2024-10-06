-- https://school.programmers.co.kr/learn/courses/30/lessons/59411

-- 보호기간이 제일 길었던 동물 두마리 아이디, 이름 조회
-- 보호기간 긴 순

SELECT i.ANIMAL_ID, i.NAME
FROM ANIMAL_INS i
JOIN ANIMAL_OUTS o ON i.ANIMAL_ID = o.ANIMAL_ID
ORDER BY DATEDIFF(o.DATETIME, i.DATETIME) DESC
LIMIT 2;