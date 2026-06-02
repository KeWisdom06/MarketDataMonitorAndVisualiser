import re
import pandas as pd
import matplotlib.pyplot as plt

# Paste your Java output between the triple quotes below
java_output = """
Added data point: price=514.059998, timestamp=2026-06-02T22:15:34.163725528
Added data point: price=514.059998, timestamp=2026-06-02T22:15:49.351220437
Added data point: price=514.059998, timestamp=2026-06-02T22:16:04.580829727
Added data point: price=514.059998, timestamp=2026-06-02T22:16:19.758465166
Added data point: price=514.059998, timestamp=2026-06-02T22:16:34.972787298
"""

# Extract price and timestamp
matches = re.findall(
    r"price=([0-9.]+), timestamp=([^\n]+)",
    java_output
)

# Convert to DataFrame
df = pd.DataFrame(matches, columns=["price", "timestamp"])

# Convert data types
df["price"] = df["price"].astype(float)
df["timestamp"] = pd.to_datetime(df["timestamp"])

print(df)

# Plot the data
plt.figure(figsize=(10, 5))
plt.plot(df["timestamp"], df["price"], marker="o")

plt.title("DIA Price Over Time")
plt.xlabel("Timestamp")
plt.ylabel("Price")

plt.xticks(rotation=45)
plt.tight_layout()
plt.show()
