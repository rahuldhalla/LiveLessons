create table if not exists QUOTE (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type INTEGER,
    quote varchar(1024) NOT NULL
);
