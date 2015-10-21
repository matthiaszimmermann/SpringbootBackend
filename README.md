<h2>JPA/Hibernate backend with a REST HATEOAS API</h2>

<h3>1. setup</h3>

create a target directory according to the entry in <a href='https://github.com/matthiaszimmermann/SpringbootBackend/blob/master/src/main/resources/application.properties'>application.properties</a>
eg. C:\DerbyDB\SpringDataJpaRest

<h3>2. running</h3>

run the server in the spring tool suite

<h3>3. testing</h3>

curl -X POST mzi:password@localhost:8080/persons -H 'Content-Type:application/json' -d '{ "firstName": "Anna", "city": "Zürich" }<br>
curl -X GET mzi:password@localhost:8080/persons

curl -X POST mzi:password@localhost:8080/companies -H 'Content-Type:application/json' -d '{ "name": "BSI", "city": "Zürich" }'<br>
curl -X GET mzi:password@localhost:8080/companies

curl -X POST mzi:password@localhost:8080/events -H 'Content-Type:application/json' -d '{ "title": "Scout User Group Meeting 2015", "city": "Ludwigsburg", "dateStart": "2015-11-01" }'<br>
curl -X GET mzi:password@localhost:8080/events<br>
curl -X PUT mzi:password@localhost:8080/events/1/persons -H 'Content-Type:text/uri-list' -d 'http://localhost:8080/persons/1'

curl -X GET mzi:password@localhost:8080/search/person?criteria=firstName:a


