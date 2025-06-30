<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registrar Mascota - SCM</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <!-- Bootstrap + Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

  <!-- Estilo Personalizado -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
</head>
<body>

<<%@ include file="/duenomascota/menu.jsp" %>

<!-- CONTENIDO -->
<div class="container mt-5 mb-5">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="card shadow-lg border-0">
        <div class="card-header bg-info text-white text-center">
          <h4><i class="fas fa-dog"></i> Registrar Nueva Mascota</h4>
        </div>
        <div class="card-body">
          <form action="MascotaController" method="POST" class="needs-validation" novalidate enctype="multipart/form-data">
  <input type="hidden" name="accion" value="Registrar">

  <!-- Nombre -->
  <div class="form-group mb-4">
    <label class="form-label fw-bold text-primary">🐶 Nombre</label>
    <input type="text" name="Nombre" class="form-control form-control-lg rounded-pill" required placeholder="Ej: Max">
  </div>

  <!-- Género -->
  <div class="form-group mb-4">
    <label class="form-label fw-bold text-primary">⚧ Género</label>
    <select name="Genero" class="form-select form-select-lg rounded-pill" required>
      <option value="">Selecciona</option>
      <option value="Macho">Macho</option>
      <option value="Hembra">Hembra</option>
    </select>
  </div>

  <!-- Fecha de nacimiento -->
  <div class="form-group mb-4">
    <label class="form-label fw-bold text-primary">📅 Fecha de Nacimiento</label>
    <input type="date" name="FechaNacimineto" class="form-control form-control-lg rounded-pill" required>
  </div>

  <!-- Raza -->
  <div class="form-group mb-4">
    <label class="form-label fw-bold text-primary">🐾 Raza</label>
    <input type="text" name="Raza" class="form-control form-control-lg rounded-pill" required placeholder="Ej: Labrador">
  </div>
  <div class="mb-3">
            <label for="foto" class="form-label">Foto de Perfil</label>
            <input type="file" class="form-control" id="foto" name="Foto" accept="image/*">
        </div>

  <!-- Botones -->
  <div class="text-center">
    <button type="submit" class="btn btn-info btn-lg me-2 rounded-pill">
      <i class="fas fa-plus"></i> Registrar Mascota
    </button>
    <a href="MascotaController?accion=Listar" class="btn btn-secondary btn-lg rounded-pill">
      <i class="fas fa-arrow-left"></i> Volver
    </a>
  </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="/duenomascota/barralatereal.jsp" %>

<!-- FOOTER tipo SCM -->
<footer class="footer mt-5" id="footer">
  <div class="container py-4">
    <div class="row text-center text-md-start">
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">John Alvarado</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:jhonyse2345@gmail.com" class="footer-link">jhonyse2345@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">321-613-2975</span></li>
        </ul>
      </div>
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">Leonardo Atencio</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:leonardoatenciob30@gmail.com" class="footer-link">leonardoatenciob30@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">321-960-9864</span></li>
        </ul>
      </div>
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">Jhoani Gallo</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:jhoanigallo@gmail.com" class="footer-link">jhoanigallo@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">312-311-0320</span></li>
        </ul>
      </div>
    </div>

    <div class="text-center mt-4">
      <p class="footer-copy">&copy; 2024. Todos los derechos reservados.</p>
    </div>
  </div>
</footer>

<!-- Bootstrap Script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>





