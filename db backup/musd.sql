-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2014 at 04:56 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `musd`
--

-- --------------------------------------------------------

--
-- Table structure for table `customerdetails`
--

CREATE TABLE IF NOT EXISTS `customerdetails` (
  `Name` varchar(50) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `PhonreNumber` int(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Index` int(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `Index` (`Index`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE IF NOT EXISTS `equipment` (
  `indexx` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(200) DEFAULT NULL,
  `catagory` varchar(200) DEFAULT NULL,
  `rate` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index` (`indexx`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`indexx`, `id`, `name`, `catagory`, `rate`) VALUES
(5, 'EQ0018', 'SSLM5', 'Measures of Length4', 25.2),
(6, 'EQ002', 'DSLM', 'Measures of Length', 100.2),
(10, 'EQ003', '50ml-01L', 'Measures of Capacity', 32.5),
(8, 'EQ004', '02L', 'Measures of Capacity', 32),
(9, 'EQ005', '04L', 'Measures of Capacity', 32),
(11, 'EQ006', '05L', 'Measures of Capacity', 32.5),
(12, 'EQ007', '10L', 'Measures of Capacity', 35.5),
(13, 'EQ008', '20L', 'Measures of Capacity', 35.5),
(14, 'EQ009', '=<2kg W', 'Weights Used In General Trades', 35.6),
(17, 'EQ010', '>=2 kg W', 'Weights Used In General Trades', 35.6),
(18, 'EQ011', 'OIMLW', 'Weights Used In General Trades', 30.2),
(19, 'EQ012', 'AM', 'Liq. dispensing measures', 30.2),
(20, 'EQ013', '05 kg BS', 'Weighting instruments', 34.2),
(21, 'EQ014', '10 kg BS', 'Weighting instruments', 34.5),
(22, 'EQ015', '15 kg BS', 'Weighting instruments', 34.5),
(23, 'EQ016', '20 kg BS', 'Weighting instruments', 20.5),
(24, 'EQ017', '25 kg BS', 'Weighting instruments', 20.5),
(25, 'EQ018', '50 kg BS', 'Weighting instruments', 20.5),
(26, 'EQ019', 'B/F', 'Weighting instruments', 40.2),
(27, 'EQ020', '<20 kg SB', 'Weighting instruments', 25.2),
(28, 'EQ021', '>20 kg SB', 'Weighting instruments', 25.2),
(29, 'EQ022', 'PS1', 'Weighting instruments', 56.2),
(30, 'EQ023', 'PS2', 'Weighting instruments', 56.2),
(31, 'EQ024', 'PS3', 'Weighting instruments', 56.2),
(32, 'EQ025', 'GDD1', 'Weighting instruments', 56.2);

-- --------------------------------------------------------

--
-- Table structure for table `equipmentdetails`
--

CREATE TABLE IF NOT EXISTS `equipmentdetails` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`index`,`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `equipmentdetails`
--

INSERT INTO `equipmentdetails` (`index`, `id`, `name`) VALUES
(4, 'EQCAT001', 'Measures of Length'),
(5, 'EQCAT002', 'Measures of Capacity'),
(6, 'EQCAT003', 'Weights Used In General Trades'),
(7, 'EQCAT004', 'Liq. dispensing measures'),
(8, 'EQCAT005', 'Weighting instruments'),
(9, 'EQCAT006', 'Class II weight Instruments'),
(10, 'EQCAT007', 'Measuring Instruments');

-- --------------------------------------------------------

--
-- Table structure for table `inspector`
--

CREATE TABLE IF NOT EXISTS `inspector` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL DEFAULT '0',
  `name` varchar(200) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `password` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index` (`index`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `inspector`
--

INSERT INTO `inspector` (`index`, `id`, `name`, `district`, `password`) VALUES
(1, 'IP0001', 'Sunil Gunasoma', 'Matara', 123456),
(3, 'IP0002', 'Sarath premasiri', 'Matara', 123456),
(4, 'IP0003', 'Premachandra kalubowila', 'Galle', 4564823),
(5, 'IP0004', 'Priyanthi perera', 'Colombo', 488655),
(6, 'IP0005', 'Saduni harishchandra', 'Trinco', 545656),
(2, 'IP0006', 'Sunil Gunasoma', 'Matara', 45566);

-- --------------------------------------------------------

--
-- Table structure for table `inspectordetails`
--

CREATE TABLE IF NOT EXISTS `inspectordetails` (
  `regid` int(4) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `age` int(3) NOT NULL,
  `address` varchar(80) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `yearofreg` int(5) NOT NULL,
  `disrtict` varchar(50) NOT NULL,
  `grade` varchar(2) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `confirmPassword` varchar(20) NOT NULL,
  PRIMARY KEY (`regid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `inspectordetails`
--

INSERT INTO `inspectordetails` (`regid`, `firstname`, `lastname`, `age`, `address`, `gender`, `yearofreg`, `disrtict`, `grade`, `username`, `password`, `confirmPassword`) VALUES
(1, 'i', 'i', 0, 'jh', 'male', 88, 'matara', 'A', 'h', 'b', 'b'),
(2, 'K.P.', 'Karunadasa', 51, 'Udara,Kadawedduwa,Yatiayana,Matara', 'Male', 1995, 'Matara', 'A', 'karu', '123abc4', '123abc4'),
(3, 'Chandana', 'Gunasoma', 52, 'none', 'male', 1999, 'Colombo', 'B', 'chandana', '123456789', '123456789');

-- --------------------------------------------------------

--
-- Table structure for table `inspector id`
--

CREATE TABLE IF NOT EXISTS `inspector id` (
  `Index` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Stamping Center` text NOT NULL,
  `Details of  the circuit stamping` text NOT NULL,
  `Issued varification No. From` text NOT NULL,
  `Issued varification No. To` text NOT NULL,
  `Cancelled varification No.` text NOT NULL,
  `No. of Traders` int(11) NOT NULL,
  `No. of Manufacturers` int(11) NOT NULL,
  `Amount` text NOT NULL,
  `Date of money deposit` date NOT NULL,
  `Branch` text NOT NULL,
  `Bill No.` varchar(100) NOT NULL,
  `Equipment ID 1` int(11) DEFAULT NULL,
  `Equipment ID 2` int(11) DEFAULT NULL,
  `Equipment ID 3` int(11) DEFAULT NULL,
  `Equipment ID 4` int(11) DEFAULT NULL,
  `Equipment ID 5` int(11) DEFAULT NULL,
  `Equipment ID 6` int(11) DEFAULT NULL,
  `Equipment ID 7` int(11) DEFAULT NULL,
  `Equipment ID 8` int(11) DEFAULT NULL,
  `Equipment ID 9` int(11) DEFAULT NULL,
  `Equipment ID 10` int(11) DEFAULT NULL,
  `Equipment ID 11` int(11) DEFAULT NULL,
  `Equipment ID 12` int(11) DEFAULT NULL,
  `Equipment ID 13` int(11) DEFAULT NULL,
  `Equipment ID 14` int(11) DEFAULT NULL,
  `Equipment ID 15` int(11) DEFAULT NULL,
  `Equipment ID 16` int(11) DEFAULT NULL,
  `Equipment ID 17` int(11) DEFAULT NULL,
  `Equipment ID 18` int(11) DEFAULT NULL,
  `Equipment ID 19` int(11) DEFAULT NULL,
  `Equipment ID 20` int(11) DEFAULT NULL,
  `Equipment ID 21` int(11) DEFAULT NULL,
  `Equipment ID 22` int(11) DEFAULT NULL,
  `Equipment ID 23` int(11) DEFAULT NULL,
  `Equipment ID 24` int(11) DEFAULT NULL,
  `Equipment ID 25` int(11) DEFAULT NULL,
  `Equipment ID 26` int(11) DEFAULT NULL,
  `Equipment ID 27` int(11) DEFAULT NULL,
  `Equipment ID 28` int(11) DEFAULT NULL,
  `Equipment ID 29` int(11) DEFAULT NULL,
  `Equipment ID 30` int(11) DEFAULT NULL,
  `Equipment ID 31` int(11) DEFAULT NULL,
  `Equipment ID 32` int(11) DEFAULT NULL,
  `Equipment ID 33` int(11) DEFAULT NULL,
  `Equipment ID 34` int(11) DEFAULT NULL,
  `Equipment ID 35` int(11) DEFAULT NULL,
  `Equipment ID 36` int(11) DEFAULT NULL,
  `Equipment ID 37` int(11) DEFAULT NULL,
  `Equipment ID 38` int(11) DEFAULT NULL,
  `Equipment ID 39` int(11) DEFAULT NULL,
  `Equipment ID 40` int(11) DEFAULT NULL,
  `Equipment ID 41` int(11) DEFAULT NULL,
  `Equipment ID 42` int(11) DEFAULT NULL,
  `Equipment ID 43` int(11) DEFAULT NULL,
  `Equipment ID 44` int(11) DEFAULT NULL,
  `Equipment ID 45` int(11) DEFAULT NULL,
  PRIMARY KEY (`Date`),
  KEY `Index` (`Index`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `inspector id`
--

INSERT INTO `inspector id` (`Index`, `Date`, `Stamping Center`, `Details of  the circuit stamping`, `Issued varification No. From`, `Issued varification No. To`, `Cancelled varification No.`, `No. of Traders`, `No. of Manufacturers`, `Amount`, `Date of money deposit`, `Branch`, `Bill No.`, `Equipment ID 1`, `Equipment ID 2`, `Equipment ID 3`, `Equipment ID 4`, `Equipment ID 5`, `Equipment ID 6`, `Equipment ID 7`, `Equipment ID 8`, `Equipment ID 9`, `Equipment ID 10`, `Equipment ID 11`, `Equipment ID 12`, `Equipment ID 13`, `Equipment ID 14`, `Equipment ID 15`, `Equipment ID 16`, `Equipment ID 17`, `Equipment ID 18`, `Equipment ID 19`, `Equipment ID 20`, `Equipment ID 21`, `Equipment ID 22`, `Equipment ID 23`, `Equipment ID 24`, `Equipment ID 25`, `Equipment ID 26`, `Equipment ID 27`, `Equipment ID 28`, `Equipment ID 29`, `Equipment ID 30`, `Equipment ID 31`, `Equipment ID 32`, `Equipment ID 33`, `Equipment ID 34`, `Equipment ID 35`, `Equipment ID 36`, `Equipment ID 37`, `Equipment ID 38`, `Equipment ID 39`, `Equipment ID 40`, `Equipment ID 41`, `Equipment ID 42`, `Equipment ID 43`, `Equipment ID 44`, `Equipment ID 45`) VALUES
(2, '2014-11-03', 'Colombp', 'done well', 'VN006', 'VN0010', 'VN109', 2, 2, '5000', '2014-11-04', 'Katubedda', 'BN002', 2, NULL, NULL, NULL, 3, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(1, '2014-11-11', 'Matara', 'done well', 'VN001', 'VN005', 'VN002', 2, 1, '250', '2014-11-12', 'Mataaa', 'BN001', 2, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
