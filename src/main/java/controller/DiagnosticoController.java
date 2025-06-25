package controller;

import Dao.DiagnosticoDuenoDao;
import Dao.MascotaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import modelo.DiagnosticoDueno;
import modelo.Mascota;

@WebServlet("/DiagnosticoController")
public class DiagnosticoController extends HttpServlet {

    DiagnosticoDuenoDao diagDao = new DiagnosticoDuenoDao();
    MascotaDao mascotaDao = new MascotaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        int IdU = (int) sesion.getAttribute("idUsuario");

        try {
            List<Mascota> listaMascotas = mascotaDao.ListarMascotaPorUsuario(IdU);
            request.setAttribute("listaMascotas", listaMascotas);
            request.getRequestDispatcher("duenomascota/dueno.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error cargando mascotas: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("Registrar")) {
            try {
                int idMascota = Integer.parseInt(request.getParameter("IdM"));
                Date fechaDiagnostico = Date.valueOf(request.getParameter("FechaDiagnostico"));
                String observaciones = request.getParameter("Observaciones");

                // ✅ Obtenemos el Id del veterinario desde la sesión
                HttpSession sesion = request.getSession();
                int idVeterinario = (int) sesion.getAttribute("idVeterinario");

                DiagnosticoDueno d = new DiagnosticoDueno();
                d.setIdM(idMascota);
                d.setIdV(idVeterinario);
                d.setFechaDiagnostico(fechaDiagnostico);
                d.setObservaciones(observaciones);

                diagDao.InsertarDiagnostico(d);

                response.sendRedirect("duenomascota/dueno.jsp");

            } catch (Exception e) {
                System.out.println("Error al registrar diagnóstico: " + e.getMessage());
                response.sendRedirect("duenomascota/dueno.jsp");
            }
        } else {
            response.sendRedirect("duenomascota/dueno.jsp");
        }
    }
}