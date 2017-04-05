-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2017 at 06:09 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ebilling`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `phno` int(10) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `cuname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cid`, `cname`, `phno`, `email`, `pass`, `cuname`) VALUES
(1, 'abc', 0, 'aaa@t.com', 'abc', 'abc');

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE IF NOT EXISTS `discount` (
  `discountid` int(10) NOT NULL AUTO_INCREMENT,
  `discount` text,
  PRIMARY KEY (`discountid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`discountid`, `discount`) VALUES
(1, 'buy 1 get 1 free'),
(2, 'flat 50% off ');

-- --------------------------------------------------------

--
-- Table structure for table `offer`
--

CREATE TABLE IF NOT EXISTS `offer` (
  `offerid` int(10) NOT NULL AUTO_INCREMENT,
  `offer` text,
  PRIMARY KEY (`offerid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `offer`
--

INSERT INTO `offer` (`offerid`, `offer`) VALUES
(4, 'buy 1 get 1 free on vimbar'),
(5, '50%off on mobiles');

-- --------------------------------------------------------

--
-- Table structure for table `prodcount`
--

CREATE TABLE IF NOT EXISTS `prodcount` (
  `pid` int(10) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  KEY `fk` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `productlist`
--

CREATE TABLE IF NOT EXISTS `productlist` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) DEFAULT NULL,
  `price` float NOT NULL,
  `floor` varchar(10) NOT NULL,
  `side` varchar(10) NOT NULL,
  `section` varchar(10) NOT NULL,
  `weight` int(10) DEFAULT NULL,
  `israre` int(2) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `pname` (`pname`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `productlist`
--

INSERT INTO `productlist` (`pid`, `pname`, `price`, `floor`, `side`, `section`, `weight`, `israre`) VALUES
(1, 'ParleG', 20, 'ground', 'left', 'biscuit', 500, 0),
(2, 'Red Dupatta', 300, 'first', 'mid', 'women', 200, 1);

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE IF NOT EXISTS `purchases` (
  `billid` varchar(50) NOT NULL,
  `pid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `purchaseid` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`purchaseid`),
  KEY `fk_customer` (`cid`),
  KEY `fk_product` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`billid`, `pid`, `cid`, `purchaseid`, `timestamp`, `count`) VALUES
('100', 1, 1, 1, '2017-02-14 18:30:00', 10),
('1wedmar01121600gmt2017abc', 1, 1, 26, '2017-03-01 12:16:04', 5),
('1wedmar01121731gmt2017abc', 1, 1, 27, '2017-03-01 12:17:25', 1),
('1wedmar01124209gmt2017abc', 1, 1, 28, '2017-03-01 12:42:02', 2),
('1monmar06094315gmt2017abc', 1, 1, 29, '2017-03-06 09:47:20', 8),
('1monmar27212721gmt2017abc', 1, 1, 31, '2017-03-28 04:33:23', 1),
('1monmar27212957gmt2017abc', 1, 1, 32, '2017-03-28 04:36:16', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `prodcount`
--
ALTER TABLE `prodcount`
  ADD CONSTRAINT `fk` FOREIGN KEY (`pid`) REFERENCES `productlist` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `customer` (`cid`),
  ADD CONSTRAINT `purchases_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `productlist` (`pid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
