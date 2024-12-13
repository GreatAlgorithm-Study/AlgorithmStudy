-- 550. Game Play Analysis IV
-- https://leetcode.com/problems/game-play-analysis-iv/?envType=study-plan-v2&envId=top-sql-50
SELECT ROUND(COUNT(DISTINCT player_id) / (SELECT COUNT(DISTINCT player_id) FROM activity), 2) AS fraction
FROM activity 
WHERE (player_id, DATE_SUB(event_date, INTERVAL 1 DAY)) in (SELECT player_id, min(event_date) FROM activity group by player_id)