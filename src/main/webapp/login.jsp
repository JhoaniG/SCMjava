<%-- 
    Document   : login
    Created on : 20/06/2025, 11:19:36 a. m.
    Author     : jhoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylos.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Registro dueño mascota</title>
    </head>
    <body>
        <!--MENU-->
        <!--MENU-->
        <header class="header">
            <div class="container-m">
                <div class="btn-menu">
                    <label for="btn-menu">☰</label>
                </div>
                <div class="logo">
                    <h1>SCM</h1>
                </div>
                <nav class="menu">
                    <a href="../index.html">Inicio</a>
                    <a href="../index.html">Profecionales</a>
                    <a href="../index.html">Mascotas</a>
                    <a href="../index.html">Contactanos </a>

                </nav>
            </div>
        </header>
        <!--FIN MENU-->


        <!--Formulario-->
        <div class="container login-container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4">
                    <div class="login-form">
                        <div class="form-title mt-5">Iniciar Sesión</div>
                        <form action="ValidarUsuario" method="POST" >
                            <!-- Correo electrónico -->
                            <div class="mb-3 form-group">
                                <i class="fas fa-envelope input-icon"></i>
                                <label for="correo" class="form-label">Correo Electrónico</label>
                                <input type="email" class="form-control"  name="Correo"
                                       pattern="[a-zA-Z0-9._%+-]+@gmail\.com"
                                       title="Debe ser un correo de Gmail válido, como usuario@gmail.com"
                                       required>
                                <div class="invalid-feedback">Por favor ingresa un correo de Gmail válido.</div>
                            </div>


                            <!-- Contraseña -->
                            <div class="mb-3 form-group">
                                <i class="fas fa-lock input-icon"></i>
                                <label for="contraseña" class="form-label">Contraseña</label>
                                <input type="password" class="form-control"  name="Contrasena"
                                       title="La contraseña es obligatoria" required>
                                <div class="invalid-feedback">Por favor ingresa tu contraseña.</div>
                            </div>
                            <input type="hidden" name="accion" value="ingresar">
                            <!-- Botones de inicio de sesión -->
                            <div class="mt-3 text-center">
                                <button type="submit" class="btn btn-outline-info">Iniciar Sesión</button>
                            </div>



                            <!-- Recuperación de contraseña -->
                            <div class="mt-3 text-center">
                                <a href="#">¿Olvidaste tu contraseña?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

       


        <!--FIN Formulario-->









        <!--footer-->
        <footer class="footer mt-5">
            <div class="container py-4">
                <div class="row text-center text-md-start">
                    <!-- Persona 1 -->
                    <div class="col-md-4 mb-3">
                        <h5 class="footer-title">John Alvarado</h5>
                        <ul class="list-unstyled">
                            <li>
                                <i class="fas fa-envelope"></i> 
                                <a href="mailto:jhonyse2345@gmail.com" class="footer-link">jhonyse2345@gmail.com</a>
                            </li>
                            <li>
                                <i class="fas fa-phone"></i> 
                                <span class="footer-text">321-613-2975</span>
                            </li>
                        </ul>
                    </div>

                    <!-- Persona 2 -->
                    <div class="col-md-4 mb-3">
                        <h5 class="footer-title">Leonardo Atencio</h5>
                        <ul class="list-unstyled">
                            <li>
                                <i class="fas fa-envelope"></i> 
                                <a href="mailto:leonardoatenciob30@gmail.com" class="footer-link">leonardoatenciob30@gmail.com</a>
                            </li>
                            <li>
                                <i class="fas fa-phone"></i> 
                                <span class="footer-text">321-960-9864</span>
                            </li>
                        </ul>
                    </div>

                    <!-- Persona 3 -->
                    <div class="col-md-4 mb-3">
                        <h5 class="footer-title">Jhoani Gallo</h5>
                        <ul class="list-unstyled">
                            <li>
                                <i class="fas fa-envelope"></i> 
                                <a href="mailto:jhoanigallo@gmail.com" class="footer-link">jhoanigallo@gmail.com</a>
                            </li>
                            <li>
                                <i class="fas fa-phone"></i> 
                                <span class="footer-text">312-311-0320</span>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="text-center mt-4">
                    <p class="footer-copy">&copy; 2024. Todos los derechos reservados.</p>
                </div>
            </div>
        </footer>
        <!--footer-->

    </div>

    <input type="checkbox" id="btn-menu">
    <div class="container-menu">
        <div class="cont-menu">
            <nav>
                <a href="Formularios/formulario_inicio_secion.html">Iniciar secion</a>
                <a href="#">Servicios</a>
                <a href="#">Suscribirse</a>
                <a href="#">Facebook</a>
                <a href="#">Youtube</a>
                <a href="#">Instagram</a>
            </nav>
            <label for="btn-menu">✖️</label>
        </div>
    </div>
    <!--FIN MENU-->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
