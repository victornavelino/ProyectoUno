/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.UsuarioJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.usuario.Usuario;
import includes.Comunes;
import includes.SHA1;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author carlos
 */
public class UsuarioFacade {

    EntityManagerFactory emf;
    UsuarioJpaController usuarioJpa;
    EntityManager em;
    Query quUsuario;
    List listUsuarios;
    SHA1 sha1;

    public UsuarioFacade() {
        try {
            emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
            usuarioJpa = new UsuarioJpaController(emf);
            em = emf.createEntityManager();
            sha1 = new SHA1();
        } catch (javax.persistence.PersistenceException ex) {
            Comunes.mensajeError(ex, "Error Conectado a la Base de Datos");
        } catch (Exception ex) {
            Comunes.mensajeError(ex, "Error");
        }
    }

    public void alta(Usuario usuario) {
        usuarioJpa.create(usuario);
    }

    public void modificar(Usuario usuario) {
        try {
            usuarioJpa.edit(usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminar(Long id) {
        try {
            usuarioJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario buscar(Long id) {
        return usuarioJpa.findUsuario(id);
    }

    public Usuario validar(Usuario usuariop) {
        Usuario usuario = new Usuario();
        try {
            usuariop.setContrasena(sha1.getHash(usuariop.getContrasena()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        quUsuario = em.createQuery("SELECT u FROM "
                + "Usuario u WHERE u.nombreUsuario = '"
                + usuariop.getNombreUsuario() + "' AND u.contrasena = '"
                + usuariop.getContrasena() + "'");
        listUsuarios = quUsuario.getResultList();
        if (listUsuarios.size() > 0) {
            usuario = (Usuario) listUsuarios.get(0);
        }
        return usuario;
    }

    public List<Usuario> listarUsuarios(String filtro) {
        quUsuario = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreCompleto LIKE '%"
                + filtro + "%'");
        List<Usuario> listUsuario = quUsuario.getResultList();
        return listUsuario;
    }

    public List<Usuario> listarTodosUsuarios() {
        quUsuario = em.createQuery(
                "SELECT u FROM Usuario u ORDER BY u.nombreUsuario");
        List<Usuario> listUsuario = quUsuario.getResultList();
        return listUsuario;
    }
}
