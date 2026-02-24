#!/bin/bash
echo "🏗️ Starting SentinelData Pipeline..."

# 1. Start Database
docker-compose up -d
sleep 5 # Wait for DB to wake up

# 2. Generate Data
echo "☕ Running Java Data Producer..."
javac InvoiceGenerator.java && java InvoiceGenerator

# 3. Process & Load
echo "🐍 Running Python ETL..."
python etl_process.py

echo "✅ Pipeline Complete!"
