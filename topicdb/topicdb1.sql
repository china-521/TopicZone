
INSERT INTO t_topic(topic_title,topic_content,topic_publish_date,user_basic_id)
VALUES('杰瑞鼠','汤姆与杰瑞1',NOW(),3);

INSERT INTO t_friends(user_id,friend_id)
VALUES(1,3);

ALTER TABLE t_topic
MODIFY topic_publish_date DATETIME NOT NULL;


ALTER TABLE t_reply
MODIFY reply_date DATETIME NOT NULL;

ALTER TABLE t_host_reply
MODIFY reply_date DATETIME NOT NULL;

ALTER TABLE t_host_reply
CHANGE host_reply_content host_reply_content VARCHAR(50) NOT NULL;

ALTER TABLE t_host_reply
CHANGE reply_date host_reply_date DATETIME NOT NULL;

ALTER TABLE t_host_reply
CHANGE reply_id reply_id INT(11) NOT NULL;
