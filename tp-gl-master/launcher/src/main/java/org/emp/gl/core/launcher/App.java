package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

public class App {

    public static void main(String[] args) {
        TimerService timerService = new DummyTimeServiceImpl();
        new HorlogeGUI(timerService);
        testDuTimeService();

    }

    private static void testDuTimeService() {
        TimerService timerService = new DummyTimeServiceImpl();

        // (c) Horloges
        new Horloge("H1", timerService);
        new Horloge("H2", timerService);

        // (d.1) Un compte à rebours à 5
        new CompteARebours("CAR5", 5, timerService);

        // (d.3) 10 compteurs aléatoires [10-20]
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < 10; i++) {
            int v = 10 + r.nextInt(11); // 10 à 20
            new CompteARebours("CAR" + i, v, timerService);
        }

        try { Thread.sleep(30000); } catch (InterruptedException e) {}
    }
}