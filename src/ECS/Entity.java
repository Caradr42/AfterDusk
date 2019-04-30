package ECS;

import java.io.Serializable;

/**
 * Entity Component System class.
 * Entities are objects that only contain an identification key, their id.
 * The id is used to attach components to the Entity, but this is handled by the
 * EntityManager as Entities are not supposed to contain any additional data.
 * For the purpose of the game engine, a name can also be added to the Entity, 
 * although the name such never be used as means to refer to the Entity, thats 
 * thats  what the id is for.
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class Entity implements Serializable{
    protected int id;
    protected String name;
    
    /**
     * Entity constructor
     * Only the Entity manager is allowed to use it to create new entities as it
     * is the one to assign them an id.
     * @param id The id to be assigned to this new Entity
     */
    public Entity(int id) {
        this.id = id;
        name = new String("");
    }
    
    /**
     * Entity constructor
     * Only the Entity manager is allowed to use it to create new entities as it
     * is the one to assign them an id.
     * @param id The id to be assigned to this new Entity
     * @param name The name to be assigned to the Entity.
     */
    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * get the id of the Entity
     * @return The id of the Entity, an integer
     */
    public int getID(){
        return id;
    }
    
    /**
     * get the name of the Entity
     * @return the name of the Entity
     */
    public String getName(){
        return name;
    }
    
    /**
     * set the name of the Entity
     * @param name a String name
     */
    public void setName(String name){
        this.name = name;
    }
}
