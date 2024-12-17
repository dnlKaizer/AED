import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener {
    
	private JTextField tfNivel;
	private CurveGraphics curveGraphics;

	public Window() {
		initGUI();
	}

	// inicializar os componentes graficos
	private void initGUI() {
		// fechar o programa ao fechar a janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Curva");
		// tamanho em pixels
		setSize(700, 800);
		// meio da tela
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);

		// Conteiner de componentes graficos
		JPanel panel = new JPanel();
		// colocar o panel dentro do JFrame
		this.getContentPane().add(panel, BorderLayout.NORTH);

		// Nivel Maximo
		JLabel lbNivel = new JLabel("Nivel"); // texto
		panel.add(lbNivel);
		tfNivel = new JTextField(5); // caixa de entrada
		panel.add(tfNivel);

		// Botao desenhar
		JButton btDesenhar = new JButton("Desenhar");
		btDesenhar.addActionListener(this); // botao sera ouvido pela janela
		panel.add(btDesenhar);

		// colocar o canvas dentro do JFrame
		curveGraphics = new CurveGraphics();
		this.getContentPane().add(curveGraphics, BorderLayout.CENTER);

		// tornar a janela visivel
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Sera executado a cada clique do botao
        int nivel = Integer.parseInt(tfNivel.getText());
        Curve curve = new Curve(nivel);

		curveGraphics.set(curve.createPoints());
	}
}
