import pandas as pd
from sqlalchemy import create_engine
import os

def run_etl():
    print("🚀 Starting ETL Process...")

    if not os.path.exists('invoices.csv'):
        print("❌ Error: invoices.csv not found. Run Java producer first!")
        return

    df = pd.read_csv('invoices.csv')

    rates = {'EUR': 1.0, 'BRL': 0.16, 'USD': 0.92}
    df['total_amount_eur'] = df.apply(lambda x: x['total_amount'] * rates.get(x['currency'], 1.0), axis=1)
    df['timestamp'] = pd.to_datetime(df['timestamp'])

    df = df.rename(columns={
        'unit_price': 'unit_price_original',
        'total_amount': 'total_amount_original'
    })

    try:
        engine = create_engine('postgresql://admin:password123@localhost:5432/sentinel_data')

        print("🔌 Connecting to SentinelDB...")

        df.to_sql('fact_sales', engine, if_exists='append', index=False)

        print(f"✅ Successfully loaded {len(df)} records into the database!")

    except Exception as e:
        print(f"❌ Database Error: {e}")

if __name__ == "__main__":
    run_etl()
