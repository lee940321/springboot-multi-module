package com.lee.star.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

/**
 * descrition：ssh通道远程链接
 */
class SSHConnection {

    private final static int LOCAl_PORT = 6378;
    private final static int REMOTE_PORT = 6379;
    private final static int SSH_REMOTE_PORT = 22;
    private final static String SSH_USER = "";
    private final static String SSH_PASSWORD = "";
    private final static String SSH_REMOTE_SERVER = "";
    private final static String REDIS_REMOTE_SERVER = "";

    private Session sesion; //represents each ssh session

    public SSHConnection() throws Throwable {

        JSch jsch;

        jsch = new JSch();
//        jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        //jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);

        sesion.setPassword(SSH_PASSWORD);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        sesion.setConfig(config);

        sesion.connect(); //ssh connection established!

        //by security policy, you must connect through a fowarded port
        sesion.setPortForwardingL(LOCAl_PORT, REDIS_REMOTE_SERVER, REMOTE_PORT);

    }

    public void closeSSH() {
        sesion.disconnect();
    }
}

@Configuration
@WebListener
@Profile({"dev"})
public class MyContextListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(MyContextListener.class);

    private SSHConnection conexionssh;


    public MyContextListener() {
        super();
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        logger.info("-####SSH Context initialized ... !####");
        try {
            conexionssh = new SSHConnection();
        } catch (Throwable e) {
            logger.error("创建SSH连接失败", e); // error connecting SSH server
        }
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.info(" ####SSH Context destroyed ... !###");
        conexionssh.closeSSH(); // disconnect
    }
}
