/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import Controlador.Controlador;

/**
 *
 * @author Andrés
 */
public class Juego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Controlador ObjC = new Controlador();
        ObjC.iniciar();
        /*int temp[][]= new int[2][2];
        String aux[][]= new String[2][2];
        int pos=1;
        for(int m=0; m<3; m++)
        {
            for(int f=0; f<3; f++)
            {
                for(int c=0; c<3; c++)
                {
                    if(c!=1 || f!=1)
                    {
                        //temp[f][c]=2;
                        //aux[f][c]=Lugar;
                        System.out.print(" " + m);
                        System.out.print(" " + f);
                        System.out.print(" "+ c);
                        System.out.println(" "+ pos++);
                        if (pos == 9)
                            pos = 1;
                    }
                }
            }
            //this.Matriz.set(m, temp);
            //this.Nombres.set(m, aux);
        }*/
    }
}
    

