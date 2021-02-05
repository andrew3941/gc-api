package com.preving.intranet.ssggapi.model.service.ora2Postgre;


import com.preving.intranet.ssggapi.model.dao.ora2Postgre.Ora2PostgreDao;
import com.preving.intranet.ssggapi.model.domain.SimpleJavaBean;
import com.preving.intranet.ssggapi.model.domain.UsuarioMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Ora2PostgreManager implements Ora2PostgreService{

    @Autowired
    private Ora2PostgreDao ora2PostgreDao;

    @Override
    public SimpleJavaBean sincronizar() {

        System.out.println(">>>>> ORA2POSTGRE >>>>> Inicia proceso Ora2Postgre ---> " + new Date());
        try {

            // Comprobamos datos procedentes de oracle con los existentes en postgre
            this.sincronizarComunidades();
            System.out.println(">>>>> ORA2POSTGRE >>>>> Comunidades sincronizadas "  + new Date());

            this.sincronizarDelegaciones();
            System.out.println(">>>>> ORA2POSTGRE >>>>> Delegaciones sincronizadas " + new Date());

            this.sincronizarDepartamentos();
            System.out.println(">>>>> ORA2POSTGRE >>>>> Departamentos sincronizados " + new Date());

            this.sincronizarUsuarios();
            System.out.println(">>>>> ORA2POSTGRE >>>>> Usuarios sincronizados " + new Date());

            System.out.println(">>>>> ORA2POSTGRE >>>>> Termina proceso Ora2Postgre ---> " + new Date());

            return new SimpleJavaBean("Termina proceso Ora2Postgre ---> " + new Date());

        } catch (Exception ex) {
            ex.printStackTrace();
            return new SimpleJavaBean("Se ha producido un error al obtener los datos para sincronizar las tablas de Oracle2Postgre");
        }
    }

    private List<SimpleJavaBean> getComunidadesPostgre() {
        return this.ora2PostgreDao.getComunidadesPostgre();
    }

    private List<SimpleJavaBean> getComunidadesOracle() {
        return this.ora2PostgreDao.getComunidadesOracle();
    }

    private List<SimpleJavaBean> getDelegacionesPostgre() {
        return this.ora2PostgreDao.getDelegacionesPostgre();
    }

    private List<SimpleJavaBean> getDelegacionesOracle() {
        return this.ora2PostgreDao.getDelegacionesOracle();
    }

    private List<SimpleJavaBean> getDepartamentosPostgre() {
        return this.ora2PostgreDao.getDepartamentosPostgre();
    }

    private List<SimpleJavaBean> getDepartamentosOracle() {
        return this.ora2PostgreDao.getDepartamentosOracle();
    }

    public List<UsuarioMin> getUsuariosPostgre() {
        return this.ora2PostgreDao.getUsuariosPostgre();
    }

    private List<UsuarioMin> getUsuariosOracle() {
        return this.ora2PostgreDao.getUsuariosOracle();
    }

    private int persistComunidad(SimpleJavaBean comunidad, boolean isUpdate) {
        return this.ora2PostgreDao.persistComunidad(comunidad, isUpdate);
    }

    private int persistDelegacion(SimpleJavaBean delegacion, boolean isUpdate) {
        return this.ora2PostgreDao.persistDelegacion(delegacion, isUpdate);
    }

    private int persistDepartamento(SimpleJavaBean departamento, boolean isUpdate) {
        return this.ora2PostgreDao.persistDepartamento(departamento, isUpdate);
    }


    private void deleteEmpleados() {
        this.ora2PostgreDao.deleteEmpleados();
    }

    private int persistUsuario(UsuarioMin usuario, boolean isUpdate) {
        return this.ora2PostgreDao.persistUsuario(usuario, isUpdate);
    }


    private void sincronizarComunidades() {

        List<SimpleJavaBean> comunidadesOracle = this.getComunidadesOracle();
        List<SimpleJavaBean> comunidadesPostgre = this.getComunidadesPostgre();

        boolean actualizado;
        int updates = 0;
        int inserts = 0;
        int errores = 0;

        // Comprobamos las comunidades
        for (int i = 0; i < comunidadesOracle.size() ; i++) {
            try {
                actualizado = false;
                for (int j = 0; j < comunidadesPostgre.size(); j++) {
                    if (comunidadesPostgre.get(j).getId() == (comunidadesOracle.get(i).getId())) {
                        this.persistComunidad(comunidadesOracle.get(i), true);
                        updates++;
                        actualizado = true;
                        comunidadesPostgre.remove(j);
                        break;
                    }
                }

                if (!actualizado) {
                    this.persistComunidad(comunidadesOracle.get(i), false);
                    inserts++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                errores ++;
            }
        }

        System.out.println("Se actualizan: " + updates + " registros de comunidad");
        System.out.println("Se insertan: "   + inserts + " registros de comunidad");
        System.out.println("Fallan: "        + errores + " registros de comunidad");
    }

    private void sincronizarDelegaciones() {

        List<SimpleJavaBean> delegacionesOracle = this.getDelegacionesOracle();
        List<SimpleJavaBean> delegacionesPostgre = this.getDelegacionesPostgre();

        boolean actualizado;
        int updates = 0;
        int inserts = 0;
        int errores = 0;

        // Comprobamos las delegaciones
        for (int i = 0; i < delegacionesOracle.size() ; i++) {
            try {
                actualizado = false;
                for (int j = 0; j < delegacionesPostgre.size(); j++) {
                    if (delegacionesPostgre.get(j).getId() == (delegacionesOracle.get(i).getId())) {
                        this.persistDelegacion(delegacionesOracle.get(i), true);
                        updates++;
                        actualizado = true;
                        delegacionesPostgre.remove(j);
                        break;
                    }
                }

                if (!actualizado) {
                    this.persistDelegacion(delegacionesOracle.get(i), false);
                    inserts++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                errores ++;
            }
        }

        System.out.println("Se actualizan: " + updates + " registros de delegacion");
        System.out.println("Se insertan: "   + inserts + " registros de delegacion");
        System.out.println("Fallan: "        + errores + " registros de delegacion");
    }

    private void sincronizarDepartamentos() {

        List<SimpleJavaBean> departamentosOracle = this.getDepartamentosOracle();
        List<SimpleJavaBean> departamentosPostgre = this.getDepartamentosPostgre();

        boolean actualizado;
        int updates = 0;
        int inserts = 0;
        int errores = 0;

        // Comprobamos los departamentos
        for (int i = 0; i < departamentosOracle.size() ; i++) {
            try {
                actualizado = false;
                for (int j = 0; j < departamentosPostgre.size(); j++) {
                    if (departamentosPostgre.get(j).getId() == (departamentosOracle.get(i).getId())) {
                        this.persistDepartamento(departamentosOracle.get(i), true);
                        updates++;
                        actualizado = true;
                        departamentosPostgre.remove(j);
                        break;
                    }
                }

                if (!actualizado) {
                    this.persistDepartamento(departamentosOracle.get(i), false);
                    inserts++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                errores ++;
            }
        }

        System.out.println("Se actualizan: " + updates + " registros de departamento");
        System.out.println("Se insertan: "   + inserts + " registros de departamento");
        System.out.println("Fallan: "        + errores + " registros de departamento");
    }


    private void sincronizarUsuarios() {

        List<UsuarioMin> usuariosOracle = this.getUsuariosOracle();
        List<UsuarioMin> usuariosPostgre = this.getUsuariosPostgre();

        boolean actualizado;
        int updates = 0;
        int inserts = 0;
        int errores = 0;

        // Comprobamos los usuarios
        for (int i = 0; i < usuariosOracle.size() ; i++) {
            try {
                actualizado = false;
                for (int j = 0; j < usuariosPostgre.size(); j++) {
                    if (usuariosPostgre.get(j).getId() == (usuariosOracle.get(i).getId())) {
                        this.persistUsuario(usuariosOracle.get(i), true);
                        updates++;
                        actualizado = true;
                        usuariosPostgre.remove(j);
                        break;
                    }
                }

                if (!actualizado) {
                    this.persistUsuario(usuariosOracle.get(i), false);
                    inserts++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                errores ++;
            }
        }

        System.out.println("Se actualizan: " + updates + " registros de usuario");
        System.out.println("Se insertan: "   + inserts + " registros de usuario");
        System.out.println("Fallan: "        + errores + " registros de usuario");

    }


}
