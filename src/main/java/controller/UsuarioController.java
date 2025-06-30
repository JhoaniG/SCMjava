package controller;

import Dao.UsuarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Usuario;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List; // Importado por si se usa en doGet, aunque no en el código proporcionado

@WebServlet("/UsuarioController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UsuarioController extends HttpServlet {

    UsuarioDao dao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        switch (accion) {
            case "Agregar":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            case "Editar":
                try {
                    int IdU = Integer.parseInt(request.getParameter("IdU"));
                    Usuario u = dao.ObtenerUsuarioPorId(IdU);
                    request.setAttribute("usuario", u);
                    request.getRequestDispatcher("editarU.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("Error al buscar usuario por ID: " + e.getMessage());
                    response.sendRedirect("error.jsp");
                }
                break;
            case "Eliminar":
                try {
                    int IdU = Integer.parseInt(request.getParameter("IdU"));
                    dao.EliminarUsuario(IdU);
                    response.sendRedirect("UsuarioController?accion=Listar");
                } catch (Exception e) {
                    System.out.println("Error al eliminar usuario: " + e.getMessage());
                    response.sendRedirect("error.jsp");
                }
                break;
            case "Listar":
                // Aquí podrías implementar el listado si tienes una vista preparada
                // List<Usuario> listaUsuarios = dao.listarUsuarios();
                // request.setAttribute("listaUsuarios", listaUsuarios);
                // request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion.equals("Registrar")) {
            String Nombre = request.getParameter("Nombre");
            String Apellido = request.getParameter("Apellido");
            String Correo = request.getParameter("Correo");
            String Contrasena = request.getParameter("Contrasena");
            String Telefono = request.getParameter("Telefono");
            String Direccion = request.getParameter("Direccion");
            int IdR = Integer.parseInt(request.getParameter("IdR"));

            // --- Lógica para la subida de fotos (VUELVE A LA RUTA ORIGINAL DENTRO DE LA APLICACIÓN) ---
            String fotoFileName = null;
            try {
                Part filePart = request.getPart("Foto");

                if (filePart != null && filePart.getSize() > 0) {
                    fotoFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                    // --- Define el directorio base donde guardar las fotos (RUTA DENTRO DE LA APLICACIÓN) ---
                    String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads"; // RUTA ORIGINAL
                    // --- Fin de definición de directorio base ---

                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        boolean created = uploadDir.mkdirs(); // Crea el directorio y padres si no existen
                        if (created) {
                            System.out.println("Directorio de subidas para usuarios creado: " + uploadPath);
                        } else {
                            System.err.println("ERROR: No se pudo crear el directorio de subidas para usuarios: " + uploadPath);
                        }
                    }

                    Path filePath = Paths.get(uploadPath + File.separator + fotoFileName);
                    try (InputStream fileContent = filePart.getInputStream()) {
                        Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Foto de usuario '" + fotoFileName + "' subida exitosamente a: " + filePath.toString());
                    }
                } else {
                    System.out.println("No se seleccionó ninguna foto para el usuario o el archivo está vacío.");
                }
            } catch (Exception e) {
                System.out.println("Error al procesar la subida de la foto de usuario: " + e.getMessage());
                e.printStackTrace();
                fotoFileName = null; // En caso de error, la foto se establece a null
            }
            // --- Fin lógica para la subida de fotos ---

            Usuario usuario = new Usuario();
            usuario.setNombre(Nombre);
            usuario.setApellido(Apellido);
            usuario.setCorreo(Correo);
            usuario.setContrasena(Contrasena);
            usuario.setTelefono(Telefono);
            usuario.setDireccion(Direccion);
            usuario.setIdR(IdR);
            // Guarda la ruta completa relativa a la aplicación, incluyendo "uploads/"
            usuario.setFoto(fotoFileName != null ? "uploads/" + fotoFileName : null); // VUELVE A LA RUTA ORIGINAL
            
            try {
                dao.InsertarUsuario(usuario);
                if (IdR == 3) { // Si es veterinario (IdR == 3)
                    String especialidad = request.getParameter("Especialidad");
                    int nuevoIdU = dao.obtenerUltimoIdInsertado();
                    dao.insertarVeterinario(nuevoIdU, especialidad);
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al registrar usuario: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("errorRegistro", "Hubo un error al registrar el usuario.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else if (accion.equals("Actualizar")) {
            try {
                int IdU = Integer.parseInt(request.getParameter("IdU"));
                Usuario usuario = new Usuario();
                usuario.setIdU(IdU);
                usuario.setNombre(request.getParameter("Nombre"));
                usuario.setApellido(request.getParameter("Apellido"));
                usuario.setCorreo(request.getParameter("Correo"));
                usuario.setContrasena(request.getParameter("Contrasena"));
                usuario.setTelefono(request.getParameter("Telefono"));
                usuario.setDireccion(request.getParameter("Direccion"));

                // --- Lógica para actualizar la foto (VUELVE A LA RUTA ORIGINAL DENTRO DE LA APLICACIÓN) ---
                String fotoFileName = null;
                try {
                    Part filePart = request.getPart("Foto");
                    if (filePart != null && filePart.getSize() > 0) {
                        fotoFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads"; // RUTA ORIGINAL
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }
                        Path filePath = Paths.get(uploadPath + File.separator + fotoFileName);
                        try (InputStream fileContent = filePart.getInputStream()) {
                            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                        }
                        usuario.setFoto("uploads/" + fotoFileName); // Guarda la nueva ruta completa
                        System.out.println("Foto de usuario actualizada a: " + fotoFileName);
                    } else {
                        // Si no se sube una nueva foto, se debe conservar la existente.
                        // Para ello, primero se debe obtener el usuario de la BD para copiar su foto actual.
                        Usuario existingUser = dao.ObtenerUsuarioPorId(IdU);
                        if (existingUser != null) {
                            usuario.setFoto(existingUser.getFoto()); // Conserva la foto existente
                        } else {
                            usuario.setFoto(null); // O establece una por defecto si el usuario no existe o no tenía foto
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error al actualizar la foto del usuario: " + e.getMessage());
                    e.printStackTrace();
                    // En caso de error de subida, intenta conservar la foto existente
                    Usuario existingUser = dao.ObtenerUsuarioPorId(IdU);
                    if (existingUser != null) {
                        usuario.setFoto(existingUser.getFoto());
                    } else {
                        usuario.setFoto(null);
                    }
                }
                // --- Fin lógica para actualizar la foto ---

                dao.actualizarUsuario(usuario);
                response.sendRedirect("duenomascota/dueno.jsp");

            } catch (Exception e) {
                System.out.println("Error al actualizar usuario: " + e.getMessage());
                e.printStackTrace();
                response.sendRedirect("editarU.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestión de usuarios";
    }
}