<%-- 
    Document   : header
    Created on : 23/06/2025, 5:52:04 p. m.
    Author     : jhoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<input type="checkbox" id="btn-menu">
<div class="container-menu">
  <div class="cont-menu">
    <label for="btn-menu">✖️</label>
    <nav>
      <div class="card">
        <img src="${pageContext.request.contextPath}/Imagenes/veterinarian1.jpg" alt="Usuario">
        <h4>Pedro Navaja</h4>
        <h4>Pedro@gmail.com</h4>
        <h4>18 años</h4>
        <h4>Usuario: Dueño de mascota</h4>
        <a href="../Formularios/editarperfil.html" class="btn btn-primary">Editar</a>
      </div>

      <!-- Dropdown de Citas -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          Citas
        </a>
        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
          <li><a class="dropdown-item" href="DiagnosticoController" data-bs-toggle="modal" data-bs-target="#diagnosticoModal">Registrar Diagnostico</a></li>
          <li><a class="dropdown-item" href="CitaController?accion=ListarCitas">Consultar Citas</a></li>
        </ul>
      </li>

      <!-- Links adicionales -->
      <a class="nav-link" href="DietaController?accion=ConsultarMascotas">Consultar Dieta</a>
      <a class="nav-link" href="../Formularios/consultaact.html">Consultar Actividad Física</a>
      <a class="nav-link" href="../Formularios/ConsultarDiagnostico.html">Consultar Diagnóstico</a>

      <!-- Dropdown de Mascotas -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          Mascotas
        </a>
        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
          <li><a class="dropdown-item" href="MascotaController?accion=Listar">Mis mascotas</a></li>
          <li><a class="dropdown-item" href="MascotaController?accion=Agregar">Agregar Mascota</a></li>
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
