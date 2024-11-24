SELECT ROUND(SUM(tiv_2016),2) tiv_2016
FROM (
    SELECT
        i.pid, i.tiv_2016
    FROM Insurance i
    INNER JOIN Insurance sub
    ON i.tiv_2015 = sub.tiv_2015
    AND i.pid != sub.pid
    LEFT JOIN (
        SELECT pid, lat, lon
        FROM Insurance
    ) exclude
    ON i.lat = exclude.lat AND i.lon = exclude.lon AND i.pid != exclude.pid
    WHERE exclude.pid IS NULL
    GROUP BY i.pid, i.tiv_2016
) SUB;