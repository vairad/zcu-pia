-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Ned 13. led 2019, 15:34
-- Verze serveru: 10.1.36-MariaDB
-- Verze PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `revoloot-test`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `accounts`
--

CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL,
  `bankCode` int(11) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `prepend` bigint(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `blocked` bit(1) DEFAULT NULL,
  `currency` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `trueAmount` double DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `accounts`
--

INSERT INTO `accounts` (`id`, `bankCode`, `number`, `prepend`, `amount`, `blocked`, `currency`, `trueAmount`, `customer_id`, `product_id`) VALUES
(1, 3666, 30606161, NULL, 4000, b'0', 'CZK', 4000, 1, 1),
(2, 3666, 30606262, NULL, 391.22, b'0', 'EUR', 391.22, 2, 3),
(3, 3666, 30606363, NULL, 36000, b'0', 'CZK', 36000, 3, 1);

-- --------------------------------------------------------

--
-- Struktura tabulky `bankers`
--

CREATE TABLE `bankers` (
  `city` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `houseNo` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `postalCode` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `street` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `secret` bit(1) DEFAULT NULL,
  `user` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `bankers`
--

INSERT INTO `bankers` (`city`, `houseNo`, `postalCode`, `state`, `street`, `email`, `photo`, `secret`, `user`) VALUES
('Technická', '8', 30100, 0, 'Plzeň', 'Admin001@revoloot.cz', 'banker1.png', b'0', 4),
('Technická', '8', 30100, 0, 'Plzeň', 'bank1@revoloot.cz', 'banker2.png', b'0', 5);

-- --------------------------------------------------------

--
-- Struktura tabulky `customers`
--

CREATE TABLE `customers` (
  `birthDate` datetime NOT NULL,
  `cardID` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `city` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `houseNo` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `postalCode` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `street` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `phone` int(11) NOT NULL,
  `personID` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `customers`
--

INSERT INTO `customers` (`birthDate`, `cardID`, `city`, `houseNo`, `postalCode`, `state`, `street`, `email`, `phone`, `personID`, `id`) VALUES
('1993-11-01 00:00:00', '1208C', 'Uživatelov', '152', 54265, 0, 'Živnostenská', 'user0001@user.user', 0, 9311012138, 1),
('1993-11-01 00:00:00', 'AU684GP', 'Uživatelov', '64E', 31200, 0, 'Zaměstnanecká', 'user0002@user.user', 0, 9311012138, 2),
('1993-11-01 00:00:00', 'sad5684', 'Makáčov', '64E', 31200, 1, 'Lenochů', 'test@test.cz', 0, 9311012138, 3);

-- --------------------------------------------------------

--
-- Struktura tabulky `exchanges`
--

CREATE TABLE `exchanges` (
  `id` bigint(20) NOT NULL,
  `fromCur` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `rate` double NOT NULL,
  `toCur` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `validFrom` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `exchanges`
--

INSERT INTO `exchanges` (`id`, `fromCur`, `rate`, `toCur`, `validFrom`) VALUES
(1, 'CZK', 0.0351080553, 'GBP', '2019-01-13 13:09:37'),
(2, 'GBP', 28.4834916, 'CZK', '2019-01-13 13:09:37'),
(3, 'CZK', 0.0391219169, 'EUR', '2019-01-13 13:09:37'),
(4, 'EUR', 25.5611197, 'CZK', '2019-01-13 13:09:37');

-- --------------------------------------------------------

--
-- Struktura tabulky `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4),
(4),
(4);

-- --------------------------------------------------------

--
-- Struktura tabulky `hibernate_sequences`
--

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES
('default', 3);

-- --------------------------------------------------------

--
-- Struktura tabulky `moves`
--

CREATE TABLE `moves` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `bankNote` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `constantSymbol` int(11) DEFAULT NULL,
  `currency` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `bankCode` int(11) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `prepend` bigint(20) DEFAULT NULL,
  `income` bit(1) NOT NULL,
  `message` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `processed` bit(1) NOT NULL,
  `specificSymbol` int(11) DEFAULT NULL,
  `submissionDate` datetime NOT NULL,
  `transferDate` datetime DEFAULT NULL,
  `variableSymbol` int(11) DEFAULT NULL,
  `owner_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `moves`
--

INSERT INTO `moves` (`id`, `amount`, `bankNote`, `constantSymbol`, `currency`, `bankCode`, `number`, `prepend`, `income`, `message`, `note`, `processed`, `specificSymbol`, `submissionDate`, `transferDate`, `variableSymbol`, `owner_id`) VALUES
(1, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'0', 'Moje Zpráva', 'Moje Poznámka', b'1', 66, '2019-01-13 15:30:00', '2019-01-13 15:30:01', 22, 3),
(2, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'0', 'Moje Zpráva', 'Moje Poznámka', b'1', 66, '2019-01-13 15:31:00', '2019-01-13 15:31:01', 22, 3),
(3, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'0', 'Moje Zpráva', 'Moje Poznámka', b'1', 66, '2019-01-13 15:30:00', '2019-01-13 15:30:01', 22, 3),
(4, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'1', 'Moje Zpráva', NULL, b'1', 66, '2019-01-13 15:30:00', '2019-01-13 15:30:01', 22, 1),
(5, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'1', 'Moje Zpráva', NULL, b'1', 66, '2019-01-13 15:30:00', '2019-01-13 15:30:01', 22, 1),
(6, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'0', 'Moje Zpráva', 'Moje Poznámka', b'1', 66, '2019-01-13 15:31:00', '2019-01-13 15:31:02', 22, 3),
(7, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'1', 'Moje Zpráva', NULL, b'1', 66, '2019-01-13 15:31:00', '2019-01-13 15:31:01', 22, 1),
(8, 1000, NULL, 33, 'CZK', 3666, 30606161, NULL, b'1', 'Moje Zpráva', NULL, b'1', 66, '2019-01-13 15:31:00', '2019-01-13 15:31:01', 22, 1),
(9, 10000, NULL, 33, 'CZK', 3666, 30606262, NULL, b'0', 'Za hranice', 'Na eura', b'1', 66, '2019-01-13 15:33:00', '2019-01-13 15:33:01', 22, 3),
(10, 391.22, 'Příjem cizí měny: 10000.0 CZK', 33, 'EUR', 3666, 30606262, NULL, b'1', 'Za hranice', NULL, b'1', 66, '2019-01-13 15:33:00', '2019-01-13 15:33:01', 22, 2);

-- --------------------------------------------------------

--
-- Struktura tabulky `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `marketing` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `shortText` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `terms` text COLLATE utf8_czech_ci
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `products`
--

INSERT INTO `products` (`id`, `available`, `marketing`, `name`, `shortText`, `terms`) VALUES
(1, b'1', b'1', 'Běžný účet', 'Běžný účet pro každého', '<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam</p>'),
(2, b'1', b'0', 'Speciání účet', 'Běžný účet pro každého', '<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam</p>'),
(3, b'1', b'0', 'Super účet', 'Běžný účet pro každého', '<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam.</p><p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam</p>');

-- --------------------------------------------------------

--
-- Struktura tabulky `templates`
--

CREATE TABLE `templates` (
  `id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `constantSymbol` int(11) DEFAULT NULL,
  `currency` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `bankCode` int(11) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `prepend` bigint(20) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `specificSymbol` int(11) DEFAULT NULL,
  `variableSymbol` int(11) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `templates`
--

INSERT INTO `templates` (`id`, `amount`, `constantSymbol`, `currency`, `bankCode`, `number`, `prepend`, `message`, `name`, `note`, `specificSymbol`, `variableSymbol`, `owner_id`, `source_id`) VALUES
(1, 500, 666, 'CZK', 3666, 222, NULL, 'Zpráva příjemci', 'Šablona 0', 'Moje tajná poznámka', NULL, NULL, 3, NULL),
(2, 1000, 33, 'CZK', 3666, 30606161, NULL, 'Moje Zpráva', 'Stovka', 'Moje Poznámka', 66, 22, 3, 3);

-- --------------------------------------------------------

--
-- Struktura tabulky `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `gender` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `login` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_czech_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `users`
--

INSERT INTO `users` (`id`, `created`, `gender`, `login`, `name`, `password`, `surname`) VALUES
(1, '2019-01-13 14:19:20', 'MALE', 'User0001', 'User001', '1000:5887cd78fdf25e891d6b62f6499e74703746dc7fc2b5badc:2737f00130662f2e7e166a75e1227b87cd4742126c419164', 'Přímení'),
(2, '2019-01-13 14:24:09', 'MALE', 'User0002', 'User002', '1000:0490e7392ef1921b88740a07e98fbd25035f40cdbae5b816:ab2499b68156def929d2076be48ba774f15128ffb2220a6f', 'Příjméníčko'),
(3, '2019-01-13 14:29:51', 'MALE', 'test', 'Test', '1000:c3a28d7b046ac71a49ef2645157b5bbee6ee05891c6b8b00:7cf2ddbcb41f747e7234d7e2feb4bb6bd098cfa4ff270cab', 'Testovič'),
(4, '2019-01-13 13:09:37', 'MALE', 'Admin001', 'Michal', '1000:bb7508e998b60425d15d6e0ef3f906431e52744f249df634:799d5b328d89b4125eaa45c90e73341fb3b45622904ec13f', 'Opravář'),
(5, '2019-01-13 13:09:37', 'MALE', 'bank1', 'Michal', '1000:f01a7a67623be5c641ede8904727360f678d0e18aa1c037c:4e0f491c69ccee40c2cc8ce731163ab60243dbe4daa6997e', 'Opravář');

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn6x8pdp50os8bq5rbb792upse` (`customer_id`),
  ADD KEY `FK4dmgcni7d05i3ka4ynr2uqahx` (`product_id`);

--
-- Klíče pro tabulku `bankers`
--
ALTER TABLE `bankers`
  ADD PRIMARY KEY (`user`),
  ADD UNIQUE KEY `UK_e4yr4qu3pn7mxcf4a95ym1leo` (`email`),
  ADD UNIQUE KEY `UK_6ag9i4ifmj46ltc0lp1xy31h5` (`photo`);

--
-- Klíče pro tabulku `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `exchanges`
--
ALTER TABLE `exchanges`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `hibernate_sequences`
--
ALTER TABLE `hibernate_sequences`
  ADD PRIMARY KEY (`sequence_name`);

--
-- Klíče pro tabulku `moves`
--
ALTER TABLE `moves`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4u0c2k0w0q6qr8valjosjtg09` (`owner_id`);

--
-- Klíče pro tabulku `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `templates`
--
ALTER TABLE `templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1nah70jfu9ck93htxiwym9c3b` (`name`),
  ADD KEY `FK4u6w2rbsr5vnv6efys6l045hp` (`owner_id`),
  ADD KEY `FKku6ca30asou62yfr1q80wrhnm` (`source_id`);

--
-- Klíče pro tabulku `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pro tabulku `exchanges`
--
ALTER TABLE `exchanges`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pro tabulku `moves`
--
ALTER TABLE `moves`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pro tabulku `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pro tabulku `templates`
--
ALTER TABLE `templates`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pro tabulku `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
