create table if NOT EXISTS users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table if NOT EXISTS authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
-- create unique index if Not EXISTS  ix_auth_username on authorities (username,authority);
SET @index_exists = (SELECT COUNT(*)
    FROM information_schema.statistics
    WHERE table_schema = DATABASE()
    AND table_name = 'authorities'
    AND index_name = 'ix_auth_username');

SET @sql = IF(@index_exists = 0, 'CREATE INDEX ix_auth_username ON authorities (username, authority);', 'SELECT 1;');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
