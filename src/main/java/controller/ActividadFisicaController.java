package controller;

import Dao.ActividadFisicaDao;
import Dao.MascotaDao; // Necesario para listar mascotas por usuario
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
    MascotaDao mascotaDao = new MascotaDao(); // Instancia de MascotaDao para listar mascotas por usuario

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

            // ***** ACCIONES PARA EL DUEÑO DE MASCOTA *****
            case "ConsultarMascotasActividad":
                Integer idUsuarioSesion = (Integer) session.getAttribute("idUsuario");
                if (idUsuarioSesion == null) {
                    response.sendRedirect("login.jsp"); // Redirigir si no hay sesión
                    return;
                }
                try {
                    // Utiliza el MascotaDao para listar mascotas por el ID del usuario
                    List<Mascota> mascotasDueno = mascotaDao.ListarMascotaPorUsuario(idUsuarioSesion);
                    request.setAttribute("listaMascotas", mascotasDueno);
                    request.getRequestDispatcher("duenomascota/mascotasLisActividadFisica.jsp").forward(request, response);
                } catch (ClassNotFoundException e) {
                    System.out.println("Error al listar mascotas para actividad física: " + e.getMessage());
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno al cargar mascotas.");
                }
                break;

            case "ConsultarActividadesMascota":
                int idMascota = Integer.parseInt(request.getParameter("idM"));
                List<ActividadFisica> actividades = dao.obtenerActividadesPorIdMascota(idMascota);
                request.setAttribute("actividades", actividades); // Cambiado a "actividades"
                request.getRequestDispatcher("duenomascota/ActividadesPorMascota.jsp").forward(request, response);
                break;
            // ***** FIN ACCIONES DUEÑO *****

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