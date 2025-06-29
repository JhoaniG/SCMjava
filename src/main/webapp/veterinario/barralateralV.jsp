<%-- 
    Document   : barralateralV
    Created on : 27/06/2025, 9:21:28 a. m.
    Author     : jhoan
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Asegúrate de incluir Bootstrap JS al final del body en la página principal -->

<input type="checkbox" id="btn-menu">
<div class="container-menu" style="position: fixed; left: 0; top: 0; width: 250px; height: 100vh; background-color: #f8f9fa; overflow-y: auto; box-shadow: 2px 0 5px rgba(0,0,0,0.1); padding: 15px;">
  <div class="cont-menu">
    <label for="btn-menu" style="cursor:pointer; float: right;">✖️</label>
    <nav>
      <div class="card mb-3 text-center">
        <img src="../Imagenes/veterinarian1.jpg" class="card-img-top rounded-circle" alt="Usuario" style="width: 100px; margin: 10px auto;">
        <div class="card-body">
          <h5 class="card-title">Juanito alimaña</h5>
          <p class="card-text">Juanito@gmail.com</p>
          <p class="card-text">30 años</p>
          <p class="card-text">Veterinario</p>
          <a href="../Formularios/editarperfil.html" class="btn btn-primary btn-sm">Editar perfil</a>
        </div>
      </div>

      <div class="nav flex-column">
        <div class="dropdown mb-2">
          <a class="btn btn-outline-dark dropdown-toggle w-100" href="#" id="dropdownCitas" data-bs-toggle="dropdown" aria-expanded="false">
            Citas
          </a>
          <ul class="dropdown-menu w-100" aria-labelledby="dropdownCitas">
            <li><a class="dropdown-item" href="DiagnosticoController?accion=Listar">Consultar Diagnóstico</a></li>
            <li><a class="dropdown-item" href="CitaController?accion=ListarCitas">Consultar Citas</a></li>
          </ul>
        </div>

        <a class="btn btn-outline-dark w-100 mb-2" href="actividad.html">Actividad</a>
        <a class="btn btn-outline-dark w-100 mb-2" href="DietaController?accion=GenerarDieta">Dieta</a>

        <div class="dropdown mb-2">
          <a class="btn btn-outline-dark dropdown-toggle w-100" href="#" id="dropdownMascotas" data-bs-toggle="dropdown" aria-expanded="false">
            Mascotas
          </a>
          <ul class="dropdown-menu w-100" aria-labelledby="dropdownMascotas">
            <li><a class="dropdown-item" href="MismascotasVete.html">Mis mascotas</a></li>
            <li><a class="dropdown-item" href="../Formularios/formulario_registro_mascota.html">Agregar Mascota</a></li>
          </ul>
        </div>

        <form action="../index.html" method="post">
          <button type="submit" class="btn btn-danger w-100">Cerrar sesión</button>
        </form>
      </div>
    </nav>
  </div>
</div>
