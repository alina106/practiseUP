

CREATE DATABASE  IF NOT EXISTS `chat` 
/*!40100 DEFAULT CHARACTER SET cp1251 COLLATE cp1251_bin */;
USE `chat`;
--
-- База данных: `chat`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Messages`
--

CREATE TABLE `Messages` (
  `id` int(11) NOT NULL,
  `text` varchar(299) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `Messages`
--

LOCK TABLES `Messages` WRITE;
INSERT INTO `Messages` (`id`, `text`, `date`, `user_id`) VALUES
(1, ‘Hello, my friend!’, ’2016-05-01 05:33:11’, 1),
(2, ‘Hey! How are you?’, '2016-05-02 05:33:11', 2),
(2, ‘hello’, '2016-05-03 05:33:11', 2),
(3, ‘ok ok’, '2016-05-03 05:33:11', 3),
(4, ‘not ok ok’, '2016-05-04 05:33:11', 4),
(5, ‘where am i?’, '2016-05-05 05:33:11', 5),
(6, ‘i dont know’, '2016-05-06 05:33:11', 6),
(9, ‘why?’, '2016-05-07 05:33:11', 9),
(10, ‘WTF’, '2016-05-08 05:33:11', 10);
UNLOCK TABLES;

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `name` varchar(299) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

--
-- Дамп данных таблицы `Users`
--

LOCK TABLES `Users` WRITE;
INSERT INTO `Users` (`id`, `name`) VALUES
(1, 'Alina'),
(2, 'Alex'),
(3, 'Sam'),
(4, 'Nina'),
(5, 'Hector'),
(6, 'Clare'),
(7, 'Lana'),
(8, 'Kate'),
(9, 'James'),
(10, 'Max');
UNLOCK TABLES;


--
-- Ограничения внешнего ключа таблицы `Messages`
--

ALTER TABLE `Messages`
 ADD CONSTRAINT `Messages` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
