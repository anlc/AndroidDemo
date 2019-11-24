package com.android.demo;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2019-11-08
 */
public class JavaTest {
    static class A {
        private B b;

        A() {
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    static class B {
        private A a;

        public B() {
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    static class C {
        private A a;
        private B b;

        C() {
            b = new B();
            a = new A();

            b.setA(a);
            a.setB(b);
        }
    }

    private C c;

    private void setC(C c) {
        this.c = c;
    }

    public static void main(String[] args) {

        JavaTest javaTest = new JavaTest();

        C c = new C();
        javaTest.setC(c);

        System.gc();

        javaTest = null;
        System.gc();
    }
}