package com.juangg.practica8contactos;

public class cContacto {

    private String codigo;
    private String nombre;
    private String apellido;
    private String telefono;


    protected cContacto(String cod, String nombre,String apellido, String telefono){
        this.codigo = cod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public void setCodigo(String codigo) { this.codigo=codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }


}
