<%-- 
    Document   : registerUsuarioModal
    Created on : 24/06/2025, 6:47:59?p. m.
    Author     : jhoan
--%>

<div class="modal fade" id="modalRegistro" tabindex="-1" aria-labelledby="modalRegistroLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content rounded-3 shadow">

      <!-- Encabezado -->
      <div class="modal-header bg-info text-white">
        <h5 class="modal-title" id="modalRegistroLabel">Registro de Usuario SCM</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>

      <!-- Formulario -->
      <form action="UsuarioController" method="POST" class="needs-validation" novalidate>
        <div class="modal-body">

          <input type="hidden" name="accion" value="Registrar">

          <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" name="Nombre" required>
          </div>

          <div class="mb-3">
            <label for="apellido" class="form-label">Apellido</label>
            <input type="text" class="form-control" name="Apellido" required>
          </div>

          <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" name="Correo" required
                   pattern="[a-zA-Z0-9._%+-]+@gmail\.com"
                   title="Debe ser un correo Gmail válido">
          </div>

          <div class="mb-3">
            <label for="contrasena" class="form-label">Contraseña</label>
            <input type="password" class="form-control" name="Contrasena" required>
          </div>

          <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono</label>
            <input type="tel" class="form-control" name="Telefono" required>
          </div>

          <div class="mb-3">
            <label for="direccion" class="form-label">Dirección</label>
            <input type="text" class="form-control" name="Direccion" required>
          </div>

          <div class="mb-3">
            <label for="IdR" class="form-label">Rol</label>
            <select class="form-select" name="IdR" id="rol" onchange="mostrarEspecialidad()" required>
              <option value="">Selecciona un rol</option>
              <option value="2">Dueño de mascota</option>
              <option value="3">Veterinario</option>
            </select>
          </div>

          <div class="mb-3" id="campoEspecialidad" style="display: none;">
            <label for="Especialidad" class="form-label">Especialidad</label>
            <input type="text" class="form-control" name="Especialidad">
          </div>

        </div>

        <!-- Pie del modal -->
        <div class="modal-footer">
          <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancelar</button>
          <button type="submit" class="btn btn-info">Registrar</button>
        </div>
      </form>

    </div>
  </div>
</div>

<!-- Script para mostrar campo de especialidad -->
<script>
function mostrarEspecialidad() {
  let rol = document.getElementById("rol").value;
  let campo = document.getElementById("campoEspecialidad");
  campo.style.display = rol === "3" ? "block" : "none";
}
</script>