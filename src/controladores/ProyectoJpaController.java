/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.proyecto.ProyectoDatosComplementarios;
import entidades.economico.Presupuesto;
import entidades.proyecto.Proyecto;
import entidades.proyecto.resultado.Contrato;
import java.util.ArrayList;
import java.util.List;
import entidades.proyecto.resultado.FormacionRRHH;
import entidades.proyecto.resultado.Publicacion;
import entidades.proyecto.resultado.Propiedad;
import entidades.proyecto.Evaluacion;
import entidades.proyecto.Participacion;
import entidades.Resolucion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hugo
 */
public class ProyectoJpaController implements Serializable {

    public ProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) {
        if (proyecto.getContratos() == null) {
            proyecto.setContratos(new ArrayList<Contrato>());
        }
        if (proyecto.getFormacionRRHHs() == null) {
            proyecto.setFormacionRRHHs(new ArrayList<FormacionRRHH>());
        }
        if (proyecto.getPublicaciones() == null) {
            proyecto.setPublicaciones(new ArrayList<Publicacion>());
        }
        if (proyecto.getPropiedades() == null) {
            proyecto.setPropiedades(new ArrayList<Propiedad>());
        }
        if (proyecto.getEvaluaciones() == null) {
            proyecto.setEvaluaciones(new ArrayList<Evaluacion>());
        }
        if (proyecto.getParticipaciones() == null) {
            proyecto.setParticipaciones(new ArrayList<Participacion>());
        }
        if (proyecto.getProyectosComplementarios() == null) {
            proyecto.setProyectosComplementarios(new ArrayList<Proyecto>());
        }
        if (proyecto.getResoluciones() == null) {
            proyecto.setResoluciones(new ArrayList<Resolucion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoDatosComplementarios proyectoDatosComplementarios = proyecto.getProyectoDatosComplementarios();
            if (proyectoDatosComplementarios != null) {
                proyectoDatosComplementarios = em.getReference(proyectoDatosComplementarios.getClass(), proyectoDatosComplementarios.getId());
                proyecto.setProyectoDatosComplementarios(proyectoDatosComplementarios);
            }
            Presupuesto presupuesto = proyecto.getPresupuesto();
            if (presupuesto != null) {
                presupuesto = em.getReference(presupuesto.getClass(), presupuesto.getId());
                proyecto.setPresupuesto(presupuesto);
            }
            Proyecto proyectoComplementarioPadre = proyecto.getProyectoComplementarioPadre();
            if (proyectoComplementarioPadre != null) {
                proyectoComplementarioPadre = em.getReference(proyectoComplementarioPadre.getClass(), proyectoComplementarioPadre.getId());
                proyecto.setProyectoComplementarioPadre(proyectoComplementarioPadre);
            }
            List<Contrato> attachedContratos = new ArrayList<Contrato>();
            for (Contrato contratosContratoToAttach : proyecto.getContratos()) {
                contratosContratoToAttach = em.getReference(contratosContratoToAttach.getClass(), contratosContratoToAttach.getId());
                attachedContratos.add(contratosContratoToAttach);
            }
            proyecto.setContratos(attachedContratos);
            List<FormacionRRHH> attachedFormacionRRHHs = new ArrayList<FormacionRRHH>();
            for (FormacionRRHH formacionRRHHsFormacionRRHHToAttach : proyecto.getFormacionRRHHs()) {
                formacionRRHHsFormacionRRHHToAttach = em.getReference(formacionRRHHsFormacionRRHHToAttach.getClass(), formacionRRHHsFormacionRRHHToAttach.getId());
                attachedFormacionRRHHs.add(formacionRRHHsFormacionRRHHToAttach);
            }
            proyecto.setFormacionRRHHs(attachedFormacionRRHHs);
            List<Publicacion> attachedPublicaciones = new ArrayList<Publicacion>();
            for (Publicacion publicacionesPublicacionToAttach : proyecto.getPublicaciones()) {
                publicacionesPublicacionToAttach = em.getReference(publicacionesPublicacionToAttach.getClass(), publicacionesPublicacionToAttach.getId());
                attachedPublicaciones.add(publicacionesPublicacionToAttach);
            }
            proyecto.setPublicaciones(attachedPublicaciones);
            List<Propiedad> attachedPropiedades = new ArrayList<Propiedad>();
            for (Propiedad propiedadesPropiedadToAttach : proyecto.getPropiedades()) {
                propiedadesPropiedadToAttach = em.getReference(propiedadesPropiedadToAttach.getClass(), propiedadesPropiedadToAttach.getId());
                attachedPropiedades.add(propiedadesPropiedadToAttach);
            }
            proyecto.setPropiedades(attachedPropiedades);
            List<Evaluacion> attachedEvaluaciones = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionesEvaluacionToAttach : proyecto.getEvaluaciones()) {
                evaluacionesEvaluacionToAttach = em.getReference(evaluacionesEvaluacionToAttach.getClass(), evaluacionesEvaluacionToAttach.getId());
                attachedEvaluaciones.add(evaluacionesEvaluacionToAttach);
            }
            proyecto.setEvaluaciones(attachedEvaluaciones);
            List<Participacion> attachedParticipaciones = new ArrayList<Participacion>();
            for (Participacion participacionesParticipacionToAttach : proyecto.getParticipaciones()) {
                participacionesParticipacionToAttach = em.getReference(participacionesParticipacionToAttach.getClass(), participacionesParticipacionToAttach.getId());
                attachedParticipaciones.add(participacionesParticipacionToAttach);
            }
            proyecto.setParticipaciones(attachedParticipaciones);
            List<Proyecto> attachedProyectosComplementarios = new ArrayList<Proyecto>();
            for (Proyecto proyectosComplementariosProyectoToAttach : proyecto.getProyectosComplementarios()) {
                proyectosComplementariosProyectoToAttach = em.getReference(proyectosComplementariosProyectoToAttach.getClass(), proyectosComplementariosProyectoToAttach.getId());
                attachedProyectosComplementarios.add(proyectosComplementariosProyectoToAttach);
            }
            proyecto.setProyectosComplementarios(attachedProyectosComplementarios);
            List<Resolucion> attachedResoluciones = new ArrayList<Resolucion>();
            for (Resolucion resolucionesResolucionToAttach : proyecto.getResoluciones()) {
                resolucionesResolucionToAttach = em.getReference(resolucionesResolucionToAttach.getClass(), resolucionesResolucionToAttach.getId());
                attachedResoluciones.add(resolucionesResolucionToAttach);
            }
            proyecto.setResoluciones(attachedResoluciones);
            em.persist(proyecto);
            if (proyectoDatosComplementarios != null) {
                Proyecto oldProyectoOfProyectoDatosComplementarios = proyectoDatosComplementarios.getProyecto();
                if (oldProyectoOfProyectoDatosComplementarios != null) {
                    oldProyectoOfProyectoDatosComplementarios.setProyectoDatosComplementarios(null);
                    oldProyectoOfProyectoDatosComplementarios = em.merge(oldProyectoOfProyectoDatosComplementarios);
                }
                proyectoDatosComplementarios.setProyecto(proyecto);
                proyectoDatosComplementarios = em.merge(proyectoDatosComplementarios);
            }
            if (presupuesto != null) {
                Proyecto oldProyectoOfPresupuesto = presupuesto.getProyecto();
                if (oldProyectoOfPresupuesto != null) {
                    oldProyectoOfPresupuesto.setPresupuesto(null);
                    oldProyectoOfPresupuesto = em.merge(oldProyectoOfPresupuesto);
                }
                presupuesto.setProyecto(proyecto);
                presupuesto = em.merge(presupuesto);
            }
            if (proyectoComplementarioPadre != null) {
                Proyecto oldProyectoComplementarioPadreOfProyectoComplementarioPadre = proyectoComplementarioPadre.getProyectoComplementarioPadre();
                if (oldProyectoComplementarioPadreOfProyectoComplementarioPadre != null) {
                    oldProyectoComplementarioPadreOfProyectoComplementarioPadre.setProyectoComplementarioPadre(null);
                    oldProyectoComplementarioPadreOfProyectoComplementarioPadre = em.merge(oldProyectoComplementarioPadreOfProyectoComplementarioPadre);
                }
                proyectoComplementarioPadre.setProyectoComplementarioPadre(proyecto);
                proyectoComplementarioPadre = em.merge(proyectoComplementarioPadre);
            }
            for (Contrato contratosContrato : proyecto.getContratos()) {
                contratosContrato.getProyectos().add(proyecto);
                contratosContrato = em.merge(contratosContrato);
            }
            for (FormacionRRHH formacionRRHHsFormacionRRHH : proyecto.getFormacionRRHHs()) {
                Proyecto oldProyectoOfFormacionRRHHsFormacionRRHH = formacionRRHHsFormacionRRHH.getProyecto();
                formacionRRHHsFormacionRRHH.setProyecto(proyecto);
                formacionRRHHsFormacionRRHH = em.merge(formacionRRHHsFormacionRRHH);
                if (oldProyectoOfFormacionRRHHsFormacionRRHH != null) {
                    oldProyectoOfFormacionRRHHsFormacionRRHH.getFormacionRRHHs().remove(formacionRRHHsFormacionRRHH);
                    oldProyectoOfFormacionRRHHsFormacionRRHH = em.merge(oldProyectoOfFormacionRRHHsFormacionRRHH);
                }
            }
            for (Publicacion publicacionesPublicacion : proyecto.getPublicaciones()) {
                publicacionesPublicacion.getProyectos().add(proyecto);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
            }
            for (Propiedad propiedadesPropiedad : proyecto.getPropiedades()) {
                propiedadesPropiedad.getProyectos().add(proyecto);
                propiedadesPropiedad = em.merge(propiedadesPropiedad);
            }
            for (Evaluacion evaluacionesEvaluacion : proyecto.getEvaluaciones()) {
                Proyecto oldProyectoOfEvaluacionesEvaluacion = evaluacionesEvaluacion.getProyecto();
                evaluacionesEvaluacion.setProyecto(proyecto);
                evaluacionesEvaluacion = em.merge(evaluacionesEvaluacion);
                if (oldProyectoOfEvaluacionesEvaluacion != null) {
                    oldProyectoOfEvaluacionesEvaluacion.getEvaluaciones().remove(evaluacionesEvaluacion);
                    oldProyectoOfEvaluacionesEvaluacion = em.merge(oldProyectoOfEvaluacionesEvaluacion);
                }
            }
            for (Participacion participacionesParticipacion : proyecto.getParticipaciones()) {
                Proyecto oldProyectoOfParticipacionesParticipacion = participacionesParticipacion.getProyecto();
                participacionesParticipacion.setProyecto(proyecto);
                participacionesParticipacion = em.merge(participacionesParticipacion);
                if (oldProyectoOfParticipacionesParticipacion != null) {
                    oldProyectoOfParticipacionesParticipacion.getParticipaciones().remove(participacionesParticipacion);
                    oldProyectoOfParticipacionesParticipacion = em.merge(oldProyectoOfParticipacionesParticipacion);
                }
            }
            for (Proyecto proyectosComplementariosProyecto : proyecto.getProyectosComplementarios()) {
                Proyecto oldProyectoComplementarioPadreOfProyectosComplementariosProyecto = proyectosComplementariosProyecto.getProyectoComplementarioPadre();
                proyectosComplementariosProyecto.setProyectoComplementarioPadre(proyecto);
                proyectosComplementariosProyecto = em.merge(proyectosComplementariosProyecto);
                if (oldProyectoComplementarioPadreOfProyectosComplementariosProyecto != null) {
                    oldProyectoComplementarioPadreOfProyectosComplementariosProyecto.getProyectosComplementarios().remove(proyectosComplementariosProyecto);
                    oldProyectoComplementarioPadreOfProyectosComplementariosProyecto = em.merge(oldProyectoComplementarioPadreOfProyectosComplementariosProyecto);
                }
            }
            for (Resolucion resolucionesResolucion : proyecto.getResoluciones()) {
                resolucionesResolucion.getProyectos().add(proyecto);
                resolucionesResolucion = em.merge(resolucionesResolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getId());
            ProyectoDatosComplementarios proyectoDatosComplementariosOld = persistentProyecto.getProyectoDatosComplementarios();
            ProyectoDatosComplementarios proyectoDatosComplementariosNew = proyecto.getProyectoDatosComplementarios();
            Presupuesto presupuestoOld = persistentProyecto.getPresupuesto();
            Presupuesto presupuestoNew = proyecto.getPresupuesto();
            Proyecto proyectoComplementarioPadreOld = persistentProyecto.getProyectoComplementarioPadre();
            Proyecto proyectoComplementarioPadreNew = proyecto.getProyectoComplementarioPadre();
            List<Contrato> contratosOld = persistentProyecto.getContratos();
            List<Contrato> contratosNew = proyecto.getContratos();
            List<FormacionRRHH> formacionRRHHsOld = persistentProyecto.getFormacionRRHHs();
            List<FormacionRRHH> formacionRRHHsNew = proyecto.getFormacionRRHHs();
            List<Publicacion> publicacionesOld = persistentProyecto.getPublicaciones();
            List<Publicacion> publicacionesNew = proyecto.getPublicaciones();
            List<Propiedad> propiedadesOld = persistentProyecto.getPropiedades();
            List<Propiedad> propiedadesNew = proyecto.getPropiedades();
            List<Evaluacion> evaluacionesOld = persistentProyecto.getEvaluaciones();
            List<Evaluacion> evaluacionesNew = proyecto.getEvaluaciones();
            List<Participacion> participacionesOld = persistentProyecto.getParticipaciones();
            List<Participacion> participacionesNew = proyecto.getParticipaciones();
            List<Proyecto> proyectosComplementariosOld = persistentProyecto.getProyectosComplementarios();
            List<Proyecto> proyectosComplementariosNew = proyecto.getProyectosComplementarios();
            List<Resolucion> resolucionesOld = persistentProyecto.getResoluciones();
            List<Resolucion> resolucionesNew = proyecto.getResoluciones();
            if (proyectoDatosComplementariosNew != null) {
                proyectoDatosComplementariosNew = em.getReference(proyectoDatosComplementariosNew.getClass(), proyectoDatosComplementariosNew.getId());
                proyecto.setProyectoDatosComplementarios(proyectoDatosComplementariosNew);
            }
            if (presupuestoNew != null) {
                presupuestoNew = em.getReference(presupuestoNew.getClass(), presupuestoNew.getId());
                proyecto.setPresupuesto(presupuestoNew);
            }
            if (proyectoComplementarioPadreNew != null) {
                proyectoComplementarioPadreNew = em.getReference(proyectoComplementarioPadreNew.getClass(), proyectoComplementarioPadreNew.getId());
                proyecto.setProyectoComplementarioPadre(proyectoComplementarioPadreNew);
            }
            List<Contrato> attachedContratosNew = new ArrayList<Contrato>();
            for (Contrato contratosNewContratoToAttach : contratosNew) {
                contratosNewContratoToAttach = em.getReference(contratosNewContratoToAttach.getClass(), contratosNewContratoToAttach.getId());
                attachedContratosNew.add(contratosNewContratoToAttach);
            }
            contratosNew = attachedContratosNew;
            proyecto.setContratos(contratosNew);
            List<FormacionRRHH> attachedFormacionRRHHsNew = new ArrayList<FormacionRRHH>();
            for (FormacionRRHH formacionRRHHsNewFormacionRRHHToAttach : formacionRRHHsNew) {
                formacionRRHHsNewFormacionRRHHToAttach = em.getReference(formacionRRHHsNewFormacionRRHHToAttach.getClass(), formacionRRHHsNewFormacionRRHHToAttach.getId());
                attachedFormacionRRHHsNew.add(formacionRRHHsNewFormacionRRHHToAttach);
            }
            formacionRRHHsNew = attachedFormacionRRHHsNew;
            proyecto.setFormacionRRHHs(formacionRRHHsNew);
            List<Publicacion> attachedPublicacionesNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionesNewPublicacionToAttach : publicacionesNew) {
                publicacionesNewPublicacionToAttach = em.getReference(publicacionesNewPublicacionToAttach.getClass(), publicacionesNewPublicacionToAttach.getId());
                attachedPublicacionesNew.add(publicacionesNewPublicacionToAttach);
            }
            publicacionesNew = attachedPublicacionesNew;
            proyecto.setPublicaciones(publicacionesNew);
            List<Propiedad> attachedPropiedadesNew = new ArrayList<Propiedad>();
            for (Propiedad propiedadesNewPropiedadToAttach : propiedadesNew) {
                propiedadesNewPropiedadToAttach = em.getReference(propiedadesNewPropiedadToAttach.getClass(), propiedadesNewPropiedadToAttach.getId());
                attachedPropiedadesNew.add(propiedadesNewPropiedadToAttach);
            }
            propiedadesNew = attachedPropiedadesNew;
            proyecto.setPropiedades(propiedadesNew);
            List<Evaluacion> attachedEvaluacionesNew = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionesNewEvaluacionToAttach : evaluacionesNew) {
                evaluacionesNewEvaluacionToAttach = em.getReference(evaluacionesNewEvaluacionToAttach.getClass(), evaluacionesNewEvaluacionToAttach.getId());
                attachedEvaluacionesNew.add(evaluacionesNewEvaluacionToAttach);
            }
            evaluacionesNew = attachedEvaluacionesNew;
            proyecto.setEvaluaciones(evaluacionesNew);
            List<Participacion> attachedParticipacionesNew = new ArrayList<Participacion>();
            for (Participacion participacionesNewParticipacionToAttach : participacionesNew) {
                participacionesNewParticipacionToAttach = em.getReference(participacionesNewParticipacionToAttach.getClass(), participacionesNewParticipacionToAttach.getId());
                attachedParticipacionesNew.add(participacionesNewParticipacionToAttach);
            }
            participacionesNew = attachedParticipacionesNew;
            proyecto.setParticipaciones(participacionesNew);
            List<Proyecto> attachedProyectosComplementariosNew = new ArrayList<Proyecto>();
            for (Proyecto proyectosComplementariosNewProyectoToAttach : proyectosComplementariosNew) {
                proyectosComplementariosNewProyectoToAttach = em.getReference(proyectosComplementariosNewProyectoToAttach.getClass(), proyectosComplementariosNewProyectoToAttach.getId());
                attachedProyectosComplementariosNew.add(proyectosComplementariosNewProyectoToAttach);
            }
            proyectosComplementariosNew = attachedProyectosComplementariosNew;
            proyecto.setProyectosComplementarios(proyectosComplementariosNew);
            List<Resolucion> attachedResolucionesNew = new ArrayList<Resolucion>();
            for (Resolucion resolucionesNewResolucionToAttach : resolucionesNew) {
                resolucionesNewResolucionToAttach = em.getReference(resolucionesNewResolucionToAttach.getClass(), resolucionesNewResolucionToAttach.getId());
                attachedResolucionesNew.add(resolucionesNewResolucionToAttach);
            }
            resolucionesNew = attachedResolucionesNew;
            proyecto.setResoluciones(resolucionesNew);
            proyecto = em.merge(proyecto);
            if (proyectoDatosComplementariosOld != null && !proyectoDatosComplementariosOld.equals(proyectoDatosComplementariosNew)) {
                proyectoDatosComplementariosOld.setProyecto(null);
                proyectoDatosComplementariosOld = em.merge(proyectoDatosComplementariosOld);
            }
            if (proyectoDatosComplementariosNew != null && !proyectoDatosComplementariosNew.equals(proyectoDatosComplementariosOld)) {
                Proyecto oldProyectoOfProyectoDatosComplementarios = proyectoDatosComplementariosNew.getProyecto();
                if (oldProyectoOfProyectoDatosComplementarios != null) {
                    oldProyectoOfProyectoDatosComplementarios.setProyectoDatosComplementarios(null);
                    oldProyectoOfProyectoDatosComplementarios = em.merge(oldProyectoOfProyectoDatosComplementarios);
                }
                proyectoDatosComplementariosNew.setProyecto(proyecto);
                proyectoDatosComplementariosNew = em.merge(proyectoDatosComplementariosNew);
            }
            if (presupuestoOld != null && !presupuestoOld.equals(presupuestoNew)) {
                presupuestoOld.setProyecto(null);
                presupuestoOld = em.merge(presupuestoOld);
            }
            if (presupuestoNew != null && !presupuestoNew.equals(presupuestoOld)) {
                Proyecto oldProyectoOfPresupuesto = presupuestoNew.getProyecto();
                if (oldProyectoOfPresupuesto != null) {
                    oldProyectoOfPresupuesto.setPresupuesto(null);
                    oldProyectoOfPresupuesto = em.merge(oldProyectoOfPresupuesto);
                }
                presupuestoNew.setProyecto(proyecto);
                presupuestoNew = em.merge(presupuestoNew);
            }
            if (proyectoComplementarioPadreOld != null && !proyectoComplementarioPadreOld.equals(proyectoComplementarioPadreNew)) {
                proyectoComplementarioPadreOld.setProyectoComplementarioPadre(null);
                proyectoComplementarioPadreOld = em.merge(proyectoComplementarioPadreOld);
            }
            if (proyectoComplementarioPadreNew != null && !proyectoComplementarioPadreNew.equals(proyectoComplementarioPadreOld)) {
                Proyecto oldProyectoComplementarioPadreOfProyectoComplementarioPadre = proyectoComplementarioPadreNew.getProyectoComplementarioPadre();
                if (oldProyectoComplementarioPadreOfProyectoComplementarioPadre != null) {
                    oldProyectoComplementarioPadreOfProyectoComplementarioPadre.setProyectoComplementarioPadre(null);
                    oldProyectoComplementarioPadreOfProyectoComplementarioPadre = em.merge(oldProyectoComplementarioPadreOfProyectoComplementarioPadre);
                }
                proyectoComplementarioPadreNew.setProyectoComplementarioPadre(proyecto);
                proyectoComplementarioPadreNew = em.merge(proyectoComplementarioPadreNew);
            }
            for (Contrato contratosOldContrato : contratosOld) {
                if (!contratosNew.contains(contratosOldContrato)) {
                    contratosOldContrato.getProyectos().remove(proyecto);
                    contratosOldContrato = em.merge(contratosOldContrato);
                }
            }
            for (Contrato contratosNewContrato : contratosNew) {
                if (!contratosOld.contains(contratosNewContrato)) {
                    contratosNewContrato.getProyectos().add(proyecto);
                    contratosNewContrato = em.merge(contratosNewContrato);
                }
            }
            for (FormacionRRHH formacionRRHHsOldFormacionRRHH : formacionRRHHsOld) {
                if (!formacionRRHHsNew.contains(formacionRRHHsOldFormacionRRHH)) {
                    formacionRRHHsOldFormacionRRHH.setProyecto(null);
                    formacionRRHHsOldFormacionRRHH = em.merge(formacionRRHHsOldFormacionRRHH);
                }
            }
            for (FormacionRRHH formacionRRHHsNewFormacionRRHH : formacionRRHHsNew) {
                if (!formacionRRHHsOld.contains(formacionRRHHsNewFormacionRRHH)) {
                    Proyecto oldProyectoOfFormacionRRHHsNewFormacionRRHH = formacionRRHHsNewFormacionRRHH.getProyecto();
                    formacionRRHHsNewFormacionRRHH.setProyecto(proyecto);
                    formacionRRHHsNewFormacionRRHH = em.merge(formacionRRHHsNewFormacionRRHH);
                    if (oldProyectoOfFormacionRRHHsNewFormacionRRHH != null && !oldProyectoOfFormacionRRHHsNewFormacionRRHH.equals(proyecto)) {
                        oldProyectoOfFormacionRRHHsNewFormacionRRHH.getFormacionRRHHs().remove(formacionRRHHsNewFormacionRRHH);
                        oldProyectoOfFormacionRRHHsNewFormacionRRHH = em.merge(oldProyectoOfFormacionRRHHsNewFormacionRRHH);
                    }
                }
            }
            for (Publicacion publicacionesOldPublicacion : publicacionesOld) {
                if (!publicacionesNew.contains(publicacionesOldPublicacion)) {
                    publicacionesOldPublicacion.getProyectos().remove(proyecto);
                    publicacionesOldPublicacion = em.merge(publicacionesOldPublicacion);
                }
            }
            for (Publicacion publicacionesNewPublicacion : publicacionesNew) {
                if (!publicacionesOld.contains(publicacionesNewPublicacion)) {
                    publicacionesNewPublicacion.getProyectos().add(proyecto);
                    publicacionesNewPublicacion = em.merge(publicacionesNewPublicacion);
                }
            }
            for (Propiedad propiedadesOldPropiedad : propiedadesOld) {
                if (!propiedadesNew.contains(propiedadesOldPropiedad)) {
                    propiedadesOldPropiedad.getProyectos().remove(proyecto);
                    propiedadesOldPropiedad = em.merge(propiedadesOldPropiedad);
                }
            }
            for (Propiedad propiedadesNewPropiedad : propiedadesNew) {
                if (!propiedadesOld.contains(propiedadesNewPropiedad)) {
                    propiedadesNewPropiedad.getProyectos().add(proyecto);
                    propiedadesNewPropiedad = em.merge(propiedadesNewPropiedad);
                }
            }
            for (Evaluacion evaluacionesOldEvaluacion : evaluacionesOld) {
                if (!evaluacionesNew.contains(evaluacionesOldEvaluacion)) {
                    evaluacionesOldEvaluacion.setProyecto(null);
                    evaluacionesOldEvaluacion = em.merge(evaluacionesOldEvaluacion);
                }
            }
            for (Evaluacion evaluacionesNewEvaluacion : evaluacionesNew) {
                if (!evaluacionesOld.contains(evaluacionesNewEvaluacion)) {
                    Proyecto oldProyectoOfEvaluacionesNewEvaluacion = evaluacionesNewEvaluacion.getProyecto();
                    evaluacionesNewEvaluacion.setProyecto(proyecto);
                    evaluacionesNewEvaluacion = em.merge(evaluacionesNewEvaluacion);
                    if (oldProyectoOfEvaluacionesNewEvaluacion != null && !oldProyectoOfEvaluacionesNewEvaluacion.equals(proyecto)) {
                        oldProyectoOfEvaluacionesNewEvaluacion.getEvaluaciones().remove(evaluacionesNewEvaluacion);
                        oldProyectoOfEvaluacionesNewEvaluacion = em.merge(oldProyectoOfEvaluacionesNewEvaluacion);
                    }
                }
            }
            for (Participacion participacionesOldParticipacion : participacionesOld) {
                if (!participacionesNew.contains(participacionesOldParticipacion)) {
                    participacionesOldParticipacion.setProyecto(null);
                    participacionesOldParticipacion = em.merge(participacionesOldParticipacion);
                }
            }
            for (Participacion participacionesNewParticipacion : participacionesNew) {
                if (!participacionesOld.contains(participacionesNewParticipacion)) {
                    Proyecto oldProyectoOfParticipacionesNewParticipacion = participacionesNewParticipacion.getProyecto();
                    participacionesNewParticipacion.setProyecto(proyecto);
                    participacionesNewParticipacion = em.merge(participacionesNewParticipacion);
                    if (oldProyectoOfParticipacionesNewParticipacion != null && !oldProyectoOfParticipacionesNewParticipacion.equals(proyecto)) {
                        oldProyectoOfParticipacionesNewParticipacion.getParticipaciones().remove(participacionesNewParticipacion);
                        oldProyectoOfParticipacionesNewParticipacion = em.merge(oldProyectoOfParticipacionesNewParticipacion);
                    }
                }
            }
            for (Proyecto proyectosComplementariosOldProyecto : proyectosComplementariosOld) {
                if (!proyectosComplementariosNew.contains(proyectosComplementariosOldProyecto)) {
                    proyectosComplementariosOldProyecto.setProyectoComplementarioPadre(null);
                    proyectosComplementariosOldProyecto = em.merge(proyectosComplementariosOldProyecto);
                }
            }
            for (Proyecto proyectosComplementariosNewProyecto : proyectosComplementariosNew) {
                if (!proyectosComplementariosOld.contains(proyectosComplementariosNewProyecto)) {
                    Proyecto oldProyectoComplementarioPadreOfProyectosComplementariosNewProyecto = proyectosComplementariosNewProyecto.getProyectoComplementarioPadre();
                    proyectosComplementariosNewProyecto.setProyectoComplementarioPadre(proyecto);
                    proyectosComplementariosNewProyecto = em.merge(proyectosComplementariosNewProyecto);
                    if (oldProyectoComplementarioPadreOfProyectosComplementariosNewProyecto != null && !oldProyectoComplementarioPadreOfProyectosComplementariosNewProyecto.equals(proyecto)) {
                        oldProyectoComplementarioPadreOfProyectosComplementariosNewProyecto.getProyectosComplementarios().remove(proyectosComplementariosNewProyecto);
                        oldProyectoComplementarioPadreOfProyectosComplementariosNewProyecto = em.merge(oldProyectoComplementarioPadreOfProyectosComplementariosNewProyecto);
                    }
                }
            }
            for (Resolucion resolucionesOldResolucion : resolucionesOld) {
                if (!resolucionesNew.contains(resolucionesOldResolucion)) {
                    resolucionesOldResolucion.getProyectos().remove(proyecto);
                    resolucionesOldResolucion = em.merge(resolucionesOldResolucion);
                }
            }
            for (Resolucion resolucionesNewResolucion : resolucionesNew) {
                if (!resolucionesOld.contains(resolucionesNewResolucion)) {
                    resolucionesNewResolucion.getProyectos().add(proyecto);
                    resolucionesNewResolucion = em.merge(resolucionesNewResolucion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proyecto.getId();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            ProyectoDatosComplementarios proyectoDatosComplementarios = proyecto.getProyectoDatosComplementarios();
            if (proyectoDatosComplementarios != null) {
                proyectoDatosComplementarios.setProyecto(null);
                proyectoDatosComplementarios = em.merge(proyectoDatosComplementarios);
            }
            Presupuesto presupuesto = proyecto.getPresupuesto();
            if (presupuesto != null) {
                presupuesto.setProyecto(null);
                presupuesto = em.merge(presupuesto);
            }
            Proyecto proyectoComplementarioPadre = proyecto.getProyectoComplementarioPadre();
            if (proyectoComplementarioPadre != null) {
                proyectoComplementarioPadre.setProyectoComplementarioPadre(null);
                proyectoComplementarioPadre = em.merge(proyectoComplementarioPadre);
            }
            List<Contrato> contratos = proyecto.getContratos();
            for (Contrato contratosContrato : contratos) {
                contratosContrato.getProyectos().remove(proyecto);
                contratosContrato = em.merge(contratosContrato);
            }
            List<FormacionRRHH> formacionRRHHs = proyecto.getFormacionRRHHs();
            for (FormacionRRHH formacionRRHHsFormacionRRHH : formacionRRHHs) {
                formacionRRHHsFormacionRRHH.setProyecto(null);
                formacionRRHHsFormacionRRHH = em.merge(formacionRRHHsFormacionRRHH);
            }
            List<Publicacion> publicaciones = proyecto.getPublicaciones();
            for (Publicacion publicacionesPublicacion : publicaciones) {
                publicacionesPublicacion.getProyectos().remove(proyecto);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
            }
            List<Propiedad> propiedades = proyecto.getPropiedades();
            for (Propiedad propiedadesPropiedad : propiedades) {
                propiedadesPropiedad.getProyectos().remove(proyecto);
                propiedadesPropiedad = em.merge(propiedadesPropiedad);
            }
            List<Evaluacion> evaluaciones = proyecto.getEvaluaciones();
            for (Evaluacion evaluacionesEvaluacion : evaluaciones) {
                evaluacionesEvaluacion.setProyecto(null);
                evaluacionesEvaluacion = em.merge(evaluacionesEvaluacion);
            }
            List<Participacion> participaciones = proyecto.getParticipaciones();
            for (Participacion participacionesParticipacion : participaciones) {
                participacionesParticipacion.setProyecto(null);
                participacionesParticipacion = em.merge(participacionesParticipacion);
            }
            List<Proyecto> proyectosComplementarios = proyecto.getProyectosComplementarios();
            for (Proyecto proyectosComplementariosProyecto : proyectosComplementarios) {
                proyectosComplementariosProyecto.setProyectoComplementarioPadre(null);
                proyectosComplementariosProyecto = em.merge(proyectosComplementariosProyecto);
            }
            List<Resolucion> resoluciones = proyecto.getResoluciones();
            for (Resolucion resolucionesResolucion : resoluciones) {
                resolucionesResolucion.getProyectos().remove(proyecto);
                resolucionesResolucion = em.merge(resolucionesResolucion);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Proyecto findProyecto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
