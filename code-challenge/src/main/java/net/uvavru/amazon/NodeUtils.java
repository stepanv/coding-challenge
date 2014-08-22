package net.uvavru.amazon;

/**
 * Created by stepan on 22.8.2014.
 */
public class NodeUtils {
    public static Node reverseIteratively(Node node) {
        if (node == null) {
            return null;
        }

        Node previousNode = node;
        Node currentNode = node.getNext();
        previousNode.setNext(null);

        while (currentNode.getNext() != null) {
            Node nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }
}
