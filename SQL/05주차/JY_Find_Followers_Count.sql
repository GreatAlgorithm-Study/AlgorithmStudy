# Find Followers Count
# https://leetcode.com/problems/find-followers-count/?envType=study-plan-v2&envId=top-sql-50
SELECT USER_ID, COUNT(FOLLOWER_ID) AS FOLLOWERS_COUNT
FROM FOLLOWERS
GROUP BY USER_ID
ORDER BY USER_ID;