package nemo;


import java.util.HashMap;

public class Nemo {
    public Coordinate position;
    public Orientation orientation;

    private HashMap<Character, Runnable> commands = new HashMap<>();

    public Launcher launcher;

    public Nemo(Coordinate position, Orientation orientation, int charges) {
        this.position = position;
        this.orientation = orientation;
        this.launcher = new Launcher(charges);
        initializeCommands();
        }

        private void initializeCommands() {
            commands.put('d', () -> position.z--);
            commands.put('u', () -> position.z++);
            commands.put('l', () -> orientation = orientation.rotateLeft());
            commands.put('r', () -> orientation = orientation.rotateRight());
            commands.put('f', ()-> position = orientation.moveForward(position));
            commands.put('m', () -> launcher.launch());
        }

        public void receiveCommands (String commands) {
        commands.chars().forEach( each -> this.processCommands((char) each));
        }

        public void processCommands(char comando) {
            if (commands.containsKey(comando)) {
                commands.get(comando).run();
            }
        }

    }

