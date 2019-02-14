/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS;

/**
 * The Entity Factory is used to create entities that belong to a prefab.
 * That means a copy of an instance of a subclass of component such that the new 
 * entity has the same data as the entity from which the components are copied.
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 14/02/2019
 * @version 1.0
 */
public class EntityFactory {
    EntityManager entityManager;

    public EntityFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
}
