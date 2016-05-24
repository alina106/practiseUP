

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `chat`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Messages`
--

CREATE TABLE `Messages` (
  `id` int(11) NOT NULL,
  `text` varchar(299) CHARACTER SET cp1251 NOT NULL,
  `date` varchar(299) COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `date` (`date`(255)),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `Messages`
--

INSERT INTO `Messages` (`id`, `text`, `date`, `user_id`) VALUES
(1, 'qqqq', '1111', 1),
(2, 'wwww', '2222', 2),
(3, 'уууу', '3333', 3),
(4, 'ееее', '4444', 4),
(5, 'нннн', '5555', 5),
(6, 'гггг', '6666', 6),
(9, 'щщщщ', '9999', 9),
(10, 'фффф', '1010', 10);

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `name` varchar(299) CHARACTER SET cp1251 NOT NULL,
  UNIQUE KEY `id_3` (`id`),
  UNIQUE KEY `id_4` (`id`),
  KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `Users`
--

INSERT INTO `Users` (`id`, `name`) VALUES
(1, 'алина'),
(2, 'саша'),
(3, 'галя'),
(4, 'дима'),
(5, 'паша'),
(6, 'катя'),
(7, 'вадим'),
(8, 'петя'),
(9, 'аня'),
(10, 'оля');

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `Messages`
--
ALTER TABLE `Messages`
  ADD CONSTRAINT `Messages_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
