
-- tạo Procedure xóa TourDetail theo trường tour_id
DROP PROCEDURE IF EXISTS delete_TourDetail_By_Tour_id;
DELIMITER $$
CREATE PROCEDURE delete_TourDetail_By_Tour_id (
	IN tour_id INT(2) )
BEGIN
	DELETE 
	FROM travelsql.tourdetail
	WHERE travelsql.tourdetail.tour_Id = tour_Id;
END$$
DELIMITER ;

call delete_TourDetail_By_Tour_id(3);

