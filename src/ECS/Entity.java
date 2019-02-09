/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS;

import java.util.UUID;

/**
 *
 * @author carlo
 */
public class Entity {
    protected int id;
    protected String name;
    
    public Entity(int id) {
        this.id = id;
        name = new String("");
    }
    
    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    /*public Integer getID(){
        return (Integer)id;
    }*/
}
