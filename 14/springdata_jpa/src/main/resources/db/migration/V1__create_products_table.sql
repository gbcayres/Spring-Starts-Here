CREATE TABLE IF NOT EXISTS products (
	id UUID PRIMARY KEY,
	name VARCHAR(150) NOT NULL,
	price NUMERIC(10,2) NOT NULL CHECK (price > 0),
    stock_quantity INT NOT NULL CHECK (stock_quantity >= 0)
);