
create table SEC_USER
(
  userId           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userName         VARCHAR(36) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  ENABLED           BIT NOT NULL 
) ;


create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;


create table USER_ROLE
(
  ID      BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

alter table USER_ROLE
  add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (userId)
  references SEC_USER (userId);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (roleId)
  references SEC_ROLE (roleId);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Scott', '$2a$10$TkGnKFjPn9HhwFzPcvI2SulUMrcumbNWAVEa7lp.2JeQotNUbfRSC', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Tonie', '$2a$10$5M7N9vlrJo9/ZpDCcZGwB.KFBT/ZI3tJCWWVf/vzcdOKvzwFwQMa6', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Adelia', '$2a$10$pIkshZR0jcLeFiT4HW05BeHJ2OnxITNNHP.fENNCcen7Ez1RUHgbm', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Lilianna', '$2a$10$7vGuqYZlsR/0R7qUZh892eO0ZiGDGQ2s3YNR4VWp4hMtmMNqTWYxy', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Galina', '$2a$10$2EQY0HgQXfzI2QMwOM/VKOpQ4l5uXwB45OpmlZuhqHH1ylec3qy1G', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Yulia', '$2a$10$vW4qwozJ/GxCwfASoNdg/OpjfgY89p7tV8o1uhFvbD8.sDJvVN/1q', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Kristina', '$2a$10$k0Go2LcByVLT50CdDjA7AewuqM9Nkcu5SMnwtAFFCbDFvuiCX4rM6', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Maya', '$2a$10$v7pvJr2hQRJsx0fCv2TEfOvGZurM1nuBtKTy5mBXcPxCw3zbpe72e', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Austin', '$2a$10$c9cOsPLNgRCPWjbIioZnyuxv5OrxBNhRwVq0pGzZUfAQLStz7G28G', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Bella', '$2a$10$6GrgnIrgDPLX7QOJhi52RuQPtrANFuNqGlT3YXUXUYCNtlARWKK9.', 1);
 
 

 
insert into sec_role (roleName)
values ('ROLE_PROFESSOR');
 
insert into sec_role (roleName)
values ('ROLE_STUDENT');



 
insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (1, 2);
 
insert into user_role (userId, roleId)
values (2, 2);

insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (4, 2);

insert into user_role (userId, roleId)
values (5, 2);

insert into user_role (userId, roleId)
values (6, 2);

insert into user_role (userId, roleId)
values (7, 2);

insert into user_role (userId, roleId)
values (8, 2);

insert into user_role (userId, roleId)
values (9, 2);

insert into user_role (userId, roleId)
values (10, 2);

insert into user_role (userId, roleId)
values (11, 2);





COMMIT;

