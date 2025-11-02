package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    private final String name;
    private int valeur;
    private final TimerService timerService;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.valeur = valeurInitiale;
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);
        System.out.println(name + " démarré à " + valeurInitiale);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            valeur--;
            System.out.println(name + " : " + Math.max(valeur, 0));
            if (valeur <= 0) {
                timerService.removeTimeChangeListener(this);
                System.out.println(name + " terminé ! Désinscription.");
            }
        }
    }
}