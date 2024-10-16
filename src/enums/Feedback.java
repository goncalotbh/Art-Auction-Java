package enums;

/**
 * Class containing all the constants from the program's feedback.
 *
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public enum Feedback {

    UNKNOWN {
        @Override
        public String toString() {
            return "desconhecido.";
        }
    },
    USER_REGISTERED {
        @Override
        public String toString() {
            return "Registo de utilizador executado.";
        }
    },
    ARTIST_REGISTERED {
        @Override
        public String toString() {
            return "Registo de artista executado.";
        }
    },
    WORK_REGISTERED {
        @Override
        public String toString() {
            return "Registo de obra executado.";
        }
    },
    AUCTION_REGISTERED {
        @Override
        public String toString() {
            return "Registo de leilao executado.";
        }
    },
    USER_REMOVED {
        @Override
        public String toString() {
            return "Remocao de utilizador executada.";
        }
    },
    EXISTENT_USER {
        @Override
        public String toString() {
            return "Utilizador existente.";
        }
    },
    EXISTENT_WORK {
        @Override
        public String toString() {
            return "Obra existente.";
        }
    },
    EXISTENT_AUCTION {
        @Override
        public String toString() {
            return "Leilao existente.";
        }
    },
    MINOR_USER {
        @Override
        public String toString() {
            return "Idade inferior a 18 anos.";
        }
    },
    INEXISTENT_USER {
        @Override
        public String toString() {
            return "Utilizador inexistente.";
        }
    },
    INEXISTENT_ARTIST {
        @Override
        public String toString() {
            return "Artista inexistente.";
        }
    },
    INEXISTENT_WORK {
        @Override
        public String toString() {
            return "Obra inexistente.";
        }
    },
    INEXISTENT_AUCTION {
        @Override
        public String toString() {
            return "Leilao inexistente.";
        }
    },
    INEXISTENT_WORK_IN_AUCTION {
        @Override
        public String toString() {
            return "Obra inexistente no leilao.";
        }
    },
    WORK_ADDED_TO_AUCTION {
        @Override
        public String toString() {
            return "Obra adicionada ao leilao.";
        }
    },
    BID_ACCEPTED {
        @Override
        public String toString() {
            return "Proposta aceite.";
        }
    },
    BELOW_MINIMUM_BID {
        @Override
        public String toString() {
            return "Valor proposto abaixo do valor minimo.";
        }
    },
    AUCTION_CLOSED {
        @Override
        public String toString() {
            return "Leilao encerrado.";
        }
    },
    ACTIVE_BIDS {
        @Override
        public String toString() {
            return "Utilizador com propostas submetidas.";
        }
    },
    WORKS_IN_AUCTION {
        @Override
        public String toString() {
            return "Artista com obras em leilao.";
        }
    },
    EMPTY_AUCTION {
        @Override
        public String toString() {
            return "Leilao sem obras.";
        }
    },
    WORK_WITH_NO_BIDS {
        @Override
        public String toString() {
            return "Obra sem propostas.";
        }
    },
    QUIT {
        @Override
        public String toString() {
            return "\nObrigado. Ate a proxima.";
        }
    },

    NO_BIDS {
        @Override
        public String toString() {
            return " sem propostas de venda.";
        }
    },

    ARTIST_HAS_NO_WORKS {
        @Override
        public String toString() {
            return "Artista sem obras.";
        }
    },

    NO_SOLD_WORKS {
        @Override
        public String toString() {
            return "Nao existem obras ja vendidas em leilao.";
        }
    }
}
