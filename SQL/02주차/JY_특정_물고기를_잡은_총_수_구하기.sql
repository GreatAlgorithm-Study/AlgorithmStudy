-- 특정 물고기를 잡은 총 수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/298518

SELECT COUNT(*) AS FISH_COUNT
FROM FISH_INFO I
JOIN FISH_NAME_INFO F
ON I.FISH_TYPE = F.FISH_TYPE
WHERE FISH_NAME = "BASS" OR FISH_NAME = "SNAPPER"