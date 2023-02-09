package pl.dolega.springcore.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("eventStorage"))
            System.out.println("Before:" + bean.getClass() + "  " + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userStorage"))
            System.out.println("After: " + bean.getClass() + "  " + beanName);
        return bean;
    }
}
