<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Seleccionar Dueño</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <!-- Tus estilos -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">

    <style>
        .card-hover:hover {
            transform: scale(1.02);
            transition: 0.3s;
        }
        .card-img-avatar {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 50%;
            border: 2px solid #0d6efd;
        }
        .card-title {
            margin-bottom: 0.3rem;
        }
        .btn-seleccionar {
            background: linear-gradient(to right, #198754, #20c997);
            border: none;
        }
        .btn-seleccionar:hover {
            background: linear-gradient(to right, #157347, #198754);
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <!-- Menú superior -->
    <%@ include file="/veterinario/menuV.jsp" %>

    <div class="d-flex" style="margin-top: 90px;">

        <!-- Barra lateral -->
        <div>
            <%@ include file="/veterinario/barralateralV.jsp" %>
        </div>

        <!-- Contenido -->
        <div class="flex-grow-1 p-4">
            <div class="container">
                <h2 class="text-center mb-4 text-primary">👥 Dueños de Mascotas Registrados</h2>

                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                    <c:forEach var="dueno" items="${listaDuenos}">
                        <div class="col">
                            <div class="card shadow-sm card-hover h-100 border-0">
                                <div class="card-body text-center">
                                    <img src="${pageContext.request.contextPath}/Imagenes/gato.jpg" class="card-img-avatar mb-3" alt="Avatar">
                                    <h5 class="card-title fw-bold text-dark">${dueno.nombre} ${dueno.apellido}</h5>
                                    <p class="text-muted mb-1"><i class="bi bi-person-badge-fill me-1"></i>ID: ${dueno.idU}</p>
                                    <p class="text-muted"><i class="bi bi-envelope-fill me-1"></i>${dueno.correo}</p>
                                    <a href="DietaController?accion=SeleccionarDueno&idDueno=${dueno.idU}"
                                       class="btn btn-seleccionar btn-sm w-100 mt-3 text-white">
                                       <i class="bi bi-check-circle me-1"></i>Seleccionar
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

