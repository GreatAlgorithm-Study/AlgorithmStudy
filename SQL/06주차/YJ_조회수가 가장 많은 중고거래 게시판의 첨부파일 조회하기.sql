-- https://school.programmers.co.kr/learn/courses/30/lessons/164671
SELECT
    CONCAT('/home/grep/src/',ugf.BOARD_ID,'/',ugf.FILE_ID,ugf.FILE_NAME,ugf.FILE_EXT) FILE_PATH
FROM USED_GOODS_BOARD ugb
INNER JOIN USED_GOODS_FILE ugf
ON ugb.BOARD_ID = ugf.BOARD_ID
WHERE ugb.VIEWS = (SELECT MAX(VIEWS) FROM USED_GOODS_BOARD)
ORDER BY ugf.FILE_ID DESC;