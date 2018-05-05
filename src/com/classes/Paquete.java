/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author Nelson Flamenco
*/
public class Paquete {
    int codPaquete;
    private String nombre;
    private String descripcion;
    private double precio;
    Scanner in = new Scanner(System.in); //Sirve para leer el input del usuario
    private ArrayList<Servicio> Servicios = new ArrayList();
    
    public Paquete(int codPaquete,String nombre,String descripcion,double precio){
        this.codPaquete=codPaquete;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
    }
            
    public Paquete(){}
    
    public int getCodPaquete() {
        return codPaquete;
    }
    
    public void setCodPaquete(int codPaquete) {
        this.codPaquete = codPaquete;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
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
            Servicio s = servs.get(i);
            int cod = s.getCodServicio();

            if(cod == codServicio){
                location = i;
            }
        }
        return location;
    }
    
    //Muestra los servicios del paquete
    public void mostrarServicios(){
        System.out.println("SERVICIOS DISPONIBLES DEL PAQUETE " + this.getNombre());
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
                System.out.println("SERVICIOS DISPONIBLES EN EL SISTEMA" + this.getNombre());
                for(Servicio serv : servsDisponibles){
                    System.out.println("Codigo: " + serv.getCodServicio() + ", Nombre: " + serv.getNombre() );
                }
                
                System.out.println("Ingrese el codigo del servicio a agregar");
                
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
