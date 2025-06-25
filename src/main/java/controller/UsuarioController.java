package controller;

import Dao.UsuarioDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

@WebServlet("/UsuarioController")
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

            Usuario usuario = new Usuario();
            usuario.setNombre(Nombre);
            usuario.setApellido(Apellido);
            usuario.setCorreo(Correo);
            usuario.setContrasena(Contrasena);
            usuario.setTelefono(Telefono);
            usuario.setDireccion(Direccion);
            usuario.setIdR(IdR);

            try {
                dao.InsertarUsuario(usuario);

                // Si es veterinario (IdR == 3)
                if (IdR == 3) {
                    String especialidad = request.getParameter("Especialidad");
                    int nuevoIdU = dao.obtenerUltimoIdInsertado(); // Recupera el último ID insertado
                    dao.insertarVeterinario(nuevoIdU, especialidad); // Insertar en tabla veterinario
                }

                request.getRequestDispatcher("login.jsp").forward(request, response);

            } catch (Exception e) {
                System.out.println("Error Registrar Usuario " + e.getMessage());
            }
        } else if (accion.equals("Actualizar")) {
            try {
                int IdU = Integer.parseInt(request.getParameter("IdU")); // ⚠️ Este ID debe venir oculto en el formulario
                Usuario usuario = new Usuario();
                usuario.setIdU(IdU);
                usuario.setNombre(request.getParameter("Nombre"));
                usuario.setApellido(request.getParameter("Apellido"));
                usuario.setCorreo(request.getParameter("Correo"));
                usuario.setContrasena(request.getParameter("Contrasena"));
                usuario.setTelefono(request.getParameter("Telefono"));
                usuario.setDireccion(request.getParameter("Direccion"));

                dao.actualizarUsuario(usuario);
                response.sendRedirect("duenomascota/dueno.jsp");

            } catch (Exception e) {
                System.out.println("Error al actualizar usuario: " + e.getMessage());
                response.sendRedirect("editarU.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestión de usuarios";
    }
}
