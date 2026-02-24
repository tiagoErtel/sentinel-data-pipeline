CREATE TABLE dim_products (
    product_name VARCHAR(100) PRIMARY KEY,
    category VARCHAR(50) DEFAULT 'General'
);

CREATE TABLE dim_stores (
    store_location VARCHAR(100) PRIMARY KEY,
    region VARCHAR(50)
);

CREATE TABLE fact_sales (
    invoice_id VARCHAR(50) PRIMARY KEY,
    timestamp TIMESTAMP,
    product_name VARCHAR(100) REFERENCES dim_products(product_name),
    store_location VARCHAR(100) REFERENCES dim_stores(store_location),
    quantity INTEGER,
    unit_price_original DECIMAL(10,2),
    currency VARCHAR(3),
    total_amount_original DECIMAL(10,2),
    total_amount_eur DECIMAL(10,2),
    processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO dim_products (product_name, category) VALUES 
('Hammer', 'Tools'), ('Steel Pan', 'Kitchen'), ('Chef Knife', 'Kitchen'), 
('Garden Shovel', 'Garden'), ('Pressure Cooker', 'Kitchen');

INSERT INTO dim_stores (store_location, region) VALUES 
('Dublin_Central', 'Leinster'), ('Cork_Hub', 'Munster'), 
('Galway_Store', 'Connacht'), ('Limerick_Outlet', 'Munster');
