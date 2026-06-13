🥗 Nutrilio - Inteligencia Artificial al Servicio de tu Nutrición

📝 Descripción del proyecto

Nutrilio es una plataforma backend diseñada para la planificación nutricional
personalizada mediante el uso de Inteligencia Artificial. La aplicación permite a los usuarios gestionar
su perfil biométrico y generar planes de comidas automatizados basados en sus metas específicas, facilitando
el seguimiento de objetivos de salud.



🛠️ Tecnologías Utilizadas:

Core: Java 25, Spring Boot 4.0.6.

Persistencia: Spring Data JPA, Hibernate, MySQL.

Seguridad: Spring Security (BCrypt, Basic Authentication).

IA: Spring AI & OpenAI API.

Herramientas: Maven, Lombok, Jakarta Validation.



Estructura de Controladores y Rutas:

-UserController	/api/users	Registro y autenticación de usuarios.

-UserProfileController	/api/profile	CRUD de métricas biométricas y objetivos.

-NutritionalAIController	/api/diet/generate	Generación rápida de sugerencias vía IA.

-DietController	/api/diets	Gestión (CRUD) de planes de dieta persistentes.

-FoodController	/api/food	Gestión del catálogo de alimentos.



Enlaces adicionales:

Trello: https://nutrilio.atlassian.net/jira/core/projects/NUT/list/?filter=allissues&jql=project%20%3D%20"NUT"%20ORDER%20BY%20created%20DESC

Diapositivas: https://docs.google.com/presentation/d/11vlyePWWuum6k6X3Ji-a8-E6CN7UCkzoNwuWfhwo90A/edit?slide=id.gc6f80d1ff_0_0#slide=id.gc6f80d1ff_0_0

Repositorio Github: https://github.com/davidztrading/Nutrilio-backend



Configuración de la Aplicación:

La APP utiliza un esquema de Configuración Externa:

-Utiliza variables de entorno (como OPENAI_API_KEY) para proteger credenciales.

-Emplea Spring Data JPA con Hibernate para mapear automáticamente los objetos Java a tablas MySQL, simplificando las consultas SQL a simples llamadas de método.

-Está configurada con Spring Security, que actúa como un "portero": intercepta cada petición HTTP, verifica si el usuario tiene credenciales válidas y decide si permite el acceso a la lógica de negocio.


Funcionamiento Interno:

-El funcionamiento interno sigue un patrón de Capas de Abstracción:

-Capa de Controladores (@RestController): Es la puerta de entrada. Reciben las peticiones HTTP (GET/POST), validan que los datos básicos sean correctos y delegan el trabajo pesado a los servicios.

-Capa de Servicios (@Service): Aquí reside el "cerebro".

-Lógica de Negocio: Procesa los datos del usuario.

-Integración con IA: El NutritionalAIService actúa como puente. Convierte el objetivo del usuario en un prompt, lo envía a OpenAI vía RestTemplate, y procesa la respuesta JSON para convertirla en un objeto DietDTO legible para tu base de datos.

-Capa de Acceso a Datos (Repository): Utiliza JpaRepository para la comunicación directa con MySQL. Es la capa que garantiza que la información de usuarios, alimentos y dietas se guarde de forma permanente.

-Flujo de Excepciones: Si ocurre un error (usuario no encontrado, datos inválidos), un GlobalExceptionHandler intercepta el fallo y devuelve una respuesta estructurada y limpia al usuario, evitando que el sistema se bloquee o muestre información sensible del código.

-Cuando un usuario interactúa con la aplicación, el flujo sigue un modelo de Arquitectura en Capas (Layered Architecture), lo que garantiza que la lógica esté desacoplada y sea fácil de mantener:

-Capa de Entrada (Controller): El NutritionalAIController recibe la petición HTTP del usuario. Su única responsabilidad es recibir el objetivo y delegar el trabajo al servicio.

-Capa de Negocio (Service): El NutritionalAIService coordina la operación:

-Prepara una petición HTTP segura, adjuntando la API Key en el encabezado (Bearer Token).

-Envía el prompt estructurado a la API de OpenAI.

-Recibe una respuesta JSON compleja, la cual "desenvuelve" para extraer solo la información útil.

-Capa de Transformación (DTOs): El servicio convierte la respuesta de la IA en un objeto Java limpio (DietDTO), facilitando el transporte de datos sin exponer las estructuras internas de la base de datos.

-Capa de Persistencia (Repository & DB): Finalmente, la aplicación toma este DTO y lo mapea a una entidad Diet utilizando JPA/Hibernate, que se encarga de traducir los objetos Java a sentencias SQL para almacenar el resultado en MySQL.


Trabajo para el futuro:

Integración con Dispositivos "Wearables" (Google Fit / Apple Health)

-En qué consiste: Conectar la aplicación mediante API con relojes inteligentes o pulseras de actividad.
-La app dejaría de basarse solo en el peso estático para ajustar las calorías diarias automáticamente en función de las calorías quemadas, pasos y frecuencia cardíaca real del usuario.

Generador de Lista de la Compra Inteligente

-En qué consiste: Una funcionalidad que transforme automáticamente los ingredientes de los planes de dieta generados en una lista de la compra organizada por pasillos de supermercado.
-Valor: Elimina la fricción entre tener el plan y ejecutarlo, mejorando enormemente la tasa de adopción del usuario.

Sistema de Análisis de Fotos (Visión Artificial)

-En qué consiste: Permitir que el usuario suba una foto de lo que ha comido y que la IA, mediante modelos de visión, estime las calorías y macronutrientes consumidos.
-Valor: Automatiza el seguimiento diario ("tracking"), evitando que el usuario tenga que escribir manualmente cada alimento.

Comunidad y "Social Eating"

-En qué consiste: Crear un entorno donde los usuarios puedan compartir sus planes de dieta favoritos, ver las valoraciones de otros y participar en retos nutricionales grupales.
-Valor: Aumenta la retención y la motivación mediante el refuerzo social y la gamificación.

Asistente de Optimización Presupuestaria

-En qué consiste: Integrar una funcionalidad que calcule el coste de los alimentos recomendados y sugiera alternativas más económicas o locales para mantener el presupuesto semanal.
-: Hace la nutrición saludable accesible a personas con presupuestos limitados, diferenciando a Nutrilio de otras apps que solo se enfocan en las calorías.



## 🏗️ Diagramas del Sistema

<div align="center">
  <img src="images/diagramas_nutrilio.jpg" alt="Diagramas de Nutrilio" width="800"/>
</div>
  
