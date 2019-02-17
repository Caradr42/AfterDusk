package ECS;

import java.lang.System;
import ECS.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Entity Component System class.
 * The EntityManager provides the structures to attach Components to Entities 
 * and as such also allow Systems to access this Entities and their respective
 * Component instances. 
 * EntityManager can be instantiated and as such we can have different groups 
 * of Entities.
 * Entity creation must be handled by their respective EntityManager and never 
 * by their own constructor.
 *  
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class EntityManager{ 
    
    /**
     * List of all the Entities that this EntityManager manages.
     */
    private ArrayList<Entity> entities;
    
    //EntityManager id 
   // private int id;
    
    /**
     * Structure containing all the Entities by their Component and also their
     * specific instance of their component.
     * A HashMap containing a HashMap of Components. 
     * The first HashMap uses as KEY the name of the different component classes.
     * The inner HashMaps use the Entity id as KEY
     * 
     * Think of it as a collection of tables like the one bellow.
     * +----------------+-------+-------+---------+---------+---------+-----------+
     * |    Entity_id   | vel_x | vel_y | accel_x | accel_y | max_vel | max_accel |
     * +----------------+-------+-------+---------+---------+---------+-----------+
     * |  1             | 3     | 4     | 0       | 1       | 5       | 5         |
     * +----------------+-------+-------+---------+---------+---------+-----------+
     * 
     * But were we can use the Component class name as a KEY.
     * 
     * the expression:
     *  ? extends Component
     * means that it expects a sub class of component
     */
    private HashMap<Class, HashMap<Integer, ? extends Component>> componentsDictionary;
    
    /**
     * the smallest id yet to be assigned to an Entity
     */
    int lowestUnasignedID;
    
    /**
     * EntityManager Constructor
     * initializes the list and the HashMap
     * @param id
     */
    public EntityManager() {
        entities = new  ArrayList<>();
        componentsDictionary = new HashMap<>();
        lowestUnasignedID = Integer.MIN_VALUE;
        //We use tha smalles Integer as the first id to be assignedd.
    }  
    
    /**
     * Creates a new Entity and returns a reference to it.
     * It also adds the entity to the list of entities in this EntityManager
     * As such, no all entities managed by this EntityManager are required to
     * have components attached.
     * 
     * @return a new Entity.
     */
    public Entity createEntity(){
        int id = GenerateNewID(); 
        Entity createdEnt = new Entity(id); //assigns a unique id to the created entity
        entities.add(createdEnt); //Adds the created entity to the entities list
        return createdEnt;
    }
    
    /**
     * Creates a new Entity and returns a reference to it.
     * It also adds the entity to the list of entities in this EntityManager
     * As such, no all entities managed by this EntityManager are required to
     * have components attached.
     * 
     * @param name the name to be assigned to the entity. 
     * @return  a new Entity.
     */
    public Entity createEntity(String name){
        int id = GenerateNewID();
        Entity createdEnt = new Entity(id, name);
        entities.add(createdEnt);
        return createdEnt;
    }
    
    /**
     * Generates a new id to be used by an entity.
     * Ids are local to an instance of an entity manager
     * @return 
     */
    private int GenerateNewID(){
        if(lowestUnasignedID < Integer.MAX_VALUE){
            return lowestUnasignedID++;
        }/*else{
            //TO DO: if there are no more IDs available, search for unassigned ids in the entities list
        }*/
        throw new ArrayIndexOutOfBoundsException("No more unique Entity IDs available beyond: " + Integer.MAX_VALUE);
    }
    
    /**
     * removes the entered Entity from this EntityManager, as such the entity is
     * removed from the entities list and the componentsDictionary along with
     * its attached components.
     * Returns the removed entity if it was able to remove it.
     * If no it returns null as the entity was not in the EntityManager.
     * @param ent the entity to be removed.
     * @return Returns the removed entity if it was able to remove it. If not it
     * returns null.
     */
    public Entity removeEntity(Entity ent){
        //for that iterates for each key of the upper HashMap
        for (Map.Entry pair : componentsDictionary.entrySet()) {
            //the inner HashMap contained at the current KEY of the upper HashMap
            HashMap<Integer, ? extends Component > componentsMap = (HashMap<Integer,  ? extends Component>)pair.getValue(); 
            //for that iterates for each key of the inner HashMap
            for (Map.Entry entryPair : componentsMap.entrySet()) {
                if((Integer)entryPair.getKey() == ent.getID()) //if the KEY (int) is equal to the id of the searched Entity
                {
                    componentsMap.remove((Integer)entryPair.getKey());
                    entities.remove(ent);
                    return  ent;
                }
            }
        }
        return null; //if the entity was not found
    }
        
    /**
     * Adds a specific instance of a component to the Entity ID (Integer) entered.
     * 
     * This function uses Generics to be able to get the class name of the
     * component, so it can use it as key of the upper HashMap.
     * 
     * @param <T> the type of a sub class of Component, of the component to be 
     * attached. 
     * @param entity the entity id as integer
     * @param component The specific instance of a sub class of Component, to be 
     * attached to the entity
     */
    public <T extends Component> void addComponetToEntity(Integer entity, T component) {
            //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component class as a KEY.
            HashMap<Integer, ? extends Component > store = componentsDictionary.get(component.getClass()); 
            
            if(store == null) {//if the component does not exists in the upper HashMap.
                store = new HashMap<Integer, T>();
                componentsDictionary.put(component.getClass(), store);
                //adds a new HashMap to the upper HashMap usinf the component class as a KEY.
            }
            //cast
            ((HashMap<Integer, T>) store).put(entity, component);
            //Stores the (component, Entity key) pair in the map.  
    }
    
    /**
     * Adds a specific instance of a component to the Entity entered;
     * 
     * This function uses Generics to be able to get the class name of the
     * component, so it can use it as key of the upper HashMap.
     * 
     * @param <T> the type of a sub class of Component, of the component to be 
     * attached. 
     * @param entity the entity id as integer
     * @param component The specific instance of a sub class of Component, to be 
     * attached to the entity 
     */
    public <T extends Component> void addComponetToEntity(Entity entity, T component) { 
        addComponetToEntity(entity.getID(), component);
    }
     
    /**
     * Returns a reference to the specific instance of the sub class of 
     * Component that is attached to the specific Entity.
     * 
     * This function uses Generics to be able to get the class name of the
     * component, so it can use it as key of the upper HashMap.
     * 
     * Gets the specific instance of the component attached to the entity
     * @param <T> the type of a sub class of Component, of the component to be 
     * retrieved.  
     * @param entity The entity from which the component will be searched an 
     * retrieved.
     * @param component the <b>Class</b> of the component to be retrieved.
     * @return 
     */
    public <T> T getEntityComponentInstance(Integer entity, Class<T> component){
        //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
        HashMap<Integer, ? extends Component> store = componentsDictionary.get(component);
        T resultComponet = (T) store.get(entity); //uses the Entity's ID as KEY to get the component instance.

        if(resultComponet == null) //trows an exeption if no component is attached.
            throw new IllegalArgumentException("Get Fail: "+entity.toString()+" does not posses Component of Class \n missing: "+ component);
        return resultComponet;
    }
    
    /**
     * Returns a list containing all the Entities which have a component of the
     * introduced  sub class of Component. Use getClass().
     * If there are no classes with such component the method returns an empty 
     * list.
     * 
     * @param component The <b>Class</b> of the <b>Component</b> to be searched.
     * @return an <b>ArrayList<Entity></b> with a reference to the entities that
     * have the searched component attached.
     */
    public ArrayList<Integer> getAllEntitiesPosessingComponentOfClass(Class component){
        //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
        HashMap<Integer, ? extends Component > componentsMap = componentsDictionary.get(component); 
        
        if(!componentsMap.isEmpty()){
            //saves in a list the IDs of the entities found to have the component.
            /*ArrayList<Integer> entitiesIDs = new ArrayList<>();    
            componentsMap.entrySet().stream().forEach((pair) -> {
                entitiesIDs.add(pair.getKey());
            });*/
            //finds entities in the entities list by their keys and returns a list of them.
            return new ArrayList<>(componentsMap.keySet());   
        }else{
            return new ArrayList<>(); // Returns an empty list, if there are no classes with such component.
        }     
    }
    
    /**
     * Returns a list containing all the Entities which have a component of the
     * introduced sub class of Component.
     * If there are no classes with such component the method returns an empty 
     * list.
     * 
     * @param <T> the type of a sub class of Component, of the component to be 
     * searched. 
     * @param component The sub class of <b>Component</b> to be searched.
     * @return an <b>ArrayList<Entity></b> with a reference to the entities that
     * have the searched component attached. 
     */
   /* public <T> ArrayList<Entity> getAllEntitiesPosessingComponentOfClass(T component){
        return getAllEntitiesPosessingComponentOfClass(component.getClass());
    }*/
    
    /**
     * get entities containing exclusively all the listed components classes in the parameters
     * @param componentsClass
     * @return 
     */
    public ArrayList<Integer> getEntitiesWithComponents(Class ... componentsClass){ //TODO
        HashSet<Integer> entitiesSet = new HashSet<>(getAllEntitiesPosessingComponentOfClass(componentsClass[0]));
        for(Class component : componentsClass){
            //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
            HashMap<Integer, ? extends Component > componentsMap = componentsDictionary.get(component); 
            if(!componentsMap.isEmpty()){
                //union of sets
                entitiesSet.retainAll(new HashSet<>(getAllEntitiesPosessingComponentOfClass(component)) );
            }else{//if one of the components is not assigned to any entity at all
                return new ArrayList<>();// Returns an empty list, if there are no classes with such component.
            }
        }
        return new ArrayList<>(entitiesSet); //Returns a list with the surviving entities of the union of sets
    }
    
    public <T> HashMap<Integer, ? extends Component> getComponentMap(Class<T> component){
        //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
        HashMap<Integer, ? extends Component> componentMap = componentsDictionary.get((Class)component);
        if(componentMap == null) //trows an exeption if no component is attached.
            throw new IllegalArgumentException("Get Fail: "+ componentsDictionary.toString()+" does not posses Component of Class \n missing: "+ component);
        return componentMap;
    }
    
    /**
     * Return true if the entered Entity has the component attached
     * @param <T> the type of a sub class of Component, of the component to be 
     * searched. 
     * @param entity The entity were the component will be searched.
     * @param component the <b>Class</b> of the sub class of Component to be
     * searched.
     * @return true if the entered Entity has the component attached
     */
    public <T> boolean hasComponent(Entity entity, Class<T> component){
        HashMap<Integer, ? extends Component> store = componentsDictionary.get(component);
        T resultComponet = (T) store.get(entity.id);
        return resultComponet != null;
    }
    
    public <T> void removeComponentFormEntity(Entity entity, Class<T> component){
        //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
        HashMap<Integer, ? extends Component> componentMap = componentsDictionary.get((Class)component);
        try{
            componentMap.remove((Integer)entity.getID()); 
        }catch(Exception e){
            System.err.println(e + "Tried to remove unexisting component from entity: " + entity.getID() + " : " + entity.getName());
        }            
    }
        
    /**
     * Prints the entire HashMap of components that this EntityManager has. the
     * components Dictionary.
     */
    public void printComponentsMap(){
        
        for (Map.Entry pair : componentsDictionary.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
            HashMap<Integer, ? extends Component > componentsMap = (HashMap<Integer,  ? extends Component>)pair.getValue(); 
            for (Map.Entry entryPair : componentsMap.entrySet()) {
                System.out.println("\t" + entryPair.getKey() + " = " + entryPair.getValue());
            }
        }    
    }
    
    /**
     * Prints the entities list from this EntityManagers, by their Id and Name.
     */
    public void printEntities(){
        for(Entity e : entities ){
           System.out.println(e.getID() + " : " + e.getName());
        }
    }
    
    /**
     * Utility function to print maps.
     * @param mp  the map to print
     */
    private void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }
    
    /**
     * Returns the specific entity in this EntityManager that has the entered ID
     * @param id the id of the Entity to be searched.
     * @return the Entity of ID id
     */
    public Entity getEntityByID(int id){
        for(Entity e : entities ){
            if(e.getID() == id)
                return  e;
        }
        return null;
    }
    
    /**
     * Returns a list of Entities in this EntityManager that have the IDs in 
     * the Integers list entered.
     *
     * @param ids An Integer List containing the IDs to be used to search
     * @return Returns a list of Entities with the IDs in ids.
     */
    public ArrayList<Entity> getEntitiesByIDs(ArrayList<Integer> ids){
        ArrayList<Entity> entitiesFound = new  ArrayList<>();
        for(Integer i : ids){ 
            for(Entity e : entities ){
                if(e.getID() == i)
                entitiesFound.add(e);
            }
        }
        return entitiesFound;
    }
}
    
