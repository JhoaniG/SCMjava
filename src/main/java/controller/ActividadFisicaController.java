package controller;

import Dao.ActividadFisicaDao;
import Dao.MascotaDao; // Necesario para listar mascotas por usuario
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import modelo.ActividadFisica;
import modelo.Mascota;
import modelo.Usuario;


@WebServlet("/ActividadFisicaController")
//Espacio apra subir Foto
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class ActividadFisicaController extends HttpServlet {
    ActividadFisicaDao dao = new ActividadFisicaDao();
    MascotaDao mascotaDao = new MascotaDao(); // Instancia de MascotaDao para listar mascotas por usuario

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        HttpSession session = request.getSession(false); // Usar getSession(false) para no crear sesión si no existe

        if (accion == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        switch (accion) {
            case "GenerarActividad":
                // Muestra el formulario con la lista de dueños (para que el veterinario seleccione uno)
                List<Usuario> listaDuenos = dao.listarDuenos(); // Asume que ActividadFisicaDao tiene este método
                request.setAttribute("listaDuenos", listaDuenos);
                request.getRequestDispatcher("veterinario/listarDuenosA.jsp").forward(request, response);
                break;

            case "SeleccionarDueno":
                // Muestra el formulario para crear una actividad, después de que el veterinario ha seleccionado un dueño
                int idDueno = Integer.parseInt(request.getParameter("idDueno"));
                // El dao.listarMascotasPorDueno(idDueno) debería estar en MascotaDao, no en ActividadFisicaDao
                // Si ActividadFisicaDao tiene este método, asegúrate de que esté correcto o cámbialo a mascotaDao
                List<Mascota> listaMascotas = dao.listarMascotasPorDueno(idDueno); // Si este método no es de ActividadFisicaDao, cambiar a mascotaDao.listarMascotasPorDueno
                
                Object idVetObj = session.getAttribute("idVeterinario");
                if (idVetObj == null) {
                    response.sendRedirect("login.jsp"); // Redirigir si no hay ID de veterinario en sesión
                    return;
                }

                int idVeterinario = (Integer) idVetObj;
                request.setAttribute("listaMascotas", listaMascotas);
                request.setAttribute("idVeterinario", idVeterinario);
                request.getRequestDispatcher("veterinario/crearActividadFisica.jsp").forward(request, response);
                break;

            // ***** ACCIONES PARA EL DUEÑO DE MASCOTA *****
            case "ConsultarMascotasActividad":
                // Esta acción es para que el DUEÑO vea sus propias mascotas antes de ver sus actividades
                Integer idUsuarioSesion = (Integer) session.getAttribute("idUsuario");
                if (idUsuarioSesion == null) {
                    response.sendRedirect("login.jsp"); // Redirigir si no hay sesión del usuario
                    return;
                }
                try {
                    // Utiliza el MascotaDao para listar mascotas por el ID del usuario logueado
                    // Esta llamada es la que trae la lista de mascotas para el dueño
                    List<Mascota> mascotasDueno = mascotaDao.ListarMascotaPorUsuario(idUsuarioSesion);
                    request.setAttribute("listaMascotas", mascotasDueno);
                    // Asegúrate de que este JSP esté preparado para mostrar las fotos de las mascotas
                    request.getRequestDispatcher("duenomascota/mascotasLisActividadFisica.jsp").forward(request, response);
                } catch (ClassNotFoundException e) {
                    System.out.println("Error al listar mascotas para actividad física: " + e.getMessage());
                    e.printStackTrace(); // Para depuración
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno al cargar mascotas para actividad.");
                }
                break;

            case "ConsultarActividadesMascota":
                // Consulta las actividades físicas de una mascota específica
                int idMascota = Integer.parseInt(request.getParameter("idM"));
                List<ActividadFisica> actividades = dao.obtenerActividadesPorIdMascota(idMascota);
                request.setAttribute("actividades", actividades); // Cambiado el nombre del atributo a "actividades" para claridad
                request.getRequestDispatcher("duenomascota/ActividadesPorMascota.jsp").forward(request, response);
                break;
            // ***** FIN ACCIONES DUEÑO *****

            default:
                response.sendRedirect("index.jsp"); // Acción por defecto si no se reconoce ninguna
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if ("RegistrarActividadFisica".equalsIgnoreCase(accion)) {
            try {
                int idM = Integer.parseInt(request.getParameter("idM"));
                int idV = Integer.parseInt(request.getParameter("idV"));
                String descripcion = request.getParameter("descripcion");
                String tipoActividad = request.getParameter("tipoActividad");
               String fotoFileName = null;
                try {
                    Part filePart = request.getPart("Foto");
                    if (filePart != null && filePart.getSize() > 0) {
                        fotoFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                        // --- Define el directorio base donde guardar las fotos (RUTA ORIGINAL DENTRO DE LA APLICACIÓN) ---
                        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads"; // RUTA ORIGINAL
                        // --- Fin de definición de directorio base ---

                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            boolean created = uploadDir.mkdirs();
                            if (created) {
                                System.out.println("Directorio de subidas para dietas creado: " + uploadPath);
                            } else {
                                System.err.println("ERROR: No se pudo crear el directorio de subidas para dietas: " + uploadPath);
                            }
                        }

                        Path filePath = Paths.get(uploadPath + File.separator + fotoFileName);
                        try (InputStream fileContent = filePart.getInputStream()) {
                            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Foto de dieta '" + fotoFileName + "' subida exitosamente a: " + filePath.toString());
                        }
                    } else {
                        System.out.println("No se seleccionó ninguna foto para la dieta o el archivo está vacío.");
                    }

                } catch (Exception e) {
                    System.out.println("Error al procesar la subida de la foto de la dieta: " + e.getMessage());
                    e.printStackTrace();
                    fotoFileName = null;
                }
                //Se setean Los campos con los de la base de datos
                ActividadFisica a = new ActividadFisica();
                a.setIdM(idM);
                a.setIdV(idV);
                a.setDescripcion(descripcion);
                a.setTipoActividad(tipoActividad);
                a.setFoto(fotoFileName != null ? "uploads/" + fotoFileName : null);//Ruta Orinial

                dao.insertarActividad(a); // Llama al DAO para insertar la actividad

            } catch (Exception e) {
                System.out.println("Error al registrar actividad física: " + e.getMessage());
                e.printStackTrace(); // Para depuración
                // Podrías reenviar a la página de registro con un mensaje de error
                request.setAttribute("errorRegistroActividad", "Hubo un error al registrar la actividad: " + e.getMessage());
                request.getRequestDispatcher("veterinario/crearActividadFisica.jsp").forward(request, response);
            }
        }
        
        // Redirige al final de cualquier acción POST para evitar reenvíos de formulario
        response.sendRedirect("ActividadFisicaController?accion=GenerarActividad");
    }

    @Override
    public String getServletInfo() {
        return "Controlador para registrar actividad física";
    }
}