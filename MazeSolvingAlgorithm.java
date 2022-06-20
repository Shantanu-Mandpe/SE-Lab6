/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Class implements local interface AlgorithmManager
 */

import java.io.Serializable;

public class MazeSolvingAlgorithm implements AlgorithmManager , Serializable{

    @Override
    public int updateAlgorithm(int player) {
        // TODO Auto-generated method stub
        for (int i = 0; i < 5; i++) {
            player++;
        }

        return player;
    }

}
