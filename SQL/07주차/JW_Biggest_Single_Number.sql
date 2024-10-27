SELECT
    MAX(num) num
FROM
    (SELECT
        num
    FROM
        MyNumbers
    GROUP BY
        num
    HAVING
        COUNT(num) = 1) temp