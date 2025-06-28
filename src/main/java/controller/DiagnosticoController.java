package controller;

import Dao.DiagnosticoDuenoDao;
import Dao.MascotaDao;
import Dao.UsuarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DiagnosticoDueno;
import modelo.Mascota;
import modelo.Usuario;

@WebServlet("/DiagnosticoController")
public class DiagnosticoController extends HttpServlet {

    private final DiagnosticoDuenoDao diagDao = new DiagnosticoDuenoDao();
    private final MascotaDao mascotaDao = new MascotaDao();
    private final UsuarioDao usuarioDao = new UsuarioDao(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();

        switch (accion) {
            case "Listar": {
                // Veterinario: listar sus diagnósticos
                Usuario u = (Usuario) sesion.getAttribute("usuarioLogueado");
                if (u == null || u.getIdR() != 3) { 
                    response.sendRedirect("login.jsp");
                    return;
                }
                int idVet = (int) sesion.getAttribute("idVeterinario");
                List<DiagnosticoDueno> lista = diagDao.listarDiagnosticosPorVeterinario(idVet);
                request.setAttribute("listaDiagnosticos", lista);
                request.getRequestDispatcher("veterinario/listaDiagnosticos.jsp")
                       .forward(request, response);
                break;
            }
            case "RegistrarModal": {
                // Dueño: preparar modal con mascotas y veterinarios
                int idU = (int) sesion.getAttribute("idUsuario");
                List<Mascota> listaMascotas = null;
                try {
                    listaMascotas = mascotaDao.ListarMascotaPorUsuario(idU);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DiagnosticoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Usuario> listaVets = usuarioDao.listarVeterinarios();
                request.setAttribute("listaMascotas", listaMascotas);
                request.setAttribute("listaVeterinarios", listaVets);
                request.getRequestDispatcher("duenomascota/dueno.jsp")
                       .forward(request, response);
                break;
            }

            default:
                response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Solo para registrar diagnóstico
        String accion = request.getParameter("accion");
        if ("Registrar".equals(accion)) {
            try {
                int idM = Integer.parseInt(request.getParameter("IdM"));
                int idV = Integer.parseInt(request.getParameter("IdV"));
                Date fecha = Date.valueOf(request.getParameter("FechaDiagnostico"));
                String obs = request.getParameter("Observaciones");

                DiagnosticoDueno d = new DiagnosticoDueno();
                d.setIdM(idM);
                d.setIdV(idV);
                d.setFechaDiagnostico(fecha);
                d.setObservaciones(obs);
                diagDao.InsertarDiagnostico(d);

            } catch (Exception e) {
                System.out.println("Error al registrar diagnóstico: " + e.getMessage());
            }
        }
        // Después regresar al modal listo para más registros
        response.sendRedirect("DiagnosticoController?accion=RegistrarModal");
    }
}
