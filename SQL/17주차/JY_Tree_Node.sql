-- https://leetcode.com/problems/tree-node/
-- 608. Tree Node

SELECT p.id,
       CASE
        WHEN p.p_id IS NULL THEN "Root"
        WHEN c.id IS NULL THEN "Leaf"
        ELSE "Inner"
       END AS type
FROM tree p
LEFT JOIN tree c
ON p.id = c.p_id
GROUP BY p.id