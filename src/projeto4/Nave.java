package projeto4;

import java.awt.Graphics;
import java.awt.Image;
/*
 * Nave.java
 *
 * Created on 2 de Maio de 2007, 20:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Glauco
 */
public class Nave extends Entidade {
    
    private Game game;
    private double delta;
    
    public Nave(Game game, int x, int y, Image a ) {
        super(a,x,y);
        this.game = game;
    }

    void setMoverHorizontal(double d) {
        delta = d;
    }
    
    @Override
    public void desenhar(Graphics g)
    {
       super.desenhar(g);
    }
    
    public void mover()
    {
        x = x + (int) delta;  
        
        
        super.mover();
        
    
    }
    @Override
    boolean verificaColisao(Entidade ele) {
        if(ele instanceof tiro || ele instanceof Nave)
        {
            return false;
        }
        return super.verificaColisao(ele);
    }
    
}
