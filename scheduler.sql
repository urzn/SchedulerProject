CREATE TABLE schedule(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    pw VARCHAR(255),
    content TEXT,
    createdDate DATETIME,
    updatedDate DATETIME
);

SHOW VARIABLES LIKE 'character_set%';