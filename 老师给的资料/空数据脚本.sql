/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/7 19:25:26                            */
/*==============================================================*/


drop  

table  if   exists   V_PERMISSION;

drop table if exists BUS;

drop table if exists LINE;

drop table if exists LINE_STATION_REF;

drop table if exists PERMISSION;

drop table if exists ROLE;

drop table if exists ROLE_PERMISSION_REF;

drop table if exists SCHEDULING;

drop table if exists STATION;

drop table if exists SYSUSER;

/*==============================================================*/
/* Table: BUS                                                   */
/*==============================================================*/
create table BUS
(
   busCode              int not null auto_increment,
   busLicense           varchar(20) not null,
   busType              varchar(25),
   busStatus            varchar(2),
   startTime            datetime,
   primary key (busCode),
   key AK_Key_2 (busLicense)
);

/*==============================================================*/
/* Table: LINE                                                  */
/*==============================================================*/
create table LINE
(
   lineCode             int not null auto_increment,
   lineName             varchar(20),
   status               varchar(2),
   startLineTime        datetime,
   direction            varchar(2),
   primary key (lineCode)
);

/*==============================================================*/
/* Table: LINE_STATION_REF                                      */
/*==============================================================*/
create table LINE_STATION_REF
(
   id                   int not null auto_increment,
   lineCode             int,
   stationCode          int,
   stationOrder         int,
   primary key (id)
);

/*==============================================================*/
/* Table: PERMISSION                                            */
/*==============================================================*/
create table PERMISSION
(
   permissionCode       int not null auto_increment,
   permissionName       varchar(50),
   permissionDescribe   varchar(100),
   primary key (permissionCode)
);

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
create table ROLE
(
   roleCode             int not null auto_increment,
   roleName             varchar(50),
   roledescribe         varchar(100),
   primary key (roleCode)
);

/*==============================================================*/
/* Table: ROLE_PERMISSION_REF                                   */
/*==============================================================*/
create table ROLE_PERMISSION_REF
(
   relationCode         int not null auto_increment,
   roleCode             int,
   permissionCode       int,
   primary key (relationCode)
);

/*==============================================================*/
/* Table: SCHEDULING                                            */
/*==============================================================*/
create table SCHEDULING
(
   code                 int not null auto_increment,
   lineCode             int,
   tcNumber             varchar(25),
   tcTime               varchar(20),
   userCode             int,
   startStation         int,
   endStation           int,
   busLicense           varchar(20),
   primary key (code)
);

/*==============================================================*/
/* Table: STATION                                               */
/*==============================================================*/
create table STATION
(
   stationCode          int not null auto_increment,
   stationName          varchar(50),
   longitude            varchar(50),
   latitude             varchar(50),
   region               varchar(50),
   street               varchar(50),
   primary key (stationCode)
);

/*==============================================================*/
/* Table: SYSUSER                                               */
/*==============================================================*/
create table SYSUSER
(
   code                 int not null auto_increment,
   loginName            varchar(25),
   password             varchar(50),
   name                 varchar(25),
   phone                varchar(11),
   idCard               varchar(25),
   role                 int,
   driving              numeric(10,0),
   status               varchar(25),
   primary key (code)
);

/*==============================================================*/
/* View: V_PERMISSION                                           */
/*==============================================================*/
create  VIEW      V_PERMISSION

  as

SELECT DISTINCT
	sysuser.loginName,
	permission.permissionName
FROM
	sysuser
JOIN role ON sysuser.role = role.roleCode
JOIN role_permission_ref ON role_permission_ref.rolecode = role.roleCode
JOIN permission ON role_permission_ref.permissioncode = permission.permissioncode;

alter table LINE_STATION_REF add constraint FK_Reference_8 foreign key (lineCode)
      references LINE (lineCode) on delete restrict on update restrict;

alter table LINE_STATION_REF add constraint FK_Reference_9 foreign key (stationCode)
      references STATION (stationCode) on delete restrict on update restrict;

alter table ROLE_PERMISSION_REF add constraint FK_Reference_1 foreign key (roleCode)
      references ROLE (roleCode) on delete restrict on update restrict;

alter table ROLE_PERMISSION_REF add constraint FK_Reference_2 foreign key (permissionCode)
      references PERMISSION (permissionCode) on delete restrict on update restrict;

alter table SCHEDULING add constraint FK_Reference_3 foreign key (busLicense)
      references BUS (busLicense) on delete restrict on update restrict;

alter table SCHEDULING add constraint FK_Reference_4 foreign key (lineCode)
      references LINE (lineCode) on delete restrict on update restrict;

alter table SCHEDULING add constraint FK_Reference_5 foreign key (userCode)
      references SYSUSER (code) on delete restrict on update restrict;

alter table SCHEDULING add constraint FK_SCH_REF_STATION_START foreign key (startStation)
      references STATION (stationCode) on delete restrict on update restrict;

alter table SCHEDULING add constraint FK_SCH_REF_STATION_END foreign key (endStation)
      references STATION (stationCode) on delete restrict on update restrict;

alter table SYSUSER add constraint FK_Reference_10 foreign key (role)
      references ROLE (roleCode) on delete restrict on update restrict;

