package Dao;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBD extends Conexion {

    public Usuario Index(Usuario user) throws Exception {

        Usuario usu = null;
        ResultSet rs = null;

        String sql = "select U.id_usuario, C.nombreCargo "
                + "         from usuario U inner join "
                + "       cargo C ON U.id_cargo = C.id_cargo "
                + "		where U.estado = 1 "
                + "        and U.nombreUsuario = '" + user.getNombreUsuario() + "'"
                + "        and U.clave = '" + user.getClave() + "'";

        try {

            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);

            if (rs.next() == true) {

                usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNombreUsuario(user.getNombreUsuario());
                usu.setCargo(new Cargo());
                usu.getCargo().setNombreCargo(rs.getString("nombreCargo"));
                usu.setEstado(true);
            }

            rs.close();

        } catch (Exception e) {

            System.out.println("Error" + e.getMessage());
        } finally {

            this.cerrar(false);

        }

        return usu;

    }

    public List<Usuario> listarUsuarios() throws Exception {
        List<Usuario> usuarios;
        Usuario usu;
        ResultSet rs = null;

        String sql = " select U.IDusuario, U.NombreUsuario, U.Clave , U.Estado, C.NombreCargo "
                + "     from Usuario U inner join Cargo C "
                + "     ON C.IDcargo = U.IdCargo "
                + "     order by U.IDusuario";

        try {

            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            usuarios = new ArrayList<>();

            while (rs.next() == true) {

                usu = new Usuario();
                usu.setIdUsuario(rs.getInt("IDusuario"));
                usu.setNombreUsuario(rs.getString("NombreUsuario"));
                usu.setClave(rs.getString("Clave"));
                usu.setEstado(rs.getBoolean("Estado"));
                usu.setCargo(new Cargo());
                usu.getCargo().setNombreCargo(rs.getString("NombreCargo"));

                usuarios.add(usu);

            }

            this.cerrar(true);

        } catch (Exception e) {

            throw e;

        } finally {

        }

        return usuarios;

    }

    public void registrarUsuarios(Usuario usu) throws Exception {

        String sql;

        sql = "insert into Usuario (NombreUsuario, Clave, Estado, IdCargo) "
                + "values('" + usu.getNombreUsuario() + "', '"
                + usu.getClave() + "', "
                + (usu.isEstado() == true ? "1" : "0")
                + ", " + usu.getCargo().getCodigo() + ")";

        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);

        } catch (Exception e) {

            this.cerrar(false);
            throw e;

        }

    }

    public Usuario leerUsuario(Usuario usu) throws Exception {

        Usuario usus = null;
        ResultSet rs = null;

        String sql = " select U.IDusuario, U.NombreUsuario, U.Clave , U.Estado, U.IdCargo "
                + "     from Usuario U where U.IDusuario =" + usu.getIdUsuario();

        try {

            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);

            if (rs.next() == true) {

                usus = new Usuario();
                usus.setIdUsuario(rs.getInt("IDusuario"));
                usus.setNombreUsuario(rs.getString("NombreUsuario"));
                usus.setClave(rs.getString("Clave"));
                usus.setEstado(rs.getBoolean("Estado"));
                usus.setCargo(new Cargo());
                usus.getCargo().setCodigo(rs.getInt("IdCargo"));

            }

            this.cerrar(true);

        } catch (Exception e) {
            this.cerrar(false);
            throw e;

        } finally {

        }

        return usus;

    }

    public void actualizarUsuarios(Usuario usu) throws Exception {

        String sql = "update Usuario set NombreUsuario = '"
                + usu.getNombreUsuario() + "', Clave = '"
                + usu.getClave() + "', Estado = "
                + (usu.isEstado() == true ? "1" : "0")
                + ", IdCargo = "
                + usu.getCargo().getCodigo()
                + " where IDusuario = " + usu.getIdUsuario();

        try {

            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);

        } catch (Exception e) {

            this.cerrar(false);
            throw e;

        }

    }

    public void eliminarUsuario(Usuario usu) throws Exception {

        String sql = "delete from Usuario"
                + " where IDusuario = " + usu.getIdUsuario();

        try {

            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);

        } catch (Exception e) {

            this.cerrar(false);
            throw e;

        }

    }

    public void cambiarVigencia(Usuario usus) throws Exception {

        String sql = "update Usuario set Estado = "
                + (usus.isEstado() == true ? "1" : "0")
                + " where IDusuario = " + usus.getIdUsuario();

        try {

            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            
            this.cerrar(false);
            throw e;

        }

    }

}
