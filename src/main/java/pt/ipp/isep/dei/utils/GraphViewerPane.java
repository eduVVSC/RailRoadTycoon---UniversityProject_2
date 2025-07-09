package pt.ipp.isep.dei.utils;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.fx_viewer.FxDefaultView;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.geom.Point2;
import org.graphstream.ui.view.camera.Camera;


public class GraphViewerPane extends StackPane {
    private FxViewer viewer;
    private FxDefaultView view;
    private final double[] sizes = {0.0, 20.0};

    public GraphViewerPane(IGraph igraph) {
        if (!(igraph instanceof GraphStreamGraphAdapter)) {
            throw new IllegalArgumentException("GraphViewerPane currently only supports GraphStreamGraphAdapter");
        }
        MultiGraph graph = ((GraphStreamGraphAdapter) igraph).getGraphStreamGraph();

        initializeViewer(graph);
        setBackgroundImage();
        setupZoomAndScroll(graph);
        applyInitialNodeStyles(graph);
        applyRoundedClipping();
    }

    private void initializeViewer(Graph graph) {
        viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.disableAutoLayout();
        view = (FxDefaultView) viewer.addDefaultView(false);
        this.getChildren().add(view);
    }

    private void setBackgroundImage() {
        Image background = new Image(getClass().getResource("/images/grassBackground.png").toExternalForm());

        view.setBackLayerRenderer((g, gg, px2Gu, width, height, minX, minY, maxX, maxY) -> {
            if (g != null && background.getPixelReader() != null) {
                try {
                    g.drawImage(background, 0, 0, width, height);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void applyInitialNodeStyles(Graph graph) {
        for (Node node : graph) {
            node.setAttribute("ui.style", "size: " + sizes[1] + "px; text-size: " + sizes[0] + "px;");
        }
    }

    private void setupZoomAndScroll(Graph graph) {
        view.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
                sizes[0] += 0.5;
                sizes[1] += 0.8;
            } else if (event.getDeltaY() < 0) {
                sizes[0] = Math.max(1.0, sizes[0] - 0.5);
                sizes[1] = Math.max(2.0, sizes[1] - 0.8);
            }

            for (Node node : graph) {
                node.setAttribute("ui.style", "size: " + sizes[1] + "px; text-size: " + sizes[0] + "px;");
            }

            handleCameraZoom(event.getDeltaY(), event.getX(), event.getY());
            event.consume();
        });
    }

    private void handleCameraZoom(double deltaY, double mouseX, double mouseY) {
        Camera cam = view.getCamera();
        double zoomStep = 1.1;
        double oldViewPercent = cam.getViewPercent();

        Point2 graphCoords = cam.transformPxToGu((float) mouseX, (float) mouseY);

        double newViewPercent = deltaY > 0 ? oldViewPercent / zoomStep : oldViewPercent * zoomStep;
        newViewPercent = Math.max(0.01, Math.min(5, newViewPercent));
        double zoomFactor = newViewPercent / oldViewPercent;

        Point2 camCenter = cam.getViewCenter();
        double newCamX = graphCoords.x - (graphCoords.x - camCenter.x) * zoomFactor;
        double newCamY = graphCoords.y - (graphCoords.y - camCenter.y) * zoomFactor;

        cam.setViewPercent(newViewPercent);
        cam.setViewCenter(newCamX, newCamY, 0);
    }

    private void applyRoundedClipping() {
        Rectangle clip = new Rectangle();
        clip.setArcWidth(30);
        clip.setArcHeight(30);

        widthProperty().addListener((obs, oldVal, newVal) -> clip.setWidth(newVal.doubleValue()));
        heightProperty().addListener((obs, oldVal, newVal) -> clip.setHeight(newVal.doubleValue()));

        setClip(clip);
    }

    public void close() {
        if (viewer != null) {
            viewer.close();
            viewer = null;
        }

        if (view != null) {
            getChildren().remove(view);
            view = null;
        }
    }

}


