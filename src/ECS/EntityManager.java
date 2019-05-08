package ECS;

import DataBaseConnection.DataBaseSystem;
import java.lang.System;
import ECS.*;
import ECS.Components.Tile;
import ECS.Components.Tool;
import ECS.Components.Transform;
import ECS.Components.WorldEntity;
import Signals.Signal;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.io.IOException;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
 * @date 26/02/2019
 * @version 1.3
 */
public class EntityManager implements Serializable{ 
    
    // List of all the Entities that this EntityManager manages.
    private ArrayList<Entity> entities;
    //Asociates entities to be deleted to the systems where this entityes recide so we can asincromically delete them
    private LinkedList<Integer> deletionQueue; 
    
    //public Signal<ArrayList<Integer>> removeEntitiesSignal;
    //public Signal<ArrayList<Integer>> addEntitiesSignal;
    
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
     * Map that assigns an archetype for each entity
     */
    public HashMap<Integer, HashSet<Class>> entitiesArchetypeMap;
    //public HashMap<Integer, ArrayList<HashSet<Class> >> entitiesArchetypeMap;
    
    /**
     * Data structure that holds lists of entities with the components in the 
     * Archetype set. This is so any system can use this lists to iterate the entities
     * The list are defined at wold initialization
     * And are only altered with deletion or instantiation of new entities
     * TODO: be able to create archetypes to be added as keys to this map
     * TODO: ADD and remove entities from the map
     * TODO: 
     */
    public HashMap<HashSet<Class>, ArrayList<Integer>> archetypesMap;
    
    //the smallest id yet to be assigned to an Entity
    volatile int lowestUnasignedID;
    
    /**
     * EntityManager Constructor
     * initializes the list and the HashMap
     */
    public EntityManager() {
        entities = new  ArrayList<>();
        componentsDictionary = new HashMap<>();
        lowestUnasignedID = Integer.MIN_VALUE; //We use tha smalles Integer as the first id to be assignedd.
        //removeEntitiesSignal = new Signal<>();
        //addEntitiesSignal = new  Signal<>();
        deletionQueue = new LinkedList<>();
        
        archetypesMap = new HashMap<>();
        entitiesArchetypeMap = new HashMap<>();
        //archetypesMap.putIfAbsent(null, null);
    }  
    
    /**
     * Creates a new Entity and returns a reference to it.
     * It also adds the entity to the list of entities in this EntityManager
     * As such, not all entities managed by this EntityManager are required to
     * have components attached.
     * 
     * @return a new Entity.
     */
    private synchronized Entity createEntity(){
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
    private synchronized Entity createEntity(String name){
        int id = GenerateNewID();
        Entity createdEnt = new Entity(id, name);
        entities.add(createdEnt);
        return createdEnt;
    }
    
    /**
     * Creates a new Entity that has the following components.
     * @param <T> some component child
     * @param name the name of the created Entity
     * @param components the list of components to attach to the entity
     * @return returns the newly created entity
     */
    public synchronized <T extends Component> Entity createEntityWithComponents(String name, T ...components){
        Entity createdEnt = createEntity(name); //assigns a unique id to the created entity
        
        //if(name.equals("Player")) System.out.println("Player");

        //creates the set of classes with the components of the entities. also known as archetype
        HashSet<Class> thisEntityArchetype = new HashSet<>();
        for(T comp: components){
            thisEntityArchetype.add(comp.getClass());
        }
        
        //adds the components to the newly created entity
        for(Component c : components){
            addComponetToEntity(createdEnt.getID(), c);
        } 
        
        //adds the archetype to the entity
        entitiesArchetypeMap.put(createdEnt.getID(), thisEntityArchetype);
        
        //TODO:  after systems initialization, search for alredy existing archetipes (creted by the systems init()) that are sdubsets of the thisEntityArchetype and add the entity to them
        
        /*
        //if the archetypes map alredy has the given archetype as an entry
        if(archetypesMap.containsKey(thisEntityArchetype)){
            archetypesMap.get(thisEntityArchetype).add(createdEnt.getID());
        }else{
            archetypesMap.put(thisEntityArchetype, new ArrayList<>(Arrays.asList(createdEnt.getID())));
        }*/
        
        /*if(name.equals("grassSide")){
            System.out.println("grass side{ ");
            System.out.println("\tID: " + createdEnt.getID());
            Transform tr = getEntityComponentInstance(createdEnt.getID(), new Transform().getClass());
            System.out.println("\tParent ID: " + tr.parent);
            System.out.println("}");
        }*/
          
        return createdEnt;
    } 
    
    
    /**
     * Generates a new id to be used by an entity.
     * Ids are local to an instance of an entity manager
     * The id 0 is reserved as a NULL id
     * @return 
     */
    private synchronized int GenerateNewID(){
        if(lowestUnasignedID < Integer.MAX_VALUE){
            if(lowestUnasignedID == 0){
                lowestUnasignedID++;
            }
            return lowestUnasignedID++;
        }/*else{
            //TO DO: if there are no more IDs available, search for unassigned ids in the entities list
        }*/
        throw new ArrayIndexOutOfBoundsException("No more unique Entity IDs available beyond: " + Integer.MAX_VALUE);
    }
        
    /**
     * removes an entity from the entity manager
     * This can be called from a system
     * @param e 
     */
    public synchronized void removeEntity(int e){
        deletionQueue.add(e);
    }
    
    /**
     * removes all queued entities
     * Executed at the main thread after update(tick) and render
     */
    public synchronized  void flushRemoveEntityQueue(){
        
        //for that iterates for each key of the upper HashMap of the components diccionary
        for (Map.Entry pair : componentsDictionary.entrySet()) {
            //the inner HashMap contained at the current KEY of the upper HashMap
            HashMap<Integer, ? extends Component > componentsMap = (HashMap<Integer,  ? extends Component>)pair.getValue();
            for(int ent  : deletionQueue){
                componentsMap.remove(ent);
            }
        }
        entities.removeAll(deletionQueue);
        
        
        //removes each entity from the arquetipe map
        for (Map.Entry pair : archetypesMap.entrySet()) {
            HashSet<Class> subset = (HashSet<Class>)pair.getKey();
            for(Integer ent  : deletionQueue){
                if(entitiesArchetypeMap.get(ent).containsAll(subset)){
                    ArrayList<Integer> eList = (ArrayList<Integer>)pair.getValue();
                    eList.remove(ent);
                }
            }
        }
        
        for(Integer ent  : deletionQueue){
            entitiesArchetypeMap.remove(ent);
        }
        
        /*if(!deletionQueue.isEmpty()){
            removeEntitiesSignal.dispatch( new ArrayList<Integer>(deletionQueue));
        }*/
        deletionQueue = new LinkedList<>();
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
    public synchronized <T extends Component> void addComponetToEntity(Integer entity, T component) {
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
    public synchronized <T extends Component> void addComponetToEntity(Entity entity, T component) { 
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
     * @return the searched component instance
     */
    public synchronized <T> T getEntityComponentInstance(Integer entity, Class<T> component){
        if(entity == 0) return null;
        
        //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
        HashMap<Integer, ? extends Component> store = componentsDictionary.get(component);
        //if(component.equals(Tool.class)){
           // System.err.println("Ent: " + entity + " : " + component);
        //}
        T resultComponet = (T) store.get(entity); //uses the Entity's ID as KEY to get the component instance.

        if(resultComponet == null) //trows an exeption if no component is attached.
            return null; //no component was found
        
        return resultComponet;
    }
    
    /**
     * Returns a reference to the specific instance of the sub class of 
     * Component that is attached to the specific Entity.
     *  @param <T> the type of a sub class of Component, of the component to be 
     * retrieved.  
     * @param entity The entity from which the component will be searched an 
     * retrieved.
     * @param component the <b>Class</b> of the component to be retrieved.
     * @return the searched component instance
     */
    public synchronized <T> T getEntityComponentInstance(Entity entity, Class<T> component){               
        return getEntityComponentInstance(entity.getID(), component);
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
    private synchronized ArrayList<Integer> getAllEntitiesPosessingComponentOfClass(Class component){
        //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
        HashMap<Integer, ? extends Component > componentsMap = componentsDictionary.get(component); 
        
        if(componentsMap == null) return new ArrayList<>();   
        
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
     * get entities containing exclusively all the listed components classes in the parameters
     * @param <T>
     * @param componentsClass
     * @return 
     */
    public synchronized ArrayList<Integer> getEntitiesWithComponents(Class ... componentsClass){ //TODO
        HashSet<Integer> entitiesSet = new HashSet<>(getAllEntitiesPosessingComponentOfClass(componentsClass[0]));
        
        //TODO: anly do this at init() for better performance
        
        //creates the set of classes with the components of the entities
        HashSet<Class> classesSet = new HashSet<>();
        for(Class comp: componentsClass){
            classesSet.add(comp);
        }
        
        //if the archetypes map alredy has does not have the given archetype as an entry, the archetipe is added,
        //This is so we can acces it 

        if(!archetypesMap.containsKey(classesSet)){
            //System.out.println("does not has key: " + classesSet);
            archetypesMap.put(classesSet, new ArrayList<>(Arrays.asList()));  
            //searches the e_a map for entities with the components that the ssystem checks
            for(Map.Entry entryPair : entitiesArchetypeMap.entrySet()){
                HashSet<Class> entityArchetype = (HashSet<Class>)entryPair.getValue();
                if(entityArchetype.containsAll(classesSet)){
                    archetypesMap.get(classesSet).add((Integer)entryPair.getKey());
                }
            }
        }
        
        return archetypesMap.get(classesSet);
        
        ///TODO: USE efficient fetch by using the archetypeMap
        //fetch from the componentsDictionary the entities by appling a union of sets
        /*for(Class component : componentsClass){
            //gets the inner HashMap contained at the current KEY of the upper HashMap. using the component Class as a KEY.
            HashMap<Integer, ? extends Component > componentsMap = componentsDictionary.get(component); 
            
            if(componentsMap == null) return new ArrayList<>();
            
            if(!componentsMap.isEmpty()){
                //union of sets
                entitiesSet.retainAll(new HashSet<>(getAllEntitiesPosessingComponentOfClass(component)) );
            }else{//if one of the components is not assigned to any entity at all
                return new ArrayList<>();// Returns an empty list, if there are no classes with such component.
            }
        }
        return new ArrayList<>(entitiesSet); //Returns a list with the surviving entities of the union of sets
        */
    }
    
    public synchronized <T> HashMap<Integer, ? extends Component> getComponentMap(Class<T> component){
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
    public synchronized <T> boolean hasComponent(Entity entity, Class<T> component){
        if(entity.getID() == 0) return false;
        HashMap<Integer, ? extends Component> store = componentsDictionary.get(component);
        T resultComponet = (T) store.get(entity.id);
        return resultComponet != null;
    }
    
    public synchronized <T> boolean hasComponent(Integer entity, Class<T> component){
        if(entity == 0) return false;
        HashMap<Integer, ? extends Component> store = componentsDictionary.get(component);
        T resultComponet = (T) store.get(entity);
        return resultComponet != null;
    }
    
    public synchronized <T> boolean hasComponents(Integer entity, Class<T> ... components){
        for(Class cl: components){
            if(!hasComponent(entity, cl)){
                return false;
            }
        }
        return true;
    }
    
    public synchronized <T> void removeComponentFormEntity(Entity entity, Class<T> component){
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
    
    public void printArchetypesMap(){
        System.out.println("Archetypes Map: ");
        for (Map.Entry pair : archetypesMap.entrySet()) {
            HashSet<Class> archetype = (HashSet<Class>)pair.getKey();
            ArrayList<Integer> archEntities = (ArrayList<Integer>)pair.getValue();
            System.out.print("[");
            for(Class cl: archetype){
                System.out.print(cl.getName() + ", ");
            }
            System.out.print("] : (");
            for(Integer i: archEntities){
                System.out.print(i + ", ");
            }
            System.out.println(") ");
        }
    }
    
    public void printEntitiesArchetypeMap(){
        System.out.println("Entities Archetype Map: ");
        for (Map.Entry pair : entitiesArchetypeMap.entrySet()) {
            Integer id = (Integer)pair.getKey();
            HashSet<Class> archetype = (HashSet<Class>)pair.getValue();
            System.out.print("id: " + id + " : [");
            for(Class cl: archetype){
                System.out.print(cl.getName() + ", ");
            }
            System.out.println("] ");
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
    
    public void selectDataBase(EntityManager em) throws ClassNotFoundException, SQLException, IOException{
        int id;
        Object myObject;
        String sClass;
        DataBaseSystem db= new DataBaseSystem();
        
        // create a mysql database connection
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
        Class.forName(myDriver);
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb","UenUhgqeHb","uGStDaKrpw");
        
        // create a sql date object so we can use it in our SELECT statement
        //Calendar calendar = Calendar.getInstance();
        //java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        
        java.sql.PreparedStatement ps = conn.prepareStatement("select * from objetos");
        
        
        db.selectObjects(conn,ps,em);
                
            //it.remove(); // avoids a ConcurrentModificationException

        ps.close();
        conn.close();
    }
    
    public void saveDatabase() throws IOException, ClassNotFoundException, SQLException{
        int id;
        Object myObject;
        String sClass;
        DataBaseSystem db= new DataBaseSystem();
        
        // create a mysql database connection
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
        Class.forName(myDriver);
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb","UenUhgqeHb","uGStDaKrpw");
        
        // create a sql date object so we can use it in our INSERT statement
        //Calendar calendar = Calendar.getInstance();
        //java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        
        java.sql.PreparedStatement del = conn.prepareStatement("TRUNCATE objetos");
        del.execute();
        
        java.sql.PreparedStatement ps = conn.prepareStatement("REPLACE into objetos values (?, ?, ?)");;
        Iterator it = componentsDictionary.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Iterator it2 = ((HashMap<Integer, ? extends Component>)pair.getValue()).entrySet().iterator();
            sClass=((Class)pair.getKey()).getName();
            while(it2.hasNext()){
                Map.Entry pair2 = (Map.Entry)it2.next();
                id = (int) pair2.getKey();
                if(hasComponent(id, WorldEntity.class)&&!(hasComponent(id, Tile.class))){
                    myObject=pair2.getValue();
                    
                    db.insertObjects(id, myObject, sClass,conn,ps);
                }
            }
            //it.remove(); // avoids a ConcurrentModificationException
        }
        System.out.println(ps);
        ps.clearParameters();
        ps.executeBatch();
        ps.close();
        conn.close();
    }
    
    
    /**
     * TODO: use binary search !
     * 
     * Returns the specific entity in this EntityManager that has the entered ID
     * @param id the id of the Entity to be searched.
     * @return the Entity of ID id
     */
    public synchronized Entity getEntityByID(int id){
        if(id == 0) return null;
        
        for(Entity e : entities ){
            if(e.getID() == id)
                return  e;
        }
        return null;
    }
    
    /**
     * TODO: use binary search !
     * 
     * Returns a list of Entities in this EntityManager that have the IDs in 
     * the Integers list entered.
     *
     * @param ids An Integer List containing the IDs to be used to search
     * @return Returns a list of Entities with the IDs in ids.
     */
    public synchronized ArrayList<Entity> getEntitiesByIDs(ArrayList<Integer> ids){
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
    

