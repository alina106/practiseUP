SELECT users.name, COUNT(messages.id) FROM users,messages WHERE users.id = messages.user_id AND date > '2016-05-09 00:00:00' AND date <= '2016-05-9 23:59:59' GROUP BY user_id;