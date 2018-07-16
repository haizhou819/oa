create table tbl_SendFileRequest
(
   uuid                 varchar(20) not null,
   reqUser              varchar(20),
   reqTime              bigint(20),
   draftUser            varchar(20),
   mainDep              varchar(20),
   urgency              varchar(20),
   securityLevel        varchar(20),
   draftType            varchar(20),
   title                varchar(100),
   contentDesc          varchar(1000),
   fileNum              varchar(20),
   printNums            int(5),
   printer              varchar(20),
   auditor              varchar(20),
   state                varchar(20),
   primary key (uuid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
      
create table tbl_user  (
   uuid   varchar(20)    primary key,
   name   varchar(20)
);
insert into tbl_user(uuid,name,password,authority) values('001','a1','001','1');
insert into tbl_user(uuid,name,password,authority) values('002','a2','002','1');
insert into tbl_user(uuid,name,password,authority) values('003','a3','003','1');
insert into tbl_user(uuid,name,password,authority) values('004','a4','004','1');
insert into tbl_user(uuid,name,password,authority) values('005','a5','005','1');
insert into tbl_user(uuid,name,password,authority) values('006','a6','006','1');
insert into tbl_user(uuid,name,password,authority) values('007','a7','007','1');
insert into tbl_user(uuid,name,password,authority) values('008','a8','008','1');
commit;

create table tbl_uuid
(
   preKey               varchar(20) not null,
   num                  int(20),
   primary key (preKey)
);
