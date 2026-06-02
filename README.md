# MarketDataMonitorAndVisualiser
Real-time market data monitor built in Java. Uses the Twelve Data API to retrieve DIA ETF prices every 15 seconds and store market data in a queue.

## Requirements

- Java 11+

- Twelve Data API Key (which can be obtained by creating an account on [the twelve website](https://twelvedata.com/) and generatng an API key

## Set Environment Variable

Set the environment variable to your API key obtained from the twelve website:

```bash

TWELVE_DATA_API_KEY=<your_api_key>

```

## Compile and Run

Compile and Run the App.java file

```bash

javac App.java

java App

```

If this readme is unclear (apologies if so), full project demonstration is available in the MarketDataMonitor.ipynb file in the Main Branch, which can be downloaded, and run it using [google colab](https://colab.research.google.com/)
