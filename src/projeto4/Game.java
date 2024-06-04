package projeto4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Glauco
 */
public class Game extends Canvas {

    /**
     * Define um buffer grafico. Tudo primeiro é enviado para o buffer depois para a placa gráfica
     */
    private BufferStrategy strategy;


    /**
     * Enquanto true executa o laco principal
     */
    private boolean gameExecutando = true;

    /**
     * Todas as entidades do jogo
     */
    private ArrayList entidades = new ArrayList();


    /**
     * Todas as entidades que precisam ser removidas do jogo
     */
    private ArrayList removerEntidades = new ArrayList();

    /**
     * Nave do jogador
     */
    private Nave nave;

    /**
     * Velociade da nave do jogador (pixels/sec)
     */
    private double velocidadeNave = 3;

    /**
     * Tempo do ultimo tiro.
     */
    private long ultimoTiro = 0;

    /**
     * Tempo entre tiros
     */
    private long intervaloTiro = 250;

    /**
     * Quantidade de inimigos que aparecem na tela
     */
    private int quantidadeInimigos;

    /**
     * Mensagem
     */
    private String message = "";

    private int numentidades;

    private Image img1;
    private Image img2;
    private Image img3;
    private Image img4;
    private Image img5;
    private Image img6;
    private Image img7;
    private Image img8;
    private Image img9;
    private Image img10;
    /**
     * Controle do teclado
     */
    private boolean esperandoKeyPress = true;
    private boolean esquerdaPressed = false;
    private boolean direitaPressed = false;
    private boolean tiroPressed = false;
    private boolean especialPressed = false;


    public Game() {

        //Define as informacoes para construir uma Janela (JFrame)
        JFrame container = new JFrame("Projeto 4");
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(null);

        // Adiciona o canvas no frame
        setBounds(0, 0, 800, 600);
        panel.add(this);

        // Desabilita a atualização (repaint) pelo AWT
        setIgnoreRepaint(true);

        // Mostra a janela
        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        // Define o evento de saida do jogo
        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        // Adiciona o manipulador (Handler) para tratar os eventos do teclado
        addKeyListener(new KeyInputHandler());

        // Requisita o focus para o canvas
        requestFocus();


        // Cria o BufferStraty
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        //Inicia as entidades
        carregaimagens();
        //iniciaEntidades();
        iniciarGame();
    }

    public Image loadImagem(String imgUrl) {
        Image i;
        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(imgUrl);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {

        }

        // create an accelerated image of the right size to store our sprite in
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        i = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);

        // draw our source image into the accelerated image
        i.getGraphics().drawImage(sourceImage, 0, 0, null);
        return i;

    }

    private void carregaimagens() {
        img1 = loadImagem("sprites/ameba2.gif");
        img2 = loadImagem("sprites/ameba_segundo.gif");
        img3 = loadImagem("sprites/ameba_terceiro.gif");
        img4 = loadImagem("sprites/ameba_quarto.gif");
        img5 = loadImagem("sprites/ameba_quinto.gif");
        img6 = loadImagem("sprites/ship.gif");
        img7 = loadImagem("sprites/shot.gif");
        img8 = loadImagem("sprites/alien.gif");
        img9 = loadImagem("sprites/redLaserRay.gif");
        img10 = loadImagem("sprites/ameba.gif");
    }

    private void iniciarGame() {
        // Limpa todas as entidades do ArrayList
        entidades.clear();
        iniciaEntidades();

        // Define os eventos do teclado para false
        esquerdaPressed = false;
        direitaPressed = false;
        tiroPressed = false;
    }

    /**
     * Instancia todas as entidades do jogo
     */
    private void iniciaEntidades() {
        nave = new Nave(this, 400, 550, img6);
        entidades.add(nave);
        for (int j = 0; j < 200; j += 50) {
            for (int i = 0; i < 500; i += 50) {
                entidades.add(new alien(img8, 150 + i, 100 + j));

            }
        }

        numentidades = entidades.size();

        //Implementar o resto !!!!!

    }


    /*Entidade a ser removida do jogo
     */
    public void removerEntidade(Entidade entidade) {
        removerEntidades.add(entidade);
    }


    /**
     * Notifica fim do jogo
     */
    public void notificaFim() {
        message = "Fim do Jogo";

        esperandoKeyPress = true;
    }


    /* Notifica vitoria */
    public void notificaVitoria() {
        message = "Você venceu!";

        esperandoKeyPress = true;
    }

    /**
     * Notifica morte de um Inimigo
     */
    public void notifyAlienKilled() {

        // Implementar

    }

    public void especial() {
        if (System.currentTimeMillis() > (ultimoTiro + intervaloTiro) || ultimoTiro == 0) {
            entidades.add(new tiro(img9, nave.x, nave.y - img9.getHeight(null)));
            ultimoTiro = System.currentTimeMillis();
        }
    }

    /**
     * Esse método tenta construir um novo Tiro. Observar o tempo entre tiros
     * Use as variáveis  ultimoTiro  e  intervaloTiro  para fazer esse controle
     * Para pegar o tempo do ultimo tiro use
     * ultimoTiro = System.currentTimeMillis();
     */
    public void tentaAtirar() {
        if (System.currentTimeMillis() > (ultimoTiro + intervaloTiro) || ultimoTiro == 0) {
            entidades.add(new tiro(img7, nave.x, nave.y));
            ultimoTiro = System.currentTimeMillis();
        }

    }

    public void descetodos() {
        for (int j = 0; j < entidades.size(); j++) {
            Entidade entidade2 = (Entidade) entidades.get(j);
            if (entidade2 instanceof alien) {
                ((alien) entidade2).baixo();
            }
        }
    }

    public int conta_aliens() {
        int contalien = 0;
        for (int i = 0; i < entidades.size(); i++) {
            Entidade entidade = (Entidade) entidades.get(i);
            if (entidade instanceof alien) {
                contalien++;
            }
        }
        return contalien;
    }

    public int conta_amebas() {
        int contameb = 0;
        for (int i = 0; i < entidades.size(); i++) {
            Object entidade = entidades.get(i);
            if (entidade instanceof ameba) {
                contameb++;
            }
        }
        return contameb;
    }

    /**
     * Laço Principal do Jogo
     */
    @SuppressWarnings("static-access")
    public void gameLoop() {
        int conta = 0, fase = 0;
        boolean ameba = false;//para adicionar a ameba só uma vez
        while (gameExecutando) {
            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
            // Graphics2D possui mais métodos que a classe Graphics

            g = (Graphics2D) strategy.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0, 0, 800, 600);

            conta = 0;
            nave.carregado = false;
//                       
            if ((conta_aliens() == 0 && ameba == false) || (conta_amebas() == 0 && ameba == true))//adiciona a ameba inicial
            {
//                         entidades.add(new amebamaster(img10,getWidth()/2,50, img1, img2, img3, img4, img5));
                fase++;
                for (int i = 0; i < fase; i++) {
                    entidades.add(new ameba(img1, ((int) (getWidth() * Math.random())), ((int) ((getHeight() / 2) * Math.random())), img1, img2, img3, img4, img5));
                }
                ameba = true;
            }
            // Desenhar todas as entidades
            for (int i = 0; i < entidades.size(); i++) {
                Entidade entidade = (Entidade) entidades.get(i);
                entidade.desenhar(g);
                conta++;
//                                if(conta==entidades.size())
//                                {
//                                   nave.carregado=true;
//
//                                }
                nave.carregado = true;

            }

            //Se entrar no if o jogo está ativo.
            if (!esperandoKeyPress && nave.carregado) {


                for (int i = 0; i < entidades.size(); i++) {
                    Entidade entidade = (Entidade) entidades.get(i);


                    if (entidade instanceof ameba) {
                        if (entidade.y > getHeight())//vendo se o inimigo chegou em baixo
                        {
                            ameba = false;
                            entidades.clear();

                        }
                        if (conta_amebas() > 200)//limitando o numero de amebas
                        {
                            ((ameba) entidade).anticoncepcional = true;
                        } else {
                            ((ameba) entidade).anticoncepcional = false;
                        }
                        if (((ameba) entidade).podesedividir(entidades))//se a ameba tem espaço para se dividir
                        {


                            entidades.add(((ameba) entidade).mitose());
                            ((ameba) entidade).setCont(0);
                        }
                        if (entidade.y < 0 || entidade.y > getHeight() || entidade.x < 0 || entidade.x > getWidth())//removendo as amebas que saem fora da tela
                        {

                            removerEntidades.add(entidade);
                        }
                    }
                    if (entidade instanceof tiro)//elimina os tiros que ultrapassam a tela
                    {
                        if (entidade.y < 0) {
                            removerEntidades.add(entidade);
                        }
                    }
                    if (entidade instanceof alien)//controla o movimento dos aliens
                    {
                        if (((alien) entidade).x < 0) {
                            ((alien) entidade).setVira(true);
                            descetodos();
                        }
                        if (((alien) entidade).x + ((alien) entidade).largura() > getWidth()) {
                            ((alien) entidade).setVira(false);
                            descetodos();
                        }
                    }

                    entidade.mover();


                }
            }


            // Trata a colisao entre as entidades
            // Compara todos com todos
            for (int p = 0; p < entidades.size(); p++) {
                for (int s = p + 1; s < entidades.size(); s++) {

                    Entidade eu = (Entidade) entidades.get(p);
                    Entidade ele = (Entidade) entidades.get(s);

                    if (eu.verificaColisao(ele)) {
                        if (ele instanceof tiro && eu instanceof amebamaster) {
                            removerEntidades.add(ele);
                        } else {
                            //eu.colisaoCom(ele);
                            //ele.colisaoCom(eu);
                            removerEntidades.add(eu);
                            removerEntidades.add(ele);
                        }
                        //System.out.println("colidiu");

                    }

                }
            }

            // remove as entidades do jogo
            entidades.removeAll(removerEntidades);
            removerEntidades.clear();
            //atualizando o numero de entidades

            //System.out.println(entidades.size());
            //Mensagem de espera de inicio do jogo
            if (esperandoKeyPress) {
                g.setColor(Color.white);
                g.drawString("Tecle algo", 400, 250);
            }
            if (!entidades.contains(nave)) {
                ameba = false;
                notificaFim();
                iniciarGame();
            }
            g.dispose();
            strategy.show();

            /* Define o valor do movimento inicialmente com zero */
            nave.setMoverHorizontal(0);

            /* Aqui é tratado o movimento da nave */
            if ((esquerdaPressed) && (!direitaPressed)) {
                if (nave.x > 0)
                    nave.setMoverHorizontal(-velocidadeNave);
            } else if ((direitaPressed) && (!esquerdaPressed)) {
                if (nave.x + nave.largura < getWidth())
                    nave.setMoverHorizontal(velocidadeNave);
            }


            if (tiroPressed) {
                tentaAtirar();
            }
            if (especialPressed) {
                especial();
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }


    private class KeyInputHandler extends KeyAdapter {
        private int pressCount = 1;


        public void keyPressed(KeyEvent e) {

            if (esperandoKeyPress) {
                return;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                esquerdaPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direitaPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                tiroPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                especialPressed = true;
            }

        }


        public void keyReleased(KeyEvent e) {

            if (esperandoKeyPress) {
                return;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                esquerdaPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direitaPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                tiroPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                especialPressed = false;
            }
        }


        public void keyTyped(KeyEvent e) {
            if (esperandoKeyPress) {
                if (pressCount == 1) {
                    esperandoKeyPress = false;
                    //iniciarGame();//não era necessário pois estava reiniciando tudo quando se apertava qualquer tecla
                    pressCount = 0;
                } else {
                    pressCount++;
                }
            }

            //Sair
            if (e.getKeyChar() == 27) {
                System.exit(0);
            }
        }
    }

    public static void main(String argv[]) {
        Game g = new Game();
        g.gameLoop();
    }
}

