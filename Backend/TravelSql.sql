-- Drop the database if it already exists
DROP DATABASE IF EXISTS TravelSql;
-- Create database
CREATE DATABASE IF NOT EXISTS TravelSql;
USE TravelSql;

-- Create table user
DROP TABLE IF EXISTS 	`User`;
CREATE TABLE IF NOT EXISTS `User` ( 	
	id 				INT 		UNSIGNED 	AUTO_INCREMENT PRIMARY KEY,
    `firstName` 	NVARCHAR(50) 	NOT NULL,
	`lastName` 		NVARCHAR(50) 	NOT NULL,
	`email` 		VARCHAR(50) 	NOT NULL 	UNIQUE CHECK (LENGTH(`email`) >= 6 AND LENGTH(`email`) <= 50),
    `identityCard`  CHAR(12) 		NOT NULL 	UNIQUE CHECK (LENGTH(`identityCard`) >= 9 AND LENGTH(`identityCard`) <= 12),
	`phoneNumber`	CHAR(11) 		NOT NULL 	UNIQUE CHECK (LENGTH(`phoneNumber`) >=10 AND LENGTH(`phoneNumber`) <= 11),
    `address`		VARCHAR(200) 	NOT NULL,
    `username`	 	VARCHAR(50) 	NOT NULL 	UNIQUE CHECK (LENGTH(`username`) >= 6 AND LENGTH(`username`) <= 50),
	`password` 		VARCHAR(800) 	NOT NULL,
    `role` 			ENUM('ADMIN','CUSTOMER') DEFAULT 'CUSTOMER',
	`status`		TINYINT DEFAULT 0  -- 0: Not Active, 1: Active
);
-- password: 123456
INSERT INTO `User` 	(`firstName`,		`lastName`, 	`email`,					`identityCard`,		`phoneNumber`,		`address`,					`username`,			`password`,															`status`, 	`role`	)
VALUE				(	'Lê'	,		'Văn Chính',	'lechinh737@gmail.com',		'187498868',		'0353309744',		'Diễn Châu, Nghệ AN',		'chinhle',	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1, 		'Admin' ), 
					(	'Nguyễn',		'Thanh Hưng',	'hung122112@gmail.com',		'187495612',		'0325122411',		'Cầu Giấy, Hà Nội',			'thanhhung12@vti',	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1, 		'Admin' ), 
					(	'Cấn'	,		'Tuấn Anh',		'cananh.tuan12@vti.com',	'187432516',		'0356685421',		'Thanh Xuân, Hà Nội',		'can.tuananh@vti',	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1, 		'Admin' ), 
					(	'Nguyễn',		'Anh Toàn',		'toananh123@vti.com',		'187625141',		'0354261544',		'Đông Triều, Quảng Ninh',	'toananh',	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1, 		'Customer' ), 
					(	'Nguyễn',		'Mạnh Hùng',	'manhhung123@vti.com',		'187945215',		'0321565666',		'Tiên Lãng, Hải Phòng',		'manhhung123@vti',	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1, 		'Customer' ),
					(	'Nguyễn',		'Mai Anh',		'maianhng@gmail.com', 		'122548557',		'1356222222',		'Phong Châu, Phú Thọ',		'maianhvti123',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1, 		'Customer');


-- ==================================================================================================================  
-- Create table Registration_User_Token
DROP TABLE IF EXISTS 	`Registration_User_Token`;
CREATE TABLE IF NOT EXISTS `Registration_User_Token` ( 	
	id 				INT 		UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`token`	 		CHAR(36) 	NOT NULL UNIQUE,
	`user_id` 		SMALLINT 	UNSIGNED NOT NULL,
	`expiryDate` 	DATETIME 	NOT NULL
    
);

-- ==================================================================================================================  
-- Create table Reset_Password_Token
DROP TABLE IF EXISTS 	`Reset_Password_Token`;
CREATE TABLE IF NOT EXISTS `Reset_Password_Token` ( 	
	id 				INT 			UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`resetToken`	CHAR(36) 		NOT NULL UNIQUE,
	`user_id` 		SMALLINT 		UNSIGNED NOT NULL,
	`expiryDate` 	DATETIME 		NOT NULL
);

-- ==================================================================================================================  
-- Create table province (tên tỉnh của địa điểm du lịch)
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
	id 				TINYINT 		UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`nameProvince` 	NVARCHAR(30) 	NOT NULL UNIQUE
);
INSERT INTO `province` (`nameProvince`) 
VALUES					('Quảng Nam'),
						('Quảng Ngãi'),
						('Huế'),
						('Đông Bắc');

-- ==================================================================================================================  
-- Create table tourType (thể hiện loại tour là gì VD: Sự kiện, nổi bật, yêu thích....)
DROP TABLE IF EXISTS `tourType`;
CREATE TABLE `tourType` (
	id 				TINYINT 		UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`typeName` 		NVARCHAR(30) 	NOT NULL	UNIQUE
);
INSERT INTO `tourType` (`typeName`) 
VALUES					('Sự kiện'),
						('Nổi bật'),
						('Yêu thích'),
						('Giá rẻ');


-- ==================================================================================================================  
-- Create table loction (thể hiện địa điểm của tour du lịch là ở đâu)
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
	id 				TINYINT 		UNSIGNED 	AUTO_INCREMENT 	PRIMARY KEY,
	`location` 		NVARCHAR(30) 	NOT NULL	UNIQUE,	
    `description`	TEXT 			NOT NULL
);
INSERT INTO `location` ( 	`location`, 				`description`)
 VALUES 				('Phố Cổ Hội An', 		'Một địa danh nổi tiếng ở quảng nam'),
						('Đảo Lý Sơn', 			'Một trong những hàng đảo nổi tiếng nhất ở quảng ngãi'),
						('Cố Đô Huế', 			'Là địa điểm nghĩ ngơi thanh tịnh'),
						('Hà Giang', 			'Hà Giang');
                        
-- ==================================================================================================================                        

-- create table tour ( mô tả qua về tour, lịch trình, địa điểm,....)
DROP TABLE IF EXISTS `tour`;
CREATE TABLE `tour` (
   id 				INT 			UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `province_id` 	TINYINT 		NOT NULL,
  `location_id` 	TINYINT			NOT NULL, 
  `tourType_id` 	TINYINT 		NOT NULL,
  `vehicle` 		NVARCHAR(255) 	NOT NULL,
  `numberOfTour` 	TINYINT		 	NOT NULL,
  `tourName` 		NVARCHAR(255) 	NOT NULL		UNIQUE,
  `dayStart` 		DATETIME 		NOT NULL,
  `dayEnd` 			DATETIME 		NOT NULL,
  `adultPrice` 		FLOAT,
  `childPrice` 		FLOAT,
  `tourDescription` TEXT,
  `createDate`		DATETIME		DEFAULT NOW()	NOT NULL,
  `active`			BOOLEAN DEFAULT 1 -- 0: Not Active, 1: Active
);
INSERT INTO `tour` ( `province_id`, `location_id`, 	`tourType_id`, 	`vehicle`, 	`numberOfTour`, 	`tourName`, 					`dayStart`, 	`dayEnd`, 	 `adultPrice`,	`childPrice`, 	`tourDescription`, 				`createDate`, 	 `active`)
VALUE				('1',			'1',			'1',			'Xe Buýt',	'23',			'Đà Nẵng - Quảng Nam -Quảng Ngãi',	'2022-03-08',	'2022-03-12',	'500000',	'300000',		'Hành trình khám phá Quảng Nam','2022-03-04',	'1'),
					('2',			'2',			'2',			'Xe Khách',	'20',			'Quảng Ngãi đẹp quên lối về',		'2022-03-09',	'2022-03-15',	'700000',	'300000',		'Hành trình khám phá Quảng Ngãi','2022-03-05',	'1'),
                    ('3',			'2',			'2',			'Máy Bay',	'30',			'Huế thành phố đậm chất thơ',		'2022-03-10',	'2022-03-12',	'1200000',	'600000',		'Hành trình khám phá Huế',		'2022-03-04',	'1'),
                    ('4',			'4',			'4',			'Xe Buýt',	'23',			'Du Lịch Miền Bắc - Hạ Long - Sapa','2022-03-03',	'2022-03-07',	'900000',	'500000',		'Hành trình khám phá đất Bắc',	'2022-03-06',	'1');
-- ==================================================================================================================  
-- create table tourDetail ( mô tả chi tiết tour. ngày 1 đi đâu, ngày 2 đi đâu ....)
DROP TABLE IF EXISTS `tourDetail`;
CREATE TABLE `tourDetail` (
	id						INT 		UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `tour_Id`				INT 		NOT NULL,
	`title`					TEXT		NOT NUll,
    `dayDetail`				CHAR(1)		NOT NUll,
    `dayDetailDescription`	TEXT		NOT NULL,
    `image`					TEXT,
    `active`				BOOLEAN DEFAULT 1 -- 0: Not Active, 1: Active
);
INSERT INTO `tourDetail` ( `tour_Id`, 	`title`, `dayDetail`, 	`dayDetailDescription`, 	`image`, 	`active`)
VALUE						('4',		'',			'1',		'Sáng: Quý khách có mặt tại ga quốc nội, sân bay Tân Sơn Nhất trước giờ bay ít nhất ba tiếng. Đại diện công ty Du Lịch Việt đón và hỗ trợ Quý khách làm thủ tục đón chuyến bay đi Hà Nội. Đến sân bay Nội Bài, Hướng dẫn viên đón Đoàn khởi hành đến đi Bắc Kạn. Trưa: Dùng cơm trưa. Đoàn tiếp tục đến Bắc Kạn.   Tối: Dùng cơm tối. Nghỉ đêm tại Khu du lịch Ba Bể.', '', '1'),

 ('4',		'',			'2',		'Sáng: Dùng bữa sáng. Đoàn xuống thuyền khám phá phong cảnh thiên nhiên kỳ thú của hồ Ba Bể - được ví như Vịnh Hạ Long thu nhỏ của tỉnh Bắc Kạn và được người xưa phong tặng \"Thiên nhiên đệ nhất Ba Hồ\". Quý khách đi thuyền tham quan động Puông, Ao Tiên,… tìm hiểu đời sống của người dân tộc Tày, Nùng. Trưa: Dùng cơm trưa. Đoàn khởi hành đến Cao Bằng, là tỉnh miền núi phía Bắc được thiên nhiên ưu đãi rất nhiều khi có núi, rừng, sông, suối trải dài hùng vĩ, bao la... Tối: Đến Cao Bằng, dùng cơm tối. Nhận phòng nghỉ ngơi.', '', '1'),
 
 ('4',		'',			'3',		'Sáng: Dùng bữa sáng. Tham quan di tích Pắc Pó, nơi Chủ tịch Hồ Chí Minh chọn làm chỗ ở sau hơn 30 năm bôn ba ở nước ngoài trở về Tổ quốc (08/02/1941) để trực tiếp lãnh đạo cách mạng Việt Nam với hang Bo Bam, bãi Cò Rạc, hang Cốc Pó, suối Lê-nin, núi Các Mác, suối Nậm. Tiếp tục đến Khu di tích anh hùng liệt sỹ Kim Đồng, nơi có Mộ anh Kim Đồng và tượng đài khang trang. Trưa: Dùng cơm trưa. Đoàn khởi hành đến Lạng Sơn, trên đường đoàn đi qua các địa danh nổi tiếng Ải Chi Lăng, núi Mặt Quỷ. Tối: Dùng cơm tối. Quý khách tự do dạo chợ đêm Kỳ Lừa. Nghỉ đêm tại Lạng Sơn.', '', '1'),
 
  ('4',		'',			'4',		'Sáng: Dùng bữa sáng. Đoàn tham quan Động Tam Thanh - Chùa Tam Thanh, núi Nàng Tô Thị di tích lịch sử văn hoá, danh thắng nổi tiếng của xứ Lạng với câu ca dao: “Đồng Đăng có phố Kỳ Lừa Có nàng Tô Thị, có chùa Tam Thanh” Đoàn khởi hành về Hà Nội. Trưa: Dùng cơm trưa.  Trên đường, Đoàn ghé Bắc Ninh tham quan Đình Bảng - một trong những ngôi đình có kiến trúc đẹp nhất còn tồn tại đến ngày hôm nay. Đến viếng chùa Phật Tích còn gọi là chùa Vạn Phúc (di tích quốc gia), một ngôi chùa nằm ở sườn phía Nam núi Phật Tích. Trong chùa có tượng đức Phật bằng đá thời nhà Lý lớn nhất Việt Nam.  Hướng dẫn viên tiễn đoàn ra sân bay Nội Bài đón chuyến bay về TP.HCM. Kết thúc chuyến tham quan, chia tay đoàn và hẹn gặp lại.', '', '1'),
  
('3',		'',			'1',		'Sáng: Đi chơi   Tối: Dùng cơm tối. Nghỉ đêm tại Khu du lịch.', '', '1'),

 ('3',		'',			'2',		'Sáng: Dùng bữa sáng. Đoàn xuống thuyền khám phá phong cảnh thiên nhiên kỳ thú nơi đây. Tối: Dùng cơm tối. Nhận phòng nghỉ ngơi.', '', '1');

-- ==================================================================================================================  
-- create table tourOrder 
DROP TABLE IF EXISTS `tourOrder`;
CREATE TABLE `tourOrder`(
	id						INT 		UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`tour_Id`				INT			NOT NULL,
    `user_id`				INT,			
    `adultNumber`			TINYINT,
    `childNumber`			TINYINT,
    `orderNote`				TEXT,
    `totalAmount`			FLOAT,
    `dateOder`				DATETIME 	DEFAULT NOW() 	NOT NULL,
    `active`				BOOLEAN DEFAULT TRUE -- 0: đã hủy tour, 1: đã đặt
    
);
INSERT INTO `tourOrder` ( `tour_Id`, `user_id`,	`dateOder`, 	`adultNumber`, 		`childNumber`, 	`orderNote`,				`totalAmount`, 	`active`)
VALUE						('4',		'1',	'2022-02-24',		'2',				'3',		'chú ý đồ dùng mang đi',	'3300000',			'1'),
							('3',		'2',	'2022-02-25',		'2',				'1',		'chú ý đồ thuốc đau bụng',	'1800000',			'1');


-- ==================================================================================================================  
-- create table invoice
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice`(
	id 					INT 		UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `tourOrder_id`		INT			NOT NULL,
    `note`				NVARCHAR(200), -- ghi chú thanh tóan
    `settlementDate` 	DATETIME	DEFAULT NOW()
);
INSERT INTO `invoice` ( `tourOrder_id`, 	 `note`, 								`settlementDate`)
VALUE					(	'1',			'Khách hàng thanh toán qua momo',		'2022-02-24'),	
						(	'2',			'Khách hàng thanh toán qua ebank',		'2022-02-25');


-- ==================================================================================================================  
-- create table tourDetail (để cho khánh du lịch đánh giá tour)
DROP TABLE IF EXISTS `tourReview`;
CREATE TABLE `tourReview`(
	id				INT 		UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`tour_Id`		INT 		NOT NULL,
    `user_Id`		SMALLINT	NOT NULL,
    `rate`			TINYINT(1),
    `comment`		TEXT,
    `reviewDate`	DATETIME	DEFAULT NOW(),
	`active`		TINYINT DEFAULT 0 -- 0: Not Active, 1: Active
);
INSERT INTO `tourReview` ( `tour_Id`, 	`user_Id`, `rate`, 	`comment`, 			`reviewDate`, 	`active`)
VALUE					('4',			'2',		'5',	'Quá tuyệt vời!',	'2022-03-08',	'0');

                        
-- ==================================================================================================================  
-- create table logActive (log những hoạt động của người dùng trong hệ thống)
DROP TABLE IF EXISTS `logActive`;
CREATE TABLE `logActive`(
	id				INT		UNSIGNED	PRIMARY KEY	 	AUTO_INCREMENT,
    `user_id`		INT		NOT NULL,
    `messenger`		TEXT	NOT NULL,
    `level`			ENUM('0','1','2') NOT NULL, -- 0 LOW, 1 MEDIUM, 2 HIGH
    `dateActive`	DATETIME	DEFAULT NOW()
);
INSERT INTO `logActive` ( `user_id`, 	`messenger`, 								`level`, 	`dateActive`)
VALUE					(	'1',		'Quảng Ngãi đẹp quên lối về',				 '2',		'2022-03-05'),	
						(	'2',		'Book tour Huế thành phố đậm chất thơ',		 '2',		'2022-02-02');		




                        

