package controle;

import com.shakalinux.modelo.Sexo;
import java.lang.reflect.Array;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Cliente;
import utils.Sexo;

public class ControleCliente {
    private Scanner read = new Scanner(System.in);
    private ArrayList<Cliente> listaCliente = new ArrayList<>();
    private int id = 0;
    
    public void cadastrarCliente(){
        System.out.println("Informe seu nome: ");
        String nome = read.nextLine();

        System.out.println("Informe seu email: ");
        String email = read.nextLine();

        System.out.println("Informe seu genero: [Masculino, Feminino, Outro]" );
        String genero = read.nextLine().toUpperCase();

        Sexo sexo = Sexo.valueOf(genero);
        if(sexo != null){
            Cliente cliente = new Cliente(id++, nome, email, sexo);
            listaCliente.add(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        }else{
            System.out.println("Genero inválido, tente novamente");
            return;
        }
    }


    public Cliente getClienteNome(String nome){
        for(Cliente cliente : listaCliente){
            if(cliente.getNome().equalsIgnoreCase(nome)){
                return cliente;
            }
        }
        return null;
    }






    
}
