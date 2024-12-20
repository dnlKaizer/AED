import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
    
	private int nivel;
	private CurveGraphics curveGraphics;

	public Window() {
		this.nivel = 0;
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

		// Configura botões
        JButton btAumentar = new JButton("+");
        JButton btDiminuir = new JButton("-");
		
        // Ajusta fonte e tamanho
        Font font = new Font("Arial", Font.BOLD, 32);
        btAumentar.setFont(font);
        btDiminuir.setFont(font);

		// Ajusta espaçmento interno
		btAumentar.setMargin(new Insets(0, 0, 0, 0));
		btDiminuir.setMargin(new Insets(0, 0, 0, 0));

        // Ajusta tamanho dos botões
        Dimension buttonSize = new Dimension(40, 40);
        btAumentar.setPreferredSize(buttonSize);
        btDiminuir.setPreferredSize(buttonSize);

        // Personaliza cores
        btAumentar.setBackground(Color.LIGHT_GRAY);
        btDiminuir.setBackground(Color.LIGHT_GRAY);
        btAumentar.setForeground(Color.BLACK);
        btDiminuir.setForeground(Color.BLACK);

        // Remove borda e altera borda padrão
        btAumentar.setFocusPainted(false);
        btDiminuir.setFocusPainted(false);
        btAumentar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        btDiminuir.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

		btAumentar.addActionListener(new ActionListener() { // adiciona ação no botão
			public void actionPerformed(ActionEvent e) {
				// Sera executado a cada clique do botao
				Curve curve = new Curve(++nivel);
				curveGraphics.set(curve.createPoints());
			}
		});
		panel.add(btAumentar);
		
		btDiminuir.addActionListener(new ActionListener() { // adiciona ação no botão
			public void actionPerformed(ActionEvent e) {
				if (nivel == 0) return;
				// Sera executado a cada clique do botao
				Curve curve = new Curve(--nivel);
				curveGraphics.set(curve.createPoints());
			}
		});
		panel.add(btDiminuir);

		// colocar o canvas dentro do JFrame
		curveGraphics = new CurveGraphics();
		this.getContentPane().add(curveGraphics, BorderLayout.CENTER);

		// tornar a janela visivel
		this.setVisible(true);
	}
}
