-- Biggest Single Number
-- https://leetcode.com/problems/biggest-single-number/?envType=study-plan-v2&envId=top-sql-50
select max(num) as num
from (
    select num
    from mynumbers
    group by num
    having count(num) = 1
) my