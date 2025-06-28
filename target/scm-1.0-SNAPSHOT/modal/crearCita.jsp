<%-- 
    Document   : crearCita
    Created on : 27/06/2025, 10:47:28?a. m.
    Author     : jhoan
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Modal Crear Cita -->
<!-- MODAL COMPLETO -->

<!-- Modal Crear Cita -->
<div class="modal fade" id="citaModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <form action="${pageContext.request.contextPath}/CitaController" method="post">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title">Crear Cita</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <input type="hidden" name="accion" value="RegistrarCita">
          <input type="hidden" name="IdD" id="modalIdD">

          <!-- Fecha -->
          <div class="mb-3">
            <label class="form-label">Fecha de la Cita</label>
            <input type="date" name="FechaCita" class="form-control" required>
          </div>

          <!-- Motivo -->
          <div class="mb-3">
            <label class="form-label">Motivo</label>
            <input type="text" name="MotivoCita" class="form-control" required>
          </div>
        </div>

        <div class="modal-footer">
          <button class="btn btn-success">Guardar</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
  // Pasar el ID del diagnóstico al modal cuando se abre
  var citaModal = document.getElementById('citaModal');
  citaModal.addEventListener('show.bs.modal', function (ev) {
    var btn = ev.relatedTarget;
    var idD = btn.getAttribute('data-idd');
    console.log("ID del diagnóstico:", idD);
    document.getElementById('modalIdD').value = idD;
  });
</script>