/*
 * Entidade.java
 *
 * Created on 2 de Maio de 2007, 20:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package projeto4;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;


/**
 *
 * @author Glauco
 */
public class Entidade {
    public int x;
    public int y;
    protected int altura,largura;
    public static boolean carregado;
    
    public Image img;
    
    
    /** Creates a new instance of Entidade */
    public Entidade(Image a, int x, int y) {
        this.x = x;
        this.y = y;
//        loadImagem(imgUrl);
        img=a;
        altura=img.getHeight(null);
        largura=img.getWidth(null);

    }
    
    

    
    public void mover()
    {
       
    }
    public int altura()
    {
        return altura;
    }
    public int largura()
    {
        return largura;
    }
    public void desenhar(Graphics g)
    {
       g.drawImage(img,x,y,null);
       
    }

    boolean verificaColisao(Entidade ele) {
        
        
         int x2,y2,larg,alt;
         x2=ele.x;
         y2=ele.y;
         larg=ele.largura();
         alt=ele.altura();
         Rectangle z = new Rectangle(x,y,altura(),largura());
        Rectangle k = new Rectangle(x2,y2,alt,larg);
        if(z.contains(k))
        {
            return true;
        }
        if(z.intersects(k)==true)
        {
            return true;
        }
        
        return false;
        
        
    }

    void colisaoCom(Entidade ele) {
        //Implementar
        
    }
    
}
