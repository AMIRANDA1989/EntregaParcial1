/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parcial1;

import com.classes.Engine;
import java.util.Scanner;
/*
 * @author allan
*/
public class Parcial1 {
    /*
     * @param args the command line arguments
    */
    public static void main(String[] args) {
        // TODO code application logic here
        Engine system = new Engine();
        Scanner in = new Scanner(System.in); //Sirve para leer el input del usuario
        
        System.out.println("*********BIENVENIDO*********");
        System.out.println("Antes de comenzar,desea iniciar con la configuracion por defecto?");
        System.out.println("La configuracion por defecto agrega 6 pisos, 10 habitaciones por piso y 2 paquetes de servicios.");
        System.out.println("");
        System.out.println("Sino, el sistema iniciara sin ninguna configuracion previa y todo se debera agregar manualmente");
        System.out.println("Recomendacion: Solo usuarios avanzados...");
        System.out.println("");
        System.out.println("Desea inicial con la configuracion por defecto?");
        System.out.println("Presione Y para aceptar, otra tecla para cancelar");
        
        if(in.nextLine().equals("Y")){
            system.initSettings();        //Inicializando configuracion
        }
        //Iniciando sistema
        system.initSystem();
    }
}
