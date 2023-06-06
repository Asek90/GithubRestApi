# GithubRestApi
Projekt GitHubRestApi jest aplikacją w Javie, która korzysta z interfejsu API GitHuba w celu pobrania informacji o repozytoriach na podstawie nazwy użytkownika. Projekt wykorzystuje framework Spring Boot do implementacji REST API, które zwraca nazwy repozytoriów, właścicieli oraz identyfikatory commitów. Po uruchomieniu, aplikacja będzie dostępna na adresie localhost:8080.

# Użycie API
Pobieranie informacji o repozytoriach użytkownika

Aby pobrać informacje o repozytoriach na podstawie nazwy użytkownika, wykonaj zapytanie GET na endpoint localhost:8080/repositories/{username}, gdzie {username} to nazwa użytkownika na GitHubie.

Przykład zapytania HTTP: GET /repositories/{username}
