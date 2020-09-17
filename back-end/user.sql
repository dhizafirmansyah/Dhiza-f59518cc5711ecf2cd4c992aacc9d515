-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2020 at 09:41 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysqlandroid`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tgl_daftar` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nohp` varchar(255) NOT NULL,
  `login_time` varchar(255) NOT NULL,
  `login_state` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `tgl_daftar`, `nohp`, `login_time`, `login_state`) VALUES
(0, 'dhiza', 'dhiza123', 'dhizafirmansyah40@gmail.com', '2020-04-21 20:38:57', '088805415140', '13:00', '0'),
(19, 'paeman', '1234567890', 'paeman@gmail.com', '2020-04-22 16:05:48', '085648748099', '', ''),
(26, 'paikem99', '1234567890', 'paikem99@gmail.com', '2020-09-14 11:46:59', '085709998909', '', ''),
(28, 'zanky udin', '', 'zanky@gmail.com', '2020-09-15 14:03:15', '', '', ''),
(68, 'Paimin', '', 'Paimin@gmail.com', '2020-09-15 21:23:10', '', '', ''),
(70, 'Lordpaeransport', '', 'lord@gmail.com', '2020-09-16 11:38:26', '', '', ''),
(71, '', '', '', '2020-09-17 14:37:24', '', '', 'login_state');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
