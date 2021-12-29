package Dao;

import Models.Cargo;
import Utils.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoBD extends Conexion {

    public List<Cargo> listarCargos() throws Exception {
        List<Cargo> cargos;
        Cargo car;
        ResultSet rs = null;

        String sql = "select C.IDcargo, C.NombreCargo, C.Estado from Cargo C "
                + " order by C.IDcargo;";

        try {

            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            cargos = new ArrayList<>();

            while (rs.next() == true) {

                car = new Cargo();
                car.setCodigo(rs.getInt("IDcargo"));
                car.setNombreCargo(rs.getString("NombreCargo"));
                car.setEstado(rs.getBoolean("Estado"));

                cargos.add(car);

            }

            this.cerrar(true);

        } catch (Exception e) {

            throw e;

        } finally {

        }

        return cargos;

    }

}
