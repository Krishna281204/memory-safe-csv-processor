# Memory-Safe CSV Processor

A high-performance memory-efficient CSV processing engine built in Java using Streams API and custom Collectors.

The system processes large CSV datasets without loading the complete file into memory, making it scalable and efficient for handling large data files. It also safely handles malformed rows and generates useful statistical summaries.

---

## Features

- Processes 500K+ CSV records efficiently
- Memory-safe file streaming using `Files.lines()`
- Handles malformed CSV rows safely
- Custom Java Collector implementation
- Statistical aggregation:
  - Processed rows count
  - Average salary
  - Minimum age
  - Maximum age
- Fault-tolerant parsing using `Optional`
- SQL schema included for relational database practice

---

## Tech Stack

- Java
- Java Streams API
- Custom Collectors
- SQL
- File Streaming
- OOP Concepts

---

## Project Structure

```text
memory-safe-csv-processor/
│
├── src/
│   ├── EmployeeRecord.java
│   ├── Summary.java
│   ├── MemorySafeCSVProcessor.java
│   └── CSVGenerator.java
│
├── sample-output/
│   ├── terminal-output.png
│   └── architecture.png
│
├── queries.sql
├── README.md
└── .gitignore
