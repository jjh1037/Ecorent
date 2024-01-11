CREATE DATABASES project;

USE project;

국내 post code, 상세주소
해외 address1, address2, city, country, postcode

-- 운송사 db
CREATE TABLE shipping(
id int not null auto_increment, -- primary key
domestic boolean not null, -- 국내/해외 국내: 1, 해외: 0
name varchar(50) not null, -- 배송사 이름
com_num varchar(30) not null, -- 회사 전화번호
fax_num varchar(30), -- 회사 팩스번호
address1 varchar(100) not null, -- 회사 사업장 주소1, ex)부산광역시 부산진구 중앙대로 668
address2 varchar(100), -- 회사 사업장 상세주소 ex) 에이원프라자 4층
city varchar(50) not null, -- 회사 사업장 도시
country varchar(50) not null, -- 회사 사업장 나라
postcode varchar(30) not null, -- 회사 사업장 우편번호
email varchar(50) not null, -- 회사 이메일
m_name varchar(50),
m_email varchar(50),
m_com_num varchar(50),
m_tel_num varchar(50),
regdate date, -- 회원가입일자
orgName varchar(255),
savedFileName varchar(255),
savedFilePathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
primary key(id)
);


-- 견적 db
CREATE TABLE request(
request_id int not null auto_increment,
request_name varchar(30),
request_num varchar(50),
request_email varchar(50),
request_export_country varchar(50),
request_export_city varchar(50),
request_export_date varchar(50),
request_import_country varchar(50),
request_import_city varchar(50),
request_import_date varchar(50),
request_product text,
request_content text,
id int not null, -- 부모 외래키
primary key(request_id),
foreign key(id) references shipping(id)
);
ON UPDATE CASCADE
ON DELETE CASCADE
-- request 요청일자, not null, product 값 입력, 국내 해외 폼 변경



