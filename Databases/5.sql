SELECT * FROM users WHERE users.id = messages.user_id AND HAVING COUNT(messages.user_id) > 3;