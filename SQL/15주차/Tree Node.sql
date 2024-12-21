SELECT
    t1.id,
    CASE
        WHEN t1.p_id IS NULL THEN 'Root'
        WHEN t2.id IS NULL THEN 'Leaf'
        ELSE 'Inner'
    END AS type
FROM Tree t1
LEFT JOIN Tree t2
ON t1.id = t2.p_id
GROUP BY t1.id;