SELECT users.name, messages.text, CAST(messages.date AS time) [time] FROM messages, users WHERE users.id = messages.user_id AND text LIKE ‘%hello%’;
