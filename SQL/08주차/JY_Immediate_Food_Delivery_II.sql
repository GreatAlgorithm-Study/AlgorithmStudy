-- 1174. Immediate Food Delivery II
-- https://leetcode.com/problems/immediate-food-delivery-ii/?envType=study-plan-v2&envId=top-sql-50
select round(count(customer_id)/(select count(distinct customer_id) from delivery), 4)*100 as immediate_percentage
from delivery
where order_date = customer_pref_delivery_date
      and (customer_id, order_date) in (select customer_id, min(order_date) from delivery group by customer_id)