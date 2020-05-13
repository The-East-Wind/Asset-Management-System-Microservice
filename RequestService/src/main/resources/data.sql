DELETE FROM request;
INSERT INTO request(requested_from,requested_till,requested_by,requested_for,requested_asset,status) VALUES
('2020-05-05','2020-08-15',10001,10000,10007,'Approved'),
('2020-05-10','2020-07-30',10007,10006,10000,'Rejected'),
('2020-05-13','2020-08-30',10001,10003,10001,'Pending');