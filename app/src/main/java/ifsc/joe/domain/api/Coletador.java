package ifsc.joe.domain.api;

/**
 *  personagens que podem coletar recursos.
 */
public interface Coletador {
    /**
         * coleta um recurso específico.
         * @param recurso O tipo de recurso a ser coletado (será implementado na fase 9).
         */
        void coletar(Object recurso);
    }