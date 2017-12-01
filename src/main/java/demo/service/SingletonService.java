package demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class SingletonService {

    public void printMe() {
        System.out.println("**** printMe() method called ****");
    }
}
