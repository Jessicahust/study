package com.cainiao.work.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author wuchanming
 * @date 18/5/6
 */
public abstract class ServiceProvider {
    public static void getDefault(){
        ServiceLoader<ServiceProvider> serviceProviders = ServiceLoader.load(ServiceProvider.class);
        Iterator<ServiceProvider> iterator = serviceProviders.iterator();
        ServiceProvider next = serviceProviders.iterator().next();
        System.out.println(next);
        while (iterator.hasNext()){
            ServiceProvider serviceProvider = iterator.next();
            System.out.println(serviceProvider.getMessage());
        }
    }

    public abstract String getMessage();

    public static void main(String[] arge){
        ServiceProvider.getDefault();
    }
}
