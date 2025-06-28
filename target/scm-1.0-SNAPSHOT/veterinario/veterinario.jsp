<%-- 
    Document   : veterinario
    Created on : 20/06/2025, 11:22:50 a. m.
    Author     : jhoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
    <title>Seguimiento y control de mascotas</title>
</head>

<%@include file="/veterinario/menuV.jsp" %>

<div class="capa">
  <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="../Imagenes/img1.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="../Imagenes/img2.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="../Imagenes/img3.jpg" class="d-block w-100" alt="...">
      </div>
    </div>
  </div>

  <!--ACERCA DE -->
  <div class="container-fluid py-5" >
    <div class="container">
        <div class="section-title text-center">
            <h4 class="text-primary text-uppercase">Acerca de Nosotros</h4>
            <h1 class="display-4">Cuidamos a tu mascota con amor</h1>
        </div>
        <div class="row align-items-center">
            <!-- Columna Izquierda: Texto -->
            <div class="col-lg-5 py-4">
                <h2 class="mb-4">Nuestra Historia</h2>
                <p>Fundados con el objetivo de mejorar el acceso a servicios de salud para tus mascotas, hemos crecido con una base de clientes satisfechos y animales felices. Nos apasiona ofrecer atención de calidad y personalizada para que tu mascota siempre reciba lo mejor.</p>
                <p>Estamos comprometidos con la salud y bienestar de cada mascota, buscando siempre innovar y adaptarnos a las necesidades de nuestros clientes. Con nosotros, tus peludos siempre están en buenas manos.</p>
            </div>
            
            <!-- Columna Central: Imagen -->
            <div class="col-lg-2 py-4 text-center">
                <img src="../Imagenes/perrotres.avif" alt="Imagen de nuestra historia" class="img-fluid rounded" style="max-height: 300px; object-fit: cover; width: 100%;">
            </div>

            <!-- Columna Derecha: Texto -->
            <div class="col-lg-5 py-4">
                <h2 class="mb-4">Nuestra Visión</h2>
                <p>Queremos ser el puente de confianza entre los dueños de mascotas y los mejores veterinarios del país. Nuestra visión es brindar atención de salud rápida, efectiva y de calidad para cada mascota, en todo momento.</p>
                <ul class="list-unstyled">
                  <li><i class="fas fa-check-circle"></i> Acceso rápido a veterinarios de confianza</li>
                  <li><i class="fas fa-check-circle"></i> Atención personalizada y de calidad</li>
                  <li><i class="fas fa-check-circle"></i> Salud y bienestar garantizados</li>
                </ul>                                        
                <a href="#" class="btn btn-info font-weight-bold py-2 px-4 mt-2">Saber más</a>
            </div>
        </div>
    </div>
<!--Fin de acerca-->


<!--NUESTROS PROFECIONALES -->
<div id="seccion1">
  <h2 class="display-1 m-5 text-center d-none d-md-block">¡Nuestros Profesionales!</h2>
  <!-- Tarjetas -->
  <div class="container mt-5 mb-5 d-none d-md-block">
    <div class="row">
      <div class="col-md-4">
        <div class="card border-0">
          <img src="../Imagenes/veterinarian1.jpg" class="card-img-top" alt="Dr. Lucas Medina">
          <div class="card-body">
            <h5 class="card-title">Dr. Lucas Medina</h5>
            <p class="card-text">Es un veterinario con 8 años de experiencia en medicina interna y cuidado preventivo. Se especializa en el diagnóstico temprano de enfermedades y la prevención de problemas de salud en mascotas.</p>
            <a href="#" class="btn btn-primary">Conócele</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card border-0">
          <img src="../Imagenes/veterinarian2.jpg" class="card-img-top" alt="Dra. Valeria Cruz">
          <div class="card-body">
            <h5 class="card-title">Dra. Valeria Cruz</h5>
            <p class="card-text">Especialista en cirugía veterinaria y atención de emergencias, con 10 años de experiencia en casos complejos y manejo quirúrgico de emergencia.</p>
            <a href="#" class="btn btn-primary">Conócele</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card border-0">
          <img src="../Imagenes/veterinarian3.jpg" class="card-img-top" alt="Dr. Tomás Ruiz">
          <div class="card-body">
            <h5 class="card-title">Dr. Tomás Ruiz</h5>
            <p class="card-text">Con 6 años de experiencia en dermatología y tratamiento de alergias, se enfoca en soluciones efectivas para problemas de la piel en mascotas.</p>
            <a href="#" class="btn btn-primary">Conócele</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<!--Fin Nuestros profecionales -->



<!--Inicio Trajetas Nuestras mascotas -->
<div id="seccion2">
  <!-- Historia de Max y Pipo -->
  <div class="container mt-5 cardV-imagen">
    <div class="card mb-3 border-0">
      <div class="row g-0 align-items-center">
        <div class="col-md-5">
          <img src="../Imagenes/mascota2.jpg" class="img-fluid rounded" alt="Max y Pipo">
        </div>
        <div class="col-md-7">
          <div class="card-body">
            <h2 class="card-title">¡Conoce a Max y Pipo!</h2>
            <p class="card-text">Max, un perrito curioso, y Pipo, un pollito inquieto, enfrentaron problemas de salud que se resolvieron gracias al sistema SCM. Sus dueños lograron diagnósticos rápidos y cuidados eficaces que cambiaron sus vidas.</p>
            <a href="Formularios/formulario_registro_mascota.html" class="btn btn-info">Registra tu mascota</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Historia de Milo -->
  <div class="container mt-5 cardV-imagen">
    <div class="card mb-3 border-0">
      <div class="row g-0 align-items-center">
        <div class="col-md-7">
          <div class="card-body">
            <h2 class="card-title">¡Conoce a Milo!</h2>
            <p class="card-text">Milo, un perrito pequeño de pelaje blanco y manchas negras, recibió atención médica oportuna gracias a SCM. Su dueño pudo registrar sus síntomas fácilmente, accediendo a un diagnóstico preciso y un tratamiento rápido.</p>
            <a href="Formularios/formulario_registro_mascota.html" class="btn btn-info">Registra tu mascota</a>
          </div>
        </div>
        <div class="col-md-5">
          <img src="../Imagenes/mascota1.jpg" class="img-fluid rounded" alt="Milo">
        </div>
      </div>
    </div>
  </div>
</div>

<!--FinTrajetas Nuestras mascotas -->



<!--Contactanos seccion -->
<div id="seccion3">
  <div class="container mt-5">
    <h2 class="text-center mb-5">¡Contáctanos!</h2>
    <form action="/enviar" method="POST">
      <div class="mb-4">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" required>
      </div>
      <div class="mb-4">
        <label for="email" class="form-label">Correo Electrónico</label>
        <input type="email" class="form-control" id="email" name="email" required>
      </div>
      <div class="mb-4">
        <label for="mensaje" class="form-label">Mensaje</label>
        <textarea class="form-control" id="mensaje" name="mensaje" rows="4" required></textarea>
      </div>
      <button type="submit" class="btn btn-info">Enviar</button>
    </form>
  </div>
</div>


<!--Fin contactanos -->



<!--footer-->
<footer class="footer mt-5">
  <div class="container py-4">
      <div class="row text-center text-md-start">
          <!-- Persona 1 -->
          <div class="col-md-4 mb-3">
              <h5 class="footer-title">John Alvarado</h5>
              <ul class="list-unstyled">
                  <li>
                      <i class="fas fa-envelope"></i> 
                      <a href="mailto:jhonyse2345@gmail.com" class="footer-link">jhonyse2345@gmail.com</a>
                  </li>
                  <li>
                      <i class="fas fa-phone"></i> 
                      <span class="footer-text">321-613-2975</span>
                  </li>
              </ul>
          </div>

          <!-- Persona 2 -->
          <div class="col-md-4 mb-3">
              <h5 class="footer-title">Leonardo Atencio</h5>
              <ul class="list-unstyled">
                  <li>
                      <i class="fas fa-envelope"></i> 
                      <a href="mailto:leonardoatenciob30@gmail.com" class="footer-link">leonardoatenciob30@gmail.com</a>
                  </li>
                  <li>
                      <i class="fas fa-phone"></i> 
                      <span class="footer-text">321-960-9864</span>
                  </li>
              </ul>
          </div>

          <!-- Persona 3 -->
          <div class="col-md-4 mb-3">
              <h5 class="footer-title">Jhoani Gallo</h5>
              <ul class="list-unstyled">
                  <li>
                      <i class="fas fa-envelope"></i> 
                      <a href="mailto:jhoanigallo@gmail.com" class="footer-link">jhoanigallo@gmail.com</a>
                  </li>
                  <li>
                      <i class="fas fa-phone"></i> 
                      <span class="footer-text">312-311-0320</span>
                  </li>
              </ul>
          </div>
      </div>

      <div class="text-center mt-4">
          <p class="footer-copy">&copy; 2024. Todos los derechos reservados.</p>
      </div>
  </div>
</footer>
<!--footer-->

</div>



  <%@include file="/veterinario/barralateralV.jsp" %>

<!--modal-->
<!-- Modal Cerrar Sesión -->
<div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #c01717;">
        <h5 class="modal-title" id="logoutModalLabel" style="color: white;">¿Estás seguro de que deseas cerrar sesión?</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="d-flex justify-content-center">
          <i class="fas fa-user-thinking" style="font-size: 40px; color: #DA9F5B; margin-right: 10px;"></i>
          <p style="font-size: 1.1rem;">¡Piensa bien antes de cerrar sesión!</p>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" href="duenio.html">Cancelar</button>
        <button type="button" class="btn btn-danger" onclick="window.location.replace('../index.html')">Cerrar Sesión</button>
      </div>  
    </div>
  </div>
</div>






    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
