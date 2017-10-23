/**
 * Created by petro on 21-Oct-17.
 */

class Outer {

    private String outerClassField = "outerClassField";
    private static String outerStaticField = "outerStaticField";

    Outer() {
        System.out.println("Outer private constructor");
    }

    private void outerMethod() {
        System.out.println("outerMethod");
    }

    private static void outerStaticMethod() {
        System.out.println("outerStaticMethod");
    }

    class InnerClass {

        // Can access any outer class fields with any access modifier
        public void accessOuterFields() {
            System.out.println(outerClassField);
            System.out.println(outerStaticField);
        }

        // Can access any outer class methods with any access modifier
        public void accessOuterMethods() {
            outerMethod();
            outerStaticMethod();
        }

        // can get Outer class this.
        public Outer getOuterThis() {
            return Outer.this;
        }

        // can get Outer instance even if the Outer constructor is private.
        public Outer getOuterInstance() {
            return new Outer();
        }
    }

    static class InnerStaticClass {

        // Can access outer class !STATIC! fields only with any access modifier
        public void accessOuterFields() {
            // System.out.println(outerClassField); -> won't compile
            System.out.println(outerStaticField);
        }

        // Can access outer class !STATIC! methods only with any access modifier
        public void accessOuterMethods() {
            // outerMethod(); -> won't compile
            outerStaticMethod();
        }

        // can't get Outer class this.
        public void getOuterThis() {
            // return Outer.this; won't compile
        }

        // can get Outer instance even if the Outer constructor is private
        public Outer getOuterInstance() {
            return new Outer();
        }
    }
}

class Testing {
    public static void main(String[] args) {
        // 1) Create outer instance. Use it to create an InnerClass instance.
        Outer outer = new Outer();
        Outer.InnerClass innerClass = outer.new InnerClass();
        // don't need to create an Outer instance
        Outer.InnerStaticClass innerStaticClass = new Outer.InnerStaticClass();
    }
}