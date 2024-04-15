# 🚀 Descripción del Proyecto

El proyecto **ecommerce** es una aplicación desarrollada en Spring Boot que proporciona una API para gestionar clientes, productos y facturas en un sistema de comercio electrónico. A continuación, se detallan los principales componentes y funcionalidades del proyecto.

## 🔧 Tecnología Utilizada

El proyecto utiliza las siguientes tecnologías y dependencias:

- ☕ **Java 21**: Lenguaje de programación robusto y altamente compatible con una amplia gama de plataformas.
- 🚀 **Spring Boot 3.2.4**: Framework de desarrollo de aplicaciones Java que simplifica el proceso de configuración y desarrollo.
- 🗃️ **Spring Data JPA**: Biblioteca que facilita el acceso y la manipulación de datos en la base de datos utilizando el paradigma de mapeo objeto-relacional (ORM).
- 🐬 **MySQL Connector**: Conector JDBC que permite la comunicación con bases de datos MySQL.
- 🌐 **Spring Web**: Módulo de Spring que facilita la creación de servicios web RESTful.
- 🛠️ **Spring Boot DevTools**: Herramientas de desarrollo que agilizan el ciclo de desarrollo y mejoran la productividad del desarrollador.
- 📦 **Spring Boot Maven Plugin**: Plugin de Maven que facilita la creación de archivos JAR ejecutables.

## 📥 Descargar y Configurar el Proyecto

Para descargar y configurar el proyecto desde GitHub, sigue estos pasos:

1. **Clonar el Repositorio desde GitHub:**
   ```
   git clone https://github.com/JulianRicomagno/ecommerceCoder.git
   ```
2. **Abrir el Proyecto en tu IDE Preferido:**
   Abre tu entorno de desarrollo integrado (IDE) y selecciona la opción para abrir un proyecto. Navega hasta el directorio donde clonaste el repositorio y ábrelo en tu IDE.
3. **Configurar el archivo application.properties:**
   Accede al archivo `application.properties` ubicado en el proyecto y proporciona los detalles de conexión para tu base de datos MySQL. Asegúrate de configurar correctamente los parámetros como la URL de la base de datos, el nombre de usuario y la contraseña.

## 🛠️ Crear una Base de Datos Ecommerce en MySQL

Para crear una base de datos "ecommerce" desde la línea de comandos de MySQL, sigue estos pasos:

1. Abre una terminal y accede a MySQL con el comando: `mysql -u <usuario> -p`
2. Ingresa tu contraseña cuando se te solicite.
3. Ejecuta el siguiente comando para crear la base de datos: `CREATE DATABASE ecommerce;`

## 📦 Crear el Archivo JAR

Para crear el archivo JAR ejecutable del proyecto, sigue estos pasos:

1. Abre una terminal en el directorio raíz del proyecto.
2. Ejecuta el siguiente comando: `mvn clean install`
3. El archivo JAR se generará en el directorio `target/`. Puedes ejecutarlo con el comando: `java -jar nombre_del_archivo.jar`

## 📋 Documentación con Swagger

La documentación de la API está disponible a través de Swagger, una herramienta de código abierto que simplifica la generación de documentación para servicios web RESTful. Puedes acceder a la documentación de los endpoints utilizando la siguiente URL:

[Documentación de la API con Swagger](http://localhost:8080/swagger-ui/index.html)

## 🌟 Controladores - API

### API Clientes
- **Obtener lista de Clientes:** `GET /api/clients/` - Obtiene todos los clientes registrados.
- **Guardar Cliente:** `POST /api/clients/` - Guarda un nuevo cliente en la base de datos.
    - *Ejemplo del JSON del cliente*:
      ```json
      {
        "name": "Gonzalo",
        "lastname": "Rivas",
        "docnumber": "16789"
      }
      ```
- **Obtener Cliente por ID:** `GET /api/clients/{id}` - Obtiene un cliente específico por su ID.
- **Actualizar Cliente por ID:** `PUT /api/clients/{id}` - Actualiza los datos de un cliente existente.
- **Eliminar Cliente por ID:** `DELETE /api/clients/{id}` - Elimina un cliente existente de la base de datos.

### API Facturas
- **Obtener lista de Facturas:** `GET /api/invoices/` - Obtiene todas las facturas registradas.
- **Obtener Factura por ID:** `GET /api/invoices/{id}` - Obtiene una factura específica por su ID.
- **Guardar Factura:** `POST /api/invoices/` - Guarda una nueva factura en la base de datos.
    - *Ejemplo del JSON de la factura*:
      ```json
      {
        "id": "1",
        "client": "2",
        "total": "500",
        "detail": [
          {
            "amount": "2",
            "productId": "1",
            "price": "500"
          }
        ]
      }
      ```

### API Productos
- **Guardar Producto:** `POST /api/products/` - Guarda un nuevo producto en la base de datos.
    - *Ejemplo del JSON del producto*:
      ```json
      {
        "description": "Camisa de algodón",
        "code": "CA01",
        "price": "29.99",
        "stock": "100"
      }
      ```
- **Obtener lista de Productos:** `GET /api/products/` - Obtiene todos los productos registrados.
- **Obtener Producto por Código:** `GET /api/products/code/{code}` - Obtiene un producto específico por su código.
- **Obtener Producto por ID:** `GET /api/products/{id}` - Obtiene un producto específico por su ID.
- **Actualizar Producto por ID:** `PUT /api/products/{id}` - Actualiza los datos de un producto existente.

