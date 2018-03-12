package net.servodata.app.system.info;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
public class AutowireHelper implements ApplicationContextAware {

    private static volatile ApplicationContext applicationContext;

    // --- getters/setters ---

    public static ApplicationContext getApplicationContext() {
        return AutowireHelper.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        AutowireHelper.applicationContext = applicationContext;
    }

    // --- methods ---

    public static void autowire(Object bean) {
        AutowireHelper.applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
    }

    public static void addApplicationListener(ApplicationListener<?> listener) {
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) AutowireHelper.applicationContext;
        applicationContext.addApplicationListener(listener);
    }

}
