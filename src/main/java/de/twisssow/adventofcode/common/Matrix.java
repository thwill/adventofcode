package de.twisssow.adventofcode.common;

import de.twisssow.adventofcode.common.MatrixPosition;

public class Matrix<T> {

    public static final MatrixPosition INVALID_POSITION = new MatrixPosition(-1, -1);

    private final T[][] data;
    private final int rows;
    private final int cols;

    @SuppressWarnings("unchecked")
    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive");
        }
        this.rows = rows;
        this.cols = cols;
        this.data = (T[][]) new Object[rows][cols];
    }

    /**
     * Gibt das Element an Position (row, col) zurück (1-basiert).
     */
    public T get(int row, int col) {
        checkBounds(row, col);
        return data[row - 1][col - 1];
    }

    /**
     * Gibt das Element an der angegebenen Position zurück.
     */
    public T get(MatrixPosition pos) {
        return get(pos.getRow(), pos.getCol());
    }

    /**
     * Setzt das Element an Position (row, col) auf value (1-basiert).
     */
    public void set(int row, int col, T value) {
        checkBounds(row, col);
        data[row - 1][col - 1] = value;
    }

    /**
     * Setzt das Element an der angegebenen Position auf value.
     */
    public void set(MatrixPosition pos, T value) {
        set(pos.getRow(), pos.getCol(), value);
    }

    /**
     * Anzahl der Zeilen.
     */
    public int rows() {
        return rows;
    }

    /**
     * Anzahl der Spalten.
     */
    public int cols() {
        return cols;
    }

    private void checkBounds(int row, int col) {
        if (row < 1 || row > rows) {
            throw new IndexOutOfBoundsException("Row out of bounds: " + row);
        }
        if (col < 1 || col > cols) {
            throw new IndexOutOfBoundsException("Column out of bounds: " + col);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                sb.append(get(r, c)).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
