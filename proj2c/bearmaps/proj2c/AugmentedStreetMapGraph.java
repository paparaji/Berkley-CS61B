package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.*;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private Map<Point, Node> pointToNode;

    private List<Point> points;

    private Map<String, List<Node>> nameToNodes;

    private TrieSet61B trieSet;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        points = new ArrayList<>();
        pointToNode = new HashMap<>();
        nameToNodes = new HashMap<>();
        trieSet = new MyTrieSet();
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (name(node.id()) != null) {
                String lowercaseName = cleanString(name(node.id()));
                trieSet.add(lowercaseName);
                if (!nameToNodes.containsKey(lowercaseName)) {
                    nameToNodes.put(lowercaseName, new LinkedList<>());
                }
                nameToNodes.get(lowercaseName).add(node);
            }
            if (neighbors(node.id()).size() > 0) {
                Point point = new Point(node.lon(), node.lat());
                points.add(point);
                pointToNode.put(point, node);
            }
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     *
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        PointSet pointSet = new WeirdPointSet(points);
        Point result = pointSet.nearest(lon, lat);
        return pointToNode.get(result).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     *
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> result = trieSet.keysWithPrefix(prefix);
        List<String> result2 = new LinkedList<>();
        for (String str : result) {
            for (Node node : nameToNodes.get(str)) {
                if (!result2.contains(node.name())) {
                    result2.add(node.name());
                }
            }
        }
        return result2;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     *
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String lowercaseName = cleanString(locationName);
        List<Node> nodes = nameToNodes.get(lowercaseName);
        List<Map<String, Object>> result = new LinkedList<>();
        for (Node node : nodes) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("lat", node.lat());
            temp.put("lon", node.lon());
            temp.put("name", node.name());
            temp.put("id", node.id());
            result.add(temp);
        }
        return result;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     *
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
