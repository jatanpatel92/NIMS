-- phpMyAdmin SQL Dump
-- version 3.4.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 10, 2012 at 05:04 PM
-- Server version: 5.1.56
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dhruvkp_nims`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_info`
--

CREATE TABLE IF NOT EXISTS `admin_info` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `admin_contactno` bigint(11) DEFAULT NULL,
  `admin_join_date` date DEFAULT NULL,
  `NGO_id` int(11) NOT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `NGO_id` (`NGO_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `admin_info`
--

INSERT INTO `admin_info` (`admin_id`, `admin_name`, `admin_contactno`, `admin_join_date`, `NGO_id`) VALUES
(1, 'Ram Bhai', 9033826750, '2012-01-01', 1),
(3, 'Magan Bhai', 9879605898, '2012-01-02', 1),
(5, 'Kanta Ben', 9876543210, '2012-02-12', 1);

-- --------------------------------------------------------

--
-- Table structure for table `campaign_info`
--

CREATE TABLE IF NOT EXISTS `campaign_info` (
  `camp_id` int(11) NOT NULL AUTO_INCREMENT,
  `set_id` int(11) NOT NULL,
  `camp_name` varchar(20) NOT NULL,
  `last_survey_date` date NOT NULL,
  PRIMARY KEY (`camp_id`),
  KEY `set_id` (`set_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `campaign_info`
--

INSERT INTO `campaign_info` (`camp_id`, `set_id`, `camp_name`, `last_survey_date`) VALUES
(1, 4, 'Dehgam', '2012-02-27'),
(2, 3, 'Ijpura', '2012-03-28'),
(3, 10, 'Dariyapur', '2012-02-09'),
(4, 9, 'Luhar vas', '2012-02-06'),
(5, 13, 'Marutinagar', '2012-01-22'),
(6, 2, 'Danta', '2012-02-12'),
(7, 5, 'Amiyapur', '2012-02-15'),
(8, 7, 'Aazadnagar', '2012-02-14'),
(9, 12, 'Meerapura', '2012-01-06'),
(10, 11, 'Gokuldham', '2012-01-08');

-- --------------------------------------------------------

--
-- Table structure for table `community_info`
--

CREATE TABLE IF NOT EXISTS `community_info` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_name` varchar(20) NOT NULL,
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `community_info`
--

INSERT INTO `community_info` (`com_id`, `com_name`) VALUES
(1, 'Nat-Bajania'),
(2, 'Bhavaiya'),
(3, 'SHIAS'),
(4, 'Vadi-Madari'),
(5, 'Gadaliya'),
(6, 'Vansfoda'),
(7, ' Ghantia'),
(8, 'Salatghera'),
(9, 'Sarania'),
(10, 'Vanjara'),
(11, 'Bajania'),
(12, 'Bhand'),
(13, 'Garudi'),
(14, 'Kathodi'),
(15, 'Shirligar'),
(16, 'Nath'),
(17, 'Bharathari'),
(18, 'Kotvalia'),
(19, 'Turi'),
(20, ' Vitoliya'),
(21, 'Devipujak');

-- --------------------------------------------------------

--
-- Table structure for table `coordinator_info`
--

CREATE TABLE IF NOT EXISTS `coordinator_info` (
  `coord_id` int(11) NOT NULL AUTO_INCREMENT,
  `coord_name` varchar(20) NOT NULL,
  `coord_contactno` bigint(11) DEFAULT NULL,
  `coord_joining_date` date DEFAULT NULL,
  `coord_username` varchar(12) NOT NULL,
  `coord_password` varchar(12) NOT NULL,
  `NGO_id` int(11) NOT NULL,
  PRIMARY KEY (`coord_id`),
  KEY `NGO_id` (`NGO_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `coordinator_info`
--

INSERT INTO `coordinator_info` (`coord_id`, `coord_name`, `coord_contactno`, `coord_joining_date`, `coord_username`, `coord_password`, `NGO_id`) VALUES
(1, 'Bharat', 9033456654, '2012-02-14', 'Bharat', '7777', 1),
(2, 'Chhagan', 8933826750, '2012-03-02', 'chhagan75', '7556', 1),
(3, 'Suresh', 9833826577, '2012-03-28', 'suresh', '555jjj', 1),
(4, 'Ramesh', 9879605588, '2011-10-10', 'ramesh99', '9870efg', 1),
(5, 'Akhilesh', 9000800044, '2011-04-04', 'akki', 'asdf', 1),
(6, 'Manoj', 8870665431, '2010-12-11', 'manoj', '6688', 1),
(7, 'Vinod', 9779615480, '2012-03-13', 'vikki', '007', 1),
(8, 'Darshan', 9033825650, '2012-03-03', 'darshan', 'xyzabc', 1),
(9, 'Naresh', 9634467972, '2012-02-20', 'naresh', '6666', 1),
(10, 'Dharam', 9634467971, '2010-12-21', 'dharma', 'don456', 1);

-- --------------------------------------------------------

--
-- Table structure for table `district_info`
--

CREATE TABLE IF NOT EXISTS `district_info` (
  `dist_id` int(11) NOT NULL AUTO_INCREMENT,
  `dist_name` varchar(20) NOT NULL,
  PRIMARY KEY (`dist_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `district_info`
--

INSERT INTO `district_info` (`dist_id`, `dist_name`) VALUES
(1, 'Sabarkantha'),
(2, 'Mehsana'),
(4, 'Ahmedabad'),
(5, 'Gandhinagar'),
(6, 'Panchmahal'),
(7, 'Surendranagar'),
(8, 'Banaskantha'),
(9, 'Kachchh'),
(10, 'Jamnagar'),
(11, 'Rajkot'),
(12, 'Bhavnagar'),
(13, 'Patan'),
(14, 'Anand'),
(15, 'Porbandar'),
(16, 'Amreli'),
(17, 'Bharuch');

-- --------------------------------------------------------

--
-- Table structure for table `family_info`
--

CREATE TABLE IF NOT EXISTS `family_info` (
  `fam_id` int(11) NOT NULL AUTO_INCREMENT,
  `fam_head` varchar(20) NOT NULL,
  `fam_no_of_members` decimal(2,0) NOT NULL DEFAULT '0',
  `fam_no_of_children` decimal(2,0) NOT NULL DEFAULT '0',
  `fam_last_migrated_from` varchar(20) NOT NULL DEFAULT 'NOT_MIGRATED',
  `fam_traditional_occupation` varchar(20) NOT NULL,
  `fam_daily_income` int(11) NOT NULL,
  `com_id` int(11) DEFAULT NULL,
  `fam_latitude` varchar(12) DEFAULT NULL,
  `fam_longitude` varchar(12) DEFAULT NULL,
  `set_id` int(11) NOT NULL,
  `fam_ration_card_status` varchar(20) NOT NULL DEFAULT 'NOT_APPLIED',
  `fam_ration_card_category` varchar(15) NOT NULL DEFAULT 'BPL',
  `fam_electricity_status` tinyint(1) NOT NULL DEFAULT '0',
  `fam_no_of_handicapped` decimal(2,0) NOT NULL DEFAULT '0',
  `fam_janani_support_status` varchar(20) DEFAULT NULL,
  `fam_loan_application_status` tinyint(1) DEFAULT NULL,
  `fam_water_connection` tinyint(1) DEFAULT NULL,
  `fam_vraddh_pen_scheme` varchar(20) DEFAULT 'NOT_APPLIED',
  `Fam_plot_card_Status` varchar(20) DEFAULT 'NOT_APPLIED',
  `Fam_no_of_children_school` decimal(2,0) NOT NULL DEFAULT '0',
  `Fam_settlement_date` date DEFAULT NULL,
  `Fam_housing_support` tinyint(1) DEFAULT NULL,
  `Fam_widow_pension_scheme` varchar(20) DEFAULT 'NOT_APPLIED',
  PRIMARY KEY (`fam_id`),
  KEY `com_id` (`com_id`),
  KEY `set_id` (`set_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `family_info`
--

INSERT INTO `family_info` (`fam_id`, `fam_head`, `fam_no_of_members`, `fam_no_of_children`, `fam_last_migrated_from`, `fam_traditional_occupation`, `fam_daily_income`, `com_id`, `fam_latitude`, `fam_longitude`, `set_id`, `fam_ration_card_status`, `fam_ration_card_category`, `fam_electricity_status`, `fam_no_of_handicapped`, `fam_janani_support_status`, `fam_loan_application_status`, `fam_water_connection`, `fam_vraddh_pen_scheme`, `Fam_plot_card_Status`, `Fam_no_of_children_school`, `Fam_settlement_date`, `Fam_housing_support`, `Fam_widow_pension_scheme`) VALUES
(1, 'Magan', '4', '2', 'Chiloda', 'Bhavai', 200, 1, NULL, NULL, 1, 'APPLIED_NOT_ISSUED', 'BPL', 1, '0', 'NOT_APPLIED', 1, 1, 'NOT_APPLIED', 'GOVT_ALLOTED', '1', '2009-03-04', 1, 'ISSUED'),
(2, 'Chhagan', '8', '2', 'NOT_MIGRATED', 'Smith', 150, 1, NULL, NULL, 4, 'APPLIED_NOT_ISSUED', 'ANTYODAY', 0, '2', 'ISSUED', 1, 0, 'APPLIED_NOT_ISSUED', 'SELF_OWEND', '2', '2012-01-08', 0, 'NOT_APPLICABLE'),
(3, 'Gopal', '5', '3', 'NOT_MIGRATED', 'Carpenter', 200, 1, NULL, NULL, 6, 'ISSUED', 'BPL', 1, '0', 'ISSUED', 0, 1, 'ISSUED', 'ENCROACHED_GOVT', '3', '2008-03-01', 1, 'NOT_APPLICABLE'),
(4, 'SARJU', '2', '0', 'NOT_MIGRATED', 'FARMER', 100, 3, NULL, NULL, 11, 'ISSUED', 'APL', 1, '0', 'ISSUED', 0, 1, 'NOT_APPLIED', 'ISSUED', '0', NULL, 0, 'NOT_APPLIED'),
(5, 'Karshan', '12', '4', 'Ijpura', 'wood cutter', 500, 2, NULL, NULL, 9, 'ISSUED', 'APL', 1, '0', 'ISSUED', 1, 1, 'ISSUED', 'GOVT_ALLOTED', '4', '2003-07-01', 0, 'NOT_APPLICABLE'),
(6, 'Babu', '9', '3', 'NOT_MIGRATED', 'Cobbler', 300, 2, NULL, NULL, 9, 'ISSUED', 'ANTYODAY', 1, '0', 'APPLIED_NOT_ISSUED', 1, 0, 'ISSUED', 'SELF_OWEND', '0', '2005-12-07', 1, 'NOT_APPLICABLE'),
(7, 'Champa', '3', '0', 'NOT_MIGRATED', 'Blacksmith', 70, 2, NULL, NULL, 10, 'APPLIED_NOT_ISSUED', 'BPL', 1, '0', 'APPLIED_NOT_ISSUED', 1, 1, 'APPLIED_NOT_ISSUED', 'GOVT_ALLOTED', '0', '2012-01-29', 1, 'APPLIED_NOT_ISSUED'),
(8, 'Rameela', '6', '2', 'Baramula', 'Farmer', 0, 4, NULL, NULL, 8, 'NOT_APPLIED', 'BPL', 1, '0', 'NOT_APPLIED', 1, 1, 'NOT_APPLIED', 'GOVT_ALLOTED', '0', '2007-01-08', 1, 'NOT_APPLICABLE'),
(9, 'Kanu', '8', '3', 'Chiloda', 'Farmer', 1200, 4, NULL, NULL, 5, 'ISSUED', 'APL', 1, '2', 'ISSUED', 1, 1, 'APPLIED_NOT_ISSUED', 'ENCROACHED_GOVT', '3', '2012-02-01', 1, 'NOT_APPLICABLE'),
(10, 'Geeta', '5', '2', 'Adalaj', 'Darji', 500, 4, NULL, NULL, 3, 'APPLIED_NOT_ISSUED', 'BPL', 1, '0', 'ISSUED', 1, 1, 'APPLIED_NOT_ISSUED', 'GOVT_ALLOTED', '2', '2012-03-01', 1, 'ISSUED'),
(11, 'Chinu', '9', '4', 'Loda', 'Carpenter', 150, 5, NULL, NULL, 7, 'NOT_APPLIED', 'ISSUED', 0, '1', 'NOT_APPLIED', 1, 1, 'NOT_APPLIED', 'SELF_OWNED', '2', '2012-02-28', 0, 'NOT_APPLICABLE'),
(12, 'Veena', '5', '2', 'Jeet', 'Shopkeeper', 1700, 5, NULL, NULL, 10, 'APPLIED_NOT_ISSUED', 'APL', 1, '0', 'ISSUED', 1, 1, 'NOT_APPLICABLE', 'SELF_OWNED', '1', '2012-03-06', 1, 'APPLIED_NOT_ISSUED'),
(13, 'Purab', '5', '1', 'Chiloda', 'Blacksmith', 600, 5, NULL, NULL, 12, 'ISSUED', 'BPL', 1, '0', 'NOT_APPLIED', 0, 0, 'APPLIED_NOT_ISSUED', 'ENCROACHED_GOVT', '0', '2012-03-02', 0, 'NOT_APPLICABLE'),
(14, 'Dhanesh', '7', '3', 'Laxmipur', 'Shopkeeper', 1000, 6, NULL, NULL, 10, 'ISSUED', 'BPL', 0, '0', 'ISSUED', 0, 1, 'NOT_APPLIED', 'GOVT_ALLOTED', '0', NULL, 0, 'NOT_APPLICABLE'),
(15, 'Birju', '6', '2', 'Ijpura', 'Smith', 200, 6, NULL, NULL, 13, 'APPLIED_NOT_ISSUED', 'BPL', 0, '0', 'APPLIED_NOT_ISSUED', 1, 1, 'NOT_APPLIED', 'SELF_OWNED', '0', NULL, 0, 'NOT_APPLICABLE'),
(16, 'Praveen', '4', '0', 'NOT_MIGRATED', 'Carpenter', 100, 6, NULL, NULL, 14, 'NOT_APPLIED', 'BPL', 0, '0', 'ISSUED', 1, 1, 'NOT_APPLIED', 'GOVT_ALLOTTED', '0', NULL, 1, 'NOT_APPLICABLE'),
(17, 'Gangaram', '7', '2', 'Ijpura', 'Cobler', 400, 7, NULL, NULL, 5, 'ISSUED', 'BPL', 0, '0', 'ISSUED', 0, 1, 'ISSUED', 'ENCROACHED_GOVT', '0', NULL, 1, 'NOT_APPLICABLE'),
(18, 'Kunal', '8', '3', 'hvb', 'Cobler', 150, 7, NULL, NULL, 2, 'NOT_APPLIED', 'BPL', 0, '5', 'ISSUED', 1, 1, 'NOT_APPLIED', 'SELF_OWNED', '6', NULL, 0, 'ISSUED'),
(19, 'Jamunaben', '9', '4', 'Chiloda', 'Carpenter', 300, 7, NULL, NULL, 14, 'ISSUED', 'ANTYODAY', 1, '0', 'ISSUED', 0, 0, 'APPLIED_NOT_ISSUED', 'NOT_APPLIED', '2', NULL, 1, 'ISSUED'),
(21, 'Kunal', '85', '3', 'hvbbxc', 'Cobler', 150, 7, NULL, NULL, 2, 'NOT_APPLIED', 'BPL', 1, '5', 'ISSUED', NULL, 1, 'NOT_APPLIED', 'SELF_OWNED', '6', NULL, 0, 'ISSUED');

-- --------------------------------------------------------

--
-- Table structure for table `item_distribution`
--

CREATE TABLE IF NOT EXISTS `item_distribution` (
  `item_id` int(11) NOT NULL,
  `fam_id` int(11) NOT NULL,
  `no_of_items` int(11) NOT NULL,
  `set_id` int(11) NOT NULL,
  `coord_id` int(11) NOT NULL,
  PRIMARY KEY (`item_id`,`fam_id`),
  KEY `set_id` (`set_id`),
  KEY `set_id_2` (`set_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_distribution`
--

INSERT INTO `item_distribution` (`item_id`, `fam_id`, `no_of_items`, `set_id`, `coord_id`) VALUES
(11, 1, 12, 15, 1),
(11, 3, 2, 15, 1),
(12, 6, 12, 15, 1),
(12, 9, 2, 15, 1),
(13, 7, 2, 15, 1),
(19, 7, 1, 10, 1),
(20, 7, 1, 10, 1),
(20, 12, 3, 10, 1),
(20, 14, 2, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `item_info`
--

CREATE TABLE IF NOT EXISTS `item_info` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(30) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `item_info`
--

INSERT INTO `item_info` (`item_id`, `item_name`) VALUES
(11, 'Blankets'),
(12, 'Solar Lights'),
(13, 'Clothes'),
(19, 'lalit'),
(20, 'parth');

-- --------------------------------------------------------

--
-- Table structure for table `member_info`
--

CREATE TABLE IF NOT EXISTS `member_info` (
  `mem_ID` int(11) NOT NULL,
  `fam_ID` int(11) NOT NULL,
  `mem_name` varchar(20) NOT NULL,
  `mem_birthyear` decimal(4,0) DEFAULT NULL,
  `mem_occupation` varchar(20) DEFAULT NULL,
  `mem_relationwithead` varchar(20) DEFAULT NULL,
  `mem_gender` char(1) DEFAULT NULL,
  `mem_jobcard_status` varchar(20) DEFAULT 'NOT_APPLIED',
  `mem_voterstatus` varchar(20) DEFAULT 'NOT_APPLIED',
  `mem_aadhar_status` varchar(20) DEFAULT 'NOT_APPLIED',
  PRIMARY KEY (`mem_ID`,`fam_ID`),
  KEY `fam_ID` (`fam_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member_info`
--

INSERT INTO `member_info` (`mem_ID`, `fam_ID`, `mem_name`, `mem_birthyear`, `mem_occupation`, `mem_relationwithead`, `mem_gender`, `mem_jobcard_status`, `mem_voterstatus`, `mem_aadhar_status`) VALUES
(1, 1, 'Magan', '1970', 'BHAVAI', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 2, 'CHHAGAN', '1950', 'SMITH', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 3, 'GOPAL', '1980', 'Carpenter', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 4, 'SARJU', '1979', 'FARMER', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 5, 'KARSHAN', '1943', 'WOOD CUTTER', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 6, 'BABU', '1961', 'RETIRED', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 7, 'CHAMPA', '1949', 'RETIRED', 'SELF', 'F', 'ISSUED', 'ISSUED      ', 'NOT_APPLIED'),
(1, 8, 'RAMEELA', '1950', 'HOUSEWIFE', 'SELF', 'F', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 9, 'KANU', '1965', 'FARMER', 'SELF', 'M', 'ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(1, 10, 'GEETA', '1967', 'TAILOR', 'SELF', 'F', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 11, 'Chinu', '1965', 'Carpenter', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 12, 'Veena', '1971', 'Shopkeeper', 'SELF', 'F', 'ISSUED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(1, 13, 'Purab', '1977', 'SMITH', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 14, 'Dhanesh', '1966', 'Shopkeeper', 'SELF', 'M', 'ISSUED', 'ISSUED      ', 'NOT_APPLIED'),
(1, 15, 'Briju', '1980', 'SMITH', 'SELF', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(1, 16, 'Praveen', '1982', 'Carpenter', 'SELF', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(1, 17, 'Gangaram', '1962', 'Cobbler', 'SELF', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(1, 18, 'Kunal', '1980', 'Cobbler', 'SELF', 'M', 'APPLIED_NOT_ISSUED', 'ISSUED  ', 'NOT_APPLIED'),
(1, 19, 'Jamunaben', '1963', 'Carpenter', 'SELF', 'F', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(1, 21, 'Kunal', '1980', 'Cobbler', 'SELF', 'M', 'APPLIED_NOT_ISSUED', 'ISSUED   ', 'NOT_APPLIED'),
(2, 1, 'Rekha', '1994', NULL, NULL, 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(2, 2, 'Savita', '1954', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLICABLE', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(2, 3, 'Ganga', '1985', NULL, 'WIFE', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(2, 4, 'SARLA', '1985', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(2, 5, 'SARITA', '1950', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(2, 6, 'CHAMPA', '1965', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'ISSUED', 'NOT_APPLIED'),
(2, 7, 'KISHAN', '1968', 'BLACKSMITH', 'SON', 'M', 'ISSUED', 'ISSUED      ', 'NOT_APPLIED'),
(2, 8, 'DINESH', '1970', 'FARMER', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(2, 9, 'NILU', '1967', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(2, 10, 'LAKHAN', '1975', 'TAILOR', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(2, 11, 'Vimla', '1969', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'ISSUED', 'NOT_APPLIED'),
(2, 12, 'Leena', '1973', 'Shopkeeper', 'SISTER', 'F', 'ISSUED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(2, 13, 'Savita', '1980', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'ISSUED', 'NOT_APPLIED'),
(2, 14, 'Meera', '1970', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(2, 15, 'Bindya', '1981', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(2, 16, 'Padmini', '1984', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(2, 17, 'Kalavati', '1965', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(2, 18, 'Aarti', '1981', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLIED', 'ISSUED  ', 'NOT_APPLIED'),
(2, 19, 'Manikchand', '1942', NULL, 'FATHER', 'M', 'NOT_APPLIED', 'ISSUED', 'NOT_APPLIED'),
(3, 1, 'Monu', '1996', 'STUDENT', 'SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(3, 2, 'Sonu', '1975', 'TEACHER', 'DAUGHTER_IN_LAW', 'F', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 3, 'Gaurav', '2001', 'STUDENT', 'SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(3, 5, 'LAKHAN', '1970', 'FARMER', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 6, 'KARAN', '1985', 'SHOPKEEPER', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 7, 'SANTOOR', '1975', 'HOUSEWIFE', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'NOT_APPLIED      ', 'NOT_APPLIED'),
(3, 8, 'LAALI', '1975', 'HOUSEWIFE', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(3, 9, 'KANHA', '1985', 'DRIVER', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 10, 'LILA', '1977', 'HOUSEWIFE', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(3, 11, 'Ratan', '1972', 'Carpenter', 'BROTHER', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 12, 'Pushto', '1995', 'Shopkeeper', 'SON', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(3, 13, 'Dashrath', '1985', 'SMITH', 'BROTHER', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 14, 'Mukund', '1969', 'Shopkeeper', 'BROTHER', 'M', 'ISSUED', 'ISSUED      ', 'NOT_APPLIED'),
(3, 15, 'Indu', '1958', NULL, 'MOTHER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(3, 16, 'Keshav', '1960', '', 'FATHER', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 17, 'Mansukh', '1942', NULL, 'FATHER', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(3, 18, 'Veena', '1960', 'HOUSEWIFE', 'MOTHER', 'F', 'NOT_APPLIED', 'NOT_APPLIED         ', 'NOT_APPLIED'),
(3, 19, 'Vidyaben', '1967', 'Carpenter', 'SISTER', 'F', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(4, 1, 'lalita', '1975', 'HOUSEWIFE', 'WIFE', 'F', 'NOT_APPLICABLE', 'ISSUED', 'NOT_APPLIED'),
(4, 2, 'RAJU', '1972', 'SHOPKEEPER', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(4, 3, 'KANNI', '2002', NULL, 'DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(4, 5, 'NANDITA', '1973', 'HOUSEWIFE', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(4, 6, 'ARJUN', '1984', 'FARMER', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(4, 8, 'RAM', '1994', 'STUDENT', 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLIED', 'NOT_APPLIED'),
(4, 9, 'RAJA', '1986', 'FARMER', 'SON', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(4, 10, 'JAMUNA', '1999', 'STUDENT', 'GRAND_DAUGHTER', 'F', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(4, 11, 'Suhasini', '1975', 'HOUSEWIFE', 'SISTER_IN_LAW', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(4, 12, 'Nandini', '1997', 'STUDENT', 'DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLIED      ', 'NOT_APPLIED'),
(4, 13, 'Mahendra', '1954', NULL, 'FATHER', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(4, 14, 'Akriti', '1971', 'HOUSEWIFE', 'SISTER_IN_LAW', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(4, 15, 'Vasant', '1955', NULL, 'FATHER', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(4, 16, 'Mandira', '1962', 'HOUSEWIFE', 'MOTHER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(4, 17, 'Parvati', '1944', NULL, 'MOTHER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(4, 18, 'Harichand', '1958', 'HOUSEWIFE', 'FATHER', 'M', 'ISSUED', 'ISSUED  ', 'NOT_APPLIED'),
(4, 19, 'Madhuriben', '1969', 'Tailor', 'SISTER', 'F', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(5, 2, 'KANHA', '1971', 'SMITH', 'SON', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(5, 3, 'LALLI', '2003', NULL, 'DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(5, 5, 'NAMAN', '1972', 'DRIVER', 'SON', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(5, 6, 'NANDINI', '1990', 'HOUSEWIFE', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(5, 8, 'NANDINI', '1995', NULL, 'GRAND_DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(5, 9, 'CHAMELI', '1989', 'HOUSEWIFE', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(5, 10, 'ANSHU', '1997', 'STUDENT', 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(5, 11, 'Manu', '1992', 'Carpenter', 'SON', 'M', 'APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(5, 12, 'Mukesh', '1998', 'STUDENT', 'NEICE', 'F', 'NOT_APPLIED', 'NOT_APPLIED      ', 'NOT_APPLIED'),
(5, 13, 'Bablu', '1998', 'STUDENT', 'SON', 'M', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(5, 14, 'Paresh', '1990', 'STUDENT', 'SON', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(5, 15, 'Bharat', '2001', 'STUDENT', 'SON', 'M', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(5, 17, 'Sukhiram', '1970', 'Cobbler', 'BROTHER', 'M', 'ISSUED', 'ISSUED', 'NOT_APPLIED'),
(5, 18, 'Surili', '1989', 'STUDENT', 'SISTER', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(5, 19, 'Garimaben', '1972', 'Tailor', 'SISTER', 'F', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(6, 2, 'SHIV', '1993', 'STUDENT', 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLIED', 'NOT_APPLIED'),
(6, 5, 'DEEPA', '1976', 'HOUSEWIFE', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'ISSUED', 'NOT_APPLIED'),
(6, 6, 'KOMAL', '1989', 'FARMER', 'DAUGHTER_IN_LAW', 'F', 'NOT_APPLIED', 'ISSUED', 'NOT_APPLIED'),
(6, 8, 'LOVELY', '1996', 'STUDENT', 'GRAND_DAUGHTER', 'F', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(6, 9, 'SITA', '1989', 'TEACHER', 'DAUGHTER_IN_LAW', 'F', 'APPLIED', 'APPLIED', 'NOT_APPLIED'),
(6, 11, 'Nandu', '1995', 'STUDENT', 'SON', 'M', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(6, 14, 'Payal', '1993', 'STUDENT', 'DAUGHTER', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(6, 15, 'Sanjay', '2003', 'STUDENT', 'SON', 'M', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(6, 17, 'Minesh', '1986', 'Cobbler', 'SON', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(6, 18, 'Punit', '2002', 'STUDENT', 'SON', 'M', 'NOT_APPLIED', 'ISSUED  ', 'NOT_APPLIED'),
(6, 19, 'Vikas', '1982', 'Carpenter', 'SON', 'M', 'APPLIED_NOT_ISSUED', 'ISSUED', 'NOT_APPLIED'),
(7, 2, 'SAKU', '1995', NULL, 'GRAND_DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLCABLE', 'NOT_APPLIED'),
(7, 5, 'LAKSHYA', '1993', 'STUDENT', 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLIED', 'NOT_APPLIED'),
(7, 6, 'SONAL', '2005', 'STUDENT', 'GRAND_DAUGHTER', 'F', 'NOT_APPLCABLE', 'NOT_APPLCABLE', 'NOT_APPLIED'),
(7, 9, 'GITA', '2009', NULL, 'GRAND_DAUGHTER', 'F', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(7, 11, 'Binu', '1997', 'STUDENT', 'DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(7, 14, 'Jay', '1992', 'STUDENT', 'NEPHEW', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED  ', 'NOT_APPLIED'),
(7, 17, 'Mohini', '1990', 'STUDENT', 'DAUGHTER', 'F', 'NOT_APPLIED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(7, 18, 'Manju', '2003', 'STUDENT', 'DAUGHTER', 'F', 'NOT_APPLIED', 'ISSUED  ', 'NOT_APPLIED'),
(7, 19, 'Mohan', '1983', 'Tailor', 'SON', 'M', 'APPLIED_NOT_ISSUED', 'APPLIED_NOT_ISSUED', 'NOT_APPLIED'),
(8, 2, 'SANGEETA', '2000', 'STUDENT', 'GRAND_DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(8, 5, 'LAVANYA', '1994', NULL, 'GRAND_DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(8, 6, 'RAJU', '2006', 'STUDENT', 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(8, 9, 'ROHIT', '2010', NULL, 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(8, 11, 'Pinku', '1994', 'STUDENT', 'NEPHEW', 'M', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(8, 18, 'Sanju', '2005', 'STUDENT', 'DAUGHTER', 'F', 'NOT_APPLIED', 'ISSUED  ', 'NOT_APPLIED'),
(8, 19, 'Neetu', '1994', 'STUDENT', 'NEICE', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(9, 5, 'SIDHU', '1995', 'STUDENT', 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(9, 6, 'AMAN', '2007', 'STUDENT', 'GRAND_SON', 'M', 'NOT_APPLICABLE', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(9, 11, 'Tinu', '1997', 'STUDENT', 'NEICE', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(9, 19, 'Chameli', '1995', 'STUDENT', 'NEICE', 'F', 'NOT_APPLIED', 'NOT_APPLIED', 'NOT_APPLIED'),
(10, 5, 'SASHA', '1995', NULL, 'GRAND_DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(11, 5, 'SAVITA', '1996', NULL, 'GRAND_DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLICABLE', 'NOT_APPLIED'),
(12, 5, 'SANAM', '1997', NULL, 'GRAND_DAUGHTER', 'F', 'NOT_APPLIED', 'NOT_APPLICABLE', 'NOT_APPLIED');

-- --------------------------------------------------------

--
-- Table structure for table `ngo`
--

CREATE TABLE IF NOT EXISTS `ngo` (
  `NGO_name` varchar(20) NOT NULL,
  `NGO_id` int(11) NOT NULL AUTO_INCREMENT,
  `NGO_officelocation` varchar(20) DEFAULT NULL,
  `NGO_type` varchar(20) NOT NULL,
  `NGO_contactno` int(11) DEFAULT NULL,
  `NGO_username` varchar(12) NOT NULL,
  `NGO_password` varchar(12) NOT NULL,
  PRIMARY KEY (`NGO_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ngo`
--

INSERT INTO `ngo` (`NGO_name`, `NGO_id`, `NGO_officelocation`, `NGO_type`, `NGO_contactno`, `NGO_username`, `NGO_password`) VALUES
('VSSM', 1, 'Ahmedabad', 'Tribe Development', 2147483647, 'OM', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `project_info`
--

CREATE TABLE IF NOT EXISTS `project_info` (
  `PROJ_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJ_NAME` varchar(40) NOT NULL,
  `PROJ_ST_DATE` date NOT NULL,
  `PROJ_END_DATE` date NOT NULL,
  `PROJ_MILESTONES` varchar(80) NOT NULL DEFAULT 'NO_MILESTONES',
  `PROJ_CATEGORY` varchar(30) NOT NULL,
  `PROJ_DONORS` varchar(80) NOT NULL,
  `PROJ_SETTLEMENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`PROJ_ID`,`PROJ_SETTLEMENT_ID`),
  KEY `PROJ_SETTLEMENT_ID` (`PROJ_SETTLEMENT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `project_info`
--

INSERT INTO `project_info` (`PROJ_ID`, `PROJ_NAME`, `PROJ_ST_DATE`, `PROJ_END_DATE`, `PROJ_MILESTONES`, `PROJ_CATEGORY`, `PROJ_DONORS`, `PROJ_SETTLEMENT_ID`) VALUES
(1, 'KACHA_GHAR', '2006-03-08', '2007-12-31', '', 'HOUSING', 'MR. ANSARI, MRS. LAYLA, MR. DHANIA', 1),
(1, 'KACHA_GHAR', '2007-11-14', '2012-01-25', '', 'HOUSING', 'MRS. SUJATA, MRS. BENIWAL', 2),
(1, 'KACHA_GHAR', '2012-03-21', '2013-12-12', '', 'HOUSING', 'MR. ANAND, MRS. ANAHITA, MRS. SHIROMANI', 10),
(1, 'KACHA_GHAR', '2009-07-14', '2011-03-17', '', 'HOUSING', 'MR. AKSHAY, MR. RANA, MR. JINDAL, MR. HAMEED', 11),
(2, 'PAKKA_GHAR', '2007-03-14', '2012-03-02', '', 'HOUSING', 'MR. AHMED, MR. SUKUMAR, MR.DHAMDHARE', 1),
(2, 'PAKKA_GHAR', '2011-01-25', '2012-03-21', '', 'HOUSING', 'MRS. SUJATA, MRS. BENIWAL, MR. RANJAN', 2);

-- --------------------------------------------------------

--
-- Table structure for table `settlement_info`
--

CREATE TABLE IF NOT EXISTS `settlement_info` (
  `set_id` int(11) NOT NULL AUTO_INCREMENT,
  `set_name` varchar(20) NOT NULL,
  `vil_id` int(11) NOT NULL,
  PRIMARY KEY (`set_id`),
  KEY `vil_id` (`vil_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `settlement_info`
--

INSERT INTO `settlement_info` (`set_id`, `set_name`, `vil_id`) VALUES
(1, 'Krushnanagar', 3),
(2, 'Danta', 6),
(3, 'Dariyapur', 16),
(4, 'Premnagar', 7),
(5, 'Amiyapur', 4),
(6, 'Dehgam', 4),
(7, 'Aazadnagar', 2),
(8, 'Suthar vas', 15),
(9, 'Luhar vas', 10),
(10, 'Gopal Nagar', 9),
(11, 'Gokuldham', 4),
(12, 'Meerapura', 5),
(13, 'Marutinagar', 8),
(14, 'Govind nagar', 15),
(17, 'danta', 1);

-- --------------------------------------------------------

--
-- Table structure for table `socialmap`
--

CREATE TABLE IF NOT EXISTS `socialmap` (
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `type` varchar(12) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `village` varchar(30) NOT NULL,
  PRIMARY KEY (`latitude`,`longitude`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `socialmap`
--

INSERT INTO `socialmap` (`latitude`, `longitude`, `type`, `Name`, `village`) VALUES
(0, 0, 'ae', 'ade', 'android.widget.EditText@43d33c'),
(23.191, 72.6305, 'atm', 'axis-bank, NIFT', 'gandhinagar'),
(23.2165, 72.6423, 'bus_stop', 'S.T. bus stand', 'gandhinagar'),
(23.2181, 72.6409, 'hospital', 'civil hospital', 'gandhinagar');

-- --------------------------------------------------------

--
-- Table structure for table `survey_info`
--

CREATE TABLE IF NOT EXISTS `survey_info` (
  `fam_id` int(11) NOT NULL,
  `coord_id` int(11) NOT NULL,
  `survey_date` date NOT NULL,
  PRIMARY KEY (`fam_id`,`coord_id`),
  KEY `coord_id` (`coord_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `survey_info`
--

INSERT INTO `survey_info` (`fam_id`, `coord_id`, `survey_date`) VALUES
(2, 1, '2012-02-12'),
(2, 6, '2012-02-03'),
(3, 8, '2012-02-10'),
(3, 10, '2012-01-11'),
(5, 4, '2012-02-13'),
(5, 8, '2012-02-19'),
(6, 4, '2012-02-11'),
(7, 5, '2012-02-26'),
(7, 7, '2012-01-31'),
(8, 2, '2012-02-06');

-- --------------------------------------------------------

--
-- Table structure for table `taluka_info`
--

CREATE TABLE IF NOT EXISTS `taluka_info` (
  `taluka_id` int(11) NOT NULL AUTO_INCREMENT,
  `taluka_name` varchar(11) NOT NULL,
  `dist_id` int(11) NOT NULL,
  PRIMARY KEY (`taluka_id`),
  KEY `dist_id` (`dist_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `taluka_info`
--

INSERT INTO `taluka_info` (`taluka_id`, `taluka_name`, `dist_id`) VALUES
(1, 'Daskroi', 4),
(2, 'Asarwa', 4),
(4, 'Ambaji', 8),
(5, 'Dantivada', 1),
(6, 'Sanand', 4),
(7, 'Deesa', 8),
(8, 'Chanasama', 2),
(9, 'Sami', 2),
(10, 'Palanpur', 8),
(11, 'Dehgam', 4),
(12, 'Danta', 8);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `Head_Name` varchar(20) NOT NULL,
  `No_of_Members` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`Head_Name`, `No_of_Members`) VALUES
('anshul', 5),
('abc', 25);

-- --------------------------------------------------------

--
-- Table structure for table `village_info`
--

CREATE TABLE IF NOT EXISTS `village_info` (
  `vil_id` int(11) NOT NULL AUTO_INCREMENT,
  `vil_name` varchar(20) NOT NULL,
  `taluka_id` int(11) NOT NULL,
  PRIMARY KEY (`vil_id`),
  KEY `taluka_id` (`taluka_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `village_info`
--

INSERT INTO `village_info` (`vil_id`, `vil_name`, `taluka_id`) VALUES
(1, 'Rampur', 1),
(2, 'Ramgadh', 2),
(3, 'Ijpura', 2),
(4, 'Dehgam', 11),
(5, 'Chiloda', 1),
(6, 'Danta', 6),
(7, 'Bopal', 6),
(8, 'Guma', 7),
(9, 'Kalyanpura', 9),
(10, 'Vasavda', 10),
(12, 'Ambaja', 7),
(13, 'Dungarpura', 10),
(14, 'Bilaspur', 9),
(15, 'Sarangpura', 12),
(16, 'Aasol', 5),
(17, 'Memnagar', 1),
(18, 'asdf', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin_info`
--
ALTER TABLE `admin_info`
  ADD CONSTRAINT `admin_info_ibfk_1` FOREIGN KEY (`NGO_id`) REFERENCES `ngo` (`NGO_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `campaign_info`
--
ALTER TABLE `campaign_info`
  ADD CONSTRAINT `campaign_info_ibfk_1` FOREIGN KEY (`set_id`) REFERENCES `settlement_info` (`set_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `coordinator_info`
--
ALTER TABLE `coordinator_info`
  ADD CONSTRAINT `coordinator_info_ibfk_1` FOREIGN KEY (`NGO_id`) REFERENCES `ngo` (`NGO_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `family_info`
--
ALTER TABLE `family_info`
  ADD CONSTRAINT `family_info_ibfk_1` FOREIGN KEY (`com_id`) REFERENCES `community_info` (`com_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `family_info_ibfk_2` FOREIGN KEY (`set_id`) REFERENCES `settlement_info` (`set_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `member_info`
--
ALTER TABLE `member_info`
  ADD CONSTRAINT `member_info_ibfk_1` FOREIGN KEY (`fam_ID`) REFERENCES `family_info` (`fam_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `project_info`
--
ALTER TABLE `project_info`
  ADD CONSTRAINT `project_info_ibfk_1` FOREIGN KEY (`PROJ_SETTLEMENT_ID`) REFERENCES `settlement_info` (`set_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `settlement_info`
--
ALTER TABLE `settlement_info`
  ADD CONSTRAINT `settlement_info_ibfk_1` FOREIGN KEY (`vil_id`) REFERENCES `village_info` (`vil_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `survey_info`
--
ALTER TABLE `survey_info`
  ADD CONSTRAINT `survey_info_ibfk_1` FOREIGN KEY (`fam_id`) REFERENCES `family_info` (`fam_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `survey_info_ibfk_2` FOREIGN KEY (`coord_id`) REFERENCES `coordinator_info` (`coord_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `taluka_info`
--
ALTER TABLE `taluka_info`
  ADD CONSTRAINT `taluka_info_ibfk_1` FOREIGN KEY (`dist_id`) REFERENCES `district_info` (`dist_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `village_info`
--
ALTER TABLE `village_info`
  ADD CONSTRAINT `village_info_ibfk_1` FOREIGN KEY (`taluka_id`) REFERENCES `taluka_info` (`taluka_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
