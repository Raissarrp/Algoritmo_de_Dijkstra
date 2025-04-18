import java.util.*;

public class Main {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        List<String> nomesCapitais = Arrays.asList(
                "Rio Branco", "Maceió", "Macapá", "Manaus", "Salvador", "Fortaleza", "Brasília", "Vitória",
                "Goiânia", "São Luís", "Cuiabá", "Campo Grande", "Belo Horizonte", "Belém", "João Pessoa",
                "Curitiba", "Recife", "Teresina", "Rio de Janeiro", "Natal", "Porto Alegre", "Porto Velho",
                "Boa Vista", "Florianópolis", "São Paulo", "Aracaju", "Palmas"
        );

        List<String> siglasCapitais = Arrays.asList(
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
                "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        );

        Map<String, Vertice> siglaToVertice = new HashMap<>();
        Map<Vertice, String> verticeToSigla = new HashMap<>();

        AlgoritmoDijkstra grafo = new AlgoritmoDijkstra();

        System.out.println("--- Capitais e suas siglas ---");
        for (int i = 0; i < nomesCapitais.size(); i++) {
            System.out.printf("%-20s (%s)\n", nomesCapitais.get(i), siglasCapitais.get(i));
            Vertice v = new Vertice(i);
            siglaToVertice.put(siglasCapitais.get(i).toLowerCase(), v);
            verticeToSigla.put(v, siglasCapitais.get(i));
            grafo.adicionarVertice(i);
        }
        System.out.println("-------------------------------\n");

        
        grafo.criarAresta(siglaToVertice.get("df").getId(), siglaToVertice.get("go").getId(), 209);
        grafo.criarAresta(siglaToVertice.get("go").getId(), siglaToVertice.get("mg").getId(), 906);
        grafo.criarAresta(siglaToVertice.get("df").getId(), siglaToVertice.get("mg").getId(), 740);
        grafo.criarAresta(siglaToVertice.get("mg").getId(), siglaToVertice.get("rj").getId(), 434);
        grafo.criarAresta(siglaToVertice.get("rj").getId(), siglaToVertice.get("sp").getId(), 429);
        grafo.criarAresta(siglaToVertice.get("sp").getId(), siglaToVertice.get("pr").getId(), 408);
        grafo.criarAresta(siglaToVertice.get("pr").getId(), siglaToVertice.get("sc").getId(), 300);
        grafo.criarAresta(siglaToVertice.get("sc").getId(), siglaToVertice.get("rs").getId(), 476);
        grafo.criarAresta(siglaToVertice.get("ba").getId(), siglaToVertice.get("se").getId(), 356);
        grafo.criarAresta(siglaToVertice.get("se").getId(), siglaToVertice.get("al").getId(), 278);
        grafo.criarAresta(siglaToVertice.get("al").getId(), siglaToVertice.get("pe").getId(), 258);
        grafo.criarAresta(siglaToVertice.get("pe").getId(), siglaToVertice.get("pb").getId(), 120);
        grafo.criarAresta(siglaToVertice.get("pb").getId(), siglaToVertice.get("rn").getId(), 185);
        grafo.criarAresta(siglaToVertice.get("ba").getId(), siglaToVertice.get("mg").getId(), 1372);
        grafo.criarAresta(siglaToVertice.get("ba").getId(), siglaToVertice.get("df").getId(), 1444);
        grafo.criarAresta(siglaToVertice.get("pa").getId(), siglaToVertice.get("ap").getId(), 600);
        grafo.criarAresta(siglaToVertice.get("pa").getId(), siglaToVertice.get("to").getId(), 1085);
        grafo.criarAresta(siglaToVertice.get("to").getId(), siglaToVertice.get("df").getId(), 973);
        grafo.criarAresta(siglaToVertice.get("df").getId(), siglaToVertice.get("mt").getId(), 1133);
        grafo.criarAresta(siglaToVertice.get("mt").getId(), siglaToVertice.get("ro").getId(), 1450);
        grafo.criarAresta(siglaToVertice.get("ro").getId(), siglaToVertice.get("ac").getId(), 544);

        System.out.println("Digite a sigla da capital de origem (ex: DF):");
        String origemInput = scanner.nextLine().toLowerCase();

        System.out.println("Digite a sigla da capital de destino (ex: SP):");
        String destinoInput = scanner.nextLine().toLowerCase();

        if (!siglaToVertice.containsKey(origemInput) || !siglaToVertice.containsKey(destinoInput)) {
            System.out.println("Sigla não reconhecida. Tente novamente.");
            return;
        }

        Vertice origemVertice = siglaToVertice.get(origemInput);
        Vertice destinoVertice = siglaToVertice.get(destinoInput);

        List<Vertice> caminhoVertices = grafo.caminhoMinimo(origemVertice.getId(), destinoVertice.getId());

        if (caminhoVertices == null) {
            System.out.println("Não há caminho disponível entre as capitais selecionadas.");
        } else {
            int distanciaTotal = 0;
            for (int i = 0; i < caminhoVertices.size() - 1; i++) {
                distanciaTotal += grafo.getCusto(caminhoVertices.get(i).getId(), caminhoVertices.get(i + 1).getId());
            }

            System.out.println("Menor caminho:");
            for (int i = 0; i < caminhoVertices.size(); i++) {
                System.out.print(verticeToSigla.get(caminhoVertices.get(i)));
                if (i < caminhoVertices.size() - 1) System.out.print(" -> ");
            }
            System.out.println("\nDistância total: " + distanciaTotal + " km");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}