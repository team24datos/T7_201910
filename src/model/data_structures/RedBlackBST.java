/**
 * 
 */
package model.data_structures;

/**
 * @author Team 24
 *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

	/**
	 * Constante que representa el color rojo de un nodo
	 */
    private static final boolean RED   = true;
    
    /**
     * Constante que representa el color negro de un nodo
     */
    private static final boolean BLACK = false;

    /**
     * Constante que reperesenta la raiz del árbol balanceado.  
     */
    private Node root;

    //Clase que representa los nodos que tienen dos hijos, un color rojo o negro y una tupla llave valor. 
    private class Node {
        private Key key;           
        private Value val;         
        private Node left, right;  
        private boolean color;     
        private int size;          

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    /**
     * Constructor del árbol balanceado rojo-negro. 
     */
    public RedBlackBST() { 
    }

    /**
     * Le pregunta a un nodo dado por parametro si es rojo
     * @param x: Nodo del cual se quiere saber la información
     * @return: true si el nodo es rojo, false si es negro
     */
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    /**
     * Retrona el tamaño del subárbol que tiene cada Nodo
     * @param x El nodo del cual se quiere saber la información
     * @return un entero con el tamañod de nodos que tiene el subárbol 
     */
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    } 


    /**
     * Retorna el número de tuplas que hay en el árbol 
     * @return el numero de nodos que hay en el árbol balanceado
     */
    public int size() {
        return size(root);
    }

   /**
     * Le pregunta al árbol balanceado que si está vacío
     * @return true en caso de que el árbol sí esté vacío y false en caso contrario. 
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Devuelve el valor asociado a una llave específica dada por parámetro. 
     * @param La llave del cual se quiere extraer el valor 
     * @return retorna null si no existe la llave en el árbol y retorna el valor de la llave si sí existe. 
     * @throws Lanza una excepción si la llave que le llegó por parámetro es null
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("La llave no puede ser null");
        return get(root, key);
    }

    /**
     * Método recusivo para hacer la búsqueda de una llave específica
     * @param x: El nodo a partir del cual se va a hacer la búsqueda. 
     * @param key: La llave que se está buscando en el subárbol del nodo dado por parámetro. 
     * @return El valor de la llave si la pudo encontrar, null si la llave no está en el árbol. 
     */
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    /**
     * Consulta si existe una llave específica en el árbol balanceado
     * @param La llave específica que se quiere saber si está contenida en el árbol 
     * @return true si la llave está en el árbol y false de lo contrario. 
     * @throws Lanza una excepción si la llave que llegó por parámetro es null. 
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Método que inserta una tupla de llave-valor al árbol binario balanceado. Si el la llave ya existe le cambia el valor al nuevo dado por parámetro.
     * Si el valor es null, entonces borra la tupla del árbol.
     * @param La llave de la tupla que se quiere insertar al árbol 
     * @param El valor asociado a la llave específica. 
     * @throws Lanza una excepción si la llave es null. 
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("La llave no puede ser null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    /**
     * Método que se encarga de hacer la inserción por medio de recursiones a través de los nodos del árbol
     * @param h Nodo a partir del cual se quiere hacer la inserción con recursión.
     * @param key La llave de la tupla que se quiere insertar al árbol 
     * @param val El valor de la tupla que se quiere insertar al árbol
     * @return Retorna el nodo en dónde va la recursión o retorna el nuevo nodo que se creó ya agregado. 
     */
    private Node put(Node h, Key key, Value val) { 
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val); 
        else if (cmp > 0) h.right = put(h.right, key, val); 
        else              h.val   = val;

        // Arregla el árbol en caso de que haya un nodo rojo a la derecha. 
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    /**
     * Quita la tupla llave-valor más pequeña de todo el árbol 
     * @throws Lanza una excepción si el elemento que se quiere borrar no existe. (En este caso si está vacío)
     */
    public void deleteMin() throws Exception {
        if (isEmpty()) throw new Exception("El elemento que se quiere borrar no existe");

        // Si ambos hijos son negros entonces volver la raiz roja. 
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    /**
     * Método que elmina el nodo mínimo del subarbol de un nodo dado por parámetro. 
     * @param h Nodo del cual se quiere eliminar el elemento mínimo. 
     * @return Nodo que se eliminó. 
     */
    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    /**
     * Elimina la tupla llave-valor más grande de todo el árbol. 
     * @throws Lanza una excepción si no existe el elemento que se quiere eliminar.(En este caso si el árbol es vacío)
     */
    public void deleteMax() throws Exception {
        if (isEmpty()) throw new Exception("BST underflow");

        // Si ambos hijos son negros, vuelve la raiz roja
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    /**
     * Método que elmina el nodo máximo del subarbol de un nodo dado por parámetro. 
     * @param h Nodo del cual se quiere eliminar el elemento máximo. 
     * @return Nodo que se eliminó. 
     */
    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    /**
     * Busca la tupla que tiene la llave dada por parámetro y la elimina del árbol binario junto con su valor. 
     * @param  key la llave del elemnto que se quiere eliminar del árbol binario. 
     * @throws Lanza excepción si la llave que llego por parámetro es null
     */
    public void delete(Key key) { 
        if (key == null) throw new IllegalArgumentException("La llave no puede ser null");
        if (!contains(key)) return;

        // Si ambos hijos son negros vuelve la raiz roja. 
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    // Elimina el nodo con una llave igual a la dada por parámetro del árbol recursivamente y con base en el nodo h dado por parámetro. 
    private Node delete(Node h, Key key) { 

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }


    // Método que rota un nodo que tiene hijo izquierdo rojo hacia la derecha
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // Método que se encarga de rotar un nodo que tiene hijo derecho rojo hacia la izquierda
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // Método que invierte los colores de un nodo y los de sus hijos
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Si un nodo h es rojo y su hijo izquierdo y el hijo izquierdo de ese hijo son negros entonces vuelve a su hijo izquierdo rojo. 
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Si un nodo h tiene a su hijo derecho negro y el hijo izquierdo de se hijo también negro entonces puede volver a su hijo derecho negro y a uno de sus nietos también. 
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    //Método que sirve para balancear de nuevo un árbol que no necesariamente está balanceado.
    private Node balance(Node h) {

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * Método que retorna la altura del árbol balanceado
     * @return Un entero que representa la altura del árbol rojo-negro balanceado. 
     */
    public int height() {
        return height(root);
    }
    
    //Método que devuelve la altura del subárbol de un nodo y funciona recursivamente. 
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * Retorna la llave más pequeña en la tabla de símbolos. 
     * @return la llave más pequeña en la tabla de símbolos. 
     * @throws Lanza excepción si la tabla está vacía. 
     */
    public Key min() throws Exception {
        if (isEmpty()) throw new Exception("La tabla de símbolos está vacía");
        return min(root).key;
    } 

    // La llave más pequeña del subárbol de un nodo x dado por parámetro, null si no existe la llave. 
    // Método que funciona recursivamente. 
    private Node min(Node x) { 
        if (x.left == null) 
        	return x; 
        else
        	return min(x.left); 
    } 

    /**
     * Retorna la llave más grande en la tabla de símbolos. 
     * @return la llave más grande  en la tabla de símbolos. 
     * @throws Lanza excepción si la tabla está vacía. 
     */
    public Key max() throws Exception {
        if (isEmpty()) throw new Exception("La tabla de símbolos está vacía");
        return max(root).key;
    } 

    // La llave más grande del subárbol de un nodo x dado por parámetro, null si no existe la llave. 
    // Método que funciona recursivamente. 
    private Node max(Node x) { 
        // assert x != null;
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 
 
}
