package com.br.shakalinux.mariasaaplicacao.controller;

import com.br.shakalinux.mariasaaplicacao.dao.ClienteDao;
import com.br.shakalinux.mariasaaplicacao.dao.FuncionarioDao;
import com.br.shakalinux.mariasaaplicacao.dao.ProdutoDao;
import com.br.shakalinux.mariasaaplicacao.model.Area;
import com.br.shakalinux.mariasaaplicacao.model.Cliente;
import com.br.shakalinux.mariasaaplicacao.model.Funcionario;
import com.br.shakalinux.mariasaaplicacao.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GlobalController {
    private final ClienteDao clienteDao;
    private final FuncionarioDao funcionarioDao;
    private final ProdutoDao produtoDao;


    public GlobalController(ClienteDao clienteDao, FuncionarioDao funcionarioDao, ProdutoDao produtoDao) {
        this.clienteDao = clienteDao;
        this.funcionarioDao = funcionarioDao;
        this.produtoDao = produtoDao;
    }


    @GetMapping("/")
    public String listar(Model model) {
        try {
            List<Cliente> clientes = clienteDao.listarClientes();
            List<Funcionario> funcionarios = funcionarioDao.listarFuncionarios();
            List<Produto> produtos = produtoDao.listarProdutos();

            model.addAttribute("clientes", clientes);
            model.addAttribute("funcionarios", funcionarios);
            model.addAttribute("produtos", produtos);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return "home";
    }

    @GetMapping("/cadastrar-cliente")
    public String cadastroCliente(Model model) {
        return "cadastrarCliente";
    }

    @PostMapping("/cadastrar-cliente")
    public String cadastrarCliente(Cliente cliente) {
        clienteDao.cadastrarCliente(cliente);
        return "redirect:/";
    }
    

    @GetMapping("/cadastrar-funcionario")
    public String cadastrorFuncionario(Model model) {
        return "cadastrarFuncionario";
    }

    @PostMapping("/cadastrar-funcionario")
    public String cadastrarFuncionario(Funcionario funcionario, @RequestParam Long idArea) {
        Area area = new Area();
        area.setIdArea(idArea);
        funcionario.setArea(area);
        funcionarioDao.cadastrarFuncionario(funcionario);
        return "redirect:/";
    }
    
}
