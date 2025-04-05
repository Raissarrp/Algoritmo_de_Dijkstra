import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AlgoritmoDijkstra {

//o "vertices" aqui significa a matriz que vai representar o grafo 
    private int vertices[][];
    public AlgoritmoDijkstra(final int numVertices) {
        this.vertices = new int[numVertices][numVertices];
        //inicia a matriz com o valor infinito, ja que ainda não sabemos o peso das arestas. Ou seja, o valor maximo que temos como "referencia" e o infinito,
        // por isso comparamos pois qualquer numero vai ser menor q infito e a matriz sera atualizada.
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    this.vertices[i][j] = 0;
                } else {
                    this.vertices[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }


    public void criarAresta(final int noOrigem, final int noDestino, final int peso) {
        if (peso < 0) {
            System.out.println("Peso negativo não permitido.");
            return;
        }
        this.vertices[noOrigem][noDestino] = peso;
        this.vertices[noDestino][noOrigem] = peso; 
    }


//metodo para receber as disctâncias dos nós e o nó inicial
    public int getMaisProximo(final int listaCusto[], final Set<Integer> naoVisitados) {
        int minDistancia = Integer.MAX_VALUE;
        int noProximo = 0;

        //O i vai percorrer todos os nos que ainda não foram visitados,
        // e o if vai verificar se o custo do nó i é menor que a distância mínima encontrada até agora. Se for, ele atualiza a distância mínima do no mais proximo.

        for (Integer i : naoVisitados) {
            if (listaCusto[i] < minDistancia) {
                minDistancia = listaCusto[i];
                noProximo = i; //Aqui a gente fala que o noProximo = i
            }
        }
        return noProximo;
    }

//Esse método vai retornar a lista de vizinhos do nó, ou seja, os nós adjacentes a ele.
//Oq ta acontecendo aq? Estou percorrendo a matriz de vertices que fiz la no inicio e verificando se o valor e maior q 0. Ou seja, se existe uma aresta entre o nó atual e o nó i(que fiz ali embaixo). Se existir, adiciono o nó i à lista de vizinhos.

    private List<Integer> getVizinhos(final int no) {
        List<Integer> vizinhos = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[no][i] > 0 && vertices[no][i] != Integer.MAX_VALUE) {
                vizinhos.add(i);
            }
        }
    return vizinhos;
}


    int getCusto(final int noOrigem, final int noDestino) {
       return vertices[noOrigem][noDestino]; //retornando o custo da aresta entre os nós
    }

//esse codigo joga tudo infinito para os outros nos e colocar ele como não visitados, pois ainda n temos antessesor
// e nem custo, ou seja, ainda n sabemos o caminho mais curto entre os nós.    
    public List<Integer> caminhoMinimo(final int noOrigem, final int noDestino){
        int custo [] = new int [vertices.length];
        int antecessor[] = new int [vertices.length];
        Set<Integer> naoVisitados = new java.util.HashSet<Integer>();

        custo[noOrigem] = 0;
        for (int v = 0; v < vertices.length; v++) {
            if (v != noOrigem) {
                custo[v] = Integer.MAX_VALUE;
            }
            antecessor[v] = -1;
            naoVisitados.add(v); // Adicionando todos os nós à lista de não visitados
        }
        while (!naoVisitados.isEmpty()) {
            int noMaisProximo = getMaisProximo(custo, naoVisitados);
            naoVisitados.remove(noMaisProximo); // Removendo o nó mais próximo da lista de não visitados

            for (Integer vizinhosInteger : getVizinhos(noMaisProximo)) {
                int custoTotal = custo[noMaisProximo] + getCusto(noMaisProximo, vizinhosInteger);
                if (custoTotal < custo[vizinhosInteger]) {
                    custo[vizinhosInteger] = custoTotal;
                    antecessor[vizinhosInteger] = noMaisProximo; // Atualizando o antecessor
                }
            }
            if (noMaisProximo == noDestino) { // Se o nó mais próximo for o destino, retorna o caminho
                return caminhoMaisProximo(antecessor, noMaisProximo);
            }
        }
        

        return null;
    }



    public List<Integer> caminhoMaisProximo(final int antecessor[], int noMaisProximo){
        List<Integer> caminho = new ArrayList<Integer>();
        caminho.add(noMaisProximo);

        while (antecessor [noMaisProximo] != -1) {
            caminho.add(antecessor[noMaisProximo]);
            noMaisProximo = antecessor[noMaisProximo];
            
        }
        Collections.reverse(caminho);// invertendo a lista para que o caminho fique na ordem correta
        return caminho; //retornando o caminho mais curto entre os nós

    }


    public static String normalizar(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'normalizar'");
    }


}

