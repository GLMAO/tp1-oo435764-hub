package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;

public class Horloge implements TimerChangeListener {

    private final String name;
    private final TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);
        System.out.println("Horloge " + name + " initialized!");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Réagir uniquement au changement de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }

    public void afficherHeure() {
        System.out.printf("%s affiche %02d:%02d:%02d%n",
                name,
                timerService.getHeures(),
                timerService.getMinutes(),
                timerService.getSecondes());
    }
}