
# AI API Extractor

This project implements an AI agent that analyzes GitHub repositories to automatically discover API endpoints and infer request/response schemas using a Large Language Model.

The system clones a repository, scans backend files, extracts API endpoints, and generates structured API documentation.

⸻

Features

• Clones any public GitHub repository
• Scans JavaScript / TypeScript backend files
• Detects Express API endpoints
• Uses an LLM to infer request and response schemas
• Generates API documentation automatically
• Exports results as structured JSON and OpenAPI documentation

⸻

Architecture

The system works in multiple stages:
	1.	Clone the GitHub repository locally
	2.	Scan JavaScript / TypeScript files in the repository
	3.	Extract Express API endpoints such as:
	  app.get("/api/users")
      router.post("/api/orders")
    4.	Send API handler code snippets to a Large Language Model
    5.	Infer request and response schemas
	6.	Generate JSON and OpenAPI documentation
 


	

⸻

System Flow

GitHub Repository URL
↓
Clone Repository
↓
Scan JS/TS Files
↓
Extract API Endpoints
↓
Send Code to LLM
↓
Infer Request / Response Schema
↓
Generate JSON + OpenAPI Documentation

⸻

Project Structure

src/main/java/com/AmanPundir
│
├── Main.java
├── RepoCloner.java
├── FileScanner.java
├── APIExtractor.java
├── APIEndpoint.java
├── APIResult.java
├── LLMService.java
└── OutputWriter.java

Each component performs a specific step in the API extraction pipeline.

⸻

How to Run

Requirements

• Java 17+
• Maven

⸻

Clone the Repository

git clone https://github.com/Amanpundir279/ai-api-extractor.git
cd ai-api-extractor


⸻

Build the Project

mvn clean install


⸻

Set OpenAI API Key

Before running the application, set your OpenAI API key as an environment variable.

Mac / Linux

export OPENAI_API_KEY=your_api_key

Windows

set OPENAI_API_KEY=your_api_key


⸻

Run the Application

java -jar target/api-extractor.jar

Enter a GitHub repository URL when prompted.

Example:

https://github.com/juice-shop/juice-shop


⸻

Output

The program generates the following files:

output/apis.json
output/openapi.json

These files contain extracted API endpoints and generated schemas.

⸻

Example

Input repository:

https://github.com/juice-shop/juice-shop

Output:

• Extracted 100+ APIs
• Generated request/response schemas
• Produced structured API documentation


⸻

Design Decisions

• Express route detection was used because the target repository (OWASP Juice Shop) is built with Express.js.

• A Large Language Model is used for schema inference because static parsing cannot always determine request and response structures from code alone.

• OpenAPI output was generated to make the extracted APIs easily usable for API documentation and testing tools.

⸻

Limitations

• Schema inference depends on LLM interpretation
• Currently supports Express-style APIs
• Dynamic schema generation inside complex business logic may not always be detected

⸻

Possible Improvements

• Support additional backend frameworks
• Use AST parsing for more accurate API extraction
• Parallelize file scanning for large repositories
• Cache LLM responses to reduce API cost

⸻

Author

Aman Pundir
