-- Set search path to public schema
SET search_path TO public;

-- Creating Tables in dev_schema
CREATE TABLE IF NOT EXISTS customer (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(100),
                                        email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS orders (
                                      id SERIAL PRIMARY KEY,
                                      customer_id INTEGER REFERENCES customer(id),
                                      order_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_detail (
                                            id SERIAL PRIMARY KEY,
                                            order_id INTEGER REFERENCES orders(id),
                                            product_name VARCHAR(100),
                                            quantity INTEGER,
                                            price DECIMAL(10, 2)
);

CREATE INDEX IF NOT EXISTS idx_customer_email ON customer (email);
CREATE INDEX IF NOT EXISTS idx_order_date ON orders (order_date);

-- Inserting Mock Data into dev_schema
INSERT INTO customer (name, email) VALUES
                                       ('Alice Johnson', 'alice.johnson@example.com'),
                                       ('Bob Smith', 'bob.smith@example.com'),
                                       ('Charlie Brown', 'charlie.brown@example.com');

INSERT INTO orders (customer_id, order_date) VALUES
                                                 ((SELECT id FROM customer WHERE email = 'alice.johnson@example.com'), '2023-06-15 14:00:00'),
                                                 ((SELECT id FROM customer WHERE email = 'bob.smith@example.com'), '2023-06-16 15:00:00');

INSERT INTO order_detail (order_id, product_name, quantity, price) VALUES
                                                                       ((SELECT id FROM orders WHERE customer_id = (SELECT id FROM customer WHERE email = 'alice.johnson@example.com')), 'Laptop', 1, 1200.00),
                                                                       ((SELECT id FROM orders WHERE customer_id = (SELECT id FROM customer WHERE email = 'alice.johnson@example.com')), 'Mouse', 2, 25.00),
                                                                       ((SELECT id FROM orders WHERE customer_id = (SELECT id FROM customer WHERE email = 'bob.smith@example.com')), 'Desk Chair', 1, 250.00);
