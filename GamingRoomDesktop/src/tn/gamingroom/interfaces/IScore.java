/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.List;
import tn.gamingroom.entities.Score;

/**
 *
 * @author Dah
 */
public interface IScore {
    int ajouterScore(Score s);
    int updateScore(Score s);
    List<Score> getScoreByJeuId(int jeux_id);
}
