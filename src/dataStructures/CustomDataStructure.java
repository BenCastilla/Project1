package dataStructures;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomDataStructure<T extends CDSCompatible<T>> implements Iterable<T>{
        Node head;

        public void add(T t) {
            if(head == null)
                head = new Node(t);
            else if (head.compareTo(t) < 0) {
                Node n = head;
                head = new Node(t, n);
            }
            else
                add(t, head);
        }

    public void add(T t, Node n) {
        if(n.hasNext()) {
            if(n.getNext().compareTo(t) < 0)
                n.setNext(new Node(t, n.getNext()));
            else
                add(t, n.getNext());
        } else {
            n.setNext(new Node(t));
        }
    }

        public T find(int id) {
            Node finding = head;
            while (finding.getData().getId() != id) {
                if (finding.hasNext())
                    finding = finding.getNext();
                else
                    return null;
            }
            return finding.getData();
        }

    class Node implements Comparable<T>{
        private T data;
        private Node next = null;

        Node (T d) {
            data = d;
        }

        Node (T d, Node n) {
            data = d;
            next = n;
        }

        Node getNext(){
            return next;
        }

        T getData() {
            return data;
        }

        void setNext(Node n) {
            this.next = n;
        }

        boolean hasNext() {
            return next != null;
        }

        @Override
        public int compareTo(T o) {
            return getData().compareTo(o);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node item = new Node(null, head);
            @Override
            public boolean hasNext() {
                return item.hasNext();
            }

            @Override
            public T next() {
                item = item.getNext();
                return item.getData();
            }
        };
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String json = "[\n";
        for(T t : this){
            json += om.writeValueAsString(t)+",\n";
        }
        return json.substring(0, json.length()-2)+"\n ]";
    }

    T get(int id) {
        return find(id);
    }
}
