package queue;

public class FilledContainer extends Container {

    private final Object cargo;

    public FilledContainer(Object cargo) {
        this.cargo = cargo;
    }

    public Object item() {
        return this.cargo;
    }


}
