package dataStructures;

/**
 * SepChainHashTableIterator implementation
 * @author Gonçalo Oliveira 65549
 * @version 1.0
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public class SepChainHashTableIterator<K,V> implements Iterator<Entry<K,V>> {












    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Entry<K,V> next() throws NoSuchElementException {
        return null;
    }

    @Override
    public void rewind() {

    }

        /* Implementação do iterador - SepChainHashTableIterator
        1- Hash Table
        2- Iterador da lista corrente
        3- Indice da lista atual (uma vez que a tabela é um vetor de listas)

        next():
          1- next do iterador da lista corrente
          Se o iterador da lista corrente tem próximo... nada
          Se o indice < tamanho do vetor, executar o método 'advance' até encontrar a próxima lista

        hasNext()
            hasNext do iterador da lista atual.

          Dica: Fazer um método auxiliar protected int advance (int pos), que dada uma posição do vetor,
           tal que i = pos + 1, que avança enquanto a lista na posição i for vazia e i < tamanho do vetor

        */
}
