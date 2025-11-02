package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * BONUS (f) : Interface graphique
 * Affiche l'heure en temps réel via TimerService + Observer
 */
public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private final JLabel labelHeure = new JLabel("00:00:00", SwingConstants.CENTER);

    public HorlogeGUI(TimerService timerService) {
        // === Fenêtre ===
        setTitle("Horloge Graphique - TP1 Observer (Bonus f)");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // === Style ===
        labelHeure.setFont(new Font("Arial", Font.BOLD, 70));
        labelHeure.setForeground(new Color(0, 102, 204));
        labelHeure.setBackground(Color.WHITE);
        labelHeure.setOpaque(true);
        add(labelHeure, BorderLayout.CENTER);

        // === Observer ===
        timerService.addTimeChangeListener(this);

        // === Afficher ===
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(() -> {
                TimerService ts = (TimerService) evt.getSource();
                labelHeure.setText(String.format("%02d:%02d:%02d",
                        ts.getHeures(),
                        ts.getMinutes(),
                        ts.getSecondes()));
            });
        }
    }
}