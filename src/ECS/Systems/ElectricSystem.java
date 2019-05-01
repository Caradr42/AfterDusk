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
    Electricity electricity;
    Playable playable;
    //AttackCollider collidesWithOther;
AttackCollider attackCollider;



    public ElectricSystem(Scene scene, boolean active) {
        super(scene, active);
    }
    

    @Override
    public void update() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    entities = scene.entityManager.getEntitiesWithComponents(AttackComponent.class, Electricity.class);
        //ArrayList<Integer> idCollidables;
        //System.out.println("init");
        for(Integer e : entities){
            //System.out.println("e" + e);
            attack = scene.entityManager.getEntityComponentInstance(e, AttackComponent.class);
            for(AttackCollider a : attack.arrColliders){
                
                System.out.println("arrColliders" + attack.arrColliders);
                for(Integer i : a.collidesWith){
                    System.out.println("collides With" + a.collidesWith);
                    
                    playable = scene.entityManager.getEntityComponentInstance(i, Playable.class);
                    
                    playable.hp = playable.hp - electricity.cost;
                    
                    System.out.println("hp enemy" + playable.hp);
                }      
         }
            //collidesWithOther = scene.entityManager.getEntityComponentInstance(e, collidesWithOther)
            
        }
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
