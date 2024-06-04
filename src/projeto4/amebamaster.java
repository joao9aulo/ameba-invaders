/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto4;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author João Paulo
 */
public class amebamaster extends Entidade{
private Image img1,img2,img3,img4,img5;
private ArrayList amebas = new ArrayList();
private int energia=0;
    public amebamaster(Image a, int x, int y,Image img1,Image img2,Image img3,Image img4,Image img5) {
        super(a, x, y);
        this.img1=img1;
        this.img2=img2;
        this.img3=img3;
        this.img4=img4;
        this.img5=img5;
    }

    @Override
    public void mover() {

    }

    public int getEnergia() {
        return energia;
    }
    public ArrayList mastermitose()
    {
//        amebas.add(new ameba(img1,x,y,img1,img2,img3,img4,img5));
//        amebas.add(new ameba(img1,x+20,y,img1,img2,img3,img4,img5));
//        amebas.add(new ameba(img1,x-20,y,img1,img2,img3,img4,img5));
//        amebas.add(new ameba(img1,x,y+20,img1,img2,img3,img4,img5));
//        amebas.add(new ameba(img1,x,y-20,img1,img2,img3,img4,img5));
//        amebas.add(new ameba(img1,x+10,y+10,img1,img2,img3,img4,img5));
//        amebas.add(new ameba(img1,x-10,y-10,img1,img2,img3,img4,img5));
//        amebas.add(new ameba(img1,x-45,y+45,img1,img2,img3,img4,img5));
         for(int j=0;j<100;j+=50)
            {
             for(int i=0;i<100;i+=10)
             {
                 amebas.add(new ameba(img1,x+i-100,j,img1,img2,img3,img4,img5));

             }
            }
        return amebas;
    }

    @Override
    boolean verificaColisao(Entidade ele) {
        if(ele instanceof tiro && super.verificaColisao(ele))
        {
            energia++;
            return true;
        }
        if(ele instanceof ameba)
        {
            return false;
        }
        return super.verificaColisao(ele);
    }
   


}
