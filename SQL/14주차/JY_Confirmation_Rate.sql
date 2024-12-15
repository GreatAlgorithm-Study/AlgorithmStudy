-- 1934. Confirmation Rate
-- https://leetcode.com/problems/confirmation-rate/
SELECT a.user_id
       , CASE
            WHEN COUNT(IF(action = "confirmed", a.user_id, null)) = 0 THEN round(0, 2)
            ELSE ROUND(COUNT(IF(action = "confirmed", a.user_id, null)) / count(*), 2)
         END AS confirmation_rate
FROM signups a
LEFT JOIN confirmations b
ON a.user_id = b.user_id
GROUP BY a.user_id