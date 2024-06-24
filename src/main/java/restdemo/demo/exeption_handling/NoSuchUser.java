package restdemo.demo.exeption_handling;

public class NoSuchUser extends RuntimeException{
    public NoSuchUser(String message) {
        super(message);
    }
}
