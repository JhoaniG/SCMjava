<%-- 
    Document   : barralateralV
    Created on : 27/06/2025, 9:21:28 a. m.
    Author     : jhoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<input type="checkbox" id="btn-menu">
<div class="container-menu">
  <div class="cont-menu">
    <label for="btn-menu">✖️</label>
    <nav>
      <div class="card">
        <img src="../Imagenes/veterinarian1.jpg" alt="Usuario">
        <h4 class="card-title">Juanito alimaña</h4>
              <h4 class="card-title">Juanito@gmail.com</h4>
              <h4 class="card-title">30 anios</h4>
              <h4 class="card-title">Usuario:Veterinario</h4>
              <a href="../Formularios/editarperfil.html" class="btn btn-primary">Editar</a>
      </div>

      <!-- Dropdown de Citas -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          Citas
        </a>
        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
          <li><a class="dropdown-item" href="DiagnosticoController?accion=Listar">Consultar Diagnostico</a></li>
          <li><a class="dropdown-item" href="../Formularios/ConsultarCita.html">Consultar Citas</a></li>
        </ul>
      </li>

      <!-- Links adicionales -->
      <a class="nav-link"  href="actividad.html">Actividad</a>
      <a class="nav-link" href="dieta.html">Dieta</a>

      <!-- Dropdown de Mascotas -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          Mascotas
        </a>
        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
          <li><a class="dropdown-item" href="MismascotasVete.html">Mis mascotas</a></li>
          <li><a class="dropdown-item" href="../Formularios/formulario_registro_mascota.html">Agregar Mascota</a></li>
        </ul>
      </li>

      <div class="text-center">
        <button class="btn btn-danger">
          <a href="../index.html" class="btn-a" data-bs-toggle="modal" data-bs-target="#logoutModal">Cerrar sesión</a>
        </button>
      </div>
    </nav>
  </div>
</div>
