TRUNCATE addresses CASCADE;

INSERT INTO addresses (id, index, country, region, city, street, house, apartment)
VALUES (1, 'Index 1', 'Country 1', 'Region 1', 'City 1', 'Street 1', 'House 1', 'Apartment 1'),
       (2, 'Index 2', 'Country 2', 'Region 2', 'City 2', 'Street 2', 'House 2', 'Apartment 2'),
       (3, 'Index 3', 'Country 3', 'Region 3', 'City 3', 'Street 3', 'House 3', 'Apartment 3'),
       (4, 'Index 4', 'Country 4', 'Region 4', 'City 4', 'Street 4', 'House 4', 'Apartment 4'),
       (5, 'Index 5', 'Country 5', 'Region 5', 'City 5', 'Street 5', 'House 5', 'Apartment 5');

SELECT setval('addresses_id_seq', max(id))
FROM addresses;