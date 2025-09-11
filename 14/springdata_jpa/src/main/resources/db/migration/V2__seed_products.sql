CREATE EXTENSION IF NOT EXISTS "pg_uuidv7"

INSERT INTO products (id, name, price, stock_quantity) VALUES
    (gen_random_uuid(), 'Notebook Gamer XYZ', 4999.99, 10),
    (gen_random_uuid(), 'Smartphone ABC', 2199.50, 25),
    (gen_random_uuid(), 'Teclado Mecânico', 349.90, 50),
    (gen_random_uuid(), 'Mouse Wireless', 129.90, 100),
    (gen_random_uuid(), 'Monitor 27" 4K', 1899.00, 15),
    (gen_random_uuid(), 'Headset Gamer', 299.90, 40),
    (gen_random_uuid(), 'Cadeira Gamer', 899.00, 12),
    (gen_random_uuid(), 'Webcam Full HD', 259.90, 20),
    (gen_random_uuid(), 'SSD 1TB', 799.00, 30),
    (gen_random_uuid(), 'HD Externo 2TB', 499.00, 18);
