/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity_component_system;

/**
 *
 * @author pablo
 */
public interface component_system {

    public void init();

    public void update();

    public void render();

    public void dispose();

    public component[] get();

}
