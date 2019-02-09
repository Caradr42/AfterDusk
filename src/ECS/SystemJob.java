/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS;

/**
 *
 * @author carlo
 */
public abstract class SystemJob {
    
    protected EntityManager entityManager;

    public SystemJob(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
       
    public void sethEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
    abstract public void Update();
}
