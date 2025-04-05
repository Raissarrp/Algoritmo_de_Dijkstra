import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> capitais = Arrays.asList(
            "Rio Branco", "Maceió", "Macapá", "Manaus", "Salvador", "Fortaleza", "Brasília", "Vitória",
            "Goiânia", "São Luís", "Cuiabá", "Campo Grande", "Belo Horizonte", "Belém", "João Pessoa",
            "Curitiba", "Recife", "Teresina", "Rio de Janeiro", "Natal", "Porto Alegre", "Porto Velho",
            "Boa Vista", "Florianópolis", "São Paulo", "Aracaju", "Palmas"
        );

        Map<String, Integer> capitalToIndex = new HashMap<>();
        Map<Integer, String> indexToCapital = new HashMap<>();

        for (int i = 0; i < capitais.size(); i++) {
            capitalToIndex.put(capitais.get(i).toLowerCase(), i);
            indexToCapital.put(i, capitais.get(i));
        }

        AlgoritmoDijkstra grafo = new AlgoritmoDijkstra(capitais.size());

        // Conexões reais com distâncias em km
        grafo.criarAresta(capitalToIndex.get("brasília"), capitalToIndex.get("goiânia"), 209);
        grafo.criarAresta(capitalToIndex.get("goiânia"), capitalToIndex.get("belo horizonte"), 906);
        grafo.criarAresta(capitalToIndex.get("brasília"), capitalToIndex.get("belo horizonte"), 740);
        grafo.criarAresta(capitalToIndex.get("belo horizonte"), capitalToIndex.get("rio de janeiro"), 434);
        grafo.criarAresta(capitalToIndex.get("rio de janeiro"), capitalToIndex.get("são paulo"), 429);
        grafo.criarAresta(capitalToIndex.get("são paulo"), capitalToIndex.get("curitiba"), 408);
        grafo.criarAresta(capitalToIndex.get("curitiba"), capitalToIndex.get("florianópolis"), 300);
        grafo.criarAresta(capitalToIndex.get("florianópolis"), capitalToIndex.get("porto alegre"), 476);
        grafo.criarAresta(capitalToIndex.get("salvador"), capitalToIndex.get("aracaju"), 356);
        grafo.criarAresta(capitalToIndex.get("aracaju"), capitalToIndex.get("maceió"), 278);
        grafo.criarAresta(capitalToIndex.get("maceió"), capitalToIndex.get("recife"), 258);
        grafo.criarAresta(capitalToIndex.get("recife"), capitalToIndex.get("joão pessoa"), 120);
        grafo.criarAresta(capitalToIndex.get("joão pessoa"), capitalToIndex.get("natal"), 185);
        grafo.criarAresta(capitalToIndex.get("salvador"), capitalToIndex.get("belo horizonte"), 1372);
        grafo.criarAresta(capitalToIndex.get("salvador"), capitalToIndex.get("brasília"), 1444);
        grafo.criarAresta(capitalToIndex.get("belém"), capitalToIndex.get("macapá"), 600);
        grafo.criarAresta(capitalToIndex.get("belém"), capitalToIndex.get("palmas"), 1085);
        grafo.criarAresta(capitalToIndex.get("palmas"), capitalToIndex.get("brasília"), 973);
        grafo.criarAresta(capitalToIndex.get("brasília"), capitalToIndex.get("cuiabá"), 1133);
        grafo.criarAresta(capitalToIndex.get("cuiabá"), capitalToIndex.get("porto velho"), 1450);
        grafo.criarAresta(capitalToIndex.get("porto velho"), capitalToIndex.get("rio branco"), 544);

        System.out.println("Digite a capital de origem:");
        String origemInput = scanner.nextLine().toLowerCase();

        System.out.println("Digite a capital de destino:");
        String destinoInput = scanner.nextLine().toLowerCase();

        if (!capitalToIndex.containsKey(origemInput) || !capitalToIndex.containsKey(destinoInput)) {
            System.out.println("Capital não reconhecida. Tente novamente.");
            return;
        }

        int origemIndex = capitalToIndex.get(origemInput);
        int destinoIndex = capitalToIndex.get(destinoInput);

        List<Integer> caminho = grafo.caminhoMinimo(origemIndex, destinoIndex);

        if (caminho == null) {
            System.out.println("Não há caminho disponível entre as capitais selecionadas.");
        } else {
            int distanciaTotal = 0;
            for (int i = 0; i < caminho.size() - 1; i++) {
                distanciaTotal += grafo.getCusto(caminho.get(i), caminho.get(i + 1));
            }

            System.out.println("Menor caminho:");
            for (int i = 0; i < caminho.size(); i++) {
                System.out.print(indexToCapital.get(caminho.get(i)));
                if (i < caminho.size() - 1) System.out.print(" -> ");
            }
            System.out.println("\nDistância total: " + distanciaTotal + " km");
        }
    }
}
