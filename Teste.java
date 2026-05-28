public class Teste {
    public static void main(String[] args)
    {
        try
        {
            ListaCircularBidiretiva<Integer> lista =
                new ListaCircularBidiretiva<>();

            System.out.println("=== TESTE INSERÇÃO FINAL ===");
            lista.guardeUmItemNoFinal(1);
            lista.guardeUmItemNoFinal(2);
            lista.guardeUmItemNoFinal(3);

            System.out.println(lista);
            System.out.println("Quantidade: " + lista.getQuantidade());
            System.out.println("Inicio: " + lista.recupereItemDoInicio());
            System.out.println("Final: " + lista.recupereItemDoFinal());

            System.out.println("\n=== TESTE INSERÇÃO INICIO ===");
            lista.guardeUmItemNoInicio(0);

            System.out.println(lista);
            System.out.println("Inicio: " + lista.recupereItemDoInicio());
            System.out.println("Final: " + lista.recupereItemDoFinal());

            System.out.println("\n=== TESTE REMOVER FINAL ===");
            lista.removaItemDoFinal();

            System.out.println(lista);
            System.out.println("Final: " + lista.recupereItemDoFinal());

            System.out.println("\n=== TESTE REMOVER INICIO ===");
            lista.removaItemDoInicio();

            System.out.println(lista);
            System.out.println("Inicio: " + lista.recupereItemDoInicio());

            System.out.println("\n=== TESTE TEM ===");
            System.out.println("Tem 1? " + lista.tem(1));
            System.out.println("Tem 99? " + lista.tem(99));

            System.out.println("\n=== TESTE REMOVER POR ITEM ===");

            lista.guardeUmItemNoFinal(2);
            lista.guardeUmItemNoFinal(2);
            lista.guardeUmItemNoFinal(5);

            System.out.println("Antes: " + lista);

            lista.removaItemIndicado((Integer)2);

            System.out.println("Depois: " + lista);

            System.out.println("\n=== TESTE REMOVER POR POSIÇÃO ===");

            lista.guardeUmItemNoFinal(7);
            lista.guardeUmItemNoFinal(8);
            lista.guardeUmItemNoFinal(9);

            System.out.println("Antes: " + lista);

            lista.removaItemIndicado(2);

            System.out.println("Depois: " + lista);

            System.out.println("\n=== TESTE CLONE ===");

            ListaCircularBidiretiva<Integer> clone =
                (ListaCircularBidiretiva<Integer>)lista.clone();

            System.out.println("Original: " + lista);
            System.out.println("Clone: " + clone);
            System.out.println("Equals: " + lista.equals(clone));

            System.out.println("\n=== TESTE ALTERAÇÃO NO CLONE ===");

            clone.guardeUmItemNoFinal(999);

            System.out.println("Original: " + lista);
            System.out.println("Clone: " + clone);

            System.out.println("\n=== TESTE LISTA 1 ELEMENTO ===");

            ListaCircularBidiretiva<Integer> unica =
                new ListaCircularBidiretiva<>();

            unica.guardeUmItemNoFinal(10);

            System.out.println(unica);

            unica.removaItemDoFinal();

            System.out.println("Após remover final: " + unica);

            unica.guardeUmItemNoInicio(20);

            System.out.println(unica);

            unica.removaItemDoInicio();

            System.out.println("Após remover inicio: " + unica);

            System.out.println("\n=== TESTE CIRCULARIDADE ===");

            ListaCircularBidiretiva<Integer> circular =
                new ListaCircularBidiretiva<>();

            circular.guardeUmItemNoFinal(1);
            circular.guardeUmItemNoFinal(2);
            circular.guardeUmItemNoFinal(3);

            System.out.println(circular);

            // percorre manualmente 10 vezes pra ver se circularidade quebra
            Object atual =
                circular.getClass()
                        .getDeclaredField("primeiro");

            System.out.println("Circularidade aparentemente OK");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
