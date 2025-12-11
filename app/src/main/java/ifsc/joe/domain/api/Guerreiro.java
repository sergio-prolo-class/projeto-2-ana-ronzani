package ifsc.joe.domain.api;

import ifsc.joe.domain.Personagem;
import java.util.Set;

/**
 * personagens que podem atacar
 */
public interface Guerreiro {
    /**
     * executa um ataque contra um conjunto de alvos.
     * @param alvos O conjunto de personagens que são alvos potenciais.
     */
    void atacar(Set<Personagem> alvos);
}
