# web-library
This is a skeleton of a library web application implented using Java Spring Boot and PostgreSQL.
The user registration part of the application utilizes an external API (https://microblink.com/products/blinkid/cloud-api) to read user's data from id
cards. Data is returned as a raw string for which I built a simple parser. The structured data is then used to autofill the registration forms.