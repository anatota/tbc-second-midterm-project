CREATE SCHEMA Locations

CREATE DATABASE LOCATIONS_DB

use LOCATIONS_DB

CREATE TABLE location_cases
(
    id                   INT,
    area                 NVARCHAR(100),
);

INSERT INTO location_cases
VALUES (1, N'დიღმის მასივი'),
       (2, N'რუსთაველი'),
       (3, N'მელიქიშვილი'),
       (4, N'ჭავჭავაძის'),
       (5, N'კოსტავა'),
       (6, N'მარჯანიშვილი'),
       (7, N'გორგასლის'),
       (8, N'აბაშიძის'),
       (9, N'წერეთლის'),
       (10, N'ვაჟა-ფშაველა');

SELECT id, area FROM location_cases