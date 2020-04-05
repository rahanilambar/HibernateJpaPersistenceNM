package com.example.servlet;

import com.example.model.User;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nilambar
 */
public class ServletWithPersistence extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
        EntityManager entitymanager = emf.createEntityManager();
        EntityTransaction tx = entitymanager.getTransaction();
        try{
            tx.begin();
            
            User user = new User("sam");
            entitymanager.persist(user);
            
            tx.commit();
        }catch(Exception ex){
            if(tx != null){
                tx.rollback();
            }
        }finally{
            entitymanager.close();
        }
    }
}
