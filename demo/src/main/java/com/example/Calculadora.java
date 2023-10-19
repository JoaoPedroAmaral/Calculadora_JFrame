package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Calculadora extends JFrame {
   public JLabel areaDeTexto;
   public StringBuilder currentInput = new StringBuilder();//ele é o valor que aparece no JLabel
   private double numero1;
   private String operacao;

   public Calculadora(){
    
        

        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //carrega os graficos da fonte
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();


        areaDeTexto = new JLabel();
        //tira a opacidade do label
        areaDeTexto.setOpaque(true);
        //a cor não muda com new Color();
        areaDeTexto.setBackground(Color.GRAY);
        //cor do texto
        areaDeTexto.setForeground(Color.white);
        areaDeTexto.setPreferredSize(new Dimension(areaDeTexto.getPreferredSize().height,150));

        //fonte
        try{
            //usamos o getClass para carregar fontes personalizadas, coisa q a variavel Font não faria normalmente
            InputStream font = getClass().getResourceAsStream("fonts\\Orbitron.ttf");
            InputStream fonteLabel = font;
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fonteLabel).deriveFont(40f);
            //registra a fonte para renderizar
            ge.registerFont(customFont);
            areaDeTexto.setFont(customFont);
            
        }
        catch(IOException | FontFormatException e){
            e.printStackTrace();
        }

        //alinha o texto a direita
        areaDeTexto.setHorizontalAlignment(JLabel.RIGHT);
        add(areaDeTexto, BorderLayout.NORTH);
        areaDeTexto.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.black));
        //o label é melhor q o Jtext no quesito de poder adicionar elementos basicos do html
        areaDeTexto.setText("<html><b>0&nbsp;&nbsp;&nbsp</b><html>");

        JPanel teclado = new JPanel(new GridLayout(4, 4));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        

        //para cada membro da grade de botoes cria um botao
        for (String botao : botoes){
            JButton btn = new JButton(botao);
            btn.addActionListener(new BotaoClickListener());
            btn.setBackground(new Color(64, 64, 64));
            //mudar cor do texto
            btn.setForeground(new Color(250,250,250));
            btn.setBorder(BorderFactory.createLineBorder(Color.black));
            teclado.add(btn);
            teclado.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.black));

            try{
                //usamos o getClass para carregar fontes personalizadas, coisa q a variavel Font não faria normalmenteno codi
                InputStream font = getClass().getResourceAsStream("fonts\\Orbitron.ttf");
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, font).deriveFont(Font.BOLD,16f);
                //registra a fonte para renderizar
                ge.registerFont(customFont);
                btn.setFont(customFont);
            
            }
            catch(IOException | FontFormatException e){
                e.printStackTrace();
            }
        }
        add(teclado, BorderLayout.CENTER);
   }

   //dá função aos botoes
    private class BotaoClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            //valor do botao apertado
            String valor = ((JButton) e.getSource()).getText();
            //se o botao apertado for entre 0 e 9 o valor aparece na tela
            if(valor.matches("[0-9]")){
                /*O método matches especifica uma expressão regular e localiza o conteúdo do objeto String em que está sendo aplicada essa expressão.*/
                currentInput.append(valor);//adiciona valor como string
                areaDeTexto.setText("<html><b>" + currentInput.toString() + "&nbsp;&nbsp;&nbsp"+"</b></html>");
            }
            //reconhece o valordos botes +,-,*,/ e limpa a tela
            else if(valor.matches("[+\\-*/]")){
                //guarda o valor mostrado na area de texto na variavel numero1
                numero1 = Double.parseDouble(currentInput.toString());//pegao valor string e transforma em double
                operacao = valor;
                currentInput.setLength(0);//limpa o valor atual
                areaDeTexto.setText("<html><b>0&nbsp;&nbsp;&nbsp</b></html>");

            }
            else if(valor.equals("=")){
                double numero2 = Double.parseDouble(currentInput.toString());
                double resultado = 0;
                int resultadoInteiro;
                switch (operacao) {
                    case "+":
                        resultado = numero1 + numero2;
                        break;
                    case "-":
                        resultado = numero1 - numero2;
                        break;
                    case "*":
                        resultado = numero1 * numero2;
                        break;
                    case "/":
                        resultado = numero1 / numero2;
                        break;
                }

                resultadoInteiro = (int) resultado;
                if(resultadoInteiro == resultado){
                    areaDeTexto.setText("<html><b>" + Integer.toString(resultadoInteiro) + "&nbsp;&nbsp;&nbsp"+ "</b></html>");
                }else{
                    areaDeTexto.setText("<html><b>" + Double.toString(resultado) + "&nbsp;&nbsp;&nbsp"+ "</b></html>");
                }
                currentInput.setLength(0);
            }
            //limpar a caixa de seleção
            else if(valor.equals("C")){
                currentInput.setLength(0);
                areaDeTexto.setText("<html><b>0&nbsp;&nbsp;&nbsp</b></html>");
                numero1 = 0;
                operacao = "";
            }
        }
    }
   

}