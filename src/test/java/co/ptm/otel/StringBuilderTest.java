package co.ptm.otel;

public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(new char[1_000_000]);

        do{
            sb.append(12345);
            sb.delete(0,5);
        }while (Thread.currentThread().isAlive());

        System.out.println(sb);
    }
}
