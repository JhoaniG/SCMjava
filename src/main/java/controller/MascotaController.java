package controller;

import Dao.MascotaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
// Se removieron las importaciones de logging si no se usan
// import java.util.logging.Level;
// import java.util.logging.Logger;
import modelo.Mascota;
import modelo.Usuario;

/**
 *
 * @author jhoan
 */
@WebServlet("/MascotaController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class MascotaController extends HttpServlet {

    MascotaDao dao = new MascotaDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MascotaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MascotaController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Listar":
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

                try {
                    if (usuario != null) {
                        List<Mascota> mascotas = dao.ListarMascotaPorUsuario(usuario.getIdU());
                        request.setAttribute("mascotas", mascotas);
                        request.getRequestDispatcher("duenomascota/mismascotas.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("login.jsp");
                    }

                } catch (Exception e) {
                    System.out.println("Error al listar Mascotas: " + e.getMessage());
                    e.printStackTrace();
                    response.sendRedirect("duenomascota/dueno.jsp");
                }
                break;
            case "Agregar":
                request.getRequestDispatcher("duenomascota/registerM.jsp").forward(request, response);
                break;
            case "Editar":
                int IdM = Integer.parseInt(request.getParameter("IdM"));
                try {
                    Mascota m = dao.obtenerMascotaPoriD(IdM);
                    request.setAttribute("mascota", m);
                    request.getRequestDispatcher("duenomascota/editarM.jsp").forward(request, response);

                } catch (Exception e) {
                    System.out.println("Error al editar mascota: " + e.getMessage());
                    e.printStackTrace();
                    response.sendRedirect("MascotaController?accion=Listar");
                }
                break;
            case "Eliminar":
                int id = Integer.parseInt(request.getParameter("IdM"));
                try {
                    dao.eliminarMascota(id);
                    response.sendRedirect("MascotaController?accion=Listar");

                } catch (Exception e) {
                    System.out.println("Error al eliminar mascota: " + e.getMessage());
                    e.printStackTrace();
                    response.sendRedirect("MascotaController?accion=Listar");
                }
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String accion = request.getParameter("accion");
            if (accion.equals("Registrar")) {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

                if (usuario == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                String Nombre = request.getParameter("Nombre");
                String Genero = request.getParameter("Genero");
                Date FechaNacimineto = Date.valueOf(request.getParameter("FechaNacimineto"));
                String Raza = request.getParameter("Raza");
                
                // --- Lógica para la subida de fotos (RUTA ORIGINAL DENTRO DE LA APLICACIÓN) ---
                String fotoFileName = null;
                try {
                    Part filePart = request.getPart("Foto");

                    if (filePart != null && filePart.getSize() > 0) {
                        fotoFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads"; // RUTA ORIGINAL
                        
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            boolean created = uploadDir.mkdirs();
                            if (created) {
                                System.out.println("Directorio de subidas para mascotas creado: " + uploadPath);
                            } else {
                                System.err.println("ERROR: No se pudo crear el directorio de subidas para mascotas: " + uploadPath);
                            }
                        }

                        Path filePath = Paths.get(uploadPath + File.separator + fotoFileName);
                        try (InputStream fileContent = filePart.getInputStream()) {
                            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                         
                            System.out.println("Foto de mascota '" + fotoFileName + "' subida exitosamente a: " + filePath.toString());
                        }
                    } else {
                        System.out.println("No se seleccionó ninguna foto para la mascota o el archivo está vacío.");
                    }

                } catch (Exception e) {
                    System.out.println("Error al procesar la subida de la foto de la mascota: " + e.getMessage());
                    e.printStackTrace();
                    fotoFileName = null;
                }
                // --- Fin lógica para la subida de fotos ---

                Mascota mascota = new Mascota();
                mascota.setIdU(usuario.getIdU());
                mascota.setNombre(Nombre);
                mascota.setGenero(Genero);
                mascota.setFechaNacimineto(FechaNacimineto);
                mascota.setRaza(Raza);
                // Guarda la ruta completa relativa a la aplicación, incluyendo "uploads/"
                mascota.setFoto(fotoFileName != null ? "uploads/" + fotoFileName : null); // RUTA ORIGINAL
                
                try {
                    dao.insertarMascota(mascota);
                    response.sendRedirect("MascotaController?accion=Listar");

                } catch (Exception e) {
                    System.out.println("Error al registrar mascota: " + e.getMessage());
                    e.printStackTrace();
                    request.setAttribute("errorRegistroMascota", "Hubo un error al registrar la mascota: " + e.getMessage());
                    request.getRequestDispatcher("duenomascota/registerM.jsp").forward(request, response);
                }

            } else if (accion.equals("Actualizar")) {
                int IdM = Integer.parseInt(request.getParameter("IdM"));
                String Nombre = request.getParameter("Nombre");
                String Genero = request.getParameter("Genero");
                Date FechaNacimineto = Date.valueOf(request.getParameter("FechaNacimineto"));
                String Raza = request.getParameter("Raza");
                
                Mascota mascota = new Mascota();
                mascota.setIdM(IdM);
                mascota.setNombre(Nombre);
                mascota.setGenero(Genero);
                mascota.setFechaNacimineto(FechaNacimineto);
                mascota.setRaza(Raza);
                
                // --- Lógica para actualizar la foto de la Mascota (VUELVE A LA RUTA ORIGINAL DENTRO DE LA APLICACIÓN) ---
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
                        mascota.setFoto("uploads/" + fotoFileName); // Guarda la nueva ruta completa
                        System.out.println("Foto de mascota actualizada a: " + fotoFileName);
                    } else {
                        // Antes de este bloque: Mascota existingMascota = null; (ya no se usa aquí)
                        try {
                            Mascota existingMascota = dao.obtenerMascotaPoriD(IdM); // Este método puede lanzar ClassNotFoundException
                            if (existingMascota != null) {
                                mascota.setFoto(existingMascota.getFoto());
                            } else {
                                mascota.setFoto(null);
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MascotaController.class.getName()).log(Level.SEVERE, null, ex);
                            mascota.setFoto(null); // En caso de error, no asignar foto
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error al actualizar la foto de la mascota: " + e.getMessage());
                    e.printStackTrace();
                    // En caso de error de subida, intenta conservar la foto existente
                    try {
                        Mascota existingMascota = dao.obtenerMascotaPoriD(IdM);
                        if (existingMascota != null) {
                            mascota.setFoto(existingMascota.getFoto());
                        } else {
                            mascota.setFoto(null);
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MascotaController.class.getName()).log(Level.SEVERE, null, ex);
                        mascota.setFoto(null); // En caso de error, no asignar foto
                    }
                }
                // --- Fin lógica para actualizar la foto ---
                
                try {
                    dao.actulizarMascota(mascota);
                    response.sendRedirect("MascotaController?accion=Listar");
                } catch (Exception e) {
                    System.out.println("Error al actualizar mascota: " + e.getMessage());
                    e.printStackTrace();
                    response.sendRedirect("MascotaController?accion=Editar&IdM=" + IdM);
                }
            }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>

    }