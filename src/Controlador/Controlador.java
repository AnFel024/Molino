package Controlador;

import Modelo.ObjetoImagen;
import Vista.Cerrar;
import Vista.Reglas;
import Vista.Ventana;
import Vista.VentanaC;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



/**
 * Clase que permite inicializar, instanciar y ejecutar los componentes graficos 
 * y logicos del juego
 * @author Andrés Morales Anaya
 */
public class Controlador implements MouseListener, ActionListener, Runnable{
    
    private Ventana FormV;
    private VentanaC FormC;
    private Reglas FormR;
    private Cerrar FormT;
    private ObjetoImagen ObjOM;
    private ImageIcon ObjI;
    private int Contador, Segundo, Jugador, Posicion;
    private Thread Hilo;
    private JProgressBar PgbTiempo;
    private JLabel LblSegundo;
    
    /**
     * Constructor basico que permite instanciar los componentes logicos del 
     * programa, tanto graficos como de la parte del paquete Modelo
     */
    public Controlador()
    {
        this.FormV = new Ventana();
        this.FormC = new VentanaC();
        this.FormT = new Cerrar();
        this.FormR = new Reglas();
        this.ObjOM = new ObjetoImagen();
        this.ObjI = new ImageIcon();
        this.Contador = 0;
        this.Jugador = 0;
        this.Segundo = 30;
        this.Posicion = 0;
        this.Hilo = new Thread(this);
        this.PgbTiempo = new JProgressBar();
        this.LblSegundo = new JLabel();
        this.PgbTiempo.setValue(30);
        this.PgbTiempo.setMinimum(0);
        this.PgbTiempo.setMaximum(30);
        this.FormV.getLblPos1().addMouseListener(this);
        this.FormV.getLblPos2().addMouseListener(this);
        this.FormV.getLblPos3().addMouseListener(this);
        this.FormV.getLblPos4().addMouseListener(this);
        this.FormV.getLblPos5().addMouseListener(this);
        this.FormV.getLblPos6().addMouseListener(this);
        this.FormV.getLblPos7().addMouseListener(this);
        this.FormV.getLblPos8().addMouseListener(this);
        this.FormV.getLblPos9().addMouseListener(this);
        this.FormV.getLblPos10().addMouseListener(this);
        this.FormV.getLblPos11().addMouseListener(this);
        this.FormV.getLblPos12().addMouseListener(this);
        this.FormV.getLblPos13().addMouseListener(this);
        this.FormV.getLblPos14().addMouseListener(this);
        this.FormV.getLblPos15().addMouseListener(this);
        this.FormV.getLblPos16().addMouseListener(this);
        this.FormV.getLblPos17().addMouseListener(this);
        this.FormV.getLblPos18().addMouseListener(this);
        this.FormV.getLblPos19().addMouseListener(this);
        this.FormV.getLblPos20().addMouseListener(this);
        this.FormV.getLblPos21().addMouseListener(this);
        this.FormV.getLblPos22().addMouseListener(this);
        this.FormV.getLblPos23().addMouseListener(this);
        this.FormV.getLblPos24().addMouseListener(this);
        this.FormC.getBtnIniciar().addActionListener(this);
        this.FormC.getBtnReglas().addActionListener(this);
        this.FormR.getBtnSalir().addActionListener(this);
        this.FormV.getBtnTabla().addActionListener(this);
        this.FormT.getBtnSi().addActionListener(this);
        this.FormT.getBtnNo().addActionListener(this);
    }
    
    /**
     * Funcion que permite retornar la clase utilizada para la ejecucion del 
     * juego
     * @return
     */
    public ObjetoImagen getObjetoImagen()
    {
        return ObjOM;
    }
    
    /**
     * Funcion que permite visualizar el JFrame principal del juego
     */
    public void iniciar()
    {
        ObjOM.setImagen(new ImageIcon("src/Imagen/Molino.jpg"));
        ObjI = new ImageIcon(ObjOM.getImagen().getImage().getScaledInstance(FormC.getLblIcono().getWidth(), FormC.getLblIcono().getHeight(), Image.SCALE_DEFAULT));
        FormC.getLblIcono().setIcon(ObjI);
        FormC.setVisible(true);
        
         
    }

    /**
     * Funcion que permite cambiar una posicion que se pre-visualizo durante el 
     * turno del jugador
     * @param lblBorde
     * @param Lugar
     */
    public void  cambiarBorde(JLabel lblBorde, int Lugar)
    {
            if (ObjOM.posicionCheck(Lugar) == false)
            {
                ImageIcon Imagen1 = ObjOM.getImagen();
                Icon Ficha1 = new ImageIcon(Imagen1.getImage().getScaledInstance(lblBorde.getWidth(), lblBorde.getHeight(), Image.SCALE_DEFAULT));
                lblBorde.setIcon(Ficha1);
                lblBorde.repaint();
            }
    }
    
    /**
     * Funcion que coloca una ficha en una determinada posicion dentro del tablero
     * @param lblBorde
     * @param Lugar
     */
    public void  establecerBorde(JLabel lblBorde, int Lugar)
    {
        if(ObjOM.posicionCheck(Lugar) == false && Contador == 0)
        {
            ObjOM.establecer(Lugar);
            ImageIcon Imagen1 = ObjOM.getImagen();
            Icon Ficha1 = new ImageIcon(Imagen1.getImage().getScaledInstance(lblBorde.getWidth(), lblBorde.getHeight(), Image.SCALE_DEFAULT));
            lblBorde.setIcon(Ficha1);
            lblBorde.repaint();
            cambiarTiempo(); 
        }
        
        if(ObjOM.comprobarFicha() == true)
        {
            if (ObjOM.getContador() != 19 || Contador != 1)
            {
                JOptionPane.showMessageDialog(FormT, "Elimine una ficha");
                Contador++;
            }
        }
        
        else if (Contador == 1)
        {
            if(ObjOM.comprobarJugador(Lugar, ObjOM.getJugador()) == true && ObjOM.comprobarJugador(Lugar, 0) == false)// && ObjOM.comprobarJugador(Lugar) != 0)
            {
                lblBorde.setBorder(null);
                lblBorde.setIcon(null);
                ImageIcon Imagen1 = ObjOM.getImagen();
                Icon Ficha1 = new ImageIcon(Imagen1.getImage().getScaledInstance(lblBorde.getWidth(), lblBorde.getHeight(), Image.SCALE_DEFAULT));
                lblBorde.setIcon(Ficha1);
                lblBorde.repaint();
                this.eliminarBorde(lblBorde, Lugar);
                this.ObjOM.establecer(Lugar, 0);
                if(ObjOM.getJugador() == 1)
                    this.ObjOM.setJugador1(ObjOM.getJugador1() + 1);
                
                else
                    this.ObjOM.setJugador2(ObjOM.getJugador2() + 1);
                
                Contador = 0;
            }
            
        }
    }
    
    /**
     * Funcion que permite eliminar un borde establecido, ya sea uno que este fijo
     * o uno que sirvio para pre-visualizar una ficha en aquel borde
     * @param lblBorde
     * @param Lugar
     */
    public void eliminarBorde(JLabel lblBorde, int Lugar)
    {
        if(ObjOM.posicionCheck(Lugar) == false || this.Posicion != 0)
        {
            lblBorde.setBorder(null);
            lblBorde.setIcon(null);
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(ObjOM.Jugador() == true)
        {
            if(e.getSource() == FormV.getLblPos1())
                establecerBorde(FormV.getLblPos1(), 1);
        
            if(e.getSource() == FormV.getLblPos2())
                establecerBorde(FormV.getLblPos2(), 2);
        
            if(e.getSource() == FormV.getLblPos3())
                establecerBorde(FormV.getLblPos3(), 3);
        
            if(e.getSource() == FormV.getLblPos4())
                establecerBorde(FormV.getLblPos4(), 4);
        
            if(e.getSource() == FormV.getLblPos5())
                establecerBorde(FormV.getLblPos5(), 5);
        
            if(e.getSource() == FormV.getLblPos6())
                establecerBorde(FormV.getLblPos6(), 6);   
        
            if(e.getSource() == FormV.getLblPos7())
                establecerBorde(FormV.getLblPos7(), 7);
        
            if(e.getSource() == FormV.getLblPos8())
                establecerBorde(FormV.getLblPos8(), 8);
        
            if(e.getSource() == FormV.getLblPos9())
                establecerBorde(FormV.getLblPos9(), 9);
        
            if(e.getSource() == FormV.getLblPos10())
                establecerBorde(FormV.getLblPos10(), 10);
        
            if(e.getSource() == FormV.getLblPos11())
                establecerBorde(FormV.getLblPos11(), 11);
                        
            if(e.getSource() == FormV.getLblPos12())
                establecerBorde(FormV.getLblPos12(), 12);
                        
            if(e.getSource() == FormV.getLblPos13())
                establecerBorde(FormV.getLblPos13(), 13);
                        
            if(e.getSource() == FormV.getLblPos14())
                establecerBorde(FormV.getLblPos14(), 14);
                        
            if(e.getSource() == FormV.getLblPos15())
                establecerBorde(FormV.getLblPos15(), 15);
                       
            if(e.getSource() == FormV.getLblPos16())
                establecerBorde(FormV.getLblPos16(), 16);
      
            if(e.getSource() == FormV.getLblPos17())
                establecerBorde(FormV.getLblPos17(), 17);
        
            if(e.getSource() == FormV.getLblPos18())
                establecerBorde(FormV.getLblPos18(), 18);
     
            if(e.getSource() == FormV.getLblPos19())
                establecerBorde(FormV.getLblPos19(), 19);
        
            if(e.getSource() == FormV.getLblPos20())
                establecerBorde(FormV.getLblPos20(), 20);
        
            if(e.getSource() == FormV.getLblPos21())
                establecerBorde(FormV.getLblPos21(), 21);
        
            if(e.getSource() == FormV.getLblPos22())
                establecerBorde(FormV.getLblPos22(), 22);
        
            if(e.getSource() == FormV.getLblPos23())
                establecerBorde(FormV.getLblPos23(), 23);
        
            if(e.getSource() == FormV.getLblPos24())
                establecerBorde(FormV.getLblPos24(), 24);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {   
        if (ObjOM.Jugador() == true)
        {
            if(e.getSource() == FormV.getLblPos1())
                cambiarBorde(FormV.getLblPos1(),1);    
            
            if(e.getSource() == FormV.getLblPos2())
                cambiarBorde(FormV.getLblPos2(), 2);
            
            if(e.getSource() == FormV.getLblPos3())
                cambiarBorde(FormV.getLblPos3(), 3);
            
            if(e.getSource() == FormV.getLblPos4())
                cambiarBorde(FormV.getLblPos4(), 4);
            
            if(e.getSource() == FormV.getLblPos5())
                cambiarBorde(FormV.getLblPos5(), 5);
            
            if(e.getSource() == FormV.getLblPos6())
                cambiarBorde(FormV.getLblPos6(), 6);   
            
            if(e.getSource() == FormV.getLblPos7())
                cambiarBorde(FormV.getLblPos7(), 7);
            
            if(e.getSource() == FormV.getLblPos8())
                cambiarBorde(FormV.getLblPos8(), 8);
            
            if(e.getSource() == FormV.getLblPos9())
                cambiarBorde(FormV.getLblPos9(), 9);
            
            if(e.getSource() == FormV.getLblPos10())
                cambiarBorde(FormV.getLblPos10(), 10);
            
            if(e.getSource() == FormV.getLblPos11())
                cambiarBorde(FormV.getLblPos11(), 11);
            
            if(e.getSource() == FormV.getLblPos12())
                cambiarBorde(FormV.getLblPos12(), 12);
            
            if(e.getSource() == FormV.getLblPos13())
                cambiarBorde(FormV.getLblPos13(), 13);
            
            if(e.getSource() == FormV.getLblPos14())
                cambiarBorde(FormV.getLblPos14(), 14);
            
            if(e.getSource() == FormV.getLblPos15())
                cambiarBorde(FormV.getLblPos15(), 15);
            
            if(e.getSource() == FormV.getLblPos16())
                cambiarBorde(FormV.getLblPos16(), 16);
            
            if(e.getSource() == FormV.getLblPos17())
                cambiarBorde(FormV.getLblPos17(), 17);
            
            if(e.getSource() == FormV.getLblPos18())
                cambiarBorde(FormV.getLblPos18(), 18);
            
            if(e.getSource() == FormV.getLblPos19())
                cambiarBorde(FormV.getLblPos19(), 19);
            
            if(e.getSource() == FormV.getLblPos20())
                cambiarBorde(FormV.getLblPos20(), 20);
            
            if(e.getSource() == FormV.getLblPos21())
                cambiarBorde(FormV.getLblPos21(), 21);
            
            if(e.getSource() == FormV.getLblPos22())
                cambiarBorde(FormV.getLblPos22(), 22);
            
            if(e.getSource() == FormV.getLblPos23())
                cambiarBorde(FormV.getLblPos23(), 23);
            
            if(e.getSource() == FormV.getLblPos24())
                cambiarBorde(FormV.getLblPos24(), 24);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(ObjOM.Jugador() == true)
        {
            if(e.getSource() == FormV.getLblPos1())
                eliminarBorde(FormV.getLblPos1(),1);    
        
            if(e.getSource() == FormV.getLblPos2())
                eliminarBorde(FormV.getLblPos2(), 2);
            
            if(e.getSource() == FormV.getLblPos3())
                 eliminarBorde(FormV.getLblPos3(), 3);
            
            if(e.getSource() == FormV.getLblPos4())
                 eliminarBorde(FormV.getLblPos4(), 4);
            
            if(e.getSource() == FormV.getLblPos5())
                eliminarBorde(FormV.getLblPos5(), 5);
            
            if(e.getSource() == FormV.getLblPos6())
                eliminarBorde(FormV.getLblPos6(), 6);   
            
            if(e.getSource() == FormV.getLblPos7())
                eliminarBorde(FormV.getLblPos7(), 7);
            
            if(e.getSource() == FormV.getLblPos8())
                eliminarBorde(FormV.getLblPos8(), 8);
            
            if(e.getSource() == FormV.getLblPos9())
                eliminarBorde(FormV.getLblPos9(), 9);
            
            if(e.getSource() == FormV.getLblPos10())
                eliminarBorde(FormV.getLblPos10(), 10);
            
            if(e.getSource() == FormV.getLblPos11())
                eliminarBorde(FormV.getLblPos11(), 11);
            
            if(e.getSource() == FormV.getLblPos12())
                eliminarBorde(FormV.getLblPos12(), 12);
            
            if(e.getSource() == FormV.getLblPos13())
                eliminarBorde(FormV.getLblPos13(), 13);
            
            if(e.getSource() == FormV.getLblPos14())
                eliminarBorde(FormV.getLblPos14(), 14);
            
            if(e.getSource() == FormV.getLblPos15())
                eliminarBorde(FormV.getLblPos15(), 15);
            
            if(e.getSource() == FormV.getLblPos16())
                eliminarBorde(FormV.getLblPos16(), 16);
            
            if(e.getSource() == FormV.getLblPos17())
                eliminarBorde(FormV.getLblPos17(), 17);
            
            if(e.getSource() == FormV.getLblPos18())
                eliminarBorde(FormV.getLblPos18(), 18);
            
            if(e.getSource() == FormV.getLblPos19())
                eliminarBorde(FormV.getLblPos19(), 19);
            
            if(e.getSource() == FormV.getLblPos20())
                eliminarBorde(FormV.getLblPos20(), 20);
            
            if(e.getSource() == FormV.getLblPos21())
                eliminarBorde(FormV.getLblPos21(), 21);
            
            if(e.getSource() == FormV.getLblPos22())
                eliminarBorde(FormV.getLblPos22(), 22);
            
            if(e.getSource() == FormV.getLblPos23())
                eliminarBorde(FormV.getLblPos23(), 23);
            
            if(e.getSource() == FormV.getLblPos24())
                eliminarBorde(FormV.getLblPos24(), 24);
        }
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(FormC.getBtnIniciar() == e.getSource())
        {
            ObjOM.setImagen(new ImageIcon("src/Imagen/Ficha1-2.png"));
            ObjI = new ImageIcon(ObjOM.getImagen().getImage().getScaledInstance(FormV.getLblContrincante().getWidth(), FormV.getLblContrincante().getHeight(), Image.SCALE_DEFAULT));
            FormV.getLblContrincante().setIcon(ObjI);
            
            //ObjOM.cambiarFicha();
            ObjOM.setImagen(new ImageIcon("src/Imagen/Ficha2-2.png"));
            ObjI = new ImageIcon(ObjOM.getImagen().getImage().getScaledInstance(FormV.getLblPrincipal().getWidth(), FormV.getLblPrincipal().getHeight(), Image.SCALE_DEFAULT));
            FormV.getLblPrincipal().setIcon(ObjI);

            FormC.setVisible(false);
            FormC.dispose();
            FormV.setVisible(true);
            
            System.out.println(ObjOM.getContador());
            cambiarTiempo();
            
        }
        
        else if(FormT.getBtnSi() == e.getSource())
        {
            FormV.setVisible(false);
            limpiarIconos(FormV.getPnlIconos());
            this.Contador = 0;
            this.Jugador = 1;
            this.Segundo = 30;
            this.Posicion = 0;
            ObjOM.restablecer();
            this.FormV.setVisible(true);
            cambiarTiempo();
            
            FormT.setVisible(false);
        }
        
        else if(FormT.getBtnNo() == e.getSource())
        {
            System.exit(0);
        }
        
        else if(FormC.getBtnReglas() == e.getSource())
        {
            FormR.setVisible(true);
            FormR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            FormR.setVisible(false);
        }
    }
    
    /**
     * Funcion que permite restablecer todos los iconos de las fichas para una 
     * nueva partida
     * @param Form 
     */
    public void limpiarIconos(JPanel Form)
    {
        for(int i=0; i<Form.getComponentCount(); i++)
        {
            if(Form.getComponent(i) instanceof JLabel)
            {
                JLabel lblControl = (JLabel) Form.getComponent(i);
                lblControl.setIcon(null);
                lblControl.setBorder(null);
            }
        }
    }
    
    /**
     * Funcion que permite cambiar el jugador que se encuentra en juego y reiniciar
     * los temporizadores de cada uno. Ademas, se establecen las propiedades de la 
     * barra de progreso y de la etiqueta de los segundos de cada temporizado.
     * Tambien determina quien es el ganador del juego
     */
    public void cambiarTiempo()
    {
        ObjOM.cambiarFicha();
        this.LblSegundo.setText("30");
        if(ObjOM.Jugador() == true)
        {
            
                switch(Jugador)
            {
                case 2:
                {
                    FormV.getPnlJug1().setVisible(false);
                    FormV.getPnlJug2().setVisible(true);
                    PgbTiempo.setBounds(1080, 310, 40, 320);
                    this.LblSegundo.setBounds(63, 379, 100, 80);
                    this.Segundo = 30;
                    this.PgbTiempo.setBackground(Color.white);
                    this.PgbTiempo.setForeground(Color.blue);
                    FormV.add(PgbTiempo);
                    FormV.getPnlJug2().add(LblSegundo);
                    Jugador--;

                    break;
                }

                case 0:
                {
                    PgbTiempo.setOrientation(1);
                    PgbTiempo.setBounds(50, 60, 40, 320);
                    FormV.add(PgbTiempo);
                    this.LblSegundo.setFont(new Font("New Times Roman", 0, 24));
                    this.LblSegundo.setForeground(Color.red);
                    FormV.getPnlJug1().add(this.LblSegundo);
                    FormV.getPnlJug1().setVisible(true);
                    FormV.getPnlJug2().setVisible(false);
                    PgbTiempo.setBounds(50, 60, 40, 320);
                    this.LblSegundo.setBounds(47, 390, 100, 80);
                    this.Segundo = 30;
                    this.PgbTiempo.setBackground(Color.white);
                    this.PgbTiempo.setForeground(Color.green);
                    FormV.add(PgbTiempo);
                    FormV.getPnlJug1().add(LblSegundo);
                    Hilo.start();
                    Jugador=2;
                    break;
                }
                
                case 1:
                {
                    FormV.getPnlJug1().setVisible(true);
                    FormV.getPnlJug2().setVisible(false);
                    PgbTiempo.setBounds(50, 60, 40, 320);
                    this.LblSegundo.setBounds(47, 390, 100, 80);
                    this.Segundo = 30;
                    this.PgbTiempo.setBackground(Color.white);
                    this.PgbTiempo.setForeground(Color.green);
                    FormV.add(PgbTiempo);
                    FormV.getPnlJug1().add(LblSegundo);
                    Jugador++;
                    break;
                }
            }
                
             System.out.println(ObjOM.getContador());  
        }
        
        else 
        {
            if(this.Contador == 1)
                try 
                {
                    this.wait(1000);
                }
                
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            PgbTiempo.setBounds(0, 0, 0, 0);            
            if(ObjOM.getJugador1()<ObjOM.getJugador2())
                JOptionPane.showMessageDialog(PgbTiempo, "El jugador 1 gana");
            
            else if(ObjOM.getJugador1()>ObjOM.getJugador2())
                JOptionPane.showMessageDialog(PgbTiempo, "El jugador 2 gana");
            
            else if(ObjOM.getJugador1()==ObjOM.getJugador2())
                JOptionPane.showMessageDialog(PgbTiempo, "Empate");
            
            FormT.setVisible(true);
        }
    }
    
    @Override
    public void run() {
        
        while (Segundo>=0)
        {   
            if (Segundo>=10)
            {
                this.LblSegundo.setText(String.valueOf(this.Segundo));
            }   
                
            
            if(Segundo<10 && Segundo>=0)
            {
                this.LblSegundo.setText("0" + String.valueOf(this.Segundo));
                this.PgbTiempo.setBackground(Color.white);
                this.PgbTiempo.setForeground(Color.red);
                
            }
            
            try 
            {
                this.Hilo.sleep(1000);
                if(Segundo==0)
                {
                    JOptionPane.showMessageDialog(FormT, "Pierde Ficha");
                    cambiarTiempo();
                }
                
                this.PgbTiempo.setValue(Segundo-1);
                
            } 

            catch (InterruptedException ex) 
            {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Segundo--;
        }
        
        
    }
    
}
