<%-- 
    Document   : registerDiagnostico
    Created on : 24/06/2025, 8:34:24?p. m.
    Author     : jhoan
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="diagnosticoModal" tabindex="-1" aria-labelledby="diagnosticoModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <form action="DiagnosticoController" method="post">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="diagnosticoModalLabel">Registrar Diagnóstico</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
        </div>
        <div class="modal-body">
          
          <!-- Acción del formulario -->
          <input type="hidden" name="accion" value="Registrar">

          <!-- Mascotas del dueño -->
          <div class="mb-3">
            <label for="IdM" class="form-label">Mascota</label>
            <select name="IdM" class="form-select" required>
  <option value="" disabled selected>Seleccione una mascota</option>
  <c:forEach var="m" items="${listaMascotas}">
    <option value="${m.idM}">${m.nombre}</option>
  </c:forEach>
</select>
          </div>

          <!-- Fecha del diagnóstico -->
          <div class="mb-3">
            <label for="FechaDiagnostico" class="form-label">Fecha del Diagnóstico</label>
            <input type="date" class="form-control" name="FechaDiagnostico" required>
          </div>

          <!-- Observaciones -->
          <div class="mb-3">
            <label for="Observaciones" class="form-label">Observaciones</label>
            <textarea name="Observaciones" class="form-control" rows="3" required></textarea>
          </div>

          <!-- ID del veterinario (oculto) -->
          <input type="hidden" name="IdV" value="${sessionScope.idVeterinario}" />

        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-success">Guardar</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>