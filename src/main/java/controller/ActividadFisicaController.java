package controller;

import Dao.ActividadFisicaDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import modelo.ActividadFisica;
import modelo.Mascota;
import modelo.Usuario;

public class ActividadFisicaController extends HttpServlet {
    ActividadFisicaDao dao = new ActividadFisicaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        HttpSession session = request.getSession(false);

        if (accion == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        switch (accion) {
            case "GenerarActividad":
                List<Usuario> listaDuenos = dao.listarDuenos();
                request.setAttribute("listaDuenos", listaDuenos);
                request.getRequestDispatcher("veterinario/listarDuenosA.jsp").forward(request, response);
                break;

            case "SeleccionarDueno":
                int idDueno = Integer.parseInt(request.getParameter("idDueno"));
                List<Mascota> listaMascotas = dao.listarMascotasPorDueno(idDueno);

                Object idVetObj = session.getAttribute("idVeterinario");
                if (idVetObj == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                int idVeterinario = (Integer) idVetObj;

                request.setAttribute("listaMascotas", listaMascotas);
                request.setAttribute("idVeterinario", idVeterinario);
                request.getRequestDispatcher("veterinario/crearActividadFisica.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
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

                ActividadFisica a = new ActividadFisica();
                a.setIdM(idM);
                a.setIdV(idV);
                a.setDescripcion(descripcion);
                a.setTipoActividad(tipoActividad);

                dao.insertarActividad(a);

            } catch (Exception e) {
                System.out.println("Error al registrar actividad física: " + e.getMessage());
            }
        }

        response.sendRedirect("ActividadFisicaController?accion=GenerarActividad");
    }

    @Override
    public String getServletInfo() {
        return "Controlador para registrar actividad física";
    }
}
