-- https://leetcode.com/problems/first-letter-capitalization-ii/
-- First Letter Capitalization II
with recursive cte as (
    select content_id, content_text, substring_index(content_text," ",1) as  sub, 1 as n 
    from user_content
    union all
    select content_id, content_text,
    substring_index(substring_index(content_text," ",n+1)," ",-1) as sub, n+1 as n
    from cte
    where n <= length(content_text) - length(replace(content_text," ",''))  ),

ctb as (select content_id,content_text, sub, n
,case when Locate("-",sub)=0 
then concat(ucase(left(sub,1)),lcase(right(sub,length(sub)-1)))
else concat(ucase(left(sub,1)),
            lcase(substr(sub,2,position("-" in sub)-2)),
            '-',ucase(substr(sub,position("-" in sub)+1,1)),
             lcase(right(sub,length(sub)-1-position("-" in sub))) )  end as con
from cte
order by content_id)

select content_id, content_text as original_text, group_concat(con  ORDER BY n SEPARATOR ' ') as converted_text
from ctb
group by content_id, content_text