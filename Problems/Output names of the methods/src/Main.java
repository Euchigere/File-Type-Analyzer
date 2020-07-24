class CreateInstance {

    public static SuperClass create() {

        /* create an instance of an anonymous class here,
           do not forget ; on the end */
        return new SuperClass() {
            @Override
            public void method2() {
                System.out.println("method2");
            }

            @Override
            public void method3() {
                System.out.println("method3");
            }
        };
    }
}