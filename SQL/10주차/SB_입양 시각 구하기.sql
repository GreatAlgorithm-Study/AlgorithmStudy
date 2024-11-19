-- 0시부터 23시까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회

-- RECURSIVE 사용
WITH RECURSIVE TM AS(
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR+1 FROM TM WHERE HOUR < 23
)

SELECT A.HOUR, IFNULL(B.COUNT, 0) AS COUNT
FROM TM A LEFT JOIN (
    SELECT HOUR(DATETIME) AS HOUR, COUNT(HOUR(DATETIME)) AS COUNT
    FROM ANIMAL_OUTS
    GROUP BY HOUR(DATETIME)
) B ON A.HOUR = B.HOUR

-- 세션 변수 사용 방식
SET @HOUR := -1;

SELECT (@HOUR := @HOUR+1) as HOUR,
(SELECT COUNT(*) FROM ANIMAL_OUTS WHERE HOUR(DATETIME) =@HOUR) AS COUNT
FROM ANIMAL_OUTS
WHERE @HOUR < 23

-- 프로시저 사용
DELIMITER $$

CREATE PROCEDURE GetAnimalCountByHour()
BEGIN
    -- 프로시저 내부에서 사용할 변수 선언
    DECLARE i INT DEFAULT 0;                -- i, 반복문의 카운터 역할, 0으로 초기화  
    DECLARE hour_count INT;                 -- 각 시간대의 레코드 개수 저장
    
    -- 시간별 결과를 저장할 임시 테이블 생성
    CREATE TEMPORARY TABLE HourlyCounts (HOUR INT, COUNT INT);
    
    -- 0시부터 23시까지 반복
    WHILE i < 24 DO
        -- 특정 시간대의 COUNT 값을 계산
        SELECT COUNT(*) INTO hour_count 
        FROM ANIMAL_OUTS 
        WHERE HOUR(DATETIME) = i;
        
        -- 결과를 임시 테이블에 저장
        INSERT INTO HourlyCounts (HOUR, COUNT) VALUES (i, hour_count);
        
        -- 시간 증가
        SET i = i + 1;
    END WHILE;

    -- 결과 출력
    SELECT * FROM HourlyCounts;

    -- 임시 테이블 삭제
    DROP TEMPORARY TABLE HourlyCounts;
END$$

DELIMITER ;

CALL GetAnimalCountByHour();