package ECS;

/**
 * The Entity Factory is used to create entities that belong to a prefab.
 * That means a copy of an instance of a subclass of component such that the new 
 * entity has the same data as the entity from which the components are copied.
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 14/02/2019
 * @version 1.0
 */
public class EntityFactory {
    EntityManager entityManager;
    
    /**
     *EntityFactory constructor
     * 
     * @param entityManager
     */
    public EntityFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
}
