<%-- 
    Document   : registerDiagnostico
    Created on : 24/06/2025, 8:34:24?p. m.
    Author     : jhoan
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="diagnosticoModal" tabindex="-1" aria-labelledby="diagnosticoModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content shadow-lg border-0 rounded-4">
      <form action="DiagnosticoController" method="POST">
        <div class="modal-header bg-primary text-white rounded-top-4">
          <h5 class="modal-title fw-bold" id="diagnosticoModalLabel">
            <i class="fas fa-notes-medical me-2"></i>Registrar Diagnóstico
          </h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
        </div>

        <div class="modal-body bg-light rounded-bottom-4">
          <!-- Acción -->
          <input type="hidden" name="accion" value="Registrar">

          <!-- Mascota -->
          <div class="mb-3">
            <label for="IdM" class="form-label fw-semibold"><i class="fas fa-paw me-1"></i> Mascota</label>
            <select name="IdM" class="form-select rounded-3 shadow-sm" required>
              <option value="" disabled selected>Seleccione una mascota</option>
              <c:forEach var="m" items="${listaMascotas}">
                <option value="${m.idM}">${m.nombre}</option>
              </c:forEach>
            </select>
          </div>

          <!-- Veterinario -->
          <div class="mb-3">
            <label for="IdV" class="form-label fw-semibold"><i class="fas fa-user-md me-1"></i> Veterinario</label>
            <select name="IdV" class="form-select rounded-3 shadow-sm" required>
              <option value="" disabled selected>Seleccione un veterinario</option>
              <c:forEach var="v" items="${listaVeterinarios}">
                <option value="${v.idU}">${v.nombre}</option>
              </c:forEach>
            </select>
          </div>

          <!-- Fecha del diagnóstico -->
          <div class="mb-3">
            <label for="FechaDiagnostico" class="form-label fw-semibold"><i class="fas fa-calendar-alt me-1"></i> Fecha del Diagnóstico</label>
            <input type="date" class="form-control rounded-3 shadow-sm" name="FechaDiagnostico" required>
          </div>

          <!-- Observaciones -->
          <div class="mb-3">
            <label for="Observaciones" class="form-label fw-semibold"><i class="fas fa-stethoscope me-1"></i> Observaciones</label>
            <textarea name="Observaciones" class="form-control rounded-3 shadow-sm" rows="3" placeholder="Describa síntomas, diagnóstico, recomendaciones..." required></textarea>
          </div>
        </div>

        <div class="modal-footer bg-white rounded-bottom-4">
          <button type="submit" class="btn btn-success px-4 rounded-pill shadow-sm">
            <i class="fas fa-save me-1"></i> Guardar
          </button>
          <button type="button" class="btn btn-secondary px-4 rounded-pill shadow-sm" data-bs-dismiss="modal">
            <i class="fas fa-times me-1"></i> Cancelar
          </button>
        </div>
      </form>
    </div>
  </div>
</div>
