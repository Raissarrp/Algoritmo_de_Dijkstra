import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Criando o frame (janela)
        JFrame frame = new JFrame("Mensagem!");
        frame.setSize(400, 200);  // Tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Criando o painel e configurando layout
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centralizando componentes
        
        // Criando um rótulo com a mensagem
        JLabel label = new JLabel("Teste para comentário em Java XD!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Criando um botão
        JButton button = new JButton("Clique Aqui!");
        
        // Adicionando o rótulo e o botão ao painel
        panel.add(label);
        panel.add(button);
        
        // Adicionando o painel ao frame
        frame.add(panel);
        
        // Centralizando a janela na tela
        frame.setLocationRelativeTo(null);
        
        // Tornando a janela visível
        frame.setVisible(true);
    }
}
