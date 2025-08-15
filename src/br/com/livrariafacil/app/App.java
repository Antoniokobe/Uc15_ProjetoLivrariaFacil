package br.com.livrariafacil.app;


import br.com.livrariafacil.controller.LivrariaController;
import br.com.livrariafacil.view.TelaInicial;

/* Criada para evitar multiplas instancias do controlador */ 

public class App {
    public static LivrariaController sistema = new LivrariaController();
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}
