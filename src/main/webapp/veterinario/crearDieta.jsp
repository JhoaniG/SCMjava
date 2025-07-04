<%--
    Document   : crearDieta
    Created on : 29/06/2025, 1:07:28 p. m.
    Author     : jhoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Registrar Dieta</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

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
                <div class="form-title">📝 Registrar Dieta para Mascota</div>

                <form action="DietaController" method="post" class="needs-validation" novalidate enctype="multipart/form-data">
                    <input type="hidden" name="accion" value="RegistrarDieta">
                    <input type="hidden" name="idV" value="${idVeterinario}">

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

                    <div class="mb-4">
                        <label class="form-label fw-semibold">🍽️ Tipo de Dieta</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-nut-fill"></i></span>
                            <select name="tipoDieta" class="form-select form-select-lg" required>
                                <option value="">Seleccione tipo de dieta</option>
                                <option value="Baja en grasas">Baja en grasas</option>
                                <option value="Alta en proteínas">Alta en proteínas</option>
                                <option value="Hipoalergénica">Hipoalergénica</option>
                                <option value="Light para control de peso">Light para control de peso</option>
                                <option value="Rica en fibra">Rica en fibra</option>
                                <option value="Dieta especial (recetada)">Dieta especial (recetada)</option>
                            </select>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label fw-semibold">🗒️ Descripción</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                            <textarea name="descripcion" class="form-control form-control-lg" rows="4" placeholder="Describa la dieta sugerida para la mascota..." required></textarea>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="foto" class="form-label">Foto de Dieta</label>
                        <input type="file" class="form-control" id="foto" name="Foto" accept="image/*">
                    </div>

                    <div class="text-end mt-4">
                        <button type="submit" class="btn btn-success me-2">✅ Registrar</button>
                        <a href="DietaController?accion=GenerarDieta" class="btn btn-secondary">↩️ Volver</a>
                    </div>
                </form>
            </div>
        </main>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>