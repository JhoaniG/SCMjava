<%-- 
    Document   : mismascotas
    Created on : 23/06/2025, 5:30:41 p. m.
    Author     : jhoan
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, modelo.Mascota" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Mascotas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body style="background: #fff; color: #000;">

<!-- Header superior (si tienes uno) -->
<%@ include file="/duenomascota/menu.jsp" %>

<div class="container mt-5">
  <h2 class="text-center mb-4" style="color: #333;">🐶 Mis Mascotas</h2>

  <div class="row">
    <%
      List<Mascota> mascotas = (List<Mascota>) request.getAttribute("mascotas");
      if (mascotas != null && !mascotas.isEmpty()) {
        for (Mascota m : mascotas) {
    %>
    <div class="col-md-4 mb-4">
      <div class="card h-100 shadow-sm">
        <img src="${pageContext.request.contextPath}/Imagenes/perfilMascota.png" class="card-img-top" alt="Mascota">
        <div class="card-body" style="color: #000;">
          <h5 class="card-title fw-bold" style="color: #000;"><%= m.getNombre() %></h5>
          <p class="card-text" style="color: #000;">
            <strong>Raza:</strong> <%= m.getRaza() %><br>
            <strong>Género:</strong> <%= m.getGenero() %><br>
            <strong>Nacimiento:</strong> <%= m.getFechaNacimineto() %>
          </p>
          <a href="MascotaController?accion=Editar&IdM=<%= m.getIdM() %>" class="btn btn-warning btn-sm">
            <i class="fas fa-edit"></i> Editar
          </a>
          <a href="MascotaController?accion=Eliminar&IdM=<%= m.getIdM() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Deseas eliminar esta mascota?');">
            <i class="fas fa-trash-alt"></i> Eliminar
          </a>
        </div>
      </div>
    </div>
    <% 
        }
      } else { 
    %>
    <div class="col-12">
      <div class="alert alert-info text-center">No tienes mascotas registradas aún.</div>
    </div>
    <% } %>
  </div>

  <div class="text-center mt-4">
    <a href="MascotaController?accion=Agregar" class="btn btn-primary">
      <i class="fas fa-plus-circle"></i> Agregar nueva mascota
    </a>
  </div>
</div>

<!-- Footer -->
<footer class="footer mt-5 bg-dark text-white">
  <div class="container text-center py-3">
    <p class="mb-0">&copy; 2024 SCM - Sistema de Control de Mascotas</p>
  </div>
</footer>

<!-- Menú lateral (si lo usas) -->
<%@ include file="/duenomascota/barralatereal.jsp" %>

<!-- Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>


