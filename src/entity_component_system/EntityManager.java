/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity_component_system;

import java.awt.Component;
import java.awt.List;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author pablo
 */
public abstract class EntityManager {
    protected UUID id;
    protected static HashMap<Class, HashMap<UUID, ? extends ComponentManager>> componentsManager = new HashMap<>();
    
    //List<ComponentsInterface> cintr = new List<>();
    
    //HashMap<UIID , ComponentsInterface> map = new HashMap< UUID , ? implements ComponentsInterface>();
    
    protected EntityManager() {
        this.id = UUID.randomUUID(); 
    }
    
    public static<T extends ComponentManager> void addComponents(UUID entity, T component) {
        synchronized(componentsManager) {
            HashMap<UUID, ? extends ComponentManager> store = componentsManager.get(component);
            if(store == null) {
                store = new HashMap<UUID, T>();
                componentsManager.put(componentsManager.getClass(), store);
            }
            //cast
            ((HashMap<UUID, T>) store).put(entity, component);
         }
    }
    
    //We can add for example the component position or transform
    public <T extends ComponentManager> void addComponents(T component) {
        synchronized(componentsManager) {
            HashMap<UUID, ? extends ComponentManager> store = componentsManager.get(component);
            if(store == null) {
                store = new HashMap<UUID, T>();
                componentsManager.put(component.getClass(), store);
            }
            
            ((HashMap<UUID, T>) store).put(this.id, component);
        }
    }
    
    public static <T> T getComponents(UUID entity, Class<T> component){
		HashMap<UUID, ? extends ComponentManager> store = componentsManager.get(component);
		T results = (T) store.get(entity);
		if(results == null)
			throw new IllegalArgumentException("Get Fail: "+entity.toString()+" does not posses Component of Class \n missing: "+ component);
		return results;
	}
	public <T> T getComponents( Class<T> component) {
		HashMap<UUID, ? extends ComponentManager> store = componentsManager.get(component);
		T results = (T) store.get(this.id);
		if(results == null)
			throw new IllegalArgumentException("Get Fail: "+this.id.toString()+" does not posses Component of Class \n missing: "+ component);
		return results;
	}
	public static <T> boolean hasComponents(UUID entity, Class<T> component){
		HashMap<UUID, ? extends ComponentManager> store = componentsManager.get(component);
		T results = (T) store.get(entity);
		if(results == null)
			return false;
		return true;
	}
	public <T> boolean hasComponents( Class<T> component) {
		HashMap<UUID, ? extends ComponentManager> store = componentsManager.get(component);
		if((T) store.get(id) == null)
			return false;
		return true;
}
    
}
