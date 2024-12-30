-- 1204. Last Person to Fit in the Bus
-- https://leetcode.com/problems/last-person-to-fit-in-the-bus/description/
SELECT a.person_name
FROM Queue a 
JOIN Queue b ON a.turn >= b.turn
GROUP BY a.turn
HAVING SUM(b.weight) <= 1000
ORDER BY SUM(b.weight) DESC
LIMIT 1