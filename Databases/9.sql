SELECT users.name, messages.text, messages.date FROM messages, users WHERE LENGTH(Messages.text)>140;