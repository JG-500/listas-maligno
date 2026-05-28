import java.lang.reflect.*;

public class ListaCircularBidiretiva<X> {

    private class No
    {
        private X  info;
        private No prox;
        private No ante;

        public No (X i, No p, No a)
        {
            this.info = i;
            this.prox = p;
            this.ante = a;
        }

        public No (X i)
        {
            this.info = i;
            this.prox = null;
            this.ante = null;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getProx ()
        {
            return this.prox;
        }

        public No getAnte(){
            return this.ante;
        }

        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setProx (No p)
        {
            this.prox = p;
        }

        public void setAnte(No a){
            this.ante = a;
        }
    } //fim da classe No

    private No primeiro;

    public ListaCircularBidiretiva ()
    {
        this.primeiro=null;
    }

    private X meuCloneDeX (X x)
    {
        X ret=null;

        try
        {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro)
        {}
        catch(IllegalAccessException erro)
        {}
        catch(InvocationTargetException erro)
        {}

        return ret;
    }

    public void guardeUmItemNoInicio (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            

        if (this.primeiro == null)
        {
            this.primeiro = new No(inserir);
            this.primeiro.setProx(this.primeiro);
            this.primeiro.setAnte(this.primeiro);
            return;
        }

        No ultimo = this.primeiro.getAnte();

        this.primeiro = new No (inserir,this.primeiro, ultimo);
        this.primeiro.getProx().setAnte(this.primeiro);

        ultimo.setProx(this.primeiro);
    }

    public void guardeUmItemNoFinal (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            
        if (this.primeiro==null){
            this.primeiro = new No (inserir);
            this.primeiro.setProx(this.primeiro);
            this.primeiro.setAnte(this.primeiro);
        }
        else
        {
            No ultimo=this.primeiro.getAnte();

            ultimo.setProx(new No (inserir, this.primeiro, ultimo));
            this.primeiro.setAnte(ultimo.getProx());
        }
    }
    
    public X recupereItemDoInicio () throws Exception
    {
        if (this.primeiro==null)
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    public X recupereItemDoFinal () throws Exception
    {
        if (this.primeiro==null)
            throw new Exception ("Nada a obter");

		No ultimo=this.primeiro.getAnte();
			
        X ret = ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    public void removaItemDoInicio () throws Exception
    {
        if (this.primeiro==null)
            throw new Exception ("Nada a remover");

        if (this.primeiro.getProx()==this.primeiro) //so 1 elemento
        {
            this.primeiro=null;
            return;
        }

        No ultimo = this.primeiro.getAnte();

        this.primeiro = this.primeiro.getProx();

        this.primeiro.setAnte(ultimo);
        ultimo.setProx(this.primeiro);
        // System.gc();
    }
    
    public void removaItemDoFinal () throws Exception
    {
        if (this.primeiro==null)
            throw new Exception ("Nada a remover");

        if (this.primeiro.getProx()==this.primeiro) //so 1 elemento
        {
            this.primeiro=null;
            return;
        }

        No ultimo = this.primeiro.getAnte().getAnte();

        this.primeiro.setAnte(ultimo);
        ultimo.setProx(this.primeiro);
    }
    
    public int getQuantidade ()
    {   
        if (this.primeiro == null) return 0;

        No  atual=this.primeiro;
        int ret  =0;

        do{
            ret++;                
            atual = atual.getProx();
        }
        while (atual!=this.primeiro);
        
        
        return ret;
    }

    public boolean tem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        if (this.primeiro == null) return false;

        No atual=this.primeiro;

        do{
            if (i.equals(atual.getInfo()))
                return true;
                
            atual = atual.getProx();
        }
        while (atual!=this.primeiro);
            
        
        return false;
	}
	
	public void removaItemIndicado (X i) throws Exception
	{
        if (i==null)
            throw new Exception ("Informacao ausente");

        boolean removeu=false;

        for(;;) // FOR EVER (repete até break)
        {   
            if (this.primeiro==null)
                break;

            if (!i.equals(this.primeiro.getInfo()))
                break;

            if (this.primeiro.getProx() == this.primeiro){
                this.primeiro = null;
                removeu = true;
                break;
            }

            No ultimo = this.primeiro.getAnte();

            this.primeiro=this.primeiro.getProx();

            ultimo.setProx(this.primeiro);
            this.primeiro.setAnte(ultimo);

            removeu=true;
        }

        if (this.primeiro!=null)
        {
            No atual=this.primeiro;

            forever:for(;;) // repete ate break
            {
                if (atual.getProx()==this.primeiro)
                    break;

                while (i.equals(atual.getProx().getInfo()))
                {
                    atual.setProx(atual.getProx().getProx());
                    atual.getProx().setAnte(atual);

                    removeu=true;

                    if (atual.getProx()==this.primeiro)
                        break forever;
                }

                atual=atual.getProx();
            }
        }

        if (!removeu)
            throw new Exception ("Informacao inexistente");
	}	
	
	public void removaItemIndicado (int posicao) throws Exception
	{
        if (posicao<0)
            throw new Exception ("Posicao invalida");
            
        if (this.primeiro==null)
            throw new Exception ("Posicao invalida");
            
        if (posicao==0)
        {
            if (this.primeiro.getProx() == this.primeiro) {
                this.primeiro = null;
                return;
            }
            No ultimo = this.primeiro.getAnte();

			this.primeiro=this.primeiro.getProx();
            ultimo.setProx(this.primeiro);
            this.primeiro.setAnte(ultimo);
			return;
		}
		
        No atual;
        int posAtual;
        for (atual=this.primeiro, posAtual=0;
             atual.getProx().getProx()!=this.primeiro && posAtual!=posicao-1;
             atual=atual.getProx(),posAtual++)
             /*comando vazio*/;
             
        if (posAtual!=posicao-1)
            throw new Exception ("Posicao invalida");
            
        atual.setProx(atual.getProx().getProx());
        atual.getProx().setAnte(atual);
	}

    public boolean isVazia ()
    {
        return this.primeiro==null;
    }
    
    public String toString ()
    {   
        if (this.primeiro == null) return "[]";

        String ret="[";

        No atual=this.primeiro;

        do{
            ret=ret+atual.getInfo();

            if (atual.getProx()!=this.primeiro)
                ret=ret+",";

            atual=atual.getProx();
        }
        while (atual!=this.primeiro);

        return ret+"]";
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        ListaCircularBidiretiva<X> lista =
       (ListaCircularBidiretiva<X>)obj;

        No atualThis =this .primeiro;
        No atualLista=lista.primeiro;

        if (this.primeiro == null && lista.primeiro == null) return true; // se ambos forem vazios eles são iguais
        if (this.primeiro == null || lista.primeiro == null) return false; // mas se passou daquela linha e algum deles for null n são iguais xd

        do{
            if (!atualThis.getInfo().equals(atualLista.getInfo())) return false;

            atualThis  = atualThis .getProx();
            atualLista = atualLista.getProx();
        }
        while (atualThis!=this.primeiro && atualLista!=lista.primeiro);


        if (atualThis!=this.primeiro  /* && atualLista==null */)
            return false;

        if (atualLista!=lista.primeiro /* && atualThis ==null */)
            return false;

        // atualThis==null && atualLista==null
        return true;
    }

    
    public int hashCode ()
    {
        final int PRIMO = 13; // qualquer número primo serve
        
        int ret=666; // qualquer inteiro positivo serve

        if (this.primeiro == null) return ret;

        No atual = this.primeiro;
        do{
            ret = PRIMO*ret + atual.getInfo().hashCode();
            atual = atual.getProx();
        }
        while(atual != this.primeiro);

        if (ret<0) ret = -ret;

        return ret;
    }
    
    // construtor de copia
    public ListaCircularBidiretiva (ListaCircularBidiretiva<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        if (modelo.primeiro==null)
            return; // sai do construtor, pq this.primeiro ja é null

        this.primeiro = new No (modelo.primeiro.getInfo());

        No atualDoThis   = this  .primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while (atualDoModelo!=modelo.primeiro){
            atualDoThis.setProx (new No (atualDoModelo.getInfo()));
            atualDoThis.getProx().setAnte(atualDoThis);
            atualDoThis   = atualDoThis  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
        }

        atualDoThis.setProx(this.primeiro);
        this.primeiro.setAnte(atualDoThis);
    }

    public Object clone ()
    {
        ListaCircularBidiretiva<X> ret=null;

        try
        {
            ret = new ListaCircularBidiretiva (this);
        }
        catch (Exception erro)
        {} // sei que this NUNCA é null e o contrutor de copia da erro quando seu parametro é null

        return ret;
    }
}
