/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity_component_system;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author pablo
 */
public interface Entity{
    //List<component> myList = new ArrayList<component>();
    LinkedList<component> targets = new LinkedList<component>();
    
}
