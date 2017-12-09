### ESCUELA COLOMBIANA DE INGENIERÍA JULIO GARAVITO
### Arquitecturas de Software - ARSW
### PROYECTO ARQUITECTURA DE SOFTWARE - SHARINGWEATHER
### Stefany Morón y Juan Pablo Arévalo Merchán


<b>Descripción:</b>

<b>SharingWeather</b> busca facilitar la movilidad de las personas que van en sus vehículos e incluso a las que van a pie, dándoles la idea de como esta el clima en el lugar a donde piensan ir, ya que  muchas veces cuando salimos de nuestras casas, de nuestros lugares de trabajo o de estudio, nos preguntamos ¿cómo estará el clima en nuestro lugar de destino?, ¿Será que está lloviendo? o ¿Estará haciendo sol?, pues esta será una aplicación que te podrá servir, será una red colaborativa, que te permitirá conocer el clima en tiempo real en cualquier parte.

<b>¿Como funciona?:</b>
Lo primero que podemos realizar aca, es consultar el mapa en tiempo real, este nos va a permitir estar monitoreando los reportes del clima, directamente en el mapa, así cada vez se realice algún reporte y este cumpla con los criterios de confiabilidad (3 o más reportes deben coincidir en la misma zona, esto se mide en un rango de 700 metros o menos entre cada reporte y si tienen el mismo clima), esto se verá reflejado en un circulo de un color diferente para cada clima, dependiendo de los repotes.

Para poder realizar la publicación se debe poder tener acceso a la ubicación del dispositivo, de lo contrario no se podrá publicar, para ello, el navegador solicitará la ubicación del equipo y automáticamente generará la longitud y latitud donde se encuentre el usuario, después de esto, se debe poder elegir el clima que esta haciendo y por ultimo, se dirá cuanto tiempo lleva el clima en esa zona (En minutos), se procede a publicar el reporte del clima.

<b>Información por defecto:</b><br><br>
       <b>Usuarios:</b><br><br>
          <b>Usuario 1:</b><br>
              <b>Nombre:</b> Juan Arevalo<br>
              <b>Edad:</b> 25<br>
              <b>nombreUsuario:</b> juanarevalomerchan<br>
              <b>contraseña:</b> 123<br>
              <b>correo:</b> juan.arevalo-m@mail.escuelaing.edu.co<br><br>
          <b>Usuario 2:</b><br>
              <b>Nombre:</b> Stefany Moron<br>
              <b>Edad:</b> 27<br>
              <b>nombreUsuario:</b> stefanymoron<br>
              <b>contraseña:</b> 1234<br>
              <b>correo:</b> stefany.moron@mail.escuelaing.edu.co<br><br>
         <b> Usuario 3:</b><br>
              <b>Nombre:</b> Hector Cadavid<br>
              <b>Edad:</b> 18<br>
              <b>nombreUsuario:</b> hectorcadavid<br>
              <b>contraseña:</b> 12345<br>
              <b>correo:</b> hector.cadavid@mail.escuelaing.edu.co<br><br>



### SharingWeather desplegada en AWS

Para ingresar a nuestra aplicación alojada en amazon, debemos entrar por la siguiente dirección : [sharinweather](http://34.209.213.0:8090/) ,esto direccionará a alguno de los servidores donde se encuentra alojada.
Contamos con base de datos externa MoongoDB (mlab), allí se almacenará todos los datos de nuestros usuarios.
Tenemos una base de datos dentro de Amazon, con REDIS, allí se almacenarán todos nuestros datos sobre la publicación de los climas.

Desafortunadamente nuestra aplicación requiere la ubicación, y el tema de geolocalización no funciona si no es con https, pero en amazon no contamos con eso, por lo cual la localización automatica no se puede utilizar.
Es por eso que hemos desbloquedo los campos de latitud y longitud para ingresarlos manualmente.


El enlace al entorno Codacy del proyecto [![Codacy Badge](https://api.codacy.com/project/badge/Grade/cc5e54277354433084212f5e7e0997ef)](https://www.codacy.com/app/JuanPabloArevalo/ARSWProyecto?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=JuanPabloArevalo/ARSWProyecto&amp;utm_campaign=Badge_Grade)

El enlace al entorno Circle.CI del proyecto [![CircleCI](https://circleci.com/gh/JuanPabloArevalo/ARSWProyecto.svg?style=svg)](https://circleci.com/gh/JuanPabloArevalo/ARSWProyecto)



