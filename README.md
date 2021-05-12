<h1 align="center">
    Social-Network-Project
  <p><img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/> <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/> <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white"/></p>
</h1>
<h3 align="center">Experimental social network to share knowledge and content acquired in the training.  </h3>



✍️ API Requirements
-----
### Must to have
- [x] Comunicar com uma API externa (desenvolvida pelo colaborador ou por terceiros).
- [x] Persistir dados num BD relacional (não relacional é "nice to have").
- [x] Apresentar alguns testes unitários e funcionais.

### Nice to have
- [ ] Organização do código.
- [ ] Logging.
- [x] Segurança (ex: JWT).
- [x] Cache.

📝 Design Patterns
-----
  ### Model
  Detentor dos dados, recebe as informações do Controller, valida
  ou não e retorna a resposta adequada.

  ### Controller
  Fornece a comunicação entre o detentor dos dados e o cliente.

  ### Repository
  Interface de consulta e manipulação dos dados, utilizado para criar uma barreira de controle e segurança entre a aplicação e os dados.

  ### DTO
  Utilizado para transferir dados entre subsistemas do software.

  ### Form 
  Utilizado para transferir dados entre subsistemas do software.


📚 Features
-----

  <table border="0" width="100%"
  >
  <tr>

  <td width="30%" valign="top" border="0">

  ## User
  - [x] CREATE
  - [x] DELETE

  </td>
  <td width="30%" valign="top">

  ## Publication
  - [x] CREATE
  - [x] READ
  - [x] UPDATE
  - [x] DELETE

  </td>
  <td width="30%" valign="top">

  ## Comment
  - [x] CREATE
  - [x] UPDATE
  - [x] DELETE

  </td>

</tr>
</table>




💻 Setup
-----
- Clone and open in Eclipse IDE
- Install maven dependencies using IDE auto import or using the command ``mvn install``
- Browse ``http://localhost:8080``
    
📃 API Doc & Sample
----------------

###  Authentication Controller
  
  #### Content-Type
  
    application/json
    
  #### Body
  
    {
      "email": "luiz@email.com",
      "password": "123456"
    }
    
  #### Paths
  
    POST /auth
-----    
### Publication Controller

 #### Content-Type
   ```
   application/json
   Authorization Bearer {{token}}
   ```
 #### Paths
  ```
  POST /publications
  ```

   ```
  GET /publications
  ```

  ```
  GET /publications?author=authorName
  ```

  ```
  GET /publications/{id}
  ```

  ```
  PUT /publications/{id}
  ```

  ```
  DELETE /publications/{id}
  ```
  
-----  
### User Controller
 
  #### Content-Type
   ```
   application/json
   
   Authorization Bearer {{token}}
   ```
  #### Paths
    POST /user
   ```

   DELETE /user/{id}
   ```
   
📲 External Tools
----------------

  #### ElephantSQL
  ```
  https://www.elephantsql.com
  ```
  #### Cloudinary
  ```
  https://cloudinary.com
  ```
  #### Swagger
  ```
  https://swagger.io
  ```
  #### Spring Boot Admin
  ```
  https://codecentric.github.io/spring-boot-admin/2.1.4/
  ```
