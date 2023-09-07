package queue;

public class NonEmptyArray extends CustomArray {

        private Object cargo;
        private CustomArray next;

        public NonEmptyArray(Object cargo, CustomArray next) {
            this.cargo = cargo;
            this.next = next;
        }

        public CustomArray add(Object cargo) {
            return NonEmptyArray(this.cargo, next.add( cargo ));
        }

        public CustomArray remove_head() {
            return next;
        }

        public Object head() {
            return cargo;
        }

        public int size() {
            return 1 + next.size();
        }
}
