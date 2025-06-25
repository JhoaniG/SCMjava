/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Dao.MascotaDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import modelo.Mascota;
import modelo.Usuario;

/**
 *
 * @author jhoan
 */
@WebServlet("/MascotaController")
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
                    List<Mascota> mascotas = dao.ListarMascotaPorUsuario(usuario.getIdU());
                    request.setAttribute("mascotas", mascotas);
                    request.getRequestDispatcher("duenomascota/mismascotas.jsp").forward(request, response);

                } catch (Exception e) {
                    System.out.println("Error al listar Mascotas");
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
                    System.out.println("Erro Al Editar mascota");
                    response.sendRedirect("MascotaController?accion=Listar");
                }
                break;
            case "Eliminar":
                int id = Integer.parseInt(request.getParameter("IdM"));
                try{
                dao.eliminarMascota(id);
                response.sendRedirect("MascotaController?accion=Listar");
                
                }catch(Exception e){
                    System.out.println("Erro al elikminar mascota" + e.getMessage());
                    response.sendRedirect("MascotaController?accion=Lsitar");
                
                }
                break;
            default:
                break;

        }
    }
    //{}

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

            String Nombre = request.getParameter("Nombre");
            String Genero = request.getParameter("Genero");
            Date FechaNacimineto = Date.valueOf(request.getParameter("FechaNacimineto"));
            String Raza = request.getParameter("Raza");
            Mascota mascota = new Mascota();
            mascota.setIdU(usuario.getIdU());
            mascota.setNombre(Nombre);
            mascota.setGenero(Genero);
            mascota.setFechaNacimineto(FechaNacimineto);
            mascota.setRaza(Raza);
            try {
                dao.insertarMascota(mascota);
                response.sendRedirect("MascotaController?accion=Listar");

            } catch (Exception e) {
                System.out.println("Error al Registrar Mascota");
                response.sendRedirect("duenomascota/registerM.jsp");

            }

        } else if (accion.equals("Actualizar")) {
            int IdM =Integer.parseInt(request.getParameter("IdM"));
            String Nombre=request.getParameter("Nombre");
            String Genero=request.getParameter("Genero");
            Date FechaNacimineto=Date.valueOf(request.getParameter("FechaNacimineto"));
            String Raza=request.getParameter("Raza");
            Mascota mascota=new Mascota();
            mascota.setIdM(IdM);
            mascota.setNombre(Nombre);
            mascota.setGenero(Genero);
            mascota.setFechaNacimineto(FechaNacimineto);
            mascota.setRaza(Raza);
            try{
            dao.actulizarMascota(mascota);
            response.sendRedirect("MascotaController?accion=Listar");
            
            
            
            }catch(Exception e){
                System.out.println("Erro al Editar" + e.getMessage());
                response.sendRedirect("MascotaController?accion=Editar&id="+ IdM);
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
