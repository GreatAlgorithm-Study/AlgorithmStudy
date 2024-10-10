# Classes More Than 5 Students
# https://leetcode.com/problems/classes-more-than-5-students/?envType=study-plan-v2&envId=top-sql-50
SELECT CLASS
FROM COURSES
GROUP BY CLASS
HAVING COUNT(CLASS) >= 5;