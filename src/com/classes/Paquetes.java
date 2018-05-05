/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author allan
 */
public class Paquetes {

    /**
     *
     */
    public static int codPaquete = 1;
    private String nombrePaquete;
    private String descripcionServicios;
    private ArrayList<Servicio> Servicios = new ArrayList();
    private double precio;
    Scanner in = new Scanner(System.in); //Sirve para leer el input del usuario

    public Paquetes() {
    }

    public Paquetes(String nombrePaquete, String descripcionServicios, double precio) {
        this.nombrePaquete = nombrePaquete;
        this.descripcionServicios = descripcionServicios;
        this.precio = precio;
    }
 
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    
    public static int getCodPaquete() {
        return codPaquete;
    }

    public static void setCodPaquete(int codPaquete) {
        Paquetes.codPaquete = codPaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getDescripcionServicios() {
        return descripcionServicios;
    }

    public void setDescripcionServicios(String descripcionServicios) {
        this.descripcionServicios = descripcionServicios;
    }
    
    public void agregarServicio(Servicio s){
        //Validando si no se esta agregando un servicio que ya existe
        if(this.buscarServicioPorCodigo(s.getCodServicio()) == -1){
            this.Servicios.add(s);
            System.out.println("Servicio agregado exitosamente");
        }else{
            System.out.println("El servicio que se desea agregar ya existe en el paquete");
        }
        
    }
    
    public void removerServicio(int codServicio){
        //Validando si el servicio a remover existe dentro del paquete
        
        if(this.buscarServicioPorCodigo(codServicio) == -1){
            this.Servicios.remove(this.buscarServicioPorCodigo(codServicio));
            System.out.println("Servicio removido exitosamente");
        }else{
            System.out.println("El servicio que se desea agregar no existe en el paquete");
        }
        
        
    }
    
    //Busca entre los servicios asignados al paquete
    public int buscarServicioPorCodigo(int codServicio){
        int location = -1;
        //-1 es un valor por defecto, indica que no se encontró el piso solicitado
        //buscando a traves del arraylist el codigo del piso
        for(int i = 0; i < this.Servicios.size(); i++){
            Servicio s = Servicios.get(i);
            int cod = s.getCodServicio();

            if(cod == codServicio){
                location = i;
            }
        }
        return location;
    }
    
    //Version adicional de buscarServicioPorCodigo
    //Este recibe un Arraylist de servicios
    public int buscarServicioPorCodigo(int codServicio, ArrayList<Servicio> servs){
        int location = -1;
        //-1 es un valor por defecto, indica que no se encontró el piso solicitado
        //buscando a traves del arraylist el codigo del piso
        for(int i = 0; i < servs.size(); i++){
            Servicio s = Servicios.get(i);
            int cod = s.getCodServicio();

            if(cod == codServicio){
                location = i;
            }
        }
        return location;
    }
    
    //Muestra los servicios del paquete
    public void mostrarServicios(){
        System.out.println("SERVICIOS DISPONIBLES DEL PAQUETE " + this.getNombrePaquete());
        for(Servicio serv : this.Servicios){
            System.out.println("Codigo: " + serv.getCodServicio() + ", Nombre: " + serv.getNombre() );
        }
        System.out.println("");
    }
    
    public void configServiciosPaquete(ArrayList<Servicio> servsDisponibles){
        boolean validation = true;
        int location = -1;
        int codServicio;
            
        while(validation){
            System.out.println("Desea ingresar un servicio adicional al paquete?");
            System.out.println("Presione Y para aceptar, cualquier otra tecla para cancelar");
            
            if(in.nextLine().equals("Y")){
                System.out.println("SERVICIOS DISPONIBLES EN EL SISTEMA" + this.getNombrePaquete());
                for(Servicio serv : servsDisponibles){
                    System.out.println("Codigo: " + serv.getCodServicio() + ", Nombre: " + serv.getNombre() );
                }
                
                System.out.println("Ingrese el codigo del paquete a agregar");
                
                codServicio = Integer.parseInt(in.nextLine());
                location = this.buscarServicioPorCodigo(codServicio, servsDisponibles);
                
                if(location == -1){
                    System.out.println("El servicio ingresado no existe en el catalogo de servicios");
                }else{
                    this.agregarServicio(servsDisponibles.get(location));
                    validation = false;
                }
            }else{
                validation = false;
            }     
        }
        System.out.println("Listo!");
            
    }
    
}
