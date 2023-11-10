package linea;

public class Game {

    public static void main( String[] args) throws Exception {

        System.out.println( "Dimensions?");

        Linea game = new Linea( prompt( "Base? " ), prompt( "Height? " ), 'C' );

        System.out.println( game.show() );

        while ( !game.isGameFinished() ) {

            game.playRedAt( prompt( "Red? " ) );

            System.out.println( game.show() );

            if ( !game.isGameFinished() ) {

                game.playBlueAt( prompt( "Blue? " ) );

                System.out.println( game.show() );

            }

        }



    }



    private static int prompt( String message ) {

        System.out.print( message );

        return Integer.parseInt( System.console().readLine() ) -1;

    }

}