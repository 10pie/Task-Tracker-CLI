# Task Tracker CLI

A command-line utility to efficiently add, update, and manage tasks with local JSON storage. This project demonstrates core Java concepts, file I/O operations, and JSON manipulation using the Jackson library.

## Features

- **Dynamic Task Creation:** Add tasks with automatic ID generation and timestamps (`Created At` / `Updated At`).
- **Status Management:** Update task descriptions and seamlessly shift statuses between `todo`, `in-progress`, and `done`. Each modification automatically refreshes the `Updated At` timestamp.
- **Flexible Listing:** List all tasks at once or filter them by their specific status.
- **Persistent Storage:** All data is safely written to and read from a local `tasks.json` file.

## Prerequisites

- **Java 21** or higher
- **Maven** (for dependency management and building)

## Installation & Setup

1. Clone the repository and navigate into the project directory:

   ```bash
   git clone https://github.com/10pie/Task-Tracker-CLI.git
   cd Task_Tracker
   ```

2. Compile the project and build the executable JAR file using Maven:

   ```bash
   mvn clean package
   ```

   This command uses the Maven Shade Plugin to bundle the application and the Jackson JSON library into a single standalone executable.

## Usage

Once built, you can run the application directly from your terminal using the generated JAR file located in the `target/` directory.

**Add a new task:**

```bash
java -jar target/Task_tracker-1.0-SNAPSHOT.jar add "Buy groceries"
```

**Update a task description (by ID):**

```bash
java -jar target/Task_tracker-1.0-SNAPSHOT.jar update 3 "Buy groceries and milk"
```

**Update a task status (by ID):**

```bash
java -jar target/Task_tracker-1.0-SNAPSHOT.jar mark-in-progress 3
java -jar target/Task_tracker-1.0-SNAPSHOT.jar mark-done 3
```

**List all tasks:**

```bash
java -jar target/Task_tracker-1.0-SNAPSHOT.jar list
```

## Built With

- **Java** - Core application logic and CLI routing.
- **Maven** - Dependency management and build lifecycle.
- **Jackson** (fasterxml.jackson) - Reading, writing, and parsing the `tasks.json` data structure.