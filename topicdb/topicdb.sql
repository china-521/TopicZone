
# TopicZone数据库

CREATE DATABASE IF NOT EXISTS topicdb  CHARACTER SET 'utf8';

USE topicdb;

# 创建基础用户表(t_user_basic)
CREATE TABLE IF NOT EXISTS t_user_basic(
user_basic_id INT(11) PRIMARY KEY AUTO_INCREMENT,
login_id VARCHAR(20) NOT NULL UNIQUE KEY,
nick_name VARCHAR(50) NOT NULL,
PASSWORD VARCHAR(20) NOT NULL,
head_image VARCHAR(50) DEFAULT NULL
)CHARACTER SET 'utf8';


# 创建用户详细信息表(t_user_detail)
CREATE TABLE IF NOT EXISTS t_user_detail(
user_detail_id INT(11) PRIMARY KEY,
user_name VARCHAR(20),
user_sex VARCHAR(4),
user_id_number VARCHAR(18) UNIQUE,
user_phone VARCHAR(11) UNIQUE,
user_email VARCHAR(50) UNIQUE,
user_birth DATE,
user_star VARCHAR(10)
)CHARACTER SET 'utf8';

# 创建日志表(t_topic)
CREATE TABLE IF NOT EXISTS t_topic(
topic_id INT(11) PRIMARY KEY AUTO_INCREMENT,
topic_title VARCHAR(50) NOT NULL,
topic_content TEXT NOT NULL,
topic_publish_date TIMESTAMP NOT NULL,
user_basic_id INT(11) NOT NULL
)CHARACTER SET 'utf8';


# 创建评论表(t_reply)
CREATE TABLE IF NOT EXISTS t_reply(
reply_id INT(11) PRIMARY KEY AUTO_INCREMENT,
reply_content VARCHAR(1000) NOT NULL,
user_basic_id INT(11) NOT NULL,
reply_date TIMESTAMP NOT NULL,
topic_id INT(11) NOT NULL,
KEY FK_reply_basic (user_basic_id)
)CHARACTER SET 'utf8';


# 创建作者回复表(t_host_reply)
CREATE TABLE IF NOT EXISTS t_host_reply(
host_reply_id INT(11) PRIMARY KEY AUTO_INCREMENT,
host_reply_content VARCHAR(1000) NOT NULL,
host_reply_date TIMESTAMP NOT NULL,
user_basic_id INT(11) NOT NULL,
reply_id INT(11) NOT NULL,
KEY FK_host_reply_basic(user_basic_id),
KEY FK_host_reply_reply(reply_id)
)CHARACTER SET 'utf8';



# 创建好友表(t_friends)
CREATE TABLE IF NOT EXISTS t_friends(
friends_id INT(11) PRIMARY KEY AUTO_INCREMENT,
user_id INT(11),
friend_id INT(11),
KEY FK_friend_base_uid(user_id),
KEY FK_friend_base_fid(friend_id)
)CHARACTER SET 'utf8';




# 删除数据库
DROP DATABASE topicdb
