package uk.ac.bris.cs.scotlandyard.ui.ai;

import java.util.*;
import java.util.function.Consumer;

import com.sun.corba.se.impl.orbutil.graph.Graph;
//import com.sun.org.apache.xml.internal.security.Init;
import uk.ac.bris.cs.scotlandyard.ai.ManagedAI;
import uk.ac.bris.cs.scotlandyard.ai.PlayerFactory;
import uk.ac.bris.cs.scotlandyard.model.*;
import uk.ac.bris.cs.gamekit.graph.*;

import javax.xml.crypto.dsig.TransformService;

// TODO name the AI
@ManagedAI("Scotty McScotface")
public class ScottyMcScotface implements PlayerFactory {

    // TODO create a new player here
    @Override
    public Player createPlayer(Colour colour) {
        return new MyPlayer();
    }

    // TODO A sample player that selects a random move
    private static class MyPlayer implements Player {

        private final Random random = new Random();

        @Override
        public void makeMove(ScotlandYardView view, int location, Set<Move> moves,
                             Consumer<Move> callback) {
            // TODO do something interesting here; find the best move

            ImmutableGraph<Integer, Transport> g = (ImmutableGraph) view.getGraph();
            callback.accept(miniMax(moves, view, g));
        }

        /**
         * Attributes a value to the current game state
         *
         * @param view current game state
         * @return The value of the current game state.
         */
        private int moveScore(ScotlandYardView view, Move move, ImmutableGraph<Integer, Transport> gameGraph,
                              int difficulty) {
            //DONE
            int fomf = freedomOfMovementFactor(move, gameGraph, view);
            int distanceFactor = manageMoveForDistanceFactor(move, view, gameGraph, difficulty);

            return (distanceFactor+ fomf);
        }


        /**
         * Implementation of the minimax algorithm for the player
         *
         * @param moves Possible moves the player can make
         * @param view  the view of the current game state
         * @param gameGraph the game graph.
         * @return the best move to be made.
         */
        private Move miniMax(Set<Move> moves, ScotlandYardView view, ImmutableGraph<Integer, Transport> gameGraph) {
            //TODO
            List<Move> m = new ArrayList<>(moves);
            List<Integer> score = new ArrayList<>();


            m.forEach(move -> {
                score.add(moveScore(view, move, gameGraph, 5));
            });

            return m.get(maximumIntIndex(score));
            //throw new RuntimeException("Implement me");
        }
        /**
         * Gives the freedom of movement value for a possible move
         *
         * @param m the move to be made
         * @param g the graph of the current game.
         * @return the freedom of movement integer for the destination of the move
         */
        private int freedomOfMovementFactor(Move m, ImmutableGraph<Integer, Transport> g, ScotlandYardView view) {
            List<Integer> detectivePostions = detectivePostions(view);
            if (m instanceof TicketMove) {
                return nodeFreedomFactor((TicketMove) m, g, detectivePostions);
            } else if (m instanceof PassMove) {
                int xPostion = view.getPlayerLocation(Colour.Black);
                TicketMove t = new TicketMove(Colour.Black, Ticket.Taxi, xPostion);

                return nodeFreedomFactor(t, g, detectivePostions);
            } else if (m instanceof DoubleMove) {
                return (int) (nodeFreedomFactor(((DoubleMove) m).secondMove(), g, detectivePostions));
            } else throw new RuntimeException("Implement me");
        }

        /**
         * Manages a move to find the distance factor
         * @param move move to be made
         * @param view the game view
         * @param gameGraph the game graph
         * @param difficulty depth of search
         * @return the distance factor
         */
        private int manageMoveForDistanceFactor(Move move, ScotlandYardView view, ImmutableGraph gameGraph, int difficulty)
        {
            Node<Integer> moveNode;
            int distanceFactor;
            if(move instanceof PassMove)
            {
                distanceFactor = 0;
            }
            else if(move instanceof DoubleMove)
            {
                moveNode = gameGraph.getNode(((DoubleMove) move).finalDestination());
                distanceFactor = distanceFromDetectiveOnNode(moveNode, gameGraph, detectivePostions(view),difficulty);
            }
            else if(move instanceof TicketMove)
            {
                moveNode = gameGraph.getNode(((TicketMove) move).destination());
                distanceFactor = distanceFromDetectiveOnNode(moveNode, gameGraph, detectivePostions(view),difficulty);
            }
            else throw new IllegalArgumentException();

            return distanceFactor;
        }

        private int maximumIntIndex(List<Integer> l) {
            int[] min = {l.get(0)};
            int[] indexOfMin = {0};

            l.forEach(i ->
            {
                if (i < min[0]) {
                    min[0] = i;
                    indexOfMin[0] =l.indexOf(i);
                }
            });


            return indexOfMin[0];
        }

        /**
         * Returns a list of the integer locations of all the detectives.
         *
         * @param view the game state.
         * @return {@link List} of all player locations
         */
        private List<Integer> detectivePostions(ScotlandYardView view) {
            List<Integer> detectivePostiions = new ArrayList<>();

            view.getPlayers().forEach(colour ->
            {
                if (colour != Colour.Black)
                    detectivePostiions.add(view.getPlayerLocation(colour));
            });

            return detectivePostiions;
        }


        /**
         * Gets the number of nodes connected to some destination which are empty.
         *
         * @param m      The move to be made
         * @param g      the game graph
         * @param detPos the position of all detectives within the game.
         * @return
         */
        private int nodeFreedomFactor(TicketMove m, ImmutableGraph<Integer, Transport> g, List<Integer> detPos) {
            int[] nodeFreedomFactor = {0};
            Collection<Edge<Integer, Transport>> edges = g.getEdgesFrom(g.getNode(m.destination()));
            edges.forEach(e -> {
                if (!detPos.contains(e.destination().value()))
                    nodeFreedomFactor[0] += 1;
            });

            return nodeFreedomFactor[0];
        }

        /**
         * Gives the maximum distance in terms of number of moves necessary to a detective.
         * @param rootNode the source node for the search.
         * @param gameGraph the graph of the gamestate containing all edges and nodes.
         * @param detectivePositions a list of integers of the detective positions
         * @param difficulty the depth of a search to neighboring nodes
         * @return the minimum node distance.
         */
        private int distanceFromDetectiveOnNode(Node<Integer> rootNode, ImmutableGraph<Integer, Transport> gameGraph,
                                                List<Integer> detectivePositions, int difficulty)
        {
            Collection<Edge<Integer, Transport>> edgesFromRoot = gameGraph.getEdgesFrom(rootNode);
            List<Integer> distancesToDetectivesOnEdge = new ArrayList<>();

            edgesFromRoot.forEach(edge ->
            {
                int val = spanEdge(edge, detectivePositions, 0, gameGraph, difficulty);
                distancesToDetectivesOnEdge.add(val);
            }
            );

            return getMinimumInt(distancesToDetectivesOnEdge);

        }

        /**
         * Explores the graph recursively along edges until detective is found
         * @param edgeOnRoot the source edge from which we are exploring
         * @param detectivePostions positions of all the detectives
         * @param depth the distance we are from root node
         * @param gameGraph the graph of the game
         * @param maxDepth maximum distance we search from root node
         * @return minimum distance of root node from detective
         */
        private int spanEdge(Edge<Integer, Transport> edgeOnRoot, List<Integer> detectivePostions,
                             int depth, ImmutableGraph<Integer, Transport> gameGraph, int maxDepth)
        {
            depth++; int ret;
            List<Integer> edgeValues = new ArrayList<>();
            int[] de = {depth};
            if(checkEdge(edgeOnRoot, detectivePostions) || depth == maxDepth) return depth;
            else {

                gameGraph.getEdgesFrom(gameGraph.getNode(edgeOnRoot.destination().value())).forEach(edge ->
                {
                    edgeValues.add(spanEdge(edge, detectivePostions, de[0], gameGraph, maxDepth));
                });
            }

            ret = getMinimumInt(edgeValues);
            return ret;
        }

        /**
         * Determines if an edge leads to a detecive
         * @param edge edge to search along
         * @param detectivePositions the list of all positions of the detectives
         * @return true if edge leads to detective, false otherwise.
         */
        private boolean checkEdge(Edge<Integer, Transport> edge, List<Integer> detectivePositions)
        {
            return (detectivePositions.contains(edge.destination().value()));
        }

        private Integer getMinimumInt(List<Integer> numbers)
        {
            int[] ret = {0};

            numbers.forEach(num -> {
                if(num > ret[0]) ret[0] = num;
            });

            return ret[0];
        }

    }
}
