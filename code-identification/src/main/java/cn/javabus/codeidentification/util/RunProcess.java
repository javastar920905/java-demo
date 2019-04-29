package cn.javabus.codeidentification.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ouzhx on 2019/4/26.
 */
public class RunProcess {
    /**
     * //自动执行mvntest.bat,生成菜单栏
     * String command = root + "mdbook\\mvntest.bat";
     * RunProcess.doExec(command);
     *
     * @param command
     */
    public static void doExec(String command) {

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedInputStream bis = new BufferedInputStream(
                    process.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
            if (process.exitValue() != 0) {
                System.out.println("error!");
            }

            bis.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
