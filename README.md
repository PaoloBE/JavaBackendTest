# JavaBackendTest


API REST JAVA 17, Spring-boot.

Conexión a MongoDB en la nube con el siguiente uri:  
> mongodb://budiel420:kXFt21ZHtRsmwnm5@ac-tk7ilza-shard-00-00.38efpk7.mongodb.net:27017,ac-tk7ilza-shard-00-01.38efpk7.mongodb.net:27017,ac-tk7ilza-shard-00-02.38efpk7.mongodb.net:27017/?ssl=true&replicaSet=atlas-8zlqvd-shard-0&authSource=admin&retryWrites=true&w=majority&appName=Cluster1
> 
Para kafka usé una distribución local, solo es cambiar los valores en las properties:  
>spring.kafka.bootstrap-servers   
message.topic.name
>  
En caso de querer desactivar kafka cambiar la siguiente property al valor 0 así:
>kafka.activated=0
> 

---
## Contenido
- Controller   
    Tres endpoints para listar todo, listar por usuario, agregar orden
- Service  
    Service con métodos para redirigir al repository y manejo de errores
- Repository  
    MongoRepository con métodos de búsqueda, para búsqueda total y agregado de orden se usa los métodos por defecto del MongoRepository (findAll y insert)
- Entity  
    Clases de los objetos
- Utils  
    Clases para generación de la respuesta y constantes usadas
- Config  
    @Beans y @Configuration para Kafka   
---
Además cuenta con un archivo collection de postman para probar los endpoints

