package Modelo;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase que permite otorgarle una logica de programacion a un juego basico con 
 * la ayuda de varios componentes tanto como una matriz de posiciones, un arreglo de matrices de 
 * lugares existentes y ocupados por una 
 * ficha, contadores y acumuladores para distintos usos de las funciones
 * @author Andrés Morales
 */
public class ObjetoImagen {
    
    private int Jugador, Jugador1, Jugador2, Contador, Posicion, Lista, MatrizAux;
    private ArrayList <int [][]> Matriz;
    private int[][] Eliminado;
    private ImageIcon Imagen = new ImageIcon();
    
    /**
     * Constructor basico de la clase ObjetoImagen que establece los componentes
     * necesarios para el funcionamiento del juego
     */
    public ObjetoImagen()
    {
        this.Jugador = 2;
        this.Jugador1 = 0;
        this.Jugador2 = 0;
        this.Contador= 0;
        this.Lista= 0;
        this.MatrizAux= 0;
        this.Posicion = 1;
        this.Matriz = new <int [][]> ArrayList();
        this.Eliminado = new int [6][8];
        this.Matriz.add(new int[3][3]);
        this.Matriz.add(new int[3][3]);
        this.Matriz.add(new int[3][3]);
    }
    /**
     * Fucnion que permite retornar la lista de posiciones cuyas fichas ya hayan 
     * sido unidas horizontal o verticalmente, esto para que no se conserven posiciones 
     * "memoria" en las jugadas que ya permitieron robar una ficha al contrincante
     * @return 
     */
    public int[][] getEliminado() {
        return Eliminado;
    }

    /**
     * Funcion que permite establecer el numero de fichas robadas por parte del 
     * jugador 1
     * @return
     */
    public int getJugador1() {
        return Jugador1;
    }

    /**
     * Funcion que permite establecer el numero de fichas robadas por parte del 
     * jugador 1
     * @param Jugador1
     */
    public void setJugador1(int Jugador1) {
        this.Jugador1 = Jugador1;
    }

    /**
     * Funcion que retorna el numero de fichas robadas por parte del 
     * jugador 2
     * @return
     */
    public int getJugador2() {
        return Jugador2;
    }

    /**
     * Funcion que permite establecer el numero de fichas robadas por parte del 
     * jugador 2
     * @param Jugador2
     */
    public void setJugador2(int Jugador2) {
        this.Jugador2 = Jugador2;
    }

    
    /**
     * Funcion que retorna la imagen que sera tenida en cuanta para 
     * modificar los iconos de las fichas correspondientes de cada jugador
     * @return
     */
    public ImageIcon getImagen() {
        return Imagen;
    }

    /**
     * Funcion que permite establecer la imagen que sera tenida en cuanta para 
     * modificar los iconos de las fichas correspondientes de cada jugador
     * @param Imagen
     */
    public void setImagen(ImageIcon Imagen) {
        this.Imagen = Imagen;
    }

    /**
     * Funcion que permite retornar el numero de la posicion actual que se usa 
     * para recorrer la matris de posiciones
     * @return
     */
    public int getPosicion() {
        return Posicion;
    }

    /**
     * Funcion que permite retornar la matriz de posiciones que se usara para 
     * obtener una referencia de los 24 lugares disponibles dentro del tablero
     * los cuales seran tenidos en cuenta para saber que jugador ha colocado una 
     * ficha en determinada posicion
     * @return
     */
    public ArrayList<int[][]> getMatriz() {
        return Matriz;
    }

    /**
     * Funcion que permite retornar el contador de numero de jugadas realizadas 
     * @return
     */
    public int getContador() {
        return Contador;
    }
    
    /**
     * Funcion que permite cambiar la ficha y el jugador que se encuentra actual-
     * mente en juego al contrincante
     */
    public void cambiarFicha()
    {
        switch(Jugador)
        {
            case 1:
            {
                Imagen = new ImageIcon("src/Imagen/Ficha1-2.png");
                Jugador = 2;
                Contador++;
                break;
            }
            
            case 2:
            {
                Imagen = new ImageIcon("src/Imagen/Ficha2-2.png");
                Jugador = 1;
                Contador++;
                break;
            }
        }
    }
    
    /**
     * Funcion que permite comprobar si ya se han realizado menos o mas movimientos
     * de los nueve permitidos para cada jugador
     * @return
     */
    public boolean Jugador ()
    {
        if(Contador < 19)
            return true;
        
        else
            return false;
    }
    
    /**
     * Funcion que permite verificar si en el lugar a colocar la ficha se encuentra disponible;
     * o, por el contrario, se encuentra ocupada por la ficha de algun jugador
     * @param Lugar
     * @return
     */
    public boolean posicionCheck(int Lugar)    
    {
            Posicion = 1;
            int it = 0;
            for(int [][] temp : Matriz)
            {
                for(int f=0; f<3; f++)
                {
                    for(int c=0; c<3; c++)
                    {
                        if(c!=1 || f!=1)
                        {
                            if(this.Posicion == Lugar)
                            {
                                
                                    if(temp[f][c] == 2 || temp[f][c] == 1)
                                        it = 1;
                                    
                            }
                            this.Posicion++;
                        }
                    }
                }       
            }

            if (it == 1)
                return true;

            else 
                return false;
    }
    
    /**
     * Funcion que permite reconocer si hay tres fichas juntas para prodceder a 
     * eliminar una ficha del oponente
     * @return
     */
    public boolean comprobarFicha()
    {
        int it = 0;
        int temp[][];
        for(int m=0; m<3; m++)
            {
                temp = this.Matriz.get(m);

                if(((temp[0][0] == 2 && temp[0][1] == 2 && temp[0][2] == 2) || (temp[0][0] == 1 && temp[0][1] == 1 && temp[0][2] == 1)) && (Eliminado[m][0]==0))
                {
                    it = 1;
                    Eliminado[m][0]=1;
                    MatrizAux = m;
                    Lista = 1;
                }

                if( ((temp[2][0] == 2 && temp[2][1] == 2 && temp[2][2] == 2) || (temp[2][0] == 1 && temp[2][1] == 1 && temp[2][2] == 1)) && (Eliminado[m][1]==0))
                {
                    it = 1;
                    Eliminado[m][1]=1;
                    MatrizAux = m;
                    Lista = 2;
                }

                if(((temp[0][0] == 2 && temp[1][0] == 2 && temp[2][0] == 2) || (temp[0][0] == 1 && temp[1][0] == 1 && temp[2][0] == 1)) && (Eliminado[m][2]==0))
                {
                    it = 1;
                    Eliminado[m][2]=1;
                    MatrizAux = m;
                    Lista = 3;
                }

                if(((temp[0][2] == 2 && temp[1][2] == 2 && temp[2][2] == 2) || (temp[0][2] == 1 && temp[1][2] == 1 && temp[2][2] == 1)) && (Eliminado[m][3]==0))
                {
                    it = 1;
                    Eliminado[m][3]=1;
                    MatrizAux = m;
                    Lista = 4;
                }
                
        }
        
        if(((Matriz.get(0)[1][0] == 2 && Matriz.get(1)[1][0] == 2 && Matriz.get(2)[1][0] == 2) || (Matriz.get(0)[1][0] == 1 && Matriz.get(1)[1][0] == 1 && Matriz.get(2)[1][0] == 1)) && (Eliminado[4][0] == 0))
                {
                    it = 1;
                    Eliminado[4][0] = 1;
                    Lista = 5;
                }

                if(((Matriz.get(0)[0][1] == 2 && Matriz.get(1)[0][1] == 2 && Matriz.get(2)[0][1] == 2) || (Matriz.get(0)[0][1] == 1 && Matriz.get(1)[0][1] == 1 && Matriz.get(2)[0][1] == 1)) && (Eliminado[4][1] == 0))
                {
                    it = 1;
                    Eliminado[4][1] = 1;
                    Lista = 6;
                }

                if(((Matriz.get(0)[2][1] == 2 && Matriz.get(1)[2][1] == 2 && Matriz.get(2)[2][1] == 2) || (Matriz.get(0)[2][1] == 1 && Matriz.get(1)[2][1] == 1 && Matriz.get(2)[2][1] == 1)) && (Eliminado[5][0] == 0))
                {
                    it = 1;
                    Eliminado[5][0] = 1;
                    Lista = 7;
                }

                if(((Matriz.get(0)[1][2] == 2 && Matriz.get(1)[1][2] == 2 && Matriz.get(2)[1][2] == 2) || (Matriz.get(0)[1][2] == 1 && Matriz.get(1)[1][2] == 1 && Matriz.get(2)[1][2] == 1)) && (Eliminado[5][1] == 0))
                {
                    it = 1;
                    Eliminado[5][1] = 1;
                    Lista = 8;
                }
        
    if(it == 1)    
            return true;

        else 
            return false;
    }
    
    /**
     * Funcion que permite establecer en la matriz de posiciones el jugador que 
     * realizo un movimiento colocando una fucha en determinada posicion
     * @param Posicion
     */
    public void establecer(int Posicion)
    {
        this.Posicion = 1;
        for(int [][] temp : Matriz)
        {
            for(int f=0; f<3; f++)
            {
                for(int c=0; c<3; c++)
                {
                    if(c!=1 || f!=1)
                    {
                        if(this.Posicion == Posicion)
                        {
                            System.out.println(Posicion + "|||");
                            System.out.println(f);
                            temp[f][c]=this.Jugador;
                            System.out.println(" " + c);
                            System.out.println(" |Jugador| " + this.Jugador);
                        }
                        
                        this.Posicion++;
                    }
                }
            }
        }
    }
    
    /**
     * Funcion sobrecargada que permite establecer en la matriz de posiciones un 
     * jugador definido
     * @param Posicion
     * @param Jugador
     */
    public void establecer(int Posicion, int Jugador)
    {
        this.Posicion = 1;
        for(int [][] temp : Matriz)
        {
            for(int f=0; f<3; f++)
            {
                for(int c=0; c<3; c++)
                {
                    if(c!=1 || f!=1)
                    {
                        if(this.Posicion == Posicion)
                        {
                            System.out.println(f);
                            temp[f][c]=Jugador;
                            System.out.println(" " + c);
                            System.out.println(" ||| " + Jugador);
                        }
                        
                        this.Posicion++;
                    }
                }
            }
        }
    }
    
    /**
     * Funcion que permite comprobar si en una determinada posicion de la matriz
     * se encuentra alguna ficha de determinado jugador
     * @param Lugar
     * @param Jugador
     * @return
     */
    public boolean comprobarJugador(int Lugar, int Jugador)
    {
        Posicion = 1;
        int Turno = 0;
        for(int [][] temp : Matriz)
        {
            for(int f=0; f<3; f++)
            {
                for(int c=0; c<3; c++)
                {
                    if(c!=1 || f!=1)
                    {
                        if(this.Posicion == Lugar)
                        {
                            
                            if (temp[f][c] == Jugador)
                                Turno = 1;
                        }
                        
                        this.Posicion++;
                    }
                }
            }
        }
        
        if(Turno == 1)
            return true;
        
        else
            return false;
    }
    
    /**
     * Funcion que permite obtener el jugador que se encuentra en turno activo
     * @return
     */
    public int getJugador() {
        return Jugador;
    }
    
    /**
     * Funcion que permite restablecer todos las matrices de posiciones y el 
     * contador de jugadas para una nueva partida
     */
    public void restablecer()
    {
       this.Jugador = 2;
        this.Jugador1 = 0;
        this.Jugador2 = 0;
        this.Contador= 0;
        this.Lista= 0;
        this.MatrizAux= 0;
        this.Posicion = 1;
        this.Matriz.clear();
        this.Matriz = new <int [][]> ArrayList();
        this.Eliminado = new int [6][8];
        this.Matriz.add(new int[3][3]);
        this.Matriz.add(new int[3][3]);
        this.Matriz.add(new int[3][3]); 
    }
    
}
