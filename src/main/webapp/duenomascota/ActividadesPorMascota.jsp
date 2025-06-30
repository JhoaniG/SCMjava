<%-- 
    Document   : ActividadesPorMascota
    Created on : Jun 29, 2025, 8:57:20 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SCM - Consulta de Actividad Física</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">

    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css" rel="stylesheet">
</head>
<body>

    <%@include file="/duenomascota/menu.jsp" %>
    <%@include file="/duenomascota/barralatereal.jsp" %>

<div class="container text-center mt-5">
  <h1 class="header-title">SCM - Consulta de Actividad Física</h1>
  <p class="lead">Consulta la actividad física recomendada para tu mascota</p>
</div>

<div class="container mt-3 text-center">
    <%-- Botón Volver (manteniendo el enlace a la lista de mascotas de actividad) --%>
    <a href="${pageContext.request.contextPath}/ActividadFisicaController?accion=ConsultarMascotasActividad" class="btn btn-secondary">
        ← Volver a Mis Mascotas
    </a>
</div>

<div class="page-cards-container">
  <c:choose>
    <c:when test="${empty actividades}">
      <div class="alert alert-warning w-100 text-center">
        Esta mascota no tiene actividades físicas registradas.
      </div>
    </c:when>

    <c:otherwise>
      <c:forEach var="af" items="${actividades}">
        <div class="page-card">
          <img src="${pageContext.request.contextPath}/${af.foto}" alt="Actividad de ${af.nombreMascota}">
          <div class="page-card-body">
            <h4>${af.tipoActividad}</h4>
            <p>${af.descripcion}</p>
            <small class="text-muted d-block">🐾 Mascota: ${af.nombreMascota}</small>
            <small class="text-muted d-block">👩‍⚕️ Vet.: Dr.&nbsp;${af.nombreVeterinario}</small>
            <%-- Botón "Ver más" REMOVIDO --%>
          </div>
        </div>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</div>

<div class="container mt-5">
  <div id="calendar"></div>
</div>

<footer class="footer mt-5">
  <div class="container py-4">
    <div class="row text-center text-md-start">
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">John&nbsp;Alvarado</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:jhonyse2345@gmail.com" class="footer-link">jhonyse2345@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">321‑613‑2975</span></li>
        </ul>
      </div>
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">Leonardo&nbsp;Atencio</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:leonardoatenciob30@gmail.com" class="footer-link">leonardoatenciob30@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">321‑960‑9864</span></li>
        </ul>
      </div>
      <div class="col-md-4 mb-3">
        <h5 class="footer-title">Jhoani&nbsp;Gallo</h5>
        <ul class="list-unstyled">
          <li><i class="fas fa-envelope"></i> <a href="mailto:jhoanigallo@gmail.com" class="footer-link">jhoanigallo@gmail.com</a></li>
          <li><i class="fas fa-phone"></i> <span class="footer-text">312‑311‑0320</span></li>
        </ul>
      </div>
    </div>
    <div class="text-center mt-4">
      <p class="footer-copy">&copy; 2024. Todos los derechos reservados.</p>
    </div>
  </div>
</footer>

<div class="modal fade" id="eventModal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title" id="eventModalLabel">Añadir Nota para el <span id="eventDateDisplay"></span></h5>
        <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label for="eventTitle">Descripción de la nota:</label>
          <textarea class="form-control" id="eventTitle" rows="4" placeholder="Escribe aquí tu nota o recordatorio..."></textarea>
        </div>
        <small class="form-text text-muted">Esta nota se guardará localmente en tu calendario.</small>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-primary" id="saveEventBtn">Guardar Nota</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="successModalLabel">¡Éxito!</h5>
        <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body text-center">
        <i class="fas fa-check-circle text-success" style="font-size: 3rem; margin-bottom: 15px;"></i>
        <p>Nota guardada correctamente.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" data-dismiss="modal">Aceptar</button>
      </div>
    </div>
  </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>


<script>
  document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');
    let selectedDate = null; // Para almacenar la fecha seleccionada

    const today = new Date().toISOString().split('T')[0];

    const calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      locale: 'es',
      selectable: true,
      editable: false,
      events: [], // Inicializa con un array de eventos vacío si no cargas eventos de base de datos

      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },

      validRange: { start: today },

      dateClick: function (info) {
        if (info.dateStr < today) {
          alert('No puedes programar notas en fechas pasadas.'); // Mensaje adaptado para notas genéricas
          return;
        }

        selectedDate = info.dateStr; // Almacena la fecha seleccionada
        document.getElementById('eventDateDisplay').innerText = info.dateStr; // Muestra la fecha en el modal
        $('#eventModal').modal('show'); // Muestra el modal de Bootstrap para añadir nota
      }
    });

    calendar.render();

    document.getElementById('saveEventBtn').addEventListener('click', function() {
        const eventTitle = document.getElementById('eventTitle').value.trim();

        if (eventTitle) {
            calendar.addEvent({
                title: eventTitle,
                start: selectedDate,
                allDay: true // Si la nota es para todo el día
            });
            $('#eventModal').modal('hide'); // Oculta el modal de añadir nota
            document.getElementById('eventTitle').value = ''; // Limpia el textarea

            // Mostrar el modal de éxito
            $('#successModal').modal('show');
            // Opcional: ocultar el modal de éxito automáticamente después de unos segundos
            setTimeout(function() {
                $('#successModal').modal('hide');
            }, 2000); // 2 segundos
        } else {
            alert('Por favor, ingresa una descripción para la nota.');
        }
    });

    // Limpiar el campo del modal cuando se cierra
    $('#eventModal').on('hidden.bs.modal', function () {
        document.getElementById('eventTitle').value = '';
    });
  });
</script>