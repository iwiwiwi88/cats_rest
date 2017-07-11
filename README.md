# cats_rest
Silly way of learning RESTful API with Jersey

API

==== GET ====

List all cats with favourite food:
http://localhost:8080/iwi/cats/ 

Get info about one cat:
http://localhost:8080/iwi/cats/[name]


==== POST ====

Add a new cat or update if it already exists:
http://localhost:8080/iwi/cats/post/[name]/[favouriteFood]


==== DELETE ====

Delete a cat:
http://localhost:8080/iwi/cats/delete/[name]