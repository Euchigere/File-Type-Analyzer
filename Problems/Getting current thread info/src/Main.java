class Info {

    public static void printCurrentThreadInfo() {
        // get the thread and print its info
        Thread th = Thread.currentThread();
        System.out.println("name: " + th.getName());
        System.out.println("priority: " + th.getPriority());
    }
}