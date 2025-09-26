CREATE TABLE IF NOT EXISTS customers (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    last_modified_at timestamptz NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS categories (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products (
	id UUID PRIMARY KEY,
    category_id UUID NOT NULL,
	name VARCHAR(255) NOT NULL,
	price NUMERIC(12,2) NOT NULL CHECK (price > 0),
    description TEXT,
    stock_quantity INT NOT NULL CHECK (stock_quantity >= 0),
    created_at timestamptz NOT NULL,
    last_modified_at timestamptz NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS orders (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    status VARCHAR(20) NOT NULL,
    total NUMERIC(10,2) NOT NULL CHECK (total > 0),
    created_at timestamptz NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT check_status CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED'))
);

CREATE TABLE IF NOT EXISTS order_items (
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    order_id UUID NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(10,2) NOT NULL CHECK (unit_price > 0),
    created_at timestamptz NOT NULL,
    last_modified_at timestamptz NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);
