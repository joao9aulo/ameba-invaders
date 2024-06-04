/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto4;



import java.awt.Image;
import java.awt.Rectangle;


import java.util.ArrayList;



/**
 *
 * @author Jo?o Paulo
 */
public class ameba extends Entidade{
   int cont=0;
   int a,b,a1,b1,z=30;//coordenadas das amebas filhas,o z ? o quanto elas estar?o afastadas
   public Image img1;
   public Image img2;
   public Image img3;
   public Image img4;
   public Image img5;
   static boolean anticoncepcional;
   
    @Override
    public void mover() {//as amebas se movem aleatoriamente nos quatro pontos
        int num;
        num=(int)(4*Math.random());
        
        if(num==1)
        {
            x+=1;
        }
        if(num==2)
        {
            x-=1;
        }
        if(num==3)
        {
            y+=1;
        }
        if(num==4)
        {
            y-=1;
        }
        cont++;
        animacao();
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
    public ameba mitose()
    {
        return new ameba(img1,a1,b1,img1,img2,img3,img4,img5);
    }
   
   
    public  boolean podesedividir(ArrayList ent)//vendo se a ameba pode se dividir
    {
        a=x-getLargura()/2-z;
        a1=x+getLargura()/2+z;
        b=y;
        b1=y;
        if(colisao_ameba(a, b,ent)==false && colisao_ameba(a1, b1, ent)==false && cont>100 && anticoncepcional==false)//vendo se ela pode crescer para os lados
        {
            x=a;
            y=b;//coordenadas novas dessa propria ameba
            img=img1;
            return true;
        }else{//vendo se ela pode crescer pra cima
            a=x;
            a1=x;
            b=y+getAltura()/2+z;
            b1=y-getAltura()/2-z;
            if(colisao_ameba(a, b,ent)==false && colisao_ameba(a1, b1, ent)==false && cont>100 && anticoncepcional==false)
            {
              x=a;
              y=b;//coordenadas novas dessa propria ameba
              img=img1;
              return true;
            }

        }
        
        return false;
    }
   

    public  boolean colisao_ameba(int a,int b,ArrayList ent)//ve se essas coordenadas batem com algum objeto na tela
    {
        
        Rectangle w = new Rectangle(a,b,altura(),largura());
        for (int p=0; p < ent.size();p++)
        {
         Entidade l  = (Entidade) ent.get(p);
         Rectangle z = new Rectangle(l.x,l.y,l.altura(),l.largura());
         if(z.intersects(w)==true)
        {
            return true;
        }
        }
        
        return false;
    }
    public ameba(Image f, int x, int y,Image a,Image b,Image c,Image d,Image e) {
        super(f, x, y);
//        img1=loadImagem_ameba("sprites/ameba2.gif");
//        img2=loadImagem_ameba("sprites/ameba_segundo.gif");
//        img3=loadImagem_ameba("sprites/ameba_terceiro.gif");
//        img4=loadImagem_ameba("sprites/ameba_quarto.gif");
//        img5=loadImagem_ameba("sprites/ameba_quinto.gif");
        img1=a;
        img2=b;
        img3=c;
        img4=d;
        img5=e;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public static boolean isCarregado() {
        return carregado;
    }

    public static void setCarregado(boolean carregado) {
        Entidade.carregado = carregado;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    boolean verificaColisao(Entidade ele) {
        if(ele instanceof ameba || ele instanceof amebamaster)
        {
            return false;
        }
        return super.verificaColisao(ele);
    }
    private void animacao()
    {
        if(anticoncepcional==false)
        {
         if(cont==0)
         {
             img=img1;
         }
         if(cont==25)
         {
             img=img2;
         }
         if(cont==50)
         {
             img=img3;
         }
         if(cont==75)
         {
             img=img4;
         }
         if(cont==100)
         {
             img=img5;
         }
         if(cont>150)
         {
             cont=0;
         }
        }
    }
}
