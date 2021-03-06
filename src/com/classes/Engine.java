/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/*
 * @author allan
 * la clase Engine es el motor principal de la aplicacion, se inicializa desde el main para poder correr
 * el sistema y la solucion
*/

public class Engine {
    private boolean status = false; //Bandera que indica si el programa se ha inicializado su default es falso y se tiene que poner a true para que ejecute
    private String operation; //Tipo de operacion realizada
    Scanner in = new Scanner(System.in); //Sirve para leer el input del usuario
    private final ArrayList<Piso> pisosHotel = new ArrayList(); //Pisos del Hotel
    private final ArrayList<Paquete> packs = new ArrayList();
    private final ArrayList<Cliente> clientes = new ArrayList();
    private final ArrayList<Reservacion> reservaciones = new ArrayList();
    private final ArrayList<Servicio> servicios = new ArrayList();
    private final ArrayList<Venta> ventas = new ArrayList();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //formateador de fecha
    LocalDateTime fechaActual;
//**************************************************************************************************************************************************************************************************************************************************************
/*
* initSystem inicializa el sistema, es necesario ejecutarlo despues de la declaracion de un objeto de Clase Engine.
*/
    public void initSystem(){
        this.setStatus(true);
        while (this.isStatus()){
            System.out.println("");
            System.out.println("************HOTEL VIÑA RAFINHA************");
            System.out.println("Seleccione en el menú la opcion que desea:");
            System.out.println("1 - Control de Hotel"               );
            System.out.println("2 - Reservaciones"                  );
            System.out.println("3 - Gestor de Clientes"            );
            System.out.println("4 - Gestor de Servicios"            );
            System.out.println("5 - Reporte de Ventas"   );
            System.out.println("6 - Apagar Sistema..."              );
            
            System.out.println("opcion:");
            //obteniendo valor de operacion
            operation = in.nextLine(); 
            
            //validando valor de operacion
            switch (operation){
                case "1":
                    System.out.println("");
                    System.out.println("**************CONTROL DE HOTEL**************" );
                    this.controlHotel();
                    break;
                    
                case "2":
                    System.out.println("");
                    System.out.println("***************RESERVACIONES***************"  );
                    this.reservaHotel();
                    break;  
                    
                case "3":
                    System.out.println("");
                    System.out.println("*************GESTOR DE CLIENTES*************" );
                    this.clienteHotel();
                    break;
                
                case "4":
                    System.out.println("");
                    System.out.println("*************GESTOR DE SERVICIOS*************" );
                    this.gestionarServicios();
                    break;
                    
                case "5":
                    System.out.println("");
                    System.out.println("********REPORTE DE RESERVACIONES********" );
                    this.reporteReservas();
                    break;
                    
                case "6":
                    System.out.println("");
                    System.out.println("*********APAGANDO SISTEMA DE HOTEL*********"  );
                    System.out.println("Adios!"                                       );
                    this.setStatus(false);
                    break;
                    
                default: 
                    System.out.println("Favor ingrese una operacion valida..."        );
                    break;
            }
        }
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
//***************************************************************************************************************************************************************************************************************************************    
    /*
    defaultSettings
    Sirve para inicializar las variables necesarias para el hotel,
    esto se puede hacer para tener una configuracion por defecto del Hotel Rafinha
    PARAMETROS:
    6 pisos:cantidad de pisos que tendrá el hotel; 
    10 habitaciones:Cantidad de pisos por habitacion;
    */
    public void defaultSettings(){
        ArrayList<Habitacion> rooms = new ArrayList();
        
        double precios=50,preciod=75;
        
        Habitacion h = null;
        Piso pi;
        
        //agregando habitaciones de los pisos
        for(int i = 0; i < 7; i++){
            pi = new Piso(""+Piso.letraPiso,true);
            Piso.letraPiso++;
            
            if(i==5 || i==6){
                precios=precios*0.10;
                preciod=preciod*0.10;
            }
            
            for(int j = 0; j < 10; j++){
                if((j+1)%2 == 0){
                    h = new Habitacion(pi.getCodPiso()+""+(j+1),true,2,true,preciod);
                }else{
                    h = new Habitacion(pi.getCodPiso()+""+(j+1),true,1,true,precios);
                }
                pi.habitaciones.add(h);
            }
            this.pisosHotel.add(pi);
        }

        //agregando servicios
        Servicio s = new Servicio(Servicio.servKey,"Buffet");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Piscina Ilimitada");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Servicio habitacion");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Minibar Ilimitado");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Internet Ilimitado");
        this.servicios.add(s);
        Servicio.servKey++;
        
        //Agregando paquetes
        Paquete pa = new Paquete(1, "PREMIUM", "Paquete premium que incluye servicio de buffet, internet, piscina, y mas",150);
        pa.setServicios(servicios);
        this.packs.add(pa);
        
        ArrayList<Servicio> pb = new ArrayList();
        pb.add(this.servicios.get(1));
        pb.add(this.servicios.get(4));
        
        pa = new Paquete(2, "BASICO", "Paquete que incluye internet y piscina",10);
        pa.setServicios(pb);
        this.packs.add(pa);
        
        System.out.println("Se realizo configuaracion por defecto...");
    }
//***********************************************************************************************************************************************************************************************************************
    public void advancedSettings(){  
        ArrayList<Habitacion> rooms = new ArrayList();
        
        int piso=0,habitacion=0;
        double precios=0,preciod=0,porcentaje=0;
        
        Habitacion h = null;
        Piso pi = null;
        
            System.out.println("********Distribucion de habitaciones********");
            System.out.println("Seleccione el orden que desea:");
            System.out.println("1 - Sencillas");
            System.out.println("2 - Dobles");
            System.out.println("3 - Ambas(default)");
            System.out.println("opcion:");
            
            String option = in.nextLine();
            
            switch (option) {
                case "1":
                    System.out.println("");
                    System.out.println("Desea modificar la cantidad pisos al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("Cuantos pisos desea agregar?");
                        piso = in.nextInt();
                        in.nextLine();
                    }else{
                        piso = 6;
                    }   
                   
                    System.out.println("");
                    System.out.println("Desea modificar la cantidad habitaciones al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("ingrese numero de habitaciones:");
                        habitacion = in.nextInt();
                        in.nextLine();
                    }else{
                        habitacion = 10;
                    }   
                    
                    System.out.println("");
                    System.out.println("Desea modificar el precio por habitacion al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("precio habitacion sencilla:");
                        precios = in.nextInt();
                    }else{
                        precios = 50;
                    }   
                    
                    System.out.println("");
                    System.out.println("Desea agregar Clase VIP?");
                    System.out.println("NOTA: la clase vip son habitaciones en los dos ultimos pisos e incrementa su precio");
                    System.out.println("Presione Y para aceptar u otra tecla para omitir");
                    System.out.println("");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("Ingrese porcentaje de aumento a Clase VIP:");
                        porcentaje=in.nextInt();                    
                    
                        //agregando pisos
                        for(int i = 0; i < piso; i++){
                            pi = new Piso(" "+Piso.letraPiso,true);
                            Piso.letraPiso++;
                            
                            if(i==piso-1 || i==piso-2){
                                precios=precios*porcentaje/100;
                                preciod=preciod*porcentaje/100;
                            }
                            
                            //agregando habitaciones de los pisos
                            for(int j = 0; j < habitacion; j++){
                                h = new Habitacion(pi.getCodPiso()+""+(j+1),true,1,true,precios);
                                pi.habitaciones.add(h);
                            }
                        }    
                        this.pisosHotel.add(pi);   
                        break;    
                    } 
                        
                    //agregando pisos
                    for(int i = 0; i < piso; i++){
                        pi = new Piso(" "+Piso.letraPiso,true);
                        Piso.letraPiso++;

                        if(i==piso-1 || i==piso-2){
                            precios=precios*0.10;
                            preciod=preciod*0.10;
                        }
                        //agregando habitaciones de los pisos
                        for(int j = 0; j < habitacion; j++){
                            h = new Habitacion(pi.getCodPiso()+""+(j+1),true,2,true,precios);
                            pi.habitaciones.add(h);
                        }
                    }
                    this.pisosHotel.add(pi);   
                    break; 
                    
                case "2":
                    System.out.println("");
                    System.out.println("Desea modificar la cantidad pisos al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("Cuantos pisos desea agregar?");
                        piso = in.nextInt();
                        in.nextLine();
                    }else{
                        piso = 6;
                    }   
                   
                    System.out.println("");
                    System.out.println("Desea modificar la cantidad habitaciones al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("ingrese numero de habitaciones:");
                        habitacion = in.nextInt();
                        in.nextLine();
                    }else{
                        habitacion = 10;
                    }   
                    
                    System.out.println("");
                    System.out.println("Desea modificar el precio por habitacion al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("precio habitacion doble:");
                        preciod = in.nextInt();
                        in.nextLine();
                    }else{
                        preciod = 75;
                    }   
                    
                    System.out.println("");
                    System.out.println("Desea agregar Clase VIP?");
                    System.out.println("NOTA: la clase vip son habitaciones en los dos ultimos pisos e incrementa su precio");
                    System.out.println("Presione Y para aceptar u otra tecla para omitir");
                    System.out.println("");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("Ingrese porcentaje de aumento a Clase VIP:");
                        porcentaje=in.nextInt();                    
                    
                        //agregando pisos
                        for(int i = 0; i < piso; i++){
                            pi = new Piso(""+Piso.letraPiso,true);
                            Piso.letraPiso++;

                            if(i==piso-1 || i==piso-2){
                                precios=precios*porcentaje/100;
                                preciod=preciod*porcentaje/100;
                            }
                            
                            //agregando habitaciones de los pisos
                            for(int j = 0; j < habitacion; j++){
                                h = new Habitacion(pi.getCodPiso()+""+(j+1),true,2,true,preciod);
                                pi.habitaciones.add(h);
                            }
                        }    
                        this.pisosHotel.add(pi);   
                        break;    
                    } 
                        
                    //agregando pisos
                    for(int i = 0; i < piso; i++){
                        pi = new Piso(""+Piso.letraPiso,true);
                        Piso.letraPiso++;

                        if(i==piso-1 || i==piso-2){
                            precios=precios*0.10;
                            preciod=preciod*0.10;
                        }
                        //agregando habitaciones de los pisos
                        for(int j = 0; j < habitacion; j++){
                            h = new Habitacion(pi.getCodPiso()+""+(j+1),true,1,true,preciod);
                            pi.habitaciones.add(h);
                        }
                    }
                    this.pisosHotel.add(pi);   
                    break;    
                    
                case "3":
                    System.out.println("");
                    System.out.println("Desea modificar la cantidad pisos al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("Cuantos pisos desea agregar?");
                        piso = in.nextInt();
                        in.nextLine();
                    }else{
                        piso = 6;
                    }   
                   
                    System.out.println("");
                    System.out.println("Desea modificar la cantidad habitaciones al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("ingrese numero de habitaciones:");
                        habitacion = in.nextInt();
                        in.nextLine();
                    }else{
                        habitacion = 10;
                    }   
                    
                    System.out.println("");
                    System.out.println("Desea modificar el precio por habitacion al hotel?");
                    System.out.println("Presione Y para aceptar u otro para agregar por defecto");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("precio habitacion sencilla:");
                        precios = in.nextInt();
                        System.out.println("precio habitacion doble:");
                        preciod = in.nextInt();
                        in.nextLine();
                    }else{
                        precios = 50;
                        preciod = 75;
                    }   
                    
                    System.out.println("");
                    System.out.println("Desea agregar Clase VIP?");
                    System.out.println("NOTA: la clase vip son habitaciones en los dos ultimos pisos e incrementa su precio");
                    System.out.println("Presione Y para aceptar u otra para agregar configuracion por defecto.");
                    System.out.println("");
                    
                    if(in.nextLine().equals("y")){
                        System.out.println("Ingrese porcentaje de aumento a Clase VIP:");
                        porcentaje=in.nextInt();                    
                    
                        //agregando pisos
                        for(int i = 0; i < piso; i++){
                            pi = new Piso(""+Piso.letraPiso,true);
                            Piso.letraPiso++;
                            
                            if(i==piso-1 || i==piso-2){
                                precios=precios*porcentaje/100;
                                preciod=preciod*porcentaje/100;
                            }

                            //agregando habitaciones de los pisos
                            for(int j = 0; j < habitacion; j++){
                                if((j+1)%2 == 0){
                                h = new Habitacion(pi.getCodPiso()+""+(j+1),true,2,true,preciod);
                                }else{
                                h = new Habitacion(pi.getCodPiso()+""+(j+1),true,1,true,precios);
                                }
                                pi.habitaciones.add(h);
                            }
                            this.pisosHotel.add(pi);   
                        }    
                        break;    
                    } 
                        
                    //agregando pisos
                    for(int i = 0; i < piso; i++){
                        pi = new Piso(""+Piso.letraPiso,true);
                        Piso.letraPiso++;

                        if(i==piso-1 || i==piso-2){
                            precios=precios*0.10;
                            preciod=preciod*0.10;
                        }
                        
                        //agregando habitaciones de los pisos
                        for(int j = 0; j < habitacion; j++){
                            if((j+1)%2 == 0){
                            h = new Habitacion(pi.getCodPiso()+""+(j+1),true,2,true,precios);
                            }else{
                            h = new Habitacion(pi.getCodPiso()+""+(j+1),true,1,true,preciod);
                            }
                            pi.habitaciones.add(h);
                        }
                    }
                    this.pisosHotel.add(pi);   
                    break;                 
            }         

        //agregando servicios
        Servicio s = new Servicio(Servicio.servKey,"Buffet");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Piscina Ilimitada");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Servicio habitacion");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Minibar Ilimitado");
        this.servicios.add(s);
        Servicio.servKey++;
        s = new Servicio(Servicio.servKey,"Internet Ilimitado");
        this.servicios.add(s);
        Servicio.servKey++;
        
        //Agregando paquetes
        Paquete pa = new Paquete(1, "PREMIUM", "Paquete premium que incluye servicio de buffet, internet, piscina, y mas",150);
        pa.setServicios(servicios);
        this.packs.add(pa);
        
        ArrayList<Servicio> pb = new ArrayList();
        pb.add(this.servicios.get(1));
        pb.add(this.servicios.get(4));
        
        pa = new Paquete(2, "BASICO", "Paquete que incluye internet y piscina",10);
        pa.setServicios(pb);
        this.packs.add(pa);
        
        in.nextLine();
        System.out.println("");
        System.out.println("Se realizo configuaracion avanzada...");
    }
//*********************************************************************************************************************************************************************************************************************
    /*
    ControlHotel
    Sirve para administrar los pisos y las habitaciones
    es privado ya que solo la clase Engine debe accederla
    de lo contrario funcionaría de manera incompleta
    */
    private void controlHotel(){
        String option;
        String pisoOpt;
        boolean controlHotelStats = true;
        int location;
        
        while (controlHotelStats){
            System.out.println("Seleccione la operacion que desea realizar:");
            System.out.println("1 - Revisar estado de pisos."            );
            System.out.println("2 - Administrador de habitaciones."     );
            System.out.println("3 - Habilitar/Deshabilitar pisos"        );
            System.out.println("4 - Agregar Piso"        );
            System.out.println("5 - Eliminar Piso"        );
            System.out.println("6 - Agregar paquete");
            System.out.println("7 - Modificar paquete");
            System.out.println("8 - Eliminar paquete");
            System.out.println("9 - Ver paquetes");
            System.out.println("10 - Regresar..."                         );
            
            System.out.println("opcion:");
            
            option = in.nextLine();
            
            switch (option) {
                case "1":
                    System.out.println("*************Revisar estado de pisos*************"     );
                    //System.out.println(pisosHotel.size());
                    for(int i = 0; i < pisosHotel.size(); i++){
                        Piso p = pisosHotel.get(i);
                        String habilitado = p.isHabilitado()? "Habilitado" : "Deshabilitado";
                        System.out.println("[Piso " + p.getCodPiso() + "] Estado: " + habilitado + ", Numero Habitaciones: " + p.getHabitaciones().size());
                    }
                    System.out.println("");
                    break;
                    
                case "2":
                    System.out.println("**********Administrador de pisos**********"     );
                    System.out.println("Ingrese el codigo del piso: ");
                    pisoOpt = in.nextLine();
                                   
                    location = this.buscarPisoPorCodigo(pisoOpt);
                    
                    //validando si el piso ingresado existe
                    if(location > -1){
                        Piso p = pisosHotel.get(location);
                        p.adminHabitaciones();
                    }else{
                        System.out.println("No se ha encontrado el piso ingresado");
                    }
                    break;
                    
                case "3":
                    System.out.println("**********Habilitar/Deshabilitar pisos**********"      );
                    System.out.println("Ingrese el codigo del piso: ");
                    pisoOpt = in.nextLine();
                                   
                    location = this.buscarPisoPorCodigo(pisoOpt);
                    
                    //validando si el piso ingresado existe
                    if(location > -1){
                        Piso p = pisosHotel.get(location);
                        System.out.println("El estado actual del piso es : " + p.isHabilitado() + " , Desea cambiarlo?" );
                        System.out.println("Presione Y para aceptar, N o cualquier otra tecla para cancelar");
                        if(in.nextLine().equals("y")){
                            p.setHabilitado(!(p.isHabilitado()));
                            //Se elimina el piso para hacer la actualizacion
                            pisosHotel.remove(location);
                            //se agrega el nuevo piso para hacer la actualizacion
                            pisosHotel.add(location,p);
                            System.out.println("Estado de piso modificado...");
                        }
                    }else{
                        System.out.println("No se ha encontrado el piso ingresado");
                    }
                    break;
                
                case "4":
                    System.out.println("**********Agregar Piso**********"      );
                    System.out.println("Esta por agregar un piso adicional al hotel, presione Y para continuar");
                    if(in.nextLine().equals("y")){
                        this.agregarPiso();
                    }
                    break;
                    
                case "5":
                    System.out.println("**********Eliminar Piso**********"      );
                    System.out.println("Ingrese el codigo del piso: ");
                    pisoOpt = in.nextLine();
                                   
                    location = this.buscarPisoPorCodigo(pisoOpt);
                    
                    //validando si el piso ingresado existe
                    if(location > -1){
                        String cod = this.pisosHotel.get(location).getCodPiso();
                        System.out.println("El piso seleccionado sera eliminado, presione Y para confirmar");
                        if(in.nextLine().equals("y")){
                            
                            if( this.eliminarPiso(cod) == 1 ){
                                System.out.println("Piso Eliminado con exito");
                            }else{
                                System.out.println("Ha ocurrido un problema al eliminar el piso");
                            }
                        }
                    }else{
                        System.out.println("No se ha encontrado el piso ingresado");
                    }
                    break;
                
                case "6":
                    System.out.println("**********Agregar Paquete**********"      );
                    System.out.println("Esta por agregar un paquete adicional al hotel, presione Y para continuar");
                    if(in.nextLine().equals("y")){
                        this.agregarPaquete();
                    }
                    break;
                    
                case "7":
                    System.out.println("**********ModificarPaquete**********"      );
                    System.out.println("Esta por agregar un paquete adicional al hotel, presione Y para continuar");
                    if(in.nextLine().equals("y")){
                        System.out.println("Ingrese el codigo del paquete");
                        if(this.modificarPaquete(Integer.parseInt(in.nextLine())) == 0){
                            System.out.println("No se ha encontrado el paquete ingresado");
                        }
                    }
                    break;
                
                case "8":
                    System.out.println("**********Eliminar Paquete**********"      );
                    System.out.println("Ingrese el codigo del paquete: ");
                    int cpack = Integer.parseInt(in.nextLine()) ;
                                   
                    location = this.buscarPackPorCodigo(cpack);
                    
                    //validando si el piso ingresado existe
                    if(location > -1){
                        int cp = this.packs.get(cpack).getCodPaquete();
                        System.out.println("El paquete seleccionado sera eliminado, presione Y para confirmar");
                        if(in.nextLine().equals("y")){
                            
                            if( this.eliminarPaquete(cp) == 1 ){
                                System.out.println("Paquete Eliminado con exito");
                            }else{
                                System.out.println("Ha ocurrido un problema al eliminar el paquete");
                            }
                        }
                    }else{
                        System.out.println("No se ha encontrado el paquete ingresado");
                    }
                    break;
                    
                case "9":
                    System.out.println("**********Ver Paquetes**********"      );
                    
                    for(int i = 0; i < packs.size(); i++){
                        Paquete p = packs.get(i);
                        System.out.println("[Paquete #" + p.getCodPaquete() + "] Nombre: " + p.getNombre() + ", Descripcion: " + p.getDescripcion() + "Precio: " + p.getPrecio());
                    }
                    System.out.println("");
                
                case "10":
                    controlHotelStats = false;
                    
                default:
                    System.out.println("Favor ingrese una opcion valida");
                    break;
            }//fin swicth(controlHotelStats)
        }//fin while (controlHotelStats)
    }  
//*********************************************************************************************************************************************************************************************+
    private void reservaHotel(){
        String option;
        boolean reservaHotelStats = true;
        boolean validation = true;
        String codRoom;
        String fechaReserva = null;
        int estadia = 0;
        double precioHabitacion = 0;
        int codPack;
        double precioPaquete = 0;
        int codCliente;
        int cantidadPersonas = 0;
        String codHabitacion = null;
        
        while (reservaHotelStats){
            System.out.println("Seleccione la operacion que desea realizar:"    );
            System.out.println("1 - Realizar reservación"                       );
            System.out.println("2 - Modificar reservación"                      );
            System.out.println("3 - Cancelar reservación"                       );
            System.out.println("4 - Ver reservaciones");
            System.out.println("4 - Regresar..."                                );
            
            System.out.println("opcion:");
            
            option = in.nextLine();
            
            switch (option) {
                case "1":
                    System.out.println("**********Realizar reservacion**********");
                    System.out.println("");
                    System.out.println("Habitaciones disponibles:");
                    for(Piso p : this.pisosHotel){
                        if(p.isHabilitado()){
                            System.out.println("Piso: " + p.getCodPiso());
                            for(Habitacion h : p.getHabitaciones()){
                                if(h.isDisponible() && h.isHabilitado()){
                                    System.out.print(h.getCodHabitacion());
                                }
                            }
                            System.out.println("");
                        }
                    }
                    
                    //ingresando habitacion
                    while(validation){
                        System.out.println("");
                        System.out.println("Ingrese el codigo de la habitacion que sera reservada:");
                        codRoom = in.nextLine();
                        
                        int i = this.buscarPisoPorCodigo(codRoom.substring(0, 1));
                        
                        if(i > -1){
                            int j = this.pisosHotel.get(i).buscarHabitacionPorCodigo(codRoom);
                            
                            if(j > -1){
                                validation = false;
                                precioHabitacion = this.pisosHotel.get(i).habitaciones.get(j).getPrecio();
                                codHabitacion = this.pisosHotel.get(i).habitaciones.get(j).getCodHabitacion();
                            }else{
                                System.out.println("Habitacion ingresada de manera incorrecta");
                            }
                                  
                        }else{
                            System.out.println("Piso ingresado de manera incorrecta");
                        }  
                    }
                    
                    //ingresando fecha
                    validation = true;
                    while(validation){
                        System.out.println("");
                        System.out.println("Ingrese la fecha de reserva FORMATO DD/MM/YYYY");
                        fechaReserva = in.nextLine();
                        
                        if(fechaReserva.length() == 10){
                            validation = false;
                        }else{
                            System.out.println("Favor ingrese una fecha correcta");
                        }
                    }
                    
                    //ingresando dias
                    validation = true;
                    while(validation){
                        System.out.println("");
                        System.out.println("Ingrese la cantidad de dias de estadia:");
                        estadia = Integer.parseInt(in.nextLine());
                        
                        if(estadia <1 || estadia>7){
                            System.out.println("La estadía no puede ser mayor a 7 dias o menor a 1 dia");
                        }else{
                            validation = false;
                        }
                    }
                    
                    //ingresando paquete, si es necesario
                    System.out.println("Desea agregar un paquete? Presion Y para aceptar, cualquier otra tecla para cancelar");
                    
                    if(in.nextLine().equals("y")){
                        validation = true;
                        while(validation){
                            System.out.println("Paquetes disponibles");
                            for(int i = 0; i < this.packs.size(); i++){
                            Paquete p = this.packs.get(i);
                            System.out.println("[Paquete #" + p.getCodPaquete() + "] Nombre: " + p.getNombre() + ", Descripcion: " + p.getDescripcion() + "Precio: " + p.getPrecio());
                            }
                            System.out.println("");

                            System.out.println("Ingrese el codigo de paquete");

                            codPack = Integer.parseInt(in.nextLine());
                            int location = this.buscarPackPorCodigo(codPack);
                            
                            if(location > -1){
                                precioPaquete = this.packs.get(location).getPrecio();
                                validation = false;
                            }else{
                                System.out.println("Ingrese un codigo correcto de paquete");
                            }
                        }
                        
                    }
                    
                    //agregando cantidad de personas
                    validation =  true;
                    while (validation){
                        System.out.println("Ingrese el numero de personas");
                        cantidadPersonas = Integer.parseInt(in.nextLine());
                        
                        if(cantidadPersonas < 1){
                            System.out.println("Numero no valido de personas");
                        }else{
                            validation = false;
                        }
                    }
                    
                    //ingresando datos de cliente
                    System.out.println("Se procedera a ingresar datos de cliente");
                    this.inputCliente();
                                       
                    Reservacion r = new Reservacion();
                    r.setCodReservacion(Reservacion.reservKey);
                    r.setNumCliente(Cliente.clienteKey -1);
                    r.setCodHabitacion(codHabitacion);
                    r.setDias(estadia);
                    r.setFechaHospedaje(fechaReserva);
                    r.setNumPersonas(cantidadPersonas);
                    r.setTotalReserva(precioPaquete + precioHabitacion);
                    
                    
                    
                    System.out.println("La reservacion ha sido agregada con exito");
                    
                    validation = true;
                    
                    while(validation){
                        System.out.println("Desea confirmar la reservacion y proceder a realizar la venta?");
                        System.out.println("Presione Y para confirmar, otra tecla para cancelar");

                        this.fechaActual = LocalDateTime.now();

                        if (in.nextLine().equals("Y")){
                            Venta v = new Venta(Venta.ventaKey,r.getCodReservacion(), dtf.format(fechaActual), r.getTotalReserva());
                            this.reservaciones.add(r);
                            this.ventas.add(v);
                            Venta.ventaKey++;
                            
                            //Deshabilitando la habitacion seleccionada
                            int i = this.buscarPisoPorCodigo(codHabitacion.substring(0, 1));
                        
                            if(i > -1){
                                int j = this.pisosHotel.get(i).buscarHabitacionPorCodigo(codHabitacion);

                                if(j > -1){
                                                                        precioHabitacion = this.pisosHotel.get(i).habitaciones.get(j).getPrecio();
                                    this.pisosHotel.get(i).habitaciones.get(j).setDisponible(false);
                              }
                            }
                            
                            System.out.println("Venta realizada con exito");
                            validation = false;
                            this.initSystem();
                        }else{
                            System.out.println("La reservacion será cancelada, presione Y para confirmar, otra tecla para cancelar");
                            
                            if(in.nextLine().equals("Y")){
                                validation = false;
                                System.out.println("Reservacion cancelada con exito");
                            }
                        }
                    }           
                    break;
                    
                case "2":
                    System.out.println("**********Modificar reservacion**********" );
                    break;
                    
                case "3":
                    System.out.println("**********Cancelar reservacion**********"  );
                    break;
                    
                case "4":
                    reservaHotelStats = false;
                    break;
                    
                default:
                    System.out.println("Favor ingrese una opcion valida");
                    break;
            }//fin swicth(reservaHotelStats)
        }//fin while (reservaHotelStats)
    }  
//******************************************************************************************************************************************************************************************
    private void clienteHotel(){
        String option;
        boolean clienteHotelStats = true;
        
        System.out.println("Seleccione la operacion que desea hacer:");
        System.out.println("1 - Agregar cliente "                 );
        System.out.println("2 - Buscar cliente"                );
        System.out.println("3 - Modificar cliente"                );
        System.out.println("4 - Eliminar cliente "                );
        System.out.println("5 - Regresar..."                      );
            
        System.out.println("opcion:");        
        
        while (clienteHotelStats){
            System.out.println("");
            
            option = in.nextLine();
            
            switch (option) {
                case "1":
                    this.inputCliente();
                    break;
                    
                case "2":
                    this.searchCliente();
                    break;   
                    
                case "3":
                    this.modCliente();
                    break; 
                    
                case "4":
                    this.deleteCliente();
                    break;   
                    
                case "5":
                    clienteHotelStats = false;
                    break;
                    
                default:
                    System.out.println("Favor ingrese una opcion valida"      );
                    break;
            }//fin swicth(clienteHotelStats)
        }//fin while (clienteStats)
    } 

    private void inputCliente(){
        Cliente c = new Cliente();
        
        System.out.println("***************Ingreso Cliente*****************");
        
        String id = Integer.toString(Cliente.clienteKey);
        
        c.setIdCliente(id);
        
        System.out.println("");
        System.out.println("Esta agregando cliente id: "+Cliente.clienteKey);
        System.out.println("");
        System.out.println("Ingrese nombre:");
        c.setNombre(in.nextLine());
        System.out.println("");
        System.out.println("Ingrese apellido:");
        c.setApellido(in.nextLine());
        System.out.println("");
        System.out.println("Ingrese dui:");
        c.setDui(in.nextLine());
        System.out.println("");
        System.out.println("Ingrese edad:");
        c.setEdad(in.nextInt());
        System.out.println("");
        in.nextLine();
        System.out.println("Ingrese sexo:");
        c.setSexo(in.nextLine());
        System.out.println("");
        System.out.println("Ingrese telefono:");
        c.setTelefono(in.nextLine());
        System.out.println("");
        System.out.println("Ingrese e-mail:");
        c.setEmail(in.nextLine());
        System.out.println("");
        System.out.println("Ingrese numero tarjeta:");
        c.setNumTarjeta(in.nextLine());
        System.out.println("");
        System.out.println("Datos ingresados exitosamente...");
        System.out.println("");
        
        this.clientes.add(c);
        Cliente.clienteKey++;
        
        this.initSystem();
    } 
    
    private void searchCliente(){
        boolean f= true;
        
        while(f){
            System.out.println("***************Buscador Cliente*****************");
            System.out.println("");
            System.out.println("Ingrese idCliente a buscar:");
            
            String clt= in.nextLine();
            
            for(int i=0; i<clientes.size();i++){
                if(clt.equals(clientes.get(i).getIdCliente())){
                    System.out.println("");
                    System.out.println("Cliente "+clientes.get(i).getIdCliente()+" ha sido encontrado...");
                    System.out.println("");
                    System.out.println("Nombre: "+clientes.get(i).getNombre());
                    System.out.println("");
                    System.out.println("Apellido: "+clientes.get(i).getApellido());
                    System.out.println("");
                    System.out.println("DUI: "+clientes.get(i).getDui());
                    System.out.println("");
                    System.out.println("Edad: "+clientes.get(i).getEdad());
                    System.out.println("");
                    System.out.println("Sexo: "+clientes.get(i).getSexo());
                    System.out.println("");
                    System.out.println("Ingrese Telefono: "+clientes.get(i).getTelefono());
                    System.out.println("");
                    System.out.println("E-mail: "+clientes.get(i).getEmail());
                    System.out.println("");
                    System.out.println("Ingrese numero tarjeta: "+clientes.get(i).getNumTarjeta());
                    System.out.println("");
                    f=false;
                }
            }
            
            if(f==true){
                System.out.println("");
                System.out.println("idCliente no existe,intentelo nuevamente...");
                System.out.println("");
            }
            else{
                f=false;
            }
        }
        this.initSystem();
    } 
    
    public void modCliente(){
        Cliente c = new Cliente();
        
        boolean f= true;
        
        while(f){
            System.out.println("***************Modificador Cliente*****************");
            System.out.println("");
            System.out.println("Ingrese idCliente a modificar:");
            
            String clt= in.nextLine();

            for(int i=0; i<clientes.size();i++){
                if(clt.equals(clientes.get(i).getIdCliente())){
                        System.out.println("");
                        System.out.println("Esta modificando cliente id: "+(Cliente.clienteKey-1));
                        System.out.println("");
                        System.out.println("1 - Nombre: "+clientes.get(i).getNombre());
                        System.out.println("");                     
                        System.out.println("2 - Apellido: "+clientes.get(i).getApellido());
                        System.out.println("");
                        System.out.println("3 - DUI: "+clientes.get(i).getDui());
                        System.out.println("");
                        System.out.println("4 - Edad: "+clientes.get(i).getEdad());
                        System.out.println("");
                        System.out.println("5 - Sexo: "+clientes.get(i).getSexo());
                        System.out.println("");
                        System.out.println("6 - Ingrese Telefono: "+clientes.get(i).getTelefono());
                        System.out.println("");
                        System.out.println("7 - E-mail: "+clientes.get(i).getEmail());
                        System.out.println("");
                        System.out.println("8 - Numero tarjeta: "+clientes.get(i).getNumTarjeta());
                        System.out.println("");
                        System.out.println("Elija dato a modificar: ");
                        String option=in.nextLine();
                        switch(option){
                            case "1":
                                System.out.println("Ingrese nombre:");
                                c.setNombre(in.nextLine());
                                System.out.println("");
                                System.out.println("Nombre modificado: "+clientes.get(i).getNombre());
                                System.out.println("");
                                break;
                                
                            case "2":
                                System.out.println("Ingrese apellido:");
                                c.setApellido(in.nextLine());
                                System.out.println("");
                                System.out.println("Apellido modificado: "+clientes.get(i).getApellido());
                                System.out.println("");
                                break;
                            
                            case "3":
                                System.out.println("Ingrese DUI:");
                                c.setDui(in.nextLine());
                                System.out.println("");
                                System.out.println("DUI modificado: "+clientes.get(i).getDui());
                                System.out.println("");
                                break;
                                
                            case "4":
                                System.out.println("Ingrese edad:");
                                c.setEdad(in.nextInt());
                                System.out.println("");
                                System.out.println("Edad modificado : "+clientes.get(i).getEdad());
                                System.out.println("");
                                break;   
                                
                            case "5":
                                in.nextLine();
                                System.out.println("Ingrese sexo:");
                                c.setSexo(in.nextLine());
                                System.out.println("");
                                System.out.println("Sexo modificado : "+clientes.get(i).getSexo());
                                System.out.println("");
                                break;   
                                
                            case "6":
                                System.out.println("Ingrese telefono:");
                                c.setTelefono(in.nextLine());
                                System.out.println("");
                                System.out.println("Telefono modificado : "+clientes.get(i).getTelefono());
                                System.out.println("");
                                break;    
                                
                            case "7":
                                System.out.println("Ingrese e-mail:");
                                c.setEmail(in.nextLine());
                                System.out.println("");
                                System.out.println("E-mail modificado : "+clientes.get(i).getEmail());
                                System.out.println("");
                                break;
                                
                            case "8":
                                System.out.println("Ingrese numero tarjeta:");
                                c.setNumTarjeta(in.nextLine());
                                System.out.println("");
                                System.out.println("Numero tarjeta modificado: "+clientes.get(i).getNumTarjeta());
                                System.out.println("");
                                break;  
                        }
                        f=false;
                        
                        System.out.println("Datos modificados exitosamente...");
                        System.out.println("");
                        
                        this.clientes.add(c);
                        Cliente.clienteKey++;
                }
            }
        }
        this.initSystem();
    }
    
    public void deleteCliente(){
        Cliente c = new Cliente();
        
        System.out.println("***************Eliminar Cliente*****************");
        System.out.println("");
        System.out.println("Ingrese idCliente a buscar:");
            
        String clt= in.nextLine();
            
        for(int i=0; i<clientes.size();i++){
            if(clt.equals(clientes.get(i).getIdCliente())){
                clientes.remove(i); 
            }
        }
        System.out.println("");
        System.out.println("Cliente eliminado exitosamente...");
        this.initSystem();
    } 
//*******************************************************************************************************************************************************************************************************************************************
    public void reporteReservas(){
        String option;
        boolean adminHotelStats = true;
        Date hoy = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //sirve para dar un formato sencillo a la fecha
        ArrayList<String> semana = new ArrayList();
        
        System.out.println("Seleccione la operacion que desea hacer:");
        System.out.println("1 - Ver Reporte semanal de reservaciones");
        System.out.println("2 - Ver Reporte semanal de ventas"       );
        System.out.println("3 - Regresar..."                         );
            
        System.out.println("opcion:"); 

        cal.setTime(hoy);
        hoy = cal.getTime();
        
        for(int i = 0; i < 7; i++){
            semana.add(sdf.format(hoy));
            cal.add(Calendar.DATE, 1);
            hoy = cal.getTime();
        }

        while(adminHotelStats){
        
            option = in.nextLine();

            switch (option) {
                case "1":
                    hoy = cal.getTime();
                    int idCliente;
                    for(Reservacion r : this.reservaciones){
                        if(r.getFechaHospedaje().equals(semana.get(0)) 
                                || r.getFechaHospedaje().equals(semana.get(1)) 
                                || r.getFechaHospedaje().equals(semana.get(2))
                                || r.getFechaHospedaje().equals(semana.get(3))
                                || r.getFechaHospedaje().equals(semana.get(4))
                                || r.getFechaHospedaje().equals(semana.get(5))
                                || r.getFechaHospedaje().equals(semana.get(6))                                
                                ){
                            
                            idCliente = r.getNumCliente();
                            Cliente c = this.clientes.get(this.buscarClientePorCodigo(Integer.toString(idCliente)));
                            
                            System.out.println("Reserva Numero: " + r.getCodReservacion());
                            System.out.println("\t"+"Cliente: " + c.getNombre() 
                                    + ",Fecha Hospedaje: " + r.getFechahospedaje() 
                                    + ", Habtiacion: " + r.getCodHabitacion()
                                    + ", Numero de Personas: " + r.getNumPersonas()
                                    + ", Dias Reserva: " + r.getDias()
                                    + ", Total: " + r.getTotalReserva()
                            );
                        }
                    }
        cal.add(Calendar.DATE, 10); // add 10 days

                                        
                    break;
                    
                case "2":
                    System.out.println("Lo sentimos, estamos trabajando para darle un buen servicio");
                    break;  
                    
                case "3":
                    adminHotelStats = false;
                    break;
                    
                default:
                    System.out.println("Favor ingrese una opcion valida");
                    break;
            }//fin swicth(adminHotelStats)
        }//fin while (adminHotelStats)
    }
//********************************************************************************************************************************************************************************************************************************************************
    private void agregarPaquete(){
        Paquete p = new Paquete();
        p.setCodPaquete(p.codPaquete);
        System.out.println("Ingrese el nombre del paquete");
        p.setNombre(in.nextLine());
        System.out.println("Ingrese la descripcion de servicios que brinda el paquete");
        p.setDescripcion(in.nextLine());
        System.out.println("Ingrese el precio del paquete");
        p.setPrecio(Double.parseDouble(in.nextLine()));
        p.configServiciosPaquete(servicios);
        this.packs.add(p);
        p.codPaquete++; //incrementando el codigo del piso para la proxima insercion
        System.out.println("Paquete agregado exitosamente con codigo: " + p.getCodPaquete());
        System.out.println("");
    }
    
    private int eliminarPaquete(int cod){
        int location = this.buscarPackPorCodigo(cod);
        if(location > -1 ){
            this.packs.remove(location);
            return 1;
        }else{
            return 0;
        }
    }
    
    private int modificarPaquete(int cod){
        int location = this.buscarPackPorCodigo(cod);
        if(location > -1 ){
            this.pisosHotel.remove(location);
            
            Paquete p = new Paquete();
            p.setCodPaquete(cod);
            System.out.println("Ingrese el nombre del paquete");
            p.setNombre(in.nextLine());
            System.out.println("Ingrese la descripcion de servicios que brinda el paquete");
            p.setDescripcion(in.nextLine());
            System.out.println("Ingrese el precio del paquete");
            p.setPrecio(Double.parseDouble(in.nextLine()));
            this.packs.add(location, p);
            System.out.println("Paquete modificado exitosamente con codigo: " + p.getCodPaquete());
            System.out.println("");
            return 1;
        }else{
            return 0;
        }
    }
    
    private int buscarServicioPorCodigo(int codServicio){
        int location = -1;
        //-1 es un valor por defecto, indica que no se encontró el piso solicitado
        //buscando a traves del arraylist el codigo del piso
        for(int i = 0; i < this.servicios.size(); i++){
            Servicio s = this.servicios.get(i);
            int cod = s.getCodServicio();

            if(cod == codServicio){
                location = i;
            }
        }
        return location;
    }
    
    private void gestionarServicios(){
        boolean servStats = true;
        String option;
        int pos;
        
        while(servStats){
            System.out.println("Seleccione la operacion que desea realizar:");
            System.out.println("1 - Ver servicios disponibles."            );
            System.out.println("2 - Agregar servicio."            );
            System.out.println("3 - Modificar Servicio"     );
            System.out.println("4 - Eliminar Servicio"        );
            System.out.println("5 - Regresar"        );
            
            option = in.nextLine();
            
            switch(option){
                case "1":
                    System.out.println("SERVICIOS DISPONIBLES EN EL SISTEMA");
                    for(Servicio serv : this.servicios){
                        System.out.println("Codigo: " + serv.getCodServicio() + ", Nombre: " + serv.getNombre() );
                    }
                    
                    break;
                
                case "2":
                    Servicio s = new Servicio();
                    System.out.println("Ingrese el Nombre del servicio");
                    s.setNombre(in.nextLine());
                    s.setCodServicio(Servicio.servKey);
                    this.servicios.add(s);
                    Servicio.servKey++;
                    System.out.println("Servicio agregado exitosamente");
                    break;
                    
                case "3":
                    System.out.println("SERVICIOS DISPONIBLES EN EL SISTEMA");
                    for(Servicio serv : this.servicios){
                        System.out.println("Codigo: " + serv.getCodServicio() + ", Nombre: " + serv.getNombre() );
                    }
                    
                    System.out.println("Ingrese el codigo del servicio");
                    int cod = Integer.parseInt(in.nextLine());
                    
                    if(this.buscarServicioPorCodigo(cod) == -1){
                        System.out.println("El codigo ingresado no existe");
                    }else{
                        Servicio mod = new Servicio();
                        System.out.println("Ingrese el Nombre del servicio");
                        mod.setNombre(in.nextLine());
                        mod.setCodServicio(cod);
                        this.servicios.remove(this.buscarServicioPorCodigo(cod));
                        
                        if(servicios.size()==0){
                            this.servicios.add(mod);
                        }else{
                            this.servicios.add(this.buscarServicioPorCodigo(cod), mod);
                        }
                    
                        System.out.println("Servicio modificado exitosamente");
                    }
                    break;
                
                case "4":
                    System.out.println("SERVICIOS DISPONIBLES EN EL SISTEMA");
                    for(Servicio serv : this.servicios){
                        System.out.println("Codigo: " + serv.getCodServicio() + ", Nombre: " + serv.getNombre() );
                    }
                    
                    System.out.println("Ingrese el codigo del servicio");
                    pos = Integer.parseInt(in.nextLine());
                    
                    if(this.buscarServicioPorCodigo(pos) == -1){
                        System.out.println("El codigo ingresado no existe");
                    }else{
                        this.servicios.remove(pos);
                        System.out.println("Servicio removido exitosamente");
                    }
                    
                    break;
                    
                case "5":
                    servStats = false;
                    break;
                    
                default:
                    System.out.println("Ingrese una opcion valida, por favor");
                    break;
            } 
        }
    }  
    /*
    agregarPiso
    Sirve para agregar un piso adicional al edificio
    */
    public void agregarPiso(){
        Piso p = new Piso();
        p.setCodPiso(Character.toString(Piso.letraPiso));
        this.pisosHotel.add(p);
        Piso.letraPiso++; //incrementando el codigo del piso para la proxima insercion
        System.out.println("Piso agregado exitosamente con codigo: " + p.getCodPiso());
        System.out.println("");
    }
    
    /*eliminarPiso
    Sirve para eliminar un piso del edificio.
    PARAMETROS
    codigoPiso: Codigo del piso a eliminar
    RETORNA
    1: Eliminacion exitosa
    0: Eliminacion fallida
    */
    
    private int eliminarPiso(String codPiso){
        int location = this.buscarPisoPorCodigo(codPiso);
        if(location > -1 ){
            this.pisosHotel.remove(location);
            return 1;
        }else{
            return 0;
        }
    }
    
    /*
    buscarPisoPorCodigo
    Sirve para buscar un piso por código
    retorna la ubicacion del piso que se quiere buscar
    retorna -1 si no se encuentra nada en el ArrayList
    */
    
    private int buscarPisoPorCodigo(String codigo){
        int pisoLocation = -1;
        //-1 es un valor por defecto, indica que no se encontró el piso solicitado
        //buscando a traves del arraylist el codigo del piso
        for(int i = 0; i < pisosHotel.size(); i++){
            Piso p = pisosHotel.get(i);
            String codPiso = p.getCodPiso();

            if(codPiso.equals(codigo)){
                pisoLocation = i;
            }
        }
        return pisoLocation;
    }
    
    private int buscarPackPorCodigo(int codigo){
        int pisoLocation = -1;
        //-1 es un valor por defecto, indica que no se encontró el piso solicitado
        //buscando a traves del arraylist el codigo del piso
        for(int i = 0; i < packs.size(); i++){
            Paquete p = packs.get(i);
            int cod = p.getCodPaquete();

            if(cod == codigo){
                pisoLocation = i;
            }
        }
        return pisoLocation;
    }
 
    private int buscarClientePorCodigo(String codigo){
        int location = -1;
        //-1 es un valor por defecto, indica que no se encontró el piso solicitado
        //buscando a traves del arraylist el codigo del piso
        for(int i = 0; i < this.clientes.size(); i++){
            Cliente c = this.clientes.get(i);
            String codPiso = c.getIdCliente();

            if(codPiso.equals(codigo)){
                location = i;
            }
        }
        return location;
    }
}
