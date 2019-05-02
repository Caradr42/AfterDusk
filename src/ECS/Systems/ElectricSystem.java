/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.Electricity;
import ECS.Components.Playable;
import ECS.Components.Tool;
import ECS.Entity;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 *
 * @author tanya
 */
public class ElectricSystem extends SystemJob{
    
    AttackComponent attack;
    int id;
    Entity entity;
    Tool tool;
    Electricity electricity;
    Playable playable;
    AttackCollider collidable;
    //AttackCollider collidesWithOther;
AttackCollider collider;



    public ElectricSystem(Scene scene, boolean active) {
        super(scene, active);
    }
    

    @Override
    public void update() {
        entities = scene.entityManager.getEntitiesWithComponents(AttackComponent.class,Electricity.class);
        for(Integer e : entities){
            
            //System.out.println(scene.entityManager.getEntityByID(e).getName());
           // System.out.println("currentActv electricsus");
            
            
             //System.out.println("currentActv electricsus" + tool.currentActive);
            if(tool.currentActive != -1){
                
            tool = scene.entityManager.getEntityComponentInstance(e, Tool.class);
            System.out.println("alo");
            
            attack = scene.entityManager.getEntityComponentInstance(e, AttackComponent.class);
             System.out.println("arrColilders " + attack.arrColliders.size());
            for (AttackCollider a : attack.arrColliders){
                System.out.println("alo2");
                
                
                System.out.println("collideswith " + a.collidesWith.size());
                for (Integer b : a.collidesWith){
                    System.out.println("alo3");
                   // a = scene.entityManager.getEntityComponentInstance(b, AttackCollider.class);
                    entity = scene.entityManager.getEntityByID(b);
                    
                    playable = scene.entityManager.getEntityComponentInstance(b, Playable.class);
                    System.out.println("playable hp" + playable.hp);
                    playable.hp = playable.hp - electricity.cost;
                    System.out.println("new playable hp" + playable.hp);
                    
                    
                }
            
                    a.collidesWith.clear();
                
                
            }
            
            //System.out.println("e" + e);
            //attack = scene.entityManager.getEntityComponentInstance(e, AttackComponent.class);
            /*for(AttackCollider a : attack.arrColliders){
                
                //System.out.println("arrColliders" + attack.arrColliders);
                System.out.println("collides " + a.collidesWith.size());
                
               // for(Integer i : a.collidesWith)
                /*if (a.collidesWith.size() == 0) {
       
                   for(Integer i : a.collidesWith){ 
                    System.out.println("collides With" + a.collidesWith);
                    
                    playable = scene.entityManager.getEntityComponentInstance(i, Playable.class);
                    
                    playable.hp = playable.hp - electricity.cost;
                    
                    System.out.println("hp enemy" + playable.hp);
                }      
                }*/
            //}
            //collidesWithOther = scene.entityManager.getEntityComponentInstance(e, collidesWithOther)
          tool.currentActive = -1;
            }
        }

    }

    @Override
    public void init() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    entities = scene.entityManager.getEntitiesWithComponents(AttackComponent.class,Electricity.class);
    electricity = new Electricity();
    tool = new Tool();
        //ArrayList<Integer> idCollidables;
        //System.out.println("init");
        
    }

    @Override
    public void onCreate() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDestroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
