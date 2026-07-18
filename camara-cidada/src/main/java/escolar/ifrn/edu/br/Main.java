package escolar.ifrn.edu.br;

import escolar.ifrn.edu.br.Modelo.Parlamentar;
import escolar.ifrn.edu.br.Modelo.Proposicao;
import escolar.ifrn.edu.br.Modelo.Votacao;
import escolar.ifrn.edu.br.Modelo.Voto;
import escolar.ifrn.edu.br.Servico.ParlamentarServico;
import escolar.ifrn.edu.br.Servico.ProposicaoServico;
import escolar.ifrn.edu.br.Servico.VotacaoServico;

public class Main {
    public static void main(String[] args) {

        ParlamentarServico parlamentarServico = new ParlamentarServico();
        ProposicaoServico proposicaoServico = new ProposicaoServico();
        VotacaoServico votacaoServico = new VotacaoServico();

        System.out.println("\n--- [C] - INSERINDO Parlamentares no MySQL (Pré-criado via Workbench) ---");

        Parlamentar p1 = new Parlamentar();
        p1.setNomeCompleto("Maria do Céu Pereira de Araújo Fernandes");
        p1.setNomeParlamentar("Maria do Céu");
        p1.setPartido("UDN");
        p1.setLegislatura(1963);
        p1.setAtivo(true);

        Parlamentar p2 = new Parlamentar();
        p2.setNomeCompleto("Juvenal Lamartine de Faria");
        p2.setNomeParlamentar("Juvenal Lamartine");
        p2.setPartido("UDN");
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

        System.out.println("\n--- [C] - INSERINDO Proposições no MySQL ---");

        Proposicao pl1 = new Proposicao();
        pl1.setTitulo("Construção de Posto de Saúde no Bairro Norte");
        pl1.setNumeroProjeto(1);
        pl1.setAno(1963);
        pl1.setAutor(p1.getNomeParlamentar());
        pl1.setEmenta("Dispõe sobre a construção de um posto de saúde para atendimento à população do Bairro Norte.");
        pl1.setCategoria("Saúde");
        pl1.setStatus("Em Tramitação");
        pl1.setPdfProjeto("https://exemplo.com/pl-001-1963.pdf");

        Proposicao pl2 = new Proposicao();
        pl2.setTitulo("Criação de Escola Noturna para Adultos");
        pl2.setNumeroProjeto(2);
        pl2.setAno(1963);
        pl2.setAutor(p4.getNomeParlamentar());
        pl2.setEmenta("Institui escola noturna voltada à alfabetização de adultos no município.");
        pl2.setCategoria("Educação");
        pl2.setStatus("Em Tramitação");
        pl2.setPdfProjeto("https://exemplo.com/pl-002-1963.pdf");

        Proposicao pl3 = new Proposicao();
        pl3.setTitulo("Pavimentação da Estrada Vicinal do Distrito Sul");
        pl3.setNumeroProjeto(3);
        pl3.setAno(1963);
        pl3.setAutor(p5.getNomeParlamentar());
        pl3.setEmenta("Autoriza a pavimentação da estrada que liga o Distrito Sul à sede do município.");
        pl3.setCategoria("Infraestrutura");
        pl3.setStatus("Em Tramitação");
        pl3.setPdfProjeto("https://exemplo.com/pl-003-1963.pdf");

        proposicaoServico.cadastrarProposicao(pl1);
        proposicaoServico.cadastrarProposicao(pl2);
        proposicaoServico.cadastrarProposicao(pl3);

        System.out.println("\n--- [R] - SELECIONANDO e exibindo as proposições ---");
        proposicaoServico.listarProposicoes().forEach(System.out::println);

        System.out.println("\n--- [C] - REGISTRANDO Votação Nominal (RF03) ---");

        Votacao votacao1 = new Votacao();
        votacao1.setProposicao(pl1);

        votacao1.getVotos().add(criarVoto(p1, "SIM"));
        votacao1.getVotos().add(criarVoto(p2, "SIM"));
        votacao1.getVotos().add(criarVoto(p3, "NAO"));
        votacao1.getVotos().add(criarVoto(p4, "SIM"));
        votacao1.getVotos().add(criarVoto(p5, "ABSTENCAO"));
        votacao1.getVotos().add(criarVoto(p6, "SIM"));
        votacao1.getVotos().add(criarVoto(p7, "AUSENCIA"));

        votacaoServico.registrarVotacao(votacao1);

        System.out.println("\n--- [R] - SELECIONANDO e exibindo as votações ---");
        votacaoServico.listarVotacoes().forEach(v -> {
            System.out.println("Votação da proposição nº " + v.getProposicao().getNumeroProjeto() +
                                "/" + v.getProposicao().getAno() + " - Resultado: " + v.calcularResultado());
            v.getVotos().forEach(voto ->
                System.out.println("   " + voto.getParlamentar().getNomeParlamentar() + ": " + voto.getVoto())
            );
        });

        System.out.println("\n--- [U] - ATUALIZANDO status da Proposição após a votação ---");
        pl1.setStatus(votacao1.calcularResultado().equals("APROVADO") ? "Aprovado" : "Reprovado");
        proposicaoServico.alterarDadosProposicao(pl1);
        proposicaoServico.listarProposicoes().forEach(System.out::println);
    }

    private static Voto criarVoto(Parlamentar parlamentar, String voto) {
        Voto v = new Voto();
        v.setParlamentar(parlamentar);
        v.setVoto(voto);
        return v;
    }
}