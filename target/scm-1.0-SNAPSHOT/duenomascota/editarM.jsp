<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="modelo.Mascota" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Mascota</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

<%@ include file="/duenomascota/menu.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-lg border-0">
                <div class="card-header bg-primary text-white text-center">
                    <h4><i class="fas fa-edit"></i> Editar Información de Mascota</h4>
                </div>
                <%
                    Mascota m = (Mascota) request.getAttribute("mascota");
                    if (m != null) {
                %>
                <div class="card-body">
                    <form action="MascotaController" method="post">
                        <input type="hidden" name="accion" value="Actualizar">
                        <input type="hidden" name="IdM" value="<%= m.getIdM() %>">

                        <div class="mb-3">
                            <label for="Nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" name="Nombre" id="Nombre" value="<%= m.getNombre() %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="Genero" class="form-label">Género</label>
                            <select class="form-select" name="Genero" id="Genero" required>
                                <option value="Macho" <%= m.getGenero().equals("Macho") ? "selected" : "" %>>Macho</option>
                                <option value="Hembra" <%= m.getGenero().equals("Hembra") ? "selected" : "" %>>Hembra</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="FechaNacimineto" class="form-label">Fecha de Nacimiento</label>
                            <input type="date" class="form-control" name="FechaNacimineto" id="FechaNacimineto" value="<%= m.getFechaNacimineto() %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="Raza" class="form-label">Raza</label>
                            <input type="text" class="form-control" name="Raza" id="Raza" value="<%= m.getRaza() %>" required>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-success px-4">
                                <i class="fas fa-save"></i> Guardar Cambios
                            </button>
                            <a href="MascotaController?accion=Listar" class="btn btn-secondary px-4 ms-2">
                                <i class="fas fa-times"></i> Cancelar
                            </a>
                        </div>
                    </form>
                </div>
                <% } else { %>
                <div class="card-body">
                    <div class="alert alert-danger text-center">No se encontró la información de la mascota.</div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<%@ include file="/duenomascota/barralatereal.jsp" %>

<footer class="footer mt-5">
    <div class="container text-center py-3">
        <p class="mb-0">&copy; 2024 SCM - Sistema de Control de Mascotas</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

