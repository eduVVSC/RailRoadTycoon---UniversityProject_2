package pt.ipp.isep.dei.utils;

import org.graphstream.graph.implementations.MultiGraph;
import pt.ipp.isep.dei.domain.City;
import pt.ipp.isep.dei.domain.industries.Industry;
import pt.ipp.isep.dei.domain.position.HouseBlock;
import pt.ipp.isep.dei.domain.rails.RailType;
import pt.ipp.isep.dei.domain.rails.RailwayLine;
import pt.ipp.isep.dei.domain.rails.TrackType;
import pt.ipp.isep.dei.domain.station.StationType;

import java.util.List;

public class GraphStreamGraphBuilderAdapter implements IGraphBuilder {

    @Override
    public IGraph buildGraph(List<City> cities,
                             List<StationType> stations,
                             List<Industry> industries,
                             List<RailwayLine> railwayLines) {
        MultiGraph mGraph = new MultiGraph("RailNetwork");
        GraphStreamGraphAdapter graph = new GraphStreamGraphAdapter(mGraph);
        graph.setStyleSheet("src/main/resources/css/graphBuilder.css");

        if (stations != null && !stations.isEmpty()) {
            addStations(graph, stations);
        }
        if (industries != null && !industries.isEmpty()) {
            addIndustries(graph, industries);
        }
        if (railwayLines != null && !railwayLines.isEmpty()) {
            addRailwayLines(graph, railwayLines);
        }
        if (cities != null && !cities.isEmpty()) {
            addCities(graph, cities);
        }

        return graph;
    }

    private void addStations(GraphStreamGraphAdapter graph, List<StationType> stations) {
        for (StationType station : stations) {
            addStation(graph, station);
        }
    }

    private void addStation(GraphStreamGraphAdapter graph, StationType station) {
        String nodeId = station.getName() + station.getLocation().getPosition().toString();
        if (!graph.hasNode(nodeId)) {
            float x = station.getLocation().getPosition().getX();
            float y = station.getLocation().getPosition().getY();
            graph.addNode(nodeId, nodeId, "station", x, y);
        }
    }

    private void addIndustries(GraphStreamGraphAdapter graph, List<Industry> industries) {
        for (Industry industry : industries) {
            addIndustry(graph, industry);
        }
    }

    private void addIndustry(GraphStreamGraphAdapter graph, Industry industry) {
        String nodeId = industry.getType().name() + industry.getLocation().getPosition().toString();
        if (!graph.hasNode(nodeId)) {
            float x = industry.getLocation().getPosition().getX();
            float y = industry.getLocation().getPosition().getY();
            graph.addNode(nodeId, nodeId, "industry", x, y);
        }
    }

    private void addCities(GraphStreamGraphAdapter graph, List<City> cities) {
        for (City city : cities) {
            addCity(graph, city);
        }
    }

    private void addCity(GraphStreamGraphAdapter graph, City city) {
        String cityId = city.getName() + city.getLocation().getPosition().toString();
        if (!graph.hasNode(cityId)) {
            float x = city.getPosition().getX();
            float y = city.getPosition().getY();
            graph.addNode(cityId, cityId, "city", x, y);

            if (city.getHouseBlocks() != null && !city.getHouseBlocks().isEmpty()) {
                for (HouseBlock houseBlock : city.getHouseBlocks()) {
                    String blockId = houseBlock.toString() + houseBlock.getPosition();
                    if (!graph.hasNode(blockId)) {
                        float bx = houseBlock.getPosition().getX();
                        float by = houseBlock.getPosition().getY();
                        graph.addNode(blockId, blockId, "house", bx, by);
                    }
                }
            }
        }
    }

    private void addRailwayLines(GraphStreamGraphAdapter graph, List<RailwayLine> railwayLines) {
        for (RailwayLine line : railwayLines) {
            addRailwayLine(graph, line);
        }
    }

    private void addRailwayLine(GraphStreamGraphAdapter graph, RailwayLine line) {
        String station1 = line.getStation1().getName() + line.getStation1().getLocation().getPosition().toString();
        String station2 = line.getStation2().getName() + line.getStation2().getLocation().getPosition().toString();
        String edgeId = station1 + "-" + station2;

        if (!graph.hasEdge(edgeId) && !graph.hasEdge(station2 + "-" + station1)) {
            StringBuilder edgeClass = new StringBuilder();

            if (line.getRailType() == RailType.ELECTRIFIED) {
                edgeClass.append("electrified");
            } else {
                edgeClass.append("nonElectrified");
            }

            if (line.getTrackType() == TrackType.SINGLE_RAIL) {
                edgeClass.append("SingleTrack");
            } else {
                edgeClass.append("DoubleTrack");
            }

            graph.addEdge(edgeId, station1, station2, edgeClass.toString());
        }
    }
}
