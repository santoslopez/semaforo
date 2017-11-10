

/**
 *
 * @author santoslopez
 */
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Semaforo {
    
    //dependiendo del valor del contador vamos a cambiar la imagen (rojo, amarillo y verde)
    private static int contador = 0 ;
    
    private static int velocidad;
    private static Timer timer;
    private static Semaforo instancia;
    private static TimerTask timerTask;
    
    //patron de diseno singleton para crear una unica instancia de la clase
    public static Semaforo getInstancia(){
        if (instancia==null) {
            instancia=new Semaforo();
        }
        return instancia;
    }
    
    //metodo que me permite cambiar la imagen del JLabel
    public void cambiarImagen(String imagen){
        
        /*
        Como tenemos las imagenes dentro de un packete entonces:
        
        Para cargar la imagen podemos indicarle a ImageIcon el recurso que se quiere utilizar. 
        Para instanciar el recurso debemos indicar el path relativo (desde la clase que quiere cargar la imagen)
        como el path absoluto de la imagen dentro del Jar (un path absoluto en el jar comienza con /, 
        como por ejemplo “/imagenes/"+imagen").
        */
        Icon icono = new ImageIcon(getClass().getResource("/imagenes/"+imagen));
        ImagenInicio.getInstancia().lblColorSemaforo.setIcon(icono);        
    }
    
    public static void main(String[] args){
        //cada cierto tiempo cambiamos la imagen
        velocidad = 2;
        
        int velocidadMilisegundo = velocidad*1000;
        ImagenInicio.getInstancia().setVisible(true);
        
        
        //creamos la tarea, o cambiar la imagen
        timerTask = new TimerTask() {
            @Override
            public void run() {
                
                switch(contador){
                    //color rojo
                    case 0:
                        contador = 1;
                        Semaforo.getInstancia().cambiarImagen("rojo.png");
                        break;
                        
                    //color amarillo
                    case 1:
                        contador = 2;
                        Semaforo.getInstancia().cambiarImagen("amarillo.png");
                        break;
                    
                    //color verde
                    case 2:
                        contador = 0;//reseteamos el contador en 0 para que pueda volver a empezar en la imagen rojo
                        Semaforo.getInstancia().cambiarImagen("verde.png");
                        break;
                }
            }
        };
        timer = new Timer();
        
        //que se repita periodicamente, vamos cambiando la imagen cada cierto tiempo
        timer.scheduleAtFixedRate(timerTask, velocidadMilisegundo, velocidadMilisegundo);        
    }
}
