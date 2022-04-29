import java.util.Arrays;

// here, we're going to make our own custom array list:
// to create a generic class, we use "T" to indicate that this class can be of any type:
public class CustomArrayList <T> implements CustomList<T> {
    // size will be how many objects are in our array list, this size will be dynamic
    private int size = 0;
    // constant, because of the final keyword
    private static final int DEFAULT_CAPACITY = 10;
    // underlying structure, store any type of class:
    private Object elements[];

    // constructor:
    public CustomArrayList() {
        // when we create an array list, we make a new array with the default capacity
        elements = new Object[DEFAULT_CAPACITY];
    }

    // add elements to our array list:
    @Override
    public void add(T element) {
        // check if we need to resize, if we've reached the end of our array:
        if(size == elements.length) {
            resize();
        }
        // assign the element to the current spot as well as increase our size to adjust for the next spot
        // TODO test out ++ position (before or after)
        elements[size++] = element;
        // [1, _, _, _]
    }   //  |  |

    // because our underlying structure is just a regular array, we have to do the resizing manually
    private void resize() {
        int newSize = elements.length * 2;
        // using the Arrays class from java.util, we make a copy of the array with the new size:
        // we have to make a copy because we can't actually change the size of an array:
        elements = Arrays.copyOf(elements, newSize);
    }

    // return the corresponding object based on the index:
    @Override
    public T get(int i) {
        // first check if we are out bounds:
        if ( i >= size || i < 0) {
            // throw an index out of bounds exception if we are out of bounds:
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        return (T) elements[i];
    }


    @Override
    public void print() {

        for(Object element: elements) {
            if(element != null) System.out.print(element + ", ");
        }
        System.out.println();
        // System.out.println("Size: " + size);
    }

    @Override
    public void add(int i, T element) {
        Object[] placeholderArray;


        if(size == elements.length) {
            resize();
        }

        //clone
        placeholderArray = Arrays.copyOf(elements, elements.length);

        for(int x = 0; x < elements.length; x++) {

            if (x == i) {
                placeholderArray[x] = element;
            }
.
            if (x > i) {
                placeholderArray[x] = elements[x-1];
            }
        }

        elements = placeholderArray;


        this.size++;
    }


}