-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2026 at 03:27 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafe`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `price`, `available`) VALUES
(1, 'Tea', 50, 1),
(2, 'Coffee', 80, 1),
(3, 'Burger', 150, 0),
(4, 'Pizza', 250, 1),
(5, 'Butter Sandwich', 100, 1),
(6, 'Cheese Sandwich', 350, 1),
(7, 'Coca-Cola', 20, 1),
(8, 'Pepsi', 20, 1),
(9, 'Meggie ', 80, 1),
(10, 'Cheese Burger', 200, 1),
(11, 'Cheese Meggie', 140, 1),
(12, 'Chocolate Ice-Cream', 50, 1),
(13, 'Dosa Paper', 50, 1),
(14, 'Cheese Butter Masala Dosa', 300, 1),
(15, 'Masala Dosa', 150, 1),
(16, 'Butter Milk', 20, 1),
(17, 'Chocalate Shake', 250, 1),
(18, 'Paneer Sabji', 250, 1),
(19, 'Paneer Pizza', 350, 1),
(20, 'Cheese Pizza', 200, 1),
(23, 'Chocolate ', 100, 1),
(24, 'Chocolate Pizza', 250, 1),
(30, 'Candy', 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `table_no` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `payment_mode` varchar(20) DEFAULT NULL,
  `order_datetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `table_no`, `item_name`, `price`, `payment_mode`, `order_datetime`) VALUES
(1, 2, 'Tea', 50, 'ONLINE', '2026-02-12 00:00:00'),
(2, 7, 'Butter Sandwich', 100, 'ONLINE', '2026-02-12 00:00:00'),
(3, 1, 'Pizza', 250, 'COD', '2026-02-12 19:10:06'),
(4, 2, 'Pizza', 250, 'COD', '2026-02-12 19:34:38'),
(5, 3, 'Coffee', 80, 'COD', '2026-02-12 19:42:44'),
(6, 4, 'Tea', 50, 'COD', '2026-02-12 19:44:10'),
(7, 5, 'Tea', 50, 'COD', '2026-02-12 19:46:05'),
(8, 3, 'Butter Sandwich', 100, 'COD', '2026-02-12 19:47:20'),
(9, 3, 'Butter Sandwich', 100, 'ONLINE', '2026-02-12 19:47:41'),
(10, 1, 'Cheeseb Sandwich', 350, 'COD', '2026-02-12 19:54:19'),
(11, 1, 'Tea', 50, 'COD', '2026-02-12 19:54:39'),
(12, 1, 'Pizza', 250, 'COD', '2026-02-12 19:54:49'),
(13, 1, 'Pizza', 250, 'ONLINE', '2026-02-12 19:54:59'),
(14, 2, 'Pizza', 250, 'ONLINE', '2026-02-12 19:58:34'),
(15, 1, 'Tea', 50, 'ONLINE', '2026-02-12 20:02:44'),
(16, 6, 'Pizza', 250, 'COD', '2026-02-12 21:04:07'),
(17, 3, 'Coca-Cola', 20, 'ONLINE', '2026-02-12 21:44:52'),
(18, 7, 'Cheese Burger', 200, 'ONLINE', '2026-02-12 23:02:37'),
(19, 7, 'Coca-Cola', 20, 'ONLINE', '2026-02-12 23:02:37'),
(20, 5, 'Cheese Butter Masala Dosa', 300, 'COD', '2026-02-13 12:11:40'),
(21, 5, 'Cheese Butter Masala Dosa', 300, 'COD', '2026-02-13 12:11:40'),
(22, 5, 'Butter Milk', 20, 'COD', '2026-02-13 12:11:40'),
(23, 5, 'Cheese Sandwich', 350, 'COD', '2026-02-13 12:11:40'),
(24, 6, 'Paneer Sabji', 250, 'ONLINE', '2026-02-13 12:13:09'),
(25, 9, 'Paneer Sabji', 250, 'ONLINE', '2026-02-13 12:13:39'),
(26, 9, 'Chocolate Ice-Cream', 50, 'ONLINE', '2026-02-13 12:13:39'),
(27, 10, 'Paneer Sabji', 250, 'COD', '2026-02-13 12:14:14'),
(28, 10, 'Chocolate Ice-Cream', 50, 'COD', '2026-02-13 12:14:14'),
(29, 10, 'Chocalate Shake', 250, 'COD', '2026-02-13 12:14:14'),
(30, 11, 'Paneer Pizza', 350, 'COD', '2026-02-13 12:16:10'),
(31, 12, 'Paneer Pizza', 350, 'ONLINE', '2026-02-13 12:16:33'),
(32, 12, 'Cheese Pizza', 200, 'ONLINE', '2026-02-13 12:16:33'),
(33, 13, 'Paneer Pizza', 350, 'ONLINE', '2026-02-13 12:17:00'),
(34, 13, 'Cheese Pizza', 200, 'ONLINE', '2026-02-13 12:17:00'),
(35, 13, 'Cheese Meggie', 140, 'ONLINE', '2026-02-13 12:17:00'),
(36, 67, 'Pepsi', 20, 'COD', '2026-02-14 10:00:25'),
(37, 12, 'Tea', 50, 'COD', '2026-02-14 10:38:36'),
(38, 1, 'Pani Puri', 50, 'COD', '2026-03-10 14:55:38'),
(39, 1, 'Paneer Pizza', 350, 'COD', '2026-03-10 14:55:38'),
(40, 1, 'Pani Puri', 50, 'COD', '2026-03-10 16:18:52'),
(41, 1, 'Cheese Pizza', 200, 'COD', '2026-03-10 16:18:52'),
(42, 1, 'Pani Puri', 50, 'Online', '2026-03-10 16:20:25'),
(43, 1, 'Pani Puri', 50, 'Online', '2026-03-10 16:20:25'),
(44, 1, 'Paneer Pizza', 350, 'Online', '2026-03-10 16:40:38'),
(45, 1, 'Pepsi', 20, 'Online', '2026-03-10 16:40:38'),
(46, 1, 'Cheese Pizza', 200, 'Online', '2026-03-10 16:49:17'),
(47, 1, 'Chocalate Shake', 250, 'Online', '2026-03-10 16:49:17'),
(48, 1, 'Pepsi', 20, 'Online', '2026-03-10 16:49:17'),
(49, 1, 'Coca-Cola', 20, 'Online', '2026-03-10 16:49:17'),
(50, 1, 'Cheese Pizza', 200, 'Online', '2026-03-10 16:53:29'),
(51, 1, 'Cheese Sandwich', 350, 'Online', '2026-03-10 16:53:29'),
(52, 1, 'Cheese Burger', 200, 'Online', '2026-03-10 16:53:29'),
(53, 1, 'Meggie ', 80, 'Online', '2026-03-10 16:53:29'),
(54, 1, 'Pizza', 250, 'Online', '2026-03-10 18:23:39'),
(55, 1, 'Pepsi', 20, 'Online', '2026-03-10 18:23:39'),
(56, 1, 'Pizza', 250, 'Online', '2026-03-10 18:30:07'),
(57, 1, 'Coffee', 80, 'Online', '2026-03-10 18:30:07'),
(58, 5, 'Pizza', 250, 'Online', '2026-03-10 18:37:21'),
(59, 5, 'Meggie ', 80, 'Online', '2026-03-10 18:37:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
