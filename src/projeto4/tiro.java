/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto4;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author João Paulo
 */
public class tiro extends Entidade{

    public tiro(Image a, int x, int y) {
        super(a, x, y);
    }

    @Override
    public void mover() {
       y-=6;
    }
    @Override
    boolean verificaColisao(Entidade ele) {
        Rectangle a= new Rectangle(x,y,altura(),largura());
        Rectangle b= new Rectangle(ele.x,ele.y,ele.altura(),ele.largura());
        if(ele instanceof tiro || ele instanceof Nave || ele instanceof amebamaster)
        {
            return false;
        }
        
        return super.verificaColisao(ele);
    }
}
