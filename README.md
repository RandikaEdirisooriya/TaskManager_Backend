🏃 How to Run the Spring Boot Application (JDK 21 + Maven)

1. Install Required Tools
   - ✅ JDK 21 (already installed)
   - ✅ Maven (already installed)

2. Clone the Repository
   ```bash
   git clone https://github.com/RandikaEdirisooriya/TaskManager_Backend.git
   cd TaskManager_Backend
   ```

3. Build the Application Using Maven
   ```bash
   ./mvnw clean package
   ```
   > This will create a `.jar` file inside the `target/` folder.

4. Run the Application
   ```bash
   java -jar target/TaskManager_Backend.jar
   ```
   > Replace `TaskManager_Backend.jar` with the actual JAR file name.

5. Access the App in Your Browser
   ```
   http://localhost:8080
   ```

6. Optional: Check Logs While Running
   You can monitor app logs in the terminal where you ran the `java -jar` command.

