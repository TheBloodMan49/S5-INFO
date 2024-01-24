public class REstDiviseur implements R<Integer> {
    public boolean canReact(Integer x, Integer y) {
        return x % y == 0;
    }
}
