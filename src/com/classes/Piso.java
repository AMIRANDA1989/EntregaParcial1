/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;

import java.util.ArrayList;
import java.util.Scanner;
/*
 *
 * @author allan
*/
public class Piso {
    static char letraPiso = 'A'; //Variable que irá guardando la letra por la que va el codigo
    private String codPiso;
    private boolean habilitado;
    public ArrayList<Habitacion> habitaciones = new ArrayList();
    Scanner in = new Scanner(System.in); //Sirve para leer el input del usuario
    

    public Piso() {
    }

    public Piso(String codPiso, boolean habilitado) {
        this.codPiso = codPiso;
        this.habilitado = habilitado;
    }
    
    
    
    public String getCodPiso() {
        return codPiso;
    }

    public void setCodPiso(String codPiso) {
        this.codPiso = codPiso;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
        
        //Si el piso está deshabilitado, entonces hay que deshabilitar las habitaciones
        if(!(this.habilitado)){
            for(int i = 0; i < habitaciones.size(); i++){
                habitaciones.get(i).setHabilitado(false);
            }  
        }else{
            for(int i = 0; i < habitaciones.size(); i++){
                habitaciones.get(i).setHabilitado(true);
            } 
        }
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void getRoomsStatus(){
        System.out.println("Estado de las habitaciones");
        
        for(Habitacion h : this.habitaciones){
            System.out.println("Habitacion: " + h.getCodHabitacion() + " Habilitado: " + h.isHabilitado() + " Disponible: " + h.isDisponible() );
        }
    }
    
    /*
    buscarHabitacionPorCodigo
    Sirve para buscar una habitacion por código
    retorna la ubicacion de la habitacion que se quiere buscar
    retorna -1 si no se encuentra nada en el ArrayList
    */
    
    public int buscarHabitacionPorCodigo(String codigo){
        int habLocation = -1;
        //-1 es un valor por defecto, indica que no se encontró el piso solicitado
        //buscando a traves del arraylist el codigo del piso
        for(int i = 0; i < this.habitaciones.size(); i++){
            Habitacion h = this.habitaciones.get(i);
            String codHab = h.getCodHabitacion();

            if(codHab.equals(codigo)){
                habLocation = i;
            }
        }
        return habLocation;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    /*
    agregarHabitacion
    Sirve para agregar una habitacion adicional al edificio
    */
    private void agregarHabitacion(){
        Habitacion h = new Habitacion();
        h.setCodHabitacion(this.getCodPiso() + (this.habitaciones.size()+1));
        
        if((this.habitaciones.size()+1)%2 == 0){
            h.setCapacidad(2);
            System.out.println("Habitacion con numero par, capacidad doble");
        }else{
            h.setCapacidad(1);
            System.out.println("Habitacion con numero impar, capacidad sencilla");
        }
        
        System.out.println("Ingrese el precio de la habitacion");
        System.out.println("Recuerda: Las habitaciones doubles pueden tener un precio mayor a las sencillas");
        h.setPrecio(Double.parseDouble(in.nextLine()),(int)this.getCodPiso().charAt(0));
        
        this.habitaciones.add(h);
        System.out.println("Habitacion agregada exitosamente con codigo: " + h.getCodHabitacion());
        System.out.println("");
    }
    
    /*
    modificarHabitacion
    Sirve para modificar una habitacion adicional al edificio
    */
    private void modificarHabitacion(String codHabitacion){
        int location = this.buscarHabitacionPorCodigo(codHabitacion);
        
        Habitacion h = this.habitaciones.get(location);
              
        System.out.println("Ingrese el precio de la habitacion");
        System.out.println("Recuerda: Las habitaciones doubles pueden tener un precio mayor a las sencillas");
        h.setPrecio(Double.parseDouble(in.nextLine()),(int)this.getCodPiso().charAt(0));
        
        String habilitado = h.isHabilitado()? "Habilitado" : "Deshabilitado";                     
        System.out.println("El estado de esta habitacion es: " + habilitado + ". Presione Y para cambiarlo, otra tecla para mantenerlo");

        if(in.nextLine().equals("Y")){
            h.setHabilitado(!(h.isHabilitado()));
            System.out.println("Estado modificado");
        }else{
            System.out.println("Se mantiene el estado");
        }
        
        habilitado = h.isDisponible()? "Disponible" : "No Disponible";
        System.out.println("La disponibilidad de esta habitacion es: " + habilitado + ". Presione Y para cambiarlo, otra tecla para mantenerlo");
        if(in.nextLine().equals("Y")){
            h.setHabilitado(!(h.isHabilitado()));
            System.out.println("Estado modificado");
        }else{
            System.out.println("Se mantiene el estado");
        }
        
        this.habitaciones.remove(location);
        this.habitaciones.add(location, h);
        System.out.println("Habitacion modificada exitosamente ");
        System.out.println("");
    }
    
    /*eliminarHabitacion
    Sirve para eliminar una habitacion del piso.
    PARAMETROS
    codigoHabitacion: Codigo de habitacion a eliminar
    RETORNA
    1: Eliminacion exitosa
    0: Eliminacion fallida
    */
    private int eliminarHabitacion(String codHabitacion){
        int location = this.buscarHabitacionPorCodigo(codHabitacion);
        if(location > -1 ){
            this.habitaciones.remove(location);
            return 1;
        }else{
            return 0;
        }
    }
    
    public void adminHabitaciones(){
        String option;
        String habilitado;
        boolean admStats = true;
        int location;
        
        while(admStats){
            System.out.println("");
            System.out.println("");
            System.out.println("*****HOTEL VIÑA RAFINHA*****");
            System.out.println("**ADMINISTRACION DE HABITACIONES**");
            System.out.println("Seleccione la operacion que desea hacer:");
            System.out.println("1 - Revisar estado de habitaciones.");
            System.out.println("2 - Habilitar/desabilitar habitacion.");
            System.out.println("3 - Agregar habitacion.");
            System.out.println("4 - Modificar habitacion.");
            System.out.println("5 - Eliminar habitacion.");
            System.out.println("6 - Regresar");
            
            option = in.nextLine();
            
            switch (option){
                case "1":
                    for(int i = 0; i < habitaciones.size(); i++){
                        Habitacion h = habitaciones.get(i);
                        System.out.println("HABITACION " + h.getCodHabitacion() + " Disponible: " + h.isDisponible() + ", Habilitado: " + h.isHabilitado());
                    }
                    break;
                
                case "2":
                    System.out.println("**********Administrador de habitaciones**********"     );
                    System.out.println("Ingrese el codigo de la habitacion: ");
                    location = this.buscarHabitacionPorCodigo(in.nextLine());
                    
                    if(location > -1){
                        habilitado = this.habitaciones.get(location).isHabilitado()? "Habilitado" : "Deshabilitado";                     
                        System.out.println("El estado de esta habitacion es: " + habilitado + ". Presione Y para cambiarlo, otra tecla para cancelar");
                        
                        if(in.nextLine().equals("Y")){
                            this.habitaciones.get(location).setHabilitado(!(this.habitaciones.get(location).isHabilitado()));
                            System.out.println("Operacion realizada con exito");
                            System.out.println("");
                        }else{
                            System.out.println("Operacion cancelara");
                            System.out.println("");
                        }
                    }else{
                        System.out.println("No se ha encontrado la habitacion ingresada");
                    }
                    
                    break;
                
                case "3":
                    System.out.println("**********Agregar Habitacion**********"      );
                    System.out.println("Esta por agregar una habitacion adicional al hotel, presione Y para continuar");
                    if(in.nextLine().equals("Y")){
                        this.agregarHabitacion();
                    }
                    break;
                
                case "4":
                    System.out.println("**********Modificar Habitacion**********"      );
                    System.out.println("Ingrese el codigo de la habitacion");
                    location = this.buscarHabitacionPorCodigo(in.nextLine());
                    if(location > -1){
                        this.modificarHabitacion(this.habitaciones.get(location).getCodHabitacion());
                    }else{
                        System.out.println("No se ha encontrado la habitacion ingresada");
                    }
                    
                    break;
                    
                case "5":
                    System.out.println("**********Eliminar Habitacion**********"      );
                    System.out.println("Ingrese el codigo de la habitacion");
                    location = this.buscarHabitacionPorCodigo(in.nextLine());
                    
                    if(location > -1){
                        System.out.println("La habitacion ingresada será elimnada, presione Y para aceptar, otra tecla para cancelar");
                        String cod = this.habitaciones.get(location).getCodHabitacion();
                        if(in.nextLine().equals("Y")){
                            if(this.eliminarHabitacion(cod) == 1){
                                System.out.println("Habitacion eliminada con Exito");
                            }else{
                                System.out.println("Ha ocurrido un problema al eliminar la habitacion");
                            }
                        }
                    }else{
                        System.out.println("No se ha encontrado la habitacion ingresada");
                    }
                    System.out.println("");
                    break;
                    
                case "6":
                    admStats = false;
                    break;
                    
                default:
                    System.out.println("Favor ingrese una opcion valida");
            }
        }
    }
    
}
