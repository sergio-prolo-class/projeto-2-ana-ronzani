package ifsc.joe.domain.api;

import ifsc.joe.domain.impl.Recurso;

/**
 *  personagens que podem coletar recursos.
 */
public interface Coletador {
    /**
         * coleta um recurso específico.
         */
        void coletar(Recurso recurso);
    }