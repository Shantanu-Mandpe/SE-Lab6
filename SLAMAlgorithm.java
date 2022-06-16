import java.io.Serializable;

public class SLAMAlgorithm implements AlgorithmManager , Serializable{

    @Override
    public int startAlgorithm(int player) {
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++) {
            player++;
        }

        return player;
    }

}
