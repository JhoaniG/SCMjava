<%-- 
    Document   : crearActividadFisica
    Created on : 29/06/2025, 3:23:25 p. m.
    Author     : jhoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Dieta</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    
    <!-- Tus estilos -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .form-card {
            max-width: 1000px;
            margin: auto;
            background: #fff;
            padding: 40px 45px;
            border-radius: 25px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
        }

        .form-title {
            text-align: center;
            font-size: 32px;
            font-weight: bold;
            color: #0d6efd;
            margin-bottom: 40px;
        }

        .input-group-text {
            background-color: #f0f0f0;
            font-size: 18px;
        }

        .form-label {
            font-size: 17px;
        }

        .btn {
            font-size: 16px;
            min-width: 150px;
            padding: 10px 20px;
        }
    </style>
</head>
<body>

    <%@ include file="/veterinario/menuV.jsp" %>
    <%@ include file="/veterinario/barralateralV.jsp" %>

    <main style="margin-top: 90px; margin-left: 250px; padding: 30px;">
        <div class="form-card">
            <div class="form-title">📝 Registrar Actividad para Mascota</div>

            <form action="ActividadFisicaController" method="post">
                <input type="hidden" name="accion" value="RegistrarActividadFisica">
                <input type="hidden" name="idV" value="${idVeterinario}">

                <!-- Mascota -->
                <div class="mb-4">
                    <label class="form-label fw-semibold">🐾 Mascota</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-paw"></i></span>
                        <select name="idM" class="form-select form-select-lg" required>
                            <option value="">Seleccione una mascota</option>
                            <c:forEach var="m" items="${listaMascotas}">
                                <option value="${m.idM}">${m.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <!-- Tipo de Dieta -->
                <div class="mb-4">
                    <label class="form-label fw-semibold">🍽️ Tipo de Actividad</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-nut-fill"></i></span>
                        <select name="tipoActividad" class="form-select form-select-lg" required>
                            <option value="">Paseos Diarios</option>
                            <option value="Juegos de lanzar y traer">Juegos de lanzar y traer</option>
                            <option value="Carreras en el parque">Carreras en el parque</option>
                            <option value="Juegos con juguetes">Juegos con juguetes</option>
                            <option value="Rascadores y árboles para gatos"> Rascadores y árboles para gatos</option>
                            <option value="Sesiones de caza"> Sesiones de caza</option>
                            <option value="Juegos de persecución">Juegos de persecución</option>
                        </select>
                    </div>
                </div>

                <!-- Descripción -->
                <div class="mb-4">
                    <label class="form-label fw-semibold">🗒️ Descripción</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                        <textarea name="descripcion" class="form-control form-control-lg" rows="4" placeholder="Describa la dieta sugerida para la mascota..." required></textarea>
                    </div>
                </div>

                <!-- Botones -->
                <div class="text-end mt-4">
                    <button type="submit" class="btn btn-success me-2">✅ Registrar</button>
                    <a href="DietaController?accion=GenerarDieta" class="btn btn-secondary">↩️ Volver</a>
                </div>
            </form>
        </div>
    </main>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

