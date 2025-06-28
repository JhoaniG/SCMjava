/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Dao.CitaDao;
import Dao.DiagnosticoDuenoDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import modelo.Cita;
import modelo.DiagnosticoDueno;
import modelo.Usuario;

/**
 *
 * @author jhoan
 */
@WebServlet("/CitaController")
public class CitaController extends HttpServlet {

    private final CitaDao dao = new CitaDao();
    private final DiagnosticoDuenoDao diagDao = new DiagnosticoDuenoDao();
    Usuario usu=new Usuario();

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
            out.println("<title>Servlet CitaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CitaController at " + request.getContextPath() + "</h1>");
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

    if ("ListarCitas".equalsIgnoreCase(accion)) {
        int idRol = (int) session.getAttribute("idRol");

        switch (idRol) {
            case 2: // DUEÑO DE MASCOTA
                int idU = (int) session.getAttribute("idUsuario");
                List<Cita> listaDueno = dao.listarCitasPorDueno(idU);
                request.setAttribute("listaCitas", listaDueno);
                request.getRequestDispatcher("duenomascota/listaCitas.jsp").forward(request, response);
                return;

            case 3: // VETERINARIO
                int idV = (int) session.getAttribute("idVeterinario");
                List<Cita> listaVet = dao.listarCitasPorVeterinarioo(idV);
                request.setAttribute("listaCitas", listaVet);
                request.getRequestDispatcher("veterinario/listaCitas.jsp").forward(request, response);
                return;

            default:
                response.sendRedirect("index.jsp");
                return;
        }

    } else if ("ListarPorDiag".equalsIgnoreCase(accion)) {
        int idD = Integer.parseInt(request.getParameter("IdD"));
        request.setAttribute("listaCitas", dao.listarCitasPorDiagnostico(idD));
        request.getRequestDispatcher("veterinario/listaCitas.jsp").forward(request, response);
        return;
    } else {
        response.sendRedirect("index.jsp");
    }

    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String accion = request.getParameter("accion");
    if ("RegistrarCita".equals(accion)) {
        try {
            int idD = Integer.parseInt(request.getParameter("IdD"));
            Date fecha = Date.valueOf(request.getParameter("FechaCita"));
            String motivo = request.getParameter("MotivoCita");

            // Buscar el diagnóstico para obtener IdM y IdV
            DiagnosticoDueno diagnostico = diagDao.obtenerPorId(idD);
            int idM = diagnostico.getIdM();
            int idV = diagnostico.getIdV();

            Cita c = new Cita();
            c.setIdM(idM);
            c.setIdV(idV);
            c.setFechaCita(fecha);
            c.setMotivoCita(motivo);
            c.setEstadoCita("Pendiente");
            c.setIdD(idD);

            dao.insertarCita(c);
        } catch (Exception e) {
            System.out.println("Error al insertar cita: " + e.getMessage());
        }
    }

    response.sendRedirect("DiagnosticoController?accion=Listar");
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
