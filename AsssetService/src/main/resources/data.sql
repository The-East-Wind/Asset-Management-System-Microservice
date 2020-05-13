DELETE FROM asset;
INSERT INTO asset(asset_name,asset_description,availability,asset_category) VALUES
('Hp Pavillion','4Gb RAM-500Gb HDD-i5 7th gen','Available','Laptop'),
('Lenovo Thinkpad','4Gb RAM-256 SSD-i5 8th gen','Available','Laptop'),
('Hp Deskjet','Laser Color Printer','Available','Printer'),
('Epson Ecotank','Laser Color printer with scanner','Available','Printer'),
('D-Link DIR-615','Wireless Router-150 Mbps','Available','Router');

INSERT INTO asset(asset_name,asset_description,availability,asset_category,allotted_to) VALUES
('ASUS Vivobook','8Gb RAM-1Tb HDD-i5 8th gen','Not Available','Laptop',10004),
('Cannon MG2570S','Inkjet Color Printer','Not Available','Printer',10003),
('Tp-Link Archer C6','Wireless Dual Band Router-450 Mbps','Not Available','Router',10000);