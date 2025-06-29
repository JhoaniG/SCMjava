/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Dao.DietaDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import modelo.Dieta;
import modelo.Mascota;
import modelo.Usuario;

/**
 *
 * @author jhoan
 */
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
                 // o por parámetro
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

                Dieta d = new Dieta();
                d.setIdM(idM);
                d.setIdV(idV);
                d.setDescripcion(descripcion);
                d.setTipoDieta(tipoDieta);

                dao.insertarDieta(d);

            } catch (Exception e) {
                System.out.println("Error al registrar dieta: " + e.getMessage());
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
