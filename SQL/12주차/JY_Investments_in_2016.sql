-- https://leetcode.com/problems/investments-in-2016/
-- Investments in 2016
select round(sum(tiv_2016), 2) as tiv_2016
from insurance
where tiv_2015 in (select tiv_2015 from insurance group by tiv_2015 having count(pid) > 1)
      and (lat, lon) in (select lat, lon from insurance group by lat, lon having count(pid) = 1)