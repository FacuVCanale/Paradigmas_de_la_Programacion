package queue;

public class NonEmptyContainer extends Container {

    private final Object cargo;

    public NonEmptyContainer(Object cargo) {
        this.cargo = cargo;
    }

    @Override
    public Object head() {
        return this.cargo;
    }


}
