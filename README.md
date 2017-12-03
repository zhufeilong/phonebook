Starting the web service by hackajob.phonebook.main

Once the web service is started, it can be reached at

http://localhost:8080/contact
REST Endpoints

The following REST endpoints are available upon deployment of the contact management system:

| HTTP Verb        | URL           | Description  | Status Codes |
| ------------- |-------------|:-----| ----|
| `GET` | `http://localhost:8080/contact` | Obtains a list of all existing contacts | <ul><li>`200 OK`</li></ul> |
| `GET` | `http://localhost:8080/contact/{id}` | Obtains the contact corresponding to the supplied contact ID | <ul><li>`200 OK` if contact exists</li><li>`404 Not Found` if contact does not exist</li></ul> |
| `POST` | `http://localhost:8080/contact` | Creates a new contact based on the payload contained in the request body | <ul><li>`201 Created` if contact successfully created</li></ul> |
| `PUT` | `http://localhost:8080/contact/{id}` | Updated an existing contact with the data contained in the request body | <ul><li>`200 OK` if contact succesfully updated</li><li>`404 Not Found` if contact does not exist</li></ul> |
| `DELETE` | `http://localhost:8080/contact/{id}` | Deletes an existing contact that corresponds to the supplied contact ID | <ul><li>`203 No Content` if contact succesfully deleted</li><li>`404 Not Found` if contact does not exist</li></ul> |
