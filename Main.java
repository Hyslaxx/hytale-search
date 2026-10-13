
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ItemCatalog catalog = new ItemCatalog();
        SearchService searchService = new SearchService(catalog);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Hytale Better Search ===");
            System.out.println("Search in any supported language.");
            System.out.println("Type 'exit' to quit.\n");

            while (true) {
                System.out.print("Search: ");
                String query = scanner.nextLine().trim();

                if (query.equalsIgnoreCase("exit")) {
                    break;
                }

                if (query.isBlank()) {
                    continue;
                }

                List<SearchResult> results = searchService.search(query);

                if (results.isEmpty()) {
                    System.out.println("No items found.\n");
                    continue;
                }

                System.out.println("Results:");

                for (SearchResult result : results) {
                    System.out.printf(
                        "- %s (score: %d)%n",
                        result.item().displayName(),
                        result.score()
                    );
                }

                System.out.println();
            }
        }

        System.out.println("Search closed.");
    }

    // Represents an item independently of the search algorithm.
    record Item(
        String id,
        String displayName,
        List<String> searchTerms
    ) {}

    // Stores the items available for searching.
    static class ItemCatalog {

        private final List<Item> items = new ArrayList<>();

        ItemCatalog() {
            register(new Item(
                "tool.pickaxe",
                "Picareta",
                List.of(
                    "picareta",
                    "picareet",
                    "pickaxe",
                    "pickaxe tool"
                )
            ));

            register(new Item(
                "weapon.sword",
                "Espada",
                List.of(
                    "espada",
                    "espadd",
                    "sword",
                    "sord",
                    "sword weapon"
                )
            ));

            register(new Item(
                "tool.axe",
                "Machado",
                List.of(
                    "machado",
                    "machadoo",
                    "axe",
                    "axee"
                )
            ));
        }

        void register(Item item) {
            items.add(item);
        }

        List<Item> getAll() {
            return List.copyOf(items);
        }
    }

    // Handles matching, typo tolerance, and result ranking.
    static class SearchService {

        private final ItemCatalog catalog;

        SearchService(ItemCatalog catalog) {
            this.catalog = catalog;
        }

        List<SearchResult> search(String query) {
            String normalizedQuery = normalize(query);
            List<SearchResult> results = new ArrayList<>();

            for (Item item : catalog.getAll()) {
                int bestScore = Integer.MAX_VALUE;

                for (String term : item.searchTerms()) {
                    String normalizedTerm = normalize(term);

                    int score = calculateScore(
                        normalizedQuery,
                        normalizedTerm
                    );

                    bestScore = Math.min(bestScore, score);
                }

                if (bestScore <= allowedDistance(normalizedQuery)) {
                    results.add(new SearchResult(item, bestScore));
                }
            }

            results.sort(
                Comparator.comparingInt(SearchResult::score)
                    .thenComparing(
                        result -> result.item().displayName()
                    )
            );

            return results;
        }

        private int calculateScore(String query, String term) {
            if (query.equals(term)) {
                return 0;
            }

            if (term.startsWith(query) || term.contains(query)) {
                return 0;
            }

            return levenshtein(query, term);
        }

        private int allowedDistance(String query) {
            if (query.length() <= 4) {
                return 1;
            }

            return 2;
        }
    }

    record SearchResult(Item item, int score) {}

    // Normalizes case and removes accents.
    static String normalize(String text) {
        return Normalizer.normalize(
                text.toLowerCase(Locale.ROOT),
                Normalizer.Form.NFD
            )
            .replaceAll("\\p{M}", "")
            .trim();
    }

    // Calculates the minimum number of single-character edits.
    static int levenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1)
                    ? 0
                    : 1;

                dp[i][j] = Math.min(
                    Math.min(
                        dp[i - 1][j] + 1,
                        dp[i][j - 1] + 1
                    ),
                    dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }
}
