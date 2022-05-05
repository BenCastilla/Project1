package dataStructures;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        CustomDataStructure<testInt> test = new CustomDataStructure<testInt>();

        test.add(new testInt(10));
        test.add(new testInt(6));
        test.add(new testInt(7));
        test.add(new testInt(4));
        test.add(new testInt(23));
        test.add(new testInt(25));
        test.add(new testInt(48));
        test.add(new testInt(89));
        test.add(new testInt(16));
        test.add(new testInt(8));


        System.out.println("test");

        for(testInt i : test) {
            System.out.println(i);
        }

        System.out.println(test.toJSON());

    }

    static class testInt implements CDSCompatible<testInt> {
        int i;

        public testInt(int seti) {
            i = seti;
        }

        @Override
        public int getId() {
            return i * (i % 10);
        }

        @Override
        public int compareTo(testInt o) {
            return i - o.i;
        }

        @Override
        public String toString() {
            return "testInt{" +
                    "i=" + i +"  "+
                    "id=" + getId() +
                    '}';
        }
    }
}
