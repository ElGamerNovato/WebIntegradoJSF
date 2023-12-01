package com.ManagedBean;

import com.Controlador.UsuarioJpaController;
import com.Entidades.Usuario;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Named
@ViewScoped
public class beanUsuario implements Serializable {

    private Usuario user = new Usuario();
    private UsuarioJpaController ujc = new UsuarioJpaController();
    private String users;
    private String clave;
    private String mensaje;

    public String getUsers()                { return users; }
    public void setUsers(String users)      { this.users = users; }
    public String getClave()                { return clave; }
    public void setClave(String clave)      { this.clave = clave; }
    public String getMensaje()              { return mensaje; }
    public void setMensaje(String mensaje)  { this.mensaje = mensaje; }

    public String login() {

        try {
            
            EntityManager em = ujc.getEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.users = :users AND u.clave = :clave");
            query.setParameter("users", users);
            query.setParameter("clave", clave);
            Usuario resultado = (Usuario) query.getSingleResult();
            
            if (resultado != null) {
                return "index?faces-redirect=true";
            } 
            
        } catch (NoResultException e) {
            // Credenciales incorrectas
            mensaje = "Credenciales incorrectas";
            users = null;
            clave = null;
            return null;
        } catch (Exception e) {
            // Manejar otras excepciones si es necesario
            mensaje = "Error en el proceso de inicio de sesión";
            return null;
        }
        return null;
        
    }
}
