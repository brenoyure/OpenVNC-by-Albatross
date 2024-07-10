package br.albatross.open.vnc.runnables;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;

import br.albatross.open.vnc.services.hints.HintService;

public class ShowHintRunnable implements Runnable {

    private final HintService<String> hintService;
    private final ExecutorService executorService;

    private static final byte CLOSE_HINT_SECONDS_DELAY = 10;

    public ShowHintRunnable(HintService<String> hintService, ExecutorService executorService) {
        this.hintService = hintService;
        this.executorService = executorService;
    }

    @Override
    public void run() {

        String hintText = 
                hintService.getRandomHint();        

        StringBuilder sb = new StringBuilder();
        sb.append("Abrindo Conexão Remota...");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append(hintText);
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("Este popup será fechado automaticamente em ");
        sb.append(CLOSE_HINT_SECONDS_DELAY);
        sb.append(" segundos");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());

        Future<?> future = executorService.submit(() -> {

            showMessageDialog(null, sb.toString(), "Abrindo conexão remota...", JOptionPane.INFORMATION_MESSAGE);

        });

        try {

            future.get(CLOSE_HINT_SECONDS_DELAY, SECONDS);

        } catch ( ExecutionException | InterruptedException | TimeoutException e) { future.cancel(true); }

    }

}
