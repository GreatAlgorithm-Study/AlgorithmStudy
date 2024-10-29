-- 550. Game Play Analysis IV
-- https://leetcode.com/problems/game-play-analysis-iv/?envType=study-plan-v2&envId=top-sql-50
SELECT ROUND(COUNT(DISTINCT A.player_id) / (SELECT COUNT(DISTINCT player_id) FROM activity), 2) AS fraction
FROM activity A
JOIN activity B
on A.player_id = B.player_id
WHERE (A.player_id, A.event_date) in (SELECT player_id, min(event_date) FROM activity group by player_id) AND DATEDIFF(B.event_date, A.event_date) = 1