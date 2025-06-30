package controller;

import Dao.DietaDao;
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
import java.io.PrintWriter; // Asegúrate de que esta importación esté presente
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import modelo.Dieta;
import modelo.Mascota;
import modelo.Usuario;

/**
 *
 * @author jhoan
 */
@WebServlet("/DietaController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class DietaController extends HttpServlet {

    private final DietaDao dao = new DietaDao();

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
            out.println("<title>Servlet DietaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DietaController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        switch (accion) {
            case "GenerarDieta":
                // Mostrar formulario con lista de dueños
                List<Usuario> listaDuenos = dao.listarDuenos();
                request.setAttribute("listaDuenos", listaDuenos);
                request.getRequestDispatcher("veterinario/seleccionarDueno.jsp").forward(request, response);
                break;

            case "SeleccionarDueno":
                // Mostrar formulario para crear dieta con mascotas del dueño
                int idDueno = Integer.parseInt(request.getParameter("idDueno"));
                List<Mascota> listaMascotas = dao.listarMascotasPorDueno(idDueno);
                int idVeterinario = (int) session.getAttribute("idVeterinario");

                request.setAttribute("listaMascotas", listaMascotas);
                request.setAttribute("idVeterinario", idVeterinario);
                request.getRequestDispatcher("veterinario/crearDieta.jsp").forward(request, response);
                break;
            case "ConsultarMascotas":
                Integer idD = (Integer) session.getAttribute("idUsuario");
                if (idD == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }
                List<Mascota> mascotasDueno = dao.listarMascotasPorDuenoDos(idD);
                request.setAttribute("listaMascotas", mascotasDueno);
                request.getRequestDispatcher("duenomascota/MascotasLis.jsp").forward(request, response);
                break;

            case "ConsultarDietasMascota":
                int idMascota = Integer.parseInt(request.getParameter("idM"));
                List<Dieta> dietas = dao.obtenerDietasPorIdMascota(idMascota);
                request.setAttribute("dietas", dietas);
                request.getRequestDispatcher("duenomascota/dietasPorMascota.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("RegistrarDieta".equalsIgnoreCase(accion)) {

            try {
                int idM = Integer.parseInt(request.getParameter("idM"));
                int idV = Integer.parseInt(request.getParameter("idV"));
                String descripcion = request.getParameter("descripcion");
                String tipoDieta = request.getParameter("tipoDieta");
                
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

                Dieta d = new Dieta();
                d.setIdM(idM);
                d.setIdV(idV);
                d.setDescripcion(descripcion);
                d.setTipoDieta(tipoDieta);
                // Guarda la ruta completa relativa a la aplicación, incluyendo "uploads/"
                d.setFoto(fotoFileName != null ? "uploads/" + fotoFileName : null); // RUTA ORIGINAL

                dao.insertarDieta(d);

            } catch (Exception e) {
                System.out.println("Error al registrar dieta: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("errorRegistroDieta", "Hubo un error al registrar la dieta: " + e.getMessage());
                request.getRequestDispatcher("veterinario/crearDieta.jsp").forward(request, response);
            }
        }

        response.sendRedirect("DietaController?accion=GenerarDieta");
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