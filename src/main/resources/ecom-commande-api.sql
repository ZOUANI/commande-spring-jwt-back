-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 26 juin 2021 à 23:08
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ecom-commande-api`
--

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` bigint(20) NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `total_paye` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `reference`, `total`, `total_paye`) VALUES
(18, 'cmd-17', 17000, 177),
(7, 'cmd-8', 800, 80),
(8, 'cmd-9', 900, 90),
(9, 'cmd-10', 11111, 11),
(10, 'cmd-11', 1100, 11),
(11, 'cmd-12', 1200000, 12),
(12, 'cmd-13', 13000, 13),
(13, 'cmd-14', 140000, 14),
(14, 'cmd-15', 15000, 15),
(15, 'cmd-16', 16000, 16);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(20);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmjdbaf04ao0egch74ttawr9fo` (`created_by`),
  KEY `FKa5cffmyctb0862coj304faenx` (`updated_by`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `authority`, `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES
(19, 'CHARGE_DE_REQUETE', NULL, NULL, NULL, NULL),
(18, 'AGENT_CAI', NULL, NULL, NULL, NULL),
(17, 'CHEF_DE_SERVICE', NULL, NULL, NULL, NULL),
(16, 'DIRECTEUR', NULL, NULL, NULL, NULL),
(15, 'AGENT_BO', NULL, NULL, NULL, NULL),
(13, 'USER', NULL, NULL, NULL, NULL),
(10, 'ADMIN', NULL, NULL, NULL, NULL),
(20, 'CHARGE_DE_TRAITEMENT_COURRIER', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_changed` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdltbr5t0nljpuuo4isxgslt82` (`created_by`),
  KEY `FK2a54xhceitopkkw1hlo3tkv3i` (`updated_by`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `account_non_expired`, `account_non_locked`, `created_at`, `credentials_non_expired`, `email`, `enabled`, `password`, `password_changed`, `updated_at`, `username`, `created_by`, `updated_by`) VALUES
(12, b'1', b'1', NULL, b'1', 'admin@gmail.com', b'1', '$2a$10$u4mxkJbzRjIKPMt/EQRz9OW39KyEwRqN0627QJOqpwXSgxeY.nonG', b'1', NULL, 'admin@gmail.com', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(20) NOT NULL,
  `role` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26f1qdx6r8j1ggkgras9nrc1d` (`role`),
  KEY `FKmow7bmkl6wduuutk26ypkgmm1` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`id`, `role`, `user`) VALUES
(1, 10, 12);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
