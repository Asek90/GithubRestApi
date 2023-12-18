# GithubRestApi
Project GitHubRestApi is a Java application that utilizes the GitHub API to retrieve information about repositories based on a username. The project employs the Spring Boot framework to implement a REST API, which returns repository names, owners, and commit identifiers. Once launched, the application will be accessible at the address localhost:8080.

# API Usage
Retrieving information about user repositories
To retrieve information about repositories based on a username, make a GET request to the endpoint localhost:8080/repositories/{username}, where {username} is the GitHub username.
Example HTTP request: GET /repositories/{username}
