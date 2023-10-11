package nemo;

public class Nemo {
    public Position position;
    public Orientation orientation;
    public Launcher launcher;

    public Nemo(Position position, Orientation orientation, int charges) {
        this.position = position;
        this.orientation = orientation;
        this.launcher = new Launcher(charges);
        }

        public void receiveCommands (String commands) {
        commands.chars().forEach( each -> this.processCommands((char) each));
        }

        public void processCommands(char command) {
            switch (command) {
                case 'd':
                    position.z--;
                    break;
                case 'u':
                    position.z++;
                    break;
                case 'l':
                    orientation = orientation.rotateLeft();
                    break;
                case 'r':
                    orientation = orientation.rotateRight();
                    break;
                case 'f':
                    position = orientation.moveForward(position);
                    break;
                case 'm':
                    launcher.launch();
                    break;
            }
        }

    }

