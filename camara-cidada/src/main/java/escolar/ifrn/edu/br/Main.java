package escolar.ifrn.edu.br;

import escolar.ifrn.edu.br.Modelo.Parlamentar;
import escolar.ifrn.edu.br.Servico.ParlamentarServico;

public class Main {
    public static void main(String[] args) {
        ParlamentarServico parlamentarServico = new ParlamentarServico();

        System.out.println("\n--- [C] - INSERINDO Parlamentares no MySQL (Pré-criado via Workbench) ---");

        Parlamentar p1 = new Parlamentar();
        p1.setNomeCompleto("Maria do Céu Pereira de Araújo Fernandes");
        p1.setNomeParlamentar("Maria do Céu");
        p1.setPartido("UDN");
        p1.setLegislatura(1963);
        p1.setAtivo(true);

        Parlamentar p2 = new Parlamentar();
        p2.setNomeCompleto("Júlia Alves Barbosa Cavalcanti");
        p2.setNomeParlamentar("Júlia Alves Barbosa");
        p2.setPartido("PSD");
        p2.setLegislatura(1963);
        p2.setAtivo(true);

        Parlamentar p3 = new Parlamentar();
        p3.setNomeCompleto("Luíza Alzira Soriano Teixeira");
        p3.setNomeParlamentar("Alzira Soriano");
        p3.setPartido("PTB");
        p3.setLegislatura(1963);
        p3.setAtivo(true);

        Parlamentar p4 = new Parlamentar();
        p4.setNomeCompleto("Celina Guimarães Viana");
        p4.setNomeParlamentar("Celina Guimarães");
        p4.setPartido("PSD");
        p4.setLegislatura(1963);
        p4.setAtivo(true);

        Parlamentar p5 = new Parlamentar();
        p5.setNomeCompleto("Dinarte de Medeiros Mariz");
        p5.setNomeParlamentar("Dinarte Mariz");
        p5.setPartido("UDN");
        p5.setLegislatura(1963);
        p5.setAtivo(true);

        Parlamentar p6 = new Parlamentar();
        p6.setNomeCompleto("Aluísio Alves");
        p6.setNomeParlamentar("Aluísio Alves");
        p6.setPartido("UDN");
        p6.setLegislatura(1963);
        p6.setAtivo(true);

        Parlamentar p7 = new Parlamentar();
        p7.setNomeCompleto("Aristófanes Fernandes e Silva");
        p7.setNomeParlamentar("Aristófanes Fernandes");
        p7.setPartido("UDN");
        p7.setLegislatura(1963);
        p7.setAtivo(true);

        parlamentarServico.cadastrarParlamentar(p1);
        parlamentarServico.cadastrarParlamentar(p2);
        parlamentarServico.cadastrarParlamentar(p3);
        parlamentarServico.cadastrarParlamentar(p4);
        parlamentarServico.cadastrarParlamentar(p5);
        parlamentarServico.cadastrarParlamentar(p6);
        parlamentarServico.cadastrarParlamentar(p7);

        System.out.println("\n--- [R] - SELECIONANDO e exibindo os registros ---");
        parlamentarServico.listarParlamentares().forEach(System.out::println);

        System.out.println("\n--- [U] - ATUALIZANDO e Modificando Dados (RF01 - Editar) ---");
        p6.setPartido("PL"); // Exemplo de troca de partido (comum na época)
        parlamentarServico.alterarDadosParlamentar(p6);
        parlamentarServico.listarParlamentares().forEach(System.out::println);

        System.out.println("\n--- [D] - INATIVANDO um registro (RF01 - Licença/Renúncia) ---");
        parlamentarServico.inativarParlamentar(p3.getId());

        System.out.println("\nEstado final da tabela no MySQL:");
        parlamentarServico.listarParlamentares().forEach(System.out::println);
    }
}