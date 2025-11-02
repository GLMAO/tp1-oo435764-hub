package org.emp.gl.time.service.impl;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class DummyTimeServiceImpl implements TimerService {

    private int heures, minutes, secondes, dixieme;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public DummyTimeServiceImpl() {
        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override public void run() { updateTime(); }
        }, 0, 100);
        updateTime();
    }

    private void updateTime() {
        LocalTime t = LocalTime.now();
        setHeures(t.getHour());
        setMinutes(t.getMinute());
        setSecondes(t.getSecond());
        setDixiemeDeSeconde(t.getNano() / 100_000_000);
    }

    private void setHeures(int v) { if (heures != v) { int o = heures; heures = v; pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, o, v); } }
    private void setMinutes(int v) { if (minutes != v) { int o = minutes; minutes = v; pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, o, v); } }
    private void setSecondes(int v) { if (secondes != v) { int o = secondes; secondes = v; pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, o, v); } }
    private void setDixiemeDeSeconde(int v) { if (dixieme != v) { int o = dixieme; dixieme = v; pcs.firePropertyChange(TimerChangeListener.DIXIEME_DE_SECONDE_PROP, o, v); } }

    @Override public void addTimeChangeListener(TimerChangeListener l) { pcs.addPropertyChangeListener(l); }
    @Override public void removeTimeChangeListener(TimerChangeListener l) { pcs.removePropertyChangeListener(l); }

    @Override public int getHeures() { return heures; }
    @Override public int getMinutes() { return minutes; }
    @Override public int getSecondes() { return secondes; }
    @Override public int getDixiemeDeSeconde() { return dixieme; }
}