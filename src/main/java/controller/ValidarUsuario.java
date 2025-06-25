package controller;

import Dao.MascotaDao;
import Dao.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Mascota;
import modelo.Usuario;

@WebServlet("/ValidarUsuario")
public class ValidarUsuario extends HttpServlet {

    UsuarioDao UsuarioDao = new UsuarioDao();
    Usuario usu = new Usuario();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion.equalsIgnoreCase("ingresar")) {

            String Correo = request.getParameter("Correo");
            String Contrasena = request.getParameter("Contrasena");

            try {
                usu = UsuarioDao.Validar(Correo, Contrasena);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ValidarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (usu.getCorreo() != null && usu.getContrasena() != null) {

                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuarioLogueado", usu);
                sesion.setAttribute("idUsuario", usu.getIdU());
                sesion.setAttribute("idRol", usu.getIdR());

                // ADMIN
                if (usu.getIdR() == 1) {
                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("admin/admin.jsp").forward(request, response);
                }

                // DUEÑO
                else if (usu.getIdR() == 2) {
                    MascotaDao mascotaDao = new MascotaDao();
                    List<Mascota> listaMascotas;
                    try {
                        listaMascotas = mascotaDao.ListarMascotaPorUsuario(usu.getIdU());
                        sesion.setAttribute("listaMascotas", listaMascotas);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ValidarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("duenomascota/dueno.jsp").forward(request, response);
                }

                // VETERINARIO
                else {
                    try {
                        int idVeterinario = UsuarioDao.obtenerIdVeterinarioPorIdUsuario(usu.getIdU());
                        sesion.setAttribute("idVeterinario", idVeterinario);
                    } catch (Exception e) {
                        System.out.println("Error obteniendo id del veterinario: " + e.getMessage());
                    }

                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("veterinario/veterinario.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("fail", "Correo o contraseña inválidos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("fail", "Debe ingresar correo y contraseña");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de validación de usuario";
    }
}
