package com.example.ej2settings;

public class DatosPersonales {

    private String nombre;
    private String email;

    private String edad;

    private String publi;

    public DatosPersonales(String n, String e, String em, String pu) {
        this.setNombre( n );
        this.setEdad(e);
        this.setEmail( em );
        this.setPubli( pu );
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPubli() {
        return publi;
    }

    public void setPubli(String publi) {
        this.publi = publi;
    }

    public String getEmail() {
        return email;
    }

    public String getEdad() {
        return edad;
    }

    public String getNombre(){
        return nombre;
    }

    @Override
    public String toString() {
        return "DatosPersonales{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", edad='" + edad + '\'' +
                ", publi='" + publi + '\'' +
                '}';
    }
}
