SELECT COUNT(*) AS `FISH_COUNT`, B.FISH_NAME `FISH_NAME`
FROM FISH_INFO A
         LEFT JOIN FISH_NAME_INFO B ON A.FISH_TYPE = B.FISH_TYPE
GROUP BY FISH_NAME
ORDER BY FISH_COUNT DESC