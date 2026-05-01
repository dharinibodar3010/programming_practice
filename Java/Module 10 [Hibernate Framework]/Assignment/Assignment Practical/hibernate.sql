-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2026 at 09:34 AM
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
-- Database: `hibernate`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `author_id` int(11) NOT NULL,
  `author_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`author_id`, `author_name`) VALUES
(1, 'Chetan Bhagat'),
(2, 'James Gosling'),
(3, 'Chetan Bhagat'),
(4, 'James Gosling'),
(5, 'Chetan Bhagat'),
(6, 'James Gosling');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`book_id`, `title`, `author_id`) VALUES
(1, '2 States', 1),
(2, 'Half Girlfriend', 1),
(3, 'Java Basics', 2),
(4, 'Advanced Java', 2),
(5, '2 States', 3),
(6, 'Half Girlfriend', 3),
(7, 'Java Basics', 4),
(8, 'Advanced Java', 4),
(9, '2 States', 5),
(10, 'Half Girlfriend', 5),
(11, 'Java Basics', 6),
(12, 'Advanced Java', 6);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `course_name`) VALUES
(1, 'Java'),
(2, 'Python'),
(3, 'Java'),
(4, 'Python');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `dep_id` int(11) NOT NULL,
  `dep_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`dep_id`, `dep_name`) VALUES
(1, 'IT'),
(2, 'IT'),
(3, 'IT'),
(4, 'IT');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `department`, `name`, `salary`) VALUES
(2, 'IT', 'Rahul', 50000),
(3, 'IT', 'Rahul', 50000),
(4, 'IT', 'Rahul', 50000),
(5, 'IT', 'Rahul', 50000),
(6, 'IT', 'Rahul', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `employee1`
--

CREATE TABLE `employee1` (
  `emp_id` int(11) NOT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `emp_salary` double DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee1`
--

INSERT INTO `employee1` (`emp_id`, `emp_name`, `emp_salary`, `dep_id`, `profile_id`) VALUES
(1, 'Dharini', 80000, 1, 1),
(2, 'Sweta', 50000, 1, 2),
(3, 'Dharini', 50000, 2, 3),
(4, 'Sweta', 50000, 2, 4),
(5, 'Dharini', 50000, 3, 5),
(6, 'Sweta', 50000, 3, 6),
(7, 'Dharini', 50000, 4, 7),
(8, 'Sweta', 50000, 4, 8);

-- --------------------------------------------------------

--
-- Table structure for table `emp_project`
--

CREATE TABLE `emp_project` (
  `emp_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `emp_project`
--

INSERT INTO `emp_project` (`emp_id`, `project_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 3),
(3, 4),
(4, 3),
(4, 4),
(5, 5),
(5, 6),
(6, 5),
(6, 6),
(7, 7),
(7, 8),
(8, 7),
(8, 8);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(6);

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `aid` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `profile_id` int(11) NOT NULL,
  `profile_address` varchar(255) DEFAULT NULL,
  `profile_phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`aid`, `address`, `phone`, `user_id`, `profile_id`, `profile_address`, `profile_phone`) VALUES
(1, 'rajkot', '9874563210', 1, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `profile1`
--

CREATE TABLE `profile1` (
  `profile_id` int(11) NOT NULL,
  `profile_address` varchar(255) DEFAULT NULL,
  `profile_phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `profile1`
--

INSERT INTO `profile1` (`profile_id`, `profile_address`, `profile_phone`) VALUES
(1, 'Rajkot', '9874561230'),
(2, 'Ahmedabad', '9999999999'),
(3, 'Rajkot', '9874561230'),
(4, 'Ahmedabad', '9999999999'),
(5, 'Rajkot', '9874561230'),
(6, 'Ahmedabad', '9999999999'),
(7, 'Rajkot', '9874561230'),
(8, 'Ahmedabad', '9999999999');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`project_id`, `project_name`) VALUES
(1, 'Project A'),
(2, 'Project B'),
(3, 'Project A'),
(4, 'Project B'),
(5, 'Project A'),
(6, 'Project B'),
(7, 'Project A'),
(8, 'Project B');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `city`, `name`, `student_id`, `student_name`) VALUES
(1, 'Rajkot', 'Dharini', 0, NULL),
(2, NULL, NULL, 0, NULL),
(3, 'rajkot', 'sweta', 0, NULL),
(4, 'rajkot', 'dharini', 0, NULL),
(5, 'rajkot', 'xyz', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student1`
--

CREATE TABLE `student1` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student1`
--

INSERT INTO `student1` (`student_id`, `student_name`) VALUES
(1, 'Dharini'),
(2, 'Sweta'),
(3, 'Dharini'),
(4, 'Sweta');

-- --------------------------------------------------------

--
-- Table structure for table `student_course`
--

CREATE TABLE `student_course` (
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_course`
--

INSERT INTO `student_course` (`student_id`, `course_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 3),
(3, 4),
(4, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`) VALUES
(1, 'dharini');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`author_id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `FKklnrv3weler2ftkweewlky958` (`author_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`dep_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`);

--
-- Indexes for table `employee1`
--
ALTER TABLE `employee1`
  ADD PRIMARY KEY (`emp_id`),
  ADD KEY `FK14ppefc4bmcsnefj4i7nl2tjd` (`dep_id`),
  ADD KEY `FK12jtvrmada1h293biue5mw7ra` (`profile_id`);

--
-- Indexes for table `emp_project`
--
ALTER TABLE `emp_project`
  ADD KEY `FK1yj4c8369e4h9i2s7wldf7cd4` (`project_id`),
  ADD KEY `FK8vbibeiffg8v9d2px2op4k4yf` (`emp_id`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`aid`),
  ADD KEY `FKawh070wpue34wqvytjqr4hj5e` (`user_id`);

--
-- Indexes for table `profile1`
--
ALTER TABLE `profile1`
  ADD PRIMARY KEY (`profile_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student1`
--
ALTER TABLE `student1`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `student_course`
--
ALTER TABLE `student_course`
  ADD KEY `FKejrkh4gv8iqgmspsanaji90ws` (`course_id`),
  ADD KEY `FKtawakdsbxbx86jrhrdpfnvyyi` (`student_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FKklnrv3weler2ftkweewlky958` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`);

--
-- Constraints for table `employee1`
--
ALTER TABLE `employee1`
  ADD CONSTRAINT `FK12jtvrmada1h293biue5mw7ra` FOREIGN KEY (`profile_id`) REFERENCES `profile1` (`profile_id`),
  ADD CONSTRAINT `FK14ppefc4bmcsnefj4i7nl2tjd` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`);

--
-- Constraints for table `emp_project`
--
ALTER TABLE `emp_project`
  ADD CONSTRAINT `FK1yj4c8369e4h9i2s7wldf7cd4` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `FK8vbibeiffg8v9d2px2op4k4yf` FOREIGN KEY (`emp_id`) REFERENCES `employee1` (`emp_id`);

--
-- Constraints for table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `FKawh070wpue34wqvytjqr4hj5e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `student_course`
--
ALTER TABLE `student_course`
  ADD CONSTRAINT `FKejrkh4gv8iqgmspsanaji90ws` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  ADD CONSTRAINT `FKtawakdsbxbx86jrhrdpfnvyyi` FOREIGN KEY (`student_id`) REFERENCES `student1` (`student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
