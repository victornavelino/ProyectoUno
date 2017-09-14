/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.EntradasSalidas;

import controladores.ExpedienteJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.Expediente;
import entidades.EntradasSalidas.Pase;
import facade.ConexionFacade;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vouilloz
 */
public class ExpedienteFacade {

    private static ExpedienteFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);

    protected ExpedienteFacade() {
    }

    public static ExpedienteFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ExpedienteFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Expediente expediente) {
        new ExpedienteJpaController(emf).create(expediente);
    }

    public void modificar(Expediente expediente) {
        try {
            new ExpedienteJpaController(emf).edit(expediente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ExpedienteFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ExpedienteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Expediente> getTodas() {
        return new ExpedienteJpaController(emf).findExpedienteEntities();
    }

    public Expediente buscar(Long id) {
        return new ExpedienteJpaController(emf).findExpediente(id);
    }

    public void eliminar(Long id) {
        try {
            new ExpedienteJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ExpedienteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        emf.close();
    }

    public List<Expediente> buscar(String nombre) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT a FROM Expediente a "
                + "WHERE o.nombre LIKE '%" + nombre + "%'");
        return q.getResultList();
    }

    public List<Expediente> buscarExp(String letra, String numero) {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.letra= '" + letra + "'AND e.numero='" + numero + "'");
        return quBuscarOrg.getResultList();
    }

    public List<Expediente> buscarPorAño(int año) {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.anio='" + año + "'");
        return quBuscarOrg.getResultList();
    }

    public List<Expediente> busquedaPorLetra(String letra) {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.letra LIKE '%" + letra + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Expediente> busquedaPorNumero(String numero) {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.numero LIKE '%" + numero + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Expediente> getExpedientes() {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesEntrada() {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesPrimeros25() {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false ORDER BY p.fechaRegistro DESC");
        return quBuscarOrg.getResultList().subList(0, 25);
    }

    public List<Pase> getPasesEmisorArea(String emisor) {
        EntityManager em = emf.createEntityManager();
        System.out.println("" + emisor);
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false AND p.areaEmisor.nombre LIKE '%" + emisor + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesEmisorInvestigador(String emisor) {
        EntityManager em = emf.createEntityManager();
        System.out.println("" + emisor);
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false AND p.investigadorEmisor.persona.apellido LIKE '%" + emisor + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesEmisorOtro(String emisor) {
        EntityManager em = emf.createEntityManager();
        System.out.println("" + emisor);
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false AND p.otroEmisor.descripcion LIKE '%" + emisor + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesDestinoInvestigador(String destino) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false AND p.investigadorDestino.persona.apellido LIKE '%" + destino + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesDestinoArea(String destino) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false AND p.areaDestino.nombre LIKE '%" + destino + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesDestinoOtro(String destino) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false AND p.otroDestino.descripcion LIKE '%" + destino + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesMotivo(String motivo) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false AND p.motivo LIKE '%" + motivo + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesMotivoSalida(String motivo) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false AND p.motivo LIKE '%" + motivo + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesFechaRecepcion(Date fechaRecepcion) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false AND p.fechaRecepcion=:fechaRecepcion");
        quBuscarOrg.setParameter("fechaRecepcion", fechaRecepcion);
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesFechaSalida(Date fechaSalida) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false AND p.fechaDestino=:fechaSalida");
        quBuscarOrg.setParameter("fechaSalida", fechaSalida);
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesFechaNota(Date fechaNota) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false AND p.nota.fechaNota=:fechaNota");
        quBuscarOrg.setParameter("fechaNota", fechaNota);
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesFechaNotaSalida(Date fechaNota) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false AND p.nota.fechaNota=:fechaNota");
        quBuscarOrg.setParameter("fechaNota", fechaNota);
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesSalida() {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false");
        return quBuscarOrg.getResultList();
    }

    public List<Expediente> busquedaPorInciador(String iniciador) {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.iniciador LIKE '%" + iniciador + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Expediente> busquedaPorEstracto(String estracto) {
        EntityManager em = emf.createEntityManager();

        Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
                + "WHERE e.estracto LIKE '%" + estracto + "%'");
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesEntradaUltSemana(Date fecha, Date fechaActual) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada' AND p.borrado=false AND (p.fechaRegistro Between :fecha AND :fechaActual)");
        quBuscarOrg.setParameter("fecha", fecha);
        quBuscarOrg.setParameter("fechaActual", fechaActual);
        return quBuscarOrg.getResultList();
    }

    public List<Pase> getPasesSalidaUltSemana(Date fecha, Date fechaActual) {
        EntityManager em = emf.createEntityManager();
        Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida' AND p.borrado=false AND (p.fechaRegistro Between :fecha AND :fechaActual)");
        quBuscarOrg.setParameter("fecha", fecha);
        quBuscarOrg.setParameter("fechaActual", fechaActual);
        return quBuscarOrg.getResultList();
    }
}
