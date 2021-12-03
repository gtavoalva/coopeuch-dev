# coopeuch-dev

Este es un programa demo para Coopeuch.
### Prerequisitos:
  - Acceso a una BD en Postgres versión 13.
  - Java versión 11 (este fue probado con la versión de AWS Corretto, pero podría ser de Sun u Openjdk).
  - Maven versión 3.8.x

Para levantar este proyecto de prueba, es necesario:
- Ejecutar el script adjunto en la carpeta "sql", directo en la base de datos. Con esto queda creada la tabla.
- Descargar el proyecto (git clone)
- Modificar el archivo "application.properties" para configurar la base de datos correcta asi como las credenciales de acceso.

![imagen](https://user-images.githubusercontent.com/4118340/144622650-30d84d17-932a-4e4a-9c2d-6fbe8747c18b.png)


- Si se utiliza Visual Studio Code:

* Hacer un "update" de maven

![imagen](https://user-images.githubusercontent.com/4118340/144618631-056db282-5c60-4328-aafd-8ba7fbc16634.png)

* luego un "clean install"

![imagen](https://user-images.githubusercontent.com/4118340/144618915-0c65b9a4-ffa4-4880-91e3-29eaa8e6fa39.png)


* finalmente levantar el servicio

![imagen](https://user-images.githubusercontent.com/4118340/144619003-52b8bee5-0dad-42d3-b4c7-939ba1630210.png)



Una vez levantado el servicio, es posible verificar la definición de la API en la url:

`http://localhost:5000/api/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config`

![imagen](https://user-images.githubusercontent.com/4118340/144620318-c030b4f6-5e0e-402b-a7ae-62e76d90ff3e.png)


En la carpeta `postman`, hay un ejemplo de prueba de cada servicio. El archivo se debe importar en Postman idealmente o software compatible con ese tipo de archivo y definición.

![imagen](https://user-images.githubusercontent.com/4118340/144620555-e0d6c994-70ce-4c98-94af-fa56be47e982.png)

![imagen](https://user-images.githubusercontent.com/4118340/144620593-e2f93a53-760b-4732-a07a-d33df8d429fa.png)

![imagen](https://user-images.githubusercontent.com/4118340/144620770-fbab5b55-aa5e-43c5-ac36-7343555f7521.png)

La variable {{BE_SERVER_URL}} es definida a nivel de ambiente en Postman. En este caso equivale al ambiente local, "localhost" o "127.0.0.1".
