package Controllers;

import Dao.CargoBD;
import Dao.UsuarioBD;
import Models.Cargo;

import Models.Usuario;
import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        
        try {
            
            if (accion != null) {
                
                switch (accion) {
                    
                    case "verificar":
                        Verificar(request, response);
                        break;
                    
                    case "cerrar":
                        CerrarSession(request, response);
                        break;
                    
                    case "listarUsuarios":
                        listarUsuarios(request, response);
                        break;
                    
                    case "nuevo":
                        presentrarFormulario(request, response);
                        break;
                    case "registrar":
                        
                        registrarUsuario(request, response);
                        
                        break;
                    
                    case "leerUsuario":
                        
                        presentarUsuario(request, response);
                        
                        break;
                    
                    case "actualizarUsuario":
                        
                        actualizarUsuario(request, response);
                        
                        break;
                    
                    case "eliminarUsuario":
                        
                        eliminarUsuario(request, response);
                        
                        break;
                    
                    default:
                        response.sendRedirect("index.jsp");
                    
                }
                
            } else if (request.getParameter("cambiar") != null) {
                
                cambiarEstado(request, response);
                
            } else {
                response.sendRedirect("index.jsp");
            }
            
        } catch (Exception e) {
            
            try {
                
                this.getServletConfig().getServletContext().getRequestDispatcher("/mensaje.jsp").forward(request, response);
                
            } catch (Exception ex) {
                
                System.out.println("Error" + e.getMessage());
                
            }
            
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
        processRequest(request, response);
    }

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
        processRequest(request, response);
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

    private void Verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession sesion;
        
        UsuarioBD dao;
        
        Usuario usuario;
        
        usuario = this.obtenerUsuario(request);
        
        dao = new UsuarioBD();
        
        usuario = dao.Index(usuario);
        
        if (usuario != null && usuario.getCargo().getNombreCargo().equals("Administrador")) {
            
            sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            request.setAttribute("msje", "Bienvenido al Sistema");
            this.getServletConfig().getServletContext().getRequestDispatcher("/Views/Menu_Principal.jsp").forward(request, response);
            
        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("Cliente")) {
            sesion = request.getSession();
            sesion.setAttribute("cliente", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/Views/Menu_Cliente.jsp").forward(request, response);
            
        } else {
            
            request.setAttribute("msje", "Credenciales Incorrectas");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        }
        
    }
    
    private void CerrarSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("index.jsp");
        
    }
    
    private Usuario obtenerUsuario(HttpServletRequest request) {
        
        Usuario u = new Usuario();
        
        u.setNombreUsuario(request.getParameter("txtUsu"));
        u.setClave(request.getParameter("txtPass"));
        
        return u;
        
    }
    
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
        
        UsuarioBD dao = new UsuarioBD();
        
        List<Usuario> usus = null;
        
        try {
            
            usus = dao.listarUsuarios();
            request.setAttribute("usuarios", usus);
            
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los usuarios" + e.getMessage());
        } finally {
            
            dao = null;
            
        }
        
        try {
            
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/Views/Usuarios.jsp").forward(request, response);
            
        } catch (Exception ex) {
            
            request.setAttribute("msje", "No se pudo realizar la peticion" + ex.getMessage());
        }
        
    }
    
    private void presentrarFormulario(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            this.cargarCargos(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/Views/NuevoUsuario.jsp").forward(request, response);
            
        } catch (Exception e) {
            
            request.setAttribute("msje", "No se pudo cargar la vista");
            
        }
        
    }
    
    private void cargarCargos(HttpServletRequest request) {
        
        CargoBD dao = new CargoBD();
        List<Cargo> car = null;
        
        try {
            
            car = dao.listarCargos();
            request.setAttribute("cargos", car);
            
        } catch (Exception e) {
            
            request.setAttribute("msje", "No se pudo cargar los cargos :( " + e.getMessage());
            
        } finally {
            
            car = null;
            dao = null;
            
        }
        
    }
    
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        
        UsuarioBD daoUsu;
        Usuario usu = null;
        Cargo carg;
        
        if (request.getParameter("txtNombre") != null
                && request.getParameter("txtClave") != null
                && request.getParameter("cboCargo") != null) {
            
            usu = new Usuario();
            usu.setNombreUsuario(request.getParameter("txtNombre"));
            usu.setClave(request.getParameter("txtClave"));
            carg = new Cargo();
            carg.setCodigo(Integer.parseInt(request.getParameter("cboCargo")));
            usu.setCargo(carg);
            if (request.getParameter("chkEstado") != null) {
                usu.setEstado(true);
            } else {
                usu.setEstado(false);
            }
            daoUsu = new UsuarioBD();
            try {
                daoUsu.registrarUsuarios(usu);
                response.sendRedirect("srvUsuario?accion=listarUsuarios");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el usuario" + e.getMessage());
                request.setAttribute("usuario", usu);
                this.presentrarFormulario(request, response);
            }
        }
        
    }
    
    private void presentarUsuario(HttpServletRequest request, HttpServletResponse response) {
        
        UsuarioBD dao;
        Usuario usus;
        
        if (request.getParameter("cod") != null) {
            usus = new Usuario();
            usus.setIdUsuario(Integer.parseInt(request.getParameter("cod")));
            
            dao = new UsuarioBD();
            try {
                usus = dao.leerUsuario(usus);
                if (usus != null) {
                    request.setAttribute("usuario", usus);
                } else {
                    request.setAttribute("msje", "No se encontró el usuario");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        try {
            this.cargarCargos(request);
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/Views/actualizarUsuario.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }
        
    }
    
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        
        UsuarioBD daoUsu;
        Usuario usus = null;
        Cargo car;
        
        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtNombre") != null
                && request.getParameter("txtClave") != null
                && request.getParameter("cboCargo") != null) {
            
            usus = new Usuario();
            usus.setIdUsuario(Integer.parseInt(request.getParameter("hCodigo")));
            usus.setNombreUsuario(request.getParameter("txtNombre"));
            usus.setClave(request.getParameter("txtClave"));
            car = new Cargo();
            car.setCodigo(Integer.parseInt(request.getParameter("cboCargo")));
            usus.setCargo(car);
            if (request.getParameter("chkEstado") != null) {
                usus.setEstado(true);
            } else {
                usus.setEstado(false);
            }
            daoUsu = new UsuarioBD();
            try {
                daoUsu.actualizarUsuarios(usus);
                response.sendRedirect("srvUsuario?accion=listarUsuarios");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar el usuario" + e.getMessage());
                request.setAttribute("usuario", usus);
                
            }
            try {
                this.cargarCargos(request);
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/Views/actualizarUsuario.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
            }
        }
        
    }
    
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        
        UsuarioBD dao = new UsuarioBD();
        Usuario usus = new Usuario();
        
        if (request.getParameter("cod") != null) {
            
            usus.setIdUsuario(Integer.parseInt(request.getParameter("cod")));
            
            try {
                
                dao.eliminarUsuario(usus);
                response.sendRedirect("srvUsuario?accion=listarUsuarios");
                
            } catch (Exception e) {
                
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
            
        } else {
            
            request.setAttribute("msje", "no se encontro el usuario");
            
        }
        
    }
    
    private void cambiarEstado(HttpServletRequest request, HttpServletResponse response) {
        
        UsuarioBD dao;
        Usuario usu;
        
        try {
            
            dao = new UsuarioBD();
            usu = new Usuario();
            
            if (request.getParameter("cambiar").equals("activar")) {
                
                usu.setEstado(true);
                
            } else {
                
                usu.setEstado(false);
            }
            
            if (request.getParameter("cod") != null) {
                
                usu.setIdUsuario(Integer.parseInt(request.getParameter("cod")));
                dao.cambiarVigencia(usu);
                
            }else{
                
                request.setAttribute("msje", "No se obtuvo el id del Usuario");
                
                
            }
            
        } catch (Exception e) {
            
            request.setAttribute("msj", e.getMessage());
        }
        
        this.listarUsuarios(request, response);
        
    }
    
}
