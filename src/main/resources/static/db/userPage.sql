use basicdb;

--커뮤니티 게시글 테이블
CREATE TABLE post(
post_num int not null auto_increment,
post_type varchar(8) not null,
post_title varchar(80) not null,
post_writer varchar(20) not null,
post_content text not null,
post_date date,
primary key(post_num)
);

--렌탈제품 카테고리
CREATE TABLE category(
item_category varchar(20) not null,
primary key(item_category)
);

INSERT INTO category VALUES('무대');
INSERT INTO category VALUES('자동차');
INSERT INTO category VALUES('상업용가전');
INSERT INTO category VALUES('오피스');
INSERT INTO category VALUES('현장장비');
INSERT INTO category VALUES('악기');
INSERT INTO category VALUES('기타장비');
INSERT INTO category VALUES('해외');

--가영의 멤버테이블
create table member (
memberId int not null auto_increment,
memberEmail varchar(50) not null UNIQUE,
memberPasswd varchar(20) not null,
memberName varchar(20) not null,
memberPhone varchar(11) not null,
memberNickName varchar(10) not null,
memberBirth DATE,
memberPostcode varchar(10),
memberAddress varchar(60),
memberInterest varchar(50),
memberRegDate date,
memberPoint bigint,
memberRole varchar(10),
primary key(memberId)
);


-- 렌탈제품 업로드 아이템 테이블
CREATE TABLE item(
itemId int auto_increment,
domestic boolean not null,
title varchar(100) not null,
name varchar(50) not null,
rentalStartDate date not null,
rentalEndDate date not null,
rentalPrice bigint not null,
address varchar(100) not null,
carrier varchar(50),
content text,
uploadDate date,
folderName varchar(10),
fileName varchar(200),
car_type varchar(20),
car_age varchar(10),
car_fuelType varchar(10),
item_category varchar(20) not null,
memberId int,
primary key(itemId),
foreign key(item_category) references category(item_category) on update cascade on delete cascade,
foreign key(memberId) references member(memberId) on update cascade on delete cascade
);

--렌탈신청 폼작성 테이블
CREATE TABLE rentalForms(
formId int auto_increment,
domestic boolean not null,
userName varchar(20) not null,
userPhone varchar(13)not null,
rentalStartDate date not null,
rentalEndDate date not null,
postcode varchar(10),
address varchar(100),
carrier varchar(50),
userContent text,
itemId int not null,
ownerId int not null,
renterId int not null,
formStatus varchar(10) default 'waiting',
createdAt timestamp,
foreign key(itemId) references item(itemId) on update cascade on delete cascade,
foreign key(ownerId) references member(memberId) on update cascade on delete cascade,
foreign key(renterId) references member(memberId) on update cascade on delete cascade,
primary key(formId)
);

--렌탈신청서를 보내면 렌탈제품 오너가 신청서를 읽었는지 확인체크 테이블
CREATE TABLE checkRequest(
memberId int,
formId int,
checked boolean default false,
primary key(memberId, formId),
foreign key(memberId) references member(memberId)on update cascade on delete cascade,
foreign key(formId) references rentalForms(formId) on update cascade on delete cascade
);

--메시지 문의 테이블
CREATE TABLE message(
msgId int auto_increment,
itemId int not null,
renterId int not null,
ownerId int not null,
sentBy int not null,
msgContent text,
checked boolean default false,
msgTime timestamp default current_timestamp,
primary key(msgId),
foreign key(itemId) references item(itemId) on delete cascade,
foreign key(renterId) references member(memberId) on update cascade on delete cascade,
foreign key(ownerId) references member(memberId) on update cascade on delete cascade
);

--커뮤니티 좋아요 테이블
CREATE TABLE love(
loveId int auto_increment,
memberId int not null,
post_num int not null,
state TINYINT default 0,
foreign key(memberId) references member(memberId) on delete cascade,
foreign key(post_num) references post(post_num) on delete cascade,
primary key(loveId)
);

--커뮤니티 코멘트 테이블
CREATE TABLE comment(
comId int auto_increment,
postNum int,
comWriter varchar(20),
comContent text,
comDate timestamp,
primary key(comId),
foreign key(postNum) references post(post_num) on delete cascade
);

-- 장바구니 DB
CREATE TABLE cart(
cartId int auto_increment,
itemId int not null,
memberId int not null,
formWritten int default 0,
cartTime timestamp default current_timestamp,
primary key(cartId),
foreign key(itemId) references item(itemId) on delete cascade,
foreign key(memberId) references member(memberId) on update cascade on delete cascade
);

-- 신고 DB
CREATE TABLE report(
reportId int auto_increment,
itemId int not null,
renterId int not null,
ownerId int not null,
renterNickName varchar(10) not null,
ownerNickName varchar(10) not null,
reportType varchar(20) not null,
reportContent text,
reportTime timestamp default current_timestamp,
primary key(reportId),
foreign key(itemId) references item(itemId) on delete cascade,
foreign key(renterId) references member(memberId) on update cascade on delete cascade,
foreign key(ownerId) references member(memberId) on update cascade on delete cascade,
foreign key(renterNickName) references member(memberNickName) on update cascade on delete cascade,
foreign key(ownerNickName) references member(memberNickName) on update cascade on delete cascade
);

-- 닉네임 유니크로 변경
alter table member modify column memberNickName varchar(10) UNIQUE;