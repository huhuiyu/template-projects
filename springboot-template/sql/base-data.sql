-- 系统核心功能表数据
use springboot_template;

-- 角色表=========================================================
turncate table tb_role;

insert into tb_role(role_name,role_group,role_info) values('admin','admin','可以管理全部信息的管理员');
insert into tb_role(role_name,role_group,role_info) values('user','user','标准用户');
insert into tb_role(role_name,role_group,role_info) values('app-admin','admin','应用管理员');


-- 用户表=========================================================
turncate table tb_user;

-- 默认管理员数据,密码是admin_pwd
insert into tb_user(username,password,salt,nickname,role) values('admin','d48dc3be25a60dafc4db503fbc36d397','JX1XRO','内置管理员','admin');
-- 默认用户数据，密码是user-pwd
insert into tb_user(username,password,salt,nickname,role) values('user','ffd3935816d6bb5b4a64a3d0f8c61cf1','C3CJXR','内置用户','user');
--  默认应用管理员数据,密码是admin
insert into tb_user(username,password,salt,nickname,role) values('app-admin','3f7e7f18f394e570dbfe0912b1d69123','WF5QS7','内置应用管理员','app-admin');
