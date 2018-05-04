/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;
/*
 * @author Nelson Flamenco
*/
public class Venta {
    private int codVenta;
    private int codReservacion;
    private String fecha;
    private String res1;
    private String res2;
    private double total;
    
    public Venta(int codVenta,int codReservacion,String fecha,String res1,String res2,double total){
        this.codVenta=codVenta;
        this.codReservacion=codReservacion;
        this.fecha=fecha;
        this.res1=res1;
        this.res2=res2;
        this.total=total;
    }
    
    public Venta(){}
    
    public int getCodVenta() {
        return codVenta;
    }
    
    public int getCodReservacion() {
        return codReservacion;
    }
    
    public String getFecha() {
        return fecha;
    }

    public String getRes1() {
        return res1;
    }
    
    public String getRes2() {
        return res2;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }
    
    public void setCodReservacion(int codReservacion) {
        this.codReservacion = codReservacion;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void setRes1(String res1) {
        this.res1 = res1;
    }
    
    public void setRes2(String res2) {
        this.res2 = res2;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
}
