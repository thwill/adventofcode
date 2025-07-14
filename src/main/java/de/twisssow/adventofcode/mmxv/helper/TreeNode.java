package de.twisssow.adventofcode.mmxv.helper;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

    private T value;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        child.parent = this;
        this.children.add(child);
    }

    public void removeChild(TreeNode<T> child) {
        this.children.remove(child);
        child.parent = null;
    }

    /**
     * Sucht rekursiv nach einem Wert im Baum.
     */
    public boolean contains(T target) {
        if (this.value.equals(target)) {
            return true;
        }
        for (TreeNode<T> child : children) {
            if (child.contains(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt den Baum als String mit Einrückungen aus (für Debug-Zwecke).
     */
    public String toString() {
        return toString(0);
    }

    private String toString(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        sb.append(value).append("\n");
        for (TreeNode<T> child : children) {
            sb.append(child.toString(level + 1));
        }
        return sb.toString();
    }
}
