package de.twisssow.adventofcode.mmxv.day19;

import de.twisssow.adventofcode.common.TreeNode;

import java.util.*;

public class TreeFromReplacements {

    public static TreeNode<Replacement> buildTree(Set<Replacement> relations, String rootName) {
        // Wir merken uns Knoten nach Name (String) → TreeNode<Replacement>
        Map<String, TreeNode<Replacement>> nodes = new HashMap<>();

        // Root-Knoten anlegen
        TreeNode<Replacement> root = new TreeNode<>(new Replacement(null, rootName));
        nodes.put(rootName, root);

        for (Replacement r : relations) {
            String parentName = r.getFrom();
            String childName  = r.getTo();

            // hole oder erstelle Parent-Knoten
            TreeNode<Replacement> parent = nodes.get(parentName);
            if (parent == null) {
                parent = new TreeNode<>(new Replacement(null, parentName));
                nodes.put(parentName, parent);
            }

            // hole oder erstelle Child-Knoten
            TreeNode<Replacement> child = nodes.get(childName);
            if (child == null) {
                child = new TreeNode<>(r); // wir nutzen hier das Replacement aus der Menge
                nodes.put(childName, child);
            }

            // hänge Child an Parent, falls noch nicht vorhanden
            if (!parent.getChildren().contains(child)) {
                parent.addChild(child);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Set<Replacement> replacements = Set.of(
                new Replacement("root", "child1"),
                new Replacement("root", "child2"),
                new Replacement("root", "child3"),
                new Replacement("child1", "child4"),
                new Replacement("child1", "child5"),
                new Replacement("child2", "child6"),
                new Replacement("child3", "child7")
        );

        TreeNode<Replacement> tree = buildTree(replacements, "root");
        printTree(tree, 0);
    }

    public static void printTree(TreeNode<Replacement> node, int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(node.getValue().getTo());
        for (TreeNode<Replacement> child : node.getChildren()) {
            printTree(child, level + 1);
        }
    }
}
