DROP TABLE IF EXISTS coffee;

CREATE TABLE coffee
(
  id SERIAL NOT NULL PRIMARY KEY,
  origin VARCHAR(20),
  brand VARCHAR(20),
  characteristic VARCHAR(30)
)