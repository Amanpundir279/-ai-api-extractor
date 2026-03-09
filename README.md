AI API Extractor

This project implements an AI agent that analyzes GitHub repositories to automatically discover API endpoints and infer request/response schemas using a Large Language Model.

Features

• Clones any public GitHub repository
• Scans JavaScript / TypeScript backend files
• Detects Express API endpoints
• Uses an LLM to infer request and response schemas
• Generates API documentation automatically
• Exports results as structured JSON and OpenAPI documentation

How to Run

Requirements
	•	Java 17+
	•	Maven

Steps

Clone the repository

git clone https://github.com/Amanpundir279/-ai-api-extractor.git

cd -ai-api-extractor

Build the project

mvn clean install

Run the application

java -jar target/api-extractor.jar

Enter a GitHub repository URL when prompted.

Example:

https://github.com/juice-shop/juice-shop

Output

The program generates:

output/apis.json
output/openapi.json

These files contain extracted API endpoints and generated schemas.

Example

Input repository:

https://github.com/juice-shop/juice-shop

Output:
	•	Extracted 100+ APIs
	•	Generated request/response schemas
	•	Produced structured API documentation

Then click Commit changes.
