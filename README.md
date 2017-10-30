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
              <b>nombreUsuario:</b> juan.arevalo.merchan<br>
              <b>contraseña:</b> 123<br>
              <b>correo:</b> juan.arevalo-m@mail.escuelaing.edu.co<br><br>
          <b>Usuario 2:</b><br>
              <b>Nombre:</b> Stefany Moron<br>
              <b>Edad:</b> 27<br>
              <b>nombreUsuario:</b> stefany.moron<br>
              <b>contraseña:</b> 1234<br>
              <b>correo:</b> stefany.moron@mail.escuelaing.edu.co<br><br>
         <b> Usuario 3:</b><br>
              <b>Nombre:</b> Hector Cadavid<br>
              <b>Edad:</b> 18<br>
              <b>nombreUsuario:</b> hector.cadavid<br>
              <b>contraseña:</b> 12345<br>
              <b>correo:</b> hector.cadavid@mail.escuelaing.edu.co<br><br>
       <b>Reportes de clima:</b>
          <b>ZONA 1:</b><br>
          <b>Reporte 1:</b><br>
              <b>Latitud:</b>4.746147<br>
              <b>Longitud:</b>-74.030096<br>
              <b>Clima:</b>sol<br>
              <b>Tiempo:</b>10<br>
              <b>Usuario:</b>Usuario 1<br><br>
          <b>Reporte 2:</b><br>
              <b>Latitud:</b>4.746040<br>
              <b>Longitud:</b>-74.031995<br>
              <b>Clima:</b>sol<br>
              <b>Tiempo:</b>12<br>
              <b>Usuario:</b>Usuario 2<br><br>
           <b>Reporte 3:</b><br>
              <b>Latitud:</b>4.748638<br>
              <b>Longitud:</b>-74.030353<br>
              <b>Clima:</b>sol<br>
              <b>Tiempo:</b>18<br>
              <b>Usuario:</b>Usuario 3<br><br>
          <b>ZONA 2:</b><br>
          <b>Reporte 4:</b><br>
              <b>Latitud:</b>4.733147<br>
              <b>Longitud:</b>-74.035017<br>
              <b>Clima:</b>agua<br>
              <b>Tiempo:</b>23<br>
              <b>Usuario:</b>Usuario 1<br><br>
          <b>Reporte 5:</b><br>
              <b>Latitud:</b>4.729650<br>
              <b>Longitud:</b>-74.031289<br>
              <b>Clima:</b>agua<br>
              <b>Tiempo:</b>32<br>
              <b>Usuario:</b>Usuario 2<br><br>
           <b>Reporte 6:</b><br>
              <b>Latitud:</b>4.732430<br>
              <b>Longitud:</b>-74.033574<br>
              <b>Clima:</b>agua<br>
              <b>Tiempo:</b>32<br>
              <b>Usuario:</b>Usuario 3<br><br>
          <b>ZONA 3:</b><br>
          <b>Reporte 7:</b><br>
              <b>Latitud:</b>4.706553<br>
              <b>Longitud:</b>-74.035508<br>
              <b>Clima:</b>nublado<br>
              <b>Tiempo:</b>55<br>
              <b>Usuario:</b>Usuario 1<br><br>
          <b>Reporte 8:</b><br>
              <b>Latitud:</b>4.703313<br>
              <b>Longitud:</b>-74.034446<br>
              <b>Clima:</b>nublado<br>
              <b>Tiempo:</b>65<br>
              <b>Usuario:</b>Usuario 2<br><br>
           <b>Reporte 9:</b><br>
              <b>Latitud:</b>4.703377<br>
              <b>Longitud:</b>-74.036817<br>
              <b>Clima:</b>nublado<br>
              <b>Tiempo:</b>75<br>
              <b>Usuario:</b>Usuario 3<br><br>
              


Nuestra aplicación esta desplegada en Heroku en el siguiente link: [https://sharingweather.herokuapp.com/](https://sharingweather.herokuapp.com/)

NOTA: Para tener acceso a la ubicación, se debe ingresar por medio del protocolo https, si se utiliza http, automaticamente se bloquea la ubicación del dispositivo. (Para dispositivos moviles encender el GPS)

El enlace al entorno Codacy del proyecto [![Codacy Badge](https://api.codacy.com/project/badge/Grade/cc5e54277354433084212f5e7e0997ef)](https://www.codacy.com/app/JuanPabloArevalo/ARSWProyecto?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=JuanPabloArevalo/ARSWProyecto&amp;utm_campaign=Badge_Grade)

El enlace al entorno Circle.CI del proyecto [![CircleCI](https://circleci.com/gh/JuanPabloArevalo/ARSWProyecto.svg?style=svg)](https://circleci.com/gh/JuanPabloArevalo/ARSWProyecto)



