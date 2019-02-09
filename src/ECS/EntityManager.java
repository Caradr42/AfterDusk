package ECS;

import java.lang.System;
import ECS.Entity;
import ECS.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author carlo
 */
public class EntityManager {
    
    private ArrayList<Entity> entities;
    private HashMap<Class, HashMap<Integer, ? extends Component>> componentsDictionary = new HashMap<>();
    
    int lowestUnasignedID;

    public EntityManager() {
        entities = new  ArrayList<>();
        componentsDictionary = new HashMap<>();
        lowestUnasignedID = 1;
    }  
    
    public Entity createEntity(){
        int id = GenerateNewID();
        Entity createdEnt = new Entity(id);
        entities.add(createdEnt);
        return createdEnt;
    }
    
    public Entity createEntity(String name){
        int id = GenerateNewID();
        Entity createdEnt = new Entity(id, name);
        entities.add(createdEnt);
        return createdEnt;
    }
    
    private int GenerateNewID(){
        if(lowestUnasignedID < Integer.MAX_VALUE){
            return lowestUnasignedID++;
        }/*else{
            for (int i = 1; i < Integer.MAX_VALUE; ++i) {
            if (//search for available id in entities) {
                
                return i;
            }
        }*/
        return  -1;
    }
    
    public void removeEntity(Entity ent){
        Iterator it = componentsDictionary.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            HashMap<Integer, ? extends Component > componentsMap = (HashMap<Integer,  ? extends Component>)pair.getValue(); 
            Iterator iter = componentsMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entryPair = (Map.Entry)iter.next();
                    if((Integer)entryPair.getKey() == ent.getID())
                    {
                        componentsMap.remove((Integer)entryPair.getKey());
                    }
               // iter.remove();
            }
           // it.remove(); // avoids a ConcurrentModificationException
        }
        entities.remove(ent);
    }
        
    /**
     * 
     * @param <T> type of Component instance component
     * @param entity the entity id as integer
     * @param component Component instance
     */
    public <T extends Component> void addComponetToEntity(Integer entity, T component) {
        
            HashMap<Integer, ? extends Component > store = componentsDictionary.get(component.getClass()); 
            //Gets reference to one of the hasmaps inside the compDir hashmap. using the Component class as a key.
            
            if(store == null) {//if the component does not exists in the hash map
                store = new HashMap<Integer, T >();
                componentsDictionary.put(component.getClass(), store);
                //adds the component hashmap to the hash map using its class as key
            }
            //cast
            ((HashMap<Integer, T>) store).put(entity, component);
            
            //Stores the component, Entity key pain in the map.  
    }
    
    /**
     * 
     * @param <T>
     * @param entity
     * @param component 
     */
    public <T extends Component> void addComponetToEntity(Entity entity, T component) { 
        addComponetToEntity(entity.getID(), component);
    }
     
    /**
     * 
     * @param <T>
     * @param entity
     * @param component
     * @return 
     */
    public <T> T getEntityComponentFromClass(Entity entity, Class<T> component){
        HashMap<Integer, ? extends Component> store = componentsDictionary.get((Class)component);
        T resultComponet = (T) store.get(entity.id);
        //printComponentsMap();
        //System.out.println(componentsDictionary);
        if(resultComponet == null)
                throw new IllegalArgumentException("Get Fail: "+entity.toString()+" does not posses Component of Class \n missing: "+ component);
        return resultComponet;
    }
    
    public ArrayList<Entity> getAllEntitiesPosessingComponentOfClass(Class component){
        HashMap<Integer, ? extends Component > componentsMap = componentsDictionary.get(component); 
        //System.out.println(componentsDictionary);
        //System.out.println(componentsMap);
        //printComponentsMap();
        if(!componentsMap.isEmpty()){
            ArrayList<Integer> entitiesIDs = new ArrayList<>();
            
            //iterating in the Entity component table
            Iterator it = componentsMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                
                entitiesIDs.add((Integer)pair.getKey());
                
                //it.remove(); // avoids a ConcurrentModificationException
               // System.out.println(componentsDictionary);
            }
            //System.out.println(componentsDictionary);
            //finds entities in entities list by their key and add them to a return list
            return getEntitiesByIDs(entitiesIDs);
            
        }
        return new ArrayList<Entity>();
    }
    
    public <T> boolean hasComponent(Entity entity, Class<T> component){
        HashMap<Integer, ? extends Component> store = componentsDictionary.get(component);
        T resultComponet = (T) store.get(entity.id);
        return resultComponet != null;
    }
    
    public void printComponentsMap(){
        //printMap(componentsDictionary);
        
        Iterator it = componentsDictionary.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            HashMap<Integer, ? extends Component > componentsMap = (HashMap<Integer,  ? extends Component>)pair.getValue(); 
            Iterator iter = componentsMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entryPair = (Map.Entry)iter.next();
                    System.out.println("\t" + entryPair.getKey() + " = " + entryPair.getValue());
                //iter.remove();
            }
            //it.remove(); // avoids a ConcurrentModificationException
        }
        
    }
    
    public void printEntities(){
        for(Entity e : entities ){
           System.out.println(e.getID() + " : " + e.getName());
        }
    }
    
    private void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }
    
    public Entity getEntityByID(int id){
        for(Entity e : entities ){
            if(e.getID() == id)
                return  e;
        }
        return null;
    }
    
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
    

