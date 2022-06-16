import java.io.Serializable;

public class MazeSolvingAlgorithm implements AlgorithmManager , Serializable{

    @Override
    public int startAlgorithm(int player) {
        // TODO Auto-generated method stub
        for (int i = 0; i < 5; i++) {
            player++;
        }

        return player;
    }

}
