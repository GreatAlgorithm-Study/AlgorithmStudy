-- 602. Friend Requests II: Who Has the Most Friends
-- https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/description/
WITH CTE AS(
    SELECT requester_id AS id
    FROM requestaccepted
    UNION ALL
    SELECT accepter_id AS id
    FROM requestaccepted 
)

SELECT id, COUNT(*) AS num
FROM CTE
GROUP BY id
ORDER BY COUNT(*) DESC
LIMIT 1
