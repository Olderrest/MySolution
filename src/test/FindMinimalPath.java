package test;

import java.util.*;

/**
 * Created by PILOT on 07.06.2017.
 */
public class FindMinimalPath {
    private Map<Edge, Integer> weights = new HashMap<Edge, Integer>();
    private List<Edge> edges = new ArrayList<Edge>();
    private List<City> cities = new ArrayList<City>();

    // This method for reading data from keyboard
    private void readData()  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the quantity of cities");
        int countCity = sc.nextInt();
        for (int i = 1; i <= countCity; i++) {
            sc.nextLine();
            System.out.println("Enter the name of the city");
            String name = sc.nextLine();
            System.out.println("Enter the quantity of neighbors in " + name);
            int neighbours = sc.nextInt();
            City newCity = new City(name, i);
            cities.add(newCity);
            for (int j = 0; j < neighbours; j++) {
                System.out.println("Enter the index of the neighbor of " + name);
                int index = sc.nextInt();
                System.out.println("Enter the cost");
                int weight = sc.nextInt();
                Edge newEdge = new Edge(newCity, index);
                edges.add(newEdge);
                weights.put(newEdge, weight);
            }
        }
        System.out.println("Enter the number of paths to find");
        int numberOfPathToFind = sc.nextInt();
        for (int i = 0; i < numberOfPathToFind; i++) {
            System.out.println("Enter the two cities for get minimum cost between pairs of cities");
            sc.nextLine();
            System.out.println("Select firs city ");
            String firstName = sc.nextLine();
            System.out.println("Select second city ");
            String secondName = sc.nextLine();
            City source = getCity(firstName);
            City destination = getCity(secondName);
            List<City> newList = new ArrayList<>(cities);
            Graph graph = new Graph(newList , edges);
            System.out.println(getMinimalPath(graph, weights, source, destination)); // Print minimal path in console
        }
    }
    // Get city if we know his name
    private City getCity(String name) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equalsIgnoreCase(name)) {
                return cities.get(i);
            }
        }
        return null;
    }
    // Method for get destination city from edge
    private City getCityFromEdge(Edge edge) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getIndex() == edge.getDestination()) {
                return cities.get(i);
            }
        }
        return null;
    }

    // Here we are get a minimal path with help dijkstra algorithm
    private int getMinimalPath(Graph graph, Map<Edge, Integer> weights, City source, City destination) {
        City finalDestination = destination;
        List<City> noVisited = graph.getCities().subList(0, cities.size());
        Map<City, DijkstraData> path = new HashMap<City, DijkstraData>(); // Map for store price for each vertex
        path.put(source, new DijkstraData(null, 0)); // Put source city to Map

        while (true) {
            // Firs need find vertex that we will 'open'
            // will search in noVisited
            City toOpen = null;
            int bestPrise = Integer.MAX_VALUE / 2;
            for (City vertex : noVisited) {
                if (path.containsKey(vertex) && path.get(vertex).getPrise() < bestPrise) {
                    toOpen = vertex;
                    bestPrise = path.get(vertex).getPrise();
                }
            }
            if (toOpen == null) {
                return 0;
            }
            //if we find destination City - quit of the cycle
            if (toOpen == destination) {
                break;
            }
            //For 'open' the vertex, need walk on all edges where this vertex is source vertex
            for (Edge edge : graph.getEdges()) {
                if (edge.getSource().equals(toOpen)) {
                    int currentPrise = path.get(toOpen).getPrise() + weights.get(edge);
                    City destinationCity = getCityFromEdge(edge);
                    //if for this vertex not find price or price better than current price - update path
                    if (!path.containsKey(destinationCity) || path.get(destinationCity).getPrise() > currentPrise) {
                        path.put(destinationCity, new DijkstraData(toOpen, currentPrise));
                    }
                }
            }
            noVisited.remove(toOpen);
        }

        int minimalWay = path.get(finalDestination).getPrise();
        return minimalWay;
    }

    public static void main(String[] args) throws Exception {
        FindMinimalPath test = new FindMinimalPath();
        test.readData();
    }

    // Class for storage price of the vertex and where we came from
    class DijkstraData {
        private City previous;
        private int prise;


        public DijkstraData(City previous, int prise) {
            this.previous = previous;
            this.prise = prise;
        }

        public int getPrise() {
            return prise;
        }

        public void setPrise(int prise) {
            this.prise = prise;
        }

        public City getPrevious() {
            return previous;
        }

        public void setPrevious(City previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return "DijkstraData{" +
                    "previous=" + previous +
                    ", prise=" + prise +
                    '}';
        }
    }

    class City {
        private String name;
        private int index;

        public City(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public class Edge {
        private City source;
        private int destination;

        public Edge(City source, int destination) {
            this.source = source;
            this.destination = destination;
        }

        public City getSource() {
            return source;
        }

        public void setSource(City source) {
            this.source = source;
        }

        public int getDestination() {
            return destination;
        }

        public void setDestination(int destination) {
            this.destination = destination;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "source=" + source +
                    ", destination=" + destination +
                    '}';
        }
    }

    public class Graph {
        private List<City> cities = new ArrayList<City>();
        private List<Edge> edges = new ArrayList<Edge>();

        public Graph(List<City> cities, List<Edge> edges) {
            this.cities = cities;
            this.edges = edges;
        }

        public List<City> getCities() {
            return cities;
        }

        public void setCities(List<City> cities) {
            this.cities = cities;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void setEdges(List<Edge> edges) {
            this.edges = edges;
        }

    }
}
