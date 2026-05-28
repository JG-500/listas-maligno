public class Main {
    public static void main(String[] args) {
        ListaCircular circulo = new ListaCircular<>();
        ListaBidiretiva bidiretiva = new ListaBidiretiva<>();

        System.out.println("TESTES LISTA CIRCULAR");
        try{
            for (int i = 0; i < 10; i++){
                circulo.guardeUmItemNoFinal("teste" + i);
            }

            ListaCircular clone = new ListaCircular<>(circulo);

            System.out.println(circulo.equals(clone));

            System.out.println(circulo.getQuantidade());
            circulo.removaItemDoInicio();
            circulo.removaItemDoFinal();

            circulo.removaItemIndicado("teste5");
            circulo.removaItemIndicado(5);
            System.out.println(circulo);
            System.out.println(circulo.equals(clone));
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        System.out.println("==================================");
    }
}
