package br.albatross.open.vnc.runnables;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_MAIN_WINDOW_TITLE;
import static br.albatross.open.vnc.configurations.AvailableProperties.DEV_GITHUB_PAGE_LINK;

public class ShowLogoOnConsoleRunnable implements Runnable {
    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("######################################\n");
        sb.append("#                                    #\n");
        sb.append("#        ");
        sb.append(APP_MAIN_WINDOW_TITLE);
        sb.append("        #");
        sb.append("\n");
        sb.append("#    ");
        sb.append(DEV_GITHUB_PAGE_LINK);
        sb.append("    #\n");
        sb.append("#                                    #\n");
        sb.append("######################################\n");

        System.out.println(sb.toString());

    }

}
