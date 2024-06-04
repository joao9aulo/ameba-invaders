/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto4;

import java.awt.Image;

/**
 *
 * @author João Paulo
 */
public class alien extends Entidade{
    private int inc=1;
    public static boolean vira=true;

    public static boolean isVira() {
        return vira;
    }

    public static void setVira(boolean vira) {
        alien.vira = vira;

    }
    public alien(Image a, int x, int y) {
        super(a, x, y);
    }

    @Override
    public void mover() {
       if(vira==false)
       {
           if(inc>0)
           {
           inc*=-1;
           }
           
       }
       else{
           if(inc<0)
           {
           inc*=-1;
           }
       }
       x+=inc;
    }
    
    public void baixo()
    {
        y+=10;
    }

    @Override
    boolean verificaColisao(Entidade ele) {
        if(ele instanceof alien)
        {
            return false;
        }
        else{
        return super.verificaColisao(ele);
        }
    }



}
