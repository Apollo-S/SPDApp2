drop table if exists `user_roles`;
drop table if exists `authorities`;
drop table if exists `users`;
drop table if exists `group_members`;
drop table if exists `group_authorities`;
drop table if exists `groups`;

-- drop table if exists `persistent_logins`;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(150) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists `persistent_logins` (
    `username` varchar(64) NOT NULL,
    `series` varchar(64) NOT NULL PRIMARY KEY,
    `token` varchar(64) NOT NULL,
    `last_used` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insertion

insert into `users`(`username`, `password`, `enabled`) 
values 
	('admin', '5182386375bf4ee4641ca484d17c879e0ce8d0a0f4613266e4cafe72e187548fd764a471a210ee7e', true),
	('user', 'bff81665a6dd72c3d403059e081d1668bec4f44eb1674347e5793075a7be6c18dc605f0ef84f4b7f', true),
	('user_blocked', '0bc86f42c366e70db440a80cbd873f8a8996b155090b92adbbb16ba81a836c642b01e2d79fb3d84a', true);

INSERT INTO user_roles (username, role)
VALUES
	('admin', 'ROLE_ADMIN'),
	('user', 'ROLE_USER');
